package structure;

/**
 * Class that defines a RB Tree (Structure)
 * @author Simor
 *
 * @param <T> "Value" of the Node
 * @param <S> "Key" of the Node
 */
public class RBT <T extends Comparable<T>, S extends Comparable<S>> extends Tree<T, S> {
	private int comp;
	private int rot;
	public int compTotal = 0;
	public int rotTotal = 0;
	private boolean flagBalanced;
	
	/**
	 * Add a Node to the Tree
	 * @param node Node to be added
	 * @param showCount If it will show the Count of Nodes and the Level of the added Node
	 */

	public void addNodeRBT(Node<T, S> node, boolean showCount)
	{
		compTotal += comp;
		rotTotal += rot;
		comp = 0;
		rot = 0;
		
		node.setLeftNode(null);
		node.setRightNode(null);
		addNodeRBT(node, getRoot(), showCount, false);
	}
	
	/**
	 * Add a Node to the Tree
	 * @param node Node to be added
	 * @param root The root of the Node (Recursive)
	 * @param level Level of the Node (Recursive)
	 * @param showCount If it will show the Count of Nodes and the Level of the added Node
	 */
	private void addNodeRBT(Node<T, S> node, Node<T, S> root, boolean showCount, boolean flagInserted)
	{
		if (root == null)
		{
			root = getRoot();
		}
		
		// If Root is null, new node will be the root
		if (getRoot() == null)
		{
			setRoot(node);
			flagInserted = true;
		}
		else
		{
			// If node >= root
			comp++;
			if(root.getKey().compareTo(node.getKey()) <= 0)
			{
				if (root.getRightNode() != null)
				{
					addNodeRBT(node, root.getRightNode(), showCount, false);
				}
				else
				{
					node.setRoot(root);
					root.setRightNode(node);
					flagInserted = true;
				}
			}
			// If node < root
			else 
			{
				if (root.getLeftNode() != null)
				{
					addNodeRBT(node, root.getLeftNode(), showCount, false);
				}
				else
				{
					node.setRoot(root);
					root.setLeftNode(node);
					flagInserted = true;
				}
			}
			if (flagInserted)
			{
				verifyCases(node, true);
				verifyCases();
				if (showCount)
				{
					// Show the Tree
					super.showRootLevel();
					this.showRotComp();
					this.showTree();
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
		
		comp++;
		// Leaf
		if (node.getLeftNode() == null && node.getRightNode() == null)
		{
			comp++;
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
			comp++;
			// Swap the child node
			if (node.getLeftNode() != null)
			{
				comp++;
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
				comp++;
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
			
			comp++;
			if (root != null)
			{
				comp++;
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
		
		// Show the Tree
		this.verifyCases();
		super.showRootLevel();
		this.showRotComp();
		this.showTree();
		
	}

	/**
	 * Case 1: Red root
	 */
	private void doCase1()
	{
		this.getRoot().setBlack(true);
	}
	
	/**
	 * Case 2: Parent and Uncle Red
	 * @param node Node to be verified
	 */
	private void doCase2(Node<T, S> node)
	{
		Node<T, S> pNode = node.getRoot();
		Node<T, S> gNode = node.getRoot().getRoot();
		Node<T, S> uNode = gNode.getLeftNode() == pNode ? gNode.getRightNode() : gNode.getLeftNode();
		
		// Repaints Parent, GrandP and Uncle
		pNode.setBlack(!pNode.isBlack());
		gNode.setBlack(!gNode.isBlack());
		if (uNode != null) uNode.setBlack(!uNode.isBlack());
	}
	
	/**
	 * Case 3: Parent Red, Uncle Black (V formation)
	 * @param node Node to be verified
	 */
	private void doCase3(Node<T, S> node)
	{
		Node<T, S> pNode = node.getRoot();
		if (pNode.getLeftNode() == node)
		{
			super.rightRotation(pNode);
			rot++;
		}
		else
		{
			super.leftRotation(pNode);
			rot++;
		}
	}
	
	/**
	 * Case 4:  Parent Red, Uncle Black (Line formation)
	 * @param node Node to be verified
	 */
	private void doCase4(Node<T, S> node)
	{
		Node<T, S> pNode = node.getRoot();
		Node<T, S> gNode = pNode.getRoot();
		
		if (pNode.getLeftNode() == node)
		{
			super.rightRotation(gNode);
			rot++;
		}
		else
		{
			super.leftRotation(gNode);
			rot++;
		}
		
		pNode.setBlack(!pNode.isBlack());
		gNode.setBlack(!gNode.isBlack());
	}
	
	/**
	 * Verifies if is needed to do something with the node
	 * @param node Initial Node
	 */
	private void verifyCases(Node<T, S> node, boolean flag)
	{
		if (this.getRoot() == node && !node.isBlack())
		{
			doCase1();
			flagBalanced = true;
		}
		
		if (node.getRoot() != null && node.getRoot().getRoot() != null && !node.getRoot().isBlack() && !node.isBlack())
		{
			Node<T, S> pNode = node.getRoot();
			Node<T, S> gNode = pNode.getRoot();
			Node<T, S> uNode = gNode.getLeftNode() == null || gNode.getRightNode() == null ? null : gNode.getLeftNode() == pNode ? gNode.getRightNode() : gNode.getLeftNode();
			boolean pLeft = gNode.getLeftNode() == pNode;
			boolean nLeft = pNode.getLeftNode() == node;
			
			if (uNode != null && !uNode.isBlack())
			{
				doCase2(node);
				flagBalanced = true;
			}
			else if (pLeft != nLeft)
			{
				doCase3(node);
				verifyCases(pNode);
				flagBalanced = true;
			}
			else
			{
				doCase4(node);
				flagBalanced = true;
			}
		}
	}
	
	/**
	 * Verifies if is needed to do something with the Tree
	 */
	private void verifyCases()
	{
		flagBalanced = false;
		Node<T, S> rNode = this.getRoot();
		
		verifyCases(rNode);
		
		if (flagBalanced)
		{
			verifyCases();
		}
	}
	
	/**
	 * Verifies if is needed to do something with the Tree (Recursive)
	 * @param node Node to be checked
	 */
	private void verifyCases(Node<T, S> node)
	{		
		if (node.getLeftNode() != null) verifyCases(node.getLeftNode());
		if (node.getRightNode() != null) verifyCases(node.getRightNode());	
		
		verifyCases(node, true);
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
