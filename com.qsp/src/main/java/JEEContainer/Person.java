package JEEContainer;

public class Person {
	
	Mobile mob;

	void study() {
		System.out.println("person need to do study");
		
		mob.ring();
		
	}

	public Mobile getMob() {
		return mob;
	}

	public void setMob(Mobile mob) {
		this.mob = mob;
	}
}
