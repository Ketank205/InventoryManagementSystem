package com.r3sys.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class IssueRaw {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id; 
	private int rawId;
	private String rawName;
	private String issuerName;
	private int quantity;
	private String issueDate;
	private String issueTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getRawId() {
		return rawId;
	}
	public void setRawId(int rawId) {
		this.rawId = rawId;
	}
	
	public String getRawName() {
		return rawName;
	}
	public void setRawName(String rawName) {
		this.rawName = rawName;
	}
	
	public String getIssuerName() {
		return issuerName;
	}
	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	
	public String getIssueTime() {
		return issueTime;
	}
	public void setIssueTime(String issueTime) {
		this.issueTime = issueTime;
	}
	
	public IssueRaw(int id, int rawId, String rawName, String issuerName, int quantity, String issueDate,
			String issueTime) {
		super();
		this.id = id;
		this.rawId = rawId;
		this.rawName = rawName;
		this.issuerName = issuerName;
		this.quantity = quantity;
		this.issueDate = issueDate;
		this.issueTime = issueTime;
	}
	
	public IssueRaw() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "IssueRaw [id=" + id + ", rawId=" + rawId + ", rawName=" + rawName + ", issuerName=" + issuerName
				+ ", quantity=" + quantity + ", issueDate=" + issueDate + ", issueTime=" + issueTime + "]";
	}
	
	
	
}
