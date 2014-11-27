package car4rent;

import java.time.LocalDate;
import java.util.HashSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author georgebotos
 */
public class BookingInfo {

    int bookingID;
    LocalDate date;
    int carID;
    String regNo;
    String make;
    String customerName;
    
    public BookingInfo() {}
    
    public BookingInfo(int bookingID, LocalDate date, int carID, String regNo, String make, String customerName) {
        this.bookingID = bookingID;
        this.date = date;
        this.carID = carID;
        this.regNo = regNo;
        this.make = make;
        this.customerName = customerName;
    }
    
    public BookingInfo(LocalDate date, int carID, int customerID) {
        DB db = new DB();
        Customer customer = db.retrieveCustomer(customerID);
        Car car = db.retrieveCar(carID);
        this.bookingID = Booking.getBookingID(date, carID, customerID);
        this.date = date;
        this.carID = carID;
        this.regNo = car.getRegNo();
        this.make = car.getMake();
        this.customerName = customer.getName();
    }
    
    public BookingInfo(Booking booking) {
        DB db = new DB();
        Customer customer = booking.getCustomer();
        bookingID = booking.getBookingID();
        date = booking.getDate();
        carID = booking.getCar().getCarID();
        regNo = booking.getCar().getRegNo();
        make = booking.getCar().getMake();
        customerName = customer.getName();
    }
    
    public int getBookingID() {
        return bookingID;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public int getCarID() {
        return carID;
    }
    
    public String getRegNo() {
        return regNo;
    }
    
    public String getMake() {
        return make;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    static ObservableList<BookingInfo> getObservableListOfBookingInfo(LocalDate date) {
        HashSet<BookingInfo> setOfBookingInfo = getSetOfBookingInfo(date);
        ObservableList<BookingInfo> observableListOfBookingInfo = FXCollections.observableArrayList(setOfBookingInfo);
        return observableListOfBookingInfo;
    }
    
    private static HashSet<BookingInfo> getSetOfBookingInfo(LocalDate date) {
        HashSet<BookingInfo> setOfBookingInfo = new HashSet();
        BookingInfo bookingInfo;
        DB db = new DB();
        HashSet<Booking> bookings = db.retrieveBookings(date);
        for(Booking b : bookings) {
            bookingInfo = new BookingInfo(b);
            setOfBookingInfo.add(bookingInfo);
        }
        return setOfBookingInfo;
    }

}
