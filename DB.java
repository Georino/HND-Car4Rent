package car4rent;

import static car4rent.PasswordHash.validatePassword;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;

/**
 *
 * @author georgebotos
 */
public class DB {
    
    private static Connection connectDB() {
        String dBUserName = "root";
        String dBPassword = "root";
        String url = "jdbc:mysql://localhost:8889/Car4Rent";
        Connection conn = null;
        
        try {
          Class.forName("com.mysql.jdbc.Driver").newInstance();
          conn = DriverManager.getConnection(url, dBUserName, dBPassword);
        } catch(Exception e) {
            System.err.println("Cannot connect to database server");
            e.printStackTrace();
        }
        return conn;  
    }
    
    public boolean saveCustomer(String username, String dOB, String addressLine1, String addressLine2, String town, String postCode, String phone, String eMail, String password, String drivingLicenceNumber, String fullName) {
        Connection conn;
        String query = "insert into Customer (username, dOB, addressLine1, addressLine2, town, postCode, phone, eMail, password, drivingLicenceNumber, userType, fullName) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
        try {
            conn = connectDB();
            PreparedStatement presta = conn.prepareStatement(query);
            presta.setString(1, username);
            presta.setString(2, dOB);
            presta.setString(3, addressLine1);
            presta.setString(4, addressLine2);
            presta.setString(5, town);
            presta.setString(6, postCode);
            presta.setString(7, phone);
            presta.setString(8, eMail);
            presta.setString(9, password);
            presta.setString(10, drivingLicenceNumber);
            presta.setString(11, "c");
            presta.setString(12, fullName);
            presta.execute();
            return true;
        } catch(Exception e) {
            return false;
        }
    }
    
