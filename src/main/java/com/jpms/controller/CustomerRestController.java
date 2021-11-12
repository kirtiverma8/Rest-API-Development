package com.jpms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jpms.bean.Customer;
import com.jpms.dao.CustomerDao;

//import io.swagger.annotations.Api;



@RestController
@CrossOrigin
//@RequestMapping("/customerService")
public class CustomerRestController {

	
	@Autowired
	private CustomerDao CustomerDao;

	
	@GetMapping("/customers")
		public List getCustomers() {
		
	
		
		return CustomerDao.list();
	}

	@GetMapping("/customers/{id}")
	public ResponseEntity getCustomer(@PathVariable("id") Long id) {

		Customer customer = CustomerDao.get(id);
		if (customer == null) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(customer, HttpStatus.OK);
	}

	
	
	
	@PostMapping(value = "/post/customers")
	public ResponseEntity createCustomer(@RequestBody Customer customer) {

		CustomerDao.insert(customer);

		return new ResponseEntity(customer, HttpStatus.OK);
	}

	
	@DeleteMapping("/delete/customers/{id}")
	public ResponseEntity deleteCustomer(@PathVariable Long id) {

		if (null == CustomerDao.delete(id)) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(id, HttpStatus.OK);

	}

	@PutMapping("/put/customers/{id}")
	public ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {

		customer = CustomerDao.update(id, customer);

		if (null == customer) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(customer, HttpStatus.OK);
	}

}