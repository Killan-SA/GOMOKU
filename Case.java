import java.util.*;
public class Case {
   private int x, y;
    private Jeton jeton;
    private EnumMap<Grille.Direction, Case> voisins;
    private int N;//nombre de jetons a choisir dans classe jeu

    public Case(int x, int y){
        this(x,y,4);
    }
    public Case(int x, int y, int N) {
        this.x = x;
        this.y = y;
        this.N = N;//rendre le nombre de jeton a aligner modulable
        this.voisins = new EnumMap<>(Grille.Direction.class);//changement par enummap;
    }
    public int getX() {
    return x;
    }

    public int getY() {
    return y;
    }


    public void setJeton(Jeton j) {
        this.jeton = j;
    }

    public Jeton getJeton() {
        return jeton;
    }

    public void setVoisin(Grille.Direction dir , Case v) {//getteur pour les voisins
        voisins.put(dir,v);//changement a la place add put un voisin
    }

    public Case getVoisin(Grille.Direction dir) {//setteur pour les voisins
        return voisins.get(dir);
    }

    public EnumMap<Grille.Direction , Case> getVoisins() {
        return voisins;
        }
    public boolean estAligneQuatre(){ //on regarde si c'est un alignement de quatres jetons
        if (jeton == null){
            return false;
            }
        for (Grille.Direction dir : Grille.Direction.values()){
            int total = 1;//premier jeton

            total += compterDansDirection(dir , jeton);
            if (total == N){
                return true;
                }
            
        }
        return false;    
        }
    private int compterDansDirection(Grille.Direction dir, Jeton j) {
    Case voisin = getVoisin(dir);
    if (voisin != null && voisin.getJeton() != null &&
        voisin.getJeton().getCouleur() == j.getCouleur()) {
        return 1 + voisin.compterDansDirection(dir, j);
    }
    return 0;
}

    public int getNombreJetonsAlignes() {//obtenir le nombre de jeton aligne
        return N;
    }
    
    public void setNombreJetonsAlignes(int N) {// Permet de modifier le nombre de jetons à aligner
        this.N = N;
    }

    
}



