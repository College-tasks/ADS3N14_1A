package view;

import static java.lang.System.out;
import structures.*;
import model.*;

public class ContactView {
	/**
	 * Shows a contact and asks for an action
	 * @param node
	 */
	public void showContact(Node<Contact> node) {
		out.println("Nome: " + node.getKey().getName());
		out.println("Telefone: " + node.getKey().getPhone());
		
		out.println("------------------------");
		out.println("O que deseja fazer?");
		out.println("1 - Contato anterior");
		out.println("2 - Próximo contato");
		out.println("3 - Digitar uma letra de contato");
		out.println("4 - Adicionar um contato");
		out.println("0 - Sair");
	}	
}