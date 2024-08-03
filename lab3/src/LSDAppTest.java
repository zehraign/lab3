import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LSDAppTest {

    private LSDApp app;
    private Course math;
    private Course physics;
    private Student zehra;
    private Student atu;
    private Student duaa;
    private Student lara;

    @BeforeEach
    void setUp() {
        app = new LSDApp();

        // Add some courses
        math = new Course("Math", 2);
        physics = new Course("Physics", 2);
        app.addCourse(math);
        app.addCourse(physics);

        // Add some students
        zehra = new Student("1", "Zehra");
        atu = new Student("2", "Atu");
        duaa = new Student("3", "Duaa");
        lara = new Student("4", "Lara");
        app.addStudent(zehra);
        app.addStudent(atu);
        app.addStudent(duaa);
        app.addStudent(lara);
    }



    @Test
    void testRegisterStudentForCourse() {
        app.registerStudentForCourse(zehra, math);
        assertEquals(1, math.getEnrolledStudents().size());
        assertTrue(math.getEnrolledStudents().contains(zehra));
    }

    @Test
    void testFinalizeRegistrations() {
        app.registerStudentForCourse(zehra, math);
        app.registerStudentForCourse(atu, math);
        app.registerStudentForCourse(duaa, math);
        app.registerStudentForCourse(lara, math);

        app.finalizeRegistrations();

        assertEquals(2, math.getEnrolledStudents().size());
        assertEquals(2, math.getWaitingList().size());
    }

    @Test
    void testAddGrade() {
        app.registerStudentForCourse(zehra, math);
        app.finalizeRegistrations();
        app.addGrade(zehra, math, 95);

        assertEquals(95, zehra.getGrades().get(math).intValue());
    }

    @Test
    void testPrintGradeReport() {
        app.registerStudentForCourse(zehra, math);
        app.finalizeRegistrations();
        app.addGrade(zehra, math, 95);

        // Capture the printed output
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        app.printGradeReport(zehra);

        System.setOut(originalOut);
        String output = outputStream.toString();
        assertTrue(output.contains("Grade Report for Zehra"));
        assertTrue(output.contains("Course: Math - Grade: 95"));
    }

    @Test
    void testPrintCourseRegistrations() {
        app.registerStudentForCourse(zehra, math);
        app.registerStudentForCourse(atu, math);
        app.registerStudentForCourse(duaa, math);
        app.registerStudentForCourse(lara, math);

        app.finalizeRegistrations();

        // Capture the printed output
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        app.printCourseRegistrations();

        System.setOut(originalOut);
        String output = outputStream.toString();
        assertTrue(output.contains("Course: Math"));
        assertTrue(output.contains("Enrolled Students:"));
        assertTrue(output.contains("Waiting List:"));
    }
}




