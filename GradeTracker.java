import java.io.*;
import java.util.*;

public class GradeTracker {
    static String[] subjects;

    public static List<Student> loadStudents(String filename) {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean header = true;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                if (header) {
                    subjects = Arrays.copyOfRange(parts, 1, parts.length);
                    header = false;
                    continue;
                }

                String name = parts[0];
                List<Integer> grades = new ArrayList<>();
                for (int i = 1; i < parts.length; i++) {
                    grades.add(Integer.parseInt(parts[i].trim()));
                }
                students.add(new Student(name, grades));
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return students;
    }

    public static void saveAllStudents(String filename, List<Student> students) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            pw.print("Name");
            for (String subject : subjects) {
                pw.print("," + subject);
            }
            for (Student s : students) {
                pw.print("\n" + s.toFileFormat());
            }
            System.out.println("File saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    public static void saveStudent(String filename, Student student) {
        try (FileWriter fw = new FileWriter(filename, true)) {
            fw.write("\n" + student.toFileFormat());
        } catch (IOException e) {
            System.out.println("Error saving student: " + e.getMessage());
        }
    }
}
