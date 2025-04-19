public class TwoStackQueue<T> implements TwoStackQueueInterface<T>
{

public static void main(String[] args){}
    
    private MyStack<T> stack1;
	private MyStack<T> stack2;
	private int size;
	
    
	public TwoStackQueue()
	{
		stack1 = new MyStack<T>();
		stack2 = new MyStack<T>();
		size = 0;
	}
	
    public void enqueue(T x)
    {
    	stack1.push(x);
    	size++;
    }

    public T dequeue() {
    if (stack2.isEmpty()) {
      while (!stack1.isEmpty()) {
        stack2.push(stack1.pop());
      }
    }

    size--;
    return stack2.pop();
  }

    public boolean isEmpty()
    {
    	
    	return size == 0;
    }

    public int size()
    {
    	return size;
    }
}
