import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class FacultyDashboard extends JFrame {

    private Faculty faculty;

    private JPanel contentPanel;

    private JLabel pendingCountLabel;
    private JLabel todayClassCountLabel;

    private final Color BLUE = new Color(42, 100, 210);
    private final Color LIGHT_BLUE = new Color(245, 247, 252);
    private final Color TEXT = new Color(35, 45, 60);
    private final Color MUTED = new Color(110, 115, 125);

    public FacultyDashboard(Faculty faculty) {

        this.faculty = faculty;

        setTitle("AIUB - Faculty Dashboard");
        setSize(1200, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createUI();
        loadDashboardData();

        setVisible(true);
    }

    // =========================================================
    // MAIN UI
    // =========================================================

    private void createUI() {

        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(LIGHT_BLUE);

        // =====================================================
        // SIDEBAR
        // =====================================================

        JPanel sidebar = new JPanel(new BorderLayout());
        sidebar.setPreferredSize(new Dimension(260, 750));
        sidebar.setBackground(Color.WHITE);

        // =====================================================
        // LOGO
        // =====================================================

        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.Y_AXIS));
        logoPanel.setBackground(Color.WHITE);
        logoPanel.setBorder(new EmptyBorder(25, 25, 20, 20));

        JLabel aiub = new JLabel("AIUB");
        aiub.setFont(new Font("Arial", Font.BOLD, 28));
        aiub.setForeground(BLUE);

        JLabel system = new JLabel("Make-Up System");
        system.setFont(new Font("Arial", Font.BOLD, 18));
        system.setForeground(TEXT);

        JLabel portal = new JLabel("Faculty Portal");
        portal.setFont(new Font("Arial", Font.PLAIN, 13));
        portal.setForeground(new Color(120, 125, 135));

        logoPanel.add(aiub);
        logoPanel.add(system);
        logoPanel.add(portal);

        sidebar.add(logoPanel, BorderLayout.NORTH);

        // =====================================================
        // MENU
        // =====================================================

        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setBackground(Color.WHITE);
        menu.setBorder(new EmptyBorder(20, 15, 15, 15));

        JButton dashboardButton =
                createMenuButton("Dashboard", true);

        JButton reviewButton =
                createMenuButton("Review Requests", false);

        JButton scheduleButton =
                createMenuButton("Schedule Class", false);

        JButton profileButton =
                createMenuButton("Profile", false);

        menu.add(dashboardButton);
        menu.add(Box.createVerticalStrut(8));

        menu.add(reviewButton);
        menu.add(Box.createVerticalStrut(8));

        menu.add(scheduleButton);
        menu.add(Box.createVerticalStrut(8));

        menu.add(profileButton);

        sidebar.add(menu, BorderLayout.CENTER);

        // =====================================================
        // LOGOUT
        // =====================================================

        JPanel logoutPanel = new JPanel(new BorderLayout());
        logoutPanel.setBackground(Color.WHITE);

        logoutPanel.setBorder(
                BorderFactory.createMatteBorder(
                        1, 0, 0, 0,
                        new Color(230, 233, 240)
                )
        );

        JButton logout = new JButton("Logout");

        logout.setHorizontalAlignment(SwingConstants.LEFT);
        logout.setFont(new Font("Arial", Font.BOLD, 15));
        logout.setForeground(new Color(220, 70, 70));
        logout.setBackground(new Color(255, 245, 245));
        logout.setBorder(new EmptyBorder(18, 25, 18, 25));
        logout.setFocusPainted(false);

        logout.addActionListener(e -> {

            dispose();
            new Login();

        });

        logoutPanel.add(logout, BorderLayout.CENTER);

        sidebar.add(logoutPanel, BorderLayout.SOUTH);

        // =====================================================
        // RIGHT SIDE
        // =====================================================

        JPanel right = new JPanel(new BorderLayout());
        right.setBackground(LIGHT_BLUE);

        // =====================================================
        // HEADER
        // =====================================================

        JPanel header = new JPanel(new BorderLayout());

        header.setBackground(BLUE);
        header.setPreferredSize(new Dimension(940, 80));
        header.setBorder(new EmptyBorder(15, 30, 15, 30));

        JLabel title = new JLabel("Faculty Dashboard");

        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);

        header.add(title, BorderLayout.WEST);

        JPanel welcomePanel =
                new JPanel(new FlowLayout(
                        FlowLayout.RIGHT,
                        10,
                        5
                ));

        welcomePanel.setOpaque(false);

        JLabel welcome =
                new JLabel(
                        "Welcome, "
                                + faculty.getFacultyName()
                );

        welcome.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        welcome.setForeground(Color.WHITE);

        JLabel userCircle = new JLabel("F");

        userCircle.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        userCircle.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        userCircle.setForeground(Color.WHITE);

        userCircle.setBackground(
                new Color(70, 125, 225)
        );

        userCircle.setOpaque(true);

        userCircle.setPreferredSize(
                new Dimension(40, 40)
        );

        welcomePanel.add(welcome);
        welcomePanel.add(userCircle);

        header.add(
                welcomePanel,
                BorderLayout.EAST
        );

        right.add(header, BorderLayout.NORTH);

        // =====================================================
        // CONTENT
        // =====================================================

        contentPanel = new JPanel();

        contentPanel.setLayout(
                new BoxLayout(
                        contentPanel,
                        BoxLayout.Y_AXIS
                )
        );

        contentPanel.setBackground(LIGHT_BLUE);

        contentPanel.setBorder(
                new EmptyBorder(
                        28,
                        30,
                        30,
                        30
                )
        );

        createDashboardCards();

        JScrollPane scroll =
                new JScrollPane(contentPanel);

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

        dashboardButton.addActionListener(
                e -> showDashboard()
        );

        reviewButton.addActionListener(
                e -> showReviewRequests()
        );

        scheduleButton.addActionListener(
                e -> showScheduleClass()
        );

        profileButton.addActionListener(
                e -> {

                    dispose();

                    new FacultyProfile(faculty);

                }
        );

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
    // DASHBOARD CARDS
    // =========================================================

    private void createDashboardCards() {

        contentPanel.removeAll();

        // =====================================================
        // PENDING REQUESTS
        // =====================================================

        JPanel pendingCard = createLargeCard();

        JLabel pendingIcon =
                createIconBox(
                        "P",
                        BLUE
                );

        pendingCountLabel =
                new JLabel("0");

        pendingCountLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        25
                )
        );

        pendingCountLabel.setForeground(TEXT);

        JLabel pendingTitle =
                new JLabel("Pending Requests");

        pendingTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        pendingTitle.setForeground(TEXT);

        JLabel pendingDescription =
                new JLabel(
                        "Requests waiting for your review"
                );

        pendingDescription.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        pendingDescription.setForeground(MUTED);

        JPanel pendingText = new JPanel();

        pendingText.setLayout(
                new BoxLayout(
                        pendingText,
                        BoxLayout.Y_AXIS
                )
        );

        pendingText.setOpaque(false);

        pendingText.add(pendingCountLabel);
        pendingText.add(pendingTitle);
        pendingText.add(pendingDescription);

        pendingCard.add(
                pendingIcon,
                BorderLayout.WEST
        );

        pendingCard.add(
                pendingText,
                BorderLayout.CENTER
        );

        contentPanel.add(pendingCard);

        contentPanel.add(
                Box.createVerticalStrut(15)
        );

        // =====================================================
        // TODAY'S CLASSES
        // =====================================================

        JPanel classCard = createLargeCard();

        JLabel classIcon =
                createIconBox(
                        "C",
                        new Color(35, 180, 105)
                );

        todayClassCountLabel =
                new JLabel("0");

        todayClassCountLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        25
                )
        );

        todayClassCountLabel.setForeground(TEXT);

        JLabel classTitle =
                new JLabel("Today's Classes");

        classTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        classTitle.setForeground(TEXT);

        JLabel classDescription =
                new JLabel(
                        "Make-up classes scheduled today"
                );

        classDescription.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        classDescription.setForeground(MUTED);

        JPanel classText = new JPanel();

        classText.setLayout(
                new BoxLayout(
                        classText,
                        BoxLayout.Y_AXIS
                )
        );

        classText.setOpaque(false);

        classText.add(todayClassCountLabel);
        classText.add(classTitle);
        classText.add(classDescription);

        classCard.add(
                classIcon,
                BorderLayout.WEST
        );

        classCard.add(
                classText,
                BorderLayout.CENTER
        );

        contentPanel.add(classCard);

        contentPanel.add(
                Box.createVerticalStrut(20)
        );

        // =====================================================
        // REVIEW REQUESTS
        // =====================================================

        JButton reviewCard =
                createFeatureCard(
                        "Review Requests",
                        "View and approve or reject student requests",
                        BLUE
                );

        reviewCard.addActionListener(
                e -> showReviewRequests()
        );

        contentPanel.add(reviewCard);

        contentPanel.add(
                Box.createVerticalStrut(15)
        );

        // =====================================================
        // SCHEDULE CLASS
        // =====================================================

        JButton scheduleCard =
                createFeatureCard(
                        "Schedule Class",
                        "Schedule a make-up class using Oracle database",
                        new Color(40, 165, 220)
                );

        scheduleCard.addActionListener(
                e -> showScheduleClass()
        );

        contentPanel.add(scheduleCard);

        contentPanel.add(
                Box.createVerticalStrut(15)
        );

        // =====================================================
        // LOGOUT
        // =====================================================

        JButton logoutCard =
                createFeatureCard(
                        "Logout",
                        "Sign out from the system",
                        new Color(220, 80, 80)
                );

        logoutCard.setBackground(
                new Color(255, 235, 235)
        );

        logoutCard.addActionListener(
                e -> {

                    dispose();
                    new Login();

                }
        );

        contentPanel.add(logoutCard);

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // =========================================================
    // LARGE CARD
    // =========================================================

    private JPanel createLargeCard() {

        JPanel card =
                new JPanel(
                        new BorderLayout(
                                15,
                                10
                        )
                );

        card.setBackground(Color.WHITE);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(225, 230, 240)
                        ),
                        new EmptyBorder(
                                18,
                                20,
                                18,
                                20
                        )
                )
        );

        card.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        105
                )
        );

        return card;
    }

    // =========================================================
    // ICON
    // =========================================================

    private JLabel createIconBox(
            String text,
            Color color
    ) {

        JLabel icon =
                new JLabel(text);

        icon.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        icon.setVerticalAlignment(
                SwingConstants.CENTER
        );

        icon.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        icon.setForeground(Color.WHITE);
        icon.setBackground(color);
        icon.setOpaque(true);

        icon.setPreferredSize(
                new Dimension(
                        50,
                        50
                )
        );

        return icon;
    }

    // =========================================================
    // FEATURE CARD
    // =========================================================

    private JButton createFeatureCard(
            String title,
            String description,
            Color iconColor
    ) {

        JButton button =
                new JButton();

        button.setLayout(
                new BorderLayout(
                        15,
                        5
                )
        );

        button.setBackground(Color.WHITE);

        button.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(225, 230, 240)
                        ),
                        new EmptyBorder(
                                18,
                                20,
                                18,
                                20
                        )
                )
        );

        button.setFocusPainted(false);

        JLabel icon =
                createIconBox(
                        "✓",
                        iconColor
                );

        JPanel text = new JPanel();

        text.setLayout(
                new BoxLayout(
                        text,
                        BoxLayout.Y_AXIS
                )
        );

        text.setOpaque(false);

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        titleLabel.setForeground(TEXT);

        JLabel descriptionLabel =
                new JLabel(description);

        descriptionLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        descriptionLabel.setForeground(MUTED);

        text.add(titleLabel);

        text.add(
                Box.createVerticalStrut(5)
        );

        text.add(descriptionLabel);

        JLabel arrow =
                new JLabel(">");

        arrow.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        arrow.setForeground(
                new Color(170, 175, 185)
        );

        button.add(
                icon,
                BorderLayout.WEST
        );

        button.add(
                text,
                BorderLayout.CENTER
        );

        button.add(
                arrow,
                BorderLayout.EAST
        );

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        90
                )
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

            button.setBackground(BLUE);
            button.setForeground(Color.WHITE);

        } else {

            button.setBackground(Color.WHITE);

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
    // DATABASE DATA
    // =========================================================

    private void loadDashboardData() {

        // =====================================================
        // PENDING REQUEST COUNT
        // =====================================================

        String pendingSQL =
                "SELECT COUNT(*) "
                        + "FROM MAKEUP_REQUEST "
                        + "WHERE FACULTY_ID = ? "
                        + "AND STATUS = 'Pending'";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(pendingSQL)
        ) {

            ps.setInt(
                    1,
                    faculty.getFacultyId()
            );

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                pendingCountLabel.setText(
                        String.valueOf(
                                rs.getInt(1)
                        )
                );
            }

        } catch (SQLException e) {

            pendingCountLabel.setText("0");

            System.out.println(
                    "Pending request error: "
                            + e.getMessage()
            );
        }

        // =====================================================
        // TODAY'S CLASS COUNT
        // =====================================================

        /*
         * MAKEUP_CLASS.CLASS_DATE is an Oracle DATE.
         *
         * TRUNC(CLASS_DATE) = TRUNC(SYSDATE)
         * means only today's date is compared.
         */

        String todayClassSQL =
                "SELECT COUNT(*) "
                        + "FROM MAKEUP_CLASS "
                        + "WHERE FACULTY_ID = ? "
                        + "AND TRUNC(CLASS_DATE) = TRUNC(SYSDATE)";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(
                                todayClassSQL
                        )
        ) {

            ps.setInt(
                    1,
                    faculty.getFacultyId()
            );

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                todayClassCountLabel.setText(
                        String.valueOf(
                                rs.getInt(1)
                        )
                );
            }

        } catch (SQLException e) {

            todayClassCountLabel.setText("0");

            System.out.println(
                    "Today's class error: "
                            + e.getMessage()
            );
        }
    }

    // =========================================================
    // SHOW DASHBOARD
    // =========================================================

    private void showDashboard() {

        createDashboardCards();
        loadDashboardData();
    }

    // =========================================================
    // REVIEW REQUESTS
    // =========================================================

    private void showReviewRequests() {

        contentPanel.removeAll();

        JLabel title =
                new JLabel(
                        "Pending Make-Up Requests"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        title.setForeground(TEXT);

        contentPanel.add(title);

        contentPanel.add(
                Box.createVerticalStrut(20)
        );

        String[] columns = {

                "Request ID",
                "Student ID",
                "Course",
                "Section",
                "Reason",
                "Status"

        };

        DefaultTableModel model =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {
                        return false;
                    }
                };

        JTable table =
                new JTable(model);

        table.setRowHeight(40);

        table.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        table.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                14
                        )
                );

        table.getTableHeader()
                .setBackground(BLUE);

        table.getTableHeader()
                .setForeground(Color.WHITE);

        JScrollPane tableScroll =
                new JScrollPane(table);

        tableScroll.setPreferredSize(
                new Dimension(
                        900,
                        300
                )
        );

        contentPanel.add(tableScroll);

        contentPanel.add(
                Box.createVerticalStrut(20)
        );

        JPanel actionPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT
                        )
                );

        actionPanel.setOpaque(false);

        JButton approve =
                new JButton("APPROVE");

        approve.setBackground(
                new Color(30, 165, 85)
        );

        approve.setForeground(Color.WHITE);

        approve.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        approve.setFocusPainted(false);

        JButton reject =
                new JButton("REJECT");

        reject.setBackground(
                new Color(220, 65, 65)
        );

        reject.setForeground(Color.WHITE);

        reject.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        reject.setFocusPainted(false);

        actionPanel.add(approve);
        actionPanel.add(reject);

        contentPanel.add(actionPanel);

        loadRequests(model);

        approve.addActionListener(
                e -> updateRequestStatus(
                        table,
                        model,
                        "Approved"
                )
        );

        reject.addActionListener(
                e -> updateRequestStatus(
                        table,
                        model,
                        "Rejected"
                )
        );

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // =========================================================
    // LOAD REQUESTS
    // =========================================================

    private void loadRequests(
            DefaultTableModel model
    ) {

        String sql =
                "SELECT r.REQUEST_ID, "
                        + "r.STUDENT_ID, "
                        + "c.COURSE_NAME, "
                        + "r.SECTION_ID, "
                        + "r.REASON, "
                        + "r.STATUS "
                        + "FROM MAKEUP_REQUEST r "
                        + "JOIN COURSE c "
                        + "ON r.COURSE_ID = c.COURSE_ID "
                        + "WHERE r.FACULTY_ID = ? "
                        + "AND r.STATUS = 'Pending' "
                        + "ORDER BY r.REQUEST_ID";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    faculty.getFacultyId()
            );

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                model.addRow(
                        new Object[]{

                                rs.getInt("REQUEST_ID"),

                                rs.getInt("STUDENT_ID"),

                                rs.getString("COURSE_NAME"),

                                rs.getInt("SECTION_ID"),

                                rs.getString("REASON"),

                                rs.getString("STATUS")

                        }
                );
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load requests.\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // UPDATE REQUEST
    // =========================================================

    private void updateRequestStatus(
            JTable table,
            DefaultTableModel model,
            String newStatus
    ) {

        int row =
                table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a request first.",
                    "Select Request",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int requestId =
                Integer.parseInt(
                        model.getValueAt(
                                row,
                                0
                        ).toString()
                );

        String sql =
                "UPDATE MAKEUP_REQUEST "
                        + "SET STATUS = ? "
                        + "WHERE REQUEST_ID = ?";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    newStatus
            );

            ps.setInt(
                    2,
                    requestId
            );

            int result =
                    ps.executeUpdate();

            if (result > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Request "
                                + newStatus
                                + " successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                showReviewRequests();
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to update request.\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // SCHEDULE CLASS
    // =========================================================

    private void showScheduleClass() {

        dispose();

        new ScheduleClass(faculty);
    }
}