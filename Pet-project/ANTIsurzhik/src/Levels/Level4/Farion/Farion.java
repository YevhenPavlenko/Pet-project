package Levels.Level4.Farion;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.IOException;

public class Farion {
    private Clip audioClip;

    @FXML
    private Button chooseButton;
    @FXML
    private ImageView conf;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public Farion() {
        playLevelCompleteMusic();
        hideConfAfterDelay();
    }

    public void playLevelCompleteMusic() {
        try {
            String musicFile = "level_complete.wav";
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/Levels/Level1/Azarov/" + musicFile));
            // AudioInputStream audioStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("scr/Program/resources/level_complete.wav"));
            audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);
            audioClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hideConfAfterDelay() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            conf.setVisible(false);
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }

    @FXML
    private void switchTo_Choose(ActionEvent event) { //повернутися до вибору
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Program/ChooseLevel/LevelChoose.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
