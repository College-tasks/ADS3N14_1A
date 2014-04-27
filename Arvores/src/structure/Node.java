package structure;

/**
 * Class that defines a Node from the Tree
 * @author Simor
 *
 * @param <T> "Value" of the Node
 */
public class Node<T, S> {
	// Attributes
	private Node<T, S> root;
	private Node<T, S> leftNode;
	private Node<T, S> rightNode;
	private S key;
	private T value;
	
	// Getters & Setters
	public Node<T, S> getRoot() {
		return root;
	}
	public void setRoot(Node<T, S> root) {
		this.root = root;
	}
	public Node<T, S> getLeftNode() {
		return leftNode;
	}
	public void setLeftNode(Node<T, S> leftNode) {
		this.leftNode = leftNode;
	}
	public Node<T, S> getRightNode() {
		return rightNode;
	}
	public void setRightNode(Node<T, S> rightNode) {
		this.rightNode = rightNode;
	}
	public S getKey() {
		return key;
	}
	public void setKey(S key) {
		this.key = key;
	}
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
}
