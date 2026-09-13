import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Login extends JFrame {

    private JTextField idField;
    private JPasswordField passwordField;

    private JRadioButton studentRadio;
    private JRadioButton facultyRadio;

    public Login() {

        setTitle("AIUB Make-Up Class Management System");

        setSize(500, 650);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        createUI();

        setVisible(true);
    }

    private void createUI() {

        JPanel main = new JPanel(null);

        main.setBackground(
                new Color(245, 247, 252)
        );

        // =====================================================
        // AIUB
        // =====================================================

        JLabel aiub = new JLabel("AIUB");

        aiub.setBounds(
                195, 30, 100, 55
        );

        aiub.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        aiub.setForeground(Color.WHITE);

        aiub.setBackground(
                new Color(35, 95, 205)
        );

        aiub.setOpaque(true);

        aiub.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        main.add(aiub);


        // =====================================================
        // TITLE
        // =====================================================

        JLabel title =
                new JLabel("MAKE-UP CLASS");

        title.setBounds(
                120, 105, 260, 30
        );

        title.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        21
                )
        );

        title.setForeground(
                new Color(35, 95, 205)
        );

        main.add(title);


        JLabel subtitle =
                new JLabel("MANAGEMENT SYSTEM");

        subtitle.setBounds(
                120, 135, 260, 25
        );

        subtitle.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        subtitle.setForeground(
                new Color(35, 95, 205)
        );

        main.add(subtitle);


        // =====================================================
        // USER ID LABEL
        // =====================================================

        JLabel idLabel =
                new JLabel("User ID");

        idLabel.setBounds(
                70, 200, 100, 25
        );

        idLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        main.add(idLabel);


        // =====================================================
        // USER ID FIELD
        // =====================================================

        idField =
                new JTextField();

        idField.setBounds(
                70, 225, 360, 40
        );

        idField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        idField.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(190, 195, 205)
                        ),
                        BorderFactory.createEmptyBorder(
                                5, 10, 5, 10
                        )
                )
        );

        main.add(idField);


        // =====================================================
        // PASSWORD LABEL
        // =====================================================

        JLabel passLabel =
                new JLabel("Password");

        passLabel.setBounds(
                70, 280, 100, 25
        );

        passLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        main.add(passLabel);


        // =====================================================
        // PASSWORD FIELD
        // TOTAL WIDTH = 360
        // FIELD = 320
        // EYE = 40
        // =====================================================

        passwordField =
                new JPasswordField();

        passwordField.setBounds(
                70, 305, 320, 40
        );

        passwordField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        passwordField.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(190, 195, 205)
                        ),
                        BorderFactory.createEmptyBorder(
                                5, 10, 5, 5
                        )
                )
        );

        main.add(passwordField);


        // =====================================================
        // EYE BUTTON
        // =====================================================

        JButton eyeButton =
                new JButton("👁");

        eyeButton.setBounds(
                390, 305, 40, 40
        );

        eyeButton.setBackground(
                new Color(35, 95, 205)
        );

        eyeButton.setForeground(
                Color.WHITE
        );

        eyeButton.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        eyeButton.setFocusPainted(false);

        eyeButton.setBorder(
                BorderFactory.createLineBorder(
                        new Color(35, 95, 205)
                )
        );

        eyeButton.setToolTipText(
                "Show / Hide Password"
        );

        main.add(eyeButton);


        // =====================================================
        // SHOW / HIDE PASSWORD
        // =====================================================

        eyeButton.addActionListener(e -> {

            if (
                    passwordField.getEchoChar()
                            == '\u0000'
            ) {

                // Hide password

                passwordField.setEchoChar('•');

                eyeButton.setBackground(
                        new Color(35, 95, 205)
                );

            } else {

                // Show password

                passwordField.setEchoChar(
                        (char) 0
                );

                eyeButton.setBackground(
                        new Color(25, 75, 165)
                );
            }
        });


        // =====================================================
        // STUDENT RADIO BUTTON
        // =====================================================

        studentRadio =
                new JRadioButton(
                        "Student"
                );

        studentRadio.setBounds(
                140, 360, 100, 30
        );

        studentRadio.setBackground(
                new Color(245, 247, 252)
        );

        studentRadio.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        main.add(studentRadio);


        // =====================================================
        // FACULTY RADIO BUTTON
        // =====================================================

        facultyRadio =
                new JRadioButton(
                        "Faculty"
                );

        facultyRadio.setBounds(
                250, 360, 100, 30
        );

        facultyRadio.setBackground(
                new Color(245, 247, 252)
        );

        facultyRadio.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        main.add(facultyRadio);


        // =====================================================
        // RADIO GROUP
        // =====================================================

        ButtonGroup group =
                new ButtonGroup();

        group.add(studentRadio);

        group.add(facultyRadio);

        studentRadio.setSelected(true);


        // =====================================================
        // LOGIN BUTTON
        // =====================================================

        JButton loginButton =
                new JButton("LOGIN");

        loginButton.setBounds(
                70, 415, 360, 45
        );

        loginButton.setBackground(
                new Color(35, 95, 205)
        );

        loginButton.setForeground(
                Color.WHITE
        );

        loginButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        loginButton.setFocusPainted(false);

        main.add(loginButton);


        // =====================================================
        // PASSWORD INFO
        // =====================================================

        JLabel info =
                new JLabel(
                        "<html><center>"
                        + "Student Password: student123<br>"
                        + "Faculty Password: faculty123"
                        + "</center></html>"
                );

        info.setBounds(
                100, 490, 300, 50
        );

        info.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        info.setForeground(
                Color.GRAY
        );

        main.add(info);


        // =====================================================
        // LOGIN ACTION
        // =====================================================

        loginButton.addActionListener(
                e -> login()
        );


        // ENTER KEY LOGIN

        passwordField.addActionListener(
                e -> login()
        );


        add(main);
    }


    // =========================================================
    // LOGIN
    // =========================================================

    private void login() {

        String idText =
                idField.getText().trim();

        String password =
                new String(
                        passwordField.getPassword()
                );


        if (
                idText.isEmpty()
                ||
                password.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter User ID and Password."
            );

            return;
        }


        int id;

        try {

            id =
                    Integer.parseInt(
                            idText
                    );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "User ID must be a number."
            );

            return;
        }


        // =====================================================
        // STUDENT LOGIN
        // =====================================================

        if (studentRadio.isSelected()) {

            if (
                    !password.equals(
                            "student123"
                    )
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Wrong student password!"
                );

                return;
            }


            try {

                Student student =
                        getStudent(id);


                if (student == null) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Student ID not found!"
                    );

                    return;
                }


                new StudentDashboard(
                        student
                );

                dispose();


            } catch (SQLException e) {

                JOptionPane.showMessageDialog(
                        this,
                        "Database Error:\n"
                        + e.getMessage()
                );
            }

            return;
        }


        // =====================================================
        // FACULTY LOGIN
        // =====================================================

        if (facultyRadio.isSelected()) {

            if (
                    !password.equals(
                            "faculty123"
                    )
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Wrong faculty password!"
                );

                return;
            }


            try {

                Faculty faculty =
                        getFaculty(id);


                if (faculty == null) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Faculty ID not found!"
                    );

                    return;
                }


                new FacultyDashboard(
                        faculty
                );

                dispose();


            } catch (SQLException e) {

                JOptionPane.showMessageDialog(
                        this,
                        "Database Error:\n"
                        + e.getMessage()
                );
            }
        }
    }


    // =========================================================
    // GET STUDENT
    // =========================================================

    private Student getStudent(
            int id
    ) throws SQLException {

        String sql =
                "SELECT student_id, "
                + "student_name, "
                + "program, "
                + "batch, "
                + "email, "
                + "phone "
                + "FROM Student "
                + "WHERE student_id = ?";


        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    id
            );

            ResultSet rs =
                    ps.executeQuery();


            if (rs.next()) {

                return new Student(

                        rs.getInt(
                                "student_id"
                        ),

                        rs.getString(
                                "student_name"
                        ),

                        rs.getString(
                                "program"
                        ),

                        rs.getString(
                                "batch"
                        ),

                        rs.getString(
                                "email"
                        ),

                        rs.getString(
                                "phone"
                        )
                );
            }
        }

        return null;
    }


    // =========================================================
    // GET FACULTY
    // =========================================================

    private Faculty getFaculty(
            int id
    ) throws SQLException {

        String sql =
                "SELECT faculty_id, "
                + "faculty_name, "
                + "designation, "
                + "email, "
                + "office_phone "
                + "FROM Faculty "
                + "WHERE faculty_id = ?";


        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    id
            );

            ResultSet rs =
                    ps.executeQuery();


            if (rs.next()) {

                return new Faculty(

                        rs.getInt(
                                "faculty_id"
                        ),

                        rs.getString(
                                "faculty_name"
                        ),

                        rs.getString(
                                "designation"
                        ),

                        rs.getString(
                                "email"
                        ),

                        rs.getString(
                                "office_phone"
                        )
                );
            }
        }

        return null;
    }


    // =========================================================
    // MAIN
    // =========================================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> new Login()
        );
    }
}