package App;

import controller.ContactController;
import Utils.ContactFile;
import structures.*;
import model.*;

public class Main {
	public static void main(String[] args) {
		ContactController controller = new ContactController();
		controller.showContact();
		
		/*
		ContactFile x = new ContactFile();
		SortedList<Contact> list = new SortedList<Contact>();
		
		x.createContacts(10);
		list = x.loadContacts();
		
		Node<Contact> y = list.getHead();
		
		System.out.println("--------------------");
		
		while (y.getNext() != null)
		{
			System.out.println("Nome: " + y.getKey().getName());
			System.out.println("Fone: " + y.getKey().getPhone());
			System.out.println("--------------------");
			
			y = y.getNext();
		}
		*/
	}
}