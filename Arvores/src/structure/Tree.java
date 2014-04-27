package structure;

/**
 * Class that defines a Tree (Structure)
 * @author Simor
 *
 * @param <T> "Value" of the Node
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
	
	/**
	 * Add a Node to the Tree
	 * @param node Node to be added
	 * @param root The root of the Node (Only for recursive function)
	 * @param level Level of the Node (Only for recursive function)
	 * @param showCount If it will show the Count of Nodes and the Level of the added Node
	 */
	public void addNode(Node<T, S> node, Node<T, S> root, int level, boolean showCount)
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
		}
		else
		{
			// If node >= root
			if(root.getKey().compareTo(node.getKey()) <= 0)
			{
				if (root.getRightNode() != null)
				{
					addNode(node, root.getRightNode(), level++, showCount);
				}
				else
				{
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
				if (root.getLeftNode() != null)
				{
					addNode(node, root.getLeftNode(), level++, showCount);
				}
				else
				{
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
	}
	
	/**
	 * Delete a Node from the Tree
	 * @param node Node to be deleted
	 */
	public void deleteNode(Node<T, S> node)
	{
		Node<T, S> root = node.getRoot();
		
		// "Delete" the node
		if (root.getLeftNode() == node)
		{
			root.setLeftNode(null);
		}
		else 
		{
			root.setRightNode(null);
		}
		
		// Add the childs of the deleted Node
		if (node.getLeftNode() != null)
		{
			addNode(node.getLeftNode(), null, 0, false);
		}
		
		if (node.getRightNode() != null)
		{
			addNode(node.getRightNode(), null, 0, false);
		}
	}
	
	/**
	 * Count the number of Nodes from the Tree
	 * @return
	 */
	public int countNodes()
	{
		return countNodes(0, getRoot());
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
			countNodes(count++, root.getRightNode());
		}
		
		// Verifies if the LeftNode is not null and count the Child
		if (root.getLeftNode() != null)
		{
			countNodes(count++, root.getLeftNode());
		}
		
		return count;
	}

	/**
	 * Search a Node in the Tree
	 * @param key Key to be searched
	 * @return The found Node (Or null, case the Node was not found)
	 */
	public Node<T, S> searchNode(S key)
	{
		return searchNode(key, getRoot(), 0);
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
			System.out.println("Comparações feitas: " + count);
			System.out.println("======================================");
			return root;
		}
		
		// Compare child
		if (root.getKey().compareTo(key) < 0)
		{
			if (root.getLeftNode() != null)
			{
				return searchNode(key, root.getLeftNode(), count++);
			}
		}
		else
		{
			if (root.getRightNode() != null)
			{
				return searchNode(key, root.getRightNode(), count++);
			}
		}
		
		System.out.println("Comparações feitas: " + count);
		return null;
	}
}
