<?php
	include_once 'sql.php';
	
	//Rechercher la ligne id + mdp
	$requete = "SELECT * FROM personne where identifiant = '".$username."' and mdp = '".$password."' ";

	$ligne = execRequete($bdd,$requete,true);
	
	//Redication
	?> <p></p> <?php
	if(count($ligne) == 0)
	{
		echo "Erreur, veuillez contactez l'administrateur";
		exit();
	}
	if($ligne[0]['statut'] == 'Gerant')
	{
		echo 'gerant';
	}
	else if ($ligne[0]['statut'] == 'Staff')
	{
		echo 'staff';
	}
?>



