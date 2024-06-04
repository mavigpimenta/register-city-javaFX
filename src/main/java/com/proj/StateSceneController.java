package com.proj;

import java.net.URL;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.proj.model.StateData;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class StateSceneController {
    
  public StateSceneController() {}

  public static Scene CreateScene() throws Exception
  {
    URL sceneUrl = StateSceneController.class.getResource("state-scene.fxml");
    FXMLLoader loader = new FXMLLoader(sceneUrl);
    Parent root = loader.load();
    Scene scene = new Scene(root);
    return scene;
  }


  @FXML
  protected TextField tfStateName;
  @FXML
  protected Button btRegisterState;

  @FXML
  protected void submit(ActionEvent e) throws Exception {
    AuthState auth = AuthState.tryState(tfStateName.getText());

    if (auth.stateExists()) {
      Alert alert = new Alert(
        AlertType.ERROR,
        "Estado já existente",
        ButtonType.OK
      );
      alert.showAndWait();
      return; 
    }

    Session session = HibernateUtil
      .getSessionFactory()
      .getCurrentSession();
    Transaction transaction = session.beginTransaction();

    String stateName = tfStateName.getText();

    session.save(new StateData(stateName));
    transaction.commit();

    Alert alert = new Alert(
      AlertType.INFORMATION,
      "Estado Cadastrado!",
      ButtonType.OK
    );
    alert.showAndWait();

    // Fechando o login
    Stage crrStage = (Stage)btRegisterState
    .getScene().getWindow();
    crrStage.close();

    // Abrindo a tela principal
    Stage stage = new Stage();
    Scene scene = MainSceneController.CreateScene();
    stage.setScene(scene);
    stage.show();    
  }
}