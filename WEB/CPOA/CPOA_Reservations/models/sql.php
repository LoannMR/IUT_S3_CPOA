<?php
  // accès base de données
  // connection à la base de données
  try
  {
	$bdd = new PDO('mysql:host='.BD_HOST.'; dbname='.BD_DBNAME.'; charset=utf8', BD_USER, BD_PWD);
	$bdd->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
  }
  catch(PDOException $e)
  { 
        if(DEBUG)
                die ('Erreur : '.$e->getMessage());
	$erreur = 'connexion';
  }

function execRequete($bdd, $requete,$parametre = null)
{
	try
	{
		$query = $bdd->prepare($requete);
		//Si la requête à éxécuter contient de(s) paramètre(s) ou non
		if($parametre == null)
			$query->execute();
		else
		{
			$c = 1;
			foreach($parametre as $p)
				$query->bindParam($c++, $p);
			$query->execute();
		}
	}
	catch(PDOException $e)
	{
				if(DEBUG)
						die ('Erreur : '.$e->getMessage());
		$erreur = 'query';
	}
	
	return $resultat = $query->fetchAll(PDO::FETCH_ASSOC);
}