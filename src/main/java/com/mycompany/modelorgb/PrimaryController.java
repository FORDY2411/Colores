package com.mycompany.modelorgb;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class PrimaryController {
    
    @FXML private TextField txtRojo, txtVerde, txtAzul;
    @FXML private Pane paneTextFields;
    
    @FXML private Spinner<Integer> spnRojo, spnVerde, spnAzul;
    @FXML private Pane paneSpinners;
    
    @FXML private Slider sldRojo, sldVerde, sldAzul;
    @FXML private Pane paneSliders;
    
    @FXML private CheckBox chkRojo, chkVerde , chkAzul;
    @FXML private Pane paneChackBoxes;
    
    @FXML private RadioButton radRojo, radVerde, radAzul;
    @FXML private ToggleGroup grupoColores;
    @FXML private Pane paneRadioButtons;
    
    @FXML private ToggleButton tglRojo, tglVerde, tglAzul;
    @FXML private ToggleGroup grupoToggle;
    @FXML private Pane paneToggleButtons;
    
    @FXML private ColorPicker cpColor;
    @FXML private Pane paneColorPicker;
    
    @FXML private TextField txtHex;
    @FXML private Pane paneHex;
    
    @FXML private TextArea txtAreaInfo;
    @FXML private Pane paneAreaInfo;
    
    @FXML 
    public void initialize(){
        configurarSection1textFields();
        configurarSection2Spinners() ;
        configurarSection3Sliders();
        configurarSection4CheckBoxes();
        configurarSection5RadioButtons();
        configurarSection6ToggleButtons();
        configurarSection7ColorPicker();
        configurarSection8TextFieldHex(); 
    }
    private void configurarSection1textFields(){
        Runnable actualizar = () -> {
            int r = parsearEntero(txtRojo.getText());
            int g = parsearEntero(txtVerde.getText());
            int b = parsearEntero(txtAzul.getText());
            cambiarColorPane(paneTextFields, r, g, b);
            registrarCambio("TextFields", r, g, b);       
        };
        txtRojo.textProperty().addListener((obs, oldV, newV) -> actualizar.run());
        txtVerde.textProperty().addListener((obs, oldV, newV) -> actualizar.run());
        txtAzul.textProperty().addListener((obs, oldV, newV) -> actualizar.run());
    }
    private void configurarSection2Spinners() {
        spnRojo.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 255, 0));
        spnVerde.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 255, 0));
        spnAzul.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 255, 0));

        Runnable actualizar = () -> {
            int r = spnRojo.getValue();
            int g = spnVerde.getValue();
            int b = spnAzul.getValue();
            cambiarColorPane(paneSpinners, r, g, b);
            registrarCambio("Spinners", r, g, b);
        };

        spnRojo.valueProperty().addListener((obs, oldV, newV) -> actualizar.run());
        spnVerde.valueProperty().addListener((obs, oldV, newV) -> actualizar.run());
        spnAzul.valueProperty().addListener((obs, oldV, newV) -> actualizar.run());
    }

    private void configurarSection3Sliders() {
        Runnable actualizar = () -> {
            int r = (int) sldRojo.getValue();
            int g = (int) sldVerde.getValue();
            int b = (int) sldAzul.getValue();
            cambiarColorPane(paneSliders, r, g, b);
            registrarCambio("Sliders", r, g, b);
        };

        sldRojo.valueProperty().addListener((obs, oldV, newV) -> actualizar.run());
        sldVerde.valueProperty().addListener((obs, oldV, newV) -> actualizar.run());
        sldAzul.valueProperty().addListener((obs, oldV, newV) -> actualizar.run());
    }
    private void configurarSection4CheckBoxes() {
        Runnable actualizar = () -> {
            int r = chkRojo.isSelected() ? 255 : 0;
            int g = chkVerde.isSelected() ? 255 : 0;
            int b = chkAzul.isSelected() ? 255 : 0;
            cambiarColorPane(paneChackBoxes, r, g, b);
            registrarCambio("CheckBoxes", r, g, b);
        };

        chkRojo.selectedProperty().addListener((obs, oldV, newV) -> actualizar.run());
        chkVerde.selectedProperty().addListener((obs, oldV, newV) -> actualizar.run());
        chkAzul.selectedProperty().addListener((obs, oldV, newV) -> actualizar.run());
    }


    private void configurarSection5RadioButtons() {
        grupoColores.selectedToggleProperty().addListener((obs, oldV, newV) -> {
            int r = 0, g = 0, b = 0;
            if (newV == radRojo) r = 255;
            else if (newV == radVerde) g = 255;
            else if (newV == radAzul) b = 255;

            cambiarColorPane(paneRadioButtons, r, g, b);
            registrarCambio("RadioButtons", r, g, b);
        });
    }


    private void configurarSection6ToggleButtons() {
        grupoToggle.selectedToggleProperty().addListener((obs, oldV, newV) -> {
            int r = 0, g = 0, b = 0;
            if (newV == tglRojo) r = 255;
            else if (newV == tglVerde) g = 255;
            else if (newV == tglAzul) b = 255;

            cambiarColorPane(paneToggleButtons, r, g, b);
            registrarCambio("ToggleButtons", r, g, b);
        });
    }


    private void configurarSection7ColorPicker() {
        cpColor.valueProperty().addListener((obs, oldV, newColor) -> {
            if (newColor != null) {
                int r = (int) (newColor.getRed() * 255);
                int g = (int) (newColor.getGreen() * 255);
                int b = (int) (newColor.getBlue() * 255);
                cambiarColorPane(paneColorPicker, r, g, b);
                registrarCambio("ColorPicker", r, g, b);
            }
        });
    }

    private void configurarSection8TextFieldHex() {
        txtHex.textProperty().addListener((obs, oldV, hex) -> {
            try {
                if (!hex.startsWith("#")) hex = "#" + hex;
                Color color = Color.web(hex);
                int r = (int) (color.getRed() * 255);
                int g = (int) (color.getGreen() * 255);
                int b = (int) (color.getBlue() * 255);
                cambiarColorPane(paneHex, r, g, b);
                registrarCambio("TextField HEX", r, g, b);
            } catch (Exception e) {
            }
        });
    }

    private void cambiarColorPane(Pane pane, int r, int g, int b) {
        pane.setStyle(String.format("-fx-background-color: rgb(%d, %d, %d);", r, g, b));
    }

    private int parsearEntero(String texto) {
        try {
            int val = Integer.parseInt(texto.trim());
            return Math.max(0, Math.min(255, val));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void registrarCambio(String seccion, int r, int g, int b) {
        String log = String.format("[%s] -> Color RGB: (%d, %d, %d)%n", seccion, r, g, b);
        txtAreaInfo.appendText(log);
        cambiarColorPane(paneAreaInfo, r, g, b);
    }
}
