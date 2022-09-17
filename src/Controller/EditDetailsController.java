package Controller;

import Module.*;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import static Controller.AddNewDriverController.driverArrayList;
import static Controller.AddNewVehicleController.vehicleArrayList;
import static Controller.MainDisplayController.*;

public class EditDetailsController {
    public AnchorPane editDetailsContext;
    public TableView tblVehicleDetails;
    public TableColumn colVNumber;
    public TableColumn colVType;
    public TableColumn colVMaximumWeight;
    public TableColumn colNoOfPassengers;
    public TableView tblDriverDetails;
    public TableColumn colDName;
    public TableColumn colDNIC;
    public TableColumn colDDrivingLicenseNo;
    public TableColumn colDAddress;
    public TableColumn colDContactNo;
    public JFXTextField txtNewVehicleNumber;
    public TextField txtSearchingVNoToUpdate;
    //public JFXTextField txtNewVehicleType;
    public JFXTextField txtVehicleWeight;
    public JFXTextField txtNumberOfPassengers;
    public TextField txtSearchingVNoToDelete;
    public TextField txtSearchingDNICToUpdate;
    public JFXTextField txtNewDriverName;
    public JFXTextField txtDriverNewNIC;
    public JFXTextField txtNewDrivingLicenseNo;
    public JFXTextField txtNewAddress;
    public JFXTextField txtNewContactNumber;
    public TextField txtSearchingDriverNICToDelete;
    double x=0;
    double y=0;

    public void logout(ActionEvent actionEvent) throws IOException {
        try {
            Stage stage = (Stage) editDetailsContext.getScene().getWindow();
            stage.close();

        }catch (Exception e){}
    }

