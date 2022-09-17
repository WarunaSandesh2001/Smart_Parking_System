package Controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VehicleAddedSuccessfullyController {
    public AnchorPane vehicleAddedSuccessfullyContext;

    public void btnOk(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) vehicleAddedSuccessfullyContext.getScene().getWindow();
            stage.close();

        }catch (Exception e) {}
    }
}
