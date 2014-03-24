package view;

import static java.lang.System.out;
import structures.*;
import model.*;

public class ContactView {
	/**
	 * Show a contact and asks for an action
	 * @param node Node to be shown
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
	
	/**
	 * Show an error or "warning" message
	 * @param msg Message to be shown
	 */
	public void showErrorMessage(String msg)
	{
		out.println("####################");
		out.println(msg);
		out.println("####################");
	}

	/**
	 * Show the "input" sign
	 */
	public void showInput()
	{
		out.println(">");
	}

	/**
	 * Show the "input name" message
	 */
	public void showInputName()
	{
		out.println("Digite o nome: ");
		showInput();
	}
	
	/**
	 * Show the "input phone" message
	 */
	public void showInputPhone()
	{
		out.println("Digite o telefone: ");
		showInput();
	}
}