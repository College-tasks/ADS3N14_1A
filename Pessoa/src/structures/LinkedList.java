package structures;

public class LinkedList<T> {

	protected Node<T> head;
	protected Node<T> tail;

	public LinkedList() {
		head = null;
		tail = null;
	}

	public void insert(Node<T> newNode)
	{
		newNode.setNext(head);
		head = newNode;
		if (tail == null)
			tail = newNode;
	}

	public void insert(Node<T> newNode, Node<T> previous)
	{
		if (previous == null) {
			newNode.setNext(head);
			head = newNode;
			if (tail == null)
				tail = head;
		} else {
			newNode.setNext(previous.getNext());
			previous.setNext(newNode);
			if (previous == tail)
				tail = newNode;
		}
	}

	public void append(Node<T> newNode)
	{
		if (tail != null) {
			tail.setNext(newNode);
		} else {
			head = newNode;
		}
		tail = newNode;
	}

	public Node<T> getTail()
	{
		return tail;
	}

	public Node<T> getHead()
	{
		return head;
	}

}