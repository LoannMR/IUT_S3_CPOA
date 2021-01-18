<?php
    
    //Appel du model
    //require_once(PATH_MODELS.$page.'.php');
    unset($_SESSION['donneeFormulaire']);


    //Appel du model
    require_once(PATH_MODELS.$page.'.php');
    //Appel de la vue
    require_once(PATH_VIEWS.$page.'.php'); 