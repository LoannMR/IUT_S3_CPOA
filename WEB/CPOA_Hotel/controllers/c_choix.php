<?php
    //Fonction selection
    require_once(PATH_LIB.'foncBase.php');

    //Vérification du formulaire + récupération des variables
    $_SESSION['statut'] = htmlspecialchars($_GET['statut']);
    $_SESSION['idPersonne'] = 1;




    //Appel de la vue
    require_once(PATH_VIEWS.$page.'.php'); 