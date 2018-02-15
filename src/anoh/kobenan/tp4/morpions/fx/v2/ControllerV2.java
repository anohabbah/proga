package anoh.kobenan.tp4.morpions.fx.v2;

import anoh.kobenan.tp4.morpions.fx.model.ModeleMorpionFX;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ControllerV2 {
    private ModeleMorpionFX morpion;

    @FXML
    private GridPane grille;

    @FXML
    private Label labelNbCoups;

    @FXML
    private Label labelEtatJeu;

    @FXML
    private Label labelJoueur;

    @FXML
    private void initialize() {
        this.morpion = new ModeleMorpionFX();
        this.recalculerLabelEtatJeu();

        for (Node n : grille.getChildren()) {
            if (n instanceof Label) {
                Label l = (Label) n;
                int ligne = (int) l.getProperties().get("gridpane-row") + 1;
                int colonne = (int) l.getProperties().get("gridpane-column") + 1;
                this.morpion.grilleCellProperty(ligne, colonne).addListener(((observable, oldValue, newValue) -> {
                    l.setText(this.morpion.symboleJoueur(newValue.intValue()));
                }));
            }
            n.setOnMouseClicked(this::clickBouton);
        }

        this.labelJoueur.textProperty().bind(this.morpion.symboleJoueurCourantProperty());

        this.morpion.nombreCoupsProperty()
                .addListener((obsValue, oldValue, newValue) -> this.updateNombreCoups(newValue.intValue()));
    }

    private void updateNombreCoups(int value) {
        String ch;
        if (value == 0) {
            ch = "Aucun coup joué";
        } else {
            ch = value == 1 ? value + " coup joué" : value + " coups joués";
        }
        this.labelNbCoups.setText(ch);
    }

    private void clickBouton(MouseEvent e) {
        Node n = (Node) e.getSource();
        Integer ligne = ((Integer) n.getProperties().get("gridpane-row")) + 1;
        Integer colonne = ((Integer) n.getProperties().get("gridpane-column")) + 1;
        this.morpion.jouerCoup(ligne, colonne);
        this.recalculerLabelEtatJeu();
        System.out.println("Coup joué : " + ligne + "/" + colonne);
        System.out.println("Résultat : " + morpion.getEtatJeu());
    }

    private void recalculerLabelEtatJeu() {
        switch (this.morpion.getEtatJeu()) {
            case J1_JOUE:
                this.labelEtatJeu.setText("C'est au tour du Joueur 1");
                break;

            case J2_JOUE:
                this.labelEtatJeu.setText("C'est au tour du Joueur 2");
                break;

            case J1_VAINQUEUR:
                this.labelEtatJeu.setText("Le gagnant est le Joueur 1");
                break;

            case J2_VAINQUEUR:
                this.labelEtatJeu.setText("Le gagnant est le Joueur 2");
                break;

            default:
                this.labelEtatJeu.setText("Match nul");
        }
    }
}
