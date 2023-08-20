import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        Course math=new Course("1","math");
        Course english=new Course("2","english");
        Course computer=new Course("3","computer");
        File file = new File();

        ArrayList<Course> arraylist = new ArrayList<Course>();
        arraylist.add(math);
        arraylist.add(english);
        System.out.println(arraylist);
        String finalTxt = file.StringOutSpace(arraylist.toString());
        System.out.println(arraylist.toString());
        System.out.println(finalTxt);
    }
    
}
