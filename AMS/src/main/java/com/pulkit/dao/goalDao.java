package com.pulkit.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.pulkit.config.dbms_configuration;
import com.pulkit.domain.Goals;


public class goalDao {

	private dbms_configuration config=new dbms_configuration();
	public void createGoal(Goals e)
	{
		Session session = config.getSf().openSession();  
        Transaction t = session.beginTransaction(); 
     
        session.save(e);
    	System.out.println("CREATED");

        t.commit();
        
	}
	public List<Goals> getAllGoals()
	{
		Session session = config.getSf().openSession();  
        Transaction t = session.beginTransaction(); 
		List<Goals> l=session.createQuery("FROM Goals").list();
		
		t.commit();
		return l;
	}	
	public Goals readGoal(int id)
	{
		Session session = config.getSf().openSession();  
        Transaction t = session.beginTransaction(); 
        
        Goals e=(Goals)session.get(Goals.class,id);
        
        t.commit();
    	System.out.println("READ");
    	session.close();
        return e;
	}
	public void updateGoal(Goals e)
	{
		Session session = config.getSf().openSession();  
        Transaction t = session.beginTransaction(); 
      
        session.update(e);
    	System.out.println("UPDATED");

        t.commit();
        
	}
	public void deleteGoal(int id)
	{
		Session session = config.getSf().openSession();  
        Transaction t = session.beginTransaction(); 
        Goals e=(Goals)session.get(Goals.class,id);
        if(e!=null)
        {
        	session.delete(e);
        	System.out.println("DELETED");
        }
        t.commit();
        
	}
}