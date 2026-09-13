import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class MyRequests extends JFrame {

    private Student student;

    private JTable table;
    private DefaultTableModel model;

    public MyRequests(Student student) {

        this.student = student;

        setTitle("My Make-Up Requests");

        setSize(1400, 700);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        createUI();

        setVisible(true);
    }


    private void createUI() {

        JPanel main =
                new JPanel(
                        new BorderLayout()
                );


        // =========================================
        // HEADER
        // =========================================

        JLabel header =
                new JLabel(
                        "  My Make-Up Requests"
                );

        header.setPreferredSize(
                new Dimension(
                        1400,
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


        // =========================================
        // TABLE COLUMNS
        // =========================================

        String[] columns = {

                "Request ID",
                "Course",
                "Section",
                "Request Date",
                "My Status",
                "Faculty",
                "Requested Students",
                "Make-Up Status",
                "Class Date",
                "Start Time",
                "End Time",
                "Room"
        };


        model =
                new DefaultTableModel(
                        columns,
                        0
                );


        // =========================================
        // SQL QUERY
        // =========================================

        String sql =

                "SELECT " +

                "r.request_id, " +

                "c.course_name, " +

                "sec.section_name, " +

                "r.request_date, " +

                "r.status, " +

                "f.faculty_name, " +

                "cnt.request_count, " +

                "mc.class_date, " +

                "mc.start_time, " +

                "mc.end_time, " +

                "cr.room_no " +

                "FROM MakeUp_Request r " +

                "JOIN Course c " +

                "ON r.course_id = c.course_id " +

                "JOIN Section sec " +

                "ON r.section_id = sec.section_id " +

                "LEFT JOIN Faculty f " +

                "ON r.faculty_id = f.faculty_id " +

                // =================================
                // COUNT STUDENTS
                // =================================

                "LEFT JOIN ( " +

                "SELECT " +

                "course_id, " +

                "section_id, " +

                "COUNT(DISTINCT student_id) AS request_count " +

                "FROM MakeUp_Request " +

                "WHERE status IN ('Pending', 'Approved') " +

                "GROUP BY course_id, section_id " +

                ") cnt " +

                "ON r.course_id = cnt.course_id " +

                "AND r.section_id = cnt.section_id " +

                // =================================
                // MAKE-UP CLASS
                // ONLY JOIN WHEN 15 STUDENTS
                // =================================

                "LEFT JOIN MakeUp_Class mc " +

                "ON r.section_id = mc.section_id " +

                "AND cnt.request_count >= 15 " +

                "LEFT JOIN Classroom cr " +

                "ON mc.room_id = cr.room_id " +

                "WHERE r.student_id = ? " +

                "ORDER BY r.request_id";


        // =========================================
        // DATABASE
        // =========================================

        try (

                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)

        ) {

            ps.setInt(
                    1,
                    student.getStudentId()
            );


            ResultSet rs =
                    ps.executeQuery();


            while (rs.next()) {


                // =====================================
                // REQUEST COUNT
                // =====================================

                int requestCount =
                        rs.getInt(
                                "request_count"
                        );


                // =====================================
                // MAKE-UP STATUS
                // =====================================

                String classStatus;


                if (requestCount < 15) {

                    classStatus =
                            "Not Scheduled";

                }

                else if (
                        rs.getDate(
                                "class_date"
                        ) != null
                ) {

                    classStatus =
                            "Scheduled";

                }

                else {

                    classStatus =
                            "Eligible for Scheduling";
                }


                // =====================================
                // FACULTY
                // =====================================

                String facultyName =
                        rs.getString(
                                "faculty_name"
                        );


                if (facultyName == null) {

                    facultyName =
                            "Not Assigned";
                }


                // =====================================
                // CLASS DATE
                // =====================================

                String classDate = "-";


                if (
                        requestCount >= 15
                        &&
                        rs.getDate(
                                "class_date"
                        ) != null
                ) {

                    classDate =
                            rs.getDate(
                                    "class_date"
                            ).toString();
                }


                // =====================================
                // START TIME
                // =====================================

                String startTime = "-";


                if (
                        requestCount >= 15
                        &&
                        rs.getString(
                                "start_time"
                        ) != null
                ) {

                    startTime =
                            rs.getString(
                                    "start_time"
                            );
                }


                // =====================================
                // END TIME
                // =====================================

                String endTime = "-";


                if (
                        requestCount >= 15
                        &&
                        rs.getString(
                                "end_time"
                        ) != null
                ) {

                    endTime =
                            rs.getString(
                                    "end_time"
                            );
                }


                // =====================================
                // ROOM
                // =====================================

                String roomNo = "-";


                if (
                        requestCount >= 15
                        &&
                        rs.getString(
                                "room_no"
                        ) != null
                ) {

                    roomNo =
                            rs.getString(
                                    "room_no"
                            );
                }


                // =====================================
                // ADD ROW
                // =====================================

                model.addRow(

                        new Object[]{

                                rs.getInt(
                                        "request_id"
                                ),

                                rs.getString(
                                        "course_name"
                                ),

                                rs.getString(
                                        "section_name"
                                ),

                                rs.getDate(
                                        "request_date"
                                ),

                                rs.getString(
                                        "status"
                                ),

                                facultyName,

                                requestCount
                                + " / 15",

                                classStatus,

                                classDate,

                                startTime,

                                endTime,

                                roomNo
                        }
                );
            }


        } catch (Exception e) {

            JOptionPane.showMessageDialog(

                    this,

                    "Error loading requests:\n"
                    + e.getMessage(),

                    "Database Error",

                    JOptionPane.ERROR_MESSAGE
            );
        }


        // =========================================
        // TABLE
        // =========================================

        table =
                new JTable(model);


        table.setRowHeight(
                40
        );


        table.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );


        table.getTableHeader().setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );


        table.getTableHeader().setBackground(
                new Color(
                        220,
                        230,
                        245
                )
        );


        table.setAutoResizeMode(
                JTable.AUTO_RESIZE_OFF
        );


        // =========================================
        // COLUMN WIDTHS
        // =========================================

        table.getColumnModel()
                .getColumn(0)
                .setPreferredWidth(80);

        table.getColumnModel()
                .getColumn(1)
                .setPreferredWidth(170);

        table.getColumnModel()
                .getColumn(2)
                .setPreferredWidth(80);

        table.getColumnModel()
                .getColumn(3)
                .setPreferredWidth(120);

        table.getColumnModel()
                .getColumn(4)
                .setPreferredWidth(100);

        table.getColumnModel()
                .getColumn(5)
                .setPreferredWidth(160);

        table.getColumnModel()
                .getColumn(6)
                .setPreferredWidth(130);

        table.getColumnModel()
                .getColumn(7)
                .setPreferredWidth(190);

        table.getColumnModel()
                .getColumn(8)
                .setPreferredWidth(120);

        table.getColumnModel()
                .getColumn(9)
                .setPreferredWidth(110);

        table.getColumnModel()
                .getColumn(10)
                .setPreferredWidth(110);

        table.getColumnModel()
                .getColumn(11)
                .setPreferredWidth(100);


        // =========================================
        // SCROLL PANE
        // =========================================

        JScrollPane scrollPane =
                new JScrollPane(
                        table
                );


        main.add(
                scrollPane,
                BorderLayout.CENTER
        );


        // =========================================
        // INFORMATION LABEL
        // =========================================

        JLabel info =
                new JLabel(

                        "  Select a Pending request "
                        + "and click Cancel Request. "
                        + "Approved requests cannot be cancelled.",

                        SwingConstants.CENTER
                );


        info.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );


        info.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        5,
                        10,
                        5
                )
        );


        // =========================================
        // CANCEL BUTTON
        // =========================================

        JButton cancelButton =
                new JButton(
                        "Cancel Request"
                );


        cancelButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );


        cancelButton.setForeground(
                Color.WHITE
        );


        cancelButton.setBackground(
                new Color(
                        200,
                        50,
                        50
                )
        );


        cancelButton.addActionListener(
                e -> cancelRequest()
        );


        // =========================================
        // BACK BUTTON
        // =========================================

        JButton back =
                new JButton(
                        "Back to Dashboard"
                );


        back.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );


        back.addActionListener(
                e -> {

                    new StudentDashboard(
                            student
                    );

                    dispose();
                }
        );


        // =========================================
        // BUTTON PANEL
        // =========================================

        JPanel buttonPanel =
                new JPanel();


        buttonPanel.add(
                cancelButton
        );


        buttonPanel.add(
                back
        );


        // =========================================
        // BOTTOM PANEL
        // =========================================

        JPanel bottom =
                new JPanel(
                        new BorderLayout()
                );


        bottom.add(
                info,
                BorderLayout.CENTER
        );


        bottom.add(
                buttonPanel,
                BorderLayout.SOUTH
        );


        main.add(
                bottom,
                BorderLayout.SOUTH
        );


        // =========================================
        // ADD MAIN
        // =========================================

        add(main);
    }


    // =================================================
    // CANCEL REQUEST
    // =================================================

    private void cancelRequest() {


        // =========================================
        // CHECK SELECTION
        // =========================================

        int selectedRow =
                table.getSelectedRow();


        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(

                    this,

                    "Please select a request first.",

                    "No Request Selected",

                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        // =========================================
        // GET REQUEST ID
        // =========================================

        int requestId =
                Integer.parseInt(

                        table.getValueAt(
                                selectedRow,
                                0
                        ).toString()
                );


        // =========================================
        // GET CURRENT STATUS
        // =========================================

        String currentStatus =
                table.getValueAt(
                        selectedRow,
                        4
                ).toString();


        // =========================================
        // ONLY PENDING CAN BE CANCELLED
        // =========================================

        if (
                !currentStatus.equalsIgnoreCase(
                        "Pending"
                )
        ) {

            JOptionPane.showMessageDialog(

                    this,

                    "Only Pending requests can be cancelled.\n\n"
                    + "Current Status: "
                    + currentStatus,

                    "Cannot Cancel Request",

                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        // =========================================
        // CONFIRMATION
        // =========================================

        int confirm =
                JOptionPane.showConfirmDialog(

                        this,

                        "Are you sure you want to cancel "
                        + "this request?",

                        "Confirm Cancellation",

                        JOptionPane.YES_NO_OPTION,

                        JOptionPane.QUESTION_MESSAGE
                );


        if (
                confirm
                !=
                JOptionPane.YES_OPTION
        ) {

            return;
        }


        // =========================================
        // UPDATE DATABASE
        // =========================================

        String sql =

                "UPDATE MakeUp_Request " +

                "SET status = 'Cancelled' " +

                "WHERE request_id = ? " +

                "AND student_id = ? " +

                "AND status = 'Pending'";


        try (

                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)

        ) {


            ps.setInt(
                    1,
                    requestId
            );


            ps.setInt(
                    2,
                    student.getStudentId()
            );


            int rows =
                    ps.executeUpdate();


            // =====================================
            // SUCCESS
            // =====================================

            if (rows > 0) {

                JOptionPane.showMessageDialog(

                        this,

                        "Request cancelled successfully!",

                        "Success",

                        JOptionPane.INFORMATION_MESSAGE
                );


                // Re-open My Requests
                new MyRequests(
                        student
                );


                dispose();


            } else {

                JOptionPane.showMessageDialog(

                        this,

                        "Request could not be cancelled.\n"
                        + "It may no longer be Pending.",

                        "Cancellation Failed",

                        JOptionPane.ERROR_MESSAGE
                );
            }


        } catch (Exception e) {

            JOptionPane.showMessageDialog(

                    this,

                    "Error cancelling request:\n"
                    + e.getMessage(),

                    "Database Error",

                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}