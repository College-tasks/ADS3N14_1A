package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.*;
import utils.*;
import structure.*;
import static java.lang.System.out;

/**
 * View class of the app - T6
 * @author Simor
 *
 */
public class AppView2 
{
	/**
	 * Shows the list of "numbers"
	 * @param list List of "numbers"
	 */
	public void showNumbers(ArrayList<Integer> list)
	{
		out.println("Lista de números: ");
		for (Integer item : list)
		{
			out.print(item + " ");
		}
		
		out.println("");
	}
	
	/**
	 * Checks what kind of list the user wants
	 * @return Selected option
	 */
	public int initApp()
	{
		int op = -1;
		out.println("Digite a opção desejada:");
		out.println("1 - Criar uma lista aleatória.");
		out.println("2 - Criar uma lista organizada.");
		out.println("3 - Criar uma lista organizada revertida.");
		out.println("0 - Sair.");
		out.println("> ");
		
		try
		{
			op = (new Scanner(System.in)).nextInt();
		}
		catch (Exception ex) {}
		
		return op;
	}
	
	/**
	 * Shows an error message
	 * @param msg Message to be shown
	 */
	public void showError(String msg)
	{
		out.println("==================================");
		out.println(msg);
		out.println("==================================");
	}

	/**
	 * Shows the main options
	 * @return Selected option
	 */
	public int showMain()
	{
		int op = -1;
		out.println("Digite a opção desejada:");
		out.println("1 - Adicionar um item.");
		out.println("2 - Excluir um item.");
		out.println("0 - Sair.");
		out.println("> ");
		
		try
		{
			op = (new Scanner(System.in)).nextInt();
		}
		catch (Exception ex) {}
		
		return op;
	}

	/**
	 * Adds a node to the Trees
	 * @return Value to be added
	 */
	public Integer retInteger()
	{
		boolean flagOk = false;
		Integer value = Integer.valueOf(0);
		
		do
		{
			System.out.println("Digite o valor desejado: ");
			try {
				value = Integer.valueOf((new Scanner(System.in).nextInt()));
				flagOk = true;
			} catch (Exception ex)
			{
				showError("Digite um valor válido!");
			}
		} while(!flagOk);
		
		return value;
	}
}
