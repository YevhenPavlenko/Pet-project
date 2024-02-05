package Program.About;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AboutController {
    @FXML
    private Button Go_Back;
    @FXML
    private TextArea aboutTextArea;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void initialize() {
        try {
            // Відкриття файлу для читання
           // BufferedReader reader = new BufferedReader(new FileReader("C:/Users/user/IdeaProjects/ANTIsurzhik/src/Program/resources/about.txt"));
            BufferedReader reader = new BufferedReader(new FileReader("src/Program/resources/about.txt"));


            // Зчитування тексту з файлу
            StringBuilder text = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line).append("\n");
            }

            // Встановлення тексту у TextArea
            aboutTextArea.setText(text.toString());

            // Закриття файлу
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
