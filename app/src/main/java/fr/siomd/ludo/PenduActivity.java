package fr.siomd.ludo;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import fr.siomd.ludo.dataaccess.DicoXml;
import fr.siomd.ludo.databinding.ActivityPenduBinding;
import fr.siomd.ludo.entity.Bourreau;
import fr.siomd.ludo.entity.Juge;
import fr.siomd.ludo.entity.Mot;
import fr.siomd.ludo.entity.Theme;
public class PenduActivity extends AppCompatActivity {


    private ActivityPenduBinding ui; // ui : interface utilisateur
    private ArrayList<Theme> lesThemes;
    //créer un fichier xml avec les thèmes comportant les mots
    //Juge (gestionnaire de mots)
    private Juge leJuge;
    //new Bourreau (gestionnaire de lettres)
    private Bourreau leBourreau;


    private static final int REP_FAUSSE_MAX = 8;
    private int numCoup = 0;
    private Mot motCache;
    private char[] motTrouve;
    private ArrayList<Mot> lesMots = new ArrayList<>();
    private ArrayList<Mot> motSaisie = new ArrayList<>();
    private Theme vehicule = new Theme("Vehicule");

    // On crée les différents mots disonibles
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
        ui.progressBarTime.setProgress(25);
        ui.devineMot.setEnabled(true);

        //créer le juge et le bourreau
        leJuge = new Juge(getResources().getXml(R.xml.dico));
        leBourreau = new Bourreau(leJuge);

        // récupérer le dictionnaire avec les thèmes
        lesThemes = DicoXml.getLesthemes(getResources().getXml(R.xml.dico));
        //afficher le mot en cours (avec des _ pour chaque lettre non trouvée)
        ui.tvMot.setText(leBourreau.getLeMotEnCours());
        // afficher liste juste pour vérification
        for (Theme unTheme : lesThemes) {
            Log.i("DICO-liste", "Theme = " + unTheme.getNom());
            for (Mot unMot : unTheme.getLesMots()) {
                Log.i("DICO-liste", "Mot = " + unMot.getContenu() + " - " +
                        unMot.getNbPoints());
            }
        }

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

