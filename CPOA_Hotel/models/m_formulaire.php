<?php
    /**
     * Récupérer dans la BD les informations pour modifier
     */
    include_once 'sql.php';

    //Reservation
    if(isset($_GET["reservation"]))
    {
        $requete = "select * from reservation where idReservation = ".$_GET['reservation'];
    	$reservations = execRequete($bdd,$requete,true);
    	$checkeds = array();
    	$donneeFormulaire = array(
                "nom" => htmlspecialchars($reservations[0]['nom']),
                "prenom" => htmlspecialchars($reservations[0]['prenom']),
                "nb_chambre" => htmlspecialchars($reservations[0]['nbChambre']),
                "type" => htmlspecialchars($reservations[0]['type']),
                "d_arr" => htmlspecialchars($reservations[0]['date_debut']),
                "d_dep" => htmlspecialchars($reservations[0]['date_fin']),
                "id" => htmlspecialchars($reservations[0]['idReservation']),
                "caracteristique" => $checkeds,
            );
            $_SESSION['donneeFormulaire'] = $donneeFormulaire;
    }

    //Hotel
    else if (isset($_GET["hotel"]))
    {
        $requete = "SELECT * from hotel where idHotel ='".$id."'";
        $ligne = execRequete($bdd,$requete,true);

        //Si il contient des caractéristiques
        $checkeds =  array();
        if($ligne[0]['caracteristique'] != null)
        {
            $checkeds = explode(" ",$ligne[0]['caracteristique']);
        }
        else
        {
            $checkeds = null;
        }
        
        $donneeFormulaire = array(
            "id" => $ligne[0]['idHotel'],
            "nom" => $ligne[0]['nomHotel'],
            "nb_chambre" => $ligne[0]['nbChambre'],
            "classification" => $ligne[0]['classification'],
            "caracteristique" => $checkeds,
        );
        $_SESSION['donneeFormulaire'] = $donneeFormulaire;
    }
	
?>


