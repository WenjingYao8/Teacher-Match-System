import java.util.ArrayList;
import java.util.List;

public class Staff
{
    private String id;
    private String name;
    private String spareTime;
    private ArrayList<Course> courses;

    public Staff(String id, String name, String spareTime, ArrayList<Course> courses) {
        this.id = id;
        this.name = name;
        this.spareTime = spareTime;
        this.courses = courses;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpareTime() {
        return spareTime;
    }

    public void setSpareTime(String spareTime) {
        this.spareTime = spareTime;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course>  courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Staff:" +
                "id="+id + ";" +
                "name="+name + ";" +
                "trainingTime="+ spareTime+ ";" +
                "teachingSkills="+File.StringOutSpace(courses.toString())+"\n";
    }
}
