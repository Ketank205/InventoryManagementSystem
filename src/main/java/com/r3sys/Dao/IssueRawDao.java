package com.r3sys.Dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.r3sys.Models.IssueRaw;
import com.r3sys.Models.RawMaterial;

@Component
public class IssueRawDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@Transactional
	public int issueRawMaterial(IssueRaw ir, int rawId, int quantity){
		RawMaterial rm = (RawMaterial)hibernateTemplate.get(RawMaterial.class, rawId);
		int quant = rm.getQuantity();
		if(quant<quantity){
			return 0;
		}
		else{
			quant -= quantity;
			rm.setQuantity(quant);
			return (Integer)hibernateTemplate.save(ir); 
		}
	}
	
	public List<IssueRaw> viewIssueRawMaterial(){
		return (List<IssueRaw>)hibernateTemplate.loadAll(IssueRaw.class);
	}
	
	@Transactional
	public void deleteIssueRaw(int id) {
		IssueRaw c = (IssueRaw)hibernateTemplate.get(IssueRaw.class, id);
		hibernateTemplate.delete(c);		
	}
}
