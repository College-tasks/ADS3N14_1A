package view;

import java.util.Scanner;

import model.*;
import static java.lang.System.out;

/**
 * View class of the app
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
}