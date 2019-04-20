package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.context.spi.CurrentSessionContext;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	//need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		//get the hibernate session 
		Session session = sessionFactory.getCurrentSession();
		
		//create query
		Query<Customer> query = session.createQuery("from Customer", Customer.class);
		
		//get resultlist
		List<Customer> resultList = query.getResultList();
		
		//return list
		return resultList;
	}

	@Override
	public void saveCustomer(Customer customer) {
		
		//get current session
		Session currectSession = sessionFactory.getCurrentSession();
		
		//save or update the customer to db
		currectSession.saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomer(int id) {

		//get session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//retrieve from db using primary id
		Customer customer = currentSession.get(Customer.class,  id);
		
		return customer;
	}

	@Override
	public void deleteCustomer(int id) {

		//get session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//delete  user
		Query query = currentSession.createQuery("delete from Customer where id=:customerId");
		
		query.setParameter("customerId", id);
		
		query.executeUpdate();

	}

}
