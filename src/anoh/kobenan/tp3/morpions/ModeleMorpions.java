package anoh.kobenan.tp3.morpions;

import morpions.kit.test.*;

public class ModeleMorpions implements SpecifModeleMorpions {
    private static final int TAILLE = 3;
    private int[][] grille;
    private Etat etatJeu;
    private int nombreCoupsJoues;

    public ModeleMorpions() {
        this.etatJeu = Etat.J1_JOUE;
        this.nombreCoupsJoues = 0;

        this.grille = new int[TAILLE][TAILLE];
        for (int l = 1; l <= TAILLE; ++l) {
            for (int c = 1; c <= TAILLE; ++c) {
                this.setCellValue(l, c, 0);
            }
        }
    }

    private void setCellValue(int ligne, int colonne, int value) {
        this.grille[ligne - 1][colonne - 1] = value;
    }

    private int getCellValue(int ligne, int colonne) {
        return this.grille[ligne - 1][colonne - 1];
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
        return this.nombreCoupsJoues;
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
        assert !this.estFinie();
        assert this.estCoupAutorise(ligne, colonne);

        this.setCellValue(ligne, colonne, this.getJoueur());
        ++nombreCoupsJoues;
        this.recalculerEtatJeu();
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
        } else if (this.nombreCoupsJoues == 9) {
            this.etatJeu = Etat.MATCH_NUL;
        } else if (this.etatJeu == Etat.J1_JOUE) {
            this.etatJeu = Etat.J2_JOUE;
        } else {
            this.etatJeu = Etat.J1_JOUE;
        }
    }
}
