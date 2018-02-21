package com.springBoot.phoneBook.service;


import java.util.List;

import com.springBoot.phoneBook.model.Contact;

public interface ContactService {
	
	void saveContact(Contact contact);
	
	void updateContact(Contact contact);
	
	void deleteContact(Contact contact);
	
	Contact findContactById(String phone);
	
	List<Contact> listAllContacts();
	
	boolean isContactExist(Contact contact);
}
