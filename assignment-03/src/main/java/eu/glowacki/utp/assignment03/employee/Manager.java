package eu.glowacki.utp.assignment03.employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Manager extends Worker {


    private List<Employee> _subordinates;

    public Manager(String _firstName, String _surname, LocalDate _date, BigDecimal _salary, Manager _manager, LocalDate _employmentDate, BigDecimal _bonus, boolean bonusExistence) {
        super(_firstName, _surname, _date, _salary, _manager, _employmentDate, _bonus, bonusExistence);
        this._subordinates = _subordinates;
    }

    // (assignment 02)
	// attributes:
	// * subordinates (a list of immediate subordinates)
	// * all subordinates (a list of subordinates in all hierarchy)


    public Manager(String _firstName, String _surname, LocalDate _date, BigDecimal _salary, Manager _manager,
                   LocalDate _employmentDate, BigDecimal _bonus, List<Employee> _subordinates, boolean _bonusExistence) {
        super(_firstName, _surname, _date, _salary, _manager, _employmentDate, _bonus, _bonusExistence);
        this._subordinates = _subordinates;
    }

    public List<Employee> getAllSubordinates() {
        List<Employee> toReturn = new ArrayList<>();
        for (Employee e : _subordinates) {
            if (e instanceof Manager) {
                toReturn.addAll(((Manager) e).getAllSubordinates());
            }
            toReturn.add(e);
        }
        return toReturn;
    }

    public List<Employee> get_subordinates() {
        return _subordinates;
    }
}