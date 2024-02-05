package Program.Start;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;



public class Start extends Application {

    public void start(Stage primaryStage) throws IOException {
        // Завантаження FXML файлу
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Program/Start/Start.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // Встановлення стилів лише для першого FXML файлу
        primaryStage.initStyle(StageStyle.UNDECORATED);

        Image image1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Program/resources/logo_.png")));

        // Завантаження зображення
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Program/resources/logo.png")));
        ImageView logoImageView = new ImageView(image);


        // Встановлення положення зображення
        logoImageView.setFitWidth(400);
        logoImageView.setFitHeight(400);
        logoImageView.setOpacity(0.0); // Початкова прозорість - 0

        // Створення анімації з'явлення та зникнення
        FadeTransition fadeInOut = new FadeTransition(Duration.seconds(2), logoImageView);
        fadeInOut.setFromValue(0.0);
        fadeInOut.setToValue(1.0);
        fadeInOut.setCycleCount(2);
        fadeInOut.setAutoReverse(true);

        // Запуск анімації перед показом сцени
        fadeInOut.play();

        // Додавання зображення до кореневого вузла
        Pane rootPane = (Pane) root;
        rootPane.getChildren().add(logoImageView);

        // Налаштування вікна

        primaryStage.getIcons().add(image1);
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(400);
        primaryStage.setTitle("АНТИсуржик");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        // Після зникнення зображення відкрити новий FXML файл
        fadeInOut.setOnFinished(event -> {
            rootPane.getChildren().remove(logoImageView);
            openNewFXML(primaryStage);
        });
    }

    private void openNewFXML(Stage primaryStage)
    {
        try {
            Parent startRoot = FXMLLoader.load(getClass().getResource("/Program/Main/Main.fxml"));
            Image image1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Program/resources/logo_.png")));
            Stage startStage = new Stage();
            startStage.setScene(new Scene(startRoot));
            startStage.centerOnScreen();
            startStage.getIcons().add(image1);
            startStage.setResizable(false);
            startStage.setTitle("АНТИсуржик");

            // Закриття попереднього вікна
            primaryStage.close();

            startStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

