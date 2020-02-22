import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class HomeController {

    @FXML
    private TableView<Person> listPerson;

    private ObservableList<Person> personData = FXCollections.observableArrayList();
    final FileChooser fileChooser = new FileChooser();
    @FXML
    private AnchorPane ap;

    Stage stage;

    @FXML
    private TextField adress;

    @FXML
    private ProgressBar bar;

    @FXML
    private DatePicker birthday;

    @FXML
    private TextField mail;

    @FXML
    private TextField phone;

    @FXML
    private TextField nickname;

    @FXML
    private TextField lastname;

    @FXML
    private TextField name;


    @FXML
    private Button submitButton;

    @FXML
    private Button editButton;



    @FXML
    public void initialize() {
        personData.addAll(DbManager.getInstance().getAllContact());
        listPerson.getItems().addAll(personData);
        bar.setProgress(100);
    }
    @FXML
    public void onDelete() {
        DbManager.getInstance().removeContact(listPerson.getSelectionModel().getSelectedItem());
        personData.remove(listPerson.getSelectionModel().getSelectedItem());
        listPerson.getItems().remove(listPerson.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void onAdd(){
        Person tmp = new Person();
        tmp.setNickname(nickname.getText());
        tmp.setAddress(adress.getText());
        tmp.setFirstname(name.getText());
        tmp.setLastname(lastname.getText());
        tmp.setEmail_address(mail.getText());
        tmp.setPhone_number(phone.getText());
        tmp.setBirth_date(Date.from(Instant.from(birthday.getValue().atStartOfDay(ZoneId.systemDefault()))));


        if (DbManager.getInstance().addContact(tmp)){
            personData.add(tmp);
            listPerson.getItems().add(tmp);
        }

    }

    public VCard personToVcardText(Person person) {
        VCard vcard = new VCard();
        StructuredName n = new StructuredName();
        n.setFamily(person.getFirstname());
        n.setGiven(person.getLastname());
        n.getPrefixes().add(person.getNickname());
        vcard.setStructuredName(n);
        Address address = new Address();
        address.setCountry(person.getAddress());
        vcard.addAddress(address);
        Email email = new Email(person.getEmail_address());
        vcard.addEmail(email);
        vcard.addTelephoneNumber(new Telephone(person.getPhone_number()));
        vcard.setBirthday(new Birthday(person.getBirth_date()));
        return vcard;
    }

    @FXML
    public void onExport() throws IOException {
        List<Person> listdepersonne = DbManager.getInstance().getAllContact();

        File file = new File("vcards.vcf");
        Collection<VCard> vcards = new ArrayList<>();
        for (Person tmp : listdepersonne){
            vcards.add(this.personToVcardText(tmp));
        }
        Ezvcard.write(vcards).go(file);
    }

    private void openFile(File file) throws IOException {
        List<VCard> vcards = Ezvcard.parse(file).all();
        for (VCard vcard : vcards) {
            Person tmp = new Person();
            tmp.setBirth_date(vcard.getBirthday().getDate());
            tmp.setAddress(vcard.getAddresses().get(0).getCountry());
            tmp.setEmail_address(vcard.getEmails().get(0).getValue());
            tmp.setFirstname(vcard.getStructuredName().getFamily());
            tmp.setLastname(vcard.getStructuredName().getGiven());
            tmp.setNickname(vcard.getStructuredName().getPrefixes().get(0));
            tmp.setPhone_number(vcard.getTelephoneNumbers().get(0).getText());
            if (DbManager.getInstance().addContact(tmp)){
                personData.add(tmp);
                listPerson.getItems().add(tmp);
            }
        }
    }

    @FXML
    public void onImport() throws IOException {
        File file = fileChooser.showOpenDialog(ap.getScene().getWindow());
        if (file != null) {
            openFile(file);
        }
    }

    @FXML
    public void onEdit() throws IOException {
        Person tmp = new Person();
        tmp.setNickname(nickname.getText());
        tmp.setIdperson(listPerson.getSelectionModel().getSelectedItem().getIdperson());
        tmp.setAddress(adress.getText());
        tmp.setFirstname(name.getText());
        tmp.setLastname(lastname.getText());
        tmp.setEmail_address(mail.getText());
        tmp.setPhone_number(phone.getText());
        tmp.setBirth_date(Date.from(Instant.from(birthday.getValue().atStartOfDay(ZoneId.systemDefault()))));
        if (DbManager.getInstance().editContact(tmp)){
            personData.remove(listPerson.getSelectionModel().getSelectedItem());
            listPerson.getItems().remove(listPerson.getSelectionModel().getSelectedItem());
            personData.add(tmp);
            listPerson.getItems().add(tmp);
        }
    }

    @FXML
    public void changeState() throws IOException {
        System.out.println("je passe ici" + submitButton.isVisible() + editButton.isVisible() );
        submitButton.setVisible(!submitButton.isVisible());
        editButton.setVisible(!editButton.isVisible());
        System.out.println("je passe ici" + submitButton.isVisible() + editButton.isVisible());
        if (editButton.isVisible()) {
            Person tmp = listPerson.getSelectionModel().getSelectedItem();
            nickname.setText(tmp.getNickname());
            adress.setText(tmp.getAddress());
            name.setText(tmp.getFirstname());
            lastname.setText(tmp.getLastname());
            mail.setText(tmp.getEmail_address());
            phone.setText(tmp.getPhone_number());
        }
        else {
            nickname.setText("");
            adress.setText("");
            name.setText("");
            lastname.setText("");
            mail.setText("");
            phone.setText("");
        }
    }
}
