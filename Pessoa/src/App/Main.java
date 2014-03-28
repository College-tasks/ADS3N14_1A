package App;

import controller.ContactController;
import Utils.ContactFile;
import structures.*;
import model.*;

public class Main {
	public static void main(String[] args) {
		ContactController controller = new ContactController();
		(new ContactFile()).createContacts(50);
		controller.showContact();
	}
}