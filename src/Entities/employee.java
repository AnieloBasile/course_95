package Entities;

public class employee {
    // attributes , user type "private" to protect variables and  ------------------------------------
	private Integer id;
	private String  name;
	private Double  salary;
	//------------------------------------------------------------------------------------------------
	

	//constructors -----------------------------------------------------------------------------------
	public employee(Integer id, String name, Double salary) {
		//super(); not necessary 
		this.id = id;
		this.name = name;
		this.salary = salary;
	}

	public employee() {
		super();
	}
	//------------------------------------------------------------------------------------------------

	
	//getters and setters ----------------------------------------------------------------------------
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}
	//------------------------------------------------------------------------------------------------
	
	
	//methods ----------------------------------------------------------------------------------------
	// increase salary with application of percentage %
	public void increaseSalary(double percentage) {
		// salary = salary + (salary * percentage / 100.00); simplifying
		// don't need "this" because it's unambiguous
		salary +=  salary * percentage / 100.00;
	}
	
	// format string 
	@Override //valida metodos herdados
	public String toString() {
		return id + ", " + name + ", " + String.format("%.2f", salary);
	}
	//------------------------------------------------------------------------------------------------
	
}
