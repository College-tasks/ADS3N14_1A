package Code;

import static java.lang.System.out;

public class BubbleSort {
	/**
	 * Gets the Array of numbers and sort the numbers using Merge Sort
	 * 
	 * @param unsortedArray The array with the numbers unsorted
	 */
	public static void sort(int[] unsortedArray) {
		int swap;
		int swapCount = 0;
		int checkCount = 0;
		int[] sortedArray = unsortedArray;
		boolean flagSwap;
		
		// Go through the numbers
		for (int i = 0; i < sortedArray.length; i++) {
			flagSwap = false;
			// Actual number to check
			for (int j = 0; j < sortedArray.length-1; j++) {
				// Verifies if is gonna have a swap
				if (sortedArray[j] > sortedArray[j + 1])
				{
					swap = sortedArray[j];
					sortedArray[j] = sortedArray[j + 1];
					sortedArray[j + 1] = swap;
					swapCount++;
					flagSwap = true;
				}
				checkCount++;
			}
			if (!flagSwap){
				break;
			}
		}
		
		// Shows the counts and the Sorted Array
		out.println("BubbleSort");
		out.println("Comparações: " + checkCount);
		out.println("Swaps: " + swapCount);
		showArray(sortedArray);
	}
	
	/**
	 * Shows the Sorted Array
	 * @param sortedArray
	 */
	public static void showArray(int[] sortedArray)
	{
		for (int i = 0; i < sortedArray.length; i++)
		{
			System.out.print(sortedArray[i] + " ");
		}
		out.println("");
		out.println("--------------------------");
	}
}
