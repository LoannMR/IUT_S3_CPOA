<?php
	include_once 'sql.php';
	
	//Rechercher la ligne id + mdp
	$requete = "SELECT * FROM personne where identifiant = '".$username."' and mdp = '".$password."' ";

	$ligne = execRequete($bdd,$requete,true);
	
?>



