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
	private int level;
	private int balance;
	private boolean black;
	
	// Constructor
	public Node()
	{
		root = null;
		leftNode = null;
		rightNode = null;
		key = null;
		value = null;
		level = -1;
		balance = 99;
		black = true;
	}
	
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
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public boolean isBlack() {
		return black;
	}
	public void setBlack(boolean black) {
		this.black = black;
	}
}
