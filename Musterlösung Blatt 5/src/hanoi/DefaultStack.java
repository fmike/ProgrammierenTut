package hanoi;

/**
 * The default linked implementation of a stack.
 * 
 * @author Die Tutoren
 * @version 1.0
 * @param <E>
 *            the object type to stack.
 */
public class DefaultStack<E> implements Stack<E> {
    private StackCell<E> head = null;

    private int size;

    /**
     * Creates a new DefaultStack.
     */
    public DefaultStack() {
    }

    @Override
    public boolean push(E element) {
        head = new StackCell<E>(element, head);
        size++;
        return true;
    }

    @Override
    public E pop() {
        E elem = top();
        if (elem == null) {
            return null;
        }
        head = head.next;
        size--;
        return elem;
    }

    @Override
    public E top() {
        if (head == null) {
            return null;
        }
        return head.content;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Flips the whole stack to the target stack.
     * 
     * After this action this stack is empty and all its contents is completely
     * on target, but reversed.
     * 
     * @param target
     *            the stack to flip this stack to.
     */
    public void flipTo(Stack<E> target) {
        while (size > 0) {
            target.push(pop());
        }
    }

    private static class StackCell<T> {
        private StackCell<T> next;
        private T content;

        public StackCell(T content, StackCell<T> next) {
            this.content = content;
            this.next = next;
        }

    }
}
