import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date; 

public class App extends JFrame { 
 
    static ArrayList<Patient> patientList = new ArrayList<>();

    public App() {
        JFrame frame = new JFrame(); 
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Hello");
        frame.setSize(400, 400);
        frame.setVisible(true);


        /*  
         * Bottom Panel, will be mainly used for adding patients as of right now
         */
        JPanel addPatientPanel = new JPanel();
        addPatientPanel.setBackground(Color.BLUE);
        addPatientPanel.setLayout(new FlowLayout(1, 15, 23));
        addPatientPanel.setPreferredSize(new Dimension(100, 75));

        //textfield to enter the name
        //should have a enter name in red preview text
        JTextField nameTextField = new JTextField();
        nameTextField.setPreferredSize(new Dimension(200, 30));
        nameTextField.setFont(new Font("Times New Roman", 0, 17));
        TextPrompt tp = new TextPrompt("Enter name", nameTextField);
        tp.setFont(new Font("Times New Roman", 0, 15));
        tp.setForeground(Color.RED);

        //textfield to enter the date of birth 
        //should have a preview text saying 'yyyy-mm-dd' since that is the format 
        JTextField dateTextField = new JTextField();
        dateTextField.setPreferredSize(new Dimension(200, 30));
        dateTextField.setFont(new Font("Times New Roman", 0, 17));
        TextPrompt tp2 = new TextPrompt("yyyy-mm-dd", dateTextField);
        tp2.setFont(new Font("Times New Roman", 0, 15));
        tp2.setForeground(Color.RED);

        //button for adding
        JButton addPatientButton = new JButton("Add Patient"); 
        addPatientButton.addActionListener(e -> {
            try {
                patientList.add(new Patient(nameTextField.getText(), new SimpleDateFormat("yyyy-MM-dd").parse(dateTextField.getText())));
            } catch (ParseException e1) {
                e1.printStackTrace();
            }

            nameTextField.setText("");
            dateTextField.setText("");

            System.out.println(patientList.toString());
        }); 

        //button to get a specific patient
        JButton getPatientButton = new JButton("Get Patient Information");
        getPatientButton.addActionListener(e -> {
            String name = nameTextField.getText(); 
            Date date = new Date();

            //make the date string into an actual date object
            //idk if i should keep this formatting but it's easier on my eyes for now might change later
            try { date = new SimpleDateFormat("yyyy-MM-dd").parse(dateTextField.getText()); } 
            catch (ParseException e1) { e1.printStackTrace(); }

            Boolean bool = false; 
            for (int i = 0; i < patientList.size(); i++) {
                if(patientList.get(i).dob.equals(date) && patientList.get(i).name.equals(name)) {
                    bool = true; 
                }
            }
            System.out.println(bool);
            nameTextField.setText("");
            dateTextField.setText("");
        });


        addPatientPanel.add(nameTextField);
        addPatientPanel.add(dateTextField);
        addPatientPanel.add(addPatientButton);
        addPatientPanel.add(getPatientButton);
        frame.add(addPatientPanel, BorderLayout.SOUTH);



        /*
         * Top Panel, add appointment for patients
         */
        JPanel addAppointmentPanel = new JPanel(); 
        addAppointmentPanel.setLayout(new FlowLayout());


        frame.add(addAppointmentPanel, BorderLayout.NORTH);


        /*
         * Center Panel, going to include all the patients/patient history
         */

        
    }

    public static void main(String[] args) throws ParseException {
        new App();

        Patient srijan = new Patient("Srijan Datta", new SimpleDateFormat("yyyy-MM-dd").parse("2006-08-28"));

        srijan.addAppointment(new SimpleDateFormat("MM-dd-yyyy").parse("5-26-2024"), "Regular checkup");
        srijan.addAppointment(new SimpleDateFormat("MM-dd-yyyy").parse("4-26-2024"), "asdfasd");
        srijan.addAppointment(new SimpleDateFormat("MM-dd-yyyy").parse("3-26-2024"), "vidkdsmx");
        srijan.addAppointment(new SimpleDateFormat("MM-dd-yyyy").parse("2-26-2024"), "stomach pain");
        srijan.addAppointment(new SimpleDateFormat("MM-dd-yyyy").parse("1-26-2024"), "jasontheweenie");
        srijan.addAppointment(new SimpleDateFormat("MM-dd-yyyy").parse("5-26-2023"), "dead from L rizz");
        srijan.addAppointment(new SimpleDateFormat("MM-dd-yyyy").parse("5-26-2022"), "jujutsu shenangins");

        patientList.add(srijan);
    }
    
}
