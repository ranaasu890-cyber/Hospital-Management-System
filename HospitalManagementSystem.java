import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class HospitalManagementSystem {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame();
            }
        });
    }
}

class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Hospital Management System - Login");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(173, 216, 230));
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(173, 216, 230));
        panel.setLayout(null);
        add(panel);

        JLabel titleLabel = new JLabel("HOSPITAL MANAGEMENT SYSTEM", SwingConstants.CENTER);
        titleLabel.setBounds(50, 30, 400, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(25, 25, 112));
        panel.add(titleLabel);

        JLabel subtitleLabel = new JLabel("Welcome Back! Please Login", SwingConstants.CENTER);
        subtitleLabel.setBounds(120, 70, 260, 25);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitleLabel.setForeground(Color.DARK_GRAY);
        panel.add(subtitleLabel);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(100, 130, 100, 30);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(210, 130, 190, 30);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(100, 180, 100, 30);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(210, 180, 190, 30);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(passwordField);

        JButton loginButton = new JButton("LOGIN");
        loginButton.setBounds(210, 240, 100, 35);
        loginButton.setBackground(new Color(0, 191, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setFocusPainted(false);
        panel.add(loginButton);

        JLabel demoLabel = new JLabel("Demo: admin / admin123", SwingConstants.CENTER);
        demoLabel.setBounds(150, 300, 200, 20);
        demoLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        demoLabel.setForeground(Color.GRAY);
        panel.add(demoLabel);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                if (username.equals("admin") && password.equals("admin123")) {
                    dispose();
                    new DashboardFrame();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "❌ Invalid credentials!\n👤 Username: admin\n🔑 Password: admin123",
                            "Login Failed", JOptionPane.ERROR_MESSAGE);
                    usernameField.setText("");
                    passwordField.setText("");
                }
            }
        });
    }
}

class DashboardFrame extends JFrame {
    public DashboardFrame() {
        setTitle("🏥 Hospital Management System - Dashboard");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        getContentPane().setBackground(new Color(240, 248, 255));
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        headerPanel.setPreferredSize(new Dimension(0, 80));
        JLabel headerLabel = new JLabel("🏥 HOSPITAL MANAGEMENT SYSTEM", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 26));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new GridLayout(2, 3, 20, 20));
        mainPanel.setBackground(new Color(240, 248, 255));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JButton patientBtn = createButton("👥 PATIENTS", new Color(100, 149, 237));
        JButton doctorBtn = createButton("👨‍⚕️ DOCTORS", new Color(30, 144, 255));
        JButton appointmentBtn = createButton("📅 APPOINTMENTS", new Color(0, 191, 255));
        JButton billBtn = createButton("💰 BILLS", new Color(135, 206, 250));
        JButton reportBtn = createButton("📊 REPORTS", new Color(176, 224, 230));
        JButton logoutBtn = createButton("🚪 LOGOUT", new Color(255, 99, 71));

        mainPanel.add(patientBtn);
        mainPanel.add(doctorBtn);
        mainPanel.add(appointmentBtn);
        mainPanel.add(billBtn);
        mainPanel.add(reportBtn);
        mainPanel.add(logoutBtn);

        add(mainPanel, BorderLayout.CENTER);

        patientBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new PatientFrame();
            }
        });

        doctorBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DoctorFrame();
            }
        });

        appointmentBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AppointmentFrame();
            }
        });

        logoutBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginFrame();
            }
        });
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        return button;
    }
}

class PatientFrame extends JFrame {
    private JTable patientTable;
    private List<Patient> patients = new ArrayList<>();

