package com.springBoot.phoneBook.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.springBoot.phoneBook.model.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact,String>{

	public Contact findByPhoneNumber(String phoneNumber);

}
