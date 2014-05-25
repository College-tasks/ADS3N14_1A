package structure;

/**
 * Class that defines a Tree (Structure)
 * @author Simor
 *
 * @param <T> "Value" of the Node
 * @param <S> "Key" of the Node
 */
public class Tree <T extends Comparable<T>, S extends Comparable<S>> {
	// Attributes
	private Node<T, S> root;

	// Getters & Setters
	public Node<T, S> getRoot() {
		return root;
	}

	public void setRoot(Node<T, S> root) {
		this.root = root;
	}

	// Get Fist and Last
	/**
	 * Gets the first Node (Order)
	 * @return The first Node
	 */
	public Node<T, S> getFirst()
	{
		return getFirst(getRoot());
	}
	
	/**
	 * Gets the first Node (Order) - Recursive
	 * @param root Root node
	 * @return The first Node
	 */
	private Node<T, S> getFirst(Node<T, S> root)
	{
		if (root.getLeftNode() == null)
		{
			return root;
		}
		else
		{
			return getFirst(root.getLeftNode());
		}
	}
	
	/**
	 * Gets the last Node (Order)
	 * @return The last Node
	 */
	public Node<T, S> getLast()
	{
		return getLast(getRoot());
	}
	
	/**
	 * Gets the last Node (Order)
	 * @param root Root node
	 * @return The last Node
	 */
	private Node<T, S> getLast(Node<T, S> root)
	{
		if (root.getRightNode() == null)
		{
			return root;
		}
		else
		{
			return getLast(root.getRightNode());
		}
	}
	
	// Add Node
	/**
	 * Add a Node to the Tree
	 * @param node Node to be added
	 * @param showCount If it will show the Count of Nodes and the Level of the added Node
	 */
	public void addNode(Node<T, S> node, boolean showCount)
	{
		addNode(node, getRoot(), 0, showCount);
	}
	
	/**
	 * Add a Node to the Tree
	 * @param node Node to be added
	 * @param root The root of the Node (Recursive)
	 * @param level Level of the Node (Recursive)
	 * @param showCount If it will show the Count of Nodes and the Level of the added Node
	 */
	private int addNode(Node<T, S> node, Node<T, S> root, int level, boolean showCount)
	{
		if (root == null)
		{
			level = 0;
			root = getRoot();
		}
		
		// If Root is null, new node will be the root
		if (getRoot() == null)
		{
			setRoot(node);
			if (showCount)
			{
				// Show the level of the added Node and the Count of the Nodes
				System.out.println("======================================");
				System.out.println("O Nó foi adicionado no nível {" + level + "}.");
				System.out.println("A contagem total de Nós é de: " + countNodes());
				System.out.println("======================================");
			}
		}
		else
		{
			// If node >= root
			if(root.getKey().compareTo(node.getKey()) <= 0)
			{
				level++;
				if (root.getRightNode() != null)
				{
					level = addNode(node, root.getRightNode(), level, showCount);
				}
				else
				{
					node.setRoot(root);
					root.setRightNode(node);
					if (showCount)
					{
						// Show the level of the added Node and the Count of the Nodes
						System.out.println("======================================");
						System.out.println("O Nó foi adicionado no nível {" + level + "}.");
						System.out.println("A contagem total de Nós é de: " + countNodes());
						System.out.println("======================================");
					}
				}
			}
			// If node < root
			else 
			{
				level++;
				if (root.getLeftNode() != null)
				{
					level = addNode(node, root.getLeftNode(), level, showCount);
				}
				else
				{
					node.setRoot(root);
					root.setLeftNode(node);
					if (showCount)
					{
						// Show the level of the added Node and the Count of the Nodes
						System.out.println("======================================");
						System.out.println("O Nó foi adicionado no nível {" + level + "}.");
						System.out.println("A contagem total de Nós é de: " + countNodes());
						System.out.println("======================================");
					}
				}
			}
		}
		return level;
	}
	
