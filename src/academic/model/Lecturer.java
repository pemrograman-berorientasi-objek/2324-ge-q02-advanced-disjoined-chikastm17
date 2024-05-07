package academic.model;

import java.util.List;

/**
 *
 * @author 12S22023 Chika Situmorang
 *  @author 12S22004 Bethania Hasibuan
 */

import java.util.Objects;

public class Lecturer extends Person{
    private String initial;
    private String email;
    private String studyProgram;

    public Lecturer(String id, String name, String initial, String email, String studyProgram) {
       super(id, name);
        this.initial = initial;
        this.email = email;
        this.studyProgram = studyProgram;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInitial() {
        return initial;
    }

    public String getEmail() {
        return email;
    }

    public String getStudyProgram() {
        return studyProgram;
    }

    public void setId(String id) {
        this.id = id;
    } 
    // metode set email
    public void setEmail(String email) {
        this.email = email;
    }

    public static String formatLecturers(List<Lecturer> lecturers) {
        StringBuilder sb = new StringBuilder();
        for (Lecturer lecturer : lecturers) {
            if (sb.length() > 0) {
                sb.append(";");
            }
            sb.append(lecturer.getInitial()).append(" (").append(lecturer.getEmail()).append(")");
        }
        return sb.toString();
    }
    


@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecturer lecturer = (Lecturer) o;
        return Objects.equals(id, lecturer.id) &&
               Objects.equals(name, lecturer.name) &&
               Objects.equals(initial, lecturer.initial) &&
               Objects.equals(email, lecturer.email) &&
               Objects.equals(studyProgram, lecturer.studyProgram);
    }

        @Override
        public String toString() {
            return id + "|" + name + "|" + initial + "|" + email + "|" + studyProgram;
        }

    
}




