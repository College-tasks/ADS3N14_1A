package Code;

import static java.lang.System.out;

public class MergeSort {
	int[] sortedArray;
	int[] aux;
	int swapCount;
	int checkCount;

	/**
	 * Gets the Array of numbers and sort the numbers using Merge Sort
	 * 
	 * @param unsortedArray The array with the numbers unsorted
	 */
	public void sort(int[] unsortedArray) {
		swapCount = 0;
		checkCount = 0;
		sortedArray = unsortedArray;
		aux = new int[sortedArray.length];

		// Sort
		mergeSort(0, sortedArray.length - 1);

		// Shows the counts and the Sorted Array
		out.println("MergeSort");
		out.println("Comparações: " + checkCount);
		out.println("Swaps: " + swapCount);
		showArray();
	}

	/**
	 * Initiate the merge sorting
	 * 
	 * @param low Lowest number
	 * @param high Highest number
	 */
	private void mergeSort(int low, int high) {
		if (low < high) {
			// Get the index of the middle
			int middle = low + (high - low) / 2;
			// Sort the left side
			mergeSort(low, middle);
			// Sort the right
			mergeSort(middle + 1, high);
			// Merge them
			merge(low, middle, high);
		}
	}

	/**
	 * Merge the arrays
	 * @param low Lowest number
	 * @param middle Middle number
	 * @param high Highest number
	 */
	private void merge(int low, int middle, int high) {
		// Copy both parts into the helper array
		for (int i = low; i <= high; i++) {
			aux[i] = sortedArray[i];
		}

		int i = low;
		int j = middle + 1;
		int k = low;
		// Copy the smallest values from either the left or the right side back
		// to the original array
		while (i <= middle && j <= high) {
			checkCount++;
			if (aux[i] <= aux[j]) {
				sortedArray[k] = aux[i];
				i++;
			}
			else {
				sortedArray[k] = aux[i];
				j++;
				swapCount++;
			}
			k++;
		}
	}

	/**
	 * Shows the Sorted Array
	 */
	public void showArray() {
		for (int i = 0; i < this.sortedArray.length; i++) {
			System.out.print(this.sortedArray[i] + " ");
		}
		out.println("");
		out.println("--------------------------");
	}
}
