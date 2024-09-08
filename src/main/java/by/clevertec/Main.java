package by.clevertec;

import by.clevertec.model.*;
import by.clevertec.util.Util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<Animal> animals = Util.getAnimals();
        List<Flower> flowers = Util.getFlowers();

//        task1();
//        System.out.println(task2(animals));
//        System.out.println(task3(animals));
//        System.out.println(task4(animals));
//        System.out.println(task5(animals));
//        System.out.println(task6(animals));
//        System.out.println(task7(animals));
//        System.out.println(task8(animals));
//        task9();
//        System.out.println(task10(animals));
//        System.out.println(task11(animals));
//        task12();
//        task13();
//        task14();
//        System.out.println(task15(flowers));
//        task16();
//        System.out.println(task17());
//        task18();
//        task19();
//        task20();
//        task21();
//        task22();
    }

    public static void task1() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getAge() >= 10 && animal.getAge() < 20)
                .sorted(Comparator.comparingInt(Animal::getAge))
                .skip(14)
                .limit(7)
                .forEach(System.out::println);
    }

    public static List<String> task2(List<Animal> animals) {
        return animals.stream()
                .filter(animal -> "Japanese" .equals(animal.getOrigin()))
                .peek(animal -> {
                    if ("Female" .equals(animal.getGender()))
                        animal.setBread(animal.getBread().toUpperCase());
                })
                .map(Animal::getBread)
                .collect(Collectors.toList());
    }

    public static List<String> task3(List<Animal> animals) {
        return animals.stream()
                .filter(animal -> animal.getAge() > 30)
                .map(Animal::getOrigin)
                .distinct()
                .filter(s -> s.startsWith("A"))
                .collect(Collectors.toList());
    }

    public static long task4(List<Animal> animals) {
        return animals.stream()
                .filter(animal -> "Female" .equals(animal.getGender()))
                .count();
    }

    public static boolean task5(List<Animal> animals) {
        return animals.stream()
                .filter(animal -> animal.getAge() >= 20 && animal.getAge() <= 30)
                .anyMatch(animal -> "Hungarian" .equals(animal.getOrigin()));
    }

    public static boolean task6(List<Animal> animals) {
        return animals.stream()
                .allMatch(animal ->
                        "Male" .equals(animal.getGender()) || "Female" .equals(animal.getGender()));
    }

    public static boolean task7(List<Animal> animals) {
        return animals.stream()
                .noneMatch(animal -> "Oceania" .equals(animal.getOrigin()));
    }

    public static int task8(List<Animal> animals) {
        return animals.stream()
                .sorted((animal, t1) -> animal.getBread().compareToIgnoreCase(t1.getBread()))
                .limit(100)
                .max(Comparator.comparingInt(Animal::getAge))
                .map(Animal::getAge)
                .orElseThrow();
    }

    public static void task9() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .map(Animal::getBread)
                .map(String::toCharArray)
                .min(Comparator.comparingInt(chars -> chars.length))
                .ifPresent(chars -> System.out.println(chars.length));
    }

    public static int task10(List<Animal> animals) {
        return animals.stream()
                .mapToInt(Animal::getAge)
                .sum();
    }

    public static double task11(List<Animal> animals) {
        return animals.stream()
                .filter(animal -> "Indonesian" .equals(animal.getOrigin()))
                .mapToInt(Animal::getAge)
                .average()
                .orElseThrow();
    }

    public static void task12() {
        List<Person> persons = Util.getPersons();
        persons.stream()
                .filter(person -> "Male" .equals(person.getGender()))
                .filter(person -> {
                    int age = Period.between(person.getDateOfBirth(),
                            LocalDate.now()).getYears();
                    return age >= 18 && age <= 27;
                })
                .sorted(Comparator.comparingInt(Person::getRecruitmentGroup))
                .limit(200)
                .forEach(System.out::println);
    }

    public static void task13() {
        List<House> houses = Util.getHouses();
        houses.stream()
                .flatMap(house -> house.getPersonList().stream()
                        .map(person -> Map.entry(((Supplier<Integer>) () -> {
                            if ("Hospital" .equals(house.getBuildingType())) {
                                return 1;
                            }
                            int age = Period.between(person.getDateOfBirth(),
                                    LocalDate.now()).getYears();
                            if (age < 18 || age > 60) {
                                return 2;
                            }
                            return 3;
                        }).get(), person)))
                .sorted(Map.Entry.comparingByKey())
                .limit(500)
                .map(Map.Entry::getValue)
                .forEach(System.out::println);
    }

    public static void task14() {
        Map<Integer, Predicate<Car>> predicates = createPredicates();
        double oneTonPriceDollar = 7.14;
        List<Car> cars = Util.getCars();
        System.out.println(cars.stream()
                .map(car -> Map.entry(
                        predicates.entrySet().stream()
                                .filter(entry -> entry.getValue().test(car))
                                .findFirst()
                                .orElse(new AbstractMap.SimpleEntry<>(7, null))
                                .getKey(), car))
                .filter((entry) -> entry.getKey() != 7)
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.summingDouble(entry ->
                                oneTonPriceDollar * (entry.getValue().getMass()))))
                .entrySet().stream()
                .peek(System.out::println)
                .mapToDouble(Map.Entry::getValue)
                .sum());
    }

    public static double task15(List<Flower> flowers) {
        List<String> flowerVaseMaterial = List.of("Glass", "Aluminum", "Steel");
        int daysInFiveYears = 1825;
        double costOneCubicMeterWater = 1.39;

        return flowers.stream()
                .filter(flower -> flower.getCommonName().charAt(0) >= 'S'
                        || flower.getCommonName().charAt(0) < 'C')
                .filter(flower -> flower.isShadePreferred()
                        && new HashSet<>(flower.getFlowerVaseMaterial()).containsAll(flowerVaseMaterial))
                .sorted(Comparator.comparing(Flower::getOrigin).reversed()
                        .thenComparingInt(Flower::getPrice)
                        .thenComparingDouble(Flower::getWaterConsumptionPerDay).reversed())
                .map(flower -> {
                    double priceFotWater = (flower.getWaterConsumptionPerDay() * daysInFiveYears)
                            * costOneCubicMeterWater;
                    return priceFotWater + flower.getPrice();
                })
                .reduce(Double::sum)
                .orElseThrow();
    }

    public static void task16() {
        List<Student> students = Util.getStudents();
        students.stream()
                .filter(student -> student.getAge() < 18)
                .sorted((student, t1) -> student.getSurname().compareToIgnoreCase(t1.getSurname()))
                .map(student -> student.getSurname() + " ; " + student.getAge())
                .forEach(System.out::println);
    }

    public static List<String> task17() {
        List<Student> students = Util.getStudents();
        return students.stream()
                .map(Student::getGroup)
                .distinct()
                .collect(Collectors.toList());
    }

    public static void task18() {
        List<Student> students = Util.getStudents();
        students.stream()
                .collect(Collectors.groupingBy(Student::getFaculty,
                        Collectors.averagingInt(Student::getAge)))
                .entrySet().stream()
                .sorted((stringDoubleEntry, t1) -> (int) (stringDoubleEntry.getValue() - t1.getValue()))
                .forEach(entry -> {
                    System.out.printf("%s: %.2f%n", entry.getKey(), entry.getValue());
                });
    }

    public static void task19() {
        String group = "P-1";
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();
        students.stream()
                .filter(student -> group.equals(student.getGroup()))
                .forEach(student -> {
                    if (examinations.stream()
                            .filter(examination -> examination.getExam3() > 4)
                            .map(Examination::getStudentId)
                            .collect(Collectors.toSet())
                            .contains(student.getId()))
                        System.out.println(student);
                });
    }

    public static void task20() {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();
        students.stream()
                .collect(Collectors.groupingBy(Student::getFaculty,
                        Collectors.averagingInt(
                                student -> examinations.stream()
                                        .filter(examination -> examination.getStudentId() == student.getId())
                                        .map(Examination::getExam1)
                                        .findFirst()
                                        .orElseThrow()
                        )))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(System.out::println);
    }

    public static void task21() {
        List<Student> students = Util.getStudents();
        System.out.println(students.stream()
                .collect(Collectors.groupingBy(Student::getGroup,
                        Collectors.counting())));
    }

    public static void task22() {
        List<Student> students = Util.getStudents();
        System.out.println(students.stream()
                .collect(Collectors.toMap(
                        Student::getFaculty,
                        Student::getAge,
                        Math::min)));
    }

    private static Map<Integer, Predicate<Car>> createPredicates() {
        Map<Integer, Predicate<Car>> predicates = new HashMap<>();

        predicates.put(1, car -> "Jaguar" .equals(car.getCarMake()) ||
                "White" .equals(car.getColor()));
        predicates.put(2, car -> car.getMass() < 1500 &&
                ("BMW" .equals(car.getCarMake()) ||
                        "Lexus" .equals(car.getCarMake()) ||
                        "Chrysler" .equals(car.getCarMake()) ||
                        "Toyota" .equals(car.getCarMake())));
        predicates.put(3, car -> ("Black" .equals(car.getColor()) && car.getMass() > 4000) ||
                "GMC" .equals(car.getCarMake()) ||
                "Dodge" .equals(car.getCarMake()));
        predicates.put(4, car -> car.getReleaseYear() < 1982 ||
                "Civic" .equals(car.getCarModel()) ||
                "Cherokee" .equals(car.getCarModel()));
        predicates.put(5, car -> car.getPrice() > 40000 ||
                !("Yellow" .equals(car.getColor()) ||
                        "Red" .equals(car.getColor()) ||
                        "Green" .equals(car.getColor()) ||
                        "Blue" .equals(car.getColor())));
        predicates.put(6, car -> car.getVin().contains("59"));
        return predicates;
    }
}
