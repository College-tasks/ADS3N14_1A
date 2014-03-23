package Utils;

import java.util.Random;
import java.lang.*;
import java.io.*;

public class ContactFile {	
	
	/**
	 * Create a file (if not exists) with N contacts
	 * @param maxContacts Number of contacts to generate
	 * @return Success of fail of the operation
	 */
	public boolean createContacts(int maxContacts) {
		Writer writer = null;
		
		// Check and create the contacts file
		if (checkFileExists("C:/contacts.txt")) {
			return true;
		}

		try {
		    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:/contacts.txt"), "utf-8"));
		    
		    for (int i = 0; i < maxContacts; i++) {
		    	writer.write(generateName() + "##" + generatePhone() + "$$\n");
		    }
		    
		} catch (IOException ex) {
			System.out.println("Erro ao criar o arquivo de contatos!");
			System.out.println(ex.getMessage());
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
				System.out.println("Erro ao salvar o arquivo de contatos!");
				System.out.println(ex.getMessage());
			}
		}

		return true;
	}
	
	/**
	 * Checks if the file exists in the given path
	 * @param fPath Path of the file to check
	 * @return Bool if exists or not
	 */
	private boolean checkFileExists(String fPath) {
		File file = new File(fPath);
		return file.exists();
	}
	
	/**
	 * Generates a random complete name
	 * @return Generated name
	 */
	private String generateName() {
		String ret = "";
		
		// Create names, middle names and last names to randomize
		String[] names = { "MARCIA", "TIAGO", "JULIO", "ANGELA", "FABIO", "EDIRLEI", "RODRIGO", "FLAVIO", "CARLOS", "ANDRE", "LUCAS", "EDUARDO", "GEOVANI" };
		String[] midNames = { "LIMA", "FREITAS", "VIEIRA", "DIAS", "CRUZ", "MELO", "CHAGAS", "OLIVEIRA", "ROSA"  };
		String[] lastNames = { "OLIVEIRA", "BRASIL", "GONÇALVES", "MARTINS", "MATTOS", "SILVA", "SOUZA", "BORGES" };
		
		// util.Random = Allowed, RIGHT?
		ret = names[(new Random()).nextInt(13)] + " " + midNames[(new Random()).nextInt(9)] + " " + lastNames[(new Random()).nextInt(8)];
		return ret;
	}
	
	/**
	 * Generates a random phone
	 * @return Generated phone
	 */
	private String generatePhone() {
		String ret = "";
		String[] areaCode = { "051", "055", "045", "043", "053" };
		
		// Gets an area code
		ret = areaCode[(new Random()).nextInt(5)];
		
		for (int i = 0; i < 8; i++) {
			int n = (new Random()).nextInt(9);
			
			// Checks if the first number is 0
			if (i == 0 && n == 0)
			{
				i -= 1;
				continue;
			}
			else {
				ret += n;
			}
		}
		
		return ret;
	}
}
