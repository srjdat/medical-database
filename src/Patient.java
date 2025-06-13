import java.text.SimpleDateFormat;
import java.util.Date;

public class Patient {
    private String name; 
    private Date dob; 
    private StringBuilder history = new StringBuilder(); 
    private String sex; 
    private String race; 

    public Patient(String name, Date dob) {
        this.name = name; 
        this.dob = dob; 
    }

    public Patient(String name, Date dob, String sex, String race) {
        this.name = name;
        this.dob = dob;
        this.sex = sex;
        this.race = race;
    }

    public String getname() { return name; }
    public Date getDOB() { return dob; }
    public String getSex() { return sex; }
    public String getRace() { return race; }

    public void setGender(String sex) { this.sex = sex; }

    public void setRace(String race) { this.race = race; }

    public String toString() { return "" + name + ": " + new SimpleDateFormat("yyyy-MM-dd").format(dob) + ", " + sex + ", " + race; }

    public void addAppointment(Date date, String reason) { 
        history.append(new SimpleDateFormat("yyyy-MM-dd").format(date) + ": " + reason + "\n"); 
    }

    public String getAppointmentHistory() { return history.toString(); }

    public boolean equals(Date date, String name) {
        //patientList.get(i).dob.equals(date) && patientList.get(i).name.equals(name);
        return this.dob.equals(date) && this.name.equalsIgnoreCase(name);
    }

}
