import java.util.ArrayList;
import java.util.List;

public class ClassDirector
{
    private String id;
    private String name;
    private ArrayList<Course> teachingRequirements;

    public ClassDirector(String id, String name, ArrayList<Course> teachingRequirements) {
        this.id = id;
        this.name = name;
        this.teachingRequirements = teachingRequirements;
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

    public ArrayList<Course> getTeachingRequirements() {
        return teachingRequirements;
    }

    public void setTeachingRequirements(ArrayList<Course> teachingRequirements) {
        this.teachingRequirements = teachingRequirements;
    }

    //match teaching requirements and export them to target file
    public void matchTeachingRequirements() {
        //assume we are exporting matched result to target file
        String result = "Teaching Requirements:\n";
        for (Course course : teachingRequirements) {
            result += course.getId() + ": " + course.getName() + "\n";
        }
        System.out.println(result);
    }

    @Override
    public String toString() {
        return "Class Director:" +
                "id="+ id+ ";" +
                "name="+name;
    }
}
