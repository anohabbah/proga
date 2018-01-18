package anoh.kobenan.tp1;

/**
 * Created by 18018881 on 18/01/2018.
 */
public class MotsCroises {

    private Grille solution;
    private Grille proposition;
    private Grille horizontal;
    private Grille vertical;

    private int hauteur;
    private int largeur;

    public MotsCroises(int hauteur, int largeur) {
        this.hauteur = hauteur;
        this.largeur = largeur;

        this.proposition = new Grille(hauteur, largeur);
        this.horizontal = new Grille(hauteur, largeur);
        this.solution = new Grille(hauteur, largeur);
        this.vertical = new Grille(hauteur, largeur);
    }

    public int getLargeur() {
        return this.largeur;
    }

    public int getHauteur() {
        return this.hauteur;
    }

    public boolean coordCorrectes(int lig, int col) {
        return solution.coordCorrectes(lig, col);
    }

    public void setCaseNoire(int lig, int col, boolean noire) {
    }

    public boolean estCaseNoire(int lig, int col) {
        assert this.coordCorrectes(lig, col) : "Mauvaises coordonnées.";
        return false;
    }

    public void setSolution(int lig, int col, char sol) {
    }

    public void setDefinition(int lig, int col, boolean horiz, String def) {
    }

    public void setProposition(int lig, int col, char prop) {
    }

    public Grille getSolution(int lig, int col) {
        return null;
    }

    public Grille getProposition(int lig, int col) {
        return null;
    }

    public Grille getDefinition(int lig, int col, boolean b) {
        return null;
    }
}
