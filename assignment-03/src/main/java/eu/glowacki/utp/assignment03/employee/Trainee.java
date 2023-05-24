package eu.glowacki.utp.assignment03.employee;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Trainee extends Employee {

	// (assignment 02)
	// attributes:
	// * practice start date
	// * practice length (in days)

    private final LocalDate _startDate;

    public Trainee(String _firstName, String _surname, LocalDate _date, BigDecimal _salary, Manager _manager, LocalDate _startDate) {
        super(_firstName, _surname, _date, _salary, _manager);
        this._startDate = _startDate;
    }

    public LocalDate get_startDate() {
        return _startDate;
    }

    public long get_length() {
        return LocalDate.now().toEpochDay()-_startDate.toEpochDay();
    }
	
	// (assignment 03)
	// * practice length is shorter than given number of days
	// * practice length is longer than given number of days

    public boolean lengthIsShorterThan(Trainee trainee1, int number_of_days){
        return trainee1.get_length() < number_of_days;
    }

    public boolean lengthIsLongerThan(Trainee trainee1, int number_of_days){
        return trainee1.get_length() > number_of_days;
    }

}