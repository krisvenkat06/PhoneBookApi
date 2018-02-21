package com.springBoot.phoneBook.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.springBoot.phoneBook.model.Contact;
import com.springBoot.phoneBook.repositories.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService{

	@Autowired
	private ContactRepository contactRepository;
	
	public void saveContact(Contact contact) {
		contactRepository.save(contact);
	}
	
	public void updateContact(Contact contact) {
		contactRepository.save(contact);
	}
	
	public void deleteContact(Contact contact) {
		contactRepository.delete(contact);
	}
	
	public List<Contact> listAllContacts() {
		List<Contact> contacts = new ArrayList<>();
		contactRepository.findAll().forEach(contacts::add);
		System.out.println("Service - In listAllContacts-->>"+contacts);
		 return contacts;
	}

	@Override
	public boolean isContactExist(Contact contact) {
		 return findByName(contact.getPhoneNumber()) != null;
	}
	
	public Contact findByName(String phoneNumber) {
	//	return contactRepository.findByName(phoneNumber);
		return null;
	}
	
	public Contact findContactById(String phone) {
		return contactRepository.findOne(phone);
	}
}
