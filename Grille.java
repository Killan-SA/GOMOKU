import java.util.Scanner;


public class Grille {
    public enum Direction {
        HAUT(-1, 0),
        BAS(1, 0),
        GAUCHE(0, -1),
        DROITE(0, 1),
        DIAG_HAUT_GAUCHE(-1, -1),
        DIAG_HAUT_DROITE(-1, 1),
        DIAG_BAS_GAUCHE(1, -1),
        DIAG_BAS_DROITE(1, 1);

        public final int dx;
        public final int dy;

        Direction(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }
    }
    
    private int taille; // pour pas etre modif 
    private int N;
    private Case[][] cases; //same 
    public static final String reset = "\u001B[0m";  
    public static final String rouge = "\u001B[31m";
    public static final String bleu = "\u001B[34m";


    public Grille(int taille, int N){
        this.taille = taille;
        this.N = N;
        this.cases = new Case[taille][taille];
        //inialize injecte directement dans Grille
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                cases[i][j] = new Case(i,j,N);
            }
        }
    }

   
    public Case getCase(int x , int y){ //pour avoir une case il faut respecter ces conditions 
        if (x >=0 && x < taille && y >=0 && y < taille){
            return cases[x][y];
        }
        return null;//existe pas 

    }
     public int getTaille() {
        return taille;
    }
    public int getN(){
        return N;
    }
    public boolean estPleine(){
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                if(cases[i][j].getJeton() == null){ 
                return false;
                }
            }
        }
        return true;
    }

    public void afficher(){
        System.out.print("  ");
        for (int j = 0; j < taille; j++) {
            System.out.print(j + " "); //ligne de numero pour meilleure visuelle
        }
        System.out.println(); 

        //vrai grille
        for (int i = 0; i < taille; i++) {
            
            System.out.print(i + " "); //colonne de numero pour meilleure visuelle
            
            for (int j = 0; j < taille; j++) {
                
                Jeton jeton = cases[i][j].getJeton();//on recup le jeton dans une case
                
                if(jeton != null ) {
                    if(jeton.getCouleur()=='b'){
                        System.out.print(bleu + "b" + reset);// si oui on mets la couleur
                    }else if(jeton.getCouleur()=='w') {
                        System.out.print(rouge+"r"+reset);
                    }
                }else{
                    
                    System.out.print("."+" ");//sinon on met un point pour dire que y a rien + espace pour l'alignement
                }
            }

        System.out.println();//passez a la prochaine ligne
        }
        System.out.println("\n");//saute une ligne de la grille
        System.out.print("  ");
        for (int j = 0; j < taille; j++) {
            System.out.print(j + " ");//affiche des numeros en bas de la grille pour visuelle
        }
    }








    }
