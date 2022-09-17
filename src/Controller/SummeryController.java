package Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import static Controller.AddNewDriverController.driverArrayList;
import static Controller.AddNewVehicleController.vehicleArrayList;
import static Controller.MainDisplayController.*;
import static Controller.MainDisplayController.onDelivery;

public class SummeryController {

    public TableView tblParkingSummery;
    public TableColumn colVehicleNumber;
    public TableColumn colVehicleType;
    public TableColumn colParkingSlot;
    public TableColumn colParkedTime;
    public TableView tblDeliverySummery;
    public TableColumn colVNumber;
    public TableColumn colVType;
    public TableColumn colDriverName;
    public TableColumn colLeftTime;
    public AnchorPane summeryContext;
    double x=0;
    double y=0;

    public void logout(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) summeryContext.getScene().getWindow();
            stage.close();

        }catch (Exception e){}
    }

    public void initialize(){
        colVehicleNumber.setCellValueFactory(new PropertyValueFactory("vehicleNumber"));
        colVehicleType.setCellValueFactory(new PropertyValueFactory("vehicleType"));
        colParkingSlot.setCellValueFactory(new PropertyValueFactory("parkingSlot"));
        colParkedTime.setCellValueFactory(new PropertyValueFactory("parkedTime"));

        tblParkingSummery.setItems(FXCollections.observableArrayList(inParkingSummery));

        colVNumber.setCellValueFactory(new PropertyValueFactory("vehicleNumber"));
        colVType.setCellValueFactory(new PropertyValueFactory("vehicleType"));
        colDriverName.setCellValueFactory(new PropertyValueFactory("driverName"));
        colLeftTime.setCellValueFactory(new PropertyValueFactory("leftTime"));

        tblDeliverySummery.setItems(FXCollections.observableArrayList(onDeliverySummery));
    }

    public void editDetails(ActionEvent actionEvent) throws IOException {
        try {
            URL resource = getClass().getResource("../view/editDetails.fxml");
            Parent load = FXMLLoader.load(resource);
            summeryContext.getChildren().clear();
            summeryContext.getChildren().add(load);
        }catch (Exception e){}
    }

    public void dragged(MouseEvent mouseEvent) {
        try {
            Node node = (Node) mouseEvent.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        }catch (Exception e){}
    }

    public void pressed(MouseEvent mouseEvent) {
        x=mouseEvent.getSceneX();
        y=mouseEvent.getSceneY();
    }

    public void backToManagementActivities(ActionEvent actionEvent) throws IOException {
        try {
            URL resource = getClass().getResource("../view/ManagementActivities.fxml");
            Parent load = FXMLLoader.load(resource);
            summeryContext.getChildren().clear();
            summeryContext.getChildren().add(load);
        }catch(Exception e){}
    }
}
