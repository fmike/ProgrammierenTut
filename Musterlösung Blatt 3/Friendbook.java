/**
 * Friendbook is a class which represents the friendbook of a given owner.
 * The class stores all the entries of the owners friends and offers
 * some functionality to add or remove an entry or calculate some
 * statistics about all the entries (favorite color and
 * women quota)
 *
 * Question answer:
 *
 * The following relationships have to hold:
 *  0 < INITIAL_SIZE <= MAX_SIZE
 *  1 < GROW_FACTOR < SHRINK_FACTOR
 *
 * Longer Explanation for GROW_FACTOR < SHRINK_FACTOR:
 *  If the shrink factor is lower than the grow factor the condition in removeFriend will evaluate
 *  to true after the first resize and create an unnecessary overhead.
 *
 * Example: grow = 2, shrink = 1
 *  After the first resize in addFriend the size of the array is 30 and the amount of friends is 15.
 *  The if condition if (entries.length > SHRINK_FACTOR * nFriends) in removeFriend (30 > 15) will always
 *  evaluate to true from this point on and resize the array length to nFriends * grow (30) + copy all the entries.
 *  -> Unwanted overhead for no reason everytime we remove an entry
 *
 * @author Christoph
 * @version 1.1
 */
class Friendbook {
    /**
     * The default size of the entries array.
     * Important: this value should be greater than zero and smaller than <code>MAX_SIZE</code>
     */
    private static final int INITIAL_SIZE = 15;

    /**
     * Maximum size of the entries array.
     * The maximum amount of allowed friends.
     * Important: this value should be greater than <code>INITIAL_SIZE</code>
     */
    private static final int MAX_SIZE = 2000000;

    /**
     * Grow factor of the entries array.
     * Increase array-size by this factor if it is full.
     * Important: this value should be lower then <code>SHRINK_FACTOR</code> and greater than one.
     */
    private static final int GROW_FACTOR = 2;

    /**
     * Shrink factor of the array.
     * Decrease the size of the array to <code>GROW_FACTOR</code> times <code>nFriends</code>
     * if <code>nFriends</code> is by this factor smaller than the total array size.
     * Important: this value should be greater then <code>GROW_FACTOR</code>
     */
    private static final int SHRINK_FACTOR = 4;

    /**
     * The owner of this friendbook.
     */
    private String owner;

    /**
     * The amount of friends which have already made an entry in the book
     */
    private int nFriends = 0;

    /**
     * The array which stores all the friendbook entries
     */
    private FriendbookEntry[] entries;

    /**
     * Constructs a new Friendbook instance and initializes all the attributes.
     * @param owner the owner of the friendbook
     */
    public Friendbook(String owner) {
        this.owner = owner;
        this.entries = new FriendbookEntry[INITIAL_SIZE];
    }

    /**
     * Looks up the friendbook entry in the entries array at a given index
     * returns the entry if the index is valid (0 <= i < nFriends) otherwise null
     * @param i the index of the desired entry
     * @return the entry in this friendbook at index i
     */
    public FriendbookEntry getFriend(int i) {
        if (i < nFriends && i >= 0) {
            return entries[i];
        } else {
            return null;
        }
    }

    /**
     * Adds an entry for a given friend if the owner didn't exceed the maximum friend limit.
     * @param author the author of the new entry to the friendbook
     * @return returns null if the entry couldn't be appended to the entry list otherwise the new entry
     */
    public FriendbookEntry addFriend(Author author) {
        // only insert a friend if the owners friends limit isn't reached yet
        if (nFriends == MAX_SIZE) {
            return null;
        } else {
            // check if we have to increase the array's size
            if (nFriends == entries.length) {
                int newSize = entries.length * GROW_FACTOR;

                // clamp the limit of the size to MAX_SIZE
                if (newSize > MAX_SIZE)
                    newSize = MAX_SIZE;

                // create a new array and copy all the old entries
                FriendbookEntry[] newEntries = new FriendbookEntry[newSize];
                for (int i = 0; i < nFriends; i++) {
                    newEntries[i] = entries[i];
                }
                entries = newEntries;
            }
        }
        // create new entry
        FriendbookEntry entry = new FriendbookEntry(author);
        entries[nFriends++] = entry;
        return entry;
    }

    /**
     * Removes a friendbook entry from the friend list.
     * @param entry the entry which shall be removed from the list
     */
    public void removeFriend(FriendbookEntry entry) {
        // find and remove entry, fill the gap with the last element
        for (int i = 0; i < nFriends; i++) {
            if (entries[i] == entry) {
                entries[i] = entries[--nFriends];
                entries[nFriends] = null;
            }
        }

        // create smaller array if it is too large
        if (entries.length > SHRINK_FACTOR * nFriends) {
            int newSize = nFriends * GROW_FACTOR;
            if (newSize < INITIAL_SIZE)
                newSize = INITIAL_SIZE;

            FriendbookEntry[] newEntries = new FriendbookEntry[newSize];
            for (int i = 0; i < nFriends; i++) {
                newEntries[i] = entries[i];
            }
            entries = newEntries;
        }
    }

    /**
     * This method calculates the favorite color amongst all entries in the friendbook and returns the result.
     * If there are multiple colors which are equally popular the result is one of those colors.
     * @return the favorite color amongst all friendbook entries
     */
    public Color getFavoriteColor() {
        ColorCounter counter = new ColorCounter();

        for (int i = 0; i < nFriends; i++) {
            counter.add(entries[i].author.preferences.favColor);
        }

        return counter.getFavoriteColor();
    }

    /**
     * Returns the womens quota amongst all friendbook entry in percent
     * @return the womens quota of all friendbook entries
     */
    public double getWomensQuota() {
        int sum = 0;

        for (int i = 0; i < nFriends; i++) {
            if (entries[i].author.sex == Sex.FEMALE) {
                sum++;
            }
        }

        if (nFriends > 0) {
            return (100.0 * sum) / nFriends;
        } else {
            return 100.0;
        }
    }
}
