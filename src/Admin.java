import java.util.ArrayList;
import java.util.List;

public class Admin
{
    private String id;
    private String name;

    // constructor
    public Admin(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // getter&setter
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

    //select matched staff(it's matched only when the courses of staffs are exactly as same as teachingRequirements
    public ArrayList<Staff> selectMatchedStaff(ArrayList<Staff> staffs, ArrayList<Course> teachingRequirements) {
        ArrayList<Staff> matchedStaffs = new ArrayList<>();
        for (Staff staff : staffs) {
            boolean isMatched = true;
            for (Course course : teachingRequirements) {
                if (!staff.getCourses().contains(course)) {
                    isMatched = false;
                    break;
                }
            }
            if (isMatched) {
                matchedStaffs.add(staff);
            }
        }
        return matchedStaffs;
    }

    // select matched training
    public String selectMatchedTraining(List<String> trainings)
    {
        // assume choosing the first training
        return trainings.get(0);
    }

    @Override
    public String toString() {
        return "Admin:" +
                "id="+ id+ ";" +
                "name="+name;
    }
}
