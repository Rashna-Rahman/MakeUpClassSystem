import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StudentProfile extends JFrame {

    private Student student;

    public StudentProfile(Student student) {

        this.student = student;

        setTitle("AIUB - Student Profile");
        setSize(1050, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        createUI();

        setVisible(true);
    }

    // =========================================================
    // MAIN UI
    // =========================================================

    private void createUI() {

        JPanel main = new JPanel(new BorderLayout());

        main.setBackground(
                new Color(245, 247, 252)
        );


        // =====================================================
        // SIDEBAR
        // =====================================================

        JPanel sidebar = new JPanel(new BorderLayout());

        sidebar.setPreferredSize(
                new Dimension(260, 700)
        );

        sidebar.setBackground(Color.WHITE);


        // =====================================================
        // LOGO
        // =====================================================

        JPanel logoPanel = new JPanel();

        logoPanel.setLayout(
                new BoxLayout(
                        logoPanel,
                        BoxLayout.Y_AXIS
                )
        );

        logoPanel.setBackground(Color.WHITE);

        logoPanel.setBorder(
                new EmptyBorder(
                        25,
                        25,
                        20,
                        20
                )
        );


        JLabel aiub = new JLabel("AIUB");

        aiub.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        aiub.setForeground(
                new Color(
                        35,
                        90,
                        190
                )
        );

        logoPanel.add(aiub);


        JLabel system =
                new JLabel("Make-Up System");

        system.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        system.setForeground(
                new Color(
                        55,
                        65,
                        80
                )
        );

        logoPanel.add(system);


        JLabel portal =
                new JLabel("Student Portal");

        portal.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        portal.setForeground(
                new Color(
                        120,
                        125,
                        135
                )
        );

        logoPanel.add(portal);


        sidebar.add(
                logoPanel,
                BorderLayout.NORTH
        );


        // =====================================================
        // SIDEBAR MENU
        // =====================================================

        JPanel menu = new JPanel();

        menu.setLayout(
                new BoxLayout(
                        menu,
                        BoxLayout.Y_AXIS
                )
        );

        menu.setBackground(Color.WHITE);

        menu.setBorder(
                new EmptyBorder(
                        20,
                        15,
                        15,
                        15
                )
        );


        JButton dashboard =
                createMenuButton(
                        "▦  Dashboard",
                        false
                );


        JButton submit =
                createMenuButton(
                        "☑  Submit Request",
                        false
                );


        JButton requests =
                createMenuButton(
                        "▤  My Requests",
                        false
                );


        JButton notifications =
                createMenuButton(
                        "🔔  Notifications",
                        false
                );


        JButton profile =
                createMenuButton(
                        "♙  Profile",
                        true
                );


        menu.add(dashboard);

        menu.add(
                Box.createVerticalStrut(8)
        );

        menu.add(submit);

        menu.add(
                Box.createVerticalStrut(8)
        );

        menu.add(requests);

        menu.add(
                Box.createVerticalStrut(8)
        );

        menu.add(notifications);

        menu.add(
                Box.createVerticalStrut(8)
        );

        menu.add(profile);


        sidebar.add(
                menu,
                BorderLayout.CENTER
        );


        // =====================================================
        // LOGOUT
        // =====================================================

        JPanel logoutPanel =
                new JPanel(
                        new BorderLayout()
                );

        logoutPanel.setBackground(Color.WHITE);

        logoutPanel.setBorder(
                BorderFactory.createMatteBorder(
                        1,
                        0,
                        0,
                        0,
                        new Color(
                                230,
                                233,
                                240
                        )
                )
        );


        JButton logout =
                new JButton("⇥  Logout");

        logout.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        logout.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        logout.setForeground(
                new Color(
                        220,
                        70,
                        70
                )
        );

        logout.setBackground(Color.WHITE);

        logout.setBorder(
                new EmptyBorder(
                        18,
                        25,
                        18,
                        25
                )
        );

        logout.setFocusPainted(false);


        logout.addActionListener(e -> {

            dispose();

            new Login();
        });


        logoutPanel.add(
                logout,
                BorderLayout.CENTER
        );


        sidebar.add(
                logoutPanel,
                BorderLayout.SOUTH
        );


        // =====================================================
        // RIGHT SIDE
        // =====================================================

        JPanel right =
                new JPanel(
                        new BorderLayout()
                );

        right.setBackground(
                new Color(
                        245,
                        247,
                        252
                )
        );


        // =====================================================
        // BLUE HEADER
        // =====================================================

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setBackground(
                new Color(
                        42,
                        100,
                        210
                )
        );

        header.setPreferredSize(
                new Dimension(
                        790,
                        80
                )
        );

        header.setBorder(
                new EmptyBorder(
                        15,
                        30,
                        15,
                        30
                )
        );


        JLabel title =
                new JLabel(
                        "Student Profile"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        25
                )
        );

        title.setForeground(Color.WHITE);


        header.add(
                title,
                BorderLayout.WEST
        );


        JLabel studentId =
                new JLabel(
                        "Student ID: "
                                + student.getStudentId()
                );

        studentId.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        studentId.setForeground(Color.WHITE);


        header.add(
                studentId,
                BorderLayout.EAST
        );


        right.add(
                header,
                BorderLayout.NORTH
        );


        // =====================================================
        // CONTENT
        // =====================================================

        JPanel content =
                new JPanel();

        content.setLayout(
                new BoxLayout(
                        content,
                        BoxLayout.Y_AXIS
                )
        );

        content.setBackground(
                new Color(
                        245,
                        247,
                        252
                )
        );

        content.setBorder(
                new EmptyBorder(
                        30,
                        35,
                        30,
                        35
                )
        );


        // =====================================================
        // PROFILE CARD
        // =====================================================

        JPanel profileCard =
                new JPanel(
                        new BorderLayout(
                                25,
                                10
                        )
                );

        profileCard.setBackground(Color.WHITE);

        profileCard.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(
                                        225,
                                        230,
                                        240
                                )
                        ),

                        new EmptyBorder(
                                25,
                                30,
                                25,
                                30
                        )
                )
        );


        // =====================================================
        // AVATAR
        // =====================================================

        JLabel avatar =
                new JLabel("STUDENT");

        avatar.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        avatar.setVerticalAlignment(
                SwingConstants.CENTER
        );

        avatar.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        avatar.setForeground(Color.WHITE);

        avatar.setBackground(
                new Color(
                        42,
                        100,
                        210
                )
        );

        avatar.setOpaque(true);

        avatar.setPreferredSize(
                new Dimension(
                        95,
                        95
                )
        );


        profileCard.add(
                avatar,
                BorderLayout.WEST
        );


        // =====================================================
        // STUDENT IDENTITY
        // =====================================================

        JPanel identity =
                new JPanel();

        identity.setLayout(
                new BoxLayout(
                        identity,
                        BoxLayout.Y_AXIS
                )
        );

        identity.setOpaque(false);


        JLabel name =
                new JLabel(
                        student.getStudentName()
                );

        name.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        25
                )
        );

        name.setForeground(
                new Color(
                        30,
                        35,
                        45
                )
        );


        identity.add(name);


        identity.add(
                Box.createVerticalStrut(8)
        );


        JLabel idText =
                new JLabel(
                        "Student ID: "
                                + student.getStudentId()
                );

        idText.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        idText.setForeground(
                new Color(
                        80,
                        85,
                        95
                )
        );


        identity.add(idText);


        identity.add(
                Box.createVerticalStrut(6)
        );


        JLabel programText =
                new JLabel(
                        student.getProgram()
                                + "  •  Batch "
                                + student.getBatch()
                );

        programText.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        programText.setForeground(
                new Color(
                        100,
                        105,
                        115
                )
        );


        identity.add(
                programText
        );


        profileCard.add(
                identity,
                BorderLayout.CENTER
        );


        content.add(profileCard);


        content.add(
                Box.createVerticalStrut(20)
        );


        // =====================================================
        // PERSONAL INFORMATION CARD
        // =====================================================

        JPanel infoCard =
                new JPanel(
                        new BorderLayout()
                );

        infoCard.setBackground(Color.WHITE);

        infoCard.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(
                                        225,
                                        230,
                                        240
                                )
                        ),

                        new EmptyBorder(
                                25,
                                30,
                                30,
                                30
                        )
                )
        );


        JLabel infoTitle =
                new JLabel(
                        "Personal Information"
                );

        infoTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        19
                )
        );

        infoTitle.setForeground(
                new Color(
                        42,
                        100,
                        210
                )
        );


        infoCard.add(
                infoTitle,
                BorderLayout.NORTH
        );


        // =====================================================
        // INFORMATION LIST
        // =====================================================

        JPanel details =
                new JPanel();

        details.setLayout(
                new BoxLayout(
                        details,
                        BoxLayout.Y_AXIS
                )
        );

        details.setBackground(Color.WHITE);

        details.setBorder(
                new EmptyBorder(
                        20,
                        0,
                        0,
                        0
                )
        );


        addProfileRow(
                details,
                "Student ID",
                String.valueOf(
                        student.getStudentId()
                )
        );


        addProfileRow(
                details,
                "Program",
                student.getProgram()
        );


        addProfileRow(
                details,
                "Batch",
                student.getBatch()
        );


        addProfileRow(
                details,
                "Email",
                student.getEmail()
        );


        addProfileRow(
                details,
                "Phone",
                student.getPhone()
        );


        infoCard.add(
                details,
                BorderLayout.CENTER
        );


        content.add(infoCard);


        // =====================================================
        // SCROLL
        // =====================================================

        JScrollPane scroll =
                new JScrollPane(
                        content
                );

        scroll.setBorder(null);

        scroll.getVerticalScrollBar()
                .setUnitIncrement(16);


        right.add(
                scroll,
                BorderLayout.CENTER
        );


        // =====================================================
        // BOTTOM BUTTON
        // =====================================================

        JPanel bottom =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        bottom.setBackground(Color.WHITE);


        JButton back =
                new JButton(
                        "← Back to Dashboard"
                );

        back.setBackground(
                new Color(
                        42,
                        100,
                        210
                )
        );

        back.setForeground(Color.WHITE);

        back.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        back.setFocusPainted(false);

        back.setBorder(
                new EmptyBorder(
                        10,
                        20,
                        10,
                        20
                )
        );


        back.addActionListener(
                e -> dispose()
        );


        bottom.add(back);


        right.add(
                bottom,
                BorderLayout.SOUTH
        );


        // =====================================================
        // SIDEBAR BUTTON ACTIONS
        // =====================================================

        dashboard.addActionListener(
                e -> {

                    dispose();

                    new StudentDashboard(
                            student
                    );
                }
        );


        submit.addActionListener(
                e -> {

                    dispose();

                    new SubmitRequest(
                            student
                    );
                }
        );


        requests.addActionListener(
                e -> {

                    dispose();

                    new MyRequests(
                            student
                    );
                }
        );


        notifications.addActionListener(
                e -> {

                    dispose();

                    new Notifications(
                            student.getStudentId()
                    );
                }
        );


        // =====================================================
        // ADD EVERYTHING
        // =====================================================

        main.add(
                sidebar,
                BorderLayout.WEST
        );

        main.add(
                right,
                BorderLayout.CENTER
        );


        add(main);
    }


    // =========================================================
    // PROFILE INFORMATION ROW
    // =========================================================

    private void addProfileRow(
            JPanel parent,
            String label,
            String value
    ) {

        JPanel row =
                new JPanel(
                        new BorderLayout(
                                20,
                                0
                        )
                );

        row.setBackground(Color.WHITE);

        row.setPreferredSize(
                new Dimension(
                        750,
                        48
                )
        );

        row.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        48
                )
        );


        JLabel labelText =
                new JLabel(label);

        labelText.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        labelText.setForeground(
                new Color(
                        50,
                        55,
                        65
                )
        );

        labelText.setPreferredSize(
                new Dimension(
                        180,
                        40
                )
        );


        JLabel valueText =
                new JLabel(value);

        valueText.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        valueText.setForeground(
                new Color(
                        70,
                        75,
                        85
                )
        );


        row.add(
                labelText,
                BorderLayout.WEST
        );

        row.add(
                valueText,
                BorderLayout.CENTER
        );


        parent.add(row);

        parent.add(
                Box.createVerticalStrut(5)
        );
    }


    // =========================================================
    // SIDEBAR BUTTON
    // =========================================================

    private JButton createMenuButton(
            String text,
            boolean selected
    ) {

        JButton button =
                new JButton(text);

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        button.setFocusPainted(false);

        button.setBorder(
                new EmptyBorder(
                        13,
                        15,
                        13,
                        15
                )
        );


        if (selected) {

            button.setBackground(
                    new Color(
                            42,
                            100,
                            210
                    )
            );

            button.setForeground(
                    Color.WHITE
            );

        } else {

            button.setBackground(
                    Color.WHITE
            );

            button.setForeground(
                    new Color(
                            70,
                            80,
                            100
                    )
            );
        }


        return button;
    }
}