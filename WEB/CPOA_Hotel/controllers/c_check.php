<?php
    
    //Récupérations des fonctions
    require_once(PATH_LIB."foncBase.php");

    //Vérification du formulaire
    $erreur = validate();
    if($erreur != null)
    {
        ?>
        <h1>Erreur : <?=$erreur?></h1>
        <?php
        exit();
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

    //Si formulaire Hotel
    if($_SESSION['statut']=='gerant')
    {
        $donneeFormulaire = array(
            "nom" => htmlspecialchars($_POST['nom']),
            "nb_personne" => htmlspecialchars($_POST['nb_personne']),
            "nb_chambre" => htmlspecialchars($_POST['nb_chambre']),
            "adresse" => htmlspecialchars($_POST['adresse']),
            "caracteristique" => $checkeds,
        );
        $_SESSION['donneeFormulaire'] = $donneeFormulaire;
    }
    //Si formulaire reservation
    if($_SESSION['statut']=='vip')
    {
        $donneeFormulaire = array(
            "nom" => htmlspecialchars($_POST['nom']),
            "prenom" => htmlspecialchars($_POST['prenom']),
            "nb_personne" => htmlspecialchars($_POST['nb_personne']),
            "nb_chambre" => htmlspecialchars($_POST['nb_chambre']),
            "type" => htmlspecialchars($_POST['type']),
            "d_arr" => htmlspecialchars($_POST['d_arr']),
            "d_dep" => htmlspecialchars($_POST['d_dep']),
            "caracteristique" => $checkeds,
        );
        $_SESSION['donneeFormulaire'] = $donneeFormulaire;
    }
    $desactivate = true;
    //Appel de la vue
    require_once(PATH_VIEWS.$page.'.php'); 