package academic.model;

import java.util.Objects;

/**
 * 
 * @author 12S22023 Chika Situmorang
 *  @author 12S22004 Bethania Hasibuan
 */

public class Enrollment {
    private String student;
    private String course;
    private String academicYear;
    private String grade; 
    private String semester;

   
    public Enrollment(String s, String c, String aY, String sem) {
        this.student = s;
        this.course = c;
        this.academicYear = aY; 
        this.semester = sem;
        this.grade = "None";
    }


     public void setStudent(String student) {
        this.student = student;
    }   

    public void setCourse(String course) {
        this.course = course;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStudent() {
        return student;
    }

    public String getCourse() {
        return course;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public String getStudentId() {
        return student;
    }

    public String getSemester() {
        return semester;
    }

    public String getGrade() {
        return grade;
    }
   

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment enrollment = (Enrollment) o;
        return Objects.equals(student, enrollment.student) &&
               Objects.equals(course, enrollment.course) &&
               Objects.equals(academicYear, enrollment.academicYear) &&
               Objects.equals(semester, enrollment.semester) &&
               Objects.equals(grade, enrollment.grade);
    }

    @Override
    public String toString() {
        return student + "|" + course + "|" + academicYear + "|" + semester + "|" + grade;
    }
}



