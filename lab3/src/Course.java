import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Course {
    private String name;
    private int maxSpots;
    private List<Student> enrolledStudents = new ArrayList<>();
    private List<Student> waitingList = new ArrayList<>();

    public Course(String name, int maxSpots) {
        this.name = name;
        this.maxSpots = maxSpots;
    }

    public String getName() {
        return name;
    }

    public int getMaxSpots() {
        return maxSpots;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public List<Student> getWaitingList() {
        return waitingList;
    }

    public boolean registerStudent(Student student) {
        if (enrolledStudents.size() < maxSpots) {
            enrolledStudents.add(student);
            return true;
        } else {
            waitingList.add(student);
            return false;
        }
    }

    public void assignSpotsByLottery() {
        if (waitingList.isEmpty()) return;

        Collections.shuffle(waitingList);
        while (enrolledStudents.size() < maxSpots && !waitingList.isEmpty()) {
            enrolledStudents.add(waitingList.remove(0));
        }
    }
}


