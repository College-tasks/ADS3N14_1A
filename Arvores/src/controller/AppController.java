package controller;

import java.util.ArrayList;

import view.*;
import structure.*;
import utils.*;
import model.*;

/**
 * Controller class of the app
 * @author Simor
 *
 */
public class AppController {
	// Variables
	private AppView view;
	private Tree<Contact, String> list;
	private Node<Contact, String> currentNode;
	private ShowType showType;
	
	/**
	 * Constructor - Initialize variables
	 */
	public AppController()
	{
		list = ContactFile.loadContacts();
		view = new AppView();
		currentNode = list.getFirst();
	}
	
	/**
	 * Initial "screen"
	 */
	public void showContact()
	{
		int op = view.showContact(currentNode.getValue());
		switch (op)
		{
			case 1:
				nextContact();
				break;
			case 2:
				previousContact();
				break;
			case 3:
				addContact();
				break;
			case 4:
				deleteContact();
				break;
			case 5:
				searchContact();
				break;
			case 6:
				showTree();
				break;
			case 0:
				saveTree();
				System.exit(0);
				break;
			default:
				view.showMessage("Digite uma opção válida!");
				break;
		}
		
		showContact();
	}
	
	/**
	 * Shows the previous Contact
	 */
	private void previousContact()
	{
		boolean flagOk = false;
		
		// If is the first Node, go to the last
		if (currentNode == this.list.getFirst())
		{
			currentNode = this.list.getLast();
		}
		else
		{
			currentNode = previousContact(currentNode);
		}
	}
	
	/**
	 * Shows the previous Contact
	 * @param node Current Node
	 * @return Previous Node
	 */
	private Node<Contact, String> previousContact(Node<Contact, String> node)
	{
		// If the left Node is null
		if(node.getLeftNode() == null)
		{
			// Gets the first parent of a "right child"
			while(node.getKey().compareTo(node.getRoot().getKey()) < 0)
			{
				node = node.getRoot();
			}
			
			return node.getRoot();
		}
		else
		{
			// If there is a right Node, gets the "last right child"
			node = node.getLeftNode();
			while(node.getRightNode() != null)
			{
				node = node.getRightNode();
			}
			return node;
		}
	}
	
	/**
	 * Shows the next Contact
	 */
	private void nextContact()
	{		
		// If is the last Node, go to the first
		if (currentNode == this.list.getLast())
		{
			currentNode = this.list.getFirst();
		}
		else
		{
			currentNode = nextContact(currentNode);
		}
	}
	
	/**
	 * Shows the next Contact
	 * @param currentNode The current node
	 * @return Next node
	 */
	private Node<Contact, String> nextContact(Node<Contact, String> node)
	{
		// If the right Node is null
		if(node.getRightNode() == null)
		{
			// Gets the first parent of a "left child"
			while(node.getKey().compareTo(node.getRoot().getKey()) >= 0)
			{
				node = node.getRoot();
			}
			
			return node.getRoot();
		}
		else
		{
			// If there is a right Node, gets the "last left child"
			node = node.getRightNode();
			while(node.getLeftNode() != null)
			{
				node = node.getLeftNode();
			}
			return node;
		}
	}
	
	/**
	 * Search and show the found contact (If found)
	 */
	private void searchContact()
	{
		String key = view.searchContact();
		
		if (key != "")
		{
			Node<Contact, String> node = this.list.searchNode(key);
			
			// If found the key
			if (node != null)
			{
				this.currentNode = node;
			}
			else
			{
				view.showMessage("Nada encontrado!");
			}
		}
		
		showContact();
	}
	
	/**
	 * Add a Contact to the Tree
	 */
	private void addContact()
	{
		Contact contact = view.addContact();
		
		// Verifies if it has been given a valid info and add to the Tree
		if (contact != null)
		{
			Node<Contact, String> node = new Node<Contact, String>();
			node.setKey(contact.getName());
			node.setValue(contact);
			
			this.list.addNode(node, true);
		}
		else
		{
			view.showMessage("Insira dados válidos!");
		}
		
		showContact();
	}
	
