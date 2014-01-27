package hanoi;

/**
 * A Stack of HanoiDisks. Prohibiting stacking in the wrong order.
 * 
 * @author Die Tutoren
 * @version 1.0
 */
public class HanoiStack extends DefaultStack<HanoiDisk> {
    /**
     * 
     * Creates a new HanoiStack.
     */
    public HanoiStack() {
    }

    @Override
    public boolean push(HanoiDisk element) {
        HanoiDisk top = top();
        if (top != null && top.compareTo(element) <= 0) {
            return false;
        }
        return super.push(element);
    }
}
