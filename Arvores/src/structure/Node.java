package structure;

/**
 * Class that defines a Node from the Tree
 * @author Simor
 *
 * @param <T> "Value" of the Node
 */
public class Node<T> {
	// Attributes
	private Node<T> root;
	private Node<T> leftNode;
	private Node<T> rightNode;
	private String key;
	private T value;
	
	// Getters & Setters
	public Node<T> getRoot() {
		return root;
	}
	public void setRoot(Node<T> root) {
		this.root = root;
	}
	public Node<T> getLeftNode() {
		return leftNode;
	}
	public void setLeftNode(Node<T> leftNode) {
		this.leftNode = leftNode;
	}
	public Node<T> getRightNode() {
		return rightNode;
	}
	public void setRightNode(Node<T> rightNode) {
		this.rightNode = rightNode;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
}
