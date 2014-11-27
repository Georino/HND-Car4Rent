package car4rent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javafx.collections.ObservableList;

/**
 *
 * @author georgebotos
 */
public class Booking {
    int bookingID;
    private Customer customer;
    private Car car; 
    private LocalDate date;
    
    public Booking() {}
    
    public Booking(Customer customer, Car car, LocalDate date) {
        this.customer = customer;
        this.car = car;
        this.date = date;
    }
    
    public Booking(int customerID, int carID, LocalDate date, int bookingID) {
        this.bookingID = bookingID;
        DB db = new DB();
        customer = db.retrieveCustomer(customerID);
        car = db.retrieveCar(carID);
        this.date = date;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public Car getCar() {
        return car;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public int getBookingID() {
        return bookingID;
    }
    
    static ArrayList<String> getAvailableCarsForBooking(LocalDate chosenDate) {
        DB db = new DB();
        ArrayList<Car> bookedCars = db.getBookedCars(chosenDate);
        ArrayList<Car> result = new ArrayList();
        HashSet<Car> resultBookedCars = new HashSet();
        TreeMap<Integer, Car> carsRetrieved = db.retrieveCars();
        Set<Map.Entry<Integer, Car>> setOfCars = carsRetrieved.entrySet();
        for(Map.Entry<Integer, Car> c : setOfCars) {
            if(c.getValue().getState().equals("Active")) {
                result.add(c.getValue());    
            }
            for(Car bookedC : bookedCars) {
                if(bookedC.getCarID()==c.getKey())
                    resultBookedCars.add(c.getValue());
            }
                                        }
        result.removeAll(resultBookedCars);
        ArrayList<String> resultString = new ArrayList();
        for(Car c : result)
        resultString.add(new String(c.getMake() + ", Car ID: " + c.getCarID()));
        return resultString;
    }

    void save() {
        DB db = new DB();
        db.saveBooking(this);
    }
    
    static int getBookingID(LocalDate date, int carID, int customerID) {
        DB db = new DB();
        int retrievedBookingID = db.retrieveBookingID(date, carID, customerID);
        return retrievedBookingID;
    }
    
    static int getBookingID(Booking booking) {
        DB db = new DB();
        int retrievedBookingID = db.retrieveBookingID(booking.getDate(), booking.getCar().getCarID(), booking.getCustomer().getCustomerNo());
        return retrievedBookingID;
    }
    
}
