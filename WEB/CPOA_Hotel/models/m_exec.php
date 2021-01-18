<?php
    include_once 'sql.php';
    if($_SESSION['statut']=='gerant')
    {
		$requete = "INSERT INTO hotel (idGerant,nomHotel,nbChambre,classification,caracteristique) 
                VALUES ('".$_SESSION['idPersonne']."',
                        '".$_SESSION['donneeFormulaire']['nom']."',
                        '".$_SESSION['donneeFormulaire']['nb_chambre']."' ,
                        '".$_SESSION['donneeFormulaire']['classification']."' ,
                        null)";
    }
    else
    {
    	$requete = "INSERT INTO reservation (idStaff, idHotel, nbChambre, date_debut, date_fin) 
            VALUES ('".$_SESSION['idPersonne']."',
                    '".$_POST['idHotel']."',
                    '".$_SESSION['donneeFormulaire']['nb_chambre']."',
                    '".$_SESSION['donneeFormulaire']['d_arr']."' ,
                    '".$_SESSION['donneeFormulaire']['d_dep']."'
                )";
    }
    
    execRequete($bdd,$requete,false);