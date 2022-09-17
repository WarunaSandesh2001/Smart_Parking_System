package Controller;

import Module.InParking;
import Module.SlotSeen;
import Module.Vehicle;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static Controller.MainDisplayController.*;

public class AddNewVehicleController {

    static ArrayList<Vehicle> vehicleArrayList = new ArrayList();

//    static Vehicle[] busVehicle={new Vehicle("null")};
//    static Vehicle[] vanVehicle={new Vehicle("null"),new Vehicle("null"),new Vehicle("null"),new Vehicle("null"),new Vehicle("null"),new Vehicle("null")};
//    static Vehicle[] cargoLorryVehicle={new Vehicle("null"),new Vehicle("null"),new Vehicle("null"),new Vehicle("null"),new Vehicle("null"),new Vehicle("null"),new Vehicle("null")};

    public JFXComboBox<String> cmbVehicle;
    public AnchorPane AddNewVehicleContext;
    public Button add;
    public TextField txtNumberOfPassengers;
    public TextField txtMaximumWeight;
    public TextField txtVehicleNumber;

    double x = 0;
    double y = 0;

    static int countBus=0;
    static int countVan=0;
    static int countLorry=0;

    public void initialize(){
        cmbVehicle.getItems().setAll("Bus","Van","Cargo Lorry");
    }

