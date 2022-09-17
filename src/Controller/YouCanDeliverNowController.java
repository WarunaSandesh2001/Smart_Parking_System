package Controller;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class YouCanDeliverNowController {
    public AnchorPane YouCanDeliverNowContext;

    public void okBTN(MouseEvent mouseEvent) {
        try {
            Stage stage = (Stage) YouCanDeliverNowContext.getScene().getWindow();
            stage.close();

        }catch (Exception e) {}
    }
}
