package Controller;

import Module.*;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static Controller.AddNewDriverController.driverArrayList;
import static Controller.AddNewVehicleController.vehicleArrayList;

public class MainDisplayController {

    static ArrayList<InParking> inParking = new ArrayList();
    static ArrayList<OnDelivery> onDelivery = new ArrayList();

    static ArrayList<InParking> inParkingSummery =new ArrayList();
    static ArrayList<OnDelivery> onDeliverySummery =new ArrayList();

    static  ArrayList<DriverSearch> driverSearch = new ArrayList();

    static SlotSeen[] vanSlot ={new SlotSeen("null","1"),new SlotSeen("null","2"),new SlotSeen("null","3"),new SlotSeen("null","4"),new SlotSeen("null","12"),new SlotSeen("null","13")};
    static SlotSeen[] busSlot ={new SlotSeen("null","14")};
    static SlotSeen[] cargoLorrySlot = {new SlotSeen("null","5"),new SlotSeen("null","6"),new SlotSeen("null","7"),new SlotSeen("null","8"),new SlotSeen("null","9"),new SlotSeen("null","10"),new SlotSeen("null","11")};

    public Label lblSlotNumber;
    public TextField txtShowTime;
    public TextField txtShowDate;
    public Button managementLogin;
    public JFXComboBox<String> cmbSelectVehicle;
    public JFXComboBox<String> cmbDriver;
    public Label displayVehicleType;
    public Button btnParkVehicle;
    public Button btnOnDeliveryShift;
    public AnchorPane mainDisplayContext;

    double x=0;
    double y=0;
    String vehicleNumber;

