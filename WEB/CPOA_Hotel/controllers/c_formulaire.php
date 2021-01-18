<?php
    
    //Si on demande une modification
    if(isset($_GET['reservation']) || isset($_GET['hotel']))
    {
        //Reservation
        if(isset($_GET['reservation']))
            $id = intval($_GET['reservation']);
        //Hotel
        if(isset($_GET['hotel']))
            $id = intval($_GET['hotel']);
            
        //Appel du model
        require_once(PATH_MODELS.$page.'.php');
    }
    else
        unset($_SESSION['donneeFormulaire']);

    //Appel de la vue
    require_once(PATH_VIEWS.$page.'.php'); 