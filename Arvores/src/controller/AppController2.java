package controller;

import java.util.ArrayList;
import java.util.Random;

import view.*;
import structure.*;
import utils.*;
import model.*;

/**
 * Controller class of the app - T6
 * @author Simor
 *
 */
public class AppController2 {
	// Variables
	private AppView2 view;
	private AVL<Integer, Integer> avl;
	private RBT<Integer, Integer> rbt;
	private Node<Integer, Integer> currentNode;
	private ArrayList<Integer> numbers;
	
	/**
	 * Constructor - Initialize variables
	 */
	public AppController2()
	{
		view = new AppView2();
		numbers = new ArrayList<Integer>();
	}
	
	/**
	 * Create a list of "numbers"
	 * @param random If the list will be random
	 * @param reverse If the list will be reverted
	 */
	private void createNumbers(boolean random, boolean reverse)
	{
		if (random)
		{
			for (int i = 0; i < 50; i++)
			{
				numbers.add(Integer.valueOf((new Random().nextInt(100))));
			}
		}
		else
		{
			if (reverse)
			{
				for (int i = 50; i > 0; i--)
				{
					numbers.add(Integer.valueOf(i));
				}
			}
			else
			{	
				for (int i = 1; i < 51; i++)
				{
					numbers.add(Integer.valueOf(i));
				}
			}
		}
	}
	
	/**
	 * Shows the list of "numbers"
	 */
	private void showNumbers()
	{
		view.showNumbers(numbers);
	}
	
	/**
	 * Shows the init screen of the app
	 */
	public void initApp()
	{
		int op = view.initApp();
		
		switch(op)
		{
		case 1:
			createNumbers(true, false);
			showNumbers();
			createTrees();
			showTrees();
			showMain();
			break;
		case 2:
			createNumbers(false, false);
			showNumbers();
			createTrees();
			showTrees();
			showMain();
			break;
		case 3:
			createNumbers(false, true);
			showNumbers();
			createTrees();
			showTrees();
			showMain();
			break;
		case 0:
			System.exit(0);
		default:
			view.showError("Digite uma opção válida!");
			initApp();
			break;
		}
	}
	
	/**
	 * Shows the main screen of the app
	 */
	public void showMain()
	{
		//showTrees();
		int op = view.showMain();
		
		switch(op)
		{
		case 1:
			addNode();
			break;
		case 2:
			delNode();
			break;
		case 0:
			System.exit(0);
		default:
			view.showError("Digite uma opção válida!");
			showMain();
			break;
		}
	}
	
	/**
	 * Adds a new Node to the Tree
	 */
	private void addNode()
	{
		Integer num = view.retInteger();
		Node<Integer, Integer> nodeAVL = new Node<Integer, Integer>();
		Node<Integer, Integer> nodeRBT = new Node<Integer, Integer>();
		nodeAVL.setValue(num);
		nodeAVL.setKey(num);
		
		nodeRBT.setValue(num);
		nodeRBT.setKey(num);
		nodeRBT.setBlack(false);
		
		avl.addNodeAVL(nodeAVL, true);
		rbt.addNodeRBT(nodeRBT, true);
		
		showMain();
	}
	
	/**
	 * Deletes a Node (if exists) from the Tree
	 */
	private void delNode()
	{
		Integer num = view.retInteger();
		Node<Integer, Integer> nodeDelAVL = avl.searchNode(num);
		Node<Integer, Integer> nodeDelRBT = rbt.searchNode(num);
		
		if (nodeDelAVL == null)
		{
			view.showError("Nenhum elemento encontrado!");
		}
		else
		{
			avl.deleteNode(nodeDelAVL);
			rbt.deleteNode(nodeDelRBT);
		}
		
		showMain();
	}

	/**
	 * Create the trees
	 */
	private void createTrees()
	{
		avl = new AVL<Integer, Integer>();
		rbt = new RBT<Integer, Integer>();
		
		for (Integer item : numbers)
		{
			Node<Integer, Integer> nodeAVL = new Node<Integer, Integer>();
			Node<Integer, Integer> nodeRBT = new Node<Integer, Integer>();
			nodeAVL.setValue(item);
			nodeAVL.setKey(item);
			
			nodeRBT.setValue(item);
			nodeRBT.setKey(item);
			nodeRBT.setBlack(false);
			
			avl.addNodeAVL(nodeAVL, false);
			rbt.addNodeRBT(nodeRBT, false);
		}
		
		view.showTotal(true, avl.compTotal, avl.rotTotal);
		view.showTotal(false, rbt.compTotal, rbt.rotTotal);
	}

	/**
	 * Show the trees
	 */
	private void showTrees()
	{
		avl.showTree();
		rbt.showTree();
	}
}
