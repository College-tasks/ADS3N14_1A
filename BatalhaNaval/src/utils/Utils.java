package utils;

public class Utils {
	/**
	 * Convert a letter into an int value
	 * @param letter Letter to be converted
	 * @return Integer value of the given letter
	 */
	public static int convertLetterToInt(char letter)
	{
		return ((int)Character.toLowerCase(letter)) - 97;
	}
}
