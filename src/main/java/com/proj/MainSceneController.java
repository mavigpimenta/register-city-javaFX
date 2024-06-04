package com.proj;

import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class MainSceneController {
    
  public MainSceneController() {}

  public static Scene CreateScene() throws Exception
  {
    URL sceneUrl = MainSceneController.class.getResource("main-scene.fxml");
    FXMLLoader loader = new FXMLLoader(sceneUrl);
    Parent root = loader.load();
    Scene scene = new Scene(root);
    return scene;
  }


  @FXML
  protected Button btCity;
  @FXML
  protected Button btState;

  @FXML
  protected void openCityScene(ActionEvent e) throws Exception {
      // Fechando o login
      Stage crrStage = (Stage)btCity
          .getScene().getWindow();
      crrStage.close();

      // Abrindo a tela principal
      Stage stage = new Stage();
      Scene scene = CitySceneController.CreateScene();
      stage.setScene(scene);
      stage.show();
  }  

  @FXML
  protected void openStateScene(ActionEvent e) throws Exception {
      // Fechando o login
      Stage crrStage = (Stage)btState
          .getScene().getWindow();
      crrStage.close();

      // Abrindo a tela principal
      Stage stage = new Stage();
      Scene scene = StateSceneController.CreateScene();
      stage.setScene(scene);
      stage.show();
  }  
}