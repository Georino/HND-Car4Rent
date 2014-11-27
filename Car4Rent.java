package car4rent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeMap;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author George Botos
 */
public class Car4Rent extends Application{
    
    String amla;
    Customer customer = null;
    Administrator administrator = null;
    Car car;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public void start(Stage stage) {
        stage.setTitle("Car4Rent");
        
        //Open Page
        GridPane openPage = new GridPane();
            openPage.setAlignment(Pos.CENTER);
            openPage.setHgap(10);
            openPage.setVgap(10);
            openPage.setPadding(new Insets(25));
        Text openPageWelcome = new Text("Car4Rent");
            openPage.add(openPageWelcome, 0, 0, 2, 1);
            openPageWelcome.setId("welcome-text");
        Label instruction1 = new Label("To log in click one of the buttons below:");
            openPage.add(instruction1, 0, 2, 2, 1);
        VBox logInHBox = new VBox();
            logInHBox.setPadding(new Insets(10));
            logInHBox.setSpacing(10);
            //logInHBox.setAlignment(Pos.CENTER);
            Button logInCust = new Button("Log In as a Customer");
                logInHBox.getChildren().add(logInCust);
                logInCust.setId("large-button");
            Button logInAdmin = new Button("Log ins as an Administrator");
                logInAdmin.setId("large-button");
                logInHBox.getChildren().add(logInAdmin);
            openPage.add(logInHBox, 0, 3);
        Label instruction2 = new Label("To register click  button below:");
            openPage.add(instruction2, 0, 5, 2, 1);
        VBox regVBox = new VBox();
            regVBox.setPadding(new Insets(10));
            regVBox.setSpacing(10);
            Button regCustomer = new Button("Register as a Customer");
                regCustomer.setId("large-button");
                regVBox.getChildren().add(regCustomer);
            openPage.add(regVBox, 0, 6);
        HBox exitHBox = new HBox();
            exitHBox.setAlignment(Pos.CENTER_RIGHT);
            exitHBox.setPadding(new Insets(10));
            Button exit = new Button("Exit Program");
                exitHBox.getChildren().add(exit);
            openPage.add(exitHBox, 1, 7);
        Scene openPageScene = new Scene(openPage, 600, 500);
            openPageScene.getStylesheets().add(Car4Rent.class.getResource("Car4Rent.css").toExternalForm());
        
        //Customer Log In Page
        GridPane CLIPage = new GridPane();
            CLIPage.setAlignment(Pos.CENTER);
            CLIPage.setHgap(10);
            CLIPage.setVgap(10);
            CLIPage.setPadding(new Insets(25));
            Text CLITitle = new Text ("Customer Log In");
                CLIPage.add(CLITitle, 0, 0, 2, 1);
                CLITitle.setId("welcome-text");
            Label usernameLabel = new Label("Username: ");
                CLIPage.add(usernameLabel, 0, 1);
            TextField username = new TextField();
                CLIPage.add(username, 1, 1);
            Label passwordLabel = new Label("Password: ");
                CLIPage.add(passwordLabel, 0, 2);
            PasswordField password = new PasswordField();
                CLIPage.add(password, 1, 2);
            VBox buttonVBox = new VBox();
                buttonVBox.setAlignment(Pos.CENTER_RIGHT);
                buttonVBox.setPadding(new Insets(10));
                buttonVBox.setSpacing(10);
                Button logInCustomer = new Button("Log In");
                    buttonVBox.getChildren().add(logInCustomer);
                Button back = new Button("Back");
                    buttonVBox.getChildren().add(back);
                CLIPage.add(buttonVBox, 1, 3);
            Text CLError = new Text();
                CLError.setId("error");
                CLIPage.add(CLError, 0, 4, 2, 1);
        Scene CLIPageScene = new Scene(CLIPage, 600, 500);
            CLIPageScene.getStylesheets().add(Car4Rent.class.getResource("Car4Rent.css").toExternalForm());
        
        // Administrator Log In page    
        GridPane ALIPage = new GridPane();
            ALIPage.setAlignment(Pos.CENTER);
            ALIPage.setHgap(10);
            ALIPage.setVgap(10);
            ALIPage.setPadding(new Insets(25));
            Text ALITitle = new Text ("Administrator Log In");
                ALIPage.add(ALITitle, 0, 0, 2, 1);
                ALITitle.setId("welcome-text");
            Label usernameLabel2 = new Label("Username: ");
                ALIPage.add(usernameLabel2, 0, 1);
            TextField usernameA = new TextField();
                ALIPage.add(usernameA, 1, 1);
            Label passwordLabel2 = new Label("Password: ");
                ALIPage.add(passwordLabel2, 0, 2);
            PasswordField passwordA = new PasswordField();
                ALIPage.add(passwordA, 1, 2);
            VBox buttonVBox2 = new VBox();
                buttonVBox2.setAlignment(Pos.CENTER_RIGHT);
                buttonVBox2.setPadding(new Insets(10));
                buttonVBox2.setSpacing(10);
                Button logInAdministrator = new Button("Log In ");
                    buttonVBox2.getChildren().add(logInAdministrator);
                Button back2 = new Button("Back");
                    buttonVBox2.getChildren().add(back2);
                ALIPage.add(buttonVBox2, 1, 3);
            Text ALError = new Text();
                ALError.setId("error");
                ALIPage.add(ALError, 0, 4, 2, 1);
        Scene ALIPageScene = new Scene(ALIPage, 600, 500);
            ALIPageScene.getStylesheets().add(Car4Rent.class.getResource("Car4Rent.css").toExternalForm());
        
        //Customer Registration page
        GridPane CRPage = new GridPane();
            CRPage.setAlignment(Pos.CENTER);
            CRPage.setHgap(10);
            CRPage.setVgap(10);
            CRPage.setPadding(new Insets(25));
            Text CRPTitle = new Text("Customer Registration");
                CRPage.add(CRPTitle, 0, 0, 2, 1);
                CRPTitle.setId("welcome-text");
            Label usernameLabel3 = new Label("Username: ");
                CRPage.add(usernameLabel3, 0, 1);
                usernameLabel3.setId("actiontarget");
            TextField usernameCR = new TextField();
                CRPage.add(usernameCR, 1, 1);
            Label passwordLableCR = new Label("Password: ");
                CRPage.add(passwordLableCR, 0, 2);
            PasswordField passwordCR = new PasswordField();
                CRPage.add(passwordCR, 1, 2);
            Label passwordConfirmLabel = new Label("Confirm Password: ");
                CRPage.add(passwordConfirmLabel, 0, 3);
            PasswordField passvordConfirmCR = new PasswordField();
                CRPage.add(passvordConfirmCR, 1, 3);
            Label fullNameLabel= new Label("Full name: ");
                CRPage.add(fullNameLabel, 0, 4);
            TextField fullNameCR = new TextField();
                CRPage.add(fullNameCR, 1, 4);
            Label dOBCRLabel = new Label("Dat of Birth: ");
                CRPage.add(dOBCRLabel, 0, 5);
            TextField dOBCR = new TextField();
                CRPage.add(dOBCR, 1, 5);
            Label addLine1CRLabel = new Label("Address Line 1: ");
                CRPage.add(addLine1CRLabel, 0, 6);
            TextField addLine1CR = new TextField();
                CRPage.add(addLine1CR, 1, 6);
            Label addLine2CRLabel = new Label("Address Line 2: ");
                CRPage.add(addLine2CRLabel, 0, 7);
            TextField addLine2CR = new TextField();
                CRPage.add(addLine2CR, 1, 7);
            Label townCRLabel = new Label("Town: ");
                CRPage.add(townCRLabel, 0, 8);
            TextField townCR = new TextField();
                CRPage.add(townCR, 1, 8);
            Label postCodeCRLabel = new Label("Post Code: ");
                CRPage.add(postCodeCRLabel, 0, 9);
            TextField postCodeCR = new TextField();
                CRPage.add(postCodeCR, 1, 9);
            Label phoneCRLabel = new Label("Phone Number: ");
                CRPage.add(phoneCRLabel, 0, 10);
            TextField phoneCR = new TextField();
                CRPage.add(phoneCR, 1, 10);
            Label eMailCRLabel = new Label("E-mail Address: ");
                CRPage.add(eMailCRLabel, 0, 11);
            TextField eMailCR = new TextField();
                CRPage.add(eMailCR, 1, 11);
            Label drivingNoCRLabel = new Label("Driving Licence Number: ");
                CRPage.add(drivingNoCRLabel, 0, 12);
            TextField drivingNoCR = new TextField();
                CRPage.add(drivingNoCR, 1, 12);
            VBox CRVBox = new VBox();
                CRVBox.setAlignment(Pos.CENTER_RIGHT);
                CRVBox.setPadding(new Insets(10));
                CRVBox.setSpacing(10);
                Button registerCustomer = new Button("Register");
                    CRVBox.getChildren().add(registerCustomer);
                Button back3 = new Button("Back");
                    CRVBox.getChildren().add(back3);
                CRPage.add(CRVBox, 1, 13);
            Text regError = new Text();
                CRPage.add(regError, 0, 15, 2, 1);
                regError.setId("error");
        Scene CRPageScene = new Scene(CRPage, 600, 650);
            CRPageScene.getStylesheets().add(Car4Rent.class.getResource("Car4Rent.css").toExternalForm());
        
        //Customer Home Page
        GridPane customerPage = new GridPane();
            customerPage.setAlignment(Pos.CENTER);
            customerPage.setHgap(10);
            customerPage.setVgap(10);
            customerPage.setPadding(new Insets(25));
            Text CPTitle = new Text("Welcome on Car4Rent's\nBooking System!");
                customerPage.add(CPTitle, 0, 0, 2, 1);
                CPTitle.setId("welcome-text");
            Label instructions = new Label("To start booking  a car, choose a date, please!");
                customerPage.add(instructions, 0, 1, 2, 1);
            Label chooseDateLabel = new Label("Choose Date:");
                customerPage.add(chooseDateLabel, 0, 2);
            DatePicker datePicker = new DatePicker();
                customerPage.add(datePicker, 1, 2);
            Label chooseCarLabel = new Label("Choose Car: ");
            HBox CRPHBox = new HBox();
                CRPHBox.setPadding(new Insets(25));
                CRPHBox.setSpacing(10);
                CRPHBox.setAlignment(Pos.CENTER_RIGHT);
                Button book = new Button("Book Car");
                    CRPHBox.getChildren().add(book);
            Text CPError = new Text("");
                CPError.setId("error");
                customerPage.add(CPError, 0, 6, 2, 1);
        Scene customerPageScene = new Scene(customerPage, 600, 500);
            customerPageScene.getStylesheets().add(Car4Rent.class.getResource("Car4Rent.css").toExternalForm());
            
        //Administrator Page
        BorderPane adminPage = new BorderPane();
            //Admin Home Page
            GridPane adminHomePage = new GridPane();
                adminHomePage.setAlignment(Pos.CENTER);
                adminHomePage.setHgap(10);
                adminHomePage.setVgap(10);
                adminHomePage.setPadding(new Insets(25));
                Text AHPTitle = new Text("Administrator Home Page");
                    adminHomePage.add(AHPTitle, 0, 0, 2, 1);
                    AHPTitle.setId("welcome-text");
                HBox AHPHBox = new HBox();
                    AHPHBox.setAlignment(Pos.CENTER);
                    AHPHBox.setPadding(new Insets(25));
                    AHPHBox.setSpacing(25);
                    Button toCars = new Button("Manage Cars");
                        toCars.setId("large-button");
                        AHPHBox.getChildren().add(toCars);
                    Button toCustomers = new Button("Manage Customers");
                        toCustomers.setId("large-button");
                        AHPHBox.getChildren().add(toCustomers);
                    Button listRentals = new Button("List Car Rentals");
                        listRentals.setId("large-button");
                        AHPHBox.getChildren().add(listRentals);
                    adminHomePage.add(AHPHBox, 0, 1, 2, 1);
                ComboBox<String> carToBook = new ComboBox();
                VBox AHPVBox = new VBox();
                    AHPVBox.setAlignment(Pos.CENTER);
                    AHPVBox.setPadding(new Insets(25));
                    AHPVBox.setSpacing(10);
                    Button logOutAdmin = new Button("Log out");
                        AHPVBox.getChildren().add(logOutAdmin);
                    Button exitAdmin = new Button("Exit Program");
                        AHPVBox.getChildren().add(exitAdmin);
            adminPage.setCenter(adminHomePage);
            //Customer Administratrion Page
            GridPane customerAdminPage = new GridPane();
                customerAdminPage.setAlignment(Pos.CENTER);
                customerAdminPage.setHgap(10);
                customerAdminPage.setVgap(10);
                customerAdminPage.setPadding(new Insets(25));
                Text CuAPTitle = new Text("Customer Administration");
                    CuAPTitle.setId("welcome-text");
                    customerAdminPage.add(CuAPTitle, 0, 0, 2, 1);
                VBox CuAPVBox = new VBox();
                    CuAPVBox.setAlignment(Pos.CENTER);
                    CuAPVBox.setPadding(new Insets(25));
                    CuAPVBox.setSpacing(10);
                    Label instruction4 = new Label("Start customer search with the right button:");
                    Label customerLabel = new Label("Customer ID");
                        CuAPVBox.getChildren().addAll(instruction4, customerLabel);
                    HBox CuAPHBox = new HBox();
                        CuAPHBox.setAlignment(Pos.CENTER);
                        CuAPHBox.setSpacing(10);
                        Button previousCustomer = new Button("<-");
                            CuAPHBox.getChildren().add(previousCustomer);
                        TextField customerID = new TextField("");
                            customerID.setId("id-display");
                            customerID.setPrefColumnCount(3);
                            CuAPHBox.getChildren().add(customerID);
                        Button nextCustomer = new Button("->");
                            CuAPHBox.getChildren().add(nextCustomer);
                        CuAPVBox.getChildren().add(CuAPHBox);
                    customerAdminPage.add(CuAPVBox, 1, 1);
                Label customerNameLabel = new Label("Full Name: ");
                    customerAdminPage.add(customerNameLabel, 0, 2);
                TextField cName = new TextField("");
                    customerAdminPage.add(cName, 1, 2);
                Label customerUsernameLabel = new Label("Username: ");
                    customerAdminPage.add(customerUsernameLabel, 0, 3);
                TextField cUsername = new TextField("");
                    customerAdminPage.add(cUsername, 1, 3);
                Label customerPasswordLabel = new Label("Password: ");
                    customerAdminPage.add(customerPasswordLabel, 0, 4);
                TextField cPassword = new TextField("");
                    customerAdminPage.add(cPassword, 1, 4);
                Label customerDOBLabel = new Label("Date of Birth: ");
                    customerAdminPage.add(customerDOBLabel, 0, 5);
                TextField cDOB = new TextField("");
                    customerAdminPage.add(cDOB, 1, 5);
                Label customerAddressLine1Label = new Label("Address Line 1: ");
                    customerAdminPage.add(customerAddressLine1Label, 0, 6);
                TextField cAddressLine1 = new TextField("");
                    customerAdminPage.add(cAddressLine1, 1, 6);
                Label customerAddressLine2Label = new Label("Address Line 2: ");
                    customerAdminPage.add(customerAddressLine2Label, 0, 7);
                TextField cAddressLine2 = new TextField("");
                    customerAdminPage.add(cAddressLine2, 1, 7);
                Label customerTownLabel = new Label("Town: ");
                    customerAdminPage.add(customerTownLabel, 0, 8);
                TextField cTown = new TextField("");
                    customerAdminPage.add(cTown, 1, 8);
                Label customerPostCodeLabel = new Label("Post Code: ");
                    customerAdminPage.add(customerPostCodeLabel, 0, 9);
                TextField cPostCode = new TextField("");
                    customerAdminPage.add(cPostCode, 1, 9);
                Label customerPhoneLabel = new Label("Phone: ");
                    customerAdminPage.add(customerPhoneLabel, 0, 10);
                TextField cPhone = new TextField("");
                    customerAdminPage.add(cPhone, 1, 10);
                Label customerEMailLabel = new Label("E-mail Address: ");
                    customerAdminPage.add(customerEMailLabel, 0, 11);
                TextField cEMail = new TextField("");
                    customerAdminPage.add(cEMail, 1, 11);
                Label customerDLNLabel = new Label("Driving Licence No.: ");
                    customerAdminPage.add(customerDLNLabel, 0, 12);
                TextField cDrivingLicenceNo = new TextField("");
                    customerAdminPage.add(cDrivingLicenceNo, 1, 12);
                HBox CAPHBox4 = new HBox();
                    CAPHBox4.setAlignment(Pos.TOP_RIGHT);
                    CAPHBox4.setSpacing(10);
                    Button saveCustomer = new Button("Save & New Customer");
                    Button cClear = new Button("Clear");
                    Button updateCustomer = new Button("Save Amendments to Customer");
                        CAPHBox4.getChildren().addAll(updateCustomer);
                    customerAdminPage.add(CAPHBox4, 0, 13, 2, 1);
                HBox CAPHBox5 = new HBox();
                    CAPHBox5.setAlignment(Pos.TOP_RIGHT);
                    CAPHBox5.setPadding(new Insets(25));
                    CAPHBox5.setSpacing(10);
                        Button backToAdminPage3 = new Button("Back to Home Screen");
                        Button backToCustomerReg = new Button("Back to Car Registration");
                        Button newCustomer = new Button("Enter New Customer");
                        CAPHBox5.getChildren().addAll(backToAdminPage3, newCustomer);
                    customerAdminPage.add(CAPHBox5, 0, 14, 2, 1);    
                Text CuAPError = new Text();
                    CuAPError.setId("error");
                    customerAdminPage.add(CuAPError, 0, 15, 2, 1);
                
            //CarAdministration Page
            GridPane carAdminPage = new GridPane();
                carAdminPage.setAlignment(Pos.CENTER);
                carAdminPage.setHgap(10);
                carAdminPage.setVgap(10);
                carAdminPage.setPadding(new Insets(25));
                Text CAPTitle = new Text("Car Administration");
                    CAPTitle.setId("welcome-text");
                    carAdminPage.add(CAPTitle, 0, 0, 2, 1);
                VBox CAPVBox = new VBox();
                    CAPVBox.setAlignment(Pos.CENTER);
                    CAPVBox.setPadding(new Insets(25));
                    CAPVBox.setSpacing(10);
                    Label instruction3 = new Label("Start car search with the right button:");
                    Label carLabel = new Label("Car ID");
                        CAPVBox.getChildren().addAll(instruction3, carLabel);
                    HBox CAPHBox = new HBox();
                        CAPHBox.setAlignment(Pos.CENTER);
                        CAPHBox.setSpacing(10);
                        Button previousCar = new Button("<-");
                            CAPHBox.getChildren().add(previousCar);
                        TextField carID = new TextField("");
                            carID.setId("id-display");
                            carID.setPrefColumnCount(3);
                            CAPHBox.getChildren().add(carID);
                        Button nextCar = new Button("->");
                            CAPHBox.getChildren().add(nextCar);
                        CAPVBox.getChildren().add(CAPHBox);
                    carAdminPage.add(CAPVBox, 1, 1);
                Label regNoLabel = new Label("Registration Number: ");
                    carAdminPage.add(regNoLabel, 0, 2);
                TextField regNo = new TextField("");
                    carAdminPage.add(regNo, 1, 2);
                Label makeLabel = new Label("Make: ");
                    carAdminPage.add(makeLabel, 0, 3);
                TextField make = new TextField("");
                    carAdminPage.add(make, 1, 3);
                Label colourLabel = new Label("Colour: ");
                    carAdminPage.add(colourLabel, 0, 4);
                TextField colour = new TextField("");
                    carAdminPage.add(colour, 1, 4);
                Label dailyPriceLabel = new Label("Daily Price (£): ");
                    carAdminPage.add(dailyPriceLabel, 0, 5);
                TextField dailyPrice = new TextField("");
                    carAdminPage.add(dailyPrice, 1, 5);
                Label stateLabel = new Label("State of the Car:");
                    carAdminPage.add(stateLabel, 0, 6);
                ObservableList<String> stateTypes = FXCollections.observableArrayList("Active", "Sold", "Written off");
                ComboBox<String> stateOfCar = new ComboBox(stateTypes);
                    carAdminPage.add(stateOfCar, 1, 6);
                HBox CAPHBox2 = new HBox();
                    CAPHBox2.setAlignment(Pos.TOP_RIGHT);
                    CAPHBox2.setSpacing(10);
                    Button saveCar = new Button("Save & New Car");
                    Button clear = new Button("Clear");
                    Button updateCar = new Button("Save Amendments to Car");
                        CAPHBox2.getChildren().addAll(updateCar);
                    carAdminPage.add(CAPHBox2, 0, 7, 2, 1);
                HBox CAPHBox3 = new HBox();
                    CAPHBox3.setAlignment(Pos.TOP_RIGHT);
                    CAPHBox3.setPadding(new Insets(25));
                    CAPHBox3.setSpacing(10);
                        Button backToAdminPage = new Button("Back to Home Screen");
                        Button backToCarReg = new Button("Back to Car Registration");
                        Button newCar = new Button("Enter New Car");
                        CAPHBox3.getChildren().addAll(backToAdminPage, newCar);
                    carAdminPage.add(CAPHBox3, 0, 8, 2, 1);
                Text CAPError = new Text();
                    CAPError.setId("error");
                    carAdminPage.add(CAPError, 0, 9, 2, 1);
            //ListRentals Page
            GridPane listPage = new GridPane();
                listPage.setAlignment(Pos.CENTER);
                listPage.setHgap(10);
                listPage.setVgap(10);
                listPage.setPadding(new Insets(25));
                Text LPTitle = new Text("List Rentals Page");
                    LPTitle.setId("welcome-text");
                    listPage.add(LPTitle, 0, 0, 2, 1);
                Label pickerLabel = new Label("Select Date: ");
                    listPage.add(pickerLabel, 0, 1);
                DatePicker datePicker2 = new DatePicker();
                    listPage.add(datePicker2, 1, 1);
                TableView rentedCars = new TableView();
                    listPage.add(rentedCars, 0, 2, 2, 1);
                    TableColumn colCarID = new TableColumn("Car ID");
                        colCarID.setMinWidth(80);
                    TableColumn colRegNo = new TableColumn("Reg No");
                        colRegNo.setMinWidth(120);
                    TableColumn colMake = new TableColumn("Make");
                        colMake.setMinWidth(220);
                    TableColumn colCustName = new TableColumn("Customer Name");
                        colCustName.setMinWidth(170);
                    rentedCars.getColumns().addAll(colCarID, colRegNo, colMake, colCustName);
                HBox LPHBox = new HBox();
                    LPHBox.setAlignment(Pos.CENTER_RIGHT);
                    Button backToAdminPage2 = new Button("Back");
                    LPHBox.getChildren().add(backToAdminPage2);
                    listPage.add(LPHBox, 1, 3);
        Scene adminPageScene = new Scene(adminPage, 700, 850);
            adminPageScene.getStylesheets().add(Car4Rent.class.getResource("Car4Rent.css").toExternalForm());
        
            
            
            
        logInCust.setOnAction(ea -> stage.setScene(CLIPageScene));
        logInAdmin.setOnAction(ea -> stage.setScene(ALIPageScene));
        regCustomer.setOnAction(ea -> stage.setScene(CRPageScene));
        logInCustomer.setOnAction(ea -> {   String usern = username.getText();
                                            String pass = password.getText();
                                            int dbCustomerNumber;
                                            boolean CustomerLogInSuccessful;
                                            if(usern.isEmpty())
                                                CLError.setText("Please enter your Username!");
                                            else if(pass.isEmpty())
                                                    CLError.setText("Please enter your Password!");
                                            else {
                                                DB db = new DB();
                                                dbCustomerNumber = db.retrieveCustomerNumber(usern, pass);
                                                if(dbCustomerNumber==-1)
                                                    CLError.setText("Either Password or Username is incorrect!");
                                                else {
                                                    customer = db.retrieveCustomer(dbCustomerNumber);
                                                    stage.setScene(customerPageScene);
                                                }
                                            }
        
        });
        back.setOnAction(ea -> {stage.setScene(openPageScene);
                                username.setText("");
                                password.setText("");
        });
        exit.setOnAction(ea -> Platform.exit());
        logInAdministrator.setOnAction(ea -> {  String usernA = usernameA.getText();
                                                String passA = passwordA.getText();
                                                int adminID;
                                                boolean administratorLogInSuccessful;
                                                if(usernA.isEmpty())
                                                    ALError.setText("Please enter Username! Admin");
                                                else if(passA.isEmpty())
                                                    ALError.setText("Please enter Password! admin");
                                                else {
                                                    DB db = new DB();
                                                    adminID = db.getAdministratorID(usernA, passA);
                                                    if(adminID==-1)
                                                        ALError.setText("Either Password or Username is incorrect!");
                                                    else {
                                                        administrator = db.logInAdministrator(adminID);
                                                        stage.setScene(adminPageScene);
                                                    }
                                                }
                                                    
        });
        back2.setOnAction(ea -> {   stage.setScene(openPageScene);
                                    usernameA.setText("");
                                    passwordA.setText("");
                                    ALError.setText("");
        });
        registerCustomer.setOnAction(ea -> {String usern = usernameCR.getText();
                                            String pass = passwordCR.getText();
                                            String pass2 = passvordConfirmCR.getText();
                                            String name = fullNameCR.getText();
                                            String dob = dOBCR.getText();
                                            String add1 = addLine1CR.getText();
                                            String add2 = addLine2CR.getText();
                                            String town = townCR.getText();
                                            String postC = postCodeCR.getText();
                                            String phoneN = phoneCR.getText();
                                            String email = eMailCR.getText();
                                            String drivingLN = drivingNoCR.getText();
                                            boolean successfulRegistration;
                                            if (usern.isEmpty()) {
                                                regError.setText("Please enter Username!");
                                                return;
                                            }    
                                            else if(pass.isEmpty()) {
                                                regError.setText("Please enter Password!");
                                                return;
                                            }
                                            else if(pass2.isEmpty()) {
                                                regError.setText("Please confirm Password!");
                                                return;
                                            }
                                            else if(name.isEmpty()) {
                                                regError.setText("Please enter your Full Name!");
                                                return;
                                            }
                                            else if(dob.isEmpty()) {
                                                regError.setText("Please enter your Date of Birth!");
                                                return;
                                            }
                                            else if(add1.isEmpty()) {
                                                regError.setText("Please enter the First Line of your Address!");
                                                return;
                                            }
                                            else if(town.isEmpty()) {
                                                regError.setText("Please enter your Town!");
                                                return;
                                            }
                                            else if(postC.isEmpty()) {
                                                regError.setText("Please enter your Post Code!");
                                                return;
                                            }
                                            else if(phoneN.isEmpty()) {
                                                regError.setText("Please enter your Phone Number!");
                                                return;
                                            }
                                            else if(email.isEmpty()) {
                                                regError.setText("Please enter your E-mail Address!");
                                                return;
                                            }
                                            else if(drivingLN.isEmpty()) {
                                                regError.setText("Please enter your Driving Licence Number!");
                                                return;
                                            }
                                            else {
                                                if (pass.equals(pass2)) {
                                                    regError.setText("Processig...");
                                                    Customer cust = new Customer(name, dob, add1, add2, town, postC, phoneN, email, usern, pass, drivingLN);
                                                    successfulRegistration = cust.addUser();
                                                    if(successfulRegistration) {
                                                        stage.setScene(openPageScene);
                                                    }
                                                    else
                                                        regError.setText("There was an error with registration! Please contact Staff!");
                                                }
                                                else
                                                    regError.setText("Your Passwords do not match! Try Again!");
                                            }
        });
        back3.setOnAction(ea -> {   stage.setScene(openPageScene);
                                    usernameCR.setText("");
                                    passwordCR.setText("");
                                    passvordConfirmCR.setText("");
                                    fullNameCR.setText("");
                                    dOBCR.setText("");
                                    addLine1CR.setText("");
                                    addLine2CR.setText("");
                                    townCR.setText("");
                                    postCodeCR.setText("");
                                    phoneCR.setText("");
                                    eMailCR.setText("");
                                    drivingNoCR.setText("");
        });
        toCars.setOnAction(ea -> adminPage.setCenter(carAdminPage));
        toCustomers.setOnAction(ea -> adminPage.setCenter(customerAdminPage));
        logOutAdmin.setOnAction(ea -> { administrator = null;
                                        stage.setScene(openPageScene);});
        exitAdmin.setOnAction(ea -> Platform.exit());
        previousCustomer.setOnAction(ea ->  {   DB db = new DB();
                                                TreeMap<Integer, Customer> customers = new TreeMap();
                                                customers = db.retrieveCustomers();
                                                String posString = customerID.getText();
                                                int position = Integer.parseInt(posString);
                                                if(position>1){
                                                    CuAPError.setText("");
                                                    position--;
                                                    customer = customers.get(position);
                                                    customerID.setText(Integer.toString(customer.getCustomerNo()));
                                                    cName.setText(customer.getName());
                                                    cUsername.setText(customer.getUsername());
                                                    cPassword.setText(customer.getPassword());
                                                    cDOB.setText(customer.getDOB());
                                                    cAddressLine1.setText(customer.getAddressLine1());
                                                    cAddressLine2.setText(customer.getAddressLine2());
                                                    cTown.setText(customer.getTown());
                                                    cPostCode.setText(customer.getPostCode());
                                                    cPhone.setText(customer.getPhone());
                                                    cEMail.setText(customer.getEMail());
                                                    cDrivingLicenceNo.setText(customer.getDrivingLicenceNumber());
                                                    }
                                                    else
                                                        CAPError.setText("You are at first customer in database!");
        }); 
        nextCustomer.setOnAction(ea -> {CuAPError.setText("");
                                        DB db = new DB();
                                        TreeMap<Integer, Customer> customers = new TreeMap();
                                        customers = db.retrieveCustomers();
                                        String posString = customerID.getText();
                                        int position = 0;
                                        if(posString.isEmpty()) {
                                            position = 1;
                                            instruction4.setText("");
                                            customer = customers.get(position);
                                            if(customer == null)
                                                CuAPError.setText("There is no customer in database!");
                                            else {
                                                customerID.setText(Integer.toString(customer.getCustomerNo()));
                                                cName.setText(customer.getName());
                                                cUsername.setText(customer.getUsername());
                                                cPassword.setText(customer.getPassword());
                                                cDOB.setText(customer.getDOB());
                                                cAddressLine1.setText(customer.getAddressLine1());
                                                cAddressLine2.setText(customer.getAddressLine2());
                                                cTown.setText(customer.getTown());
                                                cPostCode.setText(customer.getPostCode());
                                                cPhone.setText(customer.getPhone());
                                                cEMail.setText(customer.getEMail());
                                                cDrivingLicenceNo.setText(customer.getDrivingLicenceNumber());
                                            }
                                        }
                                        else {
                                            posString = customerID.getText();
                                            position = Integer.parseInt(posString);
                                            customer = customers.get(++position);
                                            if(customer == null)
                                                CuAPError.setText("There is no more customer in database!");
                                            else {
                                                customerID.setText(Integer.toString(customer.getCustomerNo()));
                                                cName.setText(customer.getName());
                                                cUsername.setText(customer.getUsername());
                                                cPassword.setText(customer.getPassword());
                                                cDOB.setText(customer.getDOB());
                                                cAddressLine1.setText(customer.getAddressLine1());
                                                cAddressLine2.setText(customer.getAddressLine2());
                                                cTown.setText(customer.getTown());
                                                cPostCode.setText(customer.getPostCode());
                                                cPhone.setText(customer.getPhone());
                                                cEMail.setText(customer.getEMail());
                                                cDrivingLicenceNo.setText(customer.getDrivingLicenceNumber());
                                            }
                                        }
        });
        saveCustomer.setOnAction(ea -> {DB db = new DB();                                        
                                        boolean successFlag;
                                        if(cName.getText().isEmpty())
                                            CuAPError.setText("Please enter Full Name!");
                                        else if(cUsername.getText().isEmpty())
                                            CuAPError.setText("Please enter Username!");
                                        else if(cPassword.getText().isEmpty())
                                            CuAPError.setText("Please enter Password!");
                                        else if(cDOB.getText().isEmpty())
                                            CuAPError.setText("Please enter Date of Birth!");
                                        else if(cAddressLine1.getText().isEmpty())
                                            CuAPError.setText("Please enter Address Line 1!");
                                        else if(cTown.getText().isEmpty())
                                            CuAPError.setText("Please enter Town");
                                        else if(cPostCode.getText().isEmpty())
                                            CuAPError.setText("Please enter PostCode!");
                                        else if(cPhone.getText().isEmpty())
                                            CuAPError.setText("Please enter Phone!");
                                        else if(cEMail.getText().isEmpty())
                                            CuAPError.setText("Please enter E-mail Address!");
                                        else if(cDrivingLicenceNo.getText().isEmpty())
                                            CuAPError.setText("Please enter Driving Licence Number!");
                                        else {
                                            successFlag = db.saveCustomer(cUsername.getText(), cDOB.getText(), cAddressLine1.getText(), cAddressLine2.getText(), cTown.getText(), cPostCode.getText(), cPhone.getText(), cEMail.getText(), cPassword.getText(), cDrivingLicenceNo.getText(), cName.getText());
                                            if(successFlag)
                                                CuAPError.setText("One customer was successfully saved!");
                                            else
                                                CuAPError.setText("Saving customer was unsuccessful");
                                                cName.setText("");
                                                cUsername.setText("");
                                                cPassword.setText("");
                                                cDOB.setText("");
                                                cAddressLine1.setText("");
                                                cAddressLine2.setText("");
                                                cTown.setText("");
                                                cPostCode.setText("");
                                                cPhone.setText("");
                                                cEMail.setText("");
                                                cDrivingLicenceNo.setText("");
                                    }        });
        cClear.setOnAction(ea -> {  CuAPError.setText("");
                                    cName.setText("");
                                    cUsername.setText("");
                                    cPassword.setText("");
                                    cDOB.setText("");
                                    cAddressLine1.setText("");
                                    cAddressLine2.setText("");
                                    cTown.setText("");
                                    cPostCode.setText("");
                                    cPhone.setText("");
                                    cEMail.setText("");
                                    cDrivingLicenceNo.setText("");
        });
        updateCustomer.setOnAction(ea -> {  int tCustID = 0;
                                            DB db = new DB();
                                            try {
                                                tCustID = Integer.parseInt(customerID.getText());
                                            } catch(NumberFormatException e) {
                                                CuAPError.setText("Please enter a whole number for Customer ID");
                                            }
                                            Customer cCustomer = new Customer(cName.getText(), cDOB.getText(), cAddressLine2.getText(), cAddressLine2.getText(), cTown.getText(), cPostCode.getText(), cPhone.getText(), cEMail.getText(), cUsername.getText(), cPassword.getText(), cDrivingLicenceNo.getText());
                                            db.amendCustomer(cCustomer);
                                            CuAPError.setText("Changes to customer are saved!");
        });
        backToAdminPage3.setOnAction(ea -> adminPage.setCenter(adminHomePage));
        backToCustomerReg.setOnAction(ea -> {   CuAPError.setText("");
                                                cName.setText("");
                                                cUsername.setText("");
                                                cPassword.setText("");
                                                cDOB.setText("");
                                                cAddressLine1.setText("");
                                                cAddressLine2.setText("");
                                                cTown.setText("");
                                                cPostCode.setText("");
                                                cPhone.setText("");
                                                cEMail.setText("");
                                                cDrivingLicenceNo.setText("");
                                                CuAPTitle.setText("Customer Administration");
                                                customerAdminPage.add(CuAPVBox, 1, 1);
                                                CAPHBox4.getChildren().remove(cClear);
                                                CAPHBox4.getChildren().remove(saveCustomer);
                                                CAPHBox5.getChildren().remove(backToCustomerReg);
                                                CAPHBox4.getChildren().add(updateCustomer);
                                                CAPHBox5.getChildren().add(newCustomer);}
        );
        newCustomer.setOnAction(ea -> { CuAPError.setText("");
                                        CuAPTitle.setText("New Customer Registration");
                                        customerAdminPage.getChildren().removeAll(CuAPVBox);
                                        CAPHBox4.getChildren().addAll(cClear, saveCustomer);
                                        CAPHBox4.getChildren().remove(updateCustomer);
                                        CAPHBox5.getChildren().remove(newCustomer);
                                        CAPHBox5.getChildren().add(backToCustomerReg);
                                        cName.setText("");
                                        cUsername.setText("");
                                        cPassword.setText("");
                                        cDOB.setText("");
                                        cAddressLine1.setText("");
                                        cAddressLine2.setText("");
                                        cTown.setText("");
                                        cPostCode.setText("");
                                        cPhone.setText("");
                                        cEMail.setText("");
                                        cDrivingLicenceNo.setText("");
        });
        previousCar.setOnAction(ea -> { DB db = new DB();
                                        TreeMap<Integer, Car> cars = new TreeMap();
                                        cars = db.retrieveCars();
                                        String posString = carID.getText();
                                        int position = Integer.parseInt(posString);
                                        if(position>1){
                                            CAPError.setText("");
                                            position--;
                                            car = cars.get(position);
                                            int tempCarID = car.getCarID();
                                            double tempPrice = car.getPrice();
                                            carID.setText(Integer.toString(tempCarID));
                                            regNo.setText(car.getRegNo());
                                            make.setText(car.getMake());
                                            colour.setText(car.getColour());
                                            dailyPrice.setText(Double.toString(tempPrice));
                                            stateOfCar.setValue(car.getState());
                                        }
                                        else
                                            CAPError.setText("You are at first car in database!");
        });
        nextCar.setOnAction(ea -> { CAPError.setText("");
                                    DB db = new DB();
                                    TreeMap<Integer, Car> cars = new TreeMap();
                                    cars = db.retrieveCars();
                                    String posString = carID.getText();
                                    int position = 0;
                                    if(posString.isEmpty()) {
                                        position = 1;
                                        instruction3.setText("");
                                        car = cars.get(position);
                                        if(car == null)
                                            CAPError.setText("There is no more car in database!");
                                        else {
                                            int tempCarID = car.getCarID();
                                            double tempPrice = car.getPrice();
                                            carID.setText(Integer.toString(tempCarID));
                                            regNo.setText(car.getRegNo());
                                            make.setText(car.getMake());
                                            colour.setText(car.getColour());
                                            dailyPrice.setText(Double.toString(tempPrice));
                                            stateOfCar.setValue(car.getState());
                                        }
                                    }
                                    else {
                                        posString = carID.getText();
                                        position = Integer.parseInt(posString);
                                        position++;
                                        car = cars.get(position);
                                        if(car == null)
                                            CAPError.setText("There is no more car in database!");
                                        else {
                                            int tempCarID = car.getCarID();
                                            double tempPrice = car.getPrice();
                                            carID.setText(Integer.toString(tempCarID));
                                            regNo.setText(car.getRegNo());
                                            make.setText(car.getMake());
                                            colour.setText(car.getColour());
                                            dailyPrice.setText(Double.toString(tempPrice));
                                            stateOfCar.setValue(car.getState());
                                        }
                                    }
        });
        newCar.setOnAction(ea -> {  CAPError.setText("");
                                    CAPTitle.setText("New Car Registration");
                                    carAdminPage.getChildren().removeAll(CAPVBox);
                                    CAPHBox2.getChildren().addAll(clear, saveCar);
                                    CAPHBox2.getChildren().remove(updateCar);
                                    CAPHBox3.getChildren().remove(newCar);
                                    CAPHBox3.getChildren().add(backToCarReg);
                                    regNo.setText("");
                                    make.setText("");
                                    colour.setText("");
                                    dailyPrice.setText("");
                                    stateOfCar.setValue("Active");
        });
        clear.setOnAction(ea -> {   regNo.setText("");
                                    make.setText("");
                                    colour.setText("");
                                    dailyPrice.setText("");
                                    stateOfCar.setValue("Active");
                                    CAPError.setText("");
        });
        saveCar.setOnAction(ea -> { DB db = new DB();
                                    double tPrice = -1;
                                    boolean successFlag;
                                    try {
                                        tPrice =  Double.parseDouble(dailyPrice.getText());
                                    } catch(NumberFormatException e) {
                                        CAPError.setText("Please enter a double value for price!\nFor Example: 100.00 or 100.0");
                                    }
                                    if(regNo.getText().isEmpty())
                                        CAPError.setText("Please enter Registration Number");
                                    else if(make.getText().isEmpty())
                                        CAPError.setText("Please enter Make of car!");
                                    else if(colour.getText().isEmpty())
                                        CAPError.setText("Please enter Colour of car!");
                                    else if(tPrice<0.0)
                                        CAPError.setText("Please enter a valid daily Price");
                                    else {
                                        Car car = new Car(regNo.getText(), make.getText(), colour.getText(),tPrice, stateOfCar.getValue());
                                        successFlag = db.saveNewCar(car);
                                        if(successFlag)
                                            CAPError.setText("One car was successfully saved!");
                                        else
                                            CAPError.setText("Saving car was unsuccessful");
                                            regNo.setText("");
                                            make.setText("");
                                            colour.setText("");
                                            dailyPrice.setText("");
                                            stateOfCar.setValue("Active");
                                    }        
        });
        updateCar.setOnAction(ea -> {   int tCarID = 0;
                                        double tPrice = 0.0;
                                        DB db = new DB();
                                        try {
                                            tCarID = Integer.parseInt(carID.getText());
                                        } catch(NumberFormatException e) {
                                            CAPError.setText("Please enter a whole number for Car ID");
                                        }
                                        try {
                                            tPrice = Double.parseDouble(dailyPrice.getText());
                                        } catch(NumberFormatException e) {
                                            CAPError.setText("Pleae enter daily price as a double!\nFor example: 100.00 or 100.0");
                                        }
                                        Car sCar = new Car(tCarID, regNo.getText(), make.getText(), colour.getText(), tPrice, stateOfCar.getValue());
                                        db.amendCar(sCar);
                                        CAPError.setText("Changes to car are saved!");
        });
        backToCarReg.setOnAction(ea -> {CAPError.setText("");
                                        regNo.setText("");
                                        make.setText("");
                                        colour.setText("");
                                        dailyPrice.setText("");
                                        stateOfCar.setValue("Active");
                                        CAPTitle.setText("Car Administration");
                                        carAdminPage.add(CAPVBox, 1, 1);
                                        CAPHBox2.getChildren().remove(clear);
                                        CAPHBox2.getChildren().remove(saveCar);
                                        CAPHBox3.getChildren().remove(backToCarReg);
                                        CAPHBox2.getChildren().add(updateCar);
                                        CAPHBox3.getChildren().add(newCar);
                                        
        });
        backToAdminPage.setOnAction(ea -> adminPage.setCenter(adminHomePage));
        datePicker.setOnAction(ea -> {  customerPage.getChildren().remove(chooseCarLabel);
                                        customerPage.getChildren().remove(carToBook);
                                        customerPage.getChildren().remove(CRPHBox);
                                        LocalDate chosenDate = datePicker.getValue();
                                        ArrayList<String> resultString = Booking.getAvailableCarsForBooking(chosenDate);
                                        ObservableList<String> availableCars = FXCollections.observableArrayList(resultString);
                                            instructions.setText("Now choose a vehicle!\nIf no vehicle is displayed in the drop down menu,\nwe have no car available on this date!");
                                            customerPage.add(chooseCarLabel, 0, 3);
                                            carToBook.setItems(availableCars);
                                            customerPage.add(carToBook, 1, 3);
                                            customerPage.add(CRPHBox, 1, 4);
                                        
        });
        book.setOnAction(ea -> {int tempCarID = -1;
                                String carChosenForBooking = carToBook.getValue();
                                if(carChosenForBooking.isEmpty())
                                    CPError.setText("Please choose a car from list!\nIF list is empty we have no available car on this date.");
                                else {
                                    String choice = carChosenForBooking.substring(carChosenForBooking.lastIndexOf(" ") + 1);
                                    tempCarID = Integer.parseInt(choice);
                                    Booking booking = new Booking(customer, Car.getCar(tempCarID), datePicker.getValue());
                                    booking.save();
                                    CPError.setText("Your booking is done. Thank you for your business!");
                                    instructions.setText("To make an other booking choose a date again!");
                                    CPTitle.setText("Car4Rent\nBooking System");
                                    carToBook.setItems(null);
                                    customerPage.getChildren().remove(chooseCarLabel);
                                    customerPage.getChildren().remove(carToBook);
                                    customerPage.getChildren().remove(CRPHBox);
                                }
                                
        });
        listRentals.setOnAction(ea -> adminPage.setCenter(listPage));
        datePicker2.setOnAction(ea -> { LocalDate date = datePicker2.getValue();
                                        ObservableList<BookingInfo> data = BookingInfo.getObservableListOfBookingInfo(date);
                                        colCarID.setCellValueFactory(new PropertyValueFactory<BookingInfo,String>("carID"));
                                        colRegNo.setCellValueFactory(new PropertyValueFactory<BookingInfo,String>("regNo"));
                                        colMake.setCellValueFactory(new PropertyValueFactory<BookingInfo,String>("make"));
                                        colCustName.setCellValueFactory(new PropertyValueFactory<BookingInfo,String>("customerName"));
                                        rentedCars.setItems(data);
        });
        backToAdminPage2.setOnAction(ea -> adminPage.setCenter(adminHomePage));
        
        stage.setScene(openPageScene);
        stage.show();
    }
}