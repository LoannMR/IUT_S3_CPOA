<?php
    //Quel page afficher
    switch($_SESSION['statut'])
    {
        case "gerant":
            $want = 'mesHotels';
            break;
        case "jury":
            break;
    }

    //Appel du model
    require_once(PATH_MODELS.$page.'.php');
    
    
    //Appel de la vue
    require_once(PATH_VIEWS.$page.'.php'); 