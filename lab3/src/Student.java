import java.util.HashMap;
import java.util.Map;

public class Student {
    private String id;
    private String name;
    private Map<Course, Integer> grades = new HashMap<>();

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addGrade(Course course, int grade) {
        grades.put(course, grade);
    }

    public Map<Course, Integer> getGrades() {
        return grades;
    }
}


