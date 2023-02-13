package com.example.demo3;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;

import java.security.*;

import java.io.FileWriter;
import java.util.Scanner;

public class HelloController {
    static String cadenaAux1;
    static String cadenaAux2;
    static String cadenaAux3;
    @FXML
    private Label welcomeText;
    @FXML
    protected TextField lloc;

    @FXML
    protected TextField usuari;

    @FXML
    private PasswordField contrasenya;
    @FXML
    private TextFlow alertaError;


    @FXML
    protected void botoRegistre() throws IOException, NoSuchAlgorithmException {
        cadenaAux3 = lloc.getText();
        cadenaAux1 = usuari.getText();
        cadenaAux2 = contrasenya.getText();
        fitxerSecret();
    }

    @FXML
    protected void botoAcces() throws IOException, NoSuchAlgorithmException {
        cadenaAux3 = lloc.getText();
        cadenaAux1 = usuari.getText();
        cadenaAux2 = contrasenya.getText();
        buscarFitxer();

    }

    public static void fitxerSecret() throws IOException {

        FileWriter fileWriterAux = new FileWriter("comptes.txt", true);
        BufferedWriter bufferedWriterAux = new BufferedWriter(fileWriterAux);
        bufferedWriterAux.append(cadenaAux3 + " " + cadenaAux1 + " " + cadenaAux2);
        bufferedWriterAux.append("\n");
        bufferedWriterAux.close();

    }

    @FXML
    public void buscarFitxer() throws IOException, NoSuchAlgorithmException {
        File ficheroDatos = new File("comptes.txt");
        Scanner lectorDatos = new Scanner(ficheroDatos);
        String datos = "";
        String frase = "";
        while (lectorDatos.hasNextLine()) {

            datos = lectorDatos.nextLine();
            String[] parts = datos.split(" ");

            String partLloc = parts[0];
            String partUser = parts[1];
            String partPass = parts[2];

            System.out.println(partLloc);
            if (partLloc.equals(cadenaAux3)) {
                System.out.println("Usuari: " + partUser + "\nContrasenya: " + partPass);
                frase = "Usuari: " + partUser + "\nContrasenya: " + partPass;
                System.out.println(frase);
                welcomeText.setText(frase);
                break;

            }
            else {
                welcomeText.setText("No hi ha cap compte a aquest lloc.");
            }
        }
        lectorDatos.close();
    }

}