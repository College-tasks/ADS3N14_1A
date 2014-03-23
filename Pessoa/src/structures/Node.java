package structures;

public class Node<T> {
	private T key;
	private Node<T> next;

	public Node()
	{
		key = null;
		next = null;
	}

	public T getKey() {
		return key;
	}

	public void setKey(T key) {
		this.key = key;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}
}