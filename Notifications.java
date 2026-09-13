import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class Notifications extends JFrame {

    private int studentId;

    private JPanel notificationPanel;


    public Notifications(int studentId) {

        this.studentId = studentId;

        setTitle(
                "AIUB - Notifications"
        );

        setSize(
                1000,
                700
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        createUI();

        loadNotifications();

        setVisible(true);
    }


    // =========================================================
    // MAIN UI
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
                        1000,
                        70
                )
        );


        JLabel title =
                new JLabel(
                        "  Notifications"
                );

        title.setForeground(
                Color.WHITE
        );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );


        header.add(
                title,
                BorderLayout.WEST
        );


        // =====================================================
        // REFRESH BUTTON
        // =====================================================

        JButton refreshButton =
                new JButton(
                        "↻ Refresh"
                );

        refreshButton.setForeground(
                Color.WHITE
        );

        refreshButton.setBackground(
                new Color(
                        30,
                        80,
                        180
                )
        );

        refreshButton.setFocusPainted(
                false
        );

        refreshButton.setBorderPainted(
                false
        );

        refreshButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        refreshButton.setPreferredSize(
                new Dimension(
                        110,
                        40
                )
        );


        refreshButton.addActionListener(
                e -> loadNotifications()
        );


        JPanel rightHeader =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                15,
                                15
                        )
                );

        rightHeader.setOpaque(
                false
        );


        rightHeader.add(
                refreshButton
        );


        header.add(
                rightHeader,
                BorderLayout.EAST
        );


        main.add(
                header,
                BorderLayout.NORTH
        );


        // =====================================================
        // NOTIFICATION AREA
        // =====================================================

        notificationPanel =
                new JPanel();


        notificationPanel.setLayout(
                new BoxLayout(
                        notificationPanel,
                        BoxLayout.Y_AXIS
                )
        );


        notificationPanel.setBackground(
                new Color(
                        245,
                        247,
                        252
                )
        );


        notificationPanel.setBorder(
                new EmptyBorder(
                        25,
                        30,
                        25,
                        30
                )
        );


        JScrollPane scrollPane =
                new JScrollPane(
                        notificationPanel
                );


        scrollPane.setBorder(
                null
        );


        scrollPane
                .getVerticalScrollBar()
                .setUnitIncrement(
                        16
                );


        main.add(
                scrollPane,
                BorderLayout.CENTER
        );


        // =====================================================
        // BACK BUTTON
        // =====================================================

        JPanel bottom =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT
                        )
                );


        bottom.setBackground(
                Color.WHITE
        );


        JButton backButton =
                new JButton(
                        "← Back to Dashboard"
                );


        backButton.setBackground(
                new Color(
                        42,
                        100,
                        210
                )
        );


        backButton.setForeground(
                Color.WHITE
        );


        backButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );


        backButton.setFocusPainted(
                false
        );


        backButton.setBorderPainted(
                false
        );


        backButton.addActionListener(
                e -> dispose()
        );


        bottom.add(
                backButton
        );


        main.add(
                bottom,
                BorderLayout.SOUTH
        );


        add(main);
    }


    // =========================================================
    // LOAD NOTIFICATIONS
    // =========================================================

    private void loadNotifications() {

        notificationPanel.removeAll();


        /*
         * IMPORTANT
         *
         * This query gets:
         *
         * Student Request
         * Course
         * Section
         * Faculty
         * Make-Up Class
         * Room
         *
         * If a Make-Up Class is scheduled,
         * student will see the schedule notification.
         */


        String sql =

                "SELECT " +

                "r.Request_ID, " +

                "r.Request_Date, " +

                "r.Status, " +

                "r.Reason, " +

                "c.Course_Name, " +

                "sec.Section_Name, " +

                "f.Faculty_Name, " +

                "mc.Class_Date, " +

                "mc.Start_Time, " +

                "mc.End_Time, " +

                "cr.Room_No " +

                "FROM MakeUp_Request r " +

                "JOIN Course c " +

                "ON r.Course_ID = c.Course_ID " +

                "JOIN Section sec " +

                "ON r.Section_ID = sec.Section_ID " +

                "LEFT JOIN Faculty f " +

                "ON r.Faculty_ID = f.Faculty_ID " +

                "LEFT JOIN MakeUp_Class mc " +

                "ON r.Section_ID = mc.Section_ID " +

                "LEFT JOIN Classroom cr " +

                "ON mc.Room_ID = cr.Room_ID " +

                "WHERE r.Student_ID = ? " +

                "ORDER BY " +

                "CASE " +

                "WHEN mc.Class_Date IS NOT NULL " +

                "THEN 0 " +

                "ELSE 1 " +

                "END, " +

                "r.Request_Date DESC";


        try (

                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)

        ) {


            ps.setInt(
                    1,
                    studentId
            );


            ResultSet rs =
                    ps.executeQuery();


            boolean found = false;


            while (
                    rs.next()
            ) {

                found = true;


                // =================================================
                // REQUEST INFORMATION
                // =================================================

                int requestId =
                        rs.getInt(
                                "Request_ID"
                        );


                Date requestDate =
                        rs.getDate(
                                "Request_Date"
                        );


                String status =
                        rs.getString(
                                "Status"
                        );


                String reason =
                        rs.getString(
                                "Reason"
                        );


                String course =
                        rs.getString(
                                "Course_Name"
                        );


                String section =
                        rs.getString(
                                "Section_Name"
                        );


                String faculty =
                        rs.getString(
                                "Faculty_Name"
                        );


                // =================================================
                // CLASS INFORMATION
                // =================================================

                Date classDate =
                        rs.getDate(
                                "Class_Date"
                        );


                String startTime =
                        rs.getString(
                                "Start_Time"
                        );


                String endTime =
                        rs.getString(
                                "End_Time"
                        );


                String roomNo =
                        rs.getString(
                                "Room_No"
                        );


                // =================================================
                // SCHEDULED CLASS
                // =================================================

                if (
                        classDate != null
                ) {

                    addScheduledNotificationCard(

                            course,

                            section,

                            classDate,

                            startTime,

                            endTime,

                            roomNo,

                            faculty
                    );


                }

                else {

                    // =============================================
                    // NORMAL REQUEST NOTIFICATION
                    // =============================================

                    addRequestNotificationCard(

                            requestId,

                            requestDate,

                            status,

                            reason,

                            course,

                            section,

                            faculty
                    );
                }
            }


            if (!found) {

                addEmptyMessage();
            }


        } catch (SQLException e) {

            addErrorMessage(
                    e.getMessage()
            );
        }


        notificationPanel.revalidate();

        notificationPanel.repaint();
    }


    // =========================================================
    // SCHEDULED CLASS NOTIFICATION
    // =========================================================

    private void addScheduledNotificationCard(

            String course,

            String section,

            Date classDate,

            String startTime,

            String endTime,

            String roomNo,

            String faculty

    ) {


        JPanel card =
                new JPanel(
                        new BorderLayout(
                                15,
                                10
                        )
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
                        190
                )
        );


        // =====================================================
        // ICON
        // =====================================================

        JLabel icon =
                new JLabel(
                        "🔔"
                );


        icon.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        28
                )
        );


        card.add(
                icon,
                BorderLayout.WEST
        );


        // =====================================================
        // TEXT PANEL
        // =====================================================

        JPanel textPanel =
                new JPanel();


        textPanel.setLayout(
                new BoxLayout(
                        textPanel,
                        BoxLayout.Y_AXIS
                )
        );


        textPanel.setOpaque(
                false
        );


        // =====================================================
        // HEADING
        // =====================================================

        JLabel headingLabel =
                new JLabel(
                        "Make-Up Class Scheduled"
                );


        headingLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );


        headingLabel.setForeground(
                new Color(
                        30,
                        145,
                        75
                )
        );


        textPanel.add(
                headingLabel
        );


        textPanel.add(
                Box.createVerticalStrut(
                        8
                )
        );


        // =====================================================
        // COURSE
        // =====================================================

        JLabel courseLabel =
                new JLabel(
                        "Course: "
                        + course
                );


        courseLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );


        courseLabel.setForeground(
                new Color(
                        50,
                        55,
                        65
                )
        );


        textPanel.add(
                courseLabel
        );


        // =====================================================
        // SECTION
        // =====================================================

        JLabel sectionLabel =
                new JLabel(
                        "Section: "
                        + section
                );


        sectionLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );


        textPanel.add(
                sectionLabel
        );


        // =====================================================
        // DATE
        // =====================================================

        String dateText = "";


        if (
                classDate != null
        ) {

            dateText =
                    new SimpleDateFormat(
                            "dd MMM yyyy"
                    ).format(
                            classDate
                    );
        }


        JLabel dateLabel =
                new JLabel(
                        "Date: "
                        + dateText
                );


        dateLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );


        textPanel.add(
                dateLabel
        );


        // =====================================================
        // TIME
        // =====================================================

        String timeText =
                formatTime(
                        startTime
                )
                + " - "
                + formatTime(
                        endTime
                );


        JLabel timeLabel =
                new JLabel(
                        "Time: "
                        + timeText
                );


        timeLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );


        textPanel.add(
                timeLabel
        );


        // =====================================================
        // ROOM
        // =====================================================

        JLabel roomLabel =
                new JLabel(
                        "Room: "
                        + (
                                roomNo != null
                                ? roomNo
                                : "-"
                        )
                );


        roomLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );


        textPanel.add(
                roomLabel
        );


        // =====================================================
        // FACULTY
        // =====================================================

        if (
                faculty != null
        ) {

            JLabel facultyLabel =
                    new JLabel(
                            "Faculty: "
                            + faculty
                    );


            facultyLabel.setFont(
                    new Font(
                            "Arial",
                            Font.PLAIN,
                            13
                    )
            );


            facultyLabel.setForeground(
                    new Color(
                            100,
                            105,
                            115
                    )
            );


            textPanel.add(
                    facultyLabel
            );
        }


        card.add(
                textPanel,
                BorderLayout.CENTER
        );


        // =====================================================
        // SCHEDULED BADGE
        // =====================================================

        JLabel badge =
                new JLabel(
                        "SCHEDULED"
                );


        badge.setHorizontalAlignment(
                SwingConstants.CENTER
        );


        badge.setForeground(
                new Color(
                        25,
                        130,
                        70
                )
        );


        badge.setBackground(
                new Color(
                        225,
                        247,
                        233
                )
        );


        badge.setOpaque(
                true
        );


        badge.setBorder(
                BorderFactory.createEmptyBorder(
                        7,
                        12,
                        7,
                        12
                )
        );


        card.add(
                badge,
                BorderLayout.EAST
        );


        notificationPanel.add(
                card
        );


        notificationPanel.add(
                Box.createVerticalStrut(
                        15
                )
        );
    }


    // =========================================================
    // NORMAL REQUEST NOTIFICATION
    // =========================================================

    private void addRequestNotificationCard(

            int requestId,

            Date requestDate,

            String status,

            String reason,

            String course,

            String section,

            String faculty

    ) {


        JPanel card =
                new JPanel(
                        new BorderLayout(
                                15,
                                10
                        )
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
                        170
                )
        );


        // =====================================================
        // ICON
        // =====================================================

        JLabel icon =
                new JLabel();


        if (

                status != null

                &&

                status.equalsIgnoreCase(
                        "Approved"
                )

        ) {

            icon.setText(
                    "✓"
            );


            icon.setForeground(
                    new Color(
                            30,
                            160,
                            85
                    )
            );


        }

        else if (

                status != null

                &&

                status.equalsIgnoreCase(
                        "Rejected"
                )

        ) {

            icon.setText(
                    "✕"
            );


            icon.setForeground(
                    new Color(
                            220,
                            65,
                            65
                    )
            );


        }

        else if (

                status != null

                &&

                status.equalsIgnoreCase(
                        "Cancelled"
                )

        ) {

            icon.setText(
                    "↩"
            );


            icon.setForeground(
                    new Color(
                            120,
                            120,
                            120
                    )
            );


        }

        else {

            icon.setText(
                    "!"
            );


            icon.setForeground(
                    new Color(
                            230,
                            155,
                            30
                    )
            );
        }


        icon.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );


        card.add(
                icon,
                BorderLayout.WEST
        );


        // =====================================================
        // TEXT PANEL
        // =====================================================

        JPanel textPanel =
                new JPanel();


        textPanel.setLayout(
                new BoxLayout(
                        textPanel,
                        BoxLayout.Y_AXIS
                )
        );


        textPanel.setOpaque(
                false
        );


        // =====================================================
        // HEADING
        // =====================================================

        String heading;


        if (

                status != null

                &&

                status.equalsIgnoreCase(
                        "Approved"
                )

        ) {

            heading =
                    "Make-Up Request Approved";


        }

        else if (

                status != null

                &&

                status.equalsIgnoreCase(
                        "Rejected"
                )

        ) {

            heading =
                    "Make-Up Request Rejected";


        }

        else if (

                status != null

                &&

                status.equalsIgnoreCase(
                        "Cancelled"
                )

        ) {

            heading =
                    "Make-Up Request Cancelled";


        }

        else {

            heading =
                    "Make-Up Request Pending";
        }


        JLabel headingLabel =
                new JLabel(
                        heading
                );


        headingLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        17
                )
        );


        if (

                status != null

                &&

                status.equalsIgnoreCase(
                        "Approved"
                )

        ) {

            headingLabel.setForeground(
                    new Color(
                            30,
                            145,
                            75
                    )
            );


        }

        else if (

                status != null

                &&

                status.equalsIgnoreCase(
                        "Rejected"
                )

        ) {

            headingLabel.setForeground(
                    new Color(
                            205,
                            55,
                            55
                    )
            );


        }

        else if (

                status != null

                &&

                status.equalsIgnoreCase(
                        "Cancelled"
                )

        ) {

            headingLabel.setForeground(
                    new Color(
                            120,
                            120,
                            120
                    )
            );


        }

        else {

            headingLabel.setForeground(
                    new Color(
                            205,
                            135,
                            20
                    )
            );
        }


        textPanel.add(
                headingLabel
        );


        textPanel.add(
                Box.createVerticalStrut(
                        7
                )
        );


        // =====================================================
        // COURSE
        // =====================================================

        JLabel courseLabel =
                new JLabel(
                        "Course: "
                        + course
                );


        courseLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );


        courseLabel.setForeground(
                new Color(
                        50,
                        55,
                        65
                )
        );


        textPanel.add(
                courseLabel
        );


        // =====================================================
        // SECTION
        // =====================================================

        JLabel sectionLabel =
                new JLabel(
                        "Section: "
                        + section
                );


        sectionLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );


        textPanel.add(
                sectionLabel
        );


        // =====================================================
        // REASON
        // =====================================================

        if (
                reason != null
                &&
                !reason.trim().isEmpty()
        ) {

            JLabel reasonLabel =
                    new JLabel(
                            "Reason: "
                            + reason
                    );


            reasonLabel.setFont(
                    new Font(
                            "Arial",
                            Font.PLAIN,
                            13
                    )
            );


            reasonLabel.setForeground(
                    new Color(
                            100,
                            105,
                            115
                    )
            );


            textPanel.add(
                    reasonLabel
            );
        }


        // =====================================================
        // REQUEST DATE
        // =====================================================

        String dateText =
                "";


        if (
                requestDate != null
        ) {

            dateText =
                    new SimpleDateFormat(
                            "dd MMM yyyy"
                    ).format(
                            requestDate
                    );
        }


        JLabel dateLabel =
                new JLabel(
                        "Request Date: "
                        + dateText
                );


        dateLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );


        dateLabel.setForeground(
                new Color(
                        130,
                        135,
                        145
                )
        );


        textPanel.add(
                dateLabel
        );


        // =====================================================
        // FACULTY
        // =====================================================

        if (
                faculty != null
        ) {

            JLabel facultyLabel =
                    new JLabel(
                            "Faculty: "
                            + faculty
                    );


            facultyLabel.setFont(
                    new Font(
                            "Arial",
                            Font.PLAIN,
                            12
                    )
            );


            facultyLabel.setForeground(
                    new Color(
                            130,
                            135,
                            145
                    )
            );


            textPanel.add(
                    facultyLabel
            );
        }


        card.add(
                textPanel,
                BorderLayout.CENTER
        );


        // =====================================================
        // STATUS BADGE
        // =====================================================

        JLabel statusLabel =
                new JLabel(
                        status
                );


        statusLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );


        if (

                status != null

                &&

                status.equalsIgnoreCase(
                        "Approved"
                )

        ) {

            statusLabel.setForeground(
                    new Color(
                            25,
                            130,
                            70
                    )
            );


            statusLabel.setBackground(
                    new Color(
                            225,
                            247,
                            233
                    )
            );


        }

        else if (

                status != null

                &&

                status.equalsIgnoreCase(
                        "Rejected"
                )

        ) {

            statusLabel.setForeground(
                    new Color(
                            195,
                            45,
                            45
                    )
            );


            statusLabel.setBackground(
                    new Color(
                            255,
                            232,
                            232
                    )
            );


        }

        else if (

                status != null

                &&

                status.equalsIgnoreCase(
                        "Cancelled"
                )

        ) {

            statusLabel.setForeground(
                    new Color(
                            120,
                            120,
                            120
                    )
            );


            statusLabel.setBackground(
                    new Color(
                            235,
                            235,
                            235
                    )
            );


        }

        else {

            statusLabel.setForeground(
                    new Color(
                            195,
                            125,
                            15
                    )
            );


            statusLabel.setBackground(
                    new Color(
                            255,
                            244,
                            220
                    )
            );
        }


        statusLabel.setOpaque(
                true
        );


        statusLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        7,
                        15,
                        7,
                        15
                )
        );


        card.add(
                statusLabel,
                BorderLayout.EAST
        );


        notificationPanel.add(
                card
        );


        notificationPanel.add(
                Box.createVerticalStrut(
                        15
                )
        );
    }


    // =========================================================
    // FORMAT TIME
    // =========================================================

    private String formatTime(
            String time
    ) {

        if (
                time == null
                ||
                time.trim().isEmpty()
        ) {

            return "-";
        }


        try {

            /*
             * Oracle TIME-like values may come as:
             *
             * 10:00:00
             * 10:00
             *
             * We only display HH:mm.
             */


            if (
                    time.length() >= 5
            ) {

                return time.substring(
                        0,
                        5
                );
            }


        } catch (Exception e) {

            // Keep original value
        }


        return time;
    }


    // =========================================================
    // NO NOTIFICATION
    // =========================================================

    private void addEmptyMessage() {

        JPanel empty =
                new JPanel(
                        new BorderLayout()
                );


        empty.setBackground(
                Color.WHITE
        );


        empty.setBorder(
                BorderFactory.createEmptyBorder(
                        50,
                        20,
                        50,
                        20
                )
        );


        JLabel message =
                new JLabel(

                        "<html><center>"
                        + "<b>No Notifications</b><br><br>"
                        + "You don't have any make-up "
                        + "request notifications yet."
                        + "</center></html>"
                );


        message.setHorizontalAlignment(
                SwingConstants.CENTER
        );


        message.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );


        message.setForeground(
                new Color(
                        100,
                        105,
                        115
                )
        );


        empty.add(
                message,
                BorderLayout.CENTER
        );


        empty.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        180
                )
        );


        notificationPanel.add(
                empty
        );
    }


    // =========================================================
    // DATABASE ERROR
    // =========================================================

    private void addErrorMessage(
            String error
    ) {

        JLabel label =
                new JLabel(

                        "<html><b>Database Error:</b><br>"
                        + error
                        + "</html>"
                );


        label.setForeground(
                new Color(
                        200,
                        50,
                        50
                )
        );


        label.setBorder(
                new EmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );


        notificationPanel.add(
                label
        );
    }
}