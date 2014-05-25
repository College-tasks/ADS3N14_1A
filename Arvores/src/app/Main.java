package app;

import utils.*;
import controller.*;

public class Main {
	public static void main(String[] args) {
		// Initialize the app
		/* T5
		(new ContactFile()).createContacts(30);
		AppController controller = new AppController();
		controller.showContact();
		*/
		
		// T6
		AppController2 controller = new AppController2();
		controller.initApp();
	}
}