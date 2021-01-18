<?php
    /**
     * Page qui crée Les Hotels / reservation dans la BD
     */
    include_once 'sql.php';

    //Récupérons les caractéristiques choisi
    if(isset($_SESSION['donneeFormulaire']['caracteristique']))
        $caracteristiques = implode(' ',$_SESSION['donneeFormulaire']['caracteristique']);
    else
        $caracteristiques = null;
    echo $caracteristiques;
    

    //HOTEL
    if($_SESSION['statut']=='gerant')
    {
        //Si il n'y a pas de caractéristique
        if(!isset($_SESSION['donneeFormulaire']['caracteristique']))
        {
            $requete = "INSERT INTO hotel (idGerant,nomHotel,nbChambre,classification,caracteristique) 
                        VALUES ('".$_SESSION['idPersonne']."',
                                '".$_SESSION['donneeFormulaire']['nom']."',
                                '".$_SESSION['donneeFormulaire']['nb_chambre']."' ,
                                '".$_SESSION['donneeFormulaire']['classification']."' ,
                                null)";
        }
        else // Sinon
        {
            $requete = "INSERT INTO hotel (idGerant,nomHotel,nbChambre,classification,caracteristique) 
                VALUES ('".$_SESSION['idPersonne']."',
                        '".$_SESSION['donneeFormulaire']['nom']."',
                        '".$_SESSION['donneeFormulaire']['nb_chambre']."' ,
                        '".$_SESSION['donneeFormulaire']['classification']."' ,
                        '".$caracteristiques."')";
        }
    }
    //RESERVATION
    else
    {
    	if(isset($_POST['hotelType'])){
    		if(strtolower($_POST['hotelType']) == 'null' or $_POST['hotelType'] == ''){
    			$requete = "UPDATE hotel set type = '".$_SESSION['donneeFormulaire']['type']."' WHERE idHotel = ".$_POST['idHotel'];
    			execRequete($bdd,$requete,false);
    		}
    	}

        if(isset($_SESSION['donneeFormulaire']['id'])){
            $requete = "UPDATE reservation set idhotel = '".$_POST['idHotel']."',
                        nbChambre = '".$_SESSION['donneeFormulaire']['nb_chambre']."',
                        date_debut = '".$_SESSION['donneeFormulaire']['d_arr']."' ,
                        date_fin = '".$_SESSION['donneeFormulaire']['d_dep']."',
                        nom = '".$_SESSION['donneeFormulaire']['nom']."',
                        prenom = '".$_SESSION['donneeFormulaire']['prenom']."',
                        type = '".$_SESSION['donneeFormulaire']['type']."'
                        where idReservation = '". $_SESSION['donneeFormulaire']['id'] . "'
                    ";
        }
        else{
            $requete = "INSERT INTO reservation (idStaff, idHotel, nbChambre, date_debut, date_fin, nom, prenom, type) 
                VALUES ('".$_SESSION['idPersonne']."',
                        '".$_POST['idHotel']."',
                        '".$_SESSION['donneeFormulaire']['nb_chambre']."',
                        '".$_SESSION['donneeFormulaire']['d_arr']."' ,
                        '".$_SESSION['donneeFormulaire']['d_dep']."',
                        '".$_SESSION['donneeFormulaire']['nom']."',
                        '".$_SESSION['donneeFormulaire']['prenom']."',
                        '".$_SESSION['donneeFormulaire']['type']."'
                    )";
        }
        
    }
    //Exécution de la requete
    execRequete($bdd,$requete,false);