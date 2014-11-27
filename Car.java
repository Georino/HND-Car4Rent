package car4rent;

/**
 *
 * @author georgebotos
 */
public class Car {
    private int carID;
    private String regNo;
    private String make;
    private String colour;
    private double price;
    private String state;
    
    public Car() {
        carID = 100;
    }
    
    public Car(int carID, String regNo, String make, String colour, double price, String state) {
        this.carID = carID;
        this.regNo = regNo;
        this.make = make;
        this.colour = colour;
        this.price = price;
        this.state = state;
    }

    Car(String regNo, String make, String colour, double price, String state) {
        this.regNo = regNo;
        this.make = make;
        this.colour = colour;
        this.price = price;
        this.state = state;
    }
    
    static Car getCar(int carID) {
        DB db = new DB();
        Car car = db.retrieveCar(carID);
        return car;
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
    
    public String getColour() {
        return colour;
    }
    
    public double getPrice() {
        return price;
    }
    
    public String getState() {
        return state;
    }
}
