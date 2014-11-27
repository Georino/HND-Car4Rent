package car4rent;

import static car4rent.PasswordHash.validatePassword;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 *
 * @author georgebotos
 */
public class Customer extends User{
    int customerNumber;
    String drivingLicenceNumber;
    
    public Customer() {
        super();
        customerNumber  = 100;
    }
    
    public Customer(String name, int customerID) {
        super(name);
        customerNumber = customerID;
    }
    
    public Customer(String name, String dOB, String addressLine1, String addressLine2, String town, String postCode, String phone, String eMail, String username, String password, char userType, String drivingLicenceNumber, int customerNumber) {
        super(name, dOB, addressLine1, addressLine2, town, postCode, phone, eMail, username, password, userType);
        this.customerNumber = customerNumber;
        this.drivingLicenceNumber = drivingLicenceNumber;
    }
    
    public Customer(String name, String dOB, String addressLine1, String addressLine2, String town, String postCode, String phone, String eMail, String username, String password, String drivingLicenceNumber, int customerNumber) {
        super(name, dOB, addressLine1, addressLine2, town, postCode, phone, eMail, username, password);
        this.customerNumber = customerNumber;
        this.drivingLicenceNumber = drivingLicenceNumber;
    }
    
    public Customer(String name, String dOB, String addressLine1, String addressLine2, String town, String postCode, String phone, String eMail, String username, String password, String drivingLicenceNumber) {
        super(name, dOB, addressLine1, addressLine2, town, postCode, phone, eMail, username, password);
        this.drivingLicenceNumber = drivingLicenceNumber;
    }
    
    public Customer(String name, String dOB, String addressLine1, String town, String postCode, String phone, String eMail, String username, String password, char userType, String drivingLicenceNumber, int customerNumber) {
        super(name, dOB, addressLine1, town, postCode, phone, eMail, username, password, userType);
        this.customerNumber = customerNumber;
        this.drivingLicenceNumber = drivingLicenceNumber;
    }

    @Override
    boolean addUser() {
        boolean successfulSaveFlag;
        DB db = new DB();
        successfulSaveFlag = db.saveCustomer(username, dOB, addressLine1, addressLine2, town, postCode, phone, eMail, password, drivingLicenceNumber, fullName);
        return successfulSaveFlag;
    }

    @Override
    boolean removeUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    boolean updateUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String getName() {
        return fullName;
    }
    
    public String getDOB() {
        return dOB;
    }
    
    public String getAddressLine1() {
        return addressLine1;
    }
    
    public String getAddressLine2() {
        return addressLine2;
    }
    
    public int getCustomerNo() {
        return customerNumber;
    }
    
    public String getTown() {
        return town;
    }
    
    public String getPostCode() {
        return postCode;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public String getEMail() {
        return eMail;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public char getUserType() {
        return 'c';
    }
    
    public String getDrivingLicenceNumber() {
        return drivingLicenceNumber;
    }
}
