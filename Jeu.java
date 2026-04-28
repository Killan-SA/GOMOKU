import java.util.Scanner;
import java.io.*;
import java.nio.file.Path;

public class Jeu {
    private Grille grille;
    private Joueur joueur1;
    private Joueur joueur2;
    private Joueur joueurActuel;
    private Case derniereCaseJouee;

    public Jeu(Joueur j1, Joueur j2, int taille , int N) {
        this.grille = new Grille(taille, N);
        this.joueur1 = j1;
        this.joueur2 = j2;
        this.joueurActuel = joueur1;
    }

    public void demarrer() {
        Scanner scanner = new Scanner(System.in);
        boolean victoire = false;
        boolean premierCoup = true;

        while (!grille.estPleine() && !victoire) {
            grille.afficher();
            System.out.println("Joueur " + joueurActuel.getNom() + ", votre tour (" + joueurActuel.getCouleur() + ")");
            System.out.println("Entrez les coordonnées de la case où vous souhaitez jouer (x,y) :");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            Case caseJouee = grille.getCase(x, y);

            if (caseJouee != null) {
                if (premierCoup || caseJouee.getJeton() == null) {
                    caseJouee.setJeton(new Jeton(joueurActuel.getCouleur()));
                    premierCoup = false;
                    derniereCaseJouee = caseJouee;

                    if (caseJouee.estAligneQuatre()) {
                        victoire = true;
                        grille.afficher();
                        System.out.println("Joueur " + joueurActuel.getNom() + " a gagné !");
                    } else {
                        changerTour();
                    }
                } else {
                    System.out.println("Cette case est déjà prise !");
                }
            }
        }
        scanner.close();
    }

    private void changerTour() {
        joueurActuel = (joueurActuel == joueur1) ? joueur2 : joueur1;
    }

    public void save(Path path) {
        try {
            FileWriter fw = new FileWriter(path.toFile());
            fw.write(grille.getTaille() + "\n");
            fw.write(grille.getN() + "\n");
            fw.write(joueur1.getNom() + " " + joueur1.getCouleur() + "\n");
            fw.write(joueur2.getNom() + " " + joueur2.getCouleur() + "\n");
            fw.write(joueurActuel.getNom() + "\n");

            for (int i = 0; i < grille.getTaille(); i++) {
                for (int j = 0; j < grille.getTaille(); j++) {
                    Case c = grille.getCase(i, j);
                    Jeton jeton = c.getJeton();
                    if (jeton != null) {
                        fw.write(i + " " + j + " " + jeton.getCouleur() + "\n");
                    }
                }
            }
            fw.close();
            System.out.println("Partie sauvegardée.");
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde.");
        }
    }

    public void load(Path path) {
        try {
            FileReader fr = new FileReader(path.toFile());
            Scanner scanner = new Scanner(fr);

            int taille = Integer.parseInt(scanner.nextLine());
            int N = Integer.parseInt(scanner.nextLine());  
            grille = new Grille(taille, N); //grille avec N
            String[] joueur1Info = scanner.nextLine().split(" ");
            String[] joueur2Info = scanner.nextLine().split(" ");
            String joueurActuelNom = scanner.nextLine();

            joueur1 = new Joueur(joueur1Info[0], joueur1Info[1].charAt(0));
            joueur2 = new Joueur(joueur2Info[0], joueur2Info[1].charAt(0));
            joueurActuel = joueur1.getNom().equals(joueurActuelNom) ? joueur1 : joueur2;

            while (scanner.hasNextLine()) {
                String[] infos = scanner.nextLine().split(" ");
                int x = Integer.parseInt(infos[0]);
                int y = Integer.parseInt(infos[1]);
                char couleur = infos[2].charAt(0);
                grille.getCase(x, y).setJeton(new Jeton(couleur));
            }

            scanner.close();
            System.out.println("Partie chargée.");
        } catch (Exception e) {
            System.out.println("Erreur lors du chargement.");
        }
    }
}
