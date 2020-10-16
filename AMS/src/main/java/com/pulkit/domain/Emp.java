package com.pulkit.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="Employee")
public class Emp {

	@Id
	@GeneratedValue
	private int empid;
	private String mail;
	private String password;
	
	@ManyToMany(cascade= {CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinTable(
			name="EMPLOYEE_GOALS",
			joinColumns= {@JoinColumn(name="empid")},
			inverseJoinColumns= {@JoinColumn(name="id")}
			)
	Set<Goals> goals=new HashSet<Goals>();
	
	
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Goals> getGoals() {
		return goals;
	}
	public void setGoals(Set<Goals> goals) {
		this.goals = goals;
	}
	@Override
	public String toString() {
		return "Emp [empid=" + empid + ", mail=" + mail + ", password=" + password + ", goals=" + goals + "]";
	}
	
}
