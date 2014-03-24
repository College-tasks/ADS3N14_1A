package structures;

public class SortedList<T extends Comparable<T>>
				extends LinkedList<T>
{	
	public Node<T> searchNode(Node<T> needle)
	{
		Node<T> current = getHead();
		Node<T> previous = null;
		T keyNeedle = needle.getKey();

		while (current != null) {
			T keyCurrent = current.getKey();
			int cmp = keyNeedle.compareTo(keyCurrent);
			if (cmp == 0)
			{
				return current;
			}
			if (cmp < 0)
			{
				return previous;
			}
			previous = current;
			current = current.getNext();
		}
		return previous;
	}

	public Node<T> getPrevious(Node<T> needle)
	{
		Node<T> current = getHead();
		Node<T> previous = null;
		T keyNeedle = needle.getKey();

		while (current != null) {
			T keyCurrent = current.getKey();
			int cmp = keyNeedle.compareTo(keyCurrent);
			if (cmp == 0)
			{
				return previous;
			}
			if (cmp < 0)
			{
				return previous;
			}
			previous = current;
			current = current.getNext();
		}
		return previous;
	}

	@Override
	public void append(Node<T> newNode)
	{
		insert(newNode);
	}

	@Override
	public void insert(Node<T> newNode, Node<T> previous)
	{
		insert(newNode);
	}

	@Override
	public void insert(Node<T> newNode)
	{
		Node<T> previous = searchNode(newNode);

		if (previous == null) {
			newNode.setNext(head);
			head = newNode;
			if (tail == null)
				tail = newNode;
		} else {
			newNode.setNext(previous.getNext());
			previous.setNext(newNode);
			if (tail == previous)
				tail = newNode;
		}

	}
}