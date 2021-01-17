<?php
    unset($_SESSION['donneeFormulaire']);
    //Appel du model
    require_once(PATH_MODELS.$page.'.php');
    
    //Fonction selection
    require_once(PATH_LIB.'foncBase.php');

    //Appel de la vue
    require_once(PATH_VIEWS.$page.'.php'); 