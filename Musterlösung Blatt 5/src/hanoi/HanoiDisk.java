package hanoi;

/**
 * A disk on the Towers of Hanoi.
 * 
 * @author Die Tutoren
 * @version 1.0
 */
public class HanoiDisk implements Comparable<HanoiDisk> {
    private int size;

    /**
     * Creates a new HanoiDisk.
     * 
     * @param size
     *            the radius
     */
    public HanoiDisk(int size) {
        this.size = size;
    }

    @Override
    public int compareTo(HanoiDisk o) {
        return Integer.compare(size, o.size);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < size * 2 - 1; i++) {
            sb.append('#');
        }
        return sb.toString();
    }

    /**
     * Returns this disks size.
     * 
     * @return this disks size
     */
    public int getSize() {
        return size;
    }
}
