package anoh.kobenan.tp4.morpions.fx.model;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import morpions.kit.test.SpecifModeleMorpions;

public class ModeleMorpionFX implements SpecifModeleMorpions {
    private ReadOnlyIntegerWrapper[][] grille;
    private Etat etatJeu;
//    private int nombreCoups;

    private ReadOnlyIntegerWrapper nombreCoups;
    private StringProperty symboleJoueurCourant;

    public ModeleMorpionFX() {
        this.etatJeu = Etat.J1_JOUE;
        this.nombreCoups = new ReadOnlyIntegerWrapper(0);
        this.symboleJoueurCourant = new SimpleStringProperty();
        this.setSymboleJoueurCourant(this.symboleJoueur(this.getJoueur()));

        this.grille = new ReadOnlyIntegerWrapper[TAILLE][TAILLE];
        for (int l = 1; l <= TAILLE; ++l) {
            for (int c = 1; c <= TAILLE; ++c) {
                this.grille[l-1][c-1] = new ReadOnlyIntegerWrapper(0);
            }
        }
    }

    public ReadOnlyIntegerWrapper grilleCellProperty(int ligne, int colonne) {
        return this.grille[ligne - 1][colonne - 1];
    }

    private void setCellValue(int ligne, int colonne, int value) {
        this.grille[ligne - 1][colonne - 1].set(value);
    }

    private int getCellValue(int ligne, int colonne) {
        return this.grille[ligne - 1][colonne - 1].getValue();
    }

    @Override
    public Etat getEtatJeu() {
        return this.etatJeu;
    }

    @Override
    public int getJoueur() {
        return this.etatJeu == Etat.J1_JOUE ? 1 : (this.etatJeu == Etat.J2_JOUE ? 2 : 0);
    }

    @Override
    public int getVainqueur() {
        return this.etatJeu == Etat.J1_VAINQUEUR ? 1 : (this.etatJeu == Etat.J2_VAINQUEUR ? 2 : 0);
    }

    @Override
    public int getNombreCoups() {
        return this.nombreCoups.intValue();
    }

    @Override
    public boolean estFinie() {
        return this.etatJeu != Etat.J1_JOUE && this.etatJeu != Etat.J2_JOUE;
    }

    @Override
    public boolean estCoupAutorise(int ligne, int colonne) {
        return this.validCell(ligne, colonne) && this.getCellValue(ligne, colonne) == 0;
    }

    private boolean validCell(int ligne, int colonne) {
        return ligne > 0 && ligne <= TAILLE && colonne > 0 && colonne <= TAILLE;
    }

    @Override
    public void jouerCoup(int ligne, int colonne) {
        assert !this.estFinie() : "Partie terminée";
        assert this.estCoupAutorise(ligne, colonne) : "Coup déjà joué";

        this.setCellValue(ligne, colonne, this.getJoueur());
        this.nombreCoups.set(this.getNombreCoups() + 1);
        this.recalculerEtatJeu();
        this.setSymboleJoueurCourant(this.symboleJoueur(this.getJoueur()));
    }

    private int chercherVainqueur() {
        int rs, l, c;

        for (l = 1; l <= TAILLE; ++l) {
            rs = 1;

            for (c = 1; c <= TAILLE; ++c) {
                rs *= this.getCellValue(l, c);
            }

            if (rs == 8) {
                return 2;
            }

            if (rs == 1) {
                return 1;
            }
        }

        for (l = 1; l <= TAILLE; ++l) {
            rs = 1;

            for (c = 1; c <= TAILLE; ++c) {
                rs *= this.getCellValue(c, l);
            }

            if (rs == 8) {
                return 2;
            }

            if (rs == 1) {
                return 1;
            }
        }

        rs = 1;
        int antiD = 1;
        for (l = 1; l <= TAILLE; ++l) {
            rs *= this.getCellValue(l, l);
            antiD *= this.getCellValue(l, 4 - l);
        }

        if (rs == 8 || antiD == 8) {
            return 2;
        }

        if (rs == 1 || antiD == 1) {
            return 1;
        }

        return 0;
    }

    private void recalculerEtatJeu() {
        int vainqueur = this.chercherVainqueur();
        if (vainqueur == 1) {
            this.etatJeu = Etat.J1_VAINQUEUR;
        } else if (vainqueur == 2) {
            this.etatJeu = Etat.J2_VAINQUEUR;
        } else if (this.nombreCoups.intValue() == 9) {
            this.etatJeu = Etat.MATCH_NUL;
        } else if (this.etatJeu == Etat.J1_JOUE) {
            this.etatJeu = Etat.J2_JOUE;
        } else {
            this.etatJeu = Etat.J1_JOUE;
        }
    }

    public StringProperty symboleJoueurCourantProperty() {
        return this.symboleJoueurCourant;
    }

    public String getSymboleJoueurCourant() {
        return symboleJoueurCourant.getValue();
    }

    public void setSymboleJoueurCourant(String symboleJoueurCourant) {
        this.symboleJoueurCourant.set(symboleJoueurCourant);
    }

    public ReadOnlyIntegerProperty nombreCoupsProperty() {
        return this.nombreCoups.getReadOnlyProperty();
    }

    public String symboleJoueur(int val) {
        switch (val) {
            case 1:
                return "X";
            case 2:
                return "O";
            default:
                return " ";
        }
    }
}
