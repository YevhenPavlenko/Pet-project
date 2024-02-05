package Program.ChooseLevel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LevelChooseController
{
    @FXML
    private Button Go_Back;

    @FXML
    private Button Level_1;
    @FXML
    private Button Level_2;
    @FXML
    private Button Level_3;
    @FXML
    private Button Level_4;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void switchTo_Level1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Levels/Level1/Level1.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void switchTo_Level2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Levels/Level2/Level2.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void switchTo_Level3(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Levels/Level3/Level3.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void switchTo_Level4(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Levels/Level4/Level4.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void AddShadow1(MouseEvent event) {//при наведенні на кнопку певного рівня з'являється тінь
        Shadow(Level_1);
    }
    @FXML
    void AddShadow2(MouseEvent event) {
        Shadow(Level_2);
    }
    @FXML
    void AddShadow3(MouseEvent event) {
        Shadow(Level_3);
    }
    @FXML
    void AddShadow4(MouseEvent event) {
        Shadow(Level_4);
    }

    public void Shadow(Button button){ //метод для додавання тіні для кнопки рівня
        // Додавання тіні до круга
        DropShadow dropShadow = new DropShadow();
        button.setEffect(dropShadow);

        button.setOnMouseClicked((MouseEvent mouseEvent) -> {
            // Перевірка стану круга
            if ( button.getEffect() == null) {
                // Якщо ефект відсутній, додаємо тінь
                button.setEffect(dropShadow);
            } else {
                // Якщо ефект присутній, видаляємо тінь
                button.setEffect(null);
            }
        });
        button.setOnMouseExited((MouseEvent mouseEvent) -> {
            // Перевірка стану круга
            if ( button.getEffect() != null) {
                // Якщо ефект присутній, видаляємо тінь
                button.setEffect(null);
            }
        });
    }
    @FXML
    void switchTo_Main(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/Program/Main/Main.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setMinWidth(600);
        stage.setMinHeight(400);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    }

