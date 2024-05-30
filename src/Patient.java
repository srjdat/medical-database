import java.text.SimpleDateFormat;
import java.util.Date;

public class Patient {
    String name; 
    Date dob; 
    StringBuilder history = new StringBuilder(); 
    String sex; 
    String race; 

    public Patient(String name, Date dob) {
        this.name = name; 
        this.dob = dob; 
    }

    /*
     * Basic stuff idk
     */
    public void setGender(String sex) { this.sex = sex; }

    public void setRace(String race) { this.race = race; }

    public String toString() { return "" + name + ": " + new SimpleDateFormat("yyyy-MM-dd").format(dob); }

    public void addAppointment(Date date, String reason) { history.append(new SimpleDateFormat("yyyy-MM-dd").format(date) + ": " + reason + "\n"); }

    public String getAppointmentHistory() { return history.toString(); }

}
