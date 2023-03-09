# Shared Preferences

> Une application Android pour stocker Les paramètres d'une application

# Post-condition

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
![version: 1.0.1](https://img.shields.io/badge/version-1.0.1-blue)
![appcompat: 1.6.1](https://img.shields.io/badge/appcompat-1.6.1-green)
![constraintlayout: 2.1.4](https://img.shields.io/badge/constraintlayout-2.1.4-red)

# Démenstartion
Cette vidéo présente l'exécution de mon application avec l'émulateur Nexus 6 API 32.

https://user-images.githubusercontent.com/48890714/224157392-e5b99985-d9b5-47cc-9553-81e78171102c.mp4

# Réalisation 
nous allons voire comment sauvegarder localement certaines informations pertinentes.

## Fichier XML 
Nous avons crée l'interface dans fichier activity_main.xml 


## La gestion des préférences
### Comment faire la gestion des préférences ?
 > L'API mise à disposition par Android pour sauvegarder des informations locales à l'application s'appelle SharedPreferences. C'est une couche d'abstraction qui vous facilite la vie : elle récupère les données que vous lui passez, et les stocke dans un fichier XML.
 Pour accéder à l'instance de SharedPreferences, il suffit d'appeler la méthode getSharedPreferences() sur l'activité courante, en lui fournissant le nom du fichier dans lequel sauvegarder les données, comme ceci:
 
 ``` java
     SharedPreferences sharedPreferences= this.getSharedPreferences("gameSetting", Context.MODE_PRIVATE);
 ```
le premier argument est le nom de fichier dans lequel sauvegarder les données, et le paramètre MODE_PRIVATE permet de préciser que les données du fichier sont accessibles seulement par notre application.
### Modifier les informations stockées 
Pour pouvoir modifier les informations stockées dans les SharedPreferences, il est obligatoire d'utiliser l'API SharedPreferences.Editor.
> :warning: **une information stockée est toujours associée à une clé, permettant ainsi de récupérer précisément une valeur souhaitée sans avoir à récupérer l'ensemble des données.**

il est obligatoire de préciser le type de donnée stockée, en utilisant la méthode correspondante. Par exemple, pour stocker une chaîne de caractères, il faudra utiliser une méthode spécifique appelée `putString()`. Et pour stocker un entier, il faudra utiliser la méthode `putInt()`. Pour valider la modification, il est nécessaire d'appeler la méthode `apply()`.
``` java
    SharedPreferences sharedPreferences= this.getSharedPreferences("gameSetting", Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putInt("brightness", this.seekBarBrightness.getProgress());
    editor.putInt("sound", this.seekBarSound.getProgress());
      // Checked RadioButton ID.
    int checkedRadioButtonId = radioGroupDiffLevel.getCheckedRadioButtonId();
    editor.putInt("checkedRadioButtonId", checkedRadioButtonId);
       // Save.
    editor.apply();
```
### la lecture des informations stockées 
la lecture d'une valeur requiert l'appel à une méthode spécifique suivant le type de donnée à récupérer. Par exemple, pour récupérer une chaîne de caractères, il faudra utiliser `getString()`, alors qu'un entier sera récupéré en utilisant `getInt()`.
``` java
    SharedPreferences sharedPreferences= this.getSharedPreferences("gameSetting", Context.MODE_PRIVATE);
    int brightness = sharedPreferences.getInt("brightness", 90);
    int sound = sharedPreferences.getInt("sound",95);
    int checkedRadioButtonId = sharedPreferences.getInt("checkedRadioButtonId", R.id.radioButton_medium);
```
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
