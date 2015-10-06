package me.stoliarov.anycipher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import me.stoliarov.anycipher.cipher.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Controller {
    private Stage stage;

    @FXML
    private PasswordField keyField;
    @FXML
    private ComboBox<Ciphers> choiceBox;
    private ObservableList<Ciphers> choiceBoxData = FXCollections.observableArrayList();
    @FXML
    private TextArea sourceField;
    @FXML
    private TextArea resultField;
    private Ciphers selected;

    @FXML
    void cypher(ActionEvent event) {
        try {
            checkPromptData();
            Class aClass = selected.className;
            Constructor constructor = aClass.getConstructor(new Class[]{String.class});
            Cipher cipher = (Cipher) constructor.newInstance(keyField.getText());
            resultField.setText(cipher.getEncrypted(sourceField.getText()));

        } catch (InstantiationException | IllegalAccessException
                | NoSuchMethodException | InvocationTargetException iae) {
            checkPromptData();
        }
    }
     private void checkPromptData() {
         if ((keyField.getText().equals("")||(sourceField.getText().equals(""))||(choiceBox.getSelectionModel().getSelectedIndex()==-1))) {
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Ошибка!!");
             alert.setHeaderText("Проверьте введенные данные!");
             alert.setContentText("Возможно вы забили ввести исходные данные или выбрать метод.");
             alert.showAndWait();
         }
     }
    @FXML
    void decypher(ActionEvent event) {
        try {
            checkPromptData();
            Class aClass = selected.className;
            Constructor constructor = aClass.getConstructor(new Class[]{String.class});
            Cipher cipher = (Cipher) constructor.newInstance(keyField.getText());

            resultField.setText(cipher.getDecrypted(sourceField.getText()));

        } catch (InstantiationException | IllegalAccessException
                | NoSuchMethodException | InvocationTargetException iae) {
            checkPromptData();
        }
    }

    @FXML
    public void initialize() {
        System.out.println("initialized");
        choiceBoxData.add(new Ciphers("Шифр Цезаря", CezarCipher.class));
        choiceBoxData.add(new Ciphers("Лозунговый шифр", SloganCipher.class));
        choiceBoxData.add(new Ciphers("Книжный шифр", BookCipher.class));
        choiceBoxData.add(new Ciphers("Шифрующие таблицы", TableCipher.class));
        choiceBoxData.add(new Ciphers("Шифр вертикальной перестановки", VertPermutationCipher.class));
        choiceBoxData.add(new Ciphers("Шифр Виженера", VigenereCipher.class));
        choiceBoxData.add(new Ciphers("Шифр модульного гаммирования", ModGammaCypher.class));
        choiceBox.setItems(choiceBoxData);
        choiceBox.promptTextProperty().setValue("выберите метод");
        choiceBox.setCellFactory((comboBox) -> {
            return new ListCell<Ciphers>() {
                @Override
                protected void updateItem(Ciphers item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.name);
                    }
                }
            };
        });

        choiceBox.setConverter(new StringConverter<Ciphers>() {
            @Override
            public String toString(Ciphers object) {
                if (object == null) {
                    return null;
                } else {
                    return object.name;
                }
            }

            @Override
            public Ciphers fromString(String string) {
                return null;
            }
        });

        choiceBox.setOnAction((event -> {
            selected = choiceBox.getSelectionModel().getSelectedItem();
        }));
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private class Ciphers {
        private final String name;
        private final Class className;

        public Ciphers(String name, Class className) {
            this.name = name;
            this.className = className;
        }
    }
}
