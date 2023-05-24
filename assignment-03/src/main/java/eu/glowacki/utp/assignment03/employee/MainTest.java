package eu.glowacki.utp.assignment03.employee;
    import com.sun.tools.javac.Main;

    import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import eu.glowacki.utp.assignment03.HumanResourceStatistics;

public class MainTest  {

        Manager director;
        Manager manager1;
        Manager manager2;
        Worker worker1;
        Worker worker2;
        Worker worker3;
        Worker worker4;
        Worker worker5;
        Trainee trainee1;
        Trainee trainee2;

        public static void main(String[] args) {
            new MainTest();
        }

        public MainTest(){

            trainee1 = new Trainee("Ana",
                    "Nas",
                    LocalDate.of(1998, 4, 15),
                    BigDecimal.valueOf(1000),
                    manager1,
                    LocalDate.of(2019, 12, 20)
            );

            trainee2 = new Trainee("Pomi",
                    "Dor",
                    LocalDate.of(2004, 8, 5),
                    BigDecimal.valueOf(666),
                    manager1,
                    LocalDate.of(2022, 10, 17));

            worker1 = new Worker("Yab",
                    "Loko",
                    LocalDate.of(1999, 9, 2),
                    BigDecimal.valueOf(2100),
                    director,
                    LocalDate.of(2019, 3, 21),
                    null, false
            );

            worker2 = new Worker("Ban",
                    "An",
                    LocalDate.of(1995, 12, 21),
                    BigDecimal.valueOf(2900),
                    director,
                    LocalDate.of(2021, 3, 21),
                    BigDecimal.valueOf(100), true
            );

            worker3 = new Worker("Kart",
                    "Oshka",
                    LocalDate.of(1991, 8, 24),
                    BigDecimal.valueOf(3100),
                    manager2,
                    LocalDate.of(2020, 10, 18),
                    BigDecimal.valueOf(2000), true
            );

            worker4 = new Worker("Mor",
                    "Kov",
                    LocalDate.of(2000, 6, 2),
                    BigDecimal.valueOf(1800),
                    manager1,
                    LocalDate.of(2019, 12, 1),
                    BigDecimal.valueOf(200), true);
            worker5 = new Worker("Bur",
                    "Yak",
                    LocalDate.of(1982, 6, 5),
                    BigDecimal.valueOf(850),
                    manager1,
                    LocalDate.of(2022, 10, 17),
                    BigDecimal.valueOf(0), false);

            manager1 = new Manager("Per",
                    "Chik",
                    LocalDate.of(1999, 2, 23),
                    BigDecimal.valueOf(1500),
                    director,
                    LocalDate.of(2021, 3, 22),
                    BigDecimal.valueOf(200),
                    List.of(worker3, worker4, trainee2),
                    true
            );

            manager2 = new Manager("Ogu",
                    "Rec",
                    LocalDate.of(1980, 12, 5),
                    BigDecimal.valueOf(1234),
                    director,
                    LocalDate.of(2020, 12, 21),
                    BigDecimal.valueOf(22),
                    List.of(worker5),
                    true
            );

            director = new Manager("Yag",
                    "Odka",
                    LocalDate.of(1990, 5, 21),
                    BigDecimal.valueOf(5000),
                    null,
                    LocalDate.of(2020, 4, 21),
                    BigDecimal.valueOf(0),
                    List.of(manager1, manager2, worker1, worker2, trainee1),
                    false);

            List<Employee> _allEmployees = List.of(director, manager1, manager2, worker1, worker2,worker3, worker4, worker5, trainee1, trainee2);





            System.out.println(HumanResourceStatistics.olderThanAndEarnMore(_allEmployees, worker1).toString() + "\n" + " --- olderThanAndEarnMore\n");
            System.out.println(HumanResourceStatistics.practiceLengthLongerThan(_allEmployees, 100).toString() + "\n" + " ----- practiceLengthLongerThan\n");
            System.out.println(HumanResourceStatistics.seniorityLongerThan(_allEmployees, 10).toString() + "\n" + " -------- seniorityLongerThan\n");
            System.out.println(HumanResourceStatistics.seniorityBetweenOneAndThreeYears(_allEmployees).toString() + "\n" + " ------- seniorityBetweenOneAndThreeYears\n");
            System.out.println(HumanResourceStatistics.seniorityLongerThan(_allEmployees, worker1).toString() + "\n" + " ------- seniorityLongerThan\n");
            System.out.println(HumanResourceStatistics.seniorityBetweenTwoAndFourYearsAndAgeGreaterThan(_allEmployees, 10).toString() + "\n" + " ------ seniorityBetweenTwoAndFourYearsAndAgeGreaterThan\n");
        }
    }