    public int retrieveCustomerNumber(String username, String password) {
        int customerNumber = -1;
        int temp = -1;
        boolean checkFlag = false;
        String dbUsername = "";
        String dbPassword = "";
        String query = "SELECT * FROM Customer WHERE username='" + username + "'";
        try {
            Connection conn = connectDB();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                dbUsername = rs.getString("username");
                dbPassword = rs.getString("password");
                temp = rs.getInt("customerNumber");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        try {
            checkFlag = validatePassword(password, dbPassword);
        } catch(InvalidKeySpecException e) {
            System.out.println(e);
        } catch(NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        if(checkFlag) {
            customerNumber = temp;
            return customerNumber;
        } else
            return customerNumber;
    }
    
    public void amendCustomer(Customer customer) {
        String query = "UPDATE Customer SET  username = ?, dOB = ?, addressLine1 = ?, addressLine2 = ?, town = ?, postCode = ?,  phone = ?, eMail = ?, password = ?, drivingLicenceNumber = ?, fullName = ? WHERE customerNumber = ?";
        Connection conn;
        try {
            conn = connectDB();
            PreparedStatement preStatement = conn.prepareStatement(query);
            preStatement.setString(1, customer.getUsername());
            preStatement.setString(2, customer.getDOB());
            preStatement.setString(3, customer.getAddressLine1());
            preStatement.setString(4, customer.getAddressLine2());
            preStatement.setString(5, customer.getTown());
            preStatement.setString(6, customer.getPostCode());
            preStatement.setString(7, customer.getPhone());
            preStatement.setString(8, customer.getEMail());
            preStatement.setString(9, customer.getPassword());
            preStatement.setString(10, customer.getDrivingLicenceNumber());
            preStatement.setString(11, customer.getName());
            preStatement.setInt(12, customer.getCustomerNo());
            preStatement.executeUpdate();
        } catch(Exception e) {
            System.err.println("Error occured in amendCustomer: " + e);
        }
    }
    
    public Customer retrieveCustomer(int customerNumber) {
        String username = "";
        String fullName = "";
        String dOB = "";
        String addressLine1 = "";
        String addressLine2 = "";
        String town = "";
        String postCode = "";
        String phone = "";
        String eMail = "";
        String password = "";
        String drivingLicenceNumber = "";
        char userType = 'c';
        String query = "SELECT * FROM Customer WHERE customerNumber='" + customerNumber + "'";
        Connection conn;
        try {
            conn = connectDB();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                username = rs.getString("username");
                fullName = rs.getString("fullName");
                dOB = rs.getString("dOB");
                addressLine1 = rs.getString("addressLine1");
                addressLine2 = rs.getString("addressLine2");
                town = rs.getString("town");
                postCode = rs.getString("postCode");
                phone = rs.getString("phone");
                eMail = rs.getString("eMail");
                password = rs.getString("password");
                drivingLicenceNumber = rs.getString("drivingLicenceNumber");
                userType = (char) rs.getByte("userType");
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
        
        Customer customer = new Customer(fullName, dOB, addressLine1, addressLine2, town, postCode, phone, eMail, username, password, userType, drivingLicenceNumber, customerNumber);
        return customer;
    }

    public int getAdministratorID(String username, String password) {
        int adminID = -1;
        int temp = -1;
        boolean checkFlag = false;
        String dbUsername = "";
        String dbPassword = "";
        String query = "SELECT * FROM Administrator WHERE username='" + username + "'";
        try {
            Connection conn = connectDB();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                dbUsername = rs.getString("username");
                dbPassword = rs.getString("password");
                temp = rs.getInt("userID");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        if(username.equals(dbUsername) && password.equals(dbPassword))
            checkFlag = true;
        if(checkFlag) {
            adminID = temp;
            return adminID;
        } else
            return adminID;
    }
    
    public Administrator logInAdministrator(int adminID) {
        String fullName = "";
        String dOB = "";
        String addressLine1 = "";
        String addressLine2 = "";
        String town = "";
        String postCode = "";
        String phone = "";
        String eMail = "";
        String username = "";
        String password = "";
        char userType = 'a';
        String nINumber = "";
        
        String query = "SELECT * FROM Administrator WHERE userID='" + adminID + "'";
        Connection conn;
        try {
            conn = connectDB();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                fullName = rs.getString("fullName");
                dOB = rs.getString("dOB");
                addressLine1 = rs.getString("addressLine1");
                addressLine2 = rs.getString("addressLine2");
                town = rs.getString("town");
                postCode = rs.getString("postCode");
                phone = rs.getString("phone");
                eMail = rs.getString("eMail");
                username = rs.getString("username");
                password = rs.getString("password");
                userType = (char) rs.getByte("userType");
                nINumber = rs.getString("nINumber");
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
        
        Administrator admin = new Administrator(fullName, dOB, addressLine1, addressLine2, town, postCode, phone, eMail, username, password, userType, adminID, nINumber);
        return admin;
    }
    
    public TreeMap<Integer, Car> retrieveCars() {
        TreeMap<Integer, Car> cars = new TreeMap();
        int carID = 0;
        String regNo = "";
        String make = "";
        String colour = "";
        double price = 0.0;
        String state= "";
        String query = "SELECT * FROM Car";
        Connection conn;
        
        try {
            conn = connectDB();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                carID = rs.getInt(("carID"));
                regNo = rs.getString("regNo");
                make = rs.getString("make");
                colour = rs.getString("colour");
                price = rs.getDouble("price");
                state = rs.getString("state");
                Car newCar = new Car(carID, regNo, make, colour, price, state);
                cars.put(newCar.getCarID(), newCar);
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
        
        return cars; 
    }
    
    Car retrieveCar(int carID) {
        Car car = new Car();
        String query = "SELECT * FROM Car WHERE carID = " + carID;
        Connection conn;
        try {
            conn = connectDB();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                car = new Car(carID, rs.getString("regNo"), rs.getString("make"), rs.getString("colour"), rs.getDouble("price"), rs.getString("state"));
                
            }
        } catch(Exception e) {
            System.err.println(e);
        }
        return car;
    }

    void amendCar(Car car) {
        String query = "UPDATE car SET regNo = ?, make = ?, colour = ?, price = ?, state = ? WHERE carID = ?";
        Connection conn;
        try {
            conn = connectDB();
            PreparedStatement preStatement = conn.prepareStatement(query);
            preStatement.setString(1, car.getRegNo());
            preStatement.setString(2, car.getMake());
            preStatement.setString(3, car.getColour());
            preStatement.setDouble(4, car.getPrice());
            preStatement.setString(5, car.getState());
            preStatement.setInt(6, car.getCarID());
            preStatement.executeUpdate();
        } catch(Exception e) {
            System.err.println(e);
        }
    }
    
    boolean saveNewCar(Car car) {
        String query = "INSERT INTO Car (regNo, make, colour, price, state) VALUES (?, ?, ?, ?, ?)";
        Connection conn;
        try {
            conn = connectDB();
            PreparedStatement preStatement = conn.prepareStatement(query);
            preStatement.setString(1, car.getRegNo());
            preStatement.setString(2, car.getMake());
            preStatement.setString(3, car.getColour());
            preStatement.setDouble(4, car.getPrice());
            preStatement.setString(5, car.getState());
            preStatement.execute();
            return true;
        } catch(Exception e) {
            System.err.println(e);
            return false;
        }
    }

    ArrayList<Car> getBookedCars(LocalDate chosenDate) {
        ArrayList<Car> bookedCars = new ArrayList();
        String query = "SELECT * FROM Booking";
        Connection conn;
        try {
            conn = connectDB();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                LocalDate bookingDate = rs.getDate("date").toLocalDate();
                if(bookingDate.equals(chosenDate)) {
                    Car bookedCar = retrieveCar(rs.getInt("carID"));
                    bookedCars.add(bookedCar);
                }
            }
        } catch(Exception e) {
            System.out.println("Error at getBookedCars method of DB: " + e);
        }
        return bookedCars;
    }
    
    boolean saveBooking(Booking booking) {
        String query = "INSERT INTO Booking (date, carID, customerID) VALUES (?, ?, ?)";
        Connection conn;
        try {
            conn = connectDB();
            PreparedStatement preStatement = conn.prepareStatement(query);
            preStatement.setDate(1, Date.valueOf(booking.getDate()));
            preStatement.setInt(2, booking.getCar().getCarID());
            preStatement.setInt(3, booking.getCustomer().getCustomerNo());
            preStatement.execute();
            return true;
        } catch(Exception e) {
            System.err.println(e);
            return false;
        }
    }
    
    int retrieveBookingID(LocalDate lDate, int carID, int customerID) {
        int bookingID = -1;
        Date date = Date.valueOf(lDate);
        String query = "SELECT * FROM Booking";
        Connection conn;
        try {
            conn = connectDB();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                if(date.equals(rs.getDate("date")) && carID==rs.getInt("carID") && customerID==rs.getInt("customerID"))
                    bookingID = rs.getInt("bookingID");
            }
        } catch(Exception e) {
            System.out.println("Error at getBookingID of DB: " + e);
            return -2;
        }
        return bookingID;
    }

    ArrayList<Car> retrieveActiveCars() {
        ArrayList<Car> cars = new ArrayList();
        String query = "SELECT * FROM Car";
        Connection conn;
        try {
            conn = connectDB();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                if(rs.getString("state").equals("Active")) {
                    Car car = new Car(rs.getInt("carID"), rs.getString("regNo"), rs.getString("make"), rs.getString("colour"), rs.getDouble("price"), rs.getString("state"));
                    cars.add(car);
                }    
            }
        } catch(Exception e) {
            System.out.println("Error at getBookingID of DB: " + e);
        }
        return cars;
    }
    
    ArrayList<Customer> retrieveCustomersNamesOnly() {
        ArrayList<Customer> customers = new ArrayList();
        String query = "SELECT * FROM Customer";
        Connection conn;
        try {
            conn = connectDB();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                if(rs.getString("state").equals("Active")) {
                    Customer customer = new Customer(rs.getString("fullName"), rs.getInt("customerNumber"));
                    customers.add(customer);
                }    
            }
        } catch(Exception e) {
            System.out.println("Error at getBookingID of DB: " + e);
        }
        return customers;
    }

    HashSet<Booking> retrieveBookings(LocalDate localDate) {
        Date date = Date.valueOf(localDate);
        HashSet<Booking> bookings = new HashSet();
        Booking booking;
        String query = "SELECT * FROM Booking";
        Connection conn;
        try {
            conn = connectDB();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                if(date.equals(rs.getDate("date"))) {
                    booking = new Booking(rs.getInt("customerID"), rs.getInt("carID"), localDate, rs.getInt("bookingID"));
                    bookings.add(booking);
                }
            }
        } catch(Exception e) {
            System.out.println("Error at getBookingID of DB: " + e);
        }
        return bookings;
    }

    TreeMap<Integer, Customer> retrieveCustomers() {
        TreeMap<Integer, Customer> customers = new TreeMap();
        String query = "SELECT * FROM Customer";
        Connection conn;
        try {
            conn = connectDB();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
               Customer customer = new Customer(rs.getString("fullName"), rs.getString("dOB"), rs.getString("addressLine1"), rs.getString("addressLine2"), rs.getString("town"), rs.getString("postCode"), rs.getString("phone"), rs.getString("eMail"), rs.getString("username"), rs.getString("password"), rs.getString("drivingLicenceNumber"), rs.getInt("customerNumber"));
               customers.put(rs.getInt("customerNumber"), customer);
            }
        } catch(Exception e) {
            System.out.println("Error at getBookingID of DB: " + e);
        }
        return customers;
    }
}