package academic.model;



/**
 * 
 * @author 12S22023 Chika Situmorang
 * @author 12S22004 Bethania Hasibuan
 */

 public class Course  {
    private String id;
    private String name;
    private int credits;
    private String grade;
    private String AcademicYear;
    private String semester;
    


    public Course(String id, String name, int credits, String grade) {
        
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.grade = grade;
       
    }
    
   

    public String getGrade(){
        return grade;
    }

    // metode set academic year dan semester
    public void setAcademicYear(String AcademicYear) {
        this.AcademicYear = AcademicYear;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }   


    
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
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

    public String getAcademicYear() {
        return AcademicYear;
    }

    public String getSemester() {
        return semester;
    }



    
     
        
    
    @Override
    public String toString() {
        return id + "|" + name + "|" + credits + "|" + grade;
    }



    public String getStudyProgram() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStudyProgram'");
    }



    public String getYear() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getYear'");
    }
}
 



