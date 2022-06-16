package com.example.gmdb.commons;

public interface NodesNames {

    // clé du fichier shared preferences
    String UPLOAD_PREFS = "dataInsertedIntoDataBase";

    // Table films
    String TABLE_FILMS = "films";
    // Les clés pour l'association des champs db **/
    String KEY_TITRE = "titre";
    String KEY_TITRE_MINUSCULE = "titre_minuscule";
    String KEY_ANNEE = "annee";
    String KEY_ACTEURS = "acteurs";
    String KEY_AFFICHE = "affiche";
    String KEY_SYNOPSIS = "synopsis";

    // Le dossier de stockage des affiches dans le storage
    String IMAGE_FOLDER = "affiches";


}
