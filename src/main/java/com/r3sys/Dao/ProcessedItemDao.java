package com.r3sys.Dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.r3sys.Models.IssueProcessed;
import com.r3sys.Models.ProcessedItem;
import com.r3sys.Models.RawMaterial;


@Component
public class ProcessedItemDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	@Transactional
	public int addProcessedItem(ProcessedItem pi) {
		return (Integer)hibernateTemplate.save(pi);
	}
	
	public List<ProcessedItem> viewProcessedItem(){
		return (List<ProcessedItem>)hibernateTemplate.loadAll(ProcessedItem.class);
	}
	
	@Transactional
	public void updateProcessedItem(int id, int newQuantity){
		ProcessedItem pi = (ProcessedItem)hibernateTemplate.get(ProcessedItem.class, id);
		pi.setQuantity(newQuantity);
		hibernateTemplate.update(pi);
	}
	
	@Transactional
	public void deleteProcessedItem(int id) {
		ProcessedItem c = (ProcessedItem)hibernateTemplate.get(ProcessedItem.class, id);
		hibernateTemplate.delete(c);
	}
	
	public int checkAvailabilityPi(int id) {
		int i = 0;
		ProcessedItem pi = (ProcessedItem)hibernateTemplate.get(ProcessedItem.class, id);
		if(pi.getQuantity()==0){
			return 0;
		}
		else{
			return pi.getQuantity();
		}
		
	}
}
