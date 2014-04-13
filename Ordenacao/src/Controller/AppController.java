package Controller;

import java.util.Random;

import View.AppView;
import Code.*;

/**
 * Controller of the App
 * @author Simor
 *
 */
public class AppController {
	AppView view;
	int[] unsortedArray;
	
	/**
	 * Constructor
	 */
	public AppController(){
		view = new AppView();
		unsortedArray = new int[10];
		
		// Initialize the unsorted list of numbers
		for(int i = 0; i < 10; i++)
		{
			unsortedArray[i] = (new Random()).nextInt(50);
		}
	}
	
	/**
	 * Shows the unsorted array, sort using BubbleSort and MergeSort and shows the results
	 */
	public void init(){
		view.showUnsortedArray(this.unsortedArray);
		BubbleSort.sort(this.unsortedArray);
		MergeSort.sort(this.unsortedArray);
	}
}
