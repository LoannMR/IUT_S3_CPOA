<?php
    unset($_SESSION['donneeFormulaire']);

    if(isset($_POST['username']) && isset($_POST['password']))
    {
        $username = htmlspecialchars($_POST['username']);
        $password = htmlspecialchars($_POST['password']);
        //Appel du model
        require_once(PATH_MODELS.$page.'.php');

        //Affichage de la bonne page
        if(count($ligne) == 0)
        {
            echo "Erreur, veuillez contactez l'administrateur";
            exit();
        }
        if($ligne[0]['statut'] == 'Gerant')
        {
            $_SESSION['statut'] = 'gerant';
        }
        else if ($ligne[0]['statut'] == 'Staff')
        {
            $_SESSION['statut'] = 'staff';
        }
        header('Location: index.php?page=choix');
        exit();
    }
    
    
    //Fonction selection
    require_once(PATH_LIB.'foncBase.php');

    //Appel de la vue
    require_once(PATH_VIEWS.$page.'.php'); 