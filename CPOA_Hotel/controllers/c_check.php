<?php
    
    //Récupérations des fonctions
    require_once(PATH_LIB."foncBase.php");

    //Vérification du formulaire
    if(!isset($_SESSION['donneeFormulaire'])){
        $erreur = validate();
        if($erreur != null)
        {
            echo "erreur";
            ?>
            <h1>Erreur : <?=$erreur?></h1>
            <?php
            exit();
        }
    }
    

    //Pour les caractéristiques
    $checkeds = array();
    foreach($listCaracteristique as $c)
    {
        $c = strtolower($c);
        if(isset($_POST[$c]))
        {
            array_push($checkeds,$c);
        }
    }
    if(isset($_SESSION['donneeFormulaire']['id']))
        $id =$_SESSION['donneeFormulaire']['id'];
    else
        $id = null;
    //Si formulaire Hotel
    if($_SESSION['statut']=='gerant')
    {
        $donneeFormulaire = array(
            "id" => $id,
            "nom" => htmlspecialchars($_POST['nom']),
            "nb_chambre" => htmlspecialchars($_POST['nb_chambre']),
            "classification" => htmlspecialchars($_POST['classification']),
            "caracteristique" => $checkeds,
        );
        $_SESSION['donneeFormulaire'] = $donneeFormulaire;
    }
    //Si formulaire reservation
    if($_SESSION['statut']=='staff')
    {
        if(!isset($_SESSION['donneeFormulaire']) or (isset($_SESSION['donneeFormulaire']['id']) and isset($_POST['id']))){
            $donneeFormulaire = array(
                "nom" => htmlspecialchars($_POST['nom']),
                "prenom" => htmlspecialchars($_POST['prenom']),
                "nb_chambre" => htmlspecialchars($_POST['nb_chambre']),
                "type" => htmlspecialchars($_POST['type']),
                "d_arr" => htmlspecialchars($_POST['d_arr']),
                "d_dep" => htmlspecialchars($_POST['d_dep']),
                "id" => htmlspecialchars($_SESSION['donneeFormulaire']['id']),
                "caracteristique" => $checkeds,
            );
            $_SESSION['donneeFormulaire'] = $donneeFormulaire;
        }
        
    }
    $desactivate = true;

    //Appel du model
    require_once(PATH_MODELS.$page.'.php');
    //Appel de la vue
    require_once(PATH_VIEWS.$page.'.php'); 