    public void exitProgram(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void exitProgram1(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void initialize(){
        try {
            txtShowDate.setEditable(false);
            txtShowTime.setEditable(false);
            try {
                new Timer(1000, e -> {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss aa");
                    String displayTime = simpleDateFormat.format(new Date());
                    String displayDate = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH).format(new Date());

                    txtShowDate.setText(displayDate);
                    txtShowTime.setText(displayTime);
                }).start();
            } catch (Exception e) {
            }

            for (Vehicle v : vehicleArrayList) {
                cmbSelectVehicle.getItems().add(v.getVehicleNumber());
            }

            for (Driver d : driverArrayList) {
                cmbDriver.getItems().add(d.getName());
            }

            cmbSelectVehicle.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                lblSlotNumber.setText("");
                try {
                    for (Vehicle v : vehicleArrayList) {
                        if (newValue.equalsIgnoreCase(v.getVehicleNumber())) {
                            displayVehicleType.setText(" " + v.getVehicleType());

                            vehicleNumber = v.getVehicleNumber();
                            if (inParking.size() == 0) {
                                btnParkVehicle.setDisable(false);
                                btnOnDeliveryShift.setDisable(true);
                                for (DriverSearch s : driverSearch) {
                                    if (cmbSelectVehicle.getSelectionModel().getSelectedItem().equals(s.getVehicleNumber())) {
                                        cmbDriver.setValue(s.getDriverName());
                                        cmbDriver.setDisable(true);
                                        break;
                                    }
                                }
                            } else {
                                setDisable(newValue);
                            }
                        }
                    }
                } catch (Exception ex) {
                }
            });
        }catch(Exception e){}
    }

    public void goToManagementLoginForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ManagementLoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public void setDisable(String vehicleNumber){
        for (InParking p : inParking) {
            if (p.getVehicleNumber().equals(vehicleNumber)) {
                cmbDriver.setDisable(false);
                btnParkVehicle.setDisable(true);
                btnOnDeliveryShift.setDisable(false);

                break;
            }else{
                for (DriverSearch s :driverSearch) {
                    if(cmbSelectVehicle.getSelectionModel().getSelectedItem().equals(s.getVehicleNumber())){
                        cmbDriver.setValue(s.getDriverName());
                        cmbDriver.setDisable(true);
                        break;
                    }
                }
                btnParkVehicle.setDisable(false);
                btnOnDeliveryShift.setDisable(true);
            }
        }
    }
    public void dragged(MouseEvent mouseEvent) {
        try {
            Node node = (Node) mouseEvent.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        }catch (Exception e){}

    }

    public void pressed(MouseEvent mouseEvent) throws IOException {
        x=mouseEvent.getSceneX();
        y=mouseEvent.getSceneY();
    }
    
    public void goToPark(ActionEvent actionEvent) throws IOException{
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy - HH:mm aa");
        Date date = new Date();
        String dateAndTime = formatter.format(date);
        for (OnDelivery d : onDelivery) {
            if(d.getVehicleNumber().equalsIgnoreCase(cmbSelectVehicle.getSelectionModel().getSelectedItem())){
                onDelivery.remove(d);

                if(displayVehicleType.getText().equalsIgnoreCase(" Van")){
                    for (SlotSeen s : vanSlot) {
                        if(s.getVehicleNumber().equalsIgnoreCase("null")) {
                            //System.out.println("B");
                            String slotNumber = s.getSlotNumber();
                            lblSlotNumber.setText(slotNumber);
                            //System.out.println(slotNumber);

                            InParking inParking1 = new InParking(cmbSelectVehicle.getSelectionModel().getSelectedItem(),displayVehicleType.getText(),slotNumber,dateAndTime);
                            inParking.add(inParking1);
                            inParkingSummery.add(inParking1);

                            s.setVehicleNumber(cmbSelectVehicle.getSelectionModel().getSelectedItem());
                            break;
                        }
                    }
                }
                if(displayVehicleType.getText().equalsIgnoreCase(" Bus")){
                    for (SlotSeen s : busSlot) {
                        if(s.getVehicleNumber().equalsIgnoreCase("null")) {
                            String slotNumber = s.getSlotNumber();
                            lblSlotNumber.setText(slotNumber);

                            InParking inParking1 = new InParking(cmbSelectVehicle.getSelectionModel().getSelectedItem(),displayVehicleType.getText(),slotNumber,dateAndTime);
                            inParking.add(inParking1);
                            inParkingSummery.add(inParking1);

                            s.setVehicleNumber(cmbSelectVehicle.getSelectionModel().getSelectedItem());
                            break;
                        }
                    }
                }

                if(displayVehicleType.getText().equalsIgnoreCase(" Cargo Lorry")){
                    for (SlotSeen s : cargoLorrySlot) {
                        if(s.getVehicleNumber().equalsIgnoreCase("null")) {
                            String slotNumber = s.getSlotNumber();
                            lblSlotNumber.setText(slotNumber);

                            InParking inParking1 = new InParking(cmbSelectVehicle.getSelectionModel().getSelectedItem(),displayVehicleType.getText(),slotNumber,dateAndTime);
                            inParking.add(inParking1);
                            inParkingSummery.add(inParking1);

                            s.setVehicleNumber(cmbSelectVehicle.getSelectionModel().getSelectedItem());
                            break;
                        }
                    }
                }
                URL resource = getClass().getResource("../view/ParkedOkMessage.fxml");
                Parent load = FXMLLoader.load(resource);
                Scene scene = new Scene(load);
                Stage stage1 = new Stage();
                stage1.setScene(scene);
                stage1.setResizable(false);
                stage1.initStyle(StageStyle.UNDECORATED);
                stage1.show();
                //try{Thread.sleep(5000);}catch(Exception ex){}
                break;
            }
        }
        for (DriverSearch d : driverSearch) {
            if(cmbSelectVehicle.getSelectionModel().getSelectedItem().equals(d.getVehicleNumber())){
                driverSearch.remove(d);
                break;
            }
        }

    }

    public void goToDelivery(ActionEvent actionEvent) throws IOException{
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy - HH:mm aa");
            Date date = new Date();
            String dateAndTime = formatter.format(date);
                    if (!cmbSelectVehicle.getSelectionModel().getSelectedItem().equals("null") && !cmbDriver.getSelectionModel().getSelectedItem().equals("null")) {
                        for (InParking p : inParking) {
                            if (p.getVehicleNumber().equalsIgnoreCase(cmbSelectVehicle.getSelectionModel().getSelectedItem())) {
                                inParking.remove(p);


                                OnDelivery onDelivery1 = new OnDelivery(cmbSelectVehicle.getSelectionModel().getSelectedItem(), displayVehicleType.getText(), cmbDriver.getSelectionModel().getSelectedItem(), dateAndTime);
                                onDelivery.add(onDelivery1);
                                onDeliverySummery.add(onDelivery1);

                                DriverSearch driverSearch1 = new DriverSearch(cmbSelectVehicle.getSelectionModel().getSelectedItem(), cmbDriver.getSelectionModel().getSelectedItem());
                                driverSearch.add(driverSearch1);
                                if (displayVehicleType.getText().equalsIgnoreCase(" Van")) {
                                    for (SlotSeen s : vanSlot) {
                                        if (cmbSelectVehicle.getSelectionModel().getSelectedItem().equalsIgnoreCase(s.getVehicleNumber())) {
                                            s.setVehicleNumber("null");
                                        }
                                    }
                                }
                                if (displayVehicleType.getText().equalsIgnoreCase(" Bus")) {
                                    for (SlotSeen s : busSlot) {
                                        if (cmbSelectVehicle.getSelectionModel().getSelectedItem().equalsIgnoreCase(s.getVehicleNumber())) {
                                            s.setVehicleNumber("null");
                                        }
                                    }
                                }
                                if (displayVehicleType.getText().equalsIgnoreCase(" Cargo Lorry")) {
                                    for (SlotSeen s : cargoLorrySlot) {
                                        if (cmbSelectVehicle.getSelectionModel().getSelectedItem().equalsIgnoreCase(s.getVehicleNumber())) {
                                            s.setVehicleNumber("null");
                                        }
                                    }
                                }

                                URL resource = getClass().getResource("../view/MainDisplay.fxml");
                                Parent load = FXMLLoader.load(resource);
                                mainDisplayContext.getChildren().clear();
                                mainDisplayContext.getChildren().add(load);
                                //new Alert(Alert.AlertType.CONFIRMATION, "You can Deliver now..", ButtonType.OK).show();
                                URL resource1 = getClass().getResource("../view/YouCanDeliverNow.fxml");
                                Parent load1 = FXMLLoader.load(resource1);
                                Scene scene = new Scene(load1);
                                Stage stage = new Stage();
                                stage.setScene(scene);
                                stage.initStyle(StageStyle.UNDECORATED);
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.showAndWait();
                                break;
                            }
                        }
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Please fill all fields and try again...", ButtonType.CLOSE).show();
                    }


        }catch (Exception e){}
    }

    public void reloadPage(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/MainDisplay.fxml");
        Parent load = FXMLLoader.load(resource);
        mainDisplayContext.getChildren().clear();
        mainDisplayContext.getChildren().add(load);
    }

    public void reloadPage1(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/MainDisplay.fxml");
        Parent load = FXMLLoader.load(resource);
        mainDisplayContext.getChildren().clear();
        mainDisplayContext.getChildren().add(load);
    }
    public void pageRefresh() throws IOException {
        //try{Thread.sleep(500);}catch(Exception ex){}
        URL resource = getClass().getResource("../view/MainDisplay.fxml");
        Parent load = FXMLLoader.load(resource);
        mainDisplayContext.getChildren().clear();
        mainDisplayContext.getChildren().add(load);
    }
}
