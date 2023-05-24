package eu.glowacki.utp.assignment04;

import java.util.Comparator;
import java.util.Date;

public final class Person implements Comparable<Person> {

    private final String _firstName;
    private final String _surname;
    private final Date _birthdate;

    public Person(String firstName, String surname, Date birthdate) {
        _firstName = firstName;
        _surname = surname;
        _birthdate = birthdate;
    }

    @Override
    public int compareTo(Person otherPerson) {
        return Comparator
                .comparing(Person::get_surname)
                .thenComparing(Person::get_firstName)
                .thenComparing(Person::get_birthdate)
                .compare(this, otherPerson);
    }

    public String get_firstName() {
        return _firstName;
    }

    public String get_surname() {
        return _surname;
    }

    public Date get_birthdate() {
        return _birthdate;
    }

    @Override
    public String toString() {
        return this.get_firstName() + "\n " + this.get_surname() + "\n " + this.get_birthdate() + "\n\n";
    }
}