    public void onClickBtLettre(View laVue){
        CharSequence laLettre = ((Button)laVue).getText();
        // évidemment dans votre jeu de pendu, au lieu de faire un toast, il convient de traiter la lettre proposée
        leBourreau.executer(laLettre.charAt(0));
        // si gagné --> afficher  "GAGNE" et afficher le score (leJuge.getScore())
        if leBourreau.isGagne(){
            Toast.makeText(this,"GAGNE", Toast.LENGTH_LONG).show();
            ui.score.setText(String.format(Locale.getDefault(), "%d", leJuge.getScore()));
            ui.btA.setEnabled(false);
            ui.btB.setEnabled(false);
            ui.btC.setEnabled(false);
            ui.btD.setEnabled(false);
            ui.btE.setEnabled(false);
            ui.btF.setEnabled(false);
            ui.btG.setEnabled(false);
            ui.btH.setEnabled(false);
            ui.btI.setEnabled(false);
            ui.btJ.setEnabled(false);
            ui.btK.setEnabled(false);
            ui.btL.setEnabled(false);
            ui.btM.setEnabled(false);
            ui.btN.setEnabled(false);
            ui.btO.setEnabled(false);
            ui.btP.setEnabled(false);
            ui.btQ.setEnabled(false);
            ui.btR.setEnabled(false);
            ui.btS.setEnabled(false);
            ui.btT.setEnabled(false);
            ui.btU.setEnabled(false);
            ui.btV.setEnabled(false);
            ui.btW.setEnabled(false);
            ui.btX.setEnabled(false);
            ui.btY.setEnabled(false);
            ui.btZ.setEnabled(false);
            // si perdu --> afficher  "PERDU" et adapter image pendu
        }else if leBourreau.isPerdu(){
            Toast.makeText(this,"PERDU", Toast.LENGTH_LONG).show();
            ui.btA.setEnabled(false);
            ui.btB.setEnabled(false);
            ui.btC.setEnabled(false);
            ui.btD.setEnabled(false);
            ui.btE.setEnabled(false);
            ui.btF.setEnabled(false);
            ui.btG.setEnabled(false);
            ui.btH.setEnabled(false);
            ui.btI.setEnabled(false);
            ui.btJ.setEnabled(false);
            ui.btK.setEnabled(false);
            ui.btL.setEnabled(false);
            ui.btM.setEnabled(false);
            ui.btN.setEnabled(false);
            ui.btO.setEnabled(false);
            ui.btP.setEnabled(false);
            ui.btQ.setEnabled(false);
            ui.btR.setEnabled(false);
            ui.btS.setEnabled(false);
            ui.btT.setEnabled(false);
            ui.btU.setEnabled(false);
            ui.btV.setEnabled(false);
            ui.btW.setEnabled(false);
            ui.btX.setEnabled(false);
            ui.btY.setEnabled(false);
            ui.btZ.setEnabled(false);


        }
    }
    public void onClickbtNouveauMot(View laVue){

        // démarrer le bourreau
        leBourreau.demarrer();

        // afficher le mot en cours
        leBourreau.getLeMotEnCours();

        // afficher image pendu du début
        ui.bomb1.setVisibility(View.VISIBLE);


        // activer les boutons lettres s'ils ont été désactivés
        ui.btA.setEnabled(true);
        ui.btB.setEnabled(true);
        ui.btC.setEnabled(true);
        ui.btD.setEnabled(true);
        ui.btE.setEnabled(true);
        ui.btF.setEnabled(true);
        ui.btG.setEnabled(true);
        ui.btH.setEnabled(true);
        ui.btI.setEnabled(true);
        ui.btJ.setEnabled(true);
        ui.btK.setEnabled(true);
        ui.btL.setEnabled(true);
        ui.btM.setEnabled(true);
        ui.btN.setEnabled(true);
        ui.btO.setEnabled(true);
        ui.btP.setEnabled(true);
        ui.btQ.setEnabled(true);
        ui.btR.setEnabled(true);
        ui.btS.setEnabled(true);
        ui.btT.setEnabled(true);
        ui.btU.setEnabled(true);
        ui.btV.setEnabled(true);
        ui.btW.setEnabled(true);
        ui.btX.setEnabled(true);
        ui.btY.setEnabled(true);
        ui.btZ.setEnabled(true);



    }
    public void changeImage(){
        if leBourreau.lesLettresAuRebut.size() == 1{
            ui.bomb1.setVisibility(View.VISIBLE);
        }else if leBourreau.lesLettresAuRebut.size() == 2{
            ui.bomb1.setVisibility(View.INVISIBLE);
            ui.bomb2.setVisibility(View.VISIBLE);

        }else if leBourreau.lesLettresAuRebut.size() == 3{
        ui.bomb2.setVisibility(View.INVISIBLE);
        ui.bomb3.setVisibility(View.VISIBLE);
        }

        }else if leBourreau.lesLettresAuRebut.size() == 4{
        ui.bomb3.setVisibility(View.INVISIBLE);
        ui.bomb4.setVisibility(View.VISIBLE);

        }else if leBourreau.lesLettresAuRebut.size() == 5{
        ui.bomb4.setVisibility(View.INVISIBLE);
        ui.bomb5.setVisibility(View.VISIBLE);

        }else if leBourreau.lesLettresAuRebut.size() == 6{
        ui.bomb5.setVisibility(View.INVISIBLE);
        ui.bomb6.setVisibility(View.VISIBLE);

        }else if leBourreau.lesLettresAuRebut.size() == 7{
        ui.bomb6.setVisibility(View.INVISIBLE);
        ui.bomb7.setVisibility(View.VISIBLE);

        }else if leBourreau.lesLettresAuRebut.size() == 8{
        ui.bomb7.setVisibility(View.INVISIBLE);
        ui.bomb8.setVisibility(View.VISIBLE);

        }else if leBourreau.lesLettresAuRebut.size() == 9

    {
        ui.bomb8.setVisibility(View.INVISIBLE);
        ui.explo.setVisibility(View.VISIBLE);
    }

}
