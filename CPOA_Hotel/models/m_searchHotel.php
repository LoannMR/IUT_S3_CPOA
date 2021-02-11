<?php
	include_once 'sql.php';

	if(count($checkeds)>0){
		$text = "'%".$checkeds[0]."%'";
		for ($i = 1; $i<count($checkeds); $i++) {
			$text = $text . " and caracteristique like '%".$checkeds[$i]."%'";
		}
		$requete = "select * from hotel where caracteristique like ".$text. "and (type ='".$_SESSION['donneeFormulaire']['type']."' or type is NULL)";
    	$hotels = execRequete($bdd,$requete,true);
	}
	else {
		$requete = "select * from hotel where type ='".$_SESSION['donneeFormulaire']['type']."' or type is NULL";
    	$hotels = execRequete($bdd,$requete,true);
	}
	
?>


