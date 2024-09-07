package com.r3sys.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class RawMaterial {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id; 
	private String name;
	private int quantity;
	private String unit;
	private float costPerUnit;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public float getCostPerUnit() {
		return costPerUnit;
	}
	public void setCostPerUnit(float costPerUnit) {
		this.costPerUnit = costPerUnit;
	}
	
	public RawMaterial(int id, String name, int quantity, String unit, float costPerUnit) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.unit = unit;
		this.costPerUnit = costPerUnit;
	}
	
	public RawMaterial() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "RawMaterial [id=" + id + ", name=" + name + ", quantity=" + quantity + ", unit=" + unit
				+ ", costPerUnit=" + costPerUnit + "]";
	}
	
}