	// Delete Node
	/**
	 * Delete a Node from the Tree
	 * @param node Node to be deleted
	 */
	public void deleteNode(Node<T, S> node)
	{
		Node<T, S> root = node.getRoot();
		
		// Leaf
		if (node.getLeftNode() == null && node.getRightNode() == null)
		{
			// "Delete" the node
			if (root.getLeftNode() != null && root.getLeftNode() == node)
			{
				root.setLeftNode(null);
			}
			else 
			{
				root.setRightNode(null);
			}
		}
		// One Child
		else if (node.getLeftNode() != null ^ node.getRightNode() != null)
		{
			// Swap the child node
			if (node.getLeftNode() != null)
			{
				if (root.getLeftNode() != null && root.getLeftNode() == node)
				{
					root.setLeftNode(node.getLeftNode());
				}
				else 
				{
					root.setRightNode(node.getLeftNode());
				}
			}
			else
			{
				if (root.getLeftNode() != null && root.getLeftNode() == node)
				{
					root.setLeftNode(node.getRightNode());
				}
				else 
				{
					root.setRightNode(node.getRightNode());
				}
			}
		}
		// More than one child
		else 
		{
			Node<T, S> rightNode = node.getRightNode();
			Node<T, S> leftNode = node.getLeftNode();
			
			if (root != null)
			{
				// "Delete" the node
				if (root.getLeftNode() != null && root.getLeftNode() == node)
				{
					root.setLeftNode(null);
				}
				else 
				{
					root.setRightNode(null);
				}
				
				addNode(leftNode, false);
				addNode(rightNode, false);
			}
			else
			{
				setRoot(null);
				addNode(leftNode, false);
				addNode(rightNode, false);
			}
		}
	}
	
	// Count Nodes
	/**
	 * Count the number of Nodes from the Tree
	 * @return
	 */
	public int countNodes()
	{
		return countNodes(1, getRoot());
	}
	
	/**
	 * Count the number of Nodes from the Tree
	 * @param count The count number (Recursive only)
	 * @param root The root to count
	 * @return The final count
	 */
	private int countNodes(int count, Node<T, S> root)
	{
		// Verifies if the RightNode is not null and count the Child
		if(root.getRightNode() != null)
		{
			count++;
			count = countNodes(count, root.getRightNode());
		}
		
		// Verifies if the LeftNode is not null and count the Child
		if (root.getLeftNode() != null)
		{
			count++;
			count = countNodes(count, root.getLeftNode());
		}
		
		return count;
	}

	// Search Nodes
	/**
	 * Search a Node in the Tree
	 * @param key Key to be searched
	 * @return The found Node (Or null, case the Node was not found)
	 */
	public Node<T, S> searchNode(S key)
	{
		return searchNode(key, getRoot(), 1);
	}
	
	/**
	 * Search a Node in the Tree
	 * @param key Key to be searched
	 * @param root Root Node
	 * @param count Count of comparisons
	 * @return The found Node (Or null, case the Node was not found)
	 */
	private Node<T, S> searchNode(S key, Node<T, S> root, int count)
	{
		// Verify root
		if (root.getKey().compareTo(key) == 0)
		{
			System.out.println("======================================");
			System.out.println("Comparações feitas para achar o Nó: " + count);
			System.out.println("======================================");
			return root;
		}
		
		// Compare child
		if (root.getKey().compareTo(key) > 0)
		{
			if (root.getLeftNode() != null)
			{
				count++;
				return searchNode(key, root.getLeftNode(), count);
			}
		}
		else
		{
			if (root.getRightNode() != null)
			{
				count++;
				return searchNode(key, root.getRightNode(), count);
			}
		}
		
		System.out.println("Comparações feitas: " + count);
		return null;
	}

	/**
	 * Shows the Tree
	 */
	public void showTree()
	{
		if (this instanceof AVL) System.out.print("AVL: ");
		if (this instanceof RBT) System.out.print("RBT: ");
		showTree(this.getRoot());
		System.out.println("");
		System.out.println("======================");
	}
	
