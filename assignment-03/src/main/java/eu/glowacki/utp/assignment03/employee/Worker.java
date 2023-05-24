package eu.glowacki.utp.assignment03.employee;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Worker extends Employee {

	// (assignment 02)
	// attributes:
	// * employment date
	// * bonus

    private final LocalDate _employmentDate;
    private BigDecimal _bonus;
    private final boolean _bonusExistence;

    public Worker(String _firstName, String _surname, LocalDate _date, BigDecimal _salary, Manager _manager, LocalDate _employmentDate, BigDecimal _bonus, boolean bonusExistence) {
        super(_firstName, _surname, _date, _salary, _manager);
        this._employmentDate = _employmentDate;
        this._bonus = _bonus;
        _bonusExistence = bonusExistence;
    }

    public LocalDate get_employmentDate() {
        return _employmentDate;
    }

    public BigDecimal get_bonus() {
        return _bonus;
    }

    public void set_bonus(BigDecimal _bonus) {
        this._bonus = _bonus;
    }

	// (assignment 03)
	// attributes:

    // * has bonus
	//
	// methods:
	// * seniority is longer than given number of years (seniority - sta¿)
	// * seniority is longer than given number of months
	// * has bonus greater than given amount of money

    public boolean bonusIsGreaterThan(Worker worker1, BigDecimal money_amount){
        if(worker1._bonusExistence)
        return worker1._bonus.compareTo(money_amount) >0;
        else return false;
    }


    public boolean seniorityIsLongerThan (Worker worker1, int numberOfYears){
        return LocalDate.now().getYear() - worker1.get_employmentDate().getYear() > numberOfYears;
    }

    public boolean seniorityIsShorterThan (Worker worker1, int numberOfYears){
        return LocalDate.now().getYear() - worker1.get_employmentDate().getYear() < numberOfYears;
    }

    public boolean seniorityIsLongerThanMonths(Worker worker1, int numberOfMonths){
        return (LocalDate.now().getYear()   - worker1.get_employmentDate().getYear())*12 +LocalDate.now().getMonthValue()> numberOfMonths;
    }

    public boolean seniorityIsLongerThanAnotherWorker(Worker worker1, Worker worker2){
        return worker1.get_employmentDate().getYear() - worker2.get_employmentDate().getYear()>0;
    }

}