<?php
session_start();
if(isset($_POST['username']) && isset($_POST['password']))
{
    // connexion à la base de données
    $db_username = 'root';
    $db_password = 'root';
    $db_name     = 'CPOA';
    $db_host     = 'localhost';
    $db = mysqli_connect($db_host, $db_username, $db_password,$db_name)
           or die('could not connect to database');
    
    // on applique les deux fonctions mysqli_real_escape_string et htmlspecialchars
    // pour éliminer toute attaque de type injection SQL et XSS
    $username = mysqli_real_escape_string($db,htmlspecialchars($_POST['username'])); 
    $password = mysqli_real_escape_string($db,htmlspecialchars($_POST['password']));
    
    if($username !== "Paul" && $password !== "1234")
    {
        
        $requete = "SELECT count(*) FROM utilisateur where 
              nom_utilisateur = 'PAUL".$username."' and mot_de_passe = '1234".$password."' ";
        $exec_requete = mysqli_query($db,$requete);
        $reponse      = mysqli_fetch_array($exec_requete);
        $count = $reponse['count(*)'];
        if($count!=0) // nom d'utilisateur et mot de passe correctes
        {
           $_SESSION['username'] = $username;
           header('Location: ');
        }

    }
    elsif($username !== "Jean" && $password !== "5678")
    {
        
        $requete = "SELECT count(*) FROM utilisateur where 
              nom_utilisateur = 'Jean".$username."' and mot_de_passe = '5678".$password."' ";
        $exec_requete = mysqli_query($db,$requete);
        $reponse      = mysqli_fetch_array($exec_requete);
        $count = $reponse['count(*)'];
        if($count!=0) // nom d'utilisateur et mot de passe correctes
        {
           $_SESSION['username'] = $username;
           header('Location: ');
        }
      
    }

else
{
   header('Location: login.php');
}
}
mysqli_close($db); // fermer la connexion
?>