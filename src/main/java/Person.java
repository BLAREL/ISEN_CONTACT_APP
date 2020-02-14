import java.util.Date;

public class Person {
    private int idperson;
    private String lastname;
    private String firstname;
    private String phone_number;
    private String address;
    private String email_address;
    private Date birth_date;

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setIdperson(int idperson) {
        this.idperson = idperson;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public int getIdperson() {
        return idperson;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail_address() {
        return email_address;
    }

    public String getLastname() {
        return lastname;
    }
}
