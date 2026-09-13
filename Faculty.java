public class Faculty {

    private int facultyId;
    private String facultyName;
    private String designation;
    private String email;
    private String officePhone;

    public Faculty(
            int facultyId,
            String facultyName,
            String designation,
            String email,
            String officePhone
    ) {

        this.facultyId = facultyId;
        this.facultyName = facultyName;
        this.designation = designation;
        this.email = email;
        this.officePhone = officePhone;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public String getDesignation() {
        return designation;
    }

    public String getEmail() {
        return email;
    }

    public String getOfficePhone() {
        return officePhone;
    }
}