package fr.siomd.ludo.entity;

/**
 * The type Carte.
 */
public class Carte {
    private String couleur;
    private String figure;

    /**
     * Instantiates a new Carte.
     *
     * @param pCouleur the p couleur
     * @param pFigure  the p figure
     */
    public Carte(String pCouleur, String pFigure) {
        couleur = pCouleur;
        figure = pFigure;
    }

    /**
     * Gets couleur.
     *
     * @return la couleur de la carte
     */
    public String getCouleur()  {

        return couleur;
    }

    /**
     * Gets figure.
     *
     * @return la figure de la carte
     */
    public String getFigure()  {

        return figure;
    }

    /**
     * Gets valeur.
     *
     * @return la valeur de la carte
     */
    public int getValeur()  {
        int laValeur;
        switch (figure) {
            case "As": laValeur = 14; break;
            case "Roi": laValeur = 13; break;
            case "Dame": laValeur = 12; break;
            case "Valet": laValeur = 11; break;
            default: laValeur = Integer.parseInt(figure); break;
        }
        return laValeur;
    }

    /**
     * Gets nom.
     *
     * @return le nom de la carte
     */
    public String getNom() {

        return String.format("%s de %s", getFigure(), getCouleur());
    }

    /**
     * Is atout boolean.
     *
     * @param pCouleur the p couleur
     * @return true si couleur = pCouleur
     */
    public boolean isAtout(String pCouleur)  {

        return (couleur.equals(pCouleur));
    }

    /**
     * Get nom img string.
     *
     * @return le nom du fichier image représentant la carte (sans l'extension)
     */
    public String getNomImg(){
         String nomCarte;
        switch (getCouleur()){
            case "Carreau": nomCarte = "ca" + getValeur(); break;
            case "Coeur" : nomCarte = "co" + getValeur(); break;
            case "Pique" : nomCarte = "p" + getValeur(); break;
            case "Trèfle" : nomCarte = "t" + getValeur(); break;
            default:
                throw new IllegalStateException("Unexpected value: " + getCouleur());
        }
        return nomCarte;

        }
    }