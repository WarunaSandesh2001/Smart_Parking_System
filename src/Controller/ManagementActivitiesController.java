package Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;
import java.net.URL;

import static Controller.AddNewVehicleController.vehicleArrayList;
import static Controller.MainDisplayController.inParking;
import static Controller.MainDisplayController.onDelivery;

public class ManagementActivitiesController {
    //public AnchorPane loadEditDetailsContext;
    public TableView tblInParking;
    public TableColumn colVehicleNumber;
    public TableColumn colVehicleType;
    public TableColumn colParkingSlot;
    public TableColumn colParkedTime;
    public TableView tblOnDelivery;
    public TableColumn colVNumber;
    public TableColumn colVType;
    public TableColumn colDriverName;
    public TableColumn colLeftTime;
    public AnchorPane ManagementActivitiesContext;
    double x=0;
    double y=0;

    public void editDetails(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/EditDetails.fxml");
        Parent load = FXMLLoader.load(resource);
        ManagementActivitiesContext.getChildren().clear();
        ManagementActivitiesContext.getChildren().add(load);
    }

    public void goToSummery(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/Summery.fxml");
        Parent load = FXMLLoader.load(resource);
        ManagementActivitiesContext.getChildren().clear();
        ManagementActivitiesContext.getChildren().add(load);
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        try {
            Stage stage = (Stage) ManagementActivitiesContext.getScene().getWindow();
            stage.close();

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

    public void loadAddVehicle(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AddNewVehicle.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public void loadAddDriver(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AddNewDriver.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }



    public void initialize(){
        colVehicleNumber.setCellValueFactory(new PropertyValueFactory("vehicleNumber"));
        colVehicleType.setCellValueFactory(new PropertyValueFactory("vehicleType"));
        colParkingSlot.setCellValueFactory(new PropertyValueFactory("parkingSlot"));
        colParkedTime.setCellValueFactory(new PropertyValueFactory("parkedTime"));

        tblInParking.setItems(FXCollections.observableArrayList(inParking));

        colVNumber.setCellValueFactory(new PropertyValueFactory("vehicleNumber"));
        colVType.setCellValueFactory(new PropertyValueFactory("vehicleType"));
        colDriverName.setCellValueFactory(new PropertyValueFactory("driverName"));
        colLeftTime.setCellValueFactory(new PropertyValueFactory("leftTime"));

        tblOnDelivery.setItems(FXCollections.observableArrayList(onDelivery));
    }

    public void refreshPage1(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/ManagementActivities.fxml");
        Parent load = FXMLLoader.load(resource);
        ManagementActivitiesContext.getChildren().clear();
        ManagementActivitiesContext.getChildren().add(load);
    }

    public void refreshPage(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/ManagementActivities.fxml");
        Parent load = FXMLLoader.load(resource);
        ManagementActivitiesContext.getChildren().clear();
        ManagementActivitiesContext.getChildren().add(load);
    }


}
