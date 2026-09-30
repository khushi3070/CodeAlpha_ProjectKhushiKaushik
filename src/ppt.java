import java.util.ArrayList;

class Student {
    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

public class ppt{
    public static void main(String[] args) {

        ArrayList<Student> list = new ArrayList<>();

        // CREATE
        list.add(new Student(1, "Ananya"));
        list.add(new Student(2, "Rahul"));

        // READ
        System.out.println("Student List:");
        for (Student s : list) {
            System.out.println(s.id + " " + s.name);
        }

        // UPDATE
        for (Student s : list) {
            if (s.id == 1) {
                s.name = "Ananya Mishra";
            }
        }

        // DELETE
        list.removeIf(s -> s.id == 2);

        // Final List
        System.out.println("\nAfter Update & Delete:");
        for (Student s : list) {
            System.out.println(s.id + " " + s.name);
        }
    }
}




