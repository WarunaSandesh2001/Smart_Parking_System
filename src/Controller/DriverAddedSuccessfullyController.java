package Controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DriverAddedSuccessfullyController {
    public AnchorPane driverAddedSuccessfullyContext;

    public void btnOk(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) driverAddedSuccessfullyContext.getScene().getWindow();
            stage.close();

        }catch (Exception e) {}
    }
}
