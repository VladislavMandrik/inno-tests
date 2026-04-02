package org.example;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.Arrays;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    public void N() {
        Map<String, PersonInfo> mapNames = Map.of(
                "Ира", new PersonInfo("ж", 18),
                "Онер", new PersonInfo("M", 24),
                "Иван", new PersonInfo("M", 19),
                "Маша", new PersonInfo("ж", 16),
                "Андрей", new PersonInfo("M", 27),
                "Никита", new PersonInfo("M", 31),
                "Соня", new PersonInfo("ж", 25),
                "Катя", new PersonInfo("ж", 18),
                "Руслан", new PersonInfo("M", 19),
                "Вика", new PersonInfo("ж", 21)
        );

        System.out.println(mapNames.values().stream().filter(v -> v.getGender().equals("ж")).count());
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }
}

class PersonInfo {
    private String gender;
    private int age;

    public PersonInfo(String gender, int age) {
        this.gender = gender;
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "(" + gender + ", " + age + ")";
    }
}
