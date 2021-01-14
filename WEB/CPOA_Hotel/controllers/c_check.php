<?php
    
    //Appel du model
    require_once(PATH_MODELS.$page.'.php');
    if(isset($erreur)){
        $alert = choixAlert($erreur);
    }
    
    //Appel de la vue
    require_once(PATH_VIEWS.$page.'.php'); 