    public void closeWindow(MouseEvent mouseEvent) {
        try {
            Stage stage = (Stage) AddNewVehicleContext.getScene().getWindow();
            stage.close();
        }catch (Exception e) {}
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

    public void goToMaximumWeightTXTField(ActionEvent actionEvent) {
        txtMaximumWeight.requestFocus();
    }

    public void goToNumOfPassengerTXTField(ActionEvent actionEvent) {
        txtNumberOfPassengers.requestFocus();
    }

    public void goToAddButton(ActionEvent actionEvent) throws IOException {
        addVehicle(actionEvent);
    }

    public int searchAvailabilityBus(){
        for (Vehicle v : vehicleArrayList) {
            if(v.getVehicleType().equals("Bus")){
                countBus++;
            }
        }
        return countBus;
    }
    public int searchAvailabilityVan(){
        for (Vehicle v : vehicleArrayList) {
            if(v.getVehicleType().equals("Van")){
                countVan++;
            }
        }
        return countVan;
    }
    public int searchAvailabilityLorry(){
        for (Vehicle v : vehicleArrayList) {
            if(v.getVehicleType().equals("Cargo Lorry")){
                countLorry++;
            }
        }
        return countLorry;
    }


    public void addVehicle(ActionEvent actionEvent) throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy - HH:mm aa");
        Date date = new Date();
        String dateAndTime = formatter.format(date);
        //System.out.println(cmbVehicle.getSelectionModel().getSelectedItem());
            try {
                if (!txtVehicleNumber.getText().equals("") && !txtMaximumWeight.getText().equals("") && !txtNumberOfPassengers.getText().equals("") && !cmbVehicle.getSelectionModel().getSelectedItem().equalsIgnoreCase("null")) {
                    Vehicle vehicle = new Vehicle(txtVehicleNumber.getText(), cmbVehicle.getSelectionModel().getSelectedItem(), Integer.parseInt(txtMaximumWeight.getText()), Integer.parseInt(txtNumberOfPassengers.getText()));
                    try {
                        if (cmbVehicle.getSelectionModel().getSelectedItem().equals("Van")) {
                            int vanCount = searchAvailabilityVan();
                            if (vanCount < 6) {
                                if (vehicleArrayList.add(vehicle)) {
                                    if (cmbVehicle.getSelectionModel().getSelectedItem().equalsIgnoreCase("Van")) {
                                        for (SlotSeen s : vanSlot) {
                                            if (s.getVehicleNumber().equalsIgnoreCase("null")) {
                                                s.setVehicleNumber(txtVehicleNumber.getText());

                                                InParking parkedVehicle1 = new InParking(txtVehicleNumber.getText(), cmbVehicle.getSelectionModel().getSelectedItem(), s.getSlotNumber(), "(newly Added) " + dateAndTime);
                                                inParking.add(parkedVehicle1);
                                                countVan =0;
                                                break;
                                            }
                                        }
                                    }
                                    txtVehicleNumber.clear();
                                    txtMaximumWeight.clear();
                                    txtNumberOfPassengers.clear();
                                    cmbVehicle.getItems().clear();
                                    initialize();
                                    URL resource = getClass().getResource("../view/VehicleAddedSuccessfully.fxml");
                                    Parent load = FXMLLoader.load(resource);
                                    Scene scene = new Scene(load);
                                    Stage stage1 = new Stage();
                                    stage1.setScene(scene);
                                    stage1.initStyle(StageStyle.UNDECORATED);
                                    stage1.showAndWait();

                                }
                            } else {
                                new Alert(Alert.AlertType.WARNING, "You can't Add..Van Parking is full... ", ButtonType.OK).show();
                            }
                        }
                        if (cmbVehicle.getSelectionModel().getSelectedItem().equals("Bus")) {
                            int busCount = searchAvailabilityBus();
                            if (busCount == 0) {
                                if (vehicleArrayList.add(vehicle)) {
                                    if (cmbVehicle.getSelectionModel().getSelectedItem().equalsIgnoreCase("Bus")) {
                                        for (SlotSeen s : busSlot) {
                                            if (s.getVehicleNumber().equalsIgnoreCase("null")) {
                                                s.setVehicleNumber(txtVehicleNumber.getText());

                                                InParking parkedVehicle2 = new InParking(txtVehicleNumber.getText(), cmbVehicle.getSelectionModel().getSelectedItem(), s.getSlotNumber(), "(newly Added) " + dateAndTime);
                                                inParking.add(parkedVehicle2);
                                                break;
                                            }
                                        }
                                    }
                                    txtVehicleNumber.clear();
                                    txtMaximumWeight.clear();
                                    txtNumberOfPassengers.clear();
                                    cmbVehicle.getItems().clear();
                                    initialize();
                                    URL resource = getClass().getResource("../view/VehicleAddedSuccessfully.fxml");
                                    Parent load = FXMLLoader.load(resource);
                                    Scene scene = new Scene(load);
                                    Stage stage1 = new Stage();
                                    stage1.setScene(scene);
                                    stage1.initStyle(StageStyle.UNDECORATED);
                                    stage1.showAndWait();
                                }
                            } else {
                                new Alert(Alert.AlertType.WARNING, "You can't Add..Bus Parking is full... ", ButtonType.OK).show();
                            }
                        }
                        if (cmbVehicle.getSelectionModel().getSelectedItem().equals("Cargo Lorry")) {
                            int lorryCount = searchAvailabilityLorry();
                            if (lorryCount < 7) {
                                if (vehicleArrayList.add(vehicle)) {
                                    if (cmbVehicle.getSelectionModel().getSelectedItem().equalsIgnoreCase("Cargo Lorry")) {
                                        for (SlotSeen s : cargoLorrySlot) {
                                            if (s.getVehicleNumber().equalsIgnoreCase("null")) {
                                                s.setVehicleNumber(txtVehicleNumber.getText());

                                                InParking parkedVehicle3 = new InParking(txtVehicleNumber.getText(), cmbVehicle.getSelectionModel().getSelectedItem(), s.getSlotNumber(), "(newly Added) " + dateAndTime);
                                                inParking.add(parkedVehicle3);
                                                countLorry=0;
                                                break;
                                            }
                                        }
                                    }
                                    txtVehicleNumber.clear();
                                    txtMaximumWeight.clear();
                                    txtNumberOfPassengers.clear();
                                    cmbVehicle.getItems().clear();
                                    initialize();
                                    URL resource = getClass().getResource("../view/VehicleAddedSuccessfully.fxml");
                                    Parent load = FXMLLoader.load(resource);
                                    Scene scene = new Scene(load);
                                    Stage stage1 = new Stage();
                                    stage1.setScene(scene);
                                    stage1.initStyle(StageStyle.UNDECORATED);
                                    stage1.showAndWait();

                                }
                            }else{
                                new Alert(Alert.AlertType.WARNING, "You can't Add..Cargo Lorry Parking is full... ", ButtonType.OK).show();
                            }
                        }
                    } catch (Exception e) {}
                } else {
                    URL resource = getClass().getResource("../view/PleaseTryAgain.fxml");
                    Parent load = FXMLLoader.load(resource);
                    Scene scene = new Scene(load);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                }
            } catch (Exception e) {


                URL resource = getClass().getResource("../view/PleaseTryAgain.fxml");
                Parent load = FXMLLoader.load(resource);
                Scene scene = new Scene(load);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            }
    }

}
