package usingAnnotations;

import org.springframework.stereotype.Component;

@Component(value = "myBank")
public class Bank {

	public void scam() {
		System.out.println("Loan is a Scam");
	}
}
