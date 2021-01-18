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
    
    //Création hotel
    //Si pas de caractéristiques choisi
    if(!isset($_SESSION['donneeFormulaire']['caracteristique']))
    {
        $requete = "INSERT INTO hotel (idGerant,nomHotel,nbChambre,classification,caracteristique) 
                    VALUES ('".$_SESSION['idPersonne']."',
                            '".$_SESSION['donneeFormulaire']['nom']."',
                            '".$_SESSION['donneeFormulaire']['nb_chambre']."' ,
                            '".$_SESSION['donneeFormulaire']['classification']."' ,
                            null)";
    }
    else // si yen a
    {
        $requete = "INSERT INTO hotel (idGerant,nomHotel,nbChambre,classification,caracteristique) 
                VALUES ('".$_SESSION['idPersonne']."',
                        '".$_SESSION['donneeFormulaire']['nom']."',
                        '".$_SESSION['donneeFormulaire']['nb_chambre']."' ,
                        '".$_SESSION['donneeFormulaire']['classification']."' ,
                        '".$caracteristiques."')";
    }
    
    echo $requete;
    execRequete($bdd,$requete,false);