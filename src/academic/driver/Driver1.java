package academic.driver;

/**
 * 
 * @author 12S22023 Chika Situmorang
 * @author 12S22004 Bethania Hasibuan
 */

 import academic.model.Course;
 import academic.model.CourseOpening;
 import academic.model.Student;
 import academic.model.Enrollment;
 import academic.model.Lecturer;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Scanner;
 import java.util.Comparator;
 import java.util.HashMap;
 import java.util.Map;
import java.util.Collections;

 
 public class Driver1 {
     static List<CourseOpening> courseOpeningsList = new ArrayList<>();
     private static double calculateGPA(String studentId, List<Enrollment> enrollments, List<Course> courses) {
         int totalCredits = calculateTotalCredits(studentId, enrollments, courses);
         String[] courseStrings = new String[enrollments.size()];
         String[] grade = new String[enrollments.size()];
         int x = 0;
         for (Enrollment enrollment : enrollments) {
             if (enrollment.getCourse().equals(studentId)) {
                 courseStrings[x] = enrollment.getStudent();
                 grade[x] = enrollment.getGrade();
                 x++;
             }
         }
         for (int i = 0; i < x; i++) {
            for (int j = i + 1; j < x; j++) {
                if (courseStrings[i] != null && courseStrings[j] != null && courseStrings[i].equals(courseStrings[j])) {
                    grade[i] = grade[j];
                    courseStrings[i] = courseStrings[j];
                    courseStrings[j] = null;
                    grade[j] = null;
                    x--;
                }
            }
        }
        double GPA = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < courses.size(); j++) {
                if (courseStrings[i] != null && courseStrings[i].equals(courses.get(j).getId())) {
                    String currentGrade = grade[i];
                    if (currentGrade != null && currentGrade.contains("(")) {
                        currentGrade = currentGrade.substring(0, currentGrade.indexOf('('));
                    }
                    GPA += Student.convertGradePoint(currentGrade) * courses.get(j).getCredits();
                }
            }
        }
         GPA = GPA / totalCredits;
         if (GPA == 0.0) {
             totalCredits = 0;
         }
         return GPA;
     }
     private static int calculateTotalCredits(String studentId, List<Enrollment> enrollments, List<Course> courses) {
         int totalCredits = 0;
         String[] course = new String[enrollments.size()];
         int index = 0;
 
         for (Enrollment enrollment : enrollments) {
             if (enrollment.getCourse().equals(studentId)) {
                 course[index] = enrollment.getStudent();
                 index++;
             }
         }
         for (int i = 0; i < index; i++) {
            for (int j = i + 1; j < index; j++) {
                if (course[i] != null && course[j] != null && course[i].equals(course[j])) {
                    course[i] = course[j];
                    course[j] = null;
                    index--;
                }
            }
        }
         for (int i = 0; i < index; i++) {
             for (Course crs : courses) {
                 if (crs.getId().equals(course[i])) {
                     totalCredits += crs.getCredits();
                 }
             }
         }
         return totalCredits;
        }
        private static Course foundCourse(String courseId, List<Course> courses) {
            for (Course course : courses) {
                if (course.getId().equals(courseId)) {
                    return course;
                }
            }
            return null; 
        }
    private static Lecturer foundLecturer(String lecturerInitial, List<Lecturer> lecturers) {
        for (Lecturer lecturer : lecturers) {
            if (lecturer.getInitial().equals(lecturerInitial)) {
                return lecturer;
            }
        }
        return null; 
    }
     public static void main(String[] args) {
         Scanner inputScanner = new Scanner(System.in);
         List<Course> courses = new ArrayList<>();
         List<Student> students = new ArrayList<>();
         List<Enrollment> enrollments = new ArrayList<>();
         List<Lecturer> lecturers = new ArrayList<>();

         while (inputScanner.hasNext()) {
             String input = inputScanner.nextLine();
             if (input.equals("---")) {
                 break;
             }
             String[] inputParts = input.split("#");
             if (inputParts[0].equals("student-add")) {
                 boolean studentExists = false;
                 for (Student existingStudent : students) {
                     if (existingStudent.getId().equals(inputParts[1]) || existingStudent.getName().equals(inputParts[2])) {
                         studentExists = true;
                         break;
                     }
                 }
                 if (!studentExists) {
                     Student newStudent = new Student(inputParts[1], inputParts[2], Integer.parseInt(inputParts[3]), inputParts[4]);
                     students.add(newStudent);
                 }
             } 
             else if (inputParts[0].equals("lecturer-add")) {
                 Lecturer newLecturer = new Lecturer(inputParts[1], inputParts[2], inputParts[3], inputParts[4], inputParts[5]);
                 if (!lecturers.contains(newLecturer)) {
                     lecturers.add(newLecturer);
                 }
                }
                 else if (inputParts[0].equals("course-add")) {
                    Course newCourse = new Course(inputParts[1], inputParts[2], Integer.parseInt(inputParts[3]), inputParts[4]);
                    if (!courses.contains(newCourse)) {
                        courses.add(newCourse);
                    }


                } 
                else if (inputParts[0].equals("course-open")) {
                    String courseCode = inputParts[1];
                    String academicYear = inputParts[2];
                    String semester = inputParts[3];
                    String lecturerList = inputParts[4];
    
                    String[] lecturerInfos = lecturerList.split(",");
    
                    Course foundCourse = null;
                    // Mencari course berdasarkan kode
                    for (Course course : courses) {
                        if (course.getId().equals(courseCode)) {
                            foundCourse = course;
                            break;
                        }
                    }
    
                    List<Lecturer> foundLecturers = new ArrayList<>();
                    for (String lecturerInitial : lecturerInfos) {
                        Lecturer lecturer = foundLecturer(lecturerInitial, lecturers);
                        if (lecturer != null) {
                            foundLecturers.add(lecturer);
                        }
                    }
    
                    if (foundCourse != null) {
                        CourseOpening open = new CourseOpening(courseCode, academicYear, semester);
                        for (Lecturer lecturer : foundLecturers) {
                            open.addLecturer(lecturer);
                        }
                        
                        for (Enrollment enrollment : enrollments) {
                            if (enrollment.getCourse().equals(courseCode) && enrollment.getAcademicYear().equals(academicYear) && enrollment.getSemester().equals(semester)) {
                                open.addEnrollment(enrollment);
                            }
                        }
                        courseOpeningsList.add(open);
                    }
                    
                 } 
                 else if (inputParts[0].equals("course-history")) {
                    String courseCode = inputParts[1];
    
                    courseOpeningsList.sort(new Comparator<CourseOpening>() {
                        @Override
                        public int compare(CourseOpening o1, CourseOpening o2) {
                            int semesterComparison = o2.getSemester().compareTo(o1.getSemester());
                            if (semesterComparison != 0) {
                                return semesterComparison;
                            }
                            if (o1.getAcademicYear() == null && o2.getAcademicYear() == null) {
                                return 0;
                            } else if (o1.getAcademicYear() == null) {
                                return -1;
                            } else if (o2.getAcademicYear() == null) {
                                return 1;
                            }
                            return o1.getAcademicYear().compareTo(o2.getAcademicYear());
                        }
                    });
                    for (CourseOpening courseOpening : courseOpeningsList) {
                        if (courseOpening.getId().equals(courseCode)) {
                            Course course = foundCourse(courseOpening.getId(), courses);
                            List<String> lecturerList = courseOpening.getLecturerList();
                            for (String lecturerInfo : lecturerList) {
                               //System.out.print(lecturerInfo);
                               System.out.println(course.getId() + "|" + course.getName() + "|" + course.getCredits() + "|" + course.getGrade() + "|" + courseOpening.getAcademicYear() + "|" + courseOpening.getSemester() + "|" +lecturerInfo);
                            }
                           
                           // System.out.println();
                
                           for (Enrollment enrollment : enrollments) {
                            //System.out.println(enrollment);
                                if (enrollment.getStudent().equals(courseCode)&&enrollment.getAcademicYear().equals(courseOpening.getAcademicYear()) && enrollment.getSemester().equals(courseOpening.getSemester())) {
                                  System.out.println(enrollment);
                                }
                            }
                            
                        }
                    }}else if (inputParts[0].equals("enrollment-add")) {
                    boolean enrollmentExists = false;
                    for (Enrollment existingEnrollment : enrollments) {
                        if (existingEnrollment.getStudent().equals(inputParts[1]) && existingEnrollment.getCourse().equals(inputParts[2])
                                && existingEnrollment.getAcademicYear().equals(inputParts[3]) && existingEnrollment.getSemester().equals(inputParts[4])) {
                            enrollmentExists = true;
                            break;
                        }
                    }
                    if (!enrollmentExists) {
                        Enrollment newEnrollment = new Enrollment(inputParts[1], inputParts[2], inputParts[3], inputParts[4]);
                        enrollments.add(newEnrollment);   
                    }
                }
            else if (inputParts[0].equals("enrollment-grade")) {
                 for (Enrollment enrollment : enrollments) {
                     if (enrollment.getStudent().equals(inputParts[1]) && enrollment.getCourse().equals(inputParts[2])
                             && enrollment.getAcademicYear().equals(inputParts[3]) && enrollment.getSemester().equals(inputParts[4])) {
                         enrollment.setGrade(inputParts[5]);
                         break;
                     }
                 }
             } else if (inputParts[0].equals("enrollment-remedial")) {
                 String studentId = inputParts[1];
                 String courseId = inputParts[2];
                 String academicYear = inputParts[3];
                 String semester = inputParts[4];
                 String grade = inputParts[5];

                 boolean remedialDone = false;
                 for (Enrollment enrollment : enrollments) {
                     if (enrollment.getStudent().equals(studentId) &&
                             enrollment.getCourse().equals(courseId) &&
                             enrollment.getAcademicYear().equals(academicYear) &&
                             enrollment.getSemester().equals(semester)) {
 
                         if (enrollment.getGrade().contains("(")) {
                             remedialDone = true;
                             break;
                         }
                     }
                 }
                 if (!remedialDone) {
                     for (Enrollment enrollment : enrollments) {
                         if (enrollment.getStudent().equals(studentId) &&
                                 enrollment.getCourse().equals(courseId) &&
                                 enrollment.getAcademicYear().equals(academicYear) &&
                                 enrollment.getSemester().equals(semester)) {
                             String previousGrade = enrollment.getGrade();
                             enrollment.setGrade(grade + "(" + previousGrade + ")");
                             break;
                         }
                     }
                 }
             } else if (inputParts[0].equals("student-details")) {
                 for (Student student : students) {
                     if (student.getId().equals(inputParts[1])) {
                         System.out.println(student.getId() + "|" + student.getName() + "|" + student.getYear() + "|" +
                                 student.getStudyProgram() + "|" + String.format("%.2f", calculateGPA(inputParts[1], enrollments, courses)) + "|" +
                                 calculateTotalCredits(inputParts[1], enrollments, courses));
                         break;
                     }
                 }
                }else if (inputParts[0].equals("student-transcript")) {
                    String studentId = inputParts[1];
                
                    class TranscriptProcessor {
                        void processTranscript(Student student) {
                            double cumulativeGPA = calculateGPA(studentId, enrollments, courses);
                            int totalCredits = calculateTotalCredits(studentId, enrollments, courses);
                
                            // Mencetak informasi mahasiswa
                            System.out.println(studentId + "|" + student.getName() + "|" + student.getYear() + "|" +
                                    student.getStudyProgram() + "|" + String.format("%.2f", cumulativeGPA) + "|" + totalCredits);
                
                            //Membuat map untuk menyimpan pendaftaran terbaru untuk setiap mata kuliah
                            Map<String, Enrollment> latestEnrollments = new HashMap<>();
                            for (Enrollment enrollment : enrollments) {
                                if (enrollment.getCourse().equals(studentId)) {
                                    String courseCode = enrollment.getStudent();
                                    // Jika mata kuliah sudah ada di map, bandingkan dengan pendaftaran saat ini
                                    if (latestEnrollments.containsKey(courseCode)) {
                                        Enrollment currentEnrollment = latestEnrollments.get(courseCode);
                                        if (enrollment.getAcademicYear().compareTo(currentEnrollment.getAcademicYear()) > 0 ||
                                            (enrollment.getAcademicYear().equals(currentEnrollment.getAcademicYear()) &&
                                             enrollment.getSemester().compareTo(currentEnrollment.getSemester()) > 0)) {
                                            latestEnrollments.put(courseCode, enrollment);
                                        }
                                    } else {
                                        // Jika mata kuliah belum ada di map, tambahkan langsung
                                        latestEnrollments.put(courseCode, enrollment);
                                    }
                                }
                            }
                            // Sort the enrollments based on academic year and semester
                            List<Enrollment> sortedEnrollments = new ArrayList<>(latestEnrollments.values());
                            Collections.sort(sortedEnrollments, Comparator.comparing(Enrollment::getAcademicYear).thenComparing(Enrollment::getSemester));
                            for (Enrollment enrollment : sortedEnrollments) {
                                Course course = foundCourse(enrollment.getStudent(), courses);
                                if (course != null) {
                                    String grade = enrollment.getGrade();
                                    if (grade != null && !grade.equals("None")) {
                                        if (grade.contains("(")) {
                                        }
                                        System.out.println(enrollment.toString());
                                    }
                                }
                            }
                        }
                    }
                
                    // Mencari mahasiswa dengan ID yang sesuai
                    for (Student student : students) {
                        if (student.getId().equals(studentId)) {
                            new TranscriptProcessor().processTranscript(student);
                        }
                    }
                }          
            }

        
 
         for (Lecturer lecturer : lecturers) {
             System.out.println(lecturer.toString());
         }
         for (Course course : courses) {
             System.out.println(course.toString());
         }
         for (Student newStudent : students) {
             System.out.println(newStudent.toString());
         }
         for (Enrollment newEnrollment : enrollments) {
             System.out.println(newEnrollment.toString());
         }
         

         
         inputScanner.close();
     }
 }
 