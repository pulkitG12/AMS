package com.pulkit.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name="GOALS_TABLE")
public class Goals {
	
	@Id
	@GeneratedValue
	//@Pattern(regexp="^[0-9]{5}",message="*length must be 5")
	private int id;
	private String name;
	@Column(name="Description")
	private String desc;
	
	@Column(name="Minimum")
	@Pattern(regexp="^[0-9]{2}",message="*Should be a number less than 100")
	private String min;
	
	
	public Goals()
	{
		
	}
	public Goals(int id, String name, String desc, String min) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.min = min;
	}
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getMin() {
		return min;
	}
	public void setMin(String min) {
		this.min = min;
	}
	@Override
	public String toString() {
		return "Goals [id=" + id + ", name=" + name + ", desc=" + desc + ", min=" + min + "]";
	}
	
}