	/**
	 * Shows the tree
	 */
	private void showTree(Node<T,S> node)
	{
		boolean flagLeft = node.getLeftNode() == null ? false : true;
		boolean flagRight = node.getRightNode() == null ? false : true;
		
		System.out.print(node.getKey());
		
		if (flagLeft || flagRight)
		{
			System.out.print("(");
			
			// Left nodes
			if (flagLeft)
			{
				showTree(node.getLeftNode());
					
			}
			else 
			{
				System.out.print("[ ]");
			}
			
			System.out.print(", ");
			
			// Right nodes
			if (flagRight)
			{
				showTree(node.getRightNode());
			}
			else
			{
				System.out.print("[ ]");
			}
			
			System.out.print(")");
		}
		
	}

	/**
	 * Performs a left rotation
	 * @param node Node to be rotate
	 */
	protected void leftRotation(Node<T,S> node)
	{
		Node<T, S> R = node.getRightNode();
		Node<T, S> L = node.getRightNode().getLeftNode();
		Node<T, S> B = L;
		node.getRightNode().setLeftNode(node);
		node.setRightNode(B);

		R.setRoot(node.getRoot());
		if (node.getRoot() != null)
		{
			if(node.getRoot().getKey().compareTo(node.getKey()) <= 0)
			{
				node.getRoot().setRightNode(R);
			}
			else 
			{
				node.getRoot().setLeftNode(R);
			}
		}
		else
		{
			setRoot(R);
		}
		node.setRoot(R);
	}
	
	/**
	 * Performs a right rotation
	 * @param node Node to be rotate
	 */
	protected void rightRotation(Node<T, S> node)
	{
		Node<T, S> L = node.getLeftNode();
		Node<T, S> R = node.getLeftNode().getRightNode();
		Node<T, S> B = R;
		node.getLeftNode().setRightNode(node);
		node.setLeftNode(B);
		
		L.setRoot(node.getRoot());
		if (node.getRoot() != null)
		{
			if(node.getRoot().getKey().compareTo(node.getKey()) <= 0)
			{
				node.getRoot().setRightNode(L);
			}
			else 
			{
				node.getRoot().setLeftNode(L);
			}
		}
		else
		{
			setRoot(L);
		}
		node.setRoot(L);
	}

	/**
	 * Resets the Balance and Level of all Nodes
	 */
	protected void resetTree()
	{
		resetTree(this.getRoot());
	}
	
	/**
	 * Resets the Balance and Level of all Nodes
	 * @param node Root of the tree
	 */
	private void resetTree(Node<T, S> node)
	{
		if (node != null)
		{			
			node.setBalance(99);
			node.setLevel(-1);
			
			resetTree(node.getLeftNode());
			resetTree(node.getRightNode());
		}
	}
	
	/**
	 * Shows the level of the root node
	 */
	protected void showRootLevel()
	{
		showRootLevel(this.getRoot());
	}
	
	/**
	 * Shows the level of the root node
	 * @param node Node to be calculated
	 */
	private void showRootLevel(Node<T, S> node)
	{
		// Leaf
		if (node.getLeftNode() == null && node.getRightNode() == null)
		{
			node.setLevel(0);
			return;
		}
		
		// Child nodes
		if (node.getRightNode() != null && node.getRightNode().getLevel() == -1) showRootLevel(node.getRightNode());
		if (node.getLeftNode() != null && node.getLeftNode().getLevel() == -1) showRootLevel(node.getLeftNode());
		
		// Set Level
		if (node.getLeftNode() == null) node.setLevel(node.getRightNode().getLevel() + 1);
		else if (node.getRightNode() == null) node.setLevel(node.getLeftNode().getLevel() + 1);
		else if (node.getLeftNode().getLevel() >= node.getRightNode().getLevel()) node.setLevel(node.getLeftNode().getLevel() + 1);
		else node.setLevel(node.getRightNode().getLevel() + 1);
		
		// If Root and have level, returns
		if (node.getRoot() == null && node.getLevel() != -1)
		{
			System.out.println("O nível do Root é " + node.getLevel());
			resetTree(node);
			return;
		}
	}
}
