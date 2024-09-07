package com.r3sys.Dao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.r3sys.Models.Manager;

@Component
public class ManagerDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public int adminAuthentication(String email, String password){
		Manager m = (Manager)hibernateTemplate.get(Manager.class, email);
		if(m.getEmail().equals(email) && m.getPassword().equals(password)){
			return 1;
		}
		else{
			return 0;
		}
	}
	
	@Transactional
	public int changePassword(String email,String newPassword){
		Manager m = (Manager)hibernateTemplate.get(Manager.class, email);
		if(m.getEmail().equals(email)){
			m.setPassword(newPassword);
			return 1;
		}
		else{
			return 0;
		}
	}
}
