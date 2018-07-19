package kacou.calulator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private Button btn_division;

    @FXML
    private TextField tf_ecran;
    @FXML
    private Button btn_signe;

    @FXML
    private Button btn_egale;

    @FXML
    private Button btn_reset;

    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_0;


    /**
     * Variable
     */

    private int resultat = 0;
    private String operateur = "", gauche = "", droite = "";
    private boolean operable = false, egale = false;


    @FXML
    void clickClavier(ActionEvent event) {
        Boolean zero = false;
        Button btn_click = (Button) event.getSource();
        if (egale)
            reset();
        if (operateur != "") {
            droite = droite + btn_click.getText();
            tf_ecran.setText(gauche + operateur + droite);
            btn_egale.setDisable(false);
            zero = true;
        } else {
            gauche = gauche + btn_click.getText();
            tf_ecran.setText(gauche);
            btn_delete.setDisable(false);
            zero = true;
        }
        if (zero)
            btn_0.setDisable(false);
        else
            btn_0.setDisable(true);
    }

    @FXML
    void clickSpecial(ActionEvent event) {
        Button btn_click = (Button) event.getSource();
        if (btn_click == btn_delete)
            delete();
        if (btn_click == btn_reset) {
            reset();
            btn_0.setDisable(true);
            btn_egale.setDisable(true);
        }

        //calcul du resultat
        if (btn_click == btn_egale) {
            calcul(Integer.parseInt(gauche), Integer.parseInt(droite), operateur);
            gauche = String.valueOf(resultat);
            droite = "";
            tf_ecran.setText(String.valueOf(gauche));
            operateur = "";
            btn_egale.setDisable(true);
            egale = true;
        }
        // Changement de signe
        if (btn_click == btn_signe) {
            if (operable && !egale) {
                droite = String.valueOf(Integer.parseInt(droite) * -1);
                tf_ecran.setText(gauche + operateur + droite);
            } else {
                gauche = String.valueOf(Integer.parseInt(gauche) * -1);
                tf_ecran.setText(String.valueOf(gauche));
            }
        }

    }


    @FXML
    void clickOperation(ActionEvent event) {
        egale = false;
        Button btn_click = (Button) event.getSource();
        if (gauche != "") { // controle pour verifier qu'il n'y a rien dans la veleur gauche sionon un operateur pour etre ecrit
            String operation = ((Button) event.getSource()).getText(); // recuperation du type d'operation
            //verification de la division par zero peu mieux faire a ameliorer
            if (btn_click == btn_division) {
                if (droite == "" || droite.isEmpty()) {
                    tf_ecran.setText(gauche + "/");
                    operable = true;
                    operateur = "/";
                    btn_0.setDisable(true);

                } else {
                    calcul(Integer.parseInt(gauche), Integer.parseInt(droite), operateur);
                    operateur = "/";
                    btn_0.setDisable(true);
                    gauche = String.valueOf(resultat);
                    droite = "";
                    tf_ecran.setText(gauche + "/");
                    btn_egale.setDisable(true);
                }
            }
            /**
             * verification de la droite avant d'effectuer une operation
             * */
            if (droite == "" || droite.isEmpty()) {
                tf_ecran.setText(gauche + operation);
                operable = true;
                operateur = operation;
            } else {
                calcul(Integer.parseInt(gauche), Integer.parseInt(droite), operateur);
                operateur = operation;
                gauche = String.valueOf(resultat);
                droite = "";
                tf_ecran.setText(gauche + operation);
                btn_egale.setDisable(true);
            }
        }
    }

    private void reset() {
        tf_ecran.clear();
        gauche = "";
        droite = "";
        operable = false;
        operateur = "";
        egale = false;
    }

    private void delete() {
        int taille = droite.length();
        int tailleG = gauche.length();
        if (operateur == "" || !operable) {
            if (tailleG == 1 || gauche == "")
                reset();
            else {
                if (taille == 2 && Integer.parseInt(gauche) < 0) {
                    gauche = "";
                    tf_ecran.setText(gauche + operateur);
                } else {
                    gauche = gauche.substring(0, tailleG - 1);
                    tf_ecran.setText(gauche);
                    operable = true;
                }
            }
        }
        if (operable) {
            if (taille > 0) {
                if (taille == 2 && Integer.parseInt(droite) < 0) {
                    droite = "";
                    tf_ecran.setText(gauche + operateur);
                } else {
                    droite = droite.substring(0, taille - 1);
                    tf_ecran.setText(gauche + operateur + droite);
                }
            } else {
                operable = false;
                tf_ecran.setText(gauche);
            }
        }
    }

    //kacou Innocent
    private void calcul(int nombreGauche, int nombreDroit, String operateur) {
        switch (operateur) {
            case "/":
                resultat = (nombreGauche) / (nombreDroit);

                break;
            case "x":
                resultat = nombreGauche * nombreDroit;
                break;
            case "+":
                resultat = nombreGauche + nombreDroit;
                break;
            case "-":
                resultat = nombreGauche - nombreDroit;
                break;
            case "%":
                resultat = nombreGauche % nombreDroit;
                if (resultat == 0)
                    gauche = "";
        }
    }


}
/**
 * @kacou innocent
 * Miage cocody
 * Master 1
 */