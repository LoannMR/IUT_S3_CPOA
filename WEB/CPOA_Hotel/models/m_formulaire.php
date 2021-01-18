<?php
    /**
     * Récupérer dans la BD les informations pour modifier
     */
    include_once 'sql.php';

    //Reservation
    if(isset($_GET["reservation"]))
    {
        
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
    