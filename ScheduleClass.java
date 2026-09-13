import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScheduleClass extends JFrame {

    private final Faculty faculty;

    private JTextField dateField;
    private JComboBox<String> startTimeBox;
    private JComboBox<String> endTimeBox;
    private JComboBox<SectionItem> sectionBox;
    private JComboBox<ClassroomItem> roomBox;

    // UI Colors
    private static final Color BLUE = new Color(42, 99, 211);
    private static final Color DARK_BLUE = new Color(30, 73, 160);
    private static final Color LIGHT_BG = new Color(244, 247, 252);
    private static final Color TEXT = new Color(35, 45, 65);
    private static final Color GREEN = new Color(31, 174, 105);

    // Minimum students required for a make-up class
    private static final int MIN_STUDENTS_REQUIRED = 15;

    public ScheduleClass(Faculty faculty) {

        this.faculty = faculty;

        setTitle("AIUB - Schedule Make-Up Class");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        createUI();

        loadSections();
        loadClassrooms();

        setVisible(true);
    }

    // ============================================================
    // CREATE UI
    // ============================================================

    private void createUI() {

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(LIGHT_BG);

        // ========================================================
        // HEADER
        // ========================================================

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(BLUE);
        header.setBorder(
                new EmptyBorder(18, 28, 18, 28)
        );

        JLabel title = new JLabel(
                "Schedule Make-Up Class"
        );

        title.setForeground(Color.WHITE);
        title.setFont(
                new Font("Segoe UI", Font.BOLD, 25)
        );

        JLabel facultyLabel = new JLabel(
                "Faculty: " + faculty.getFacultyName()
        );

        facultyLabel.setForeground(Color.WHITE);
        facultyLabel.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        header.add(title, BorderLayout.WEST);
        header.add(facultyLabel, BorderLayout.EAST);

        mainPanel.add(header, BorderLayout.NORTH);

        // ========================================================
        // CENTER
        // ========================================================

        JPanel center = new JPanel(
                new GridBagLayout()
        );

        center.setBackground(LIGHT_BG);

        center.setBorder(
                new EmptyBorder(
                        30,
                        45,
                        25,
                        45
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(10, 10, 10, 10);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1;
        gbc.weighty = 1;

        // ========================================================
        // CARD
        // ========================================================

        JPanel card = new JPanel(
                new GridBagLayout()
        );

        card.setBackground(Color.WHITE);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 226, 238)
                        ),
                        new EmptyBorder(
                                25,
                                30,
                                25,
                                30
                        )
                )
        );

        GridBagConstraints c =
                new GridBagConstraints();

        c.insets =
                new Insets(10, 10, 10, 10);

        c.fill =
                GridBagConstraints.HORIZONTAL;

        c.weightx = 1;

        // ========================================================
        // TITLE
        // ========================================================

        JLabel sectionTitle =
                new JLabel(
                        "Class Schedule Information"
                );

        sectionTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );

        sectionTitle.setForeground(TEXT);

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;

        card.add(
                sectionTitle,
                c
        );

        // ========================================================
        // SUBTITLE
        // ========================================================

        JLabel subtitle =
                new JLabel(
                        "Enter the date and class details to schedule a make-up class."
                );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        subtitle.setForeground(
                new Color(100, 110, 125)
        );

        c.gridy = 1;

        card.add(
                subtitle,
                c
        );

        c.gridwidth = 1;

        // ========================================================
        // CLASS DATE
        // ========================================================

        JLabel dateLabel =
                createLabel("Class Date");

        c.gridx = 0;
        c.gridy = 2;

        card.add(
                dateLabel,
                c
        );

        dateField =
                new JTextField();

        dateField.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        dateField.setPreferredSize(
                new Dimension(
                        300,
                        42
                )
        );

        dateField.setToolTipText(
                "Format: DD-MM-YYYY"
        );

        // Today's date
        dateField.setText(
                new SimpleDateFormat(
                        "dd-MM-yyyy"
                ).format(
                        new Date()
                )
        );

        styleTextField(
                dateField
        );

        c.gridx = 1;

        card.add(
                dateField,
                c
        );

        // ========================================================
        // START TIME
        // ========================================================

        JLabel startLabel =
                createLabel("Start Time");

        c.gridx = 0;
        c.gridy = 3;

        card.add(
                startLabel,
                c
        );

        startTimeBox =
                new JComboBox<>(
                        new String[]{
                                "08:00 AM",
                                "08:30 AM",
                                "09:00 AM",
                                "09:30 AM",
                                "10:00 AM",
                                "10:30 AM",
                                "11:00 AM",
                                "11:30 AM",
                                "12:00 PM",
                                "12:30 PM",
                                "01:00 PM",
                                "01:30 PM",
                                "02:00 PM",
                                "02:30 PM",
                                "03:00 PM",
                                "03:30 PM",
                                "04:00 PM",
                                "04:30 PM",
                                "05:00 PM",
                                "05:30 PM",
                                "06:00 PM"
                        }
                );

        styleComboBox(
                startTimeBox
        );

        c.gridx = 1;

        card.add(
                startTimeBox,
                c
        );

        // ========================================================
        // END TIME
        // ========================================================

        JLabel endLabel =
                createLabel("End Time");

        c.gridx = 0;
        c.gridy = 4;

        card.add(
                endLabel,
                c
        );

        endTimeBox =
                new JComboBox<>(
                        new String[]{
                                "09:00 AM",
                                "09:30 AM",
                                "10:00 AM",
                                "10:30 AM",
                                "11:00 AM",
                                "11:30 AM",
                                "12:00 PM",
                                "12:30 PM",
                                "01:00 PM",
                                "01:30 PM",
                                "02:00 PM",
                                "02:30 PM",
                                "03:00 PM",
                                "03:30 PM",
                                "04:00 PM",
                                "04:30 PM",
                                "05:00 PM",
                                "05:30 PM",
                                "06:00 PM",
                                "06:30 PM",
                                "07:00 PM"
                        }
                );

        styleComboBox(
                endTimeBox
        );

        c.gridx = 1;

        card.add(
                endTimeBox,
                c
        );

        // ========================================================
        // SECTION
        // ========================================================

        JLabel sectionLabel =
                createLabel("Section");

        c.gridx = 0;
        c.gridy = 5;

        card.add(
                sectionLabel,
                c
        );

        sectionBox =
                new JComboBox<>();

        styleComboBox(
                sectionBox
        );

        c.gridx = 1;

        card.add(
                sectionBox,
                c
        );

        // ========================================================
        // CLASSROOM
        // ========================================================

        JLabel roomLabel =
                createLabel("Classroom");

        c.gridx = 0;
        c.gridy = 6;

        card.add(
                roomLabel,
                c
        );

        roomBox =
                new JComboBox<>();

        styleComboBox(
                roomBox
        );

        c.gridx = 1;

        card.add(
                roomBox,
                c
        );

        // ========================================================
        // INFORMATION PANEL
        // ========================================================

        JPanel infoPanel =
                new JPanel(
                        new BorderLayout()
                );

        infoPanel.setBackground(
                new Color(
                        238,
                        245,
                        255
                )
        );

        infoPanel.setBorder(
                new EmptyBorder(
                        12,
                        15,
                        12,
                        15
                )
        );

        JLabel info =
                new JLabel(
                        "<html>"
                                + "<b>Note:</b> "
                                + "At least "
                                + MIN_STUDENTS_REQUIRED
                                + " different students must request "
                                + "the same course and section before "
                                + "a make-up class can be scheduled."
                                + "</html>"
                );

        info.setForeground(
                DARK_BLUE
        );

        info.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        infoPanel.add(
                info,
                BorderLayout.CENTER
        );

        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 2;

        card.add(
                infoPanel,
                c
        );

        // ========================================================
        // BUTTONS
        // ========================================================

        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                15,
                                0
                        )
                );

        buttonPanel.setBackground(
                Color.WHITE
        );

        JButton scheduleButton =
                createButton(
                        "✓  Schedule Class",
                        GREEN
                );

        JButton cancelButton =
                createButton(
                        "←  Back",
                        new Color(
                                110,
                                120,
                                135
                        )
                );

        scheduleButton.addActionListener(
                e -> scheduleClass()
        );

        cancelButton.addActionListener(
                e -> dispose()
        );

        buttonPanel.add(
                scheduleButton
        );

        buttonPanel.add(
                cancelButton
        );

        c.gridx = 0;
        c.gridy = 8;
        c.gridwidth = 2;

        c.insets =
                new Insets(
                        20,
                        10,
                        5,
                        10
                );

        card.add(
                buttonPanel,
                c
        );

        // ========================================================
        // ADD CARD
        // ========================================================

        gbc.gridx = 0;
        gbc.gridy = 0;

        center.add(
                card,
                gbc
        );

        mainPanel.add(
                center,
                BorderLayout.CENTER
        );

        setContentPane(
                mainPanel
        );
    }

    // ============================================================
    // LOAD SECTIONS
    // ============================================================

    private void loadSections() {

        sectionBox.removeAllItems();

        String sql =
                "SELECT SECTION_ID, SECTION_NAME, SEMESTER " +
                "FROM SECTION " +
                "WHERE FACULTY_ID = ? " +
                "ORDER BY SECTION_ID";

        try (
                Connection conn =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    faculty.getFacultyId()
            );

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                while (rs.next()) {

                    int id =
                            rs.getInt(
                                    "SECTION_ID"
                            );

                    String name =
                            rs.getString(
                                    "SECTION_NAME"
                            );

                    String semester =
                            rs.getString(
                                    "SEMESTER"
                            );

                    sectionBox.addItem(
                            new SectionItem(
                                    id,
                                    name,
                                    semester
                            )
                    );
                }
            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Could not load sections.\n\n"
                            + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ============================================================
    // LOAD CLASSROOMS
    // ============================================================

    private void loadClassrooms() {

        roomBox.removeAllItems();

        String sql =
                "SELECT ROOM_ID, ROOM_NO, CAPACITY, "
                        + "BUILDING_LOCATION "
                        + "FROM CLASSROOM "
                        + "ORDER BY ROOM_ID";

        try (
                Connection conn =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                int id =
                        rs.getInt(
                                "ROOM_ID"
                        );

                String roomNo =
                        rs.getString(
                                "ROOM_NO"
                        );

                int capacity =
                        rs.getInt(
                                "CAPACITY"
                        );

                String building =
                        rs.getString(
                                "BUILDING_LOCATION"
                        );

                roomBox.addItem(
                        new ClassroomItem(
                                id,
                                roomNo,
                                capacity,
                                building
                        )
                );
            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Could not load classrooms.\n\n"
                            + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ============================================================
    // CHECK STUDENT REQUEST COUNT
    // ============================================================

    private int getStudentRequestCount(int sectionId) {

        /*
         * We count DISTINCT students.
         *
         * This means:
         * 15 different students = eligible
         *
         * If the same student somehow has multiple requests,
         * that student will still count only once.
         *
         * Course ID is obtained from SECTION table because
         * ScheduleClass currently selects SECTION.
         */

        String sql =
                "SELECT COUNT(DISTINCT r.STUDENT_ID) "
                        + "FROM MAKEUP_REQUEST r "
                        + "JOIN SECTION s "
                        + "ON r.SECTION_ID = s.SECTION_ID "
                        + "WHERE r.SECTION_ID = ? "
                        + "AND r.COURSE_ID = s.COURSE_ID "
                        + "AND r.STATUS IN ('Pending', 'Approved')";

        try (
                Connection conn =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    sectionId
            );

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Could not check student request count.\n\n"
                            + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        return -1;
    }

    // ============================================================
    // SCHEDULE CLASS
    // ============================================================

    private void scheduleClass() {

        String dateText =
                dateField.getText().trim();

        if (dateText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter the class date.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        Date classDate;

        try {

            SimpleDateFormat sdf =
                    new SimpleDateFormat(
                            "dd-MM-yyyy"
                    );

            sdf.setLenient(false);

            classDate =
                    sdf.parse(
                            dateText
                    );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid date format.\n\n"
                            + "Please use:\n"
                            + "DD-MM-YYYY\n\n"
                            + "Example: 28-08-2026",
                    "Invalid Date",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String startTime =
                (String)
                        startTimeBox
                                .getSelectedItem();

        String endTime =
                (String)
                        endTimeBox
                                .getSelectedItem();

        SectionItem section =
                (SectionItem)
                        sectionBox
                                .getSelectedItem();

        ClassroomItem room =
                (ClassroomItem)
                        roomBox
                                .getSelectedItem();

        if (section == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "No section is available "
                            + "for this faculty.",
                    "Section Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (room == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a classroom.",
                    "Classroom Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // ========================================================
        // CHECK 15 STUDENT REQUEST RULE
        // ========================================================

        int studentCount =
                getStudentRequestCount(
                        section.sectionId
                );

        if (studentCount == -1) {
            return;
        }

        if (studentCount < MIN_STUDENTS_REQUIRED) {

            JOptionPane.showMessageDialog(
                    this,

                    "<html>"
                            + "<div style='font-size:16px;'>"
                            + "<b>Cannot Schedule Yet</b>"
                            + "</div><br>"

                            + "<b>Section:</b> "
                            + section.sectionName
                            + "<br>"

                            + "<b>Student Requests:</b> "
                            + studentCount
                            + "<br>"

                            + "<b>Required:</b> "
                            + MIN_STUDENTS_REQUIRED
                            + "<br><br>"

                            + "At least "
                            + MIN_STUDENTS_REQUIRED
                            + " different students must request "
                            + "this section before scheduling a "
                            + "make-up class."
                            + "</html>",

                    "Not Enough Requests",

                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // ========================================================
        // CHECK TIME
        // ========================================================

        if (!isValidTimeRange(
                startTime,
                endTime
        )) {

            JOptionPane.showMessageDialog(
                    this,
                    "End time must be later "
                            + "than start time.",
                    "Invalid Time",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // ========================================================
        // CHECK ROOM CONFLICT
        // ========================================================

        String conflictSql =
                "SELECT COUNT(*) "
                        + "FROM MAKEUP_CLASS "
                        + "WHERE ROOM_ID = ? "
                        + "AND TRUNC(CLASS_DATE) = ? "
                        + "AND START_TIME = ?";

        try (
                Connection conn =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(
                                conflictSql
                        )
        ) {

            ps.setInt(
                    1,
                    room.roomId
            );

            ps.setDate(
                    2,
                    new java.sql.Date(
                            classDate.getTime()
                    )
            );

            ps.setString(
                    3,
                    startTime
            );

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                if (
                        rs.next()
                                &&
                        rs.getInt(1) > 0
                ) {

                    JOptionPane.showMessageDialog(
                            this,
                            "This classroom is already "
                                    + "scheduled for this "
                                    + "date and start time.",
                            "Classroom Conflict",
                            JOptionPane.WARNING_MESSAGE
                    );

                    return;
                }
            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Could not check classroom availability.\n\n"
                            + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // ========================================================
        // INSERT INTO MAKEUP_CLASS
        // ========================================================

        String insertSql =
                "INSERT INTO MAKEUP_CLASS "
                        + "(MAKEUP_CLASS_ID, CLASS_DATE, "
                        + "START_TIME, END_TIME, FACULTY_ID, "
                        + "SECTION_ID, ROOM_ID) "
                        + "VALUES "
                        + "(MAKEUP_CLASS_SEQ.NEXTVAL, "
                        + "?, ?, ?, ?, ?, ?)";

        try (
                Connection conn =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(
                                insertSql
                        )
        ) {

            ps.setDate(
                    1,
                    new java.sql.Date(
                            classDate.getTime()
                    )
            );

            ps.setString(
                    2,
                    startTime
            );

            ps.setString(
                    3,
                    endTime
            );

            ps.setInt(
                    4,
                    faculty.getFacultyId()
            );

            ps.setInt(
                    5,
                    section.sectionId
            );

            ps.setInt(
                    6,
                    room.roomId
            );

            int rows =
                    ps.executeUpdate();

            if (rows > 0) {

                JOptionPane.showMessageDialog(
                        this,

                        "<html>"
                                + "<div style='font-size:16px;'>"
                                + "<b>✓ Class Scheduled Successfully</b>"
                                + "</div><br>"

                                + "<b>Students Requested:</b> "
                                + studentCount
                                + "<br>"

                                + "<b>Date:</b> "
                                + dateText
                                + "<br>"

                                + "<b>Time:</b> "
                                + startTime
                                + " - "
                                + endTime
                                + "<br>"

                                + "<b>Section:</b> "
                                + section.sectionName
                                + "<br>"

                                + "<b>Room:</b> "
                                + room.roomNo
                                + "<br>"

                                + "</html>",

                        "Success",

                        JOptionPane.INFORMATION_MESSAGE
                );

                dispose();
            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Could not schedule the class.\n\n"
                            + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ============================================================
    // TIME VALIDATION
    // ============================================================

    private boolean isValidTimeRange(
            String start,
            String end
    ) {

        try {

            SimpleDateFormat sdf =
                    new SimpleDateFormat(
                            "hh:mm a"
                    );

            Date startDate =
                    sdf.parse(start);

            Date endDate =
                    sdf.parse(end);

            return endDate.after(
                    startDate
            );

        } catch (Exception e) {

            return false;
        }
    }

    // ============================================================
    // LABEL
    // ============================================================

    private JLabel createLabel(
            String text
    ) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        label.setForeground(
                TEXT
        );

        return label;
    }

    // ============================================================
    // TEXT FIELD STYLE
    // ============================================================

    private void styleTextField(
            JTextField field
    ) {

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        210,
                                        218,
                                        232
                                )
                        ),
                        new EmptyBorder(
                                8,
                                12,
                                8,
                                12
                        )
                )
        );

        field.setBackground(
                Color.WHITE
        );

        field.setForeground(
                TEXT
        );
    }

    // ============================================================
    // COMBO BOX STYLE
    // ============================================================

    private void styleComboBox(
            JComboBox<?> box
    ) {

        box.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        box.setBackground(
                Color.WHITE
        );

        box.setForeground(
                TEXT
        );

        box.setPreferredSize(
                new Dimension(
                        300,
                        42
                )
        );
    }

    // ============================================================
    // BUTTON
    // ============================================================

    private JButton createButton(
            String text,
            Color background
    ) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        button.setForeground(
                Color.WHITE
        );

        button.setBackground(
                background
        );

        button.setFocusPainted(
                false
        );

        button.setBorderPainted(
                false
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setPreferredSize(
                new Dimension(
                        250,
                        45
                )
        );

        return button;
    }

    // ============================================================
    // SECTION ITEM
    // ============================================================

    private static class SectionItem {

        int sectionId;
        String sectionName;
        String semester;

        SectionItem(
                int sectionId,
                String sectionName,
                String semester
        ) {

            this.sectionId =
                    sectionId;

            this.sectionName =
                    sectionName;

            this.semester =
                    semester;
        }

        @Override
        public String toString() {

            return sectionName
                    + "  ("
                    + semester
                    + ")";
        }
    }

    // ============================================================
    // CLASSROOM ITEM
    // ============================================================

    private static class ClassroomItem {

        int roomId;
        String roomNo;
        int capacity;
        String building;

        ClassroomItem(
                int roomId,
                String roomNo,
                int capacity,
                String building
        ) {

            this.roomId =
                    roomId;

            this.roomNo =
                    roomNo;

            this.capacity =
                    capacity;

            this.building =
                    building;
        }

        @Override
        public String toString() {

            return roomNo
                    + " | Capacity: "
                    + capacity
                    + " | "
                    + building;
        }
    }
}