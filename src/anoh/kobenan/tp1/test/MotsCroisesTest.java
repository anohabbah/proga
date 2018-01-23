package anoh.kobenan.tp1.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import anoh.kobenan.tp2.*;

public class MotsCroisesTest {
    private MotsCroises mc;
    private int hauteur, largeur;
    private boolean[][] noire;
    private Character[][] solution, proposition;
    private String[][] horizontal, vertical;

    @Before
    public void setUp() throws Exception {
        hauteur = 321;
        largeur = 654;
        mc = new MotsCroises(hauteur, largeur);
        System.out.println("Test de " + mc.getClass().getName());
        noire = new boolean[hauteur][largeur];
        solution = new Character[hauteur][largeur];
        proposition = new Character[hauteur][largeur];
        horizontal = new String[hauteur][largeur];
        vertical = new String[hauteur][largeur];

        // Mise en place des cases noires
        // Premiére étape : "mise é blanc" de toutes les cases
        for (int lig = 1; lig <= mc.getHauteur(); lig++) {
            for (int col = 1; col <= mc.getLargeur(); col++) {
                noire[lig - 1][col - 1] = false;
                mc.setCaseNoire(lig, col, false);
            }
        }

        // Deuxiéme étape : noircir toutes les cases d'une diagonale (i,i)
        // exceptée pour (2,2)
        for (int i = 1; i <= mc.getHauteur(); i++) {
            if (i != 2) {
                noire[i - 1][i - 1] = true;
                mc.setCaseNoire(i, i, true);
            }
        }

        // Mise en place des solutions, des propositions et des définitions
        char lettre = 'A';
        for (int lig = 1; lig <= mc.getHauteur(); lig++) {
            for (int col = 1; col <= mc.getLargeur(); col++) {
                if (!mc.estCaseNoire(lig, col)) {
                    solution[lig - 1][col - 1] = lettre;
                    mc.setSolution(lig, col, lettre);
                    String def = "(" + lig + "," + col + ") " + lettre;
                    if ((lig + col) % 2 == 0) {
                        horizontal[lig - 1][col - 1] = def;
                        mc.setDefinition(lig, col, true, def);
                    }
                    if ((lig + col) % 3 == 0) {
                        vertical[lig - 1][col - 1] = def;
                        mc.setDefinition(lig, col, false, def);
                    }
                    lettre++;
                    proposition[lig - 1][col - 1] = lettre;
                    mc.setProposition(lig, col, lettre);
                    lettre++;
                }
            }
        }

        // Création d'une 2éme instance pour tester d'éventuels effets de bord
        MotsCroises mc2 = new MotsCroises(2, 4);
        for (int lig = 1; lig <= mc2.getHauteur(); lig++) {
            for (int col = 1; col <= mc2.getLargeur(); col++) {
                mc2.setCaseNoire(lig, col, false);
            }
        }

        mc2.setCaseNoire(1, 1, true);
        mc2.setSolution(2, 3, 'Z');
        mc2.setProposition(2, 3, 'Y');
        mc2.setDefinition(2, 1, true, "WWWW");
        mc2.setDefinition(1, 3, false, "XXXX");
    }

    public void afficherReference(Object[][] tab, String libelle) {
        System.out.println("Référence " + libelle + " :");
        for (int lig = 1; lig <= hauteur; lig++) {
            for (int col = 1; col <= largeur; col++) {
                System.out.print("" + tab[lig - 1][col - 1] + "-");
            }
            System.out.println("");
        }
        System.out.println(" ");
    }

    @Test
    public void testCasesNoires() {
        for (int lig = 1; lig <= mc.getHauteur(); lig++)
            for (int col = 1; col <= mc.getLargeur(); col++) {
                if (noire[lig - 1][col - 1]) {
                    assertTrue("La case (" + lig + "," + col + ") doit étre noire",
                            mc.estCaseNoire(lig, col));
                } else {
                    assertFalse("La case (" + lig + "," + col + ") ne doit pas étre noire",
                            mc.estCaseNoire(lig, col));
                }
            }
    }

    @Test
    public void testSolutions() {
        testGrille(solution, "solution");
    }

    @Test
    public void testPropositions() {
        testGrille(proposition, "proposition");
    }

    @Test
    public void testHorizontal() {
        testGrille(horizontal, "définition horizontale");
    }

    @Test
    public void testVertical() {
        testGrille(vertical, "définition verticale");
    }

    private void testGrille(Object[][] grille, String libelle) {
        // afficherReference(grille, libelle) ;
        Object attendu, observe = null;
        for (int lig = 1; lig <= mc.getHauteur(); lig++)
            for (int col = 1; col <= mc.getLargeur(); col++) {
                if (!noire[lig - 1][col - 1]) {
                    attendu = grille[lig - 1][col - 1];
                    switch (libelle) {
                        case "solution":
                            observe = mc.getSolution(lig, col);
                            break;
                        case "proposition":
                            observe = mc.getProposition(lig, col);
                            break;
                        case "définition horizontale":
                            observe = mc.getDefinition(lig, col, true);
                            break;
                        case "définition verticale":
                            observe = mc.getDefinition(lig, col, false);
                            break;
                    }
                    assertEquals("La case (" + lig + "," + col + ") de " + libelle
                                    + " doit contenir \"" + attendu + "\".",
                            attendu, observe);
                }
            }
    }

}
