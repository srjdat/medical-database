import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date; 

public class App extends JFrame { 
 
    static ArrayList<Patient> patientList = new ArrayList<>();

    //get the fonts
    Font font1 = new Font("Times New Roman", 0, 17);
    Font font2 = new Font("Times New Roman", 0, 15);
    Font font3 = new Font("Times New Roman", 0, 25);
    
    /*Get patient from name method */
    public Patient findPatient(String name, String dob) {
        for (Patient p : patientList) {
            try {
                if (p.equals(new SimpleDateFormat("yyyy-MM-dd").parse(dob), name)) {
                    return p;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public App() {

        //Get screen dimensions
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();

        JFrame frame = new JFrame(); 
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Database");
        frame.setSize((int)(screenSize.getWidth()/2), (int)(screenSize.getHeight()/2));
        frame.setVisible(true);
        
        /*
         * Center Panel, going to include all the patients/patient history
         */ 
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBackground(Color.LIGHT_GRAY);

        // text area to display stuff
        JTextArea displayTextArea = new JTextArea();
        //user can't edit it
        displayTextArea.setEditable(false); 
        JScrollPane scrollPane = new JScrollPane(displayTextArea);
        displayPanel.add(scrollPane, BorderLayout.CENTER);

        frame.add(displayPanel, BorderLayout.CENTER);     


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
        nameTextField.setFont(font1);
        TextPrompt tp = new TextPrompt("Enter name", nameTextField);
        tp.setFont(font2);
        tp.setForeground(Color.RED);

        //textfield to enter the date of birth 
        //should have a preview text saying 'yyyy-mm-dd' since that is the format 
        JTextField dateTextField = new JTextField();
        dateTextField.setPreferredSize(new Dimension(200, 30));
        dateTextField.setFont(font1);
        TextPrompt tp2 = new TextPrompt("yyyy-mm-dd", dateTextField);
        tp2.setFont(font2);
        tp2.setForeground(Color.RED);

        //textfield to enter sex
        //i'll add a pop down thing later
        JTextField sexTextField = new JTextField();
        sexTextField.setPreferredSize(new Dimension(200,30));
        sexTextField.setFont(font1);
        TextPrompt tp3 = new TextPrompt("Enter Sex", sexTextField);
        tp3.setFont(font2);
        tp3.setForeground(Color.RED);

        //textfield to enter race
        //maybe a pop down? not sure right now
        JTextField raceTextField = new JTextField();
        raceTextField.setPreferredSize(new Dimension(200,30));
        raceTextField.setFont(font1);
        TextPrompt tp4 = new TextPrompt("Enter Race", raceTextField);
        tp4.setFont(font2);
        tp4.setForeground(Color.RED);

        //button for adding
        //have a dialog to add sex and race
        JButton addPatientButton = new JButton("Add Patient"); 
        addPatientButton.addActionListener(e -> {
            try {
                patientList.add(new Patient(nameTextField.getText(), new SimpleDateFormat("yyyy-MM-dd").parse(dateTextField.getText()), sexTextField.getText(), raceTextField.getText()));

                nameTextField.setText("");
                dateTextField.setText("");
                sexTextField.setText("");
                raceTextField.setText("");
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        });

        addPatientPanel.add(nameTextField);
        addPatientPanel.add(dateTextField);
        addPatientPanel.add(sexTextField);
        addPatientPanel.add(raceTextField);
        addPatientPanel.add(addPatientButton);

        frame.add(addPatientPanel, BorderLayout.SOUTH);


        /*
         * Top Panel, add appointment for patients
         */
        JPanel addAppointmentPanel = new JPanel(); 
        addAppointmentPanel.setLayout(new FlowLayout(1, 15, 23));

        JButton addAppointmentButton = new JButton("Add Appointment");
        JButton getAppointmentButton = new JButton("Get Appointment");

        getAppointmentButton.addActionListener(e -> {
            String name = nameTextField.getText();
            String dob = dateTextField.getText();
            Patient patient = findPatient(name, dob);

            if (patient != null) {
                new AppointmentDialog(frame, patient).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(frame, "Patient not found.");
            }

            nameTextField.setText("");
            dateTextField.setText("");
        });     
        
        //get patient button
        JButton getPatientButton = new JButton("Get Patient Information");
        getPatientButton.addActionListener(e -> {
            String name = nameTextField.getText();
            String date = dateTextField.getText();

            //make the date string into an actual date object
            //idk if i should keep this formatting but it's easier on my eyes for now might change later
            
            //date = new SimpleDateFormat("yyyy-MM-dd").parse(dateTextField.getText()); 
            Patient p = findPatient(name, date);

            if (p != null) {
                displayTextArea.setFont(font3);
                displayTextArea.setText(p.toString());
            } else {
                JOptionPane.showMessageDialog(frame, "Patient Not Found");
            }
           
            
            nameTextField.setText("");
            dateTextField.setText("");
        });

        addAppointmentPanel.add(getPatientButton);
        addAppointmentPanel.add(addAppointmentButton); 
        addAppointmentPanel.add(getAppointmentButton);
        frame.add(addAppointmentPanel, BorderLayout.NORTH);


        
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

        System.out.println(srijan.getAppointmentHistory());
    }    
}