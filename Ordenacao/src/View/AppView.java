package View;

import static java.lang.System.out;

/**
 * View of the App
 * @author Simor
 *
 */
public class AppView {
	/**
	 * Shows the unsorted numbers
	 * @param unsortedArray The array with the numbers unsorted
	 */
	public void showUnsortedArray(int[] unsortedArray){
		out.println("A lista de números sorteados foi:");
		
		// Shows the numbers
		for(int i = 0; i < unsortedArray.length; i++)
		{
			out.print(unsortedArray[i] + " ");
		}
		out.println("");
		out.println("--------------------------");
	}
}