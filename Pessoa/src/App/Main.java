package App;

import Utils.ContactFile;

public class Main {
	public static void main(String[] args) {
		ContactFile x = new ContactFile();
		if (x.createContacts(100))
		{
			System.out.println("Ok!");
		}
		else
		{
			System.out.println("Nope!");
		}
	}
}