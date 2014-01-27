/**
 * Solution package for the hanoi-task from assignment 5 in WS13/14.
 */
package hanoi;

/**
 * The generic interface of a stack. A stack follows the LIFO (last in - first
 * out) principle.
 * 
 * @param <E>
 *            the type of the elements to be stacked
 * 
 * @author markus
 * @version 1
 */
public interface Stack<E> {
    /**
     * Puts an element on top of the stack.
     * 
     * @param element
     *            The element to put on top of the stack
     * @return true if the operation was successful, false otherwise
     */
    boolean push(E element);

    /**
     * Removes the top-level element from the stack and returns it.
     * 
     * @return the former top-level element on the stack
     */
    E pop();

    /**
     * Returns the top-level element of the stack. The element remains on the
     * stack.
     * 
     * @return the top-level element of the stack
     */
    E top();

    /**
     * Returns the number of elements on the stack.
     * 
     * @return the number of elements on the stack
     */
    int size();
}
