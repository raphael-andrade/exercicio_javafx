package javafx.javafx.gui;

import javafx.javafx.gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.javafx.application;
import javafx.javafx.model.services.SellerService;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.javafx.model.services.DepartmentService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class MainViewController implements Initializable {

    @FXML
    private MenuItem menuItemSeller;

    @FXML
    private MenuItem menuItemDepartment;

    @FXML
    private MenuItem menuItemAbout;

    @FXML
    public void onMenuItemSellerAction() {

        loadView("SellerList.fxml",(SellerListController controller) -> {
            controller.setSellerService(new SellerService());
            controller.updateTableView();
        });
    }
    @FXML
    public void onMenuItemDeparmentAction() {

        loadView("DepartmentList.fxml",(DepartmentListController controller) -> {
            controller.setDeparmentService(new DepartmentService());
            controller.updateTableView();
        });
    }
    @FXML
    public void onMenuItemAboutAction() {
         loadView("About.fxml", x -> {});
    }
    @Override
    public void initialize(URL uri, ResourceBundle rb) {
    }

    private synchronized <T> void loadView (String absoluteName, Consumer<T> initializingAction){
        try {
            FXMLLoader loader = new FXMLLoader(application.class.getResource(absoluteName));
            VBox newVbox = loader.load();

            Scene mainScene = application.getMainScene();
            VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

            Node mainMenu = mainVBox.getChildren().get(0);
            mainVBox.getChildren().clear();
            mainVBox.getChildren().add(mainMenu);
            mainVBox.getChildren().addAll(newVbox.getChildren());

            T controller = loader.getController();
            initializingAction.accept(controller);

        }
        catch (IOException e) {
            Alerts.showAlert("IOException", "Error loading view",e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}