 package com.r3sys.Dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.r3sys.Models.RawMaterial;

@Component
public class RawMaterialDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@Transactional
	public int addRawMaterial(RawMaterial rm) {
		return (Integer)hibernateTemplate.save(rm);
	}
	
	public List<RawMaterial> viewRawMaterial(){
		return (List<RawMaterial>)hibernateTemplate.loadAll(RawMaterial.class);
	}
	
	@Transactional
	public void updateRawMaterial(int id, int newQuantity){
		RawMaterial rm = (RawMaterial)hibernateTemplate.get(RawMaterial.class, id);
		rm.setQuantity(newQuantity);
		hibernateTemplate.update(rm);
	}
	
	@Transactional
	public void deleteRawMaterial(int id) {
		RawMaterial c = (RawMaterial)hibernateTemplate.get(RawMaterial.class, id);
		hibernateTemplate.delete(c);
	}
}