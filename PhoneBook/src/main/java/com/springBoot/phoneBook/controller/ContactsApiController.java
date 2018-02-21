package com.springBoot.phoneBook.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.springBoot.phoneBook.model.Contact;
import com.springBoot.phoneBook.repositories.ContactRepository;
import com.springBoot.phoneBook.service.ContactService;

@RestController
@RequestMapping("/contacts")
public class ContactsApiController {
	
	public static final Logger logger = LoggerFactory.getLogger(ContactsApiController.class);
	
	@Autowired
	ContactService contactService;
	
	@GetMapping("/retrieve")
	public ResponseEntity<List<Contact>> listAllContacts() {
		System.out.println("we arre here--->>>>>");
		logger.info("In Retrieve Method -- Controller Entered");
		List<Contact> contacts = contactService.listAllContacts();
		if(contacts.isEmpty()) {
			logger.info("In Retrieve Method -- Contact are Empty");
			return new ResponseEntity<List<Contact>>(HttpStatus.NO_CONTENT);
		}
		logger.info("Contacts/retrieve - returned ArrayList");
		return new ResponseEntity<List<Contact>>(contacts, HttpStatus.OK);
	}
	
	@PostMapping("/add") 
	public ResponseEntity<?> createContact(@RequestBody Contact contactData, UriComponentsBuilder ucBuilder) {
		
		if(contactService.isContactExist(contactData)) {
			System.out.println("Response -->"+new ResponseEntity<String>(HttpStatus.CONFLICT));	
			return new ResponseEntity<String>(HttpStatus.CONFLICT);
		};
		System.out.println("In controller Add Method -->>"+contactData);
		System.out.println("Add Contact Info-->>"+contactData.getPhoneNumber()+"**"+contactData.getFirstName()+"**"+
		contactData.getLastName()+"**"+contactData.getEmailAddr()+"**");
		contactService.saveContact(contactData);
		//HttpHeaders headers = new HttpHeaders();
		//headers.setLocation(ucBuilder.path("/contacts"));
		System.out.println("Response -->"+new ResponseEntity<String>(HttpStatus.CREATED));
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/update/{phone}",method=RequestMethod.PUT)
	public void updateContact(@PathVariable String phone, @RequestBody Contact contact) {		
		System.out.println("Updating User with id {}"+phone);
		contactService.updateContact(contact);

	}
	
	@RequestMapping(value="/remove/{phone}",method=RequestMethod.DELETE)
	public void deleteContact(@PathVariable String phone) {
		System.out.println("Deleting User with id {}"+phone);
		Contact contToRemove = contactService.findContactById(phone);
		if(contToRemove != null) {
			contactService.deleteContact(contToRemove);
		}

	}
	
	

}
