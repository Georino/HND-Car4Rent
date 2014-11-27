package car4rent;

import static car4rent.PasswordHash.createHash;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 *
 * @author georgebotos
 */
abstract class User extends Person{
    String username;
    String password;
    char userType;
    
    public User() {
        super();
    }
    
    public User(String name) {
        super(name);
    }
    public User(String name, String dOB, String addressLine1, String addressLine2, String town, String postCode, String phone, String eMail, String username, String password, char userType) {
        super(name, dOB, addressLine1, addressLine2,town, postCode, phone, eMail);
        this.username = username;
        try {
           this.password = createHash(password.toCharArray()); 
        } catch(NoSuchAlgorithmException e) {
            System.err.println(e);
        } catch (InvalidKeySpecException e) {
            System.err.println(e);
        }
        
        this.userType = userType;    
    }
    
    public User(String name, String dOB, String addressLine1, String addressLine2, String town, String postCode, String phone, String eMail, String username, String password) {
        super(name, dOB, addressLine1, addressLine2,town, postCode, phone, eMail);
        this.username = username;
        try {
           this.password = createHash(password.toCharArray()); 
        } catch(NoSuchAlgorithmException e) {
            System.err.println(e);
        } catch (InvalidKeySpecException e) {
            System.err.println(e);
        }
    }
    
    public User(String name, String dOB, String addressLine1, String town, String postCode, String phone, String eMail, String username, String password, char userType) {
        super(name, dOB, addressLine1,town, postCode, phone, eMail);
        this.username = username;
        try {
           this.password = createHash(password.toCharArray()); 
        } catch(NoSuchAlgorithmException e) {
            System.err.println(e);
        } catch (InvalidKeySpecException e) {
            System.err.println(e);
        }
        this.userType = userType;
    }
    
    abstract boolean addUser();
    
    abstract boolean removeUser();
    
    abstract boolean updateUser(); 
}
