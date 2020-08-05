package Application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import Entities.employee;

public class Program {

	public static void main(String[] args) {

		// exercise resolution :
		// https://youtu.be/Xj-osdBe3TE
		// https://github.com/acenelio/list1-java

		//sets the parameter ".,"  to the "US" standard
		Locale.setDefault(Locale.US);
		
		//instantiates the "scanner" of what was typed(enter) on the screen
		Scanner sc = new Scanner(System.in);
		
		//Variables ----------------------------------------------------------------------------------
		int intNumeroRegistros;
		
		// PART 1 - READING DATA:
		
		System.out.println();
		System.out.println("************************************************************");
		System.out.println("*              BOM DIA !!!!                                *");
		System.out.println("*                                       2                   *");
		System.out.println("*              REGISTRATION OF EMPLOYEES                   *");
		System.out.println("************************************************************");
		System.out.println("*                                                          *");
		System.out.println("*  How many employees will be registered ?                 *");
		System.out.println("*                                                          *");
		System.out.print("*  - Employees quantity : ");
		intNumeroRegistros = sc.nextInt();
		System.out.println("*                                                          *");
		System.out.println("************************************************************");
		System.out.println("*                                                          *");
		
		List<employee> list = new ArrayList<>(); //	create list and instantiate 
		//list is a interface and can not be instantiated, so we use an ArrayList 
		
		//Initialize data entry and reading from employees--------------------------------------------
		for (int i=1; i <= intNumeroRegistros; i++) {
			

			if (i < 10) {
			   System.out.println("*  Employee #0" + i + ":                                           *");
			} else {
			   System.out.println("*  Employee #"  + i + ":                                           *");
			}
			
			System.out.print("*  - Id     : ");
			int intId = sc.nextInt();
			
			// validate Id ---------------------------------------------------------------------------
			// enquanto o id digitado for um id existente na list "(hasId(list, intId))"
			          // hasId - função de validação de id, esta no final do codigo
			while (hasId(list, intId)) {
				System.out.print("*    Attention - Id already taken ! Try again : ");
				intId = sc.nextInt();
			}
			//----------------------------------------------------------------------------------------
			
			System.out.print("*  - Name   : ");
			sc.nextLine(); //to clear the input buffer and the variable name is not empty, enter of the sc.nextInt() previous
			String strName = sc.nextLine();
			
			// validate Name ---------------------------------------------------------------------------
			// enquanto o nome digitado for um nome existente na list "(hasId(list, strName))"
	          // hasId - função de validação de name, esta no final do codigo
        	if (hasName(list, strName.toUpperCase()) != null) {
		          System.out.println("*    Attention - Name already taken !                      *");
		          System.out.print("*              - Do you want to try another name (y/n) ? ");
		  		  char resName = sc.next().charAt(0);
 					
		  		  if (resName == 'y') {
			          System.out.print("*              - Enter new name : ");
			          sc.nextLine();
					  strName = sc.nextLine();		  			  
					  
					  while (hasName(list, strName.toUpperCase()) != null) {
				            System.out.print("*              - Name already taken ! Enter new name : ");
					        //sc.nextLine();
							strName = sc.nextLine();	
					  }
		  		  }
        	}
	        //----------------------------------------------------------------------------------------


			System.out.print("*  - Salary : R$ ");
			double douSalary = sc.nextDouble();
			
			//default mode ---------------------------------------------------------------------------
			//employee emp = new employee(id, name, salary)
			//list.add(emp);	
			//simplified mode ------------------------------------------------------------------------
			list.add(new employee(intId, strName.toUpperCase(), douSalary));
			//----------------------------------------------------------------------------------------
			
			System.out.println("*                                                          *");

		}
		//--------------------------------------------------------------------------------------------
		
		
		// PART 2 - UPDATING SALARY OF GIVEN EMPLOYEE ------------------------------------------------
		System.out.println("*                                                          *");	
		System.out.print("*  Do you want to change the salary of any employee (y/n)?");
		char resSalary = sc.next().charAt(0);
		
		if (resSalary == 'y') {

			System.out.print("*  Enter the employee id that will have salary increase: ");
			int intIdSalary = sc.nextInt();
			
			Integer intPosition = positionID(list, intIdSalary);

		       // este é um modelo para localizar o id que tera o salary alterado
		       // usando a função stream é possivel usar comandos lambida no filter 
	    	//employee emp = list.stream().filter(x -> x.getId() == intIdSalary).findFirst().orElse(null);
		       // x  argumento de pesquisa 
		       // -> quando
		       // x.getId da lista for = id passado na variavel intIdSalary pela pergunta 		
               // se a igualdade for atendida pegar o primeiro resultado "findFirst()"
			   // se não retorna null "orElse(null)"	
			
			
			if (intPosition == null) { // emp
				System.out.println("*  This id does not exist !");
				System.out.print("*  Do you want to try another id (y/n)? ");
				char response = sc.next().charAt(0);
				if (response == 'y') {
					while (intPosition == null) {
						System.out.print("*  Enter new ID :");
						intIdSalary = sc.nextInt();
						intPosition = positionID(list, intIdSalary);
						
					}
					/*
					System.out.print("*  Enter the percentage: ");
					double douPercentage = sc.nextDouble();
					list.get(intPosition).increaseSalary(douPercentage);
					*/
				}
			} 
			
			/*
			else {
				System.out.print("*  Enter the percentage: ");
				double douPercentage = sc.nextDouble();
				list.get(intPosition).increaseSalary(douPercentage); 
			
			}
            */
			
			System.out.print("*  Enter the percentage: ");
			double douPercentage = sc.nextDouble();
			//emp.increaseSalary(percentage);
			list.get(intPosition).increaseSalary(douPercentage); 
			
		}
		
		System.out.println("*                                                          *");
		//--------------------------------------------------------------------------------------------
		
		
		// PART 3 - LISTING EMPLOYEES-----------------------------------------------------------------
		System.out.println();
		System.out.println("*  List of employees:                                      *");
		for (employee obj : list) {
			System.out.println("*  - " + obj);
		}
		System.out.println("*                                                          *");
		System.out.println("************************************************************");
		//--------------------------------------------------------------------------------------------
		
		
		//scanner finalized
		sc.close();
		
	}

	// Auxiliary function to search for "id" in the "list"
	// it is not necessary to use "public" because the function is in the same
	// class, it is optional
	// the function receives 1 list and 1 ID, returns the position of the id within
	// the list
	// At this point for the function to be called in the same class it must be
	// "static" because "Main" is "static"
	public static Integer positionID(List<employee> list, int id) {

		for (int i = 0; i < list.size(); i++) {
			// System.out.print(list.get(i).getId()); //só para teste
			if (list.get(i).getId() == id) {
				return i;
			}

		}
		return null; // to return "null", it is necessary to use the "Integer" type in the function
						// declaration
	}

	public static boolean hasId(List<employee> list, int id) {
		employee emp = list.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
		return emp != null;
	}
	
	public static Integer hasName(List<employee> list, String name) {

		//employee emp = list.stream().filter(x -> x.getName().equals(name)).findFirst().orElse(null);
		//para comparar string usar o equals(x) , pois o == compara endereço de memoria
		for (int i = 0; i < list.size(); i++) {
			//System.out.print(list.get(i).getName()); //só para teste
			if (list.get(i).getName().equals(name)) {
				return i;
			}

		}
		return null; // to return "null", it is necessary to use the "Integer" type in the function
						// declaration
	}

}
