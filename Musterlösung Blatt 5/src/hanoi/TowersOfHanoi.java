package hanoi;

import java.util.ArrayList;

/**
 * Solves the Towers of Hanoi problem.
 * 
 * @author Die Tutoren
 * @version 1.0
 */
public class TowersOfHanoi {
    private HanoiStack[] stacks = null;
    private int count;

    /**
     * Creates a new TowersOfHanoi.
     * 
     * @param initCount
     *            the number of disks used
     */
    public TowersOfHanoi(int initCount) {
        this.count = initCount;
        initEmptyStacks();
    }

    private void initEmptyStacks() {
        stacks = new HanoiStack[] {//
        new HanoiStack(), new HanoiStack(), new HanoiStack() };
    }

    /**
     * inits the default configuration. All disks left.
     */
    public void initDefault() {
        initEmptyStacks();
        for (int i = 0; i < count; i++) {
            stacks[0].push(new HanoiDisk(count - i));
        }

    }

    /**
     * init an arbitrary configuration.
     * 
     * @param a
     *            radiuses on stack a
     * @param b
     *            radiuses on stack b
     * @param c
     *            radiuses on stack c
     */
    public void initShaker(int[] a, int[] b, int[] c) {
        initEmptyStacks();
        if (checkAllValues(a, b, c) == false) {
            throw new IllegalArgumentException(
                    "not all values present or there are duplicates");
        } else if (checkDescending(a) == false || checkDescending(b) == false
                || checkDescending(c) == false) {
            throw new IllegalArgumentException(
                    "there are non-descending values");
        } else {
            for (int i = 0; i < a.length; i++) {
                stacks[0].push(new HanoiDisk(a[i]));
            }
            for (int i = 0; i < b.length; i++) {
                stacks[1].push(new HanoiDisk(b[i]));
            }
            for (int i = 0; i < c.length; i++) {
                stacks[2].push(new HanoiDisk(c[i]));
            }
        }

    }

    /**
     * Moves n disks from a to c using b as help stack.
     * 
     * @param n
     *            number of disks
     * @param a
     *            stack to take the disks
     * @param c
     *            stack to put the disks
     * @param b
     *            stack to use as help
     */
    public void move(int n, HanoiStack a, HanoiStack c, HanoiStack b) {
        if (n > 0) {
            move(n - 1, a, b, c);
            if (!c.push(a.pop())) {
                throw new Error("Algorithm is bad.");
            }
            output();
            move(n - 1, b, c, a);
        }
    }

    private void output() {
        DefaultStack<StringBuffer> buf = new DefaultStack<>();
        for (int i = 0; i < count; i++) {
            buf.push(new StringBuffer());
        }
        appendStack(buf, stacks[0], false);
        appendStack(buf, stacks[1], true);
        appendStack(buf, stacks[2], true);
        while (buf.size() > 0) {
            System.out.println(buf.pop().toString());
        }
        System.out.println("---");
    }

    /**
     * Outputs a Hanoi Stack to a Stack of String Buffers. Adding new lines, if
     * needed.
     * 
     * @param buf
     *            the output buffers
     * @param stack
     *            the stack to output
     * @param addSpace
     *            true, iff a space should be appended to each line
     */
    private void appendStack(DefaultStack<StringBuffer> buf, HanoiStack stack,
            boolean addSpace) {
        DefaultStack<HanoiDisk> tmp = new DefaultStack<>();
        DefaultStack<StringBuffer> tmpBuf = new DefaultStack<>();

        buf.flipTo(tmpBuf);
        stack.flipTo(tmp);
        while (tmp.size() > 0) {
            HanoiDisk disk = tmp.pop();
            stack.push(disk);
            StringBuffer line = tmpBuf.pop();
            if (addSpace) {
                line.append(' ');
            }
            line.append(pad(disk.toString()));
            buf.push(line);
        }
        while (tmpBuf.size() > 0) {
            StringBuffer line = tmpBuf.pop();
            if (addSpace) {
                line.append(' ');
            }
            line.append(pad(" "));
            buf.push(line);
        }
    }

