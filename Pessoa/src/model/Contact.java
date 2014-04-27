package model;

/***
 * Model class -> Definition of a Contact
 * 
 * @author Simor
 * 
 */
public class Contact implements Comparable<Contact> {
	// Attributes
	private String name;
	private String phone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public int compareTo(Contact contact) {
		return this.getName().toLowerCase().compareTo(contact.getName().toLowerCase());
	}
	

}