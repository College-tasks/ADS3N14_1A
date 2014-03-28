package Utils;

import model.*;
import structures.*;

public class SortedListUtils {
	private SortedList<Contact> list;
	
	/**
	 * Constructor
	 * @param list SortedList
	 */
	public SortedListUtils(SortedList<Contact> list)
	{
		this.list = list;
	}

	/**
	 * Search the list of contacts and return (if finds) the first contact that starts
	 * with the input string
	 * @param chr String to be searched
	 * @return Node found (If not found, returns null)
	 */
	public Node<Contact> getFromChar(String chr)
	{
		Node<Contact> current = this.list.getHead();
		
		while (current != null)
		{
			if (current.getKey().getName().toLowerCase().startsWith(chr.toLowerCase()))
			{
				return current;
			}
			current = current.getNext();
		}
		
		return null;
	}
	
	/**
	 * Search the list of contacts and return the count of comparisons made
	 * @param chr String to be searched
	 * @return Count of comparisons made
	 */
	public int getFromChar(String chr, boolean flag)
	{
		Node<Contact> current = this.list.getHead();
		int count = 0;
		
		while (current != null)
		{
			count++;
			if (current.getKey().getName().toLowerCase().startsWith(chr.toLowerCase()))
			{
				break;
			}
			current = current.getNext();
		}
		
		return count;
	}
}