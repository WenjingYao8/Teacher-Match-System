import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class main
{
    public static void main(String[] args) throws IOException
    {
        //Builder Pattern
        File file = new File();


        //when the systems ends, count returns to 0
        int count=0;
        //create course
        Course math=new Course("1","math");
        Course english=new Course("2","english");
        Course computer=new Course("3","computer");

        //create ptt
        PTT pttnull=new PTT("","","");

        //create courselist
        ArrayList<Course> list1=new ArrayList<>();
        list1.add(math);
        list1.add(english);
        ArrayList<Course> list2=new ArrayList<>();
        list2.add(math);
        list2.add(computer);
        ArrayList<Course> list3=new ArrayList<>();
        list3.add(english);
        list3.add(computer);
        ArrayList<Course> list4=new ArrayList<>();
        list4.add(math);
        list4.add(english);
        list4.add(computer);
        ArrayList<Course> list5=new ArrayList<>();
        list5.add(math);
        ArrayList<Course> list6=new ArrayList<>();
        list6.add(english);
        ArrayList<Course> list7=new ArrayList<>();
        list7.add(computer);


        //create classdirector
        ClassDirector classdirector = new ClassDirector("2","classdir",null);

        //create admin
        Admin admin=new Admin("1","manage");

        //create staff
        ArrayList<Staff> stafflist=new ArrayList<>();
        Staff staff1=new Staff("3","user1","19-20",list1);
        Staff staff2=new Staff("4","user2","19-20",list2);
        Staff staff3=new Staff("5","user3","19-20",list3);
        Staff staff4=new Staff("6","user4","20-21",list1);
        Staff staff5=new Staff("7","user5","20-21",list2);
        Staff staff6=new Staff("8","user6","20-21",list3);
        Staff staff7=new Staff("9","user7","20-21",list4);
        Staff staff8=new Staff("10","user8","19-20",list5);
        Staff staff9=new Staff("11","user9","20-21",list6);
        Staff staff10=new Staff("12","user10","19-20",list7);
        Staff staff11=new Staff("13","user11","19-20",list4);
        Staff staff12=new Staff("14","user12","20-21",list5);
        Staff staff13=new Staff("15","user13","19-20",list6);
        Staff staff14=new Staff("16","user14","20-21",list7);
        stafflist.add(staff1);
        stafflist.add(staff2);
        stafflist.add(staff3);
        stafflist.add(staff4);
        stafflist.add(staff5);
        stafflist.add(staff6);
        stafflist.add(staff7);
        stafflist.add(staff8);
        stafflist.add(staff9);
        stafflist.add(staff10);
        stafflist.add(staff11);
        stafflist.add(staff12);
        stafflist.add(staff13);
        stafflist.add(staff14);

        //create training time
        String time1="Training Time:19-20;20-21";

        //initialize txt file
        FileWriter fw=new FileWriter("src/source.txt");
        fw.write(staff1.toString()+"\n");
        fw.write(staff2.toString()+"\n");
        fw.write(staff3.toString()+"\n");
        fw.write(staff4.toString()+"\n");
        fw.write(staff5.toString()+"\n");
        fw.write(staff6.toString()+"\n");
        fw.write(staff7.toString()+"\n");
        fw.write(staff8.toString()+"\n");
        fw.write(staff9.toString()+"\n");
        fw.write(staff10.toString()+"\n");
        fw.write(staff11.toString()+"\n");
        fw.write(staff12.toString()+"\n");
        fw.write(staff13.toString()+"\n");
        fw.write(staff14.toString()+"\n");
        fw.write(pttnull.toString()+"\n");
        fw.write(admin.toString()+"\n");
        fw.write(classdirector.toString()+"\n");
        fw.write(time1);

        fw.close();


        //save the course requirement of classdirector
        ArrayList<Course> arraylist = new ArrayList<Course>();


        //beginning of the system
        while (true)
        {
            System.out.println("welcome to staff system");
            System.out.println("input 0 to exit system. id:1 is admin,id=2 is class director");
            Scanner scanner=new Scanner(System.in);
            System.out.print("please input your id：");
            int inputID=scanner.nextInt();

            if(inputID==0)
            {
                break;
            }
            //class director would decide what two courses they need
            if(file.VerifyClassDirectorOrNot(inputID))
            {
                String expectCourse;
                System.out.println("");
                System.out.println("welcome Class Director!!");

                System.out.println("Please enter your expected course (math, english, computer):"+"\n"+" (Each course is , separated, no spaces, and no . at the end.)");
                expectCourse=scanner.next();
                String[] s=expectCourse.split(",");
                arraylist.clear();
                for (int i = 0; i < s.length; i++)
                {
                    if(s[i].equals("math"))
                    {
                        arraylist.add(math);
                    } else if (s[i].equals("english"))
                    {
                        arraylist.add(english);
                    }
                    else if (s[i].equals("computer"))
                    {
                        arraylist.add(computer);
                    }
                }
                String finalTxt = file.StringOutSpace(arraylist.toString());
                finalTxt="teachingSkills="+finalTxt;
                file.WriteToClassDirector(finalTxt);
                count=1;
            } else if (file.VerifyAdminOrNot(inputID))
            {
                System.out.println("welcome Admin!!");
                if(count==1)
                {
                    //start course match
                    System.out.println("class director choose "+file.StringOutSpace(arraylist.toString()));
                    ArrayList<Staff> matchCourseList =file.MatchingCourses(stafflist,arraylist);

                    //start time match
                    ArrayList<Staff> matchFinalList = file.MatchingTimes(matchCourseList,time1);
                    System.out.println("According to match, the following staffs meet requirement: ");
                    System.out.println(matchFinalList.toString());

                    //Match the input staff id with the staff list that meets the requirements; if they are matched,
                    //add all staffs that meet requirements to pttlist and write it to txt file
                    System.out.println("Please enter the staff id you want from all staffs that meet requirement: ");
                    String chooseNumber=scanner.next();

                    ArrayList<PTT> pttlist=new ArrayList<>();
                    for (int i = 0; i < matchFinalList.size(); i++)
                    {
                        if(matchFinalList.get(i).getId().equals(chooseNumber))
                        {
                            PTT ptti=new PTT(matchFinalList.get(i).getId(),matchFinalList.get(i).getName(),matchFinalList.get(i).getSpareTime());
                            pttlist.add(ptti);
                        }
                    }

                    if(pttlist.size()!=0)
                    {
                        file.WriteToPtt(pttlist);
                    }
                    count=0;
                }else
                {
                    System.out.println("please let class director choose courses first");
                }

            }
            else
            {
                System.out.println("sorry, id error");
            }
        }


    }
}
