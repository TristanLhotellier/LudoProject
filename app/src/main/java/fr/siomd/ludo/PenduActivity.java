package fr.siomd.ludo;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;
import fr.siomd.ludo.dataaccess.DicoXml;
import fr.siomd.ludo.databinding.ActivityPenduBinding;
import fr.siomd.ludo.entity.Mot;
import fr.siomd.ludo.entity.Theme;
public class PenduActivity extends AppCompatActivity {
    private ActivityPenduBinding ui; // ui : interface utilisateur
    private ArrayList<Theme> lesThemes;
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
    }
}
