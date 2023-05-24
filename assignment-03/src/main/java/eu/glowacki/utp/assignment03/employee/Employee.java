package eu.glowacki.utp.assignment03.employee;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee extends Person {

	// (assignment 02)
	// attributes:
	// * salary
	// * manager (empty if at top of hierarchy)


    private BigDecimal _salary;
    private final Manager _manager;

    public Employee(String _firstName, String _surname, LocalDate _date, BigDecimal _salary, Manager _manager) {
        super(_firstName, _surname, _date);
        this._salary = _salary;
        this._manager = _manager;
    }

    public BigDecimal get_salary() {
        return _salary;
    }

    public Manager get_manager() {
        return _manager;
    }

    public void set_salary(BigDecimal _salary) {
        this._salary = _salary;
    }

// (assignment 03)
	// methods:
	// * salary is greater than given amount of money
	// * salary is less than given amount of money
	// * compare salary with other employee salary

//    public BigDecimal salaryIsGreaterThan(Employee employee1, BigDecimal money_amount){
//        if(employee1 ==null || money_amount == null) return null;
//        if(employee1.get_salary().compareTo(money_amount) > 0 ) return employee1._salary;
//        else return money_amount;
//    }
//    public BigDecimal salaryIsLessThan(Employee employee1, BigDecimal money_amount){
//        if(employee1 ==null || money_amount == null) return null;
//        if(employee1.get_salary().compareTo(money_amount) < 0 ) return employee1._salary;
//        else return money_amount;
//    }


    public static boolean salaryIsGreaterThan(Employee employee1, BigDecimal money_amount){
        return employee1.get_salary().compareTo(money_amount) >0;
    }

    public static boolean salaryIsLessThan(Employee employee1, BigDecimal money_amount){
        return employee1.get_salary().compareTo(money_amount) <0;
    }

    public Employee compareSalary(Employee employee1, Employee employee2){
        if(employee1 ==null || employee2 == null) return null;
        if(employee1.get_salary().compareTo(employee2.get_salary()) > 0){
            System.out.println(employee1.get_firstName()+ "'s salary is greater than "+ employee2.get_firstName()); return employee1;}
        else if(employee1.get_salary().compareTo(employee2.get_salary()) < 0){
            System.out.println(employee1.get_firstName()+ "'s salary is less than "+ employee2.get_firstName()); return employee2;}
        else System.out.println("Salaries are equal");
        return null;
    }

}