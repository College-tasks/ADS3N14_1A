package controller;

import java.util.Scanner;

import Utils.ContactFile;
import Utils.SortedListUtils;
import model.Contact;
import view.ContactView;
import structures.*;

public class ContactController {
	private ContactView view;
	private SortedList<Contact> list;
	private Node<Contact> currentNode;
	private NormalList normalList;
	
	/**
	 * Constructor - Initialize variables
	 */
	public ContactController()
	{
		list = ContactFile.loadContacts();
		view = new ContactView();
		currentNode = list.getHead();
		normalList = new NormalList(list);
	}
	
	/**
	 * Shows a contact and the menu
	 */
	public void showContact()
	{
		view.showContact(currentNode);
		boolean flagOk = true;
		int option;
		
		
		while(flagOk)
		{
			view.showInput();
			
			try {
				option = (new Scanner(System.in)).nextInt();
			} catch(Exception ex) { continue; }
			
			switch(option)
			{
			case 1:
				previousContact();
				showContact();
				break;
			case 2:
				nextContact();
				showContact();
				break;
			case 3:
				inputContact();
				showContact();
				break;
			case 4:
				addContact();
				showContact();
				break;
			case 0:
				// Exit
				flagOk = false;
				System.exit(0);
				break;
			}
		}
	}

	/**
	 * Shows the previous contact
	 */
	private void previousContact()
	{
		Node<Contact> tempContact;
		tempContact = list.getPrevious(currentNode);
		this.currentNode = tempContact == null ? list.getTail() : tempContact;
	}
	
	/**
	 * Shows the next contact
	 */
	private void nextContact()
	{
		Node<Contact> tempContact;
		tempContact = currentNode.getNext();
		this.currentNode = tempContact == null ? list.getHead() : tempContact;
	}
	
	/**
	 * Shows the searched (if exists) contact
	 */
	private void inputContact()
	{
		// Input a character
		SortedListUtils sortedUtils = new SortedListUtils(this.list);
		view.showInput();
		String ch = (new Scanner(System.in)).nextLine();
		int comparisonsSorted = 0;
		int comparisonsBinary = 0;
		
		if (ch.length() > 1)
		{
			this.view.showErrorMessage("Digite somente uma letra!");
		}
		else
		{
			Node<Contact> nextNode = normalList.searchContact(ch);
			
			if (nextNode == null)
			{
				this.view.showErrorMessage("Nada encontrado!");
			}
			else {
				this.currentNode = nextNode;
			}
		}
		
		comparisonsSorted = sortedUtils.getFromChar(ch, true);
		comparisonsBinary = normalList.searchContact(ch, true);
		this.view.showSortedCount(comparisonsSorted);
		this.view.showBinaryCount(comparisonsBinary);
		
	}
	
	/**
	 * Adds the a contact (List and file)
	 */
	private void addContact()
	{
		// Add a contact
		String name, phone;
		Node<Contact> node = new Node<Contact>();
		Contact contact = new Contact();
		
		// Inputs
		view.showInputName();
		name = (new Scanner(System.in)).nextLine();
		view.showInputPhone();
		phone = (new Scanner(System.in)).nextLine();
		
		contact.setName(name);
		contact.setPhone(phone);
		node.setKey(contact);
		
		ContactFile.addContact(contact);
		list.insert(node);
		
	}
}
