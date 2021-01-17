<?php
    //Fonction selection
    require_once(PATH_LIB.'foncBase.php');

    //Vérification du formulaire + récupération des variables
    $_SESSION['statut'] = htmlspecialchars($_GET['statut']);




    //Appel de la vue
    require_once(PATH_VIEWS.$page.'.php'); 