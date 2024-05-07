package academic.model;

/**
 * 
 * @author 12S22023 Chika Situmorang
 *  @author 12S22004 Bethania Hasibuan
 */

public class Student extends Person{
    private String year;
    private String studyProgram;
    private String semester;
    
   
     public Student(String id, String name, int year, String studyProgram ) {
        super(id, name);
        this.year = String.valueOf(year); 
        this.studyProgram = studyProgram;
        this.semester = semester;
    
    }

   public void setId(String id) {
        this.id = id;
    }
   
    public void getSemester(String semester) {
        this.semester = semester;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setStudyProgram(String studyProgram) {
        this.studyProgram = studyProgram;
    }
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    
    public String getStudyProgram() {
        return studyProgram;
    }

    public String getYear() {
        return year;
    }

    public static double convertGradePoint(String grade) {
        switch (grade) {
            case "A":
                return 4.0;
            case "AB":
                return 3.5;
            case "B":
                return 3.0;
            case "BC":
                return 2.5;
            case "C":
                return 2.0;
            case "D":
                return 1.0;
            case "E":
                return 0.0;
            default:
                return 0.0;
        }
    }


    @Override
    public String toString() {
        return id + "|" + name + "|" + year + "|" + studyProgram;
    }
}





