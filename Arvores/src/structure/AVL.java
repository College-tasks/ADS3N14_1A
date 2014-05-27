package structure;

/**
 * Class that defines a AVL Tree (Structure)
 * @author Simor
 *
 * @param <T> "Value" of the Node
 * @param <S> "Key" of the Node
 */
public class AVL<T extends Comparable<T>, S extends Comparable<S>> extends Tree<T, S> {
	private int comp;
	private int rot;
	public int compTotal = 0;
	public int rotTotal = 0;
	
	/**
	 * Add a Node to the Tree
	 * @param node Node to be added
	 * @param showCount If it will show the Count of Nodes and the Level of the added Node
	 */
	public void addNodeAVL(Node<T, S> node, boolean showCount)
	{
		compTotal += comp;
		rotTotal += rot;
		comp = 0;
		rot = 0;
		
		addNodeAVL(node, getRoot(), showCount);
	}
	
	/**
	 * Add a Node to the Tree
	 * @param node Node to be added
	 * @param root The root of the Node (Recursive)
	 * @param level Level of the Node (Recursive)
	 * @param showCount If it will show the Count of Nodes and the Level of the added Node
	 */
	private void addNodeAVL(Node<T, S> node, Node<T, S> root, boolean showCount)
	{
		if (root == null)
		{
			root = getRoot();
		}
		
		// If Root is null, new node will be the root
		if (getRoot() == null)
		{
			setRoot(node);
			if (showCount)
			{
				// Show the tree
				super.showTree();
				return;
			}
		}
		else
		{
			// If node >= root
			comp++;
			if(root.getKey().compareTo(node.getKey()) <= 0)
			{
				if (root.getRightNode() != null)
				{
					addNodeAVL(node, root.getRightNode(), showCount);
				}
				else
				{
					node.setRoot(root);
					root.setRightNode(node);
					resetTree();
					balanceTree(showCount);
					if (showCount)
					{
						// Show the Tree
						this.showRotComp();
						super.showTree();
						super.showRootLevel();
					}
				}
			}
			// If node < root
			else 
			{
				if (root.getLeftNode() != null)
				{
					addNodeAVL(node, root.getLeftNode(), showCount);
				}
				else
				{
					node.setRoot(root);
					root.setLeftNode(node);
					resetTree();
					balanceTree(showCount);
					if (showCount)
					{
						// Show the Tree
						super.showRootLevel();
						this.showRotComp();
						super.showTree();						
					}
				}
			}
		}
	}

	// Delete Node
	/**
	 * Delete a Node from the Tree
	 * @param node Node to be deleted
	 */
	@Override
	public void deleteNode(Node<T, S> node)
	{		
		compTotal += comp;
		rotTotal += rot;
		comp = 0;
		rot = 0;
		
		Node<T, S> root = node.getRoot();
		
		// Leaf
		comp++;
		if (node.getLeftNode() == null && node.getRightNode() == null)
		{
			// "Delete" the node
			comp++;
			if (root.getLeftNode() != null && root.getLeftNode() == node)
			{
				root.setLeftNode(null);
				balanceTree(true);
			}
			else 
			{
				root.setRightNode(null);
				balanceTree(true);
			}
		}
		// One Child
		else if (node.getLeftNode() != null ^ node.getRightNode() != null)
		{
			comp++;
			// Swap the child node
			if (node.getLeftNode() != null)
			{
				comp++;
				if (root.getLeftNode() != null && root.getLeftNode() == node)
				{
					root.setLeftNode(node.getLeftNode());
					balanceTree(true);
				}
				else 
				{
					root.setRightNode(node.getLeftNode());
					balanceTree(true);
				}
			}
			else
			{
				comp++;
				if (root.getLeftNode() != null && root.getLeftNode() == node)
				{
					root.setLeftNode(node.getRightNode());
					balanceTree(true);
				}
				else 
				{
					root.setRightNode(node.getRightNode());
					balanceTree(true);
				}
			}
		}
		// More than one child
		else 
		{
			Node<T, S> rightNode = node.getRightNode();
			Node<T, S> leftNode = node.getLeftNode();
			
			comp++;
			if (root != null)
			{
				// "Delete" the node
				comp++;
				if (root.getLeftNode() != null && root.getLeftNode() == node)
				{
					root.setLeftNode(null);
				}
				else 
				{
					root.setRightNode(null);
				}
				
				addNodeAVL(leftNode, false);
				addNodeAVL(rightNode, false);
				
				balanceTree(true);
			}
			else
			{
				setRoot(null);
				addNodeAVL(leftNode, false);
				addNodeAVL(rightNode, false);
				
				balanceTree(true);
			}
		}
		// Show the Tree
		super.showRootLevel();
		this.showRotComp();
		this.showTree();
		
		
	}

	/**
	 * Balances a tree
	 */
	private void balanceTree(boolean showMsg)
	{
		balanceTree(getRoot(), showMsg);
	}
	
	/**
	 * Balances a tree
	 * @param node Node to be compared to
	 */
	private void balanceTree(Node<T, S> node, boolean showMsg)
	{
		
		// Leaf
		if (node.getLeftNode() == null && node.getRightNode() == null)
		{
			node.setBalance(0);
			node.setLevel(1);
			return;
		}
		
		// Checks if the child nodes are balanced
		if (node.getLeftNode() != null && node.getLeftNode().getLevel() == -1) balanceTree(node.getLeftNode(), showMsg);
		if (node.getRightNode() != null && node.getRightNode().getLevel() == -1) balanceTree(node.getRightNode(), showMsg);

		// Sets the level of the node
		if (node.getLeftNode() == null)
		{
			node.setLevel(node.getRightNode().getLevel() + 1);
		}
		else if (node.getRightNode() == null)
		{
			node.setLevel(node.getLeftNode().getLevel() + 1);
		}
		else if (node.getLeftNode().getLevel() >= node.getRightNode().getLevel())
		{
			node.setLevel(node.getLeftNode().getLevel() + 1);
		}
		else
		{
			node.setLevel(node.getRightNode().getLevel() + 1);
		}
		
		// Sets the balance
		int levelLeft = node.getLeftNode() == null ? 0 : node.getLeftNode().getLevel();
		int levelRight = node.getRightNode() == null ? 0 : node.getRightNode().getLevel();
		node.setBalance(levelLeft - levelRight);
		
		// Verifies if the node is balanced
		if (node.getBalance() == 0 || node.getBalance() == 1 || node.getBalance() == -1) return;
		
		// If not balanced
		if (node.getBalance() == 2)
		{
			if (node.getLeftNode().getBalance() == -1)
			{
				leftRotation(node.getLeftNode());
				rot++;
			}
			rightRotation(node);
			rot++;
			resetTree();
			balanceTree(showMsg);
		}
		else
		{
			if (node.getRightNode().getBalance() == 1)
			{
				rightRotation(node.getRightNode());
				rot++;
			}
			leftRotation(node);
			rot++;
			resetTree();
			balanceTree(showMsg);
		}
		
		// Balanced Root
		if (node.getRoot() == null && node.getBalance() != 99) return;
	}

	/**
	 * Shows the number of rotations and comparisons made
	 */
	private void showRotComp()
	{
		System.out.println("Rotações: " + rot);
		System.out.println("Comparações: " + comp);
	}
}
