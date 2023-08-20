package by.teachmeskills.students;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StudentTests {
    private static Student student1;
    private static Student student2;
    private static Student student3;

    private static List<Student> actual;
    private static List<Student> actualBySex;

    @BeforeAll
    public static void setUp() {
        student1 = new Student("Ivan", 17, Sex.MALE);
        student2 = new Student("Anna", 19, Sex.FEMALE);
        student3 = new Student("Polina", 18, Sex.FEMALE);

        actual = new ArrayList<>();
        actual.add(student1);
        actual.add(student2);
        actual.add(student3);

        actualBySex = new ArrayList<>();
        actualBySex.add(student1);
    }

    @Test
    public void checkAllStudentsReturned() {
        List<Student> expected = Student.getAllStudents();
        assertEquals(expected, actual);
    }

    @Test
    public void checkAllStudentsReturned_NotNull() {
        List<Student> expected = Student.getAllStudents();
        assertNotNull(expected);
    }

    @Test
    public void checkAllStudents_MALE() {
        List<Student> expected = Student.getAllStudentsBySex(Sex.MALE);
        assertEquals(expected, actualBySex);
    }

    @Test
    public void checkStudentsCountWithSex() {
        int expected = Student.getStudentsCountWithSex(Sex.MALE);
        assertEquals(expected, actualBySex.size());
    }

    @Test
    public void checkStudentsCount() {
        int expected = Student.getStudentsCount();
        assertEquals(expected, actual.size());
    }

    @Test
    public void checkSumOfAllStudentsAge() {
        int expected = Student.getSumOfAllStudentsAge();
        int actualSumAge = actual.stream().map(Student::getAge).reduce(0, Integer::sum);
        assertEquals(expected, actualSumAge);
    }

    @Test
    public void checkSumOfAllStudentsAgeBySex() {
        int expected = Student.getSumOfAllStudentsAgeBySex(Sex.MALE);
        int actualSumAge = actualBySex.stream().map(Student::getAge).reduce(0, Integer::sum);
        assertEquals(expected, actualSumAge);
    }

    @Test
    public void checkAverageAgeOfAllStudents() {
        int expected = Student.getAverageAgeOfAllStudents();
        int actualAverageAge = actual.stream().map(Student::getAge).reduce(0, Integer::sum) / actual.size();
        assertEquals(expected, actualAverageAge);
    }

    @Test
    public void checkAverageAgeOfAllStudentsBySex() {
        int expected = Student.getAverageOfAllStudentsBySex(Sex.MALE);
        int actualAverageAge = actualBySex.stream().map(Student::getAge)
                .reduce(0, Integer::sum) / actualBySex.size();
        assertEquals(expected, actualAverageAge);
    }
}
