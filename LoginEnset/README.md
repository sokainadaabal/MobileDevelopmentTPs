# TP2-Splash screen-Navigation entre Écrans- Internationalisation
> Une application Android Login Enset

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
![version: 1.0.1](https://img.shields.io/badge/version-1.0.1-blue)
![appcompat: 1.6.1](https://img.shields.io/badge/appcompat-1.6.1-green)
![constraintlayout: 2.1.4](https://img.shields.io/badge/constraintlayout-2.1.4-red)



## Démenstartion

Cette vidéo présente l'exécution de mon application avec l'émulateur Nexus 6 API 32.

https://user-images.githubusercontent.com/48890714/222481059-58936fe7-2448-4c45-bcf0-43cff93f2a46.mp4


## Les interfaces de L'application 

| Interfaces  | l’écran d’accueil ( Splash Screen )                                                                                       | Authentification                                                                                                       | Information de compte                                                                                                  |
|-------------|---------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------|
| AR          | ![Image](https://github.com/sokainadaabal/MobileDevelopmentTPs/blob/main/LoginEnset/app/Captures/Interface1-ar%20.png)    | ![Image](https://github.com/sokainadaabal/MobileDevelopmentTPs/blob/main/LoginEnset/app/Captures/Interface2-ar%20.png) | ![Image](https://github.com/sokainadaabal/MobileDevelopmentTPs/blob/main/LoginEnset/app/Captures/Interface3-ar%20.png) |
| FR          | ![Image](https://github.com/sokainadaabal/MobileDevelopmentTPs/blob/main/LoginEnset/app/Captures/Interface1-fr%20.png)    | ![Image](https://github.com/sokainadaabal/MobileDevelopmentTPs/blob/main/LoginEnset/app/Captures/Interface2-fr%20.png) | ![Image](https://github.com/sokainadaabal/MobileDevelopmentTPs/blob/main/LoginEnset/app/Captures/Interface3-fr%20.png) |
| EN          | ![Image](https://github.com/sokainadaabal/MobileDevelopmentTPs/blob/main/LoginEnset/app/Captures/Interface1-en%20.png)    | ![Image](https://github.com/sokainadaabal/MobileDevelopmentTPs/blob/main/LoginEnset/app/Captures/Interface2-en%20.png) | ![Image](https://github.com/sokainadaabal/MobileDevelopmentTPs/blob/main/LoginEnset/app/Captures/Interface3-en%20.png) |


## La réalisation 

Pour réaliser cette application, nous avons conçu trois interfaces : 
  - Splash screen ou bien l'écran d'accueil avec une animation de l'image, le nom de l'application et le slogan.
  - L'authentification : un formulaire à remplir par l'utilisateur et qui contient des champs permettant de saisir le nom d'utilisateur et l'autre pour saisir son mot de passe et un bouton pour l'identifier.
  - Informations du compte : affiche le nom d'utilisateur et le mot de passe de la personne se connecter à l'aide d'un bouton de déconnexion.

### I. Réalisation d'interface graphique
#### 1.1. Suppression de la barre d'action

   - Suivez ce chemin pour trouver le fichier que vous devez modifier : res > values > themes > themes.xml .
   - Éditer DarkAction > NoActionBar au sein de balise <style>.  
  
       ```` xml
            <resources>
              <!-- Base application theme. -->
              <style name="Theme.LoginEnset" parent="Theme.MaterialComponents.DayNight.NoActionBar">
                  <!-- Primary brand color. -->
                  <item name="colorPrimary">@color/purple_500</item>
                  <item name="colorPrimaryVariant">@color/MycalorforApp</item>
                  <item name="colorOnPrimary">@color/white</item>
                  <!-- Secondary brand color. -->
                  <item name="colorSecondary">@color/teal_200</item>
                  <item name="colorSecondaryVariant">@color/teal_700</item>
                  <item name="colorOnSecondary">@color/black</item>
                  <!-- Status bar color. -->
                  <item name="android:statusBarColor">?attr/colorPrimaryVariant</item>
                  <!-- Customize your theme here. -->
                  <item name="android:windowContentTransitions">true</item>
              </style>
           </resources>
      ````
  
#### 1.2. Masquer la barre d'état dans l'activite Android 
  
  Masquer la barre de statut dans un studio Android à partir de l'activité spécifique. Ouvrez le fichier Java pour cette activité et ajoutez la ligne simple indiquée ci-dessous, au-dessus de la méthode setContentView() dans onCreate() dans .java du code.
  
  ``` java
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
  ```
  
#### 1.3. Concevoir les interfaces 
##### 1.3.1. Splach Screen
  
  Celle-ci contient une image, le nom de l'application et le slogan.
  
  ``` xml
        <ImageView
              android:id="@+id/image"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content"
              android:layout_marginStart="10dp"
              android:layout_marginEnd="10dp"
              android:layout_marginBottom="160dp"
              android:transitionName="logo_image"
              app:layout_constraintBottom_toBottomOf="parent"
              app:layout_constraintEnd_toEndOf="parent"
              app:layout_constraintStart_toStartOf="parent"
              app:layout_constraintTop_toTopOf="parent"
              app:srcCompat="@drawable/connectedworldbro" />

          <TextView
              android:id="@+id/logo"
              android:layout_width="0dp"
              android:layout_height="wrap_content"
              android:layout_marginStart="20dp"
              android:layout_marginEnd="20dp"
              android:layout_marginBottom="10dp"
              android:fontFamily="@font/bungee"
              android:text="@string/logo"
              android:textAlignment="center"
              android:textColor="@color/white"
              android:textSize="50sp"
              android:transitionName="logo_text"
              app:layout_constraintBottom_toTopOf="@+id/slogan"
              app:layout_constraintEnd_toEndOf="parent"
              app:layout_constraintStart_toStartOf="parent" />

          <TextView
              android:id="@+id/slogan"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content"
              android:layout_marginBottom="40dp"
              android:fontFamily="@font/annie_use_your_telescope"
              android:text="@string/slogan"
              android:textAlignment="center"
              android:textColor="@color/white"
              android:textSize="20sp"
              app:layout_constraintBottom_toBottomOf="@+id/image"
              app:layout_constraintEnd_toEndOf="parent"
              app:layout_constraintStart_toStartOf="parent" />
  ```
  
##### 1.3.2. Authentification 
   
 Cette interface contient deux champs de saisie, un button, image et texte.
  
   - ``` Image ```
  
     ``` xml 
          <ImageView
            android:id="@+id/logoImage"
            android:layout_width="150dp"
            android:layout_height="150dp"
            android:layout_gravity="end"
            android:transitionName="logo_image"
            app:srcCompat="@drawable/conversation_pana"/>
     ```
  
  - ``` Text ```
  
     ``` xml 
        <TextView
          android:id="@+id/logoName"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:fontFamily="@font/bungee"
          android:transitionName="logo_text"
          android:text="@string/textLogin"
          android:textColor="@color/MycalorforApp"
          android:textSize="40sp" />
     ```
  
  - ``` les champs de saisies ```
  
    ``` xml 
        <com.google.android.material.textfield.TextInputLayout
              android:id="@+id/username"
              android:layout_width="match_parent"
              android:layout_height="wrap_content"
              android:hint="@string/username">

              <com.google.android.material.textfield.TextInputEditText
                  android:layout_width="match_parent"
                  android:layout_height="wrap_content"
                  android:layout_gravity="center"
                  android:layout_marginBottom="20sp"
                  android:backgroundTint="@color/white"
                  android:singleLine="true"
                  android:textColor="@color/MycalorforApp" />

         </com.google.android.material.textfield.TextInputLayout>
    ```
  
   - ``` Button ```
  
      ``` xml 
          <Button
              android:layout_width="200dp"
              android:layout_height="wrap_content"
              android:layout_gravity="center"
              android:layout_marginTop="30dp"
              android:backgroundTint="@color/MycalorforApp"
              android:fontFamily="@font/antic"
              android:hint="@string/button"
              android:textColorHint="@color/white"
              android:textSize="20sp"
              android:textStyle="bold"
              app:cornerRadius="40sp"
              android:onClick="envoyer"/>
      ```
  
  > :warning: **Assurez-vous d'avoir ajouté cette extension dans le fichier de gradle : ``` com.google.android.material:material:1.8.0 ```, dans le but de travailler avec le materiel  design.** 
  
  ##### 1.3.3. Information de compte
  
  Cette interface contient une image, du texte, des infos utilisateur et un bouton à déconnecter.
  
  <img src="https://github.com/sokainadaabal/MobileDevelopmentTPs/blob/main/LoginEnset/app/Captures/Interface3-fr%20.png" style=" height:500px; text-align:center"/>
  
  > :warning: **Toutes les valeurs textuelles sont stockées dans le fichier res > values > strings.xml.** 
  
### II. Animation et Codage de ecran d'acceuil :
#### 2. Associer interface a fichier ``` manifest.xml ```
  
  > Le splash screen est le premier écran, l'écran de lancement de l'application, qui apparaît lorsque l'application est en cours de chargement.
  > Vous devez déclarer que le fichier contenant splach screen correspond à l'activité de démarrage de votre application.
  
  ``` xml 
            <activity
                android:name=".MainActivity"
                android:exported="true"
                android:theme="@style/Theme.App.Starting">
                <intent-filter>
                    <action android:name="android.intent.action.MAIN" />

                    <category android:name="android.intent.category.LAUNCHER" />
                </intent-filter>
            </activity>
  ```
  
 #### 2.2. Créer classe de l'interface  graphique splash screen
  
 ##### 2.2.1. Les éléments d'interface
  
  ``` java
        image = findViewById(R.id.image);
        logo = findViewById(R.id.logo);
        slogan = findViewById(R.id.slogan);
  ```
  
  ##### 2.2.2.  Fichier Animation (Exemple > pour image)
  
  ``` xml
     <?xml version="1.0" encoding="utf-8"?>
    <set xmlns:android="http://schemas.android.com/apk/res/android">
        <translate
            android:fromXDelta="0%"
            android:fromYDelta="100%"
            android:duration="2500"/>

        <alpha android:fromAlpha="0.1"
            android:toAlpha="1.0"
            android:duration="2500"/>
    </set>
  ```
  
  ##### 2.2.3. Classe MainActivity contrôle le splash screen
  
  ``` java 
  public class MainActivity extends AppCompatActivity {
    // variable of splashscreen
    private static int SPLASH_TIME_OUT=5000;

    // variables
    ImageView image;
    TextView logo,slogan;

    // variables for Animation
    Animation toAnim, bottomAnim;

    private String TAG="SplashScreen";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        Log.e(TAG,"OnCreate vient  d'être appelée!");
        Toast.makeText(this,"OnCreate vient  d'être appelée!",Toast.LENGTH_SHORT).show();
        //Animations
        toAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim=AnimationUtils.loadAnimation(this,R.anim.bottom_annimation);
        // hooks
        image = findViewById(R.id.image);
        logo = findViewById(R.id.logo);
        slogan = findViewById(R.id.slogan);
        image.setAnimation(toAnim);
        logo.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);

        new Handler().postDelayed(()->{
                //  This methode will  be executed one the timer is over
                // start your app  login activity
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                //startActivity(i);
                // close this Activity you can use function  finish
                Pair[] pairs = new Pair[2];
                pairs[0]=new Pair<View,String>(image,"logo_image");
                pairs[1]=new Pair<View,String>(logo,"logo_text");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
                startActivity(i,options.toBundle());
        }, SPLASH_TIME_OUT);
    }
  }
  ```
  
 pour permettre l'animation partagée, ajoutez au fichier res > values > themes.xml
  
  ``` xml 
    <item name="android:windowContentTransitions">true</item>
  ```
  
 ### III. La navigation entre les activités
  
  Pour passer d'une activité à une autre, nous utilisons ce qui suit :
  
  - Pour but de naviger entre les activités , passer des parametre d'une activité à une autre et écoyuter et réagir à des invénents extérieurs à l'application on va utilise des Intents.
   
    ```java
       Intent i = new Intent(MainActivity.this,LoginActivity.class);
       startActivity(i);
    ```
    > :warning: **Chaque nouvelle activite doit declarer dans manifest.xml**
  
  - Afin de stocker les données entre les activités, nous utilisons Bundle.
  
    ``` java 
        Bundle bundle = new Bundle();
        bundle.putString("username",username.getEditText().getText().toString().trim());
        bundle.putString("password",password.getEditText().getText().toString().trim());
        intent.putExtras(bundle);
        startActivity(intent);
    ```
  
  - Afin de récupérer des données, nous avons utilser le code suivant :

    ``` java
       Bundle bundle = getIntent().getExtras();
       username.setText(bundle.getString("username"));
       password.setText(bundle.getString("password"));
    ```
  
 ### IV. L'internationnalisation 
  
 > L’internationalisation ou L18N se défini comme un procédé de conception de logiciel ou d’une application et qui permet de l’adapter à différentes langues, régions et cultures sans avoir à apporter des modifications sur le programme. Le terme internationalisation est souvent abrégé en i18n, avec 18 représentant le nombre de caractères entre les lettres i et n dans le mot. Pour rendre votre application multi-langage, il suffit de creer des repertois values-XX( ou XX est le code de langue que l'on souhqite implanter).
 
 - Répertoires contenant le fichier string.xml, le fichier contient les chaînes traduites associées avec les mêmes clés que dans valeurs/strings.xml.  
 - Pour notre cas, nous avons créé deux fichiers, un pour l'anglais et l'autre pour l'arabe.
 - Utilisation de l'éditeur de traduction qui offre `Android studio` pour traiter tous les fichiers de traduction.
  
  ![image](https://user-images.githubusercontent.com/48890714/222532561-b960a218-172c-4e74-81bd-0737f7f6519b.png)
  
   > :warning: **Pour tester, vous n'avez qu'à modifier la langue de votre téléphone.**
  
### V. Utlissation de classe Toast  pour connaitre cycle de vie de
  
 - Une fenêtre de dialogue qui affiche un message pendant 2 (Toast.LENGTH_SHORT) ou 5 (Toast.LENGTH_LONG) secondes est un composant graphique Android
 - On le construit et on l'affiche avec le code.
  
     ``` java 
        Toast leToast = Toast.makeText(leContexte, "texteAAfficher", Toast.LENGTH_LONG);
        leToast.show();
     ```
  
  - Une des méthodes qui construit un Toast est la méthode statique :
  
    ``` java
      public static Toast makeText (Context context, CharSequence text, int duree)
    ```
  
  - Le premier argument est le context de l'application, le deuxieme est le text a afficher et le troisieme c'est la duree LENGTH_LONG ou  LENGTH_SHORT.
    
    > :warning: *Attention construire le Toast ne l'affiche pas : il faut utiliser show().*
  
  
  On va utiliser cette classe pour afficher certains fonctionnalites qui decrit le cycle de vie d'une application: 
  > - OnCreate(): cette méthode permet de réaliser les tâches d’initialisation les plus coûteuse (comme par exemple, créer le layout d’affichage de l’activité). C’est dans cette méthode que doivent être réalisés les traitements à exécuter une seule fois pour toute la vie de l’activité.
  > - onStart(): cette méthode permet de savoir quand une activité va être rendue visible à l’utilisateur.
  > - OnResume(): cette méthode permet de savoir quand une activité va être accessible (y compris quand elle sort d’une pause).
  > - OnPause() : cette méthode permet de savoir quand une activité n’est plus active pour l’utilisateur ou passe à un autre activité.
  > - OnRestart() : cette méthode permet de savoir qu’une activité va être à nouveau démarrée après avoir été stoppée.
  > - OnStop(): cette méthode permet de savoir que l’activité va être stoppée et ne sera plus visible de l’utilisateur.
  > - OnDestory():   cette méthode permet de savoir que l’activité va être détruite.
  
  - code a ajouter dans l'interface MainActivity : 
 
  ``` java 
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,"OnCreate vient  d'être appelée!",Toast.LENGTH_SHORT).show();
    }
  
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart vient d'être appelée !");
        Toast.makeText(this, "onStart vient d'être appelée !", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart vient d'être appelée !");
        Toast.makeText(this, "onRestart vient d'être appelée !", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume vient d'être appelée !");
        Toast.makeText(this, "onResume vient d'être appelée !", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause vient d'être appelée !");
        Toast.makeText(this, "onPause vient d'être appelée !", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop vient d'être appelée !");
        Toast.makeText(this, "onStop vient d'être appelée !", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy vient d'être appelée !");
        Toast.makeText(this, "onDestroy vient d'être appelée !", Toast.LENGTH_SHORT).show();
    }
  
  ```
# Contribuer au projet

<p style="justify">Les contributions sont ce qui fait de la communauté open source un excellent lieu d'apprentissage, d'inspiration et de création. </p>
Nous apprécions beaucoup votre contribution

1. Cloner le projet.
2. Créer votre branche  appler MiseAjour  ( ` git checkout -b feature/AmazingFeatur ` ).
3. Importer vos modifications a votre branche ( `git commit -m 'Add some AmazingFeature'` ).
4. Ajouter à la branche initiale ( `git push origin feature/AmazingFeatur ` ).
5. Ouvrir Pull Request.


# License

[MIT](https://choosealicense.com/licenses/mit/) 
