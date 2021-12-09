package fr.siomd.ludo;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import fr.siomd.ludo.dataaccess.DicoXml;
import fr.siomd.ludo.databinding.ActivityPenduBinding;
import fr.siomd.ludo.entity.Mot;
import fr.siomd.ludo.entity.Theme;
public class PenduActivity extends AppCompatActivity {

    private ActivityPenduBinding ui; // ui : interface utilisateur
    private ArrayList<Theme> lesThemes;

    private static final int REP_FAUSSE_MAX = 8;
    private int numCoup = 0;
    private Mot motCache;
    private char[] motTrouve;
    private ArrayList<Mot> lesMots = new ArrayList<>();
    private ArrayList<Mot> motSaisie = new ArrayList<>();
    private Theme vehicule = new Theme("Vehicule");

    // On crée les différents mots disonibles
    Mot Mot1 = new Mot("Voiture", 10);
    Mot Mot2 = new Mot("Camion", 24);
    Mot Mot3 = new Mot("Moto", 11);
    Mot Mot4 = new Mot("Velo", 19);

    public static final Random hasard = new Random();

    private String motCache() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < motTrouve.length; i++) {
            builder.append(motTrouve[i]);

            if (i < motTrouve.length - 1) {
                builder.append(" ");
            }
        }

        return builder.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // mettre en place le layout (la mise en page)
        ui = ActivityPenduBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());
        // récupérer le dictionnaire avec les thèmes
        lesThemes = DicoXml.getLesthemes(getResources().getXml(R.xml.dico));
        // afficher liste juste pour vérification
        for (Theme unTheme : lesThemes) {
            Log.i("DICO-liste", "Theme = " + unTheme.getNom());
            for (Mot unMot : unTheme.getLesMots()) {
                Log.i("DICO-liste", "Mot = " + unMot.getContenu() + " - " +
                        unMot.getNbPoints());
            }
        }

        // Ajout des mots dans le dictionnaire de mots
        lesMots.add(Mot1);
        lesMots.add(Mot2);
        lesMots.add(Mot3);
        lesMots.add(Mot4);
        demarrerJeu();
    }

    private void demarrerJeu() {
        numCoup = -1;
        motSaisie.clear();
        Random hasard = new Random();
        for (int i = 0; i < lesMots.size(); i++) {
            int motHasard = hasard.nextInt(lesMots.size() - 1);
            motCache = lesMots.get(motHasard);
        }

        // Afficher les composants de départ
        ui.btA.setEnabled(true);
        ui.progressBarTime.setProgress(25);
        ui.devineMot.setEnabled(true);
    }

    public void setOnClickbtJouer(View arg0) {
        demarrerJeu();
    }
}
