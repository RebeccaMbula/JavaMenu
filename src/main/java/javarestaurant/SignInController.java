package javarestaurant;

import com.gluonhq.charm.glisten.control.TextField;
import httpComm.HttpComm;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.controlsfx.control.GridCell;
import org.controlsfx.control.cell.ImageGridCell;
import org.json.JSONObject;
import scene.SceneSwitch;

import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements Initializable {

    @FXML TextField txtEmail;
    @FXML PasswordField txtPassword;
    @FXML Label lbError;
    @FXML Node root;
    @FXML GridPane signInNode;
    @FXML GridPane mainPane;
    private ProgressIndicator progressIndicator = new ProgressIndicator();
//    VBox progressBox = new VBox(progressIndicator);


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        progressBox.setAlignment(Pos.CENTER);
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setHue(0.9);
        progressIndicator.setEffect(colorAdjust);
        mainPane.add(progressIndicator, 1, 6);
        progressIndicator.setVisible(false);


        lbError.setTextFill(Color.web("#ffffff"));
        GridPane.setHalignment(lbError, HPos.CENTER);
    }

    public void onSubmit(ActionEvent actionEvent) {
        lbError.setText("");
        if(!txtEmail.getText().isEmpty() && !txtPassword.getText().isEmpty()){
            Task<Void> verifierTask = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    JSONObject response = HttpComm.signIn(
                            txtEmail.getText(), txtPassword.getText());
                    Platform.runLater(() -> handleResponse(response, actionEvent));
                    return null;
                }
            };
            progressIndicator.visibleProperty().bind(verifierTask.runningProperty());
            new Thread(verifierTask).start();
        }
    }

    private void handleResponse(JSONObject response, ActionEvent actionEvent){
        if(response.get("valid")!=null && (boolean)response.get("valid")){
            //Go to next page
            SceneSwitch.goTo("/fxml/menu.fxml", actionEvent, SignInController.class);
            System.out.println("Go to next page");
        } else {
            showErrorMessage((String)response.get("error_message"));
            txtPassword.setText("");
        }
    }

    private void showErrorMessage(String error){
//        Label errorMessage = new Label(error);
        lbError.setText(error);
    }
}
