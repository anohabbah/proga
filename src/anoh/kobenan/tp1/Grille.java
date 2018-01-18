package anoh.kobenan.tp1;

/**
 * Created by 18018881 on 18/01/2018.
 */
public class Grille {
    /**
     * int hauteur
     * Nombre de lignes
     */
    private int hauteur;

    /**
     * int largeur
     * Nombre de colonnes
     */
    private int largeur;

    /**
     * String[][] tab
     * tableau de chaînes de caractères à 2 dimensionss avec taille = hauteur x largeur
     */
    private String[][] tab;


    /**
     * Constructeur permettant d’obtenir une grille
     * dotée d’un tableau de dimensions conformes aux valeurs
     * respectives de hauteur et de largeur, dont tous les
     * éléments contiennent la valeur null.
     * Précondition (assert) : hauteur ≥ 0 et largeur ≥ 0
     *
     * @param hauteur hauteur de la grille
     * @param largeur largeur de la grille
     */
    public Grille(int hauteur, int largeur) {
        assert hauteur >= 0 && largeur >= 0 : "Les dimensions de la grille ne peuvent etre negatives.";

        this.hauteur = hauteur;
        this.largeur = largeur;
        this.tab = new String[hauteur][largeur];
    }

    /**
     * Validité des coordonnées.
     * Resultat : vrai si et seulement si (lig, col) désignent une cellule existante de la grille
     *
     * @param lig ligne à vérifier
     * @param col colonne à vérifier
     * @return boolean
     */
    public boolean coordCorrectes(int lig, int col) {
        return lig > 0 && lig <= this.getHauteur() && col > 0 && col <= this.getLargeur();
    }

    /**
     * Valeur de la cellule ayant pour coordonnées (lig, col), où lig (resp. col) est compris entre 1 et getHauteur()
     * (resp. getlargeur())
     * Précondition (assert) : coordCorrectes(lig, col)
     *
     * @param lig ligne
     * @param col colonne
     * @return la valeur de la cellule
     */
    public String getCellule(int lig, int col) {
        assert this.coordCorrectes(lig, col) : "La ligne doit etre comprise entre 1 et "
                + this.getHauteur() + ". et la colonne entre 1 et " + this.getLargeur();

        return this.tab[lig - 1][col - 1];
    }

    public void setCellule(int lig, int col, String ch) {
        assert this.coordCorrectes(lig, col) : "La ligne doit etre comprise entre 1 et "
                + this.getHauteur() + ". et la colonne entre 1 et " + this.getLargeur();

        this.tab[lig - 1][col - 1] = ch;
    }

    public int getHauteur() {
        return hauteur;
    }

    public int getLargeur() {
        return largeur;
    }

    @Override
    public String toString() {
        StringBuilder rs = new StringBuilder();

        for (int i = 0; i < this.getHauteur(); ++i) {
            for (int k = 0; k < this.getLargeur(); ++k) {
                rs.append(this.tab[i][k]);
            }
            rs.append("|");
        }

        return rs.toString();
    }
}
