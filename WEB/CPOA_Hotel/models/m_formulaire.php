<?php
	include_once 'sql.php';

	if(isset($_GET['reservation'])){
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
	
?>


