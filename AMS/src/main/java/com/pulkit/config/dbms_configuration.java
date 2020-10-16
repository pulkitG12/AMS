package com.pulkit.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.pulkit.domain.Emp;
import com.pulkit.domain.Goals;


public class dbms_configuration {

	private static SessionFactory sf;
	 public SessionFactory getSf()
	    {
	    	if(sf==null)
	    	{
	    		Configuration con=new Configuration().configure().addAnnotatedClass(Goals.class).addAnnotatedClass(Emp.class);
	    	    
	    		ServiceRegistry reg=new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
	    		sf =con.buildSessionFactory(reg);  
	    	}
	    	return sf;
	    }
}
