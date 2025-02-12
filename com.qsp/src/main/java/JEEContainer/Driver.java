package JEEContainer;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Driver {
	
	public static void main(String[] args) {
		
		
		ConfigurableApplicationContext cac=new ClassPathXmlApplicationContext("jee.xml");
		
		Person prs=cac.getBean("per)
	}

}
