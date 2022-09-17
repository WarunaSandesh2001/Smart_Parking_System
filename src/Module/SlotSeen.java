package Module;

public class SlotSeen {
    private String vehicleNumber;
    private String slotNumber;

    public SlotSeen(String vehicleNumber, String slotNumber) {
        this.vehicleNumber = vehicleNumber;
        this.slotNumber = slotNumber;
    }

    public SlotSeen() {
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(String slotNumber) {
        this.slotNumber = slotNumber;
    }

    @Override
    public String toString() {
        return "slotSeen{" +
                "vehicleNumber='" + vehicleNumber + '\'' +
                ", slotNumber='" + slotNumber + '\'' +
                '}';
    }
}
