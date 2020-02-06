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

    public List<Person> getAllContact() {
        List<Person> personList = new ArrayList<Person>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * from person;");
            while (resultSet.next()) {
              Person person = new Person();

              person.setAddress(resultSet.getString("address"));
              person.setBirth_date(resultSet.getDate("birth_date"));
              person.setEmail_address(resultSet.getString("email_address"));

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
