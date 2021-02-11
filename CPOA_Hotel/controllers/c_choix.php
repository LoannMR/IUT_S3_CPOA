<?php
    //Fonction selection
    require_once(PATH_LIB.'foncBase.php');

    $_SESSION['idPersonne'] = 1;
    unset($_SESSION['donneeFormulaire']);




    //Appel de la vue
    require_once(PATH_VIEWS.$page.'.php'); 