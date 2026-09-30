import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StudentGradeServer {

    // ==============================
    // STUDENT CLASS
    // ==============================
    static class Student implements Serializable {

        String name;
        ArrayList<Double> grades;

        Student(String name, ArrayList<Double> grades) {
            this.name = name;
            this.grades = grades;
        }

        double getAverage() {

            if (grades.isEmpty()) {
                return 0;
            }

            double total = 0;

            for (double grade : grades) {
                total += grade;
            }

            return total / grades.size();
        }

        double getHighest() {

            if (grades.isEmpty()) {
                return 0;
            }

            double highest = grades.get(0);

            for (double grade : grades) {

                if (grade > highest) {
                    highest = grade;
                }
            }

            return highest;
        }

        double getLowest() {

            if (grades.isEmpty()) {
                return 0;
            }

            double lowest = grades.get(0);

            for (double grade : grades) {

                if (grade < lowest) {
                    lowest = grade;
                }
            }

            return lowest;
        }
    }


    // ==============================
    // DATA
    // ==============================

    static ArrayList<Student> students =
            new ArrayList<>();

    static final String FILE_NAME =
            "student_grades.dat";


    // ==============================
    // MAIN METHOD
    // ==============================

    public static void main(String[] args)
            throws IOException {

        loadStudents();

        HttpServer server =
                HttpServer.create(
                        new InetSocketAddress(8080),
                        0
                );

        server.createContext(
                "/",
                StudentGradeServer::home
        );

        server.createContext(
                "/add",
                StudentGradeServer::addStudent
        );

        server.createContext(
                "/students",
                StudentGradeServer::viewStudents
        );

        server.createContext(
                "/clear",
                StudentGradeServer::clearStudents
        );

        server.start();

        System.out.println(
                "===================================="
        );

        System.out.println(
                "     STUDENT GRADE TRACKER"
        );

        System.out.println(
                "===================================="
        );

        System.out.println(
                "Server started successfully!"
        );

        System.out.println(
                "Open: http://localhost:8080"
        );
    }


    // ==============================
    // HOME PAGE
    // ==============================

    static void home(HttpExchange exchange)
            throws IOException {

        String content = """

                <div class="hero">

                    <h1>🎓 Student Grade Tracker</h1>

                    <p>
                    Manage student grades and calculate
                    academic performance easily.
                    </p>

                </div>


                <div class="grid">

                    <div class="card">

                        <h2>➕ Add Student</h2>

                        <p>
                        Enter student details and grades.
                        </p>

                        <a href="/add" class="button">
                        Add Student
                        </a>

                    </div>


                    <div class="card">

                        <h2>📊 Student Summary</h2>

                        <p>
                        View average, highest and lowest
                        grades.
                        </p>

                        <a href="/students" class="button">
                        View Students
                        </a>

                    </div>


                    <div class="card">

                        <h2>🗑️ Clear Data</h2>

                        <p>
                        Remove all saved student records.
                        </p>

                        <a href="/clear"
                           class="button danger">
                        Clear All
                        </a>

                    </div>

                </div>

                """;

        send(
                exchange,
                page(
                        "Student Grade Tracker",
                        content
                )
        );
    }


    // ==============================
    // ADD STUDENT PAGE
    // ==============================

    static void addStudent(HttpExchange exchange)
            throws IOException {

        // --------------------------
        // FORM SUBMISSION
        // --------------------------

        if (exchange.getRequestMethod()
                .equalsIgnoreCase("POST")) {

            String body =
                    new String(
                            exchange.getRequestBody()
                                    .readAllBytes(),
                            StandardCharsets.UTF_8
                    );

            Map<String, String> data =
                    parseData(body);

            String name =
                    data.get("name");

            double grade1 =
                    Double.parseDouble(
                            data.get("grade1")
                    );

            double grade2 =
                    Double.parseDouble(
                            data.get("grade2")
                    );

            double grade3 =
                    Double.parseDouble(
                            data.get("grade3")
                    );

            // Validate grades

            if (grade1 < 0 || grade1 > 100 ||
                    grade2 < 0 || grade2 > 100 ||
                    grade3 < 0 || grade3 > 100) {

                send(
                        exchange,
                        page(
                                "Invalid Grade",
                                """
                                <div class="error">

                                <h1>❌ Invalid Grade</h1>

                                <p>
                                Grades must be between
                                0 and 100.
                                </p>

                                <a href="/add"
                                   class="button">
                                Try Again
                                </a>

                                </div>
                                """
                        )
                );

                return;
            }


            // Store grades in ArrayList

            ArrayList<Double> grades =
                    new ArrayList<>();

            grades.add(grade1);
            grades.add(grade2);
            grades.add(grade3);


            // Create student object

            Student student =
                    new Student(
                            name,
                            grades
                    );


            // Add to ArrayList

            students.add(student);


            // Save data

            saveStudents();


            // Show result

            String content = """

                    <div class="success">

                        <h1>✅ Student Added Successfully!</h1>

                        <h2>%s</h2>

                        <p>
                        Average:
                        <b>%.2f</b>
                        </p>

                        <p>
                        Highest Grade:
                        <b>%.2f</b>
                        </p>

                        <p>
                        Lowest Grade:
                        <b>%.2f</b>
                        </p>

                    </div>


                    <a href="/add"
                       class="button">
                    Add Another Student
                    </a>

                    <a href="/students"
                       class="button">
                    View All Students
                    </a>

                    <br><br>

                    <a href="/">
                    ← Back to Home
                    </a>

                    """.formatted(
                    student.name,
                    student.getAverage(),
                    student.getHighest(),
                    student.getLowest()
            );


            send(
                    exchange,
                    page(
                            "Student Added",
                            content
                    )
            );

            return;
        }


        // --------------------------
        // ADD STUDENT FORM
        // --------------------------

        String content = """

                <h1>➕ Add Student</h1>

                <form method="POST"
                      action="/add">

                    <label>
                    Student Name
                    </label>

                    <input
                        type="text"
                        name="name"
                        placeholder="Enter student name"
                        required
                    >


                    <label>
                    Grade 1
                    </label>

                    <input
                        type="number"
                        name="grade1"
                        min="0"
                        max="100"
                        step="0.01"
                        placeholder="0 - 100"
                        required
                    >


                    <label>
                    Grade 2
                    </label>

                    <input
                        type="number"
                        name="grade2"
                        min="0"
                        max="100"
                        step="0.01"
                        placeholder="0 - 100"
                        required
                    >


                    <label>
                    Grade 3
                    </label>

                    <input
                        type="number"
                        name="grade3"
                        min="0"
                        max="100"
                        step="0.01"
                        placeholder="0 - 100"
                        required
                    >


                    <button
                        type="submit"
                        class="button">
                    Add Student
                    </button>

                </form>


                <br>

                <a href="/">
                ← Back to Home
                </a>

                """;


        send(
                exchange,
                page(
                        "Add Student",
                        content
                )
        );
    }


    // ==============================
    // VIEW ALL STUDENTS
    // ==============================

    static void viewStudents(
            HttpExchange exchange)
            throws IOException {

        StringBuilder content =
                new StringBuilder();


        content.append(
                "<h1>📊 Student Grade Summary</h1>"
        );


        if (students.isEmpty()) {

            content.append("""
                    
                    <div class="card">

                        <h2>
                        No students found.
                        </h2>

                        <p>
                        Add a student to see
                        the summary.
                        </p>

                    </div>
                    
                    """);

        } else {

            // Summary table

            content.append("""
                    
                    <table>

                    <tr>

                        <th>Student</th>
                        <th>Grade 1</th>
                        <th>Grade 2</th>
                        <th>Grade 3</th>
                        <th>Average</th>
                        <th>Highest</th>
                        <th>Lowest</th>

                    </tr>
                    
                    """);


            for (Student student :
                    students) {

                content.append(
                        """

                        <tr>

                            <td><b>%s</b></td>

                            <td>%.2f</td>

                            <td>%.2f</td>

                            <td>%.2f</td>

                            <td>
                            <b>%.2f</b>
                            </td>

                            <td>
                            %.2f
                            </td>

                            <td>
                            %.2f
                            </td>

                        </tr>

                        """.formatted(
                                student.name,
                                student.grades.get(0),
                                student.grades.get(1),
                                student.grades.get(2),
                                student.getAverage(),
                                student.getHighest(),
                                student.getLowest()
                        )
                );
            }


            content.append("</table>");


            // Overall statistics

            double totalAverage = 0;

            double overallHighest = 0;

            double overallLowest = 100;


            for (Student student :
                    students) {

                totalAverage +=
                        student.getAverage();

                if (student.getHighest()
                        > overallHighest) {

                    overallHighest =
                            student.getHighest();
                }

                if (student.getLowest()
                        < overallLowest) {

                    overallLowest =
                            student.getLowest();
                }
            }


            double classAverage =
                    totalAverage /
                            students.size();


            content.append(
                    """

                    <div class="statistics">

                        <h2>
                        📈 Overall Statistics
                        </h2>

                        <p>
                        Total Students:
                        <b>%d</b>
                        </p>

                        <p>
                        Class Average:
                        <b>%.2f</b>
                        </p>

                        <p>
                        Highest Score:
                        <b>%.2f</b>
                        </p>

                        <p>
                        Lowest Score:
                        <b>%.2f</b>
                        </p>

                    </div>

                    """.formatted(
                            students.size(),
                            classAverage,
                            overallHighest,
                            overallLowest
                    )
            );
        }


        content.append(
                """

                <br>

                <a href="/add"
                   class="button">
                Add Student
                </a>

                <a href="/"
                   class="button">
                Home
                </a>

                """
        );


        send(
                exchange,
                page(
                        "Student Summary",
                        content.toString()
                )
        );
    }


    // ==============================
    // CLEAR ALL STUDENTS
    // ==============================

    static void clearStudents(
            HttpExchange exchange)
            throws IOException {

        students.clear();

        saveStudents();


        String content = """

                <div class="success">

                    <h1>
                    ✅ All Student Data Cleared
                    </h1>

                    <p>
                    All student records have
                    been removed.
                    </p>

                </div>

                <a href="/"
                   class="button">
                Back to Home
                </a>

                """;


        send(
                exchange,
                page(
                        "Data Cleared",
                        content
                )
        );
    }


    // ==============================
    // SAVE STUDENTS
    // ==============================

    static void saveStudents() {

        try {

            ObjectOutputStream output =
                    new ObjectOutputStream(
                            new FileOutputStream(
                                    FILE_NAME
                            )
                    );

            output.writeObject(students);

            output.close();

        } catch (IOException e) {

            System.out.println(
                    "Error saving student data: "
                            + e.getMessage()
            );
        }
    }


    // ==============================
    // LOAD STUDENTS
    // ==============================

    @SuppressWarnings("unchecked")
    static void loadStudents() {

        File file =
                new File(FILE_NAME);


        if (!file.exists()) {
            return;
        }


        try {

            ObjectInputStream input =
                    new ObjectInputStream(
                            new FileInputStream(
                                    FILE_NAME
                            )
                    );

            students =
                    (ArrayList<Student>)
                            input.readObject();

            input.close();

        } catch (Exception e) {

            System.out.println(
                    "Could not load previous data."
            );
        }
    }


    // ==============================
    // PARSE FORM DATA
    // ==============================

    static Map<String, String> parseData(
            String data) {

        Map<String, String> map =
                new HashMap<>();


        for (String pair :
                data.split("&")) {

            String[] parts =
                    pair.split("=", 2);


            if (parts.length == 2) {

                map.put(
                        URLDecoder.decode(
                                parts[0],
                                StandardCharsets.UTF_8
                        ),

                        URLDecoder.decode(
                                parts[1],
                                StandardCharsets.UTF_8
                        )
                );
            }
        }


        return map;
    }


    // ==============================
    // HTML PAGE
    // ==============================

    static String page(
            String title,
            String content) {

        return """

                <!DOCTYPE html>

                <html>

                <head>

                    <meta charset="UTF-8">

                    <title>%s</title>


                    <style>

                        * {
                            box-sizing: border-box;
                        }


                        body {

                            margin: 0;

                            font-family:
                            Arial, sans-serif;

                            background:
                            #f2f4f7;

                            color: #333;
                        }


                        header {

                            background:
                            #26384a;

                            color: white;

                            padding: 25px;

                            text-align: center;
                        }


                        .container {

                            width: 92%%;

                            max-width: 1000px;

                            margin:
                            40px auto;

                            text-align: center;
                        }


                        .hero {

                            background:
                            #26384a;

                            color: white;

                            padding: 40px;

                            border-radius: 12px;

                            margin-bottom: 30px;
                        }


                        .hero h1 {

                            font-size: 36px;
                        }


                        .grid {

                            display: grid;

                            grid-template-columns:
                            repeat(
                            auto-fit,
                            minmax(250px, 1fr)
                            );

                            gap: 20px;
                        }


                        .card {

                            background: white;

                            padding: 30px;

                            border-radius: 12px;

                            box-shadow:
                            0 4px 12px
                            rgba(0,0,0,0.1);
                        }


                        form {

                            background: white;

                            padding: 30px;

                            border-radius: 12px;

                            max-width: 550px;

                            margin: auto;

                            box-shadow:
                            0 4px 12px
                            rgba(0,0,0,0.1);
                        }


                        label {

                            display: block;

                            text-align: left;

                            margin-top: 15px;

                            font-weight: bold;
                        }


                        input {

                            width: 100%%;

                            padding: 12px;

                            margin-top: 7px;

                            border:
                            1px solid #ccc;

                            border-radius: 6px;

                            font-size: 16px;
                        }


                        .button {

                            display: inline-block;

                            background: #3498db;

                            color: white;

                            text-decoration: none;

                            border: none;

                            padding: 12px 22px;

                            border-radius: 7px;

                            cursor: pointer;

                            font-size: 16px;

                            margin: 10px 5px;
                        }


                        .button:hover {

                            background:
                            #2980b9;
                        }


                        .danger {

                            background: #e74c3c;
                        }


                        .success {

                            background: white;

                            padding: 30px;

                            border-radius: 12px;

                            box-shadow:
                            0 4px 12px
                            rgba(0,0,0,0.1);

                            margin-bottom: 25px;
                        }


                        .error {

                            background: white;

                            padding: 30px;

                            border-radius: 12px;

                            border-top:
                            5px solid #e74c3c;
                        }


                        table {

                            width: 100%%;

                            border-collapse:
                            collapse;

                            background: white;

                            box-shadow:
                            0 4px 12px
                            rgba(0,0,0,0.1);
                        }


                        th {

                            background:
                            #26384a;

                            color: white;

                            padding: 15px;
                        }


                        td {

                            padding: 14px;

                            border-bottom:
                            1px solid #ddd;
                        }


                        tr:hover {

                            background:
                            #f5f5f5;
                        }


                        .statistics {

                            background: white;

                            padding: 25px;

                            margin-top: 25px;

                            border-radius: 12px;

                            box-shadow:
                            0 4px 12px
                            rgba(0,0,0,0.1);
                        }

                    </style>

                </head>


                <body>

                    <header>

                        <h1>
                        🎓 Student Grade Tracker
                        </h1>

                    </header>


                    <div class="container">

                        %s

                    </div>

                </body>

                </html>

                """.formatted(
                title,
                content
        );
    }


    // ==============================
    // SEND RESPONSE
    // ==============================

    static void send(
            HttpExchange exchange,
            String response)
            throws IOException {

        byte[] bytes =
                response.getBytes(
                        StandardCharsets.UTF_8
                );


        exchange.getResponseHeaders()
                .set(
                        "Content-Type",
                        "text/html; charset=UTF-8"
                );


        exchange.sendResponseHeaders(
                200,
                bytes.length
        );


        OutputStream output =
                exchange.getResponseBody();


        output.write(bytes);

        output.close();
    }
}