    public PatientFrame() {
        setTitle("👥 Patient Management System");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        initComponents();
        loadSampleData();
        setVisible(true);
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        JPanel header = new JPanel(new FlowLayout());
        header.setBackground(new Color(70, 130, 180));
        JLabel title = new JLabel("PATIENT MANAGEMENT SYSTEM");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        header.add(title);
        add(header, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addBtn = new JButton("➕ Add Patient");
        JButton deleteBtn = new JButton("🗑️ Delete");
        JButton backBtn = new JButton("← Back to Dashboard");

        addBtn.setBackground(new Color(34, 139, 34));
        deleteBtn.setBackground(new Color(220, 20, 60));
        backBtn.setBackground(new Color(70, 130, 180));

        addBtn.setForeground(Color.WHITE);
        deleteBtn.setForeground(Color.WHITE);
        backBtn.setForeground(Color.WHITE);

        buttonPanel.add(addBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(backBtn);

        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addPatient();
            }
        });

        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deletePatient();
            }
        });

        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        add(buttonPanel, BorderLayout.SOUTH);

        String[] columns = { "ID", "Name", "Age", "Gender", "Contact", "Disease", "Room", "Status" };
        patientTable = new JTable(new Object[][] {}, columns);
        patientTable.setFont(new Font("Arial", Font.PLAIN, 14));
        patientTable.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(patientTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadSampleData() {
        patients.add(new Patient(1001, "Deepak choudhary", 28, "Male", "9876543210", "Fever", "101", "Active"));
        patients.add(new Patient(1002, "Jane Smith", 35, "Female", "9876543211", "Fracture", "205", "Active"));
        patients.add(new Patient(1003, "Anwar", 45, "Male", "9876543212", "Diabetes", "310", "Active"));
        patients.add(new Patient(1004, "Abhinav", 22, "Female", "9876543213", "Cold", "115", "Discharged"));
        updateTable();
    }

    private void updateTable() {
        Object[][] data = new Object[patients.size()][8];
        for (int i = 0; i < patients.size(); i++) {
            Patient p = patients.get(i);
            data[i] = new Object[] { p.id, p.name, p.age, p.gender, p.contact, p.disease, p.room, p.status };
        }
        patientTable.setModel(new javax.swing.table.DefaultTableModel(data,
                new String[] { "ID", "Name", "Age", "Gender", "Contact", "Disease", "Room", "Status" }));
    }

    private void addPatient() {
        JTextField nameField = new JTextField(15);
        JTextField ageField = new JTextField(5);
        JTextField genderField = new JTextField(10);
        JTextField contactField = new JTextField(12);
        JTextField diseaseField = new JTextField(15);
        JTextField roomField = new JTextField(5);

        Object[] fields = {
                "Full Name:", nameField,
                "Age:", ageField,
                "Gender:", genderField,
                "Contact:", contactField,
                "Disease:", diseaseField,
                "Room No:", roomField
        };

        int result = JOptionPane.showConfirmDialog(this, fields, "➕ Add New Patient", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION && !nameField.getText().trim().isEmpty()) {
            try {
                int id = 1000 + patients.size() + 1;
                patients.add(new Patient(id, nameField.getText().trim(),
                        Integer.parseInt(ageField.getText().trim()), genderField.getText().trim(),
                        contactField.getText().trim(), diseaseField.getText().trim(),
                        roomField.getText().trim(), "Active"));
                updateTable();
                JOptionPane.showMessageDialog(this, "✅ Patient added successfully!\nID: " + id);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "❌ Please enter valid age!");
            }
        }
    }

    private void deletePatient() {
        int row = patientTable.getSelectedRow();
        if (row >= 0) {
            String patientName = (String) patientTable.getValueAt(row, 1);
            int confirm = JOptionPane.showConfirmDialog(this,
                    "🗑️ Delete patient: " + patientName + "?\nThis action cannot be undone!",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                patients.remove(row);
                updateTable();
                JOptionPane.showMessageDialog(this, "✅ Patient deleted successfully!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "⚠️ Please select a patient from the table!");
        }
    }

    static class Patient {
        int id;
        String name, gender, contact, disease, room, status;
        int age;

        Patient(int id, String name, int age, String gender, String contact, String disease,
                String room, String status) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.gender = gender;
            this.contact = contact;
            this.disease = disease;
            this.room = room;
            this.status = status;
        }
    }
}

class DoctorFrame extends JFrame {
    public DoctorFrame() {
        setTitle("👨‍⚕️ Doctor Management");
        setSize(800, 600);
        setLocationRelativeTo(null);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JLabel label = new JLabel("DOCTOR MANAGEMENT");
        label.setFont(new Font("Arial", Font.BOLD, 32));
        label.setForeground(new Color(70, 130, 180));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(label);

        JLabel desc = new JLabel("Manage Doctor Profiles, Schedules & Availability");
        desc.setFont(new Font("Arial", Font.PLAIN, 18));
        desc.setForeground(Color.GRAY);
        desc.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(desc);

        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        JButton backBtn = new JButton("← Back to Dashboard");
        backBtn.setFont(new Font("Arial", Font.BOLD, 16));
        backBtn.setBackground(new Color(70, 130, 180));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        bottomPanel.add(backBtn);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}

class AppointmentFrame extends JFrame {
    public AppointmentFrame() {
        setTitle("📅 Appointment Management");
        setSize(800, 600);
        setLocationRelativeTo(null);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JLabel label = new JLabel("APPOINTMENT MANAGEMENT");
        label.setFont(new Font("Arial", Font.BOLD, 32));
        label.setForeground(new Color(70, 130, 180));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(label);

        JLabel desc = new JLabel("Schedule Appointments, View Calendar & Manage Bookings");
        desc.setFont(new Font("Arial", Font.PLAIN, 18));
        desc.setForeground(Color.GRAY);
        desc.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(desc);

        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        JButton backBtn = new JButton("← Back to Dashboard");
        backBtn.setFont(new Font("Arial", Font.BOLD, 16));
        backBtn.setBackground(new Color(70, 130, 180));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        bottomPanel.add(backBtn);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}