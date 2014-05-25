package structure;

/**
 * Class that defines a RB Tree (Structure)
 * @author Simor
 *
 * @param <T> "Value" of the Node
 * @param <S> "Key" of the Node
 */
public class RBT <T extends Comparable<T>, S extends Comparable<S>> extends Tree<T, S> {
	/**
	 * Add a Node to the Tree
	 * @param node Node to be added
	 * @param showCount If it will show the Count of Nodes and the Level of the added Node
	 */
	@Override
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
				System.out.println("Derp");
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
	@Override
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

	/**
	 * Shows the Tree
	 * @param node Node to be shown
	 */
	@Override
	public void showTree()
	{
		System.out.println("*Nodos vermelhos serão mostrados com um hífen antes da key*");
		showTree(super.getRoot(), true);
	}
	
	/**
	 * Shows the Tree (Recursive)
	 * @param node Node to be shown
	 * @param flag Recursive flag
	 */
	private void showTree(Node<T,S> node, boolean flag)
	{
		boolean flagLeft = node.getLeftNode() == null ? false : true;
		boolean flagRight = node.getRightNode() == null ? false : true;
		
		if (!node.isBlack()) { System.out.print("-"); }
		System.out.print(node.getKey());
		
		if (flagLeft || flagRight)
		{
			System.out.print("(");
			
			// Left nodes
			if (flagLeft)
			{
				showTree(node.getLeftNode(), true);
				if (flagRight)
				{
					System.out.print(", ");
				}
			}
			
			// Right nodes
			if (flagRight)
			{
				showTree(node.getRightNode(), true);
			}
			
			System.out.print(")");
		}
	}
}
