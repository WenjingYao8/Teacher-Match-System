import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SplittableRandom;
import java.util.concurrent.CountDownLatch;

public class File
{
    private ArrayList<Staff> staffs;
    private ArrayList<PTT> ptts;
    private ArrayList<String> trainings;

    public File() {
        this.staffs = new ArrayList<>();
        this.ptts = new ArrayList<>();
        this.trainings = new ArrayList<>();
    }

    // getter&setter
    public ArrayList<Staff> getStaffs() {
        return staffs;
    }

    public void setStaffs(ArrayList<Staff> staffs) {
        this.staffs = staffs;
    }

    public ArrayList<PTT> getPtts() {
        return ptts;
    }

    public void setPtts(ArrayList<PTT> ptts) {
        this.ptts = ptts;
    }

    public ArrayList<String> getTrainings() {
        return trainings;
    }

    public void setTrainings(ArrayList<String> trainings) {
        this.trainings = trainings;
    }


    public static boolean VerifyClassDirectorOrNot(int input) throws IOException
    {
        boolean resultIs=false;
        FileReader fr = new FileReader("src/source.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        ArrayList<String> al=new ArrayList<>();
        while ((line = br.readLine()) != null)
        {
            int startIndex = line.indexOf("Class Director:id=");
            if (startIndex >= 0)
            {
                startIndex += "Class Director:id=".length();
                int endIndex = line.indexOf(" ", startIndex);
                if (endIndex < 0)
                {
                    endIndex = line.length();
                }
                //the number of id we could get
                String result = line.substring(startIndex, endIndex);
                String[] s = result.split(";");
                for (int i = 0; i < s.length; i++) {
                    al.add(s[i]);
                }
            }
        }
        //in this way, we will only get id(excluding the part "name="+name)
        String str = al.get(0);
        int num = Integer.parseInt(str);
        if(num == input)
        {
            resultIs=true;
        }

        fr.close();
        br.close();
        return resultIs;

    }

    public static boolean VerifyAdminOrNot(int input) throws IOException
    {
        boolean resultIs=false;
        FileReader fr = new FileReader("src/source.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        ArrayList<String> al=new ArrayList<>();
        while ((line = br.readLine()) != null)
        {
            int startIndex = line.indexOf("Admin:id=");
            if (startIndex >= 0)
            {
                startIndex += "Admin:id=".length();
                int endIndex = line.indexOf(" ", startIndex);
                if (endIndex < 0)
                {
                    endIndex = line.length();
                }
                String result = line.substring(startIndex, endIndex);
                String[] s = result.split(";");
                for (int i = 0; i < s.length; i++) {
                    al.add(s[i]);
                }
            }
        }
        String str = al.get(0);
        int num = Integer.parseInt(str);
        if(num == input)
        {
            resultIs=true;
        }

        fr.close();
        br.close();
        return resultIs;

    }

    public static String StringOutSpace(String str)
    {
        String[] s=str.split(" ");
        String result="";
        for (int i = 0; i < s.length; i++)
        {
           result += s[i];
        }
        return result;
    }

    public static void WriteToClassDirector(String str) throws IOException
    {
        ArrayList<String> arr = new ArrayList<>();
        FileReader fr = new FileReader("src/source.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null)
        {
            if(line.startsWith("Class Director:"))
            {
                if(line.indexOf("teachingSkills")>=0)
                {
                    line=line.substring(0,line.indexOf(";teachingSkills"));
                }
                line = line+";"+str;
            }
            arr.add(line);
        }

        FileWriter fw=new FileWriter("src/source.txt");
        for (int i = 0; i < arr.size(); i++) {
            fw.write(arr.get(i)+"\n");
        }
        fr.close();
        fw.close();
        br.close();
    }

    //if the courses are matched with corresponding staff's courses, export staffs that meet this requirement
    public static ArrayList<Staff> MatchingCourses(ArrayList<Staff> stafflist,ArrayList<Course> courselist)
    {
        ArrayList<Staff> staffResult=new ArrayList<>();
        String s1="";
        for (int i = 0; i < courselist.size(); i++)
        {
            s1 += courselist.get(i).getId();
            if(i!=courselist.size()-1)
            {
                s1+=" ";
            }
        }
        String[] result=s1.split(" ");
        int resultcount=result.length;

        for (int i = 0; i < stafflist.size(); i++)
        {
            ArrayList<Course> courses = stafflist.get(i).getCourses();
            int count=0;
            for (int j = 0; j < courses.size(); j++)
            {
                if (Arrays.asList(result).contains(courses.get(j).getId()))
                {
                        count++;
                }
            }
            if(count==resultcount)
            {
                staffResult.add(stafflist.get(i));
            }
        }
        return staffResult;

    }

    //if time is matched with corresponding staff's time, export staffs that meet this requirement
    public static ArrayList<Staff> MatchingTimes(ArrayList<Staff> stafflist,String time)
    {
        ArrayList<Staff> staffResult=new ArrayList<>();
        String times1=time.substring(14,19);
        String times2=time.substring(20,25);
        for (int i = 0; i < stafflist.size(); i++)
        {
            if(stafflist.get(i).getSpareTime().equals(times1) || stafflist.get(i).getSpareTime().equals(times2))
            {
                staffResult.add(stafflist.get(i));
            }
        }
        return staffResult;
    }


    public static void WriteToPtt(ArrayList<PTT> pttlist) throws IOException
    {
        String str="";
        if(pttlist.size()!=0)
        {
            for (int i = 0; i < pttlist.size(); i++)
            {
                str +=pttlist.get(i).toString();
                if(i!=pttlist.size()-1)
                {
                    str+="\n";
                }
            }
        }

        //in this step, we could get all lines that start with PTT in the format of ArrayList
        ArrayList<String> arr = new ArrayList<>();
        FileReader fr = new FileReader("src/source.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null)
        {
            if(line.startsWith("PTT"))
            {
                line=str;
            }
            arr.add(line);
        }

        //in this step, we will write this arr to that related file
        FileWriter fw=new FileWriter("src/source.txt");
        for (int i = 0; i < arr.size(); i++) {
            fw.write(arr.get(i)+"\n");
        }
        fr.close();
        fw.close();
        br.close();
    }
}
