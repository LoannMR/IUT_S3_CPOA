<?php
	include_once 'sql.php';
	//Si l'utilisateur à choisi une catégorie de photo

    $requete = "select * from hotel ";
    $hotels = execRequete($bdd,$requete,true);
?>


