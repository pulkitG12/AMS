package com.pulkit.dao;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.pulkit.config.dbms_configuration;
import com.pulkit.domain.Emp;
import com.pulkit.domain.Goals;
public class empDao {

	private dbms_configuration config=new dbms_configuration();
	public void createEmp(Emp e)
	{
		Session session = config.getSf().openSession();  
        Transaction t = session.beginTransaction(); 
     
        session.save(e);
    	System.out.println("CREATED");
    	//session.close();
        t.commit();
        
	}
	
	public Emp readEmp(int id)
	{
		Session session = config.getSf().openSession();  
        Transaction t = session.beginTransaction(); 
        
        Emp e=(Emp)session.get(Emp.class,id);
        
        t.commit();
    	System.out.println("READ");
    	session.close();
        return e;
	}
	
	public void addGoal(Emp e,Goals g)
	{
		Session session = config.getSf().openSession();  
        Transaction t = session.beginTransaction(); 
		e.getGoals().add(g);
		System.out.println(e);
		session.update(e);
		System.out.println(g);
    	System.out.println("CREATED");
    	//session.close();
        t.commit();
	}
	public List<Goals> selectedGoals(int id)
	{
		List<Goals> l=new LinkedList<Goals>();
		Session session = config.getSf().openSession();  
        Transaction t = session.beginTransaction();
        String hql="SELECT e.goals from Emp e where e.empid = :employee_id";
        Query q=session.createQuery(hql);
        q.setParameter("employee_id",id);
        l=q.list();
        //session.close();
        t.commit();
		return l;
 	}
	public void delGoal(int empid,int gid)
	{
		Session session = config.getSf().openSession();  
        Transaction t = session.beginTransaction(); 
        Emp e=readEmp(empid);
        Set<Goals> selected=e.getGoals();
        for(Goals go:selected)
        {
        	if(go.getId()==gid)
        		{
        		selected.remove(go);
        		break;
        		}
        }
        e.setGoals(selected);
        System.out.println(e);
        session.update(e);
    	//session.close();
        t.commit();
	}
	
	
	public List<Emp> getAllEmp()
	{
		Session session = config.getSf().openSession();  
        Transaction t = session.beginTransaction(); 
		List<Emp> l=session.createQuery("FROM Emp").list();
		t.commit();
		session.close();
		return l;
	}	
	
	
	
}
