/* Name: Maricruz Tolosa Amaya
 * Net ID: mtolosaa
 * ID#: 29988518
 * CSC 172 Project#1
 * Lab: MW 2:00-3:15
 * 
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
