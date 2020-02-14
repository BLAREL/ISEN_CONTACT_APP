import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbManager {
    public static final DbManager instance = new DbManager();
    private Connection connection = null;

    private DbManager() {
        System.out.println("instance de dbmanager");
    }

    public static DbManager getInstance() {
        return(instance);
    }

    public void addContact(Person person) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `person`(lastname,firstname,nickname,phone_number,address,email_address,birth_date) VALUES (?,?,?,?,?,?,?);");
            preparedStatement.setString(1, person.getLastname());
            preparedStatement.setString(2, person.getFirstname());
            preparedStatement.setString(3, "teest");
            preparedStatement.setString(4, person.getPhone_number());
            preparedStatement.setString(5, person.getAddress());
            preparedStatement.setString(6, person.getEmail_address());
            preparedStatement.setDate(7, new Date(person.getBirth_date().getTime()));
            preparedStatement.executeUpdate();
        }
        catch (Exception e) {

        }
    }

    public void editContact(Person person) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `person` SET lastname = ?,firstname = ?, nickname = ?,phone_number = ?,address = ?,email_address = ?,birth_date = ? where  idperson = ?;");
            preparedStatement.setString(1, person.getLastname());
            preparedStatement.setString(2, person.getFirstname());
            preparedStatement.setString(3, "test");
            preparedStatement.setString(4, person.getPhone_number());
            preparedStatement.setString(5, person.getAddress());
            preparedStatement.setString(6, person.getEmail_address());
            preparedStatement.setDate(7, new Date(person.getBirth_date().getTime()));
            preparedStatement.setInt(8, person.getIdperson());
            preparedStatement.executeUpdate();
        }
        catch (Exception e) {

        }
    }

    public void removeContact(Person person) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE from `person` where  idperson = ?;");
            preparedStatement.setInt(1, person.getIdperson());
            preparedStatement.executeUpdate();
        }
        catch (Exception e) {

        }
    }


    public List<Person> getAllContact() {
        // pourquoi le <~>
        List<Person> personList = new ArrayList<Person>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * from person;");
            while (resultSet.next()) {
                Person person = new Person();

                person.setAddress(resultSet.getString("address"));
                person.setBirth_date(resultSet.getDate("birth_date"));
                person.setEmail_address(resultSet.getString("email_address"));
                person.setFirstname(resultSet.getString("firstname"));
                person.setLastname(resultSet.getString("lastname"));
                person.setIdperson(resultSet.getInt("idperson"));
                person.setPhone_number(resultSet.getString("phone_number"));

                personList.add(person);
            }
            return personList;
        }
        catch (Exception e) {

        }
        return personList;
    }

    public boolean setCredentials(String userName, String password) {
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3308/contact_app?autoreconnect=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            conn = DriverManager.getConnection(url, userName, password);
            if (conn.isValid(1)) {
                if (this.connection != null)
                    this.connection.close();
                this.connection = conn;
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

}
