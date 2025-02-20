package usingAnnotations;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Driver {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext ac=new AnnotationConfigApplicationContext(Myconfig.class);
		
		Bank bank=ac.getBean("myBank",Bank.class);
		
		bank.scam();

	}

}
