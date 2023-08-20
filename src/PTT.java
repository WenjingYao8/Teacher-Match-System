public class PTT
{
    private String id;
    private String name;
    private String training;

    public PTT(String id, String name, String training) {
        this.id = id;
        this.name = name;
        this.training = training;
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

    public String getTraining() {
        return training;
    }

    public void setTraining(String training) {
        this.training = training;
    }

    @Override
    public String toString() {
        return "PTT:" +
                "id="+ id+ ";" +
                "name="+name+ ";" +
                "trainingTime="+training
                ;

    }
}
