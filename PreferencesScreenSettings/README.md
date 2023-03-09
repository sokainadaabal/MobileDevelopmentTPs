# Prefrences Screen Setting " Les paramètres d'une application "

> Une application Android pour modifier Les paramètres d'une application
# c'est quoi prefrences screen setting ( Paramètres d'une application )
> Dans de nombreuses applications, nous avons vu l'écran Paramètres qui est le plus courant dans la plupart des applications. Cet écran de paramètres est utilisé pour gérer les préférences des utilisateurs. Pour créer cet écran de paramètres, Android fournit une fonctionnalité permettant de créer un écran de préférences de paramètres.

# Post-condition
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
![version: 1.0.1](https://img.shields.io/badge/version-1.0.1-blue)
![appcompat: 1.6.1](https://img.shields.io/badge/appcompat-1.6.1-green)
![constraintlayout: 2.1.4](https://img.shields.io/badge/constraintlayout-2.1.4-red)
# Demo

https://user-images.githubusercontent.com/48890714/224146193-459a0766-6b67-4447-87b9-4186607c0380.mp4

# Menu
Première étape est de créer une ressource menu.
Une ressource de menu définit un menu d'application (menu d'options, menu contextuel ou sous-menu) qui peut être agrandi avec MenuInflater.
## Emplacement de fichier menu 
  `res>menu>menu.xml` 
   > :warning: **Le nom de fichier sera utilisé comme ID de ressource.**
   - ``` le contenu de fichier menu```
   ``` xml
    <?xml version="1.0" encoding="utf-8"?>
    <menu xmlns:android="http://schemas.android.com/apk/res/android">
        <item android:id="@+id/setting" android:title="Settings"></item>
    </menu>
   ```
   - ``` associer le menu a class mainActivity ```
   ``` java
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return  super.onCreateOptionsMenu(menu);

    }
   ```
# Mise en place de préférences spécifiques à votre application
Ces préférences se définissent au travers d’un fichier XML stocké sous res\xml\. Ce fichier XML est appelé par la méthode 
``` java 
 @SuppressLint("ValidFragment")
    public static class MySettingsfragment extends PreferenceFragment {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences_settings);
        }
    }
```
qui charge dans l’objet shareReference de l’application les valeurs contenues dans ce fichier. Android dispose d’un éditeur générique pour afficher ce fichier et permettre à l’utilisateur de le modifier. Ces préférences peuvent aussi être modifiées de manière programmatique.
  > :warning: **Dans ce projet nous avons pas modifier les préférences avec la manière programmatique.**
  
Par la suite j'ai ajouter ceci ` fragment ` à mon activity `Settings`:
``` java
   @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content,new MySettingsfragment()).commit();
    }
```
Pour lier la transaction entre `MainActivity` et `Setting` j'ai ajouter ce code : 
``` java
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.setting:
                startActivity(new Intent(MainActivity.this,Settings.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
 ```
 
    
### le fichier de préférences
  - ``` le fichier preferences_settings```
  ``` xml 
     <PreferenceCategory android:title="Informations de la mise en veille">
        <CheckBoxPreference
            android:defaultValue="true"
            android:key="veille"
            android:title="Mise en veille"
            android:summary="Mettre le téléphone en veille"/>
        <ListPreference
            android:defaultValue="20"
            android:entries="@array/updateInterval"
            android:entryValues="@array/updateIntervalValues"
            android:key="veille_intervale"
            android:title="la durée de la mise en veille"
            android:dependency="veille"
            android:summary="Déterminer la durrée après laquelle l'appareil se mettra en veille"/>
    </PreferenceCategory>
     <PreferenceCategory android:title="Information personnelle">
        <EditTextPreference
            android:defaultValue=""
            android:key="full_name"
            android:selectAllOnFocus="false"
            android:singleLine="true"
            android:title="Nom complet"
            android:dialogTitle="Entrez votre nom complet"
            android:dialogMessage="Entrez votre nom complet svp"
            android:summary="Entrez votre nom complet qui sera utilisé dans l'application"
            android:inputType="textCapWords"/>
        <EditTextPreference
            android:defaultValue=""
            android:key="email_address"
            android:selectAllOnFocus="true"
            android:singleLine="true"
            android:title="Adresse email"
            android:dialogTitle="Entrez votre adresses email"
            android:dialogMessage="Entrez votre adresse email"
            android:summary="Entrez votre adresse email institutionnelle"

            android:inputType="textEmailAddress"/>
    </PreferenceCategory>
  ```
  
  Cette hiérarchie contient deux catégorie une qui regroupe les informations de la mise en veille, et l'autre Information personnelle.
  la premier contient `CheckBoxPreference` qui fournit la fonctionnalité de widget de case à cocher.
  Cette préférence stockera un booléen dans SharedPreferences. nous avons aussi  `ListPreference` qui affiche une liste d'entrées sous forme de boîte de dialogue, cette liste sera stocker dans le fichier `res>values>array.xml`.
      
  - Le ficher ``` array.xml ```contient
      
      ```xml
        <string-array name="updateInterval">
            <item>5 minutes</item>
            <item>10 minutes</item>
            <item>20 minutes</item>
        </string-array>
        <string-array name="updateIntervalValues">
            <item>5</item>
            <item>10</item>
            <item>20</item>
        </string-array>
      ```
      
   la deuxieme `PreferenceCategory` contient deux `EditTextPreference` qui permet l'entrée de chaîne. C'est une sous-classe de DialogPreferenceet affiche le EditText dans une boîte de dialogue. Cela EditTextpeut être modifié soit par programmation via getEditText(), soit via XML en définissant tous les attributs EditText sur EditTextPreference..
  
# Contribuer au projet
Les contributions sont ce qui fait de la communauté open source un excellent lieu d'apprentissage, d'inspiration et de création.

Nous apprécions beaucoup votre contribution
  1. Cloner le projet.
  2. Créer votre branche appler MiseAjour ( `git checkout -b feature/AmazingFeatur` ).
  3. Importer vos modifications a votre branche ( `git commit -m 'Add some AmazingFeature'` ).
  4. Ajouter à la branche initiale ( `git push origin feature/AmazingFeatur`  ).
  5. Ouvrir Pull Request.
# Licence 
[MIT](https://choosealicense.com/licenses/mit/) 
