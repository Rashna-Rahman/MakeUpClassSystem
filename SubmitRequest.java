import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class SubmitRequest extends JFrame {

    private Student student;

    private JComboBox<String> courseBox;
    private JComboBox<String> sectionBox;

    private JTextField dateField;
    private JTextArea reasonArea;

    // Selected date
    private LocalDate selectedDate;


    public SubmitRequest(Student student) {

        this.student = student;

        // Default selected date = today
        selectedDate = LocalDate.now();

        setTitle(
                "Submit Make-Up Request"
        );

        setSize(
                1000,
                700
        );

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
        // HEADER
        // =====================================================

        JLabel header =
                new JLabel(
                        "  Submit Make-Up Request"
                );

        header.setPreferredSize(
                new Dimension(
                        1000,
                        65
                )
        );

        header.setBackground(
                new Color(
                        35,
                        95,
                        205
                )
        );

        header.setForeground(
                Color.WHITE
        );

        header.setOpaque(true);

        header.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        21
                )
        );

        main.add(
                header,
                BorderLayout.NORTH
        );


        // =====================================================
        // FORM
        // =====================================================

        JPanel form =
                new JPanel();

        form.setLayout(
                new BoxLayout(
                        form,
                        BoxLayout.Y_AXIS
                )
        );

        form.setBackground(
                Color.WHITE
        );

        form.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        50,
                        30,
                        50
                )
        );


        // =====================================================
        // STUDENT ID
        // =====================================================

        JLabel studentId =
                new JLabel(
                        "Student ID: "
                        + student.getStudentId()
                );


        // =====================================================
        // STUDENT NAME
        // =====================================================

        JLabel studentName =
                new JLabel(
                        "Student Name: "
                        + student.getStudentName()
                );


        form.add(
                studentId
        );

        form.add(
                Box.createVerticalStrut(
                        10
                )
        );

        form.add(
                studentName
        );

        form.add(
        Box.createVerticalStrut(
                20
        )
);


        // =====================================================
        // COURSE
        // =====================================================

        form.add(
                new JLabel(
                        "Course"
                )
        );


        courseBox =
                new JComboBox<>();


        courseBox.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        40
                )
        );


        form.add(
                courseBox
        );


        form.add(
                Box.createVerticalStrut(
                        10
                )
        );


        // =====================================================
        // SECTION
        // =====================================================

        form.add(
                new JLabel(
                        "Section"
                )
        );


        sectionBox =
                new JComboBox<>();


        sectionBox.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        40
                )
        );


        form.add(
                sectionBox
        );


        form.add(
                Box.createVerticalStrut(
                        10
                )
        );


        // =====================================================
        // REQUEST DATE
        // =====================================================

        form.add(
                new JLabel(
                        "Request Date"
                )
        );


        // ---------------------------------------------
        // Date field
        // ---------------------------------------------

        dateField =
                new JTextField();


        dateField.setText(
                selectedDate.toString()
        );


        dateField.setEditable(false);


        dateField.setBackground(
                Color.WHITE
        );


        dateField.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        40
                )
        );


        // ---------------------------------------------
        // Select Date button
        // ---------------------------------------------

        JButton dateButton =
                new JButton(
                        "Select Date"
                );


        dateButton.setPreferredSize(
                new Dimension(
                        130,
                        40
                )
        );


        dateButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );


        dateButton.addActionListener(
                e -> showCalendar()
        );


        // ---------------------------------------------
        // Date panel
        // ---------------------------------------------

        JPanel datePanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                0
                        )
                );


        datePanel.setBackground(
                Color.WHITE
        );


        datePanel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        40
                )
        );


        datePanel.add(
                dateField,
                BorderLayout.CENTER
        );


        datePanel.add(
                dateButton,
                BorderLayout.EAST
        );


        form.add(
                datePanel
        );


        form.add(
                Box.createVerticalStrut(
                        10
                )
        );


        // =====================================================
        // REASON
        // =====================================================

        form.add(
                new JLabel(
                        "Reason"
                )
        );


        reasonArea =
                new JTextArea(
                        5,
                        30
                );


        reasonArea.setLineWrap(
                true
        );


        reasonArea.setWrapStyleWord(
                true
        );


        JScrollPane reasonScroll =
                new JScrollPane(
                        reasonArea
                );


        form.add(
                reasonScroll
        );


        // =====================================================
        // SUBMIT BUTTON
        // =====================================================

        JButton submitButton =
                new JButton(
                        "SUBMIT REQUEST"
                );


        submitButton.setBackground(
                new Color(
                        35,
                        95,
                        205
                )
        );


        submitButton.setForeground(
                Color.WHITE
        );


        submitButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );


        submitButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        form.add(
                Box.createVerticalStrut(
                        20
                )
        );


        form.add(
                submitButton
        );


        main.add(
                new JScrollPane(
                        form
                ),
                BorderLayout.CENTER
        );


        add(main);


        // =====================================================
        // DATABASE
        // =====================================================

        loadCourses();


        courseBox.addActionListener(
                e -> loadSections()
        );


        submitButton.addActionListener(
                e -> submitRequest()
        );
    }


    // =========================================================
    // LOAD COURSES
    // =========================================================

    private void loadCourses() {

        courseBox.removeAllItems();


        String sql =
                "SELECT course_id, course_name " +
                "FROM Course " +
                "ORDER BY course_id";


        try (

                Connection con =
                        DBConnection.getConnection();

                Statement st =
                        con.createStatement();

                ResultSet rs =
                        st.executeQuery(sql)

        ) {


            while (rs.next()) {

                courseBox.addItem(

                        rs.getInt(
                                "course_id"
                        )

                        + " - "

                        + rs.getString(
                                "course_name"
                        )
                );
            }


        } catch (Exception e) {

            JOptionPane.showMessageDialog(

                    this,

                    "Cannot load courses:\n"
                    + e.getMessage(),

                    "Error",

                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // =========================================================
    // LOAD SECTIONS
    // =========================================================

    private void loadSections() {

        sectionBox.removeAllItems();


        if (
                courseBox.getSelectedItem()
                        == null
        ) {

            return;
        }


        String selected =
                courseBox
                        .getSelectedItem()
                        .toString();


        int courseId =
                Integer.parseInt(

                        selected
                                .split(" - ")[0]
                );


        String sql =
                "SELECT section_id, section_name " +
                "FROM Section " +
                "WHERE course_id = ? " +
                "ORDER BY section_id";


        try (

                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)

        ) {


            ps.setInt(
                    1,
                    courseId
            );


            ResultSet rs =
                    ps.executeQuery();


            while (rs.next()) {

                sectionBox.addItem(

                        rs.getInt(
                                "section_id"
                        )

                        + " - "

                        + rs.getString(
                                "section_name"
                        )
                );
            }


        } catch (Exception e) {

            JOptionPane.showMessageDialog(

                    this,

                    "Cannot load sections:\n"
                    + e.getMessage(),

                    "Error",

                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // =========================================================
    // SHOW CALENDAR
    // =========================================================

    private void showCalendar() {

        final JDialog dialog =
                new JDialog(
                        this,
                        "Select Request Date",
                        true
                );


        dialog.setSize(
                420,
                430
        );


        dialog.setLocationRelativeTo(
                this
        );


        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );


        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        10,
                        10,
                        10
                )
        );


        // =====================================================
        // CURRENT MONTH
        // =====================================================

        final YearMonth[] currentMonth = {

                YearMonth.from(
                        selectedDate
                )
        };


        // =====================================================
        // TOP PANEL
        // =====================================================

        JPanel topPanel =
                new JPanel(
                        new BorderLayout()
                );


        JButton previousButton =
                new JButton(
                        "<"
                );


        JButton nextButton =
                new JButton(
                        ">"
                );


        JLabel monthLabel =
                new JLabel(
                        "",
                        SwingConstants.CENTER
                );


        monthLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        17
                )
        );


        topPanel.add(
                previousButton,
                BorderLayout.WEST
        );


        topPanel.add(
                monthLabel,
                BorderLayout.CENTER
        );


        topPanel.add(
                nextButton,
                BorderLayout.EAST
        );


        mainPanel.add(
                topPanel,
                BorderLayout.NORTH
        );


        // =====================================================
        // CALENDAR PANEL
        // =====================================================

        JPanel calendarPanel =
                new JPanel(
                        new GridLayout(
                                7,
                                7,
                                3,
                                3
                        )
                );


        mainPanel.add(
                calendarPanel,
                BorderLayout.CENTER
        );


        // =====================================================
        // RENDER CALENDAR
        // =====================================================

        Runnable renderCalendar =
                () -> {

                    calendarPanel.removeAll();


                    // -----------------------------------------
                    // DAYS
                    // -----------------------------------------

                    String[] days = {

                            "Sun",
                            "Mon",
                            "Tue",
                            "Wed",
                            "Thu",
                            "Fri",
                            "Sat"
                    };


                    for (
                            String day :
                            days
                    ) {

                        JLabel label =
                                new JLabel(
                                        day,
                                        SwingConstants.CENTER
                                );


                        label.setFont(
                                new Font(
                                        "Arial",
                                        Font.BOLD,
                                        12
                                )
                        );


                        calendarPanel.add(
                                label
                        );
                    }


                    YearMonth ym =
                            currentMonth[0];


                    monthLabel.setText(
                            ym.format(
                                    DateTimeFormatter.ofPattern(
                                            "MMMM yyyy"
                                    )
                            )
                    );


                    LocalDate firstDay =
                            ym.atDay(1);


                    int startDay =
                            firstDay
                                    .getDayOfWeek()
                                    .getValue();


                    // Java:
                    // Monday = 1
                    // Sunday = 7
                    //
                    // We need:
                    // Sunday = 0
                    // Monday = 1
                    // ...


                    int offset;


                    if (
                            startDay == 7
                    ) {

                        offset = 0;

                    } else {

                        offset = startDay;
                    }


                    // -----------------------------------------
                    // EMPTY CELLS
                    // -----------------------------------------

                    for (
                            int i = 0;
                            i < offset;
                            i++
                    ) {

                        calendarPanel.add(
                                new JLabel()
                        );
                    }


                    // -----------------------------------------
                    // DAYS OF MONTH
                    // -----------------------------------------

                    int daysInMonth =
                            ym.lengthOfMonth();


                    for (
                            int day = 1;
                            day <= daysInMonth;
                            day++
                    ) {

                        final int selectedDay =
                                day;


                        JButton dayButton =
                                new JButton(
                                        String.valueOf(
                                                day
                                        )
                                );


                        LocalDate thisDate =
                                ym.atDay(
                                        day
                                );


                        // -------------------------------------
                        // SELECTED DATE
                        // -------------------------------------

                        if (
                                thisDate.equals(
                                        selectedDate
                                )
                        ) {

                            dayButton.setFont(
                                    new Font(
                                            "Arial",
                                            Font.BOLD,
                                            13
                                    )
                            );
                        }


                        dayButton.addActionListener(
                                e -> {

                                    selectedDate =
                                            currentMonth[0]
                                                    .atDay(
                                                            selectedDay
                                                    );


                                    dateField.setText(
                                            selectedDate.toString()
                                    );


                                    dialog.dispose();
                                }
                        );


                        calendarPanel.add(
                                dayButton
                        );
                    }


                    calendarPanel.revalidate();

                    calendarPanel.repaint();
                };


        // =====================================================
        // PREVIOUS MONTH
        // =====================================================

        previousButton.addActionListener(
                e -> {

                    currentMonth[0] =
                            currentMonth[0]
                                    .minusMonths(1);

                    renderCalendar.run();
                }
        );


        // =====================================================
        // NEXT MONTH
        // =====================================================

        nextButton.addActionListener(
                e -> {

                    currentMonth[0] =
                            currentMonth[0]
                                    .plusMonths(1);

                    renderCalendar.run();
                }
        );


        // =====================================================
        // BOTTOM BUTTON
        // =====================================================

        JButton todayButton =
                new JButton(
                        "Today"
                );


        todayButton.addActionListener(
                e -> {

                    selectedDate =
                            LocalDate.now();


                    dateField.setText(
                            selectedDate.toString()
                    );


                    dialog.dispose();
                }
        );


        JPanel bottomPanel =
                new JPanel();


        bottomPanel.add(
                todayButton
        );


        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );


        // =====================================================
        // INITIAL CALENDAR
        // =====================================================

        renderCalendar.run();


        dialog.add(
                mainPanel
        );


        dialog.setVisible(true);
    }


    // =========================================================
    // SUBMIT REQUEST
    // =========================================================

    private void submitRequest() {


        // =====================================================
        // CHECK COURSE & SECTION
        // =====================================================

        if (

                courseBox.getSelectedItem()
                        == null

                ||

                sectionBox.getSelectedItem()
                        == null

        ) {

            JOptionPane.showMessageDialog(

                    this,

                    "Please select Course and Section.",

                    "Warning",

                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        // =====================================================
        // CHECK REASON
        // =====================================================

        String reason =
                reasonArea
                        .getText()
                        .trim();


        if (
                reason.isEmpty()
        ) {

            JOptionPane.showMessageDialog(

                    this,

                    "Please enter a reason.",

                    "Warning",

                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        try {


            // =================================================
            // COURSE ID
            // =================================================

            int courseId =
                    Integer.parseInt(

                            courseBox
                                    .getSelectedItem()
                                    .toString()
                                    .split(" - ")[0]
                    );


            // =================================================
            // SECTION ID
            // =================================================

            int sectionId =
                    Integer.parseInt(

                            sectionBox
                                    .getSelectedItem()
                                    .toString()
                                    .split(" - ")[0]
                    );


            // =================================================
            // DUPLICATE REQUEST CHECK
            // =================================================
            //
            // Same student
            // Same course
            // Same section
            //
            // Pending / Approved
            // = Cannot submit again
            //
            // Rejected / Cancelled
            // = Can submit again
            //
            // =================================================

            String checkSql =

                    "SELECT COUNT(*) AS total " +

                    "FROM MakeUp_Request " +

                    "WHERE student_id = ? " +

                    "AND course_id = ? " +

                    "AND section_id = ? " +

                    "AND status IN " +
                    "('Pending', 'Approved')";


            try (

                    Connection con =
                            DBConnection.getConnection();

                    PreparedStatement checkPs =
                            con.prepareStatement(
                                    checkSql
                            )

            ) {


                checkPs.setInt(
                        1,
                        student.getStudentId()
                );


                checkPs.setInt(
                        2,
                        courseId
                );


                checkPs.setInt(
                        3,
                        sectionId
                );


                ResultSet checkRs =
                        checkPs.executeQuery();


                if (
                        checkRs.next()
                ) {


                    int existingRequest =
                            checkRs.getInt(
                                    "total"
                            );


                    if (
                            existingRequest > 0
                    ) {

                        JOptionPane.showMessageDialog(

                                this,

                                "You have already submitted "
                                + "a request for this course "
                                + "and section.",

                                "Duplicate Request",

                                JOptionPane.WARNING_MESSAGE
                        );


                        return;
                    }
                }
            }


            // =================================================
            // INSERT REQUEST
            // =================================================

            String sql =

                    "INSERT INTO MakeUp_Request " +

                    "(request_id, request_date, " +

                    "status, reason, student_id, " +

                    "course_id, faculty_id, section_id) " +

                    "VALUES " +

                    "(request_seq.NEXTVAL, ?, " +

                    "'Pending', ?, ?, ?, " +

                    "(SELECT faculty_id " +

                    "FROM Section " +

                    "WHERE section_id = ?), ?)";


            try (

                    Connection con =
                            DBConnection.getConnection();

                    PreparedStatement ps =
                            con.prepareStatement(sql)

            ) {


                // ---------------------------------------------
                // REQUEST DATE
                // ---------------------------------------------

                ps.setDate(
                        1,
                        Date.valueOf(
                                selectedDate
                        )
                );


                // ---------------------------------------------
                // REASON
                // ---------------------------------------------

                ps.setString(
                        2,
                        reason
                );


                // ---------------------------------------------
                // STUDENT ID
                // ---------------------------------------------

                ps.setInt(
                        3,
                        student.getStudentId()
                );


                // ---------------------------------------------
                // COURSE ID
                // ---------------------------------------------

                ps.setInt(
                        4,
                        courseId
                );


                // ---------------------------------------------
                // SECTION ID
                // ---------------------------------------------

                ps.setInt(
                        5,
                        sectionId
                );


                // ---------------------------------------------
                // SECTION ID FOR SUBQUERY
                // ---------------------------------------------

                ps.setInt(
                        6,
                        sectionId
                );


                // ---------------------------------------------
                // INSERT
                // ---------------------------------------------

                ps.executeUpdate();


                JOptionPane.showMessageDialog(

                        this,

                        "Make-Up Request Submitted Successfully!",

                        "Success",

                        JOptionPane.INFORMATION_MESSAGE
                );


                // ---------------------------------------------
                // OPEN MY REQUESTS
                // ---------------------------------------------

                new MyRequests(
                        student
                );


                dispose();
            }


        } catch (Exception e) {

            JOptionPane.showMessageDialog(

                    this,

                    "Request could not be submitted:\n"
                    + e.getMessage(),

                    "Error",

                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}