    private String pad(String textIn) {
        String text = textIn;
        while (text.length() < count * 2 - 1) {
            text = ' ' + text + ' ';
        }
        return text;
    }

    /**
     * Returns the i'th stack.
     * 
     * @param i
     *            number of stack (0 to 2)
     * @return the i'th stack.
     */
    public HanoiStack getStack(int i) {
        return stacks[i];
    }

    /**
     * Moves all disks to the given target stack.
     * 
     * @param to
     *            the stack index to move them to.
     */
    public void solve(int to) {
        int position = stackOf(1);
        for (int current = 2; current <= count; current++) {
            int stack = stackOf(current);
            if (stack != -1) {
                move(current - 1, stacks[position], stacks[stack],
                        stacks[other(position, stack)]);
                position = stack;
            }
        }
        if (position != to) {
            move(count, stacks[position], stacks[to],
                    stacks[other(position, to)]);
        }
    }

    private static int other(int position, int stack) {
        if ((position == 0 && stack == 1) || (position == 1 && stack == 0)) {
            return 2;
        }
        if ((position == 0 && stack == 2) || (position == 2 && stack == 0)) {
            return 1;
        }
        return 0;
    }

    private int stackOf(int current) {
        for (int i = 0; i < stacks.length; i++) {
            if (stacks[i].top() != null && stacks[i].top().getSize() == current) {
                return i;
            }
        }
        return -1;
    }

    private boolean checkDescending(int[] config) {
        boolean isDescending = true;
        int value = Integer.MAX_VALUE;
        for (int i = 0; i < config.length; i++) {
            if (config[i] > value) {
                isDescending = false;
            }
            value = config[i];
        }
        return isDescending;
    }

    private boolean checkAllValues(int[] configA, int[] configB, int[] configC) {
        ArrayList<Integer> intListA = new ArrayList<Integer>();
        ArrayList<Integer> intListB = new ArrayList<Integer>();
        ArrayList<Integer> intListC = new ArrayList<Integer>();
        for (int x : configA) {
            intListA.add(x);
        }
        for (int x : configB) {
            intListB.add(x);
        }
        for (int x : configC) {
            intListC.add(x);
        }
        boolean allValues = true;
        for (int i = 1; i <= count; i++) {
            if (!intListA.contains(i) && !intListB.contains(i)
                    && !intListC.contains(i)) {
                allValues = false;
            }
        }
        if ((intListA.size() + intListB.size() + intListC.size()) > count) {
            allValues = false;
        }
        return allValues;
    }

    private static int[] toIntArray(String[] stringArray) {
        int[] intArray = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            intArray[i] = Integer.parseInt(stringArray[i]);
        }
        return intArray;
    }

    /**
     * Solves task A (arguments only a count) and task B.
     * 
     * @param args
     *            the commandline arguments
     */
    public static void main(String[] args) {
        if (args.length != 1 && args.length != 2) {
            System.out.println("Invalid number of arguments");
            System.exit(0);
        }
        int count = 0;
        try {
            count = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
            System.exit(0);
        }
        TowersOfHanoi theTowers = new TowersOfHanoi(count);
        if (args.length == 1) {
            theTowers.initDefault();
            theTowers.output();
            theTowers.move(count, theTowers.getStack(0), theTowers.getStack(2),
                    theTowers.getStack(1));
        } else {
            String[] towerConfigs = args[1].split(";");
            if (towerConfigs.length != 3) {
                System.out.println("Invalid number of towers");
                System.exit(0);
            }
            try {
                int[] towerConfigA = toIntArray(towerConfigs[0].split(","));
                int[] towerConfigB = toIntArray(towerConfigs[1].split(","));
                int[] towerConfigC = toIntArray(towerConfigs[2].split(","));
                theTowers.initShaker(towerConfigA, towerConfigB, towerConfigC);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
                System.exit(0);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
            theTowers.output();
            theTowers.solve(2);
        }
    }

}
