package by.clevertec;

import by.clevertec.model.Animal;
import by.clevertec.model.Flower;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class MainTest {
    private static List<Animal> animals;

    @BeforeAll
    public static void init() {
        animals = List.of(
                new Animal(1, "Cat", 2, "Japanese", "Male"),
                new Animal(2, "Dog", 4, "Japanese", "Female"),
                new Animal(3, "Tiger", 5, "Japanese", "Female"),
                new Animal(4, "Mouth", 20, "Hungarian", "Male"),
                new Animal(5, "Lion", 10, "Japanese", "Female"),
                new Animal(6, "Lemur", 20, "Japanese", "Male"),
                new Animal(7, "Iguana", 34, "Afrikaans", "Female"),
                new Animal(8, "Bear", 31, "Afrikaans", "Female"),
                new Animal(9, "Antelope", 3, "Indonesian", "It"),
                new Animal(10, "Horse", 32, "Azeri", "Female"));
    }

    @Test
    void task2Test() {
        List<String> expected = List.of("Cat", "DOG", "TIGER", "LION", "Lemur");

        List<String> result = Main.task2(animals);

        assertEquals(expected, result);
    }

    @Test
    void task3Test() {
        List<String> expected = List.of("Afrikaans", "Azeri");

        List<String> result = Main.task3(animals);

        assertEquals(expected, result);
    }

    @Test
    void task4Test() {
        long expected = 6;

        long result = Main.task4(animals);

        assertEquals(expected, result);
    }

    @Test
    void task5Test() {
        boolean result = Main.task5(animals);

        assertTrue(result);
    }

    @Test
    void task6Test() {
        boolean result = Main.task6(animals);

        assertFalse(result);
    }

    @Test
    void task7Test() {
        boolean result = Main.task7(animals);

        assertTrue(result);
    }

    @Test
    void task8Test() {
        int expected = 34;

        int result = Main.task8(animals);

        assertEquals(expected, result);
    }

    @Test
    void task10Test() {
        int result = Main.task10(animals);

        assertEquals(161, result);
    }

    @Test
    void task11Test() {
        double expected = 3.0;

        double result = Main.task11(animals);

        assertEquals(expected, result);
    }

    @Test
    void task15Test() {
        List<String> flowerVaseMaterial = List.of("Glass", "Aluminum", "Steel");
        Flower flower1 = new Flower(1, "Ssss", "A", 0.01,
                true, "Aaaa", 10, flowerVaseMaterial);
        Flower flower2 = new Flower(2, "Www", "A", 0.02,
                true, "Baaa", 11, flowerVaseMaterial);
        Flower flower3 = new Flower(3, "Tttt", "A", 0.03,
                true, "Caaa", 12, flowerVaseMaterial);
        Flower flower4 = new Flower(4, "Aaaa", "A", 0.04,
                true, "Daaa", 13, flowerVaseMaterial);
        Flower flower5 = new Flower(5, "Ddd", "A", 0.05,
                false, "Aaaa", 14, flowerVaseMaterial);
        List<Flower> flowers = List.of(flower1, flower2, flower3, flower4, flower5);

        double result = Main.task15(flowers);

        assertEquals(299.67499999999995, result);
    }

    @Test
    void task17Test() {
        List<String> expected = List.of("P-1", "C-2", "M-3", "C-4", "M-1",
                "C-3", "M-2", "P-3", "P-4", "C-1", "P-2");

        List<String> result = Main.task17();

        assertEquals(expected, result);
    }
}
