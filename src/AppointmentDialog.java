import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AppointmentDialog extends JDialog{
    public AppointmentDialog(JFrame parent, Patient patient) {
        super(parent, "Appointments for " + patient.getname(), true);
        setLayout(new FlowLayout());

        String builder = patient.getAppointmentHistory();
        JTextArea appointmentText = new JTextArea(15, 30);
        appointmentText.setEditable(false);

        appointmentText.setText(builder.toString());
        JScrollPane scrollPane = new JScrollPane(appointmentText);

        add(scrollPane);
        pack();
        setLocationRelativeTo(parent);
    }
}
