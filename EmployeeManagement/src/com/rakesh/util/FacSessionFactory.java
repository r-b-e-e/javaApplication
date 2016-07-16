package com.rakesh.util;

import java.util.Scanner;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FacSessionFactory { 
	static final SessionFactory sessionfactory =  new Configuration().configure("employeeprofile.cfg.xml").buildSessionFactory();
	public static SessionFactory getSessionFactory() {
		return sessionfactory;
	}
}
