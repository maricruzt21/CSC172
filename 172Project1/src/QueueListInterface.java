/* Name: Maricruz
 */
public interface QueueListInterface <T> {
	public void insert(T x);
	public T delete();
	public boolean isPresent(T x);
	public T lookup(T x);
	public boolean isEmpty();
	
	
}
