<?php
    
    //Appel du model
    //require_once(PATH_MODELS.$page.'.php');
    
    //Quel formulaire ?
    $formulaire = htmlspecialchars($_GET['type']);

    //Appel de la vue
    require_once(PATH_VIEWS.$page.'.php'); 