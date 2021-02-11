<?php
	include_once 'sql.php';
	//Si l'utilisateur à choisi une catégorie de photo

	if(isset($_POST["hotel"])){
		$requete = "select * from hotel where nomHotel = '".$_POST["hotel"] ."'";
    	$hotel = execRequete($bdd,$requete,true);
	}

?>


