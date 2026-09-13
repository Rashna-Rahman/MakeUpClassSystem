public class Student {

    private int studentId;
    private String studentName;
    private String program;
    private String batch;
    private String email;
    private String phone;

    public Student(
            int studentId,
            String studentName,
            String program,
            String batch,
            String email,
            String phone
    ) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.program = program;
        this.batch = batch;
        this.email = email;
        this.phone = phone;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getProgram() {
        return program;
    }

    public String getBatch() {
        return batch;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}