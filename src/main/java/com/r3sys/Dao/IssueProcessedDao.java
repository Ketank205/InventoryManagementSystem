package com.r3sys.Dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.r3sys.Models.IssueProcessed;
import com.r3sys.Models.ProcessedItem;

@Component
public class IssueProcessedDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@Transactional
	public int issueProcessedItem(IssueProcessed ip, int processedId, int quantity){
		ProcessedItem pi = (ProcessedItem)hibernateTemplate.get(ProcessedItem.class, processedId);
		int quant = pi.getQuantity();
		if(quant<quantity){
			return 0;
		}
		else{
			quant -= quantity;
			pi.setQuantity(quant);
			return (Integer)hibernateTemplate.save(ip); 
		}
	}
	
	public List<IssueProcessed> viewIssueProcessedItem(){
		return (List<IssueProcessed>)hibernateTemplate.loadAll(IssueProcessed.class);
	}
	
	@Transactional
	public void deleteIssueProcessedItem(int id) {
		IssueProcessed c = (IssueProcessed)hibernateTemplate.get(IssueProcessed.class, id);
		hibernateTemplate.delete(c);		
	}
}
