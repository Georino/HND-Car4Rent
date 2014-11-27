/*
 */

package car4rent;

/**
 *
 * @author georgebotos
 */
class Administrator extends User{
    int userID;
    String nINumber;
    
    public Administrator(String name, String dOB, String addressLine1, String addressLine2, String town, String postCode, String phone, String eMail, String username, String password, char userType, int userID, String nINumber) {
        super(name, dOB, addressLine1, addressLine2, town, postCode, phone, eMail, username, password, userType);
        this.userID = userID;
        this.nINumber = nINumber;
    }
    
    
    @Override
    boolean addUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    boolean removeUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    boolean updateUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
