/* Name: Maricruz Tolosa Amaya
 * Net ID: mtolosaa
 * ID#: 29988518
 * CSC 172 Project#1
 * Lab: MW 2:00-3:15
 * 
 */
public interface QueueListInterface <T> {
	public void insert(T x);
	public T delete();
	public boolean isPresent(T x);
	public T lookup(T x);
	public boolean isEmpty();
	
	
}
