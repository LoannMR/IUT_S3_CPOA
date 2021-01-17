<?php
	include_once 'sql.php';

    /**
     * Afficher les informations qu'il demande
     * Hotel en fonction des caractéristiques / types = hotelDisponible
     * Ces réservations = mesReservations
     * Ces Hotels = mesHotels
     */

    switch($want)
    {
        case "mesHotels":
            $requete = "select * from hotel where idGerant = ".$_SESSION['idPersonne'];
            $hotels = execRequete($bdd,$requete,true);
            break;
        case "mesReservations":
            break;
        
        case "hotelDisponible":
            break;
    }
?>