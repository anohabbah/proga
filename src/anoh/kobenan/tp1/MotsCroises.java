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
        if (noire) {
            this.solution.setCellule(lig, col, "*");
        } else {
            this.solution.setCellule(lig, col, " ");
        }
    }

    public boolean estCaseNoire(int lig, int col) {
        assert this.coordCorrectes(lig, col) : "Mauvaises coordonnées.";
        return this.solution.getCellule(lig, col) == "*";
    }

    public char getSolution(int lig, int col) {
        assert this.coordCorrectes(lig, col) && !this.estCaseNoire(lig, col);

        return this.solution.getCellule(lig, col).charAt(0);
    }

    public void setSolution(int lig, int col, char sol) {
        assert this.coordCorrectes(lig, col) && !this.estCaseNoire(lig, col);

        this.solution.setCellule(lig, col, String.valueOf(sol));
    }

    public char getProposition(int lig, int col) {
        assert this.coordCorrectes(lig, col) && !this.estCaseNoire(lig, col);

        return this.proposition.getCellule(lig, col).charAt(0);
    }

    public void setProposition(int lig, int col, char prop) {
        assert this.coordCorrectes(lig, col) && !this.estCaseNoire(lig, col);

        this.proposition.setCellule(lig, col, String.valueOf(prop));
    }

    public String getDefinition(int lig, int col, boolean horiz) {
        assert this.coordCorrectes(lig, col) && !this.estCaseNoire(lig, col);

        return horiz ? this.horizontal.getCellule(lig, col) : this.vertical.getCellule(lig, col);
    }

    public void setDefinition(int lig, int col, boolean horiz, String def) {
        assert this.coordCorrectes(lig, col) && !this.estCaseNoire(lig, col);

        if (horiz)
            this.horizontal.setCellule(lig, col, String.valueOf(def));
        else
            this.vertical.setCellule(lig, col, String.valueOf(def));
    }
}
