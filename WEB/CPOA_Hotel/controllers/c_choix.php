<?php
    //Fonction selection
    require_once(PATH_LIB.'foncBase.php');

    //Vérification du formulaire + récupération des variables
    if(isset($_GET['statut'])){
    	$_SESSION['statut'] = htmlspecialchars($_GET['statut']);
    }
    
    $_SESSION['idPersonne'] = 1;
    unset($_SESSION['donneeFormulaire']);




    //Appel de la vue
    require_once(PATH_VIEWS.$page.'.php'); 