<?php
    
    //Appel du model
    //require_once(PATH_MODELS.$page.'.php');
    
    //Quel formulaire ?
    $_SESSION['statut'] = htmlspecialchars($_GET['statut']);

    //Appel de la vue
    require_once(PATH_VIEWS.$page.'.php'); 