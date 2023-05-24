package eu.glowacki.utp.assignment04.comparators;

import eu.glowacki.utp.assignment04.Person;

import java.util.Comparator;

public class SurnameComparator implements Comparator<Person> {

    @Override
    public int compare(Person p1, Person p2) {
        return p1.get_surname().compareTo(p2.get_surname());
    }
}
