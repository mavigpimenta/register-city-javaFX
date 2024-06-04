package com.proj;

import java.net.URL;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.proj.model.CityData;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CitySceneController {
    
  public CitySceneController() {}

  public static Scene CreateScene() throws Exception
  {
    URL sceneUrl = CitySceneController.class.getResource("city-scene.fxml");
    FXMLLoader loader = new FXMLLoader(sceneUrl);
    Parent root = loader.load();
    Scene scene = new Scene(root);
    
    CitySceneController controller = loader.getController();
    controller.loadtStateFromDataBase();

    return scene;
  }


  @FXML
  protected TextField tfCityName;
  @FXML
  protected Button btRegisterCity;
  @FXML
  protected ComboBox cbState;

  @FXML
  protected void submit(ActionEvent e) throws Exception {
    AuthCity auth = AuthCity.tryCity(tfCityName.getText());

    if (auth.cityExists()) {
      Alert alert = new Alert(
        AlertType.ERROR,
        "Cidade já existente",
        ButtonType.OK
      );
      alert.showAndWait();
      return; 
    }
    
    Session session = HibernateUtil
      .getSessionFactory()
      .getCurrentSession();
    Transaction transaction = session.beginTransaction();

    String cityName = tfCityName.getText();
    String state = (cbState.getValue()).toString();

    System.out.println("oi");
    System.out.println(state);

    Query queryStates = session.createQuery(
      "from StateData where StateName = :state"
    );
    queryStates.setParameter("state", state);
    List<StateData> states = queryStates.list();

    session.save(new CityData(cityName, states.get(0)));
    transaction.commit();

    Alert alertOk = new Alert(
      AlertType.INFORMATION,
      "Cidade Cadastrada!",
      ButtonType.OK
    );
    alertOk.showAndWait();

    // Fechando o login
    Stage crrStage = (Stage)btRegisterCity
    .getScene().getWindow();
    crrStage.close();

    // Abrindo a tela principal
    Stage stage = new Stage();
    Scene scene = MainSceneController.CreateScene();
    stage.setScene(scene);
    stage.show();
    
  }

  public void loadtStateFromDataBase() {
    Session session = HibernateUtil
      .getSessionFactory()
      .getCurrentSession();
    Transaction transaction = session.beginTransaction();

    Query queryState = session.createQuery(
      "from StateData"
    );

    List<StateData> states = queryState.list();

    for (StateData state : states) {
      cbState.getItems().add(state.getStateName());
    }

    transaction.commit();
  }
}