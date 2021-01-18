<?php
    unset($_SESSION['donneeFormulaire']);

    if(isset($_POST['username']) && isset($_POST['password']))
    {
        $username = htmlspecialchars($_POST['username']);
        $password = htmlspecialchars($_POST['password']);
        //Appel du model
        require_once(PATH_MODELS.$page.'.php');

        
    }
    
    
    //Fonction selection
    require_once(PATH_LIB.'foncBase.php');

    //Appel de la vue
    require_once(PATH_VIEWS.$page.'.php'); 