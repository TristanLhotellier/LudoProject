package fr.siomd.ludo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

import fr.siomd.ludo.databinding.ActivityCalculerBinding;

public class CalculerActivity extends AppCompatActivity {
    //la présentation de ce qu'il faut compter ou calculer,
    // la saisie du nombre,
    // la vérification et l'affichage du résultat correct, le calcul du nombre de points total  et son affichage,
    // la possibilité de redémarrer une série de calculs, et
    // une option à choisir concernant l'affichage.

    private ActivityCalculerBinding ui; // ui : interface utilisateur
    /**
     * The Saisie resultat.
     */
    EditText saisieResultat;
    private int resultat = 0;
    private int points = 0;
    private int essais = 0;
    /**
     * The Nb 1.
     */
    int nb1 = 0;
    /**
     * The Nb 2.
     */
    int nb2 = 0;
    /**
     * The Nb 3.
     */
    int nb3 = 0;
    /**
     * The Nb 4.
     */
    int nb4 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculer);
        // mettre en place le layout (la mise en page)
        ui = ActivityCalculerBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());
        // désactiver la zone de saisie du résultat
        ui.Resultat.setEnabled(false);
        saisieResultat = (EditText)findViewById(R.id.Resultat);
        ui.btValider.setEnabled(false);
        //images d'additionator
        ui.imgLogoRouge.setVisibility(View.VISIBLE);
        ui.imgLogoVert.setVisibility(View.INVISIBLE);
        ui.imgLogoOrange.setVisibility(View.INVISIBLE);
        ui.imgLogoNoir.setVisibility(View.INVISIBLE);
        ui.imgLogoBleu.setVisibility(View.INVISIBLE);
        //images de multiplicator
        ui.imgMultRouge.setVisibility(View.INVISIBLE);
        ui.imgMultVert.setVisibility(View.INVISIBLE);
        ui.imgMultOrange.setVisibility(View.INVISIBLE);
        ui.imgMultNoir.setVisibility(View.INVISIBLE);
        ui.imgMultBleu.setVisibility(View.INVISIBLE);
        //boutons de modes
        ui.btModeAddition.setVisibility(View.INVISIBLE);
        ui.btModeMultiplication.setVisibility(View.VISIBLE);
        //les signes x
        ui.Fois1.setVisibility(View.INVISIBLE);
        ui.Fois2.setVisibility(View.INVISIBLE);
        ui.Fois3.setVisibility(View.INVISIBLE);
        // les signes +
        ui.Plus1.setVisibility(View.VISIBLE);
        ui.Plus2.setVisibility(View.VISIBLE);
        ui.Plus3.setVisibility(View.VISIBLE);
        Toast.makeText(this,"Commencez un nouveau jeu", Toast.LENGTH_LONG).show();

    }
    /**
     * On clickbt nouveau jeu.
     *
     * @param arg0 the arg 0
     */
    public void onClickbtNouveauJeu(View arg0) {
        demarrerJeu();
    }

    private void demarrerJeu() {
        Toast.makeText(this,"Vous commencez une nouvelle partie", Toast.LENGTH_LONG).show();
        ui.btValider.setEnabled(true);
        points = 0;
        essais = 0;
        //on initialise les nombres aléatoirement entre 0 et 10
        nb1 = 0 + (int)(Math.random() * ((5 - 0) + 1));
        nb2 = 0 + (int)(Math.random() * ((5 - 0) + 1));
        nb3 = 0 + (int)(Math.random() * ((5 - 0) + 1));
        nb4 = 0 + (int)(Math.random() * ((5 - 0) + 1));
        if (ui.btModeAddition.getVisibility() == View.INVISIBLE) {
            resultat = nb1 + nb2 + nb3 + nb4;
        }else{
            resultat = nb1 * nb2 * nb3 * nb4;

        }

        //Les nombres alétoires s'affichent dans les cases

        ui.Nb1.setText(String.format(Locale.getDefault(), "%d", nb1));
        ui.Nb2.setText(String.format(Locale.getDefault(), "%d", nb2));
        ui.Nb3.setText(String.format(Locale.getDefault(), "%d", nb3));
        ui.Nb4.setText(String.format(Locale.getDefault(), "%d", nb4));
        ui.NbPoints.setText(String.format(Locale.getDefault(), "%d", points));
        ui.NbEssais.setText(String.format(Locale.getDefault(), "%d", essais));

        // effacer la zone de saisie du nombre de points
        ui.Resultat.setText("");
        ui.Resultat.setEnabled(true);

    }

    /**
     * On clickbt valider.
     *
     * @param arg0 the arg 0
     */
    public void onClickbtValider(View arg0) {
        traitementReponse();
    }

    private void traitementReponse(){
        // quand la zone de saisie est vide
        if (saisieResultat.getText().toString().isEmpty()){
            Toast.makeText(this,"N'oubliez pas le résultat", Toast.LENGTH_LONG).show();
        }
        else if (Integer.parseInt(saisieResultat.getText().toString()) == (resultat)){
            Toast.makeText(this,"Bravo !", Toast.LENGTH_LONG).show();
            points += 1;
            essais += 1;
            ui.NbPoints.setText(String.format(Locale.getDefault(), "%d", points));
            nb1 = 0 + (int)(Math.random() * ((5 - 0) + 1));
            nb2 = 0 + (int)(Math.random() * ((5 - 0) + 1));
            nb3 = 0 + (int)(Math.random() * ((5 - 0) + 1));
            nb4 = 0 + (int)(Math.random() * ((5 - 0) + 1));
            if (ui.btModeAddition.getVisibility() == View.INVISIBLE) {
                resultat = nb1 + nb2 + nb3 + nb4;
            }else{
                resultat = nb1 * nb2 * nb3 * nb4;

            }
            ui.Nb1.setText(String.format(Locale.getDefault(), "%d", nb1));
            ui.Nb2.setText(String.format(Locale.getDefault(), "%d", nb2));
            ui.Nb3.setText(String.format(Locale.getDefault(), "%d", nb3));
            ui.Nb4.setText(String.format(Locale.getDefault(), "%d", nb4));
            ui.Resultat.setText("");
            ui.NbEssais.setText(String.format(Locale.getDefault(), "%d", essais));
        }else{
            //quand le résultat est différent du nombre saisi
            essais += 1;
            Toast.makeText(this,"Perdu ! Le bon résultat était " + resultat, Toast.LENGTH_LONG).show();
            nb1 = 0 + (int)(Math.random() * ((5 - 0) + 1));
            nb2 = 0 + (int)(Math.random() * ((5 - 0) + 1));
            nb3 = 0 + (int)(Math.random() * ((5 - 0) + 1));
            nb4 = 0 + (int)(Math.random() * ((5 - 0) + 1));
            if (ui.btModeAddition.getVisibility() == View.INVISIBLE) {
                resultat = nb1 + nb2 + nb3 + nb4;
            }else{
                resultat = nb1 * nb2 * nb3 * nb4;

            }
            ui.Nb1.setText(String.format(Locale.getDefault(), "%d", nb1));
            ui.Nb2.setText(String.format(Locale.getDefault(), "%d", nb2));
            ui.Nb3.setText(String.format(Locale.getDefault(), "%d", nb3));
            ui.Nb4.setText(String.format(Locale.getDefault(), "%d", nb4));
            ui.Resultat.setText("");
            ui.NbEssais.setText(String.format(Locale.getDefault(), "%d", essais));

        }

    }

    /**
     * On clickbt mode addition.
     *
     * @param arg0 the arg 0
     */
    public void onClickbtModeAddition(View arg0) {
        resultat = nb1 + nb2 + nb3 + nb4;
        points = 0;
        essais = 0;
        ui.NbPoints.setText(String.format(Locale.getDefault(), "%d", points));
        ui.NbEssais.setText(String.format(Locale.getDefault(), "%d", essais));
        ui.btModeAddition.setVisibility(View.INVISIBLE);
        ui.btModeMultiplication.setVisibility(View.VISIBLE);
        ui.imgLogoRouge.setVisibility(View.VISIBLE);
        ui.imgLogoVert.setVisibility(View.INVISIBLE);
        ui.imgLogoOrange.setVisibility(View.INVISIBLE);
        ui.imgLogoNoir.setVisibility(View.INVISIBLE);
        ui.imgLogoBleu.setVisibility(View.INVISIBLE);
        ui.imgMultRouge.setVisibility(View.INVISIBLE);
        ui.imgMultVert.setVisibility(View.INVISIBLE);
        ui.imgMultOrange.setVisibility(View.INVISIBLE);
        ui.imgMultNoir.setVisibility(View.INVISIBLE);
        ui.imgMultBleu.setVisibility(View.INVISIBLE);
        //les signes x
        ui.Fois1.setVisibility(View.INVISIBLE);
        ui.Fois2.setVisibility(View.INVISIBLE);
        ui.Fois3.setVisibility(View.INVISIBLE);
        // les signes +
        ui.Plus1.setVisibility(View.VISIBLE);
        ui.Plus2.setVisibility(View.VISIBLE);
        ui.Plus3.setVisibility(View.VISIBLE);
    }

    /**
     * On clickbt mode multiplication.
     *
     * @param arg0 the arg 0
     */
    public void onClickbtModeMultiplication(View arg0) {
        resultat = nb1 * nb2 * nb3 * nb4;
        points = 0;
        essais = 0;
        ui.NbPoints.setText(String.format(Locale.getDefault(), "%d", points));
        ui.NbEssais.setText(String.format(Locale.getDefault(), "%d", essais));
        ui.btModeAddition.setVisibility(View.VISIBLE);
        ui.btModeMultiplication.setVisibility(View.INVISIBLE);
        ui.imgMultRouge.setVisibility(View.VISIBLE);
        ui.imgMultVert.setVisibility(View.INVISIBLE);
        ui.imgMultOrange.setVisibility(View.INVISIBLE);
        ui.imgMultNoir.setVisibility(View.INVISIBLE);
        ui.imgMultBleu.setVisibility(View.INVISIBLE);
        ui.imgLogoRouge.setVisibility(View.INVISIBLE);
        ui.imgLogoVert.setVisibility(View.INVISIBLE);
        ui.imgLogoOrange.setVisibility(View.INVISIBLE);
        ui.imgLogoNoir.setVisibility(View.INVISIBLE);
        ui.imgLogoBleu.setVisibility(View.INVISIBLE);
        //les signes x
        ui.Fois1.setVisibility(View.VISIBLE);
        ui.Fois2.setVisibility(View.VISIBLE);
        ui.Fois3.setVisibility(View.VISIBLE);
        // les signes +
        ui.Plus1.setVisibility(View.INVISIBLE);
        ui.Plus2.setVisibility(View.INVISIBLE);
        ui.Plus3.setVisibility(View.INVISIBLE);
    }


    /**
     * On clickbt change logo.
     *
     * @param arg0 the arg 0
     */
    public void onClickbtChangeLogo(View arg0) {
        changeLogo();
    }

    private void changeLogo(){
        //changer le logo de l'additionator
        if (ui.btModeAddition.getVisibility() == View.INVISIBLE){
            if (ui.imgLogoRouge.getVisibility() == View.VISIBLE){
                ui.imgLogoVert.setVisibility(View.VISIBLE);
                ui.imgLogoRouge.setVisibility(View.INVISIBLE);
            }else if (ui.imgLogoVert.getVisibility() == View.VISIBLE) {
                ui.imgLogoOrange.setVisibility(View.VISIBLE);
                ui.imgLogoVert.setVisibility(View.INVISIBLE);
            }else if (ui.imgLogoOrange.getVisibility() == View.VISIBLE) {
                ui.imgLogoNoir.setVisibility(View.VISIBLE);
                ui.imgLogoOrange.setVisibility(View.INVISIBLE);
            }else if (ui.imgLogoNoir.getVisibility() == View.VISIBLE){
                ui.imgLogoBleu.setVisibility(View.VISIBLE);
                ui.imgLogoNoir.setVisibility(View.INVISIBLE);
            }else if (ui.imgLogoBleu.getVisibility() == View.VISIBLE){
                ui.imgLogoRouge.setVisibility(View.VISIBLE);
                ui.imgLogoBleu.setVisibility(View.INVISIBLE);
            }
        } else{
            //changer le logo du multiplicator
            if (ui.imgMultRouge.getVisibility() == View.VISIBLE){
                ui.imgMultVert.setVisibility(View.VISIBLE);
                ui.imgMultRouge.setVisibility(View.INVISIBLE);
            }else if (ui.imgMultVert.getVisibility() == View.VISIBLE) {
                ui.imgMultOrange.setVisibility(View.VISIBLE);
                ui.imgMultVert.setVisibility(View.INVISIBLE);
            }else if (ui.imgMultOrange.getVisibility() == View.VISIBLE) {
                ui.imgMultNoir.setVisibility(View.VISIBLE);
                ui.imgMultOrange.setVisibility(View.INVISIBLE);
            }else if (ui.imgMultNoir.getVisibility() == View.VISIBLE){
                ui.imgMultBleu.setVisibility(View.VISIBLE);
                ui.imgMultNoir.setVisibility(View.INVISIBLE);
            }else if (ui.imgMultBleu.getVisibility() == View.VISIBLE){
                ui.imgMultRouge.setVisibility(View.VISIBLE);
                ui.imgMultBleu.setVisibility(View.INVISIBLE);
            }
        }

    }
}