	/**
	 * Delete a Contact from the Tree
	 */
	private void deleteContact()
	{
		this.list.deleteNode(currentNode);
		view.showMessage("Deletado com sucesso!");
		currentNode = list.getFirst();
		showContact();
	}
	
	/**
	 * Shows the Tree within the chosen method
	 */
	private void showTree()
	{
		ShowType showType = view.showTree();
		
		switch(showType)
		{
			case PreOrder:
				showPreOrder();
				break;
			case PostOrder:
				showPostOrder();
				break;
			case InOrder:
				showInOrder();
				break;
			case BreadthFirst:
				showBreadth();
				break;
			case DepthFirst:
				showDepth();
				break;
			default:
				view.showMessage("Selecione uma opção válida!");
				break;
		}
		
		showContact();
	}
	
	/**
	 * Shows the Tree with the Pre-Order method
	 */
	private void showPreOrder()
	{
		showPreOrder(this.list.getRoot());
	}
	
	/**
	 * Shows the Tree with the Pre-Order method (Recursive)
	 * @param node Current Node
	 */
	private void showPreOrder(Node<Contact, String> node)
	{
		view.showKey(node);
		if (node.getLeftNode() != null)
		{
			showPreOrder(node.getLeftNode());
		}
		
		if (node.getRightNode() != null)
		{
			showPreOrder(node.getRightNode());
		}
	}
	
	/**
	 * Shows the Tree with the Post-Order method
	 */
	private void showPostOrder()
	{
		showPostOrder(this.list.getRoot());
	}
	
	/**
	 * Shows the Tree with the Post-Order method
	 * @param node Current Node
	 */
	private void showPostOrder(Node<Contact, String> node)
	{
		if (node.getLeftNode() != null)
		{
			showPostOrder(node.getLeftNode());
		}
		
		if (node.getRightNode() != null)
		{
			showPostOrder(node.getRightNode());
		}
		
		view.showKey(node);
	}
	
	/**
	 * Shows the Tree with the In-Order method
	 */
	private void showInOrder()
	{
		showInOrder(this.list.getRoot());
	}
	
	/**
	 * Shows the Tree with the In-Order method
	 * @param node Current Node
	 */
	private void showInOrder(Node<Contact, String> node)
	{
		if (node.getLeftNode() != null)
		{
			showInOrder(node.getLeftNode());
		}
		
		view.showKey(node);
		
		if (node.getRightNode() != null)
		{
			showInOrder(node.getRightNode());
		}
	}
	
	/**
	 * Shows the Tree with the Breadth First Search method
	 */
	private void showBreadth()
	{
		ArrayList<Node<Contact, String>> queueNode = new ArrayList<Node<Contact, String>>();
		
		// Add root
		queueNode.add(this.list.getRoot());
		
		// While queue is not empty
		while(!queueNode.isEmpty())
		{
			Node<Contact, String> node = queueNode.get(0);
			view.showKey(node);
			queueNode.remove(node);
			
			// Add left child
			if (node.getLeftNode() != null)
			{
				queueNode.add(node.getLeftNode());
			}
			
			// Add right child
			if (node.getRightNode() != null)
			{
				queueNode.add(node.getRightNode());
			}
		}
	}
	
	/**
	 * Shows the Tree with the Depth First Method method
	 */
	private void showDepth()
	{
		ArrayList<Node<Contact, String>> stackNode = new ArrayList<Node<Contact, String>>();
		
		// Add root
		stackNode.add(this.list.getRoot());
		
		// While stack is not empty
		while(!stackNode.isEmpty())
		{
			Node<Contact, String> node = stackNode.get(stackNode.size()-1);
			view.showKey(node);
			stackNode.remove(node);
			
			// Add right child
			if (node.getRightNode() != null)
			{
				stackNode.add(node.getRightNode());
			}
			
			// Add left child
			if (node.getLeftNode() != null)
			{
				stackNode.add(node.getLeftNode());
			}
		}
	}
	
	/**
	 * Save the Tree into a file
	 */
	private void saveTree()
	{
		
	}
}
