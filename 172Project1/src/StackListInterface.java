/* Name: Maricruz 
 */

public interface StackListInterface<T> {
	public void add(T x);
	public T delete();
	public boolean present(T x); 
	public T search(T x);
	public boolean isEmpty();
	public void printList();
	boolean isPresent(T x);
}