    public void backToManagementActivities(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ManagementActivities.fxml");
        Parent load = FXMLLoader.load(resource);
        editDetailsContext.getChildren().clear();
        editDetailsContext.getChildren().add(load);
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
    public void initialize(){
        colVNumber.setCellValueFactory(new PropertyValueFactory("vehicleNumber"));
        colVType.setCellValueFactory(new PropertyValueFactory("vehicleType"));
        colVMaximumWeight.setCellValueFactory(new PropertyValueFactory("maximumWeight"));
        colNoOfPassengers.setCellValueFactory(new PropertyValueFactory("numberOfPassengers"));

        tblVehicleDetails.setItems(FXCollections.observableArrayList(vehicleArrayList));

        colDName.setCellValueFactory(new PropertyValueFactory("name"));
        colDNIC.setCellValueFactory(new PropertyValueFactory("nic"));
        colDDrivingLicenseNo.setCellValueFactory(new PropertyValueFactory("drivingLicenseNo"));
        colDAddress.setCellValueFactory(new PropertyValueFactory("address"));
        colDContactNo.setCellValueFactory(new PropertyValueFactory("contactNo"));

        tblDriverDetails.setItems(FXCollections.observableArrayList(driverArrayList));
    }


    public void updateVehicleDetails(ActionEvent actionEvent) {
        int count=0;
        try {
            for (Vehicle v: vehicleArrayList) {
                if(txtSearchingVNoToUpdate.getText().equalsIgnoreCase(v.getVehicleNumber())){
                    v.setMaximumWeight(Integer.parseInt(txtVehicleWeight.getText()));
                    v.setNumberOfPassengers(Integer.parseInt(txtNumberOfPassengers.getText()));
                    count = 1;
                    break;
                }else if(!txtSearchingVNoToUpdate.getText().equalsIgnoreCase(v.getVehicleNumber())){
                    count=-1;
                }
            }

            pageRefresh();
        }catch (Exception e){}
        finally {
            if (count==1){
                new Alert(Alert.AlertType.CONFIRMATION, "Vehicle details Updated successfully", ButtonType.OK).show();
            }else if(count==-1){
                new Alert(Alert.AlertType.WARNING, "Vehicle Number Does not exists..Please try again..!", ButtonType.OK).show();
            }
        }
    }

    public void deleteVehicle(ActionEvent actionEvent) {
        int count=0;
        try {
            for (Vehicle v : vehicleArrayList) {
                if (txtSearchingVNoToDelete.getText().equalsIgnoreCase(v.getVehicleNumber())){
                    vehicleArrayList.remove(v);
                    count=1;
                    break;
                }else if(!txtSearchingVNoToDelete.getText().equalsIgnoreCase(v.getVehicleNumber())){
                    count=-1;
                }
            }
            for(OnDelivery d : onDelivery){
                if(txtSearchingVNoToDelete.getText().equalsIgnoreCase(d.getVehicleNumber())){
                    onDelivery.remove(d);
                    break;
                }
            }
            for(InParking p : inParking){
                if(txtSearchingVNoToDelete.getText().equalsIgnoreCase(p.getVehicleNumber())){
                    inParking.remove(p);
                    break;
                }
            }
            pageRefresh();
        }catch (Exception e){}
        finally {
            if(count==1){
                new Alert(Alert.AlertType.CONFIRMATION, "Vehicle details Deleted successfully", ButtonType.OK).show();
            }else if (count==-1){
                new Alert(Alert.AlertType.WARNING, "Vehicle Number Does not exists..Please try again..!", ButtonType.OK).show();
            }
        }
    }


    public void updateDriverDetails(ActionEvent actionEvent) {
        int count=0;
        try {
            for (Driver d : driverArrayList) {
                if(txtSearchingDNICToUpdate.getText().equalsIgnoreCase(d.getNic())){
                    d.setName(txtNewDriverName.getText());
                    d.setNic(txtDriverNewNIC.getText());
                    d.setDrivingLicenseNo(txtNewDrivingLicenseNo.getText());
                    d.setAddress(txtNewAddress.getText());
                    d.setContactNo(txtNewContactNumber.getText());
                    count=1;
                    break;
                }else if(!txtSearchingDNICToUpdate.getText().equalsIgnoreCase(d.getNic())){
                    count=-1;
                }
            }
            pageRefresh();
        }catch (Exception e){}
        finally {
            if(count==1){
                new Alert(Alert.AlertType.CONFIRMATION, "Driver Details Updated Successfully..!", ButtonType.OK).show();
            }else if(count==-1){
                new Alert(Alert.AlertType.WARNING, "Driver NIC Does not exists..Please try again..!", ButtonType.OK).show();
            }

        }
    }

    public void deleteDriverDetails(ActionEvent actionEvent) {
        int count=0;
        try {
            for (Driver d : driverArrayList) {
                if(txtSearchingDriverNICToDelete.getText().equalsIgnoreCase(d.getNic())){
                    driverArrayList.remove(d);
                    count=1;
                    break;
                }else if(!txtSearchingDriverNICToDelete.getText().equalsIgnoreCase(d.getNic())){
                    count=-1;
                }
            }
            pageRefresh();
        }catch (Exception e){}
        finally {
            if (count==1){
                new Alert(Alert.AlertType.CONFIRMATION, "Driver Details Deleted Successfully..!", ButtonType.OK).show();
            }else if (count==-1){
                new Alert(Alert.AlertType.WARNING, "Driver NIC does not exists..!", ButtonType.OK).show();
            }
        }
    }

    public void pageRefresh() throws IOException {
        URL resource = getClass().getResource("../view/EditDetails.fxml");
        Parent load = FXMLLoader.load(resource);
        editDetailsContext.getChildren().clear();
        editDetailsContext.getChildren().add(load);
    }

    public void goToSummery(ActionEvent actionEvent) throws IOException {
        try {
            URL resource = getClass().getResource("../view/Summery.fxml");
            Parent load = FXMLLoader.load(resource);
            editDetailsContext.getChildren().clear();
            editDetailsContext.getChildren().add(load);
        }catch (Exception e){}
    }
}
