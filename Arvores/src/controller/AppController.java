package controller;

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
	}
	
	private void previousContact()
	{
		
	}
	
	private void nextContact()
	{
		
	}
	
	private void searchContact()
	{
		
	}
	
	private void addContact()
	{
		
	}
	
	private void deleteContact()
	{
		
	}
	
	private void showTree()
	{
		
	}
}
