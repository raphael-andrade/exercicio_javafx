package javafx.javafx.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.javafx.db.DbException;
import javafx.javafx.entities.Department;
import javafx.javafx.gui.listeners.DataChangeListener;
import javafx.javafx.gui.util.Alerts;
import javafx.javafx.gui.util.Constraints;
import javafx.javafx.gui.util.Utils;
import javafx.javafx.model.exception.ValidationException;
import javafx.javafx.model.services.DepartmentService;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;

public class DepartmentFormController implements Initializable {

    private Department entity;
    private DepartmentService service;
    private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private Label labelErrorName;

    @FXML
    private Button btSave;

    @FXML
    private Button btCancel;

    public void setDepartment(Department entity){
        this.entity = entity;
    }

    public void setDepartmentService (DepartmentService service){
        this.service = service;
    }

    public void subscribeDataChangeListeners(DataChangeListener listener){
        dataChangeListeners.add(listener);
    }

    @FXML
    public void onBtSaveAction(ActionEvent event) {
        if (entity == null){
            throw new IllegalStateException("Entity was null");
        }
        if (service == null){
            throw  new IllegalStateException("Service was null");
        }
        try {
            entity = getFormData();
            service.saveOrUpdate(entity);
            notifyDataChangeListeners();
            Utils.currentStage(event).close();
        }
        catch (ValidationException e){
            setErrorMessages(e.getErrors());
        }
        catch (DbException e){
            Alerts.showAlert("Error saving object", null, e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void notifyDataChangeListeners() {
        for (DataChangeListener listener : dataChangeListeners){
            listener.onDataChanged();
        }
    }

    private Department getFormData() {
        Department obj = new Department();

        ValidationException exception = new ValidationException("Validation error");

        obj.setId(Utils.tryParseToInt(txtId.getText()));
        if (txtName.getText() == null || txtName.getText().trim().equals("")){
            exception.addError("Name","Field can't be empty");
        }
        obj.setName(txtName.getText());

        if (exception.getErrors().size()>0 ){
            throw exception;
        }
        return obj;
    }

    @FXML
    public void onBtCancelAction(ActionEvent event) {
        Utils.currentStage(event).close() ;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeNodes();
    }

    private void initializeNodes(){
        Constraints.setTextFieldInteger(txtId);
        Constraints.setTextFieldMaxLength(txtName,30);
    }

    public void updateFormData(){
        if(entity == null){
            throw new IllegalStateException("Entity was null");
        }
        txtId.setText(String.valueOf(entity.getId()));
        txtName.setText(entity.getName());
    }
    private void setErrorMessages(Map<String, String> errors){
        Set<String> fields = errors.keySet();
        if (fields.contains("Name")) {
            labelErrorName.setText(errors.get("Name"));
        }
    }
}
