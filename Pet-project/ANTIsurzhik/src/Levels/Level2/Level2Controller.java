package Levels.Level2;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Level2Controller
{
    @FXML
    private GridPane gridPane;
    @FXML
    private TextField textField1, textField2, textField3, textField4, textField5, textField6, textField7, textField8,
            textField9, textField10, textField11, textField12, textField13, textField14, textField15, textField16, textField17, textField18,
            textField19, textField20, textField21, textField22, textField23, textField24, textField25, textField26,
            textField27, textField28, textField29, textField30, textField31, textField32, textField33, textField34,
            textField35, textField36, textField37, textField38, textField39, textField40, textField41, textField42, textField43,
            textField44, textField45, textField46, textField47, textField48, textField49, textField50, textField51, textField52;

    @FXML
    private Text incorrectWord1, incorrectWord2, incorrectWord3, incorrectWord4, incorrectWord5, incorrectWord6;

    private List<String> correctWordList;
    private List<Text> incorrectWordList;
    private List<List<TextField>> textFieldList;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void initialize() // створюємо масив полів. В кожній комірці масива слово
    {
        textFieldList = new ArrayList<>();
        textFieldList.add(Arrays.asList(textField1, textField2, textField3, textField4, textField5, textField6, textField7, textField8));
        textFieldList.add(Arrays.asList(textField8, textField9, textField10, textField11, textField12, textField13, textField14, textField15, textField16, textField17, textField18));
        textFieldList.add(Arrays.asList(textField19, textField20, textField21, textField10, textField22, textField23, textField24, textField25, textField26));
        textFieldList.add(Arrays.asList(textField27, textField28, textField29,textField30, textField31, textField32, textField33, textField34, textField17));
        textFieldList.add(Arrays.asList(textField35, textField36, textField37, textField38, textField39, textField32, textField40, textField41, textField42, textField43));
        textFieldList.add(Arrays.asList(textField44, textField45, textField46, textField47, textField29, textField48, textField49, textField50, textField51, textField52));

        incorrectWordList = Arrays.asList(incorrectWord1, incorrectWord2, incorrectWord3, incorrectWord4,
                incorrectWord5, incorrectWord6);

        setCorrectWords();
        maxCharactersInTextField();
    }

    public void setCorrectWords() // з файлу дістати слова
    {
        correctWordList = new ArrayList<>();

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("src/Levels/Level2/level2Words.txt"));
            
            String s = "";
            while ((s = reader.readLine()) != null)
                correctWordList.add(s);

            reader.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void addTextListeners(KeyEvent event)
    {
        maxCharactersInTextField();
    }

    private void maxCharactersInTextField() // метод для встановлення одного символа до поля
    {
        for(List<TextField> textFields: textFieldList)
        {
            for (TextField textField : textFields)
            {
                textField.setFont(Font.font(20));
                textField.textProperty().addListener((observable, oldValue, newValue) ->
                {
                    String text = textField.getText();

                    if (!text.matches("^[а-яєіїґ]*$") || text.contains("ы") || text.contains("ъ") || text.contains("э"))
                        textField.setText(oldValue);

                    if (text.length() > 1)
                        textField.setText(text.substring(0, 1));

                    processTextFields();
                });
            }
        }
    }

    private void processTextFields()
    {
        for (int i = 0; i < textFieldList.size(); i++)
        {
            if(checkWordFieldsCompleted(textFieldList.get(i)))
                wordCorrectChecker(i);

            else
                changeColorOfTextFields("incomplete", textFieldList.get(i), "#FFFFFF", "-fx-border-color: #000000;", "-fx-border-radius: 5;", "-fx-background-radius: 5;");
        }
    }

    private boolean isTextFieldColorGreen(List<TextField> textFields) // чи комірка зеленого кольору ? перевірка
    {
        for (TextField textField : textFields)
        {
            if (textField.getStyle().contains("#8FD5AB"))
                return true;
        }
        return false;
    }

    private String getWordFromTextFields(List<TextField> textFields) // отримати слово з комірок
    {
        StringBuilder sb = new StringBuilder();
        for (TextField textField : textFields)
            sb.append(textField.getText());

        return sb.toString();
    }

    private boolean checkWordFieldsCompleted(List<TextField> textFields) // перевірити чи всі комірки одного слова заповнені
    {
        for (TextField textField: textFields)
            if (textField.getText().length() < 1)
                return false;

        return true;
    }

    private void changeColorOfTextFields(String flag, List<TextField> textFields, String color, String... styleProperties) // поміняти задній колір комірок
    {
        int textFieldCounter = 0;
        for (TextField textField: textFields)
        {
            if(flag.equals("correct"))
                textField.setStyle("-fx-background-color: " + color + ";" + String.join(" ", styleProperties));

            else if(flag.equals("incorrect") && !textField.getStyle().contains("#8FD5AB"))
                textField.setStyle("-fx-background-color: " + color + ";" + String.join(" ", styleProperties));

            else if(flag.equals("incomplete"))
            {
                if (textFieldCounter == 0)
                {
                    for (TextField tf : textFields)
                    {
                        if (tf.getStyle().contains("#EAD38E"))
                            textFieldCounter++;
                    }
                }
                if(textFieldCounter > 3)
                    textField.setStyle("-fx-background-color: " + color + ";" + String.join(" ", styleProperties));
            }
        }
    }

    private void wordCorrectChecker(int wordNumber)
    {
        List<TextField> textFields = textFieldList.get(wordNumber);

        if(getWordFromTextFields(textFields).equals(correctWordList.get(wordNumber)))
        {
            changeColorOfTextFields("correct", textFields, "#8FD5AB", "-fx-border-color: #000000;", "-fx-border-radius: 5;", "-fx-background-radius: 5;");
            incorrectWordList.get(wordNumber).setStyle("-fx-strikethrough: true;");
            incorrectWordList.get(wordNumber).setOpacity(0.3);
            for (TextField textField: textFields)
                textField.setEditable(false);

            checkAllWordsCorrectlyEntered();
        }
        else
            changeColorOfTextFields("incorrect", textFields, "#EAD38E", "-fx-border-color: #000000;", "-fx-border-radius: 5;", "-fx-background-radius: 5;");
    }

    @FXML
    private void textFieldMove(KeyEvent event) // метод для пересування між клітинками
    {
        KeyCode keyCode = event.getCode();
        Node source = (Node) event.getSource();
        int col = GridPane.getColumnIndex(source);
        int row = GridPane.getRowIndex(source);

        Node currentNode;
        switch (keyCode)
        {
            case UP:
                currentNode = getNodeByRowColumnIndex(row - 1, col);
                if (currentNode != null)
                    currentNode.requestFocus();
                break;

            case DOWN:
                currentNode = getNodeByRowColumnIndex(row + 1, col);
                if (currentNode != null)
                    currentNode.requestFocus();
                break;

            case LEFT:
                currentNode = getNodeByRowColumnIndex(row, col - 1);
                if (currentNode != null)
                    currentNode.requestFocus();
                break;

            case RIGHT:
                currentNode = getNodeByRowColumnIndex(row, col + 1);
                if (currentNode != null)
                    currentNode.requestFocus();
                break;

            default:
                break;
        }
    }

    private Node getNodeByRowColumnIndex(int row, int column) // додатковий метод для пересування - визначення адреси наступного поля
    {
        ObservableList<Node> children = gridPane.getChildren();
        for (Node node : children)
        {
            Integer rowIndex = GridPane.getRowIndex(node);
            Integer columnIndex = GridPane.getColumnIndex(node);

            if (rowIndex != null && columnIndex != null && rowIndex == row && columnIndex == column)
                return node;

        }
        return null;
    }

    // Оголосити змінну для збереження посилання на вікно
    private Stage winStage;

    private void checkAllWordsCorrectlyEntered()
    {
        for (List<TextField> fields : textFieldList)
        {
            for (TextField textField : fields)
            {
                if (!isTextFieldColorGreen(Collections.singletonList(textField)))
                    return; // Якщо знайдено незелену комірку, вийти з методу
            }
        }
        // Виконується, якщо всі комірки зеленого кольору
        if (winStage == null)
        {
            try
            {
                Image image1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Program/resources/logo_.png")));
                Parent klitschkoRoot = FXMLLoader.load(getClass().getResource("/Levels/Level2/Klitschko/Klitschko.fxml"));
                winStage = new Stage();
                winStage.getIcons().add(image1);
                winStage.setScene(new Scene(klitschkoRoot));
                winStage.centerOnScreen();
                winStage.setResizable(false);
                winStage.setTitle("АНТИсуржик");
                // Закриття попереднього вікна
                Stage previousStage = (Stage) textFieldList.get(0).get(0).getScene().getWindow();
                previousStage.close();   // я додала зачинення вікна, а ще походу треба отут база даних для збереження результату ....

            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }

        if(!winStage.isShowing())
            winStage.show();

        else
            winStage.toFront();
    }

    @FXML
    void switchTo_Choose(ActionEvent event) throws IOException  //кнопка виходу
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setHeaderText("Ви впевнені, що хочете вийти із гри?");
        alert.setContentText("Ваші дані не буде збережено");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) // Вийти із програми
        {
            root = FXMLLoader.load(getClass().getResource("/Program/ChooseLevel/LevelChoose.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Program/resources/logo_.png")));
            stage.getIcons().add(image);
            stage.setMinWidth(600);
            stage.setMinHeight(400);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        }

        else
            event.consume();
    }
}