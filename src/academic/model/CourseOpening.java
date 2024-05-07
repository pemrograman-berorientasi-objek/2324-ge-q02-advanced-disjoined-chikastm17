package academic.model;

/**
 * 
 * @author 12S22023 Chika Situmorang
 *  @author 12S22004 Bethania Hasibuan
 */

import java.util.ArrayList;
import java.util.List;


public class CourseOpening {
    private String id;
    private String academicYear; 
    private String semester;
    private List<Lecturer> lecturers = new ArrayList<>();
    private String grade;
    private String name;
    private int credits;
    private String email;
    public String courseCode;
    private List<Enrollment> enrollmentList;

    public CourseOpening(String id, String academicYear, String semester) {
        this.id = id;
        this.academicYear = academicYear;
        this.semester = semester;
        this.lecturers = new ArrayList<>();
        this.email = "";
        this.enrollmentList = new ArrayList<>();
    }

    public void setId(String id) {
        this.id = id;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public String getSemester() {
        return semester;
    }

    public String getGrade() {
        return grade;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public String getEmail() {
        return email;
    }

    public String getEnrollmentList() {
        StringBuilder enrollmentInfo = new StringBuilder();
        for (Enrollment enrollment : enrollmentList) {
            enrollmentInfo.append(enrollment.toString()).append("\n");
        }
        return enrollmentInfo.toString();
    }


    public void addLecturer(Lecturer lecturer) {
        lecturers.add(lecturer);
    }
    public List<String> getLecturerList() {
        List<String> lecturerInfoList = new ArrayList<>();
        for (Lecturer lecturer : lecturers) {
            lecturerInfoList.add(lecturer.getInitial() + " (" + lecturer.getEmail() + ")");
        }
        return lecturerInfoList;
    }

   


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(id).append("|");
        stringBuilder.append(name).append("|");
        stringBuilder.append(credits).append("|");
        stringBuilder.append(grade).append("|");
        stringBuilder.append(academicYear).append("|");
        stringBuilder.append(semester).append("|");
        return stringBuilder.toString();
    }

    public void addEnrollment(Enrollment enrollment) {
        throw new UnsupportedOperationException("");
    }

}
