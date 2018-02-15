package anoh.kobenan.tp4.morpions.fx.v1;

import anoh.kobenan.tp4.morpions.fx.model.ModeleMorpionFX;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import morpions.kit.test.SpecifModeleMorpions;

public class ControllerV1 {
    private SpecifModeleMorpions morpion;

    @FXML
    GridPane grille;

    @FXML
    private void initialize() {
        morpion = new ModeleMorpionFX();

        for (Node n: grille.getChildren()) {
            n.setOnMouseClicked(this::clickBouton);
        }
    }

    private void clickBouton(MouseEvent e) {
        Node n = (Node) e.getSource();
        Integer ligne = ((Integer) n.getProperties().get("gridpane-row")) + 1;
        Integer colonne = ((Integer) n.getProperties().get("gridpane-column")) + 1;
        morpion.jouerCoup(ligne, colonne);
        System.out.println("Coup joué : " + ligne + "/" + colonne);
        System.out.println("Résultat : " + morpion.getEtatJeu());
    }
}
