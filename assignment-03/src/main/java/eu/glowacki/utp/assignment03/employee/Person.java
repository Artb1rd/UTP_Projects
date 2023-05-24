package eu.glowacki.utp.assignment03.employee;

import java.time.LocalDate;

public  class Person {

	// (assignment 02)
	// attributes:
	// * first name
	// * surname
	// * birth date
	// * age (derived --- computed based on birth date)

    private final String _firstName; // backing field
    private final String _surname;
    private final LocalDate _dateOfBirth;

    public Person(String _firstName, String _surname, LocalDate _dateOfBirth) {
        this._firstName = _firstName;
        this._surname = _surname;
        this._dateOfBirth = _dateOfBirth;
    }

    public String get_firstName() {
        return _firstName;
    }

    public String get_surname() {
        return _surname;
    }

    public short getAge() {
        return (short) (LocalDate.now().getYear() - _dateOfBirth.getYear());
    }

    @Override
    public String toString() {
        return _firstName + " " + _surname;
    }
	
	// (assignment 03)
	// methods:
	// * is older than other person
	// * is younger than other person
	// * compare age with other person's age

//    public Person isOlderThan(Person person1, Person person2){
//        if(person1 == null || person2 == null) return null;
//        if(person1.getAge() > person2.getAge())
//            return person1;
//        else
//            return person2;
//    }
//
//    public Person isYoungerThan(Person person1, Person person2){
//        if(person1 == null || person2 == null) return null;
//        if(person1.getAge() < person2.getAge())
//            return person1;
//            else
//                return person2;
//    }

    public boolean isOlderThan(Person person1, Person person2){
        return person1.getAge()>person2.getAge();
    }

    public boolean isYoungerThan(Person person1, Person person2){
        return person1.getAge()<person2.getAge();
    }

    public boolean isGreaterThanNumber(Person person1, int numberOfYears){
        return person1.getAge() > numberOfYears;
    }

    public void compareAge(Person person1, Person person2){
        if(person1 == null || person2 == null)  System.out.println("Empty Person");
        if(person1.getAge() > person2.getAge()){
            int difference = person1.getAge()- person2.getAge();
             System.out.println(person1._firstName+ " is older than "+ person2._firstName+ " for "+ difference+ " years");
        }
        else if(person1.getAge() < person2.getAge()){
            int difference = person2.getAge()- person1.getAge();
            System.out.println(person1._firstName+ " is younger than "+ person2._firstName+ " for "+ difference+ " years");
        }else System.out.println(person1._firstName+ " and "+ person2._firstName+ " are the same age");
    }



}