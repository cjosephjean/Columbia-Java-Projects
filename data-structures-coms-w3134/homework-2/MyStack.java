import java.util.LinkedList;

public class MyStack<T> implements MyStackInterface<T>{
    
public static void main(String[] args){}
        
    private LinkedList<T> linkedlist;
    
    public MyStack()
	{
		linkedlist = new LinkedList<T>();
	}
	
	public T pop()
	{
		return (linkedlist.size() == 0) ? null : linkedlist.remove();
	}
	
	public void push(T a)
	{
		linkedlist.addFirst(a);
	}
	
	public T peek()
	{
		return linkedlist.getFirst();
	}
	
	public boolean isEmpty()
	{
		return linkedlist.isEmpty();
	}
    
    public int size()
    {
        return linkedlist.size();
    }
    
}
