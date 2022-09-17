package Module;

public class DriverSearch {
    private String vehicleNumber;
    private String driverName;

    public DriverSearch(String vehicleNumber, String driverName) {
        this.vehicleNumber = vehicleNumber;
        this.driverName = driverName;
    }

    public DriverSearch() {}

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    @Override
    public String toString() {
        return "DriverSearch{" +
                "vehicleNumber='" + vehicleNumber + '\'' +
                ", driverName='" + driverName + '\'' +
                '}';
    }
}
