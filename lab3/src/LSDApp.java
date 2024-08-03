import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LSDApp {
    private List<Course> courses = new ArrayList<>();
    private List<Student> students = new ArrayList<>();

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void registerStudentForCourse(Student student, Course course) {
        if (course.registerStudent(student)) {
            System.out.println(student.getName() + " registered for " + course.getName());
        } else {
            System.out.println(student.getName() + " added to waiting list for " + course.getName());
        }
    }

    public void finalizeRegistrations() {
        for (Course course : courses) {
            course.assignSpotsByLottery();
        }
    }

    public void printCourseRegistrations() {
        for (Course course : courses) {
            System.out.println("Course: " + course.getName());
            System.out.println("Enrolled Students:");
            for (Student student : course.getEnrolledStudents()) {
                System.out.println(" - " + student.getName());
            }
            System.out.println("Waiting List:");
            for (Student student : course.getWaitingList()) {
                System.out.println(" - " + student.getName());
            }
        }
    }

    public void addGrade(Student student, Course course, int grade) {
        student.addGrade(course, grade);
    }

    public void printGradeReport(Student student) {
        System.out.println("Grade Report for " + student.getName());
        for (Map.Entry<Course, Integer> entry : student.getGrades().entrySet()) {
            System.out.println("Course: " + entry.getKey().getName() + " - Grade: " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        LSDApp app = new LSDApp();

        // Add some courses
        Course math = new Course("Math", 2);
        Course physics = new Course("Physics", 2);
        app.addCourse(math);
        app.addCourse(physics);

        // Add some students
        Student zehra = new Student("1", "Zehra");
        Student atu = new Student("2", "Atu");
        Student duaa = new Student("3", "Duaa");
        Student lara = new Student("3", "Lara");
        app.addStudent(zehra);
        app.addStudent(atu);
        app.addStudent(duaa);
        app.addStudent(lara);

        // Register students for courses
        app.registerStudentForCourse(zehra, math);
        app.registerStudentForCourse(atu, math);
        app.registerStudentForCourse(duaa, math);
        app.registerStudentForCourse(lara, math);

        app.registerStudentForCourse(zehra, physics);
        app.registerStudentForCourse(atu, physics);
        app.registerStudentForCourse(duaa, physics);
        app.registerStudentForCourse(lara, physics);

        // Finalize registrations with lottery
        app.finalizeRegistrations();

        // Print course registrations
        app.printCourseRegistrations();

        // Add grades
        app.addGrade(zehra, math, 90);
        app.addGrade(atu, math, 85);
        app.addGrade(duaa, physics, 95);
        app.addGrade(lara, physics, 88);

        // Print grade report
        app.printGradeReport(zehra);
        app.printGradeReport(atu);
        app.printGradeReport(duaa);
        app.printGradeReport(lara);
    }

}


