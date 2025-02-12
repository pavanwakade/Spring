package com.qsp;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class StudentDriver {

	public static void main(String[] args) {
		
		Resource res=new ClassPathResource("Config.xml");
		
		BeanFactory bf=new XmlBeanFactory(res);
		
		Student student=bf.getBean("stud", Student.class);
		student.study();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
