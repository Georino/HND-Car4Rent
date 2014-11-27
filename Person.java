package car4rent;

/**
 *
 * @author georgebotos
 */
abstract class Person {
    String fullName;
    String dOB;
    String addressLine1;
    String addressLine2;
    String town;
    String postCode;
    String phone;
    String eMail;
    
    public Person() {
    }
    
    public Person(String name) {
        fullName = name;
    }
    public Person(String name, String dOB, String addressLine1, String addressLine2, String town, String postCode, String phone, String eMail) {
        this.fullName = name;
        this.dOB = dOB;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.town = town;
        this.postCode = postCode;
        this.phone = phone;
        this.eMail = eMail;
    }
    
    public Person(String name, String dOB, String addressLine1, String town, String postCode, String phone, String eMail) {
        this.fullName = name;
        this.dOB = dOB;
        this.addressLine1 = addressLine1;
        this.addressLine2 = "";
        this.town = town;
        this.postCode = postCode;
        this.phone = phone;
        this.eMail = eMail;
    }
}
