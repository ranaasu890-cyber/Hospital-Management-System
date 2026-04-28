import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class HospitalManagementSystem{

    static ArrayList<String> patients = new ArrayList<>();
    static ArrayList<String> doctors = new ArrayList<>();
    static ArrayList<String> appointments = new ArrayList<>();
    static ArrayList<String> lpdRecords = new ArrayList<>();

    public static void main(String[] args) {
        loginPage();
    }

    // 🔐 LOGIN PAGE
    static void loginPage() {
        JFrame frame = new JFrame("Login");
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(3,2));

        JTextField user = new JTextField();
        JPasswordField pass = new JPasswordField();

        frame.add(new JLabel("Username:"));
        frame.add(user);
        frame.add(new JLabel("Password:"));
        frame.add(pass);

        JButton loginBtn = new JButton("Login");
        frame.add(loginBtn);

        loginBtn.addActionListener(e -> {
            if(user.getText().equals("admin") && new String(pass.getPassword()).equals("1234")){
                frame.dispose();
                mainMenu();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid Login");
            }
        });

        frame.setVisible(true);
    }

    // 🏠 MAIN MENU
    static void mainMenu() {
        JFrame frame = new JFrame("Main Menu");
        frame.setSize(300,300);
        frame.setLayout(new GridLayout(6,1));

        JButton p = new JButton("Patient");
        JButton d = new JButton("Doctor");
        JButton opd = new JButton("OPD");
        JButton lpd = new JButton("LPD");
        JButton bill = new JButton("Billing");

        frame.add(p);
        frame.add(d);
        frame.add(opd);
        frame.add(lpd);
        frame.add(bill);

        p.addActionListener(e -> patientPage());
        d.addActionListener(e -> doctorPage());
        opd.addActionListener(e -> opdPage());
        lpd.addActionListener(e -> lpdPage());
        bill.addActionListener(e -> billPage());

        frame.setVisible(true);
    }

    // 👤 PATIENT
    static void patientPage() {
        JFrame f = new JFrame("Patient");
        f.setSize(300,200);
        f.setLayout(new GridLayout(3,1));

        JTextField name = new JTextField();
        JButton add = new JButton("Add Patient");
        JButton view = new JButton("View Patients");

        f.add(name);
        f.add(add);
        f.add(view);

        add.addActionListener(e -> {
            patients.add(name.getText());
            JOptionPane.showMessageDialog(f, "Patient Added");
        });

        view.addActionListener(e -> {
            JOptionPane.showMessageDialog(f, patients.toString());
        });

        f.setVisible(true);
    }

    // 🧑‍⚕️ DOCTOR
    static void doctorPage() {
        JFrame f = new JFrame("Doctor");
        f.setSize(300,200);
        f.setLayout(new GridLayout(3,1));

        JTextField name = new JTextField();
        JButton add = new JButton("Add Doctor");
        JButton view = new JButton("View Doctors");

        f.add(name);
        f.add(add);
        f.add(view);

        add.addActionListener(e -> {
            doctors.add(name.getText());
            JOptionPane.showMessageDialog(f, "Doctor Added");
        });

        view.addActionListener(e -> {
            JOptionPane.showMessageDialog(f, doctors.toString());
        });

        f.setVisible(true);
    }

    // 📅 OPD
    static void opdPage() {
        JFrame f = new JFrame("OPD");
        f.setSize(300,250);
        f.setLayout(new GridLayout(4,1));

        JTextField patient = new JTextField();
        JTextField doctor = new JTextField();

        JButton book = new JButton("Book Appointment");
        JButton view = new JButton("View Appointments");

        f.add(patient);
        f.add(doctor);
        f.add(book);
        f.add(view);

        book.addActionListener(e -> {
            String data = patient.getText() + " -> " + doctor.getText();
            appointments.add(data);
            JOptionPane.showMessageDialog(f, "Appointment Booked");
        });

        view.addActionListener(e -> {
            JOptionPane.showMessageDialog(f, appointments.toString());
        });

        f.setVisible(true);
    }

    // 🏥 LPD (Admission)
    static void lpdPage() {
        JFrame f = new JFrame("LPD");
        f.setSize(300,300);
        f.setLayout(new GridLayout(5,1));

        JTextField p = new JTextField();
        JTextField d = new JTextField();
        JTextField date = new JTextField();
        JTextField room = new JTextField();

        JButton add = new JButton("Admit");
        JButton view = new JButton("View Records");

        f.add(p);
        f.add(d);
        f.add(date);
        f.add(room);
        f.add(add);
        f.add(view);

        add.addActionListener(e -> {
            String rec = p.getText()+" "+d.getText()+" "+date.getText()+" Room:"+room.getText();
            lpdRecords.add(rec);
            JOptionPane.showMessageDialog(f, "LPD Added");
        });

        view.addActionListener(e -> {
            JOptionPane.showMessageDialog(f, lpdRecords.toString());
        });

        f.setVisible(true);
    }

    // 💵 BILL
    static void billPage() {
        JFrame f = new JFrame("Bill");
        f.setSize(300,300);
        f.setLayout(new GridLayout(6,1));

        JTextField patient = new JTextField();
        JTextField doctor = new JTextField();
        JTextField consult = new JTextField();
        JTextField medicine = new JTextField();
        JTextField test = new JTextField();

        JButton gen = new JButton("Generate Bill");

        f.add(patient);
        f.add(doctor);
        f.add(consult);
        f.add(medicine);
        f.add(test);
        f.add(gen);

        gen.addActionListener(e -> {
            int total = Integer.parseInt(consult.getText()) +
                        Integer.parseInt(medicine.getText()) +
                        Integer.parseInt(test.getText());

            JOptionPane.showMessageDialog(f,
                    "Patient: "+patient.getText()+
                    "\nDoctor: "+doctor.getText()+
                    "\nTotal: ₹"+total);
        });

        f.setVisible(true);
    }
}