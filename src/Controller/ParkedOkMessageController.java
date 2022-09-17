package Controller;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ParkedOkMessageController {
    public AnchorPane anchorPaneContext;

    public void closeTheWindow(MouseEvent mouseEvent) {
        try {
            Stage stage = (Stage) this.anchorPaneContext.getScene().getWindow();
            stage.close();

        } catch (Exception e) { }
    }
}

