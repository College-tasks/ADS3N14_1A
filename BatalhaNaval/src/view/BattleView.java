package view;

import static java.lang.System.out;

/**
 * View of the Battle
 * @author Simor
 *
 */
public class BattleView {
	
	/**
	 * Shows the table
	 * @param table Matrix of the table
	 */
	public void showTable(String[][] table)
	{
		for(int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				out.print(table[i][j] + " ");
			}
			out.println("");
		}
	}
}
