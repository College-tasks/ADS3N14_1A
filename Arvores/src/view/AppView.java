package view;

import java.util.Scanner;

import model.*;
import utils.*;
import structure.*;
import static java.lang.System.out;

/**
 * View class of the app - T5
 * @author Simor
 *
 */
public class AppView {
	/**
	 * Shows a contact information and asks for an action
	 * @param contact Contact to be shown
	 * @return The inputed action
	 */
	public int showContact(Contact contact){
		out.print(contact.toString());
		out.println("O que gostaria de fazer agora?");
		out.println("1 - Mostrar próximo contato");
		out.println("2 - Mostrar contato anterior");
		out.println("3 - Adicionar contato");
		out.println("4 - Remover contato atual");
		out.println("5 - Procurar contato");
		out.println("6 - Mostrar árvore");
		out.println("0 - Sair");
		
		try
		{
			return (new Scanner(System.in)).nextInt();
		}
		catch(Exception ex)
		{
			return -1;
		}
	}
	
	/**
	 * Print the key passed by the parameter node
	 * @param node Node to get the Key
	 */
	public void showKey(Node<Contact, String> node)
	{
		if (node != null)
		{
			out.println(node.getKey());
		}
	}
	
	/**
	 * Add a Contact to the Tree
	 * @return The created Contact
	 */
	public Contact addContact()
	{
		Contact ret = new Contact();
		
		try
		{
			out.println("Digite o nome do contato:");
			ret.setName((new Scanner(System.in)).nextLine());
			out.println("Digite o telefone do contato:");
			ret.setPhone((new Scanner(System.in).nextLine()));
		}
		catch(Exception ex)
		{
			return null;
		}
		
		return ret;
	}
	
	/**
	 * Checks what sort of Tree will be shown
	 * @return The type of Tree that will be shown
	 */
	public ShowType showTree()
	{
		int op = 0;
		out.println("Digite o tipo de estrutura desejada:");
		out.println("1 - Travessia Prefixa");
		out.println("2 - Travessia Posfixa");
		out.println("3 - Travessia Infixa");
		out.println("4 - Busca em Largura");
		out.println("5 - Busca em Profundidade");
		
		try
		{
			op = (new Scanner(System.in)).nextInt();
		}
		catch(Exception ex)
		{
			return null;
		}
		
		// Checks what the user inputed
		switch(op)
		{
			case 1:
				return ShowType.PreOrder;
			case 2:
				return ShowType.PostOrder;
			case 3:
				return ShowType.InOrder;
			case 4:
				return ShowType.BreadthFirst;
			case 5:
				return ShowType.DepthFirst;
			default:
				return null;
		}
	}
	
	/**
	 * Search and show the found contact (If found)
	 * @return The key to search
	 */
	public String searchContact()
	{
		out.println("Digite o nome do contato para iniciar a pesquisa:");
		try
		{
			return (new Scanner(System.in)).nextLine();
		}
		catch(Exception ex)
		{
			return "";
		}
	}
	
	/**
	 * Shows some message
	 * @param message Message to be shown
	 */
	public void showMessage(String message)
	{
		out.println("============================");
		out.println(message);
		out.println("============================");
	}
}