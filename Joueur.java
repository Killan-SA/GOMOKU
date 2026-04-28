class Joueur{       //classe joueur
    String nom;
    char couleur;


    public Joueur(String nom, char couleur){         //Constructeur pour joueur  
        this.nom=nom;
        this.couleur=couleur;
    }

    public String getNom(){         //methode getNom() qui nous donne le nom du joueur
        return nom;
    }

    public char getCouleur(){       //methode getcouleur() qui nous donne la couleur du joueur
        return couleur;
    }

}  

