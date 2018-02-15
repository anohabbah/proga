package anoh.kobenan.tp3.morpions.test;

import anoh.kobenan.tp3.morpions.ModeleMorpions;
import anoh.kobenan.tp4.morpions.fx.model.ModeleMorpionFX;
import morpions.kit.test.MorpionsReference;
import morpions.kit.test.SpecifModeleMorpions;
import org.junit.*;

import static org.junit.Assert.*;

public class MorpionsTest {
    SpecifModeleMorpions morpions;
    public static final int TAILLE = 3;
    public static final int NB_CASES = 9;


    @Before
    public void setUp() throws Exception {
//         morpions = new MorpionsReference();
        // morpions = new Bogue1() ;
        // morpions = new Bogue2() ;
        // morpions = new Bogue3() ;
        // morpions = new Bogue4() ;
        // morpions = new Bogue5();
//        morpions = new ModeleMorpions();
        morpions = new ModeleMorpionFX();
    }

    @Test
    public void testInit() {
        assertTrue("Avant le 1er coup, la partie n'est pas terminée", !morpions.estFinie());
        assertTrue("Toutes les cases sont accessibles avant le 1er coup",
                morpions.estCoupAutorise(1, 1));
        // Test de l'invariant de la classe
        testInvariant();
    }

    @Test
    public void testPremierCoup() {
        morpions.jouerCoup(1, 1);
        assertTrue("Partie pas finie après premier coup", !morpions.estFinie());
        assertFalse("Coup déjà joué", morpions.estCoupAutorise(1, 1));
        assertEquals("Joueur 1 a déjà joué", SpecifModeleMorpions.Etat.J2_JOUE, morpions.getEtatJeu());

        testInvariant();
    }

    private void testInvariant() {
        // Le nombre de coups est positif ou nul, et inf�rieur ou �gal au nombre de cases
        assertTrue("Nombre de coups >= 0", morpions.getNombreCoups() >= 0);
        assertTrue("Nombre de coups <= " + NB_CASES,
                morpions.getNombreCoups() <= NB_CASES);
        // ----------------------
        // S�QUENCE 3 � COMPL�TER
        // ----------------------
    }

    @Test
    public void testCoupDejaJoue() {
        morpions.jouerCoup(1, 1);
        assertTrue("La case (1,1) ne peut �tre jou�e", !morpions.estCoupAutorise(1, 1));
    }

    @Test
    public void testPartiePasFinie() {
        morpions.jouerCoup(1, 1);
        morpions.jouerCoup(2, 2);
        assertTrue("Partie non terminée", !morpions.estFinie());
    }

    @Test
    public void testJoueur1gagnant() {
        morpions.jouerCoup(1, 1);
        morpions.jouerCoup(1, 2);
        morpions.jouerCoup(3, 3);
        morpions.jouerCoup(3, 1);
        morpions.jouerCoup(2, 2);
        assertTrue("La partie est finie lorsqu'un joueur aligne 3 cases", morpions.estFinie());
        assertEquals(SpecifModeleMorpions.Etat.J1_VAINQUEUR, morpions.getEtatJeu());
    }

    @Test
    public void testControle() {
        assertFalse("Coups non autorisé", morpions.estCoupAutorise(-9, 5));

        morpions.jouerCoup(2, 2);
        assertFalse("Coup déjà joué", morpions.estCoupAutorise(2, 2));
    }

    @Test
    public void testFinPartieHorizontal() {
        morpions.jouerCoup(1, 1);
        morpions.jouerCoup(2, 1);
        morpions.jouerCoup(1, 2);
        morpions.jouerCoup(2, 2);
        morpions.jouerCoup(1, 3);
        assertTrue("Partie finie", morpions.estFinie());
        assertEquals("Joueur 1 est vainqueur", 1, morpions.getVainqueur());
    }

    @Test
    public void testFinPartieVertical() {
        morpions.jouerCoup(1, 1);
        morpions.jouerCoup(1, 2);
        morpions.jouerCoup(2, 1);
        morpions.jouerCoup(2, 2);
        morpions.jouerCoup(3, 1);
        assertTrue("Partie finie", morpions.estFinie());
        assertEquals("Joueur 1 est vainqueur", 1, morpions.getVainqueur());
    }

    @Test
    public void testFinPartieDiagonal() {
        morpions.jouerCoup(2, 1);
        morpions.jouerCoup(1, 1);
        morpions.jouerCoup(1, 2);
        morpions.jouerCoup(2, 2);
        morpions.jouerCoup(3, 1);
        morpions.jouerCoup(3, 3);
        assertTrue("Partie finie", morpions.estFinie());
        assertEquals("Joueur 2 est vainqueur", 2, morpions.getVainqueur());
    }

    @Test
    public void testFinPartie() {
        morpions.jouerCoup(1, 3);
        morpions.jouerCoup(1, 2);
        morpions.jouerCoup(2, 1);
        morpions.jouerCoup(1, 1);
        morpions.jouerCoup(2, 2);
        morpions.jouerCoup(3, 1);
        morpions.jouerCoup(3, 2);
        morpions.jouerCoup(2, 3);
        morpions.jouerCoup(3, 3);
        assertTrue("Partie finie", morpions.estFinie());
        assertEquals("Pas de gagnant", 0, morpions.getVainqueur());
        assertEquals(9, morpions.getNombreCoups());
    }

    @Test
    public void testGetEtatJeu() {
        assertSame(
                "On s'attend à un objet de type SpecifModeleMorpions.ETat",
                SpecifModeleMorpions.Etat.class,
                morpions.getEtatJeu().getClass()
        );
        assertEquals(
                "C'est au joueur 1 de jouer le prochain coup",
                SpecifModeleMorpions.Etat.J1_JOUE,
                morpions.getEtatJeu()
        );
    }

    @Test
    public void testGetJoueur() {
        assertEquals("Avant le 1er coup", 1, morpions.getJoueur());
        morpions.jouerCoup(1, 1);
        assertEquals("Après le 1er coup", 2, morpions.getJoueur());
        morpions.jouerCoup(1, 3);
        assertEquals("Après le 2e coup", 1, morpions.getJoueur());
    }

    @Test
    public void testGetVainqueur() {
        assertTrue("Avant le 1er coup, pas de vainqueur", morpions.getVainqueur() == 0);
        morpions.jouerCoup(1, 3);
        assertTrue("Après le 1er coup, pas de vainqueur", morpions.getVainqueur() == 0);
        morpions.jouerCoup(1, 1);
        morpions.jouerCoup(2, 2);
        morpions.jouerCoup(2, 1);
        morpions.jouerCoup(3, 1);
        assertTrue("Joueur 1 vainqueur", morpions.getVainqueur() == 1);
    }

    @Test
    public void testGetNombreCoups() {
        assertEquals("Avant le 1er coup", 0, morpions.getNombreCoups());
        morpions.jouerCoup(1, 1);
        assertEquals("Après le 1er coup", 1, morpions.getNombreCoups());
        morpions.jouerCoup(1, 2);
        assertEquals("Après le 2e coup", 2, morpions.getNombreCoups());
    }
}
