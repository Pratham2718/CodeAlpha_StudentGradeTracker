import java.util.*;

public class Student {
    String name;
    List<Integer> grades;

    public Student(String name, List<Integer> grades) {
        this.name = name;
        this.grades = grades;
    }

    public double getAverage() {
        return grades.stream().mapToInt(i -> i).average().orElse(0);
    }

    public int getHighest() {
        return grades.isEmpty() ? 0 : Collections.max(grades);
    }

    public int getLowest() {
        return grades.isEmpty() ? 0 : Collections.min(grades);
    }

    public Object[] toRow() {
        List<Object> row = new ArrayList<>(grades);
        row.add(0, name);
        return row.toArray();
    }

    public String toFileFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        for (int grade : grades) {
            sb.append(",").append(grade);
        }
        return sb.toString();
    }
}
