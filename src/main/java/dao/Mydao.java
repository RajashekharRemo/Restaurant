package dao;

 import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dto.Customer;

public class Mydao {
	EntityManagerFactory e = Persistence.createEntityManagerFactory("dev");
	EntityManager m = e.createEntityManager();
	EntityTransaction t = m.getTransaction();

	public void save(Customer c) {
		t.begin();
		m.persist(c);
		t.commit();  
		// if u change the dependencies ctrl shift o
	}

	public Customer fetchByEmail(String email) {
		
		// import Query interface   from persistence
		
		Query query=m.createQuery("select ab from Customer ab where email=?1").setParameter(1, email);// here email from inside quotes is from Customer variable & another one is from sign up class
		List<Customer> list=query.getResultList();  // inside List Customer object will be there
	if(list.isEmpty()) {
		return null;
	}else {
		return list.get(0);
	}
	} 
	
	
	

//	List<Customer> list=m.createQuery("select ab from Customer ab where email=?1").setParameter(1, email).getResultList();  // inside List Customer object will be there
//if(list.isEmpty()) {
//	return null;
//}else {
//	return list.get(0);
//}
//} 
	
	
	

	public Customer fetchByMobile(long num) {
		Query query=m.createQuery("select ab from Customer ab where num=?1").setParameter(1, num);
		List<Customer> list=query.getResultList();  // inside List Customer object will be there
	if(list.isEmpty()) {
		return null;
	}else {
		return list.get(0);
	}
	}
}
