import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StudentDashboard extends JFrame {

    private Student student;

    public StudentDashboard(Student student) {

        this.student = student;

        setTitle("AIUB - Student Dashboard");

        setSize(1200, 750);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        createUI();

        setVisible(true);
    }

    // =========================================================
    // CREATE UI
    // =========================================================

    private void createUI() {

        JPanel main =
                new JPanel(
                        new BorderLayout()
                );

        main.setBackground(
                new Color(
                        245,
                        247,
                        252
                )
        );

        // =====================================================
        // SIDEBAR
        // =====================================================

        JPanel sidebar =
                new JPanel(
                        new BorderLayout()
                );

        sidebar.setPreferredSize(
                new Dimension(
                        260,
                        750
                )
        );

        sidebar.setBackground(
                Color.WHITE
        );

        // =====================================================
        // LOGO
        // =====================================================

        JPanel logoPanel =
                new JPanel();

        logoPanel.setLayout(
                new BoxLayout(
                        logoPanel,
                        BoxLayout.Y_AXIS
                )
        );

        logoPanel.setBackground(
                Color.WHITE
        );

        logoPanel.setBorder(
                new EmptyBorder(
                        25,
                        25,
                        20,
                        20
                )
        );

        JLabel aiub =
                new JLabel("AIUB");

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
                new JLabel(
                        "Make-Up System"
                );

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
                new JLabel(
                        "Student Portal"
                );

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
        // MENU
        // =====================================================

        JPanel menu =
                new JPanel();

        menu.setLayout(
                new BoxLayout(
                        menu,
                        BoxLayout.Y_AXIS
                )
        );

        menu.setBackground(
                Color.WHITE
        );

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
                        true
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
                        false
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

        logoutPanel.setBackground(
                Color.WHITE
        );

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
                new JButton(
                        "⇥  Logout"
                );

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

        logout.setBackground(
                Color.WHITE
        );

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
        // HEADER
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
                        940,
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

        JPanel headerText =
                new JPanel();

        headerText.setLayout(
                new BoxLayout(
                        headerText,
                        BoxLayout.Y_AXIS
                )
        );

        headerText.setOpaque(false);

        JLabel welcome =
                new JLabel(
                        "Welcome, "
                                + student.getStudentName()
                );

        welcome.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        welcome.setForeground(
                Color.WHITE
        );

        headerText.add(welcome);

        headerText.add(
                Box.createVerticalStrut(4)
        );

        JLabel subtitle =
                new JLabel(
                        "Student Portal"
                );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        subtitle.setForeground(
                new Color(
                        225,
                        235,
                        255
                )
        );

        headerText.add(subtitle);

        header.add(
                headerText,
                BorderLayout.WEST
        );

        JLabel id =
                new JLabel(
                        "Student ID: "
                                + student.getStudentId()
                );

        id.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        id.setForeground(
                Color.WHITE
        );

        header.add(
                id,
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
                        30,
                        30,
                        30
                )
        );

        // =====================================================
        // WELCOME CARD
        // =====================================================

        JPanel welcomeCard =
                new JPanel(
                        new BorderLayout()
                );

        welcomeCard.setBackground(
                Color.WHITE
        );

        welcomeCard.setBorder(
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
                                25,
                                25,
                                25
                        )
                )
        );

        JLabel dashboardTitle =
                new JLabel(
                        "Student Dashboard"
                );

        dashboardTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        dashboardTitle.setForeground(
                new Color(
                        35,
                        45,
                        60
                )
        );

        JLabel message =
                new JLabel(
                        "Manage your make-up class requests easily."
                );

        message.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        message.setForeground(
                new Color(
                        105,
                        110,
                        120
                )
        );

        JPanel welcomeText =
                new JPanel();

        welcomeText.setLayout(
                new BoxLayout(
                        welcomeText,
                        BoxLayout.Y_AXIS
                )
        );

        welcomeText.setOpaque(false);

        welcomeText.add(
                dashboardTitle
        );

        welcomeText.add(
                Box.createVerticalStrut(7)
        );

        welcomeText.add(
                message
        );

        welcomeCard.add(
                welcomeText,
                BorderLayout.WEST
        );

        content.add(
                welcomeCard
        );

        content.add(
                Box.createVerticalStrut(20)
        );

        // =====================================================
        // INFO CARDS
        // =====================================================

        JPanel cards =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                18,
                                18
                        )
                );

        cards.setBackground(
                new Color(
                        245,
                        247,
                        252
                )
        );

        cards.add(
                createInfoCard(
                        "Student ID",
                        String.valueOf(
                                student.getStudentId()
                        )
                )
        );

        cards.add(
                createInfoCard(
                        "Program",
                        student.getProgram()
                )
        );

        cards.add(
                createInfoCard(
                        "Batch",
                        student.getBatch()
                )
        );

        content.add(cards);

        content.add(
                Box.createVerticalStrut(25)
        );

        // =====================================================
        // QUICK ACTIONS
        // =====================================================

        JLabel quick =
                new JLabel(
                        "Quick Actions"
                );

        quick.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        quick.setForeground(
                new Color(
                        40,
                        50,
                        65
                )
        );

        content.add(quick);

        content.add(
                Box.createVerticalStrut(15)
        );

        JPanel actions =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                18,
                                18
                        )
                );

        actions.setBackground(
                new Color(
                        245,
                        247,
                        252
                )
        );

        JButton submitAction =
                createActionButton(
                        "Submit Request",
                        "Submit a new make-up request"
                );

        JButton requestAction =
                createActionButton(
                        "My Requests",
                        "View your request status"
                );

        JButton notificationAction =
                createActionButton(
                        "Notifications",
                        "Check your latest updates"
                );

        actions.add(
                submitAction
        );

        actions.add(
                requestAction
        );

        actions.add(
                notificationAction
        );

        content.add(actions);

        JScrollPane scroll =
                new JScrollPane(content);

        scroll.setBorder(null);

        scroll.getVerticalScrollBar()
                .setUnitIncrement(16);

        right.add(
                scroll,
                BorderLayout.CENTER
        );

        // =====================================================
        // BUTTON ACTIONS
        // =====================================================

        submit.addActionListener(
                e -> openSubmitRequest()
        );

        requests.addActionListener(
                e -> openMyRequests()
        );

        notifications.addActionListener(
                e -> openNotifications()
        );

        profile.addActionListener(
                e -> openProfile()
        );

        submitAction.addActionListener(
                e -> openSubmitRequest()
        );

        requestAction.addActionListener(
                e -> openMyRequests()
        );

        notificationAction.addActionListener(
                e -> openNotifications()
        );

        // =====================================================
        // MAIN
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
    // INFO CARD
    // =========================================================

    private JPanel createInfoCard(
            String title,
            String value
    ) {

        JPanel card =
                new JPanel(
                        new BorderLayout()
                );

        card.setBackground(
                Color.WHITE
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(
                                        225,
                                        230,
                                        240
                                )
                        ),

                        new EmptyBorder(
                                20,
                                20,
                                20,
                                20
                        )
                )
        );

        JLabel t =
                new JLabel(title);

        t.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        t.setForeground(
                new Color(
                        105,
                        110,
                        120
                )
        );

        JLabel v =
                new JLabel(value);

        v.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        23
                )
        );

        v.setForeground(
                new Color(
                        42,
                        100,
                        210
                )
        );

        JPanel text =
                new JPanel();

        text.setLayout(
                new BoxLayout(
                        text,
                        BoxLayout.Y_AXIS
                )
        );

        text.setOpaque(false);

        text.add(t);

        text.add(
                Box.createVerticalStrut(10)
        );

        text.add(v);

        card.add(
                text,
                BorderLayout.CENTER
        );

        return card;
    }

    // =========================================================
    // ACTION CARD
    // =========================================================

    private JButton createActionButton(
            String title,
            String description
    ) {

        JButton button =
                new JButton();

        button.setLayout(
                new BorderLayout()
        );

        button.setBackground(
                Color.WHITE
        );

        button.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(
                                        225,
                                        230,
                                        240
                                )
                        ),

                        new EmptyBorder(
                                20,
                                20,
                                20,
                                20
                        )
                )
        );

        button.setFocusPainted(false);

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        17
                )
        );

        titleLabel.setForeground(
                new Color(
                        42,
                        100,
                        210
                )
        );

        JLabel descriptionLabel =
                new JLabel(
                        "<html>"
                                + description
                                + "</html>"
                );

        descriptionLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        descriptionLabel.setForeground(
                new Color(
                        110,
                        115,
                        125
                )
        );

        JPanel panel =
                new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.setOpaque(false);

        panel.add(titleLabel);

        panel.add(
                Box.createVerticalStrut(8)
        );

        panel.add(descriptionLabel);

        button.add(
                panel,
                BorderLayout.CENTER
        );

        return button;
    }

    // =========================================================
    // MENU BUTTON
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

    // =========================================================
    // SUBMIT REQUEST
    // =========================================================

    private void openSubmitRequest() {

        try {

            new SubmitRequest(student);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to open Submit Request.\n"
                            + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // MY REQUESTS
    // =========================================================

    private void openMyRequests() {

        try {

            // IMPORTANT:
            // MyRequests takes Student object,
            // not int.

            new MyRequests(student);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to open My Requests.\n"
                            + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // NOTIFICATIONS
    // =========================================================

    private void openNotifications() {

        try {

            new Notifications(
                    student.getStudentId()
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to open Notifications.\n"
                            + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // PROFILE
    // =========================================================

    private void openProfile() {

        try {

            new StudentProfile(student);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to open Profile.\n"
                            + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}