package Controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PleaseTryAgainController {
    public AnchorPane pleaseTryAgainContext;

    public void btnOk(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) pleaseTryAgainContext.getScene().getWindow();
            stage.close();

        }catch (Exception e) {}
    }
}
