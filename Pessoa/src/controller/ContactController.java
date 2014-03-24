package controller;

import java.util.Scanner;

import Utils.ContactFile;
import model.Contact;
import view.ContactView;
import structures.*;

public class ContactController {
	private ContactView view;
	private SortedList<Contact> list;
	private Node<Contact> currentNode;
	
	/**
	 * Constructor - Initialize variables
	 */
	public ContactController()
	{
		list = ContactFile.loadContacts();
		view = new ContactView();
		currentNode = list.getHead();
	}
	
	/**
	 * Shows a contact and the menu
	 */
	public void showContact()
	{
		view.showContact(currentNode);
		boolean flagOk = true;
		int option;
		Node<Contact> tempContact;
		
		while(flagOk)
		{
			System.out.print(">");
			
			try {
				option = (new Scanner(System.in)).nextInt();
			} catch(Exception ex) { continue; }
			
			switch(option)
			{
			case 1:
				// Previous Contact
				tempContact = list.getPrevious(currentNode);
				this.currentNode = tempContact == null ? list.getTail() : tempContact;
				showContact();
				break;
			case 2:
				// Next Contact
				tempContact = currentNode.getNext();
				this.currentNode = tempContact == null ? list.getHead() : tempContact;
				showContact();
				break;
			case 3:
				// Input a character
				String ch = (new Scanner(System.in)).next();
				//Node<Contact> nextNode = list.getFromChar(ch);
				
				break;
			case 4:
				// Add a contact
				break;
			case 0:
				// Exit
				System.exit(0);
				break;
			}
		}
	}
}
