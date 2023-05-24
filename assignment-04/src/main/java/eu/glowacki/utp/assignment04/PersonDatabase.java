package eu.glowacki.utp.assignment04;

import eu.glowacki.utp.assignment04.comparators.BirthdateComparator;
import eu.glowacki.utp.assignment04.comparators.FirstNameComparator;

import java.io.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class PersonDatabase {

    private final List<Person> _people;
    private final static String pattern = "([A-Z][a-z]+[ ]*)+[0-9]{4}-[0-9]{2}-[0-9]{2}";
    private final static String datePattern = "yyyy-MM-dd";
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);

    public static void main(String[] args) throws IOException, ParseException {
        List<Person> personList = new ArrayList<>();
        personList.addAll(InputParser.parse2());
        PersonDatabase personDatabase = new PersonDatabase(personList);
//        System.out.println(personDatabase.sortedByBirthdate());
//        System.out.println(personDatabase.sortedByFirstName());
//        System.out.println(personDatabase.sortedBySurnameFirstNameAndBirthdate());
        Date date = new Date(1328482800000L);
//        System.out.println(date);
//        System.out.println(personDatabase.bornOnDay(date));
//        personDatabase.writeData(personDatabase.inputUserData());
        System.out.println(personList);

    }

    public PersonDatabase(List<Person> people) { //
        _people = people;
    }

    public List<Person> sortedByFirstName() { //
        _people.sort(new FirstNameComparator());
        return _people; // external rule for ordering (based on Comparator --- FirstNameComparator)
    }

    public List<Person> sortedBySurnameFirstNameAndBirthdate() { //
        _people.sort(Person::compareTo);
        return _people; // natural order (Comparable)
    }

    public List<Person> sortedByBirthdate() { //
        _people.sort(new BirthdateComparator());
        return _people; // external rule for ordering (based on Comparator --- BirthdateComparator)
    }

    public List<Person> bornOnDay(Date date) { //
        return _people.stream()
                .filter(person -> date.getTime() == person.get_birthdate().getTime())
                .collect(Collectors.toList());
    }


    //Assignment 08

    public Person inputUserData() throws ParseException {
        String[] personData = null;
        Person person = null;
        while (true) {
            System.out.println("Enter name, surname and date of birth: ");
            Scanner inp = new Scanner(System.in);
            String tempBuf = inp.nextLine();
            if (tempBuf.matches(pattern)) {
                personData = tempBuf.split(" ");
                person = new Person(personData[0], personData[1], dateFormat.parse(personData[2]));
                break;
            }
        }
        return person;
    }

    public void writeData(Person person) throws IOException {
        String filePath = "C:\\Study\\skeletons\\assignment-04\\src\\main\\java\\eu\\glowacki\\utp\\assignment04\\comparators\\binarytest";
        String fullInfo = person.get_firstName().concat(" " + person.get_surname()).concat(" " + getYearCorrectFormat(person.get_birthdate())) + "\n";
        char[] messChar = fullInfo.toCharArray();
        String result = "";

        for (int i = 0; i < messChar.length; i++) {
            result += Integer.toBinaryString(messChar[i]) + " ";
        }
        FileOutputStream outputStream = new FileOutputStream(filePath, true);
        DataOutputStream dataOutputStr = new DataOutputStream(outputStream);
        dataOutputStr.writeBytes(result);
    }

    public static String getYearCorrectFormat(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getYear() + "-" + getCorrectDayMonth(localDate.getMonth().getValue()) + "-" + getCorrectDayMonth(localDate.getDayOfMonth());
    }

    public static String getCorrectDayMonth(int number) {
        String numberString = "" + number;
        int numLength = numberString.toCharArray().length;
        return numLength == 1 ? "0" + numberString : numberString;
    }

}