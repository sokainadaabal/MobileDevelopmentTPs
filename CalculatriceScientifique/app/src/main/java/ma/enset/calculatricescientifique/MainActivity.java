package ma.enset.calculatricescientifique;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.mariuszgromada.math.mxparser.Expression;


public class MainActivity extends AppCompatActivity {
    /**
     * enum d'opérations que l'on trouve dans une simple calculatrice.
     * **/
    public enum Ops {
        ADD("+"),
        SOUSTRACTIONS("-"),
        MULTIPLE("*"),
        DIVISER("/");
        private String nom = "";

        Ops(String nom) {
            this.nom = nom;
        }

        public String toString() {
            return name();
        }
    }

    private TextView Affichage;
    private int valeur1 = 0, valeur2 = 0;
    private Ops operateur = null;
    private boolean isOp1 = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Affichage = (TextView) findViewById(R.id.Affichage);

        /**
         * Bouton AC permettant d'effacer le contenu de l'écran.
         */
        Button btnClear = (Button) findViewById(R.id.btn_AC);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
            }
        });
    }

    /**
     * Equals permettent d'appel de function calculer à  chaque fois besoin de retenir la resulat
     * cette function appelée par la button btn_equals
     * @param view
     */
    public void Equals(View view) {
        Button btn_equal = (Button) view;
        btn_equal.findViewById(R.id.btn_equal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calculer();
            }
        });
    }

    /**
     * Cette fonation permettant d'afficher la résulat dans l'écran
     */
    private void afficher() {
        if (!isOp1) {
            // v = valeur2;
            Affichage.setText(String.valueOf(valeur1)+" "+operateur.nom +" "+String.valueOf(valeur2));
        }
        else {
            Affichage.setText(String.valueOf(valeur1));
        }
    }

    /**
     * la méthode void calculer(View view) qui effectue l'opération demandée entre les deux opérandes,
     * stocker le résultat en premier opérande et mettre à jour l'affichage.
     */
    public void Calculer() {
        if (!isOp1) {
            switch (operateur) {
                case ADD:
                    valeur1 = valeur1 + valeur2;
                    break;
                case MULTIPLE:
                    valeur1 = valeur1 * valeur2;
                    break;
                case SOUSTRACTIONS:
                    valeur1 = valeur1 - valeur2;
                    break;
                case DIVISER:
                    if( valeur2==0) Toast.makeText(this, "Impossible de diviser par 0!", Toast.LENGTH_LONG).show();
                    else valeur1 = valeur1 / valeur2;
                    break;
                default:
                    return; // si ya pas d'operateur
            }
            valeur2 = 0;
            isOp1 = true;
            afficher();
        }
    }

    /**
     * la méthode void clear() qui initialise l’affichage.
     */
    private void clear() {
        valeur1 = 0;
        valeur2 = 0;
        operateur = null;
        isOp1 = true;
        Affichage.setText(null);
    }

    /**
     * La méthode void setOperator(View view) pour gérer les 4 boutonsd’opérateurs (+, -, * et /).
     * @param view
     */
    public void setOperateur(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                operateur = Ops.ADD;
                break;
            case R.id.btn_multipe:
                operateur = Ops.MULTIPLE;
                break;
            case R.id.btn_dive:
                operateur = Ops.DIVISER;
                break;
            case R.id.btn_st:
                operateur = Ops.SOUSTRACTIONS;
                break;
            default:
                Toast.makeText(this, "opérateur non reconnu", Toast.LENGTH_LONG).show();
                return;
        }
        isOp1 = false;
        afficher();
    }

    /**
     * la méthode void ajouterNbr(View view) qui gère les boutons associés aux
     * chiffres par conversion numérique de la valeur présente dans le texte du bouton
     * @param view
     */
    public void ajouterNbr(View view) {
        try {
            int val = Integer.parseInt(((Button) view).getText().toString());
            if (isOp1) {
                valeur1 = valeur1 * 10 + val;
                afficher();
            } else {
                valeur2 = valeur2 * 10 + val;
                afficher();
            }

        } catch (NumberFormatException | ClassCastException e) {
            Toast.makeText(this, "Valeur erronée", Toast.LENGTH_LONG);
        }
    }

    /**
     *  Les fonctions de calculatrice Scientifique
     * @param view
     */

    public void click(View view) {
        Button clicked = (Button) view;
        if (clicked.getText().toString().equals("AC")) {
            Affichage.clearComposingText();
        } else if (clicked.getText().toString().equals("=")) {
            if (Affichage.getText().toString().trim().isEmpty()) {
                Toast.makeText(MainActivity.this, "SVP entrer une expression!", Toast.LENGTH_LONG).show();
            } else calculatrice(Affichage.getText().toString());
        } else {
            if (clicked.getId() == R.id.btn_add)
                Affichage.setText(Affichage.getText() + "+");
            else if (clicked.getId() == R.id.btn_st)
                Affichage.setText(Affichage.getText() + "-");
            else if (clicked.getId() == R.id.btn_multipe)
                Affichage.setText(Affichage.getText() + "*");
            else if (clicked.getId() == R.id.btn_dive)
                Affichage.setText(Affichage.getText() + "/");
            else Affichage.setText(Affichage.getText() + clicked.getText().toString());
        }
    }

    public void calculatrice(String expression) {
        Expression exp = new Expression(expression);
        double result = exp.calculate();
        if (Double.isNaN(result) && (Affichage.getText().toString().contains("/0"))) {
            Toast.makeText(MainActivity.this, "Impossible de diviser par 0!", Toast.LENGTH_LONG).show();
            clear();
        } else if (Double.isNaN(result)) {
            Toast.makeText(MainActivity.this, "Erreur de syntax !", Toast.LENGTH_LONG).show();
            clear();
        } else {
            Affichage.setText(null);
            Affichage.setText(String.valueOf(result));
        }
    }


}