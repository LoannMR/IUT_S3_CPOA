<?php require_once(PATH_VIEWS.'header.php');?>

<!--  Zone message d'alerte -->
<?php require_once(PATH_VIEWS.'alert.php');?>

<!--  Choix -->
<?php
if($_SESSION['statut'] == "gerant")
{ ?>
    <a href ="index.php?page=formulaire&statut=gerant"><button >Créer un Hôtel</button></a>
    <a href ="index.php?page=formulaire&statut=gerant"><button >Modifier un hôtel</button></a>
<?php
}
else // Vip
{ ?>
    <a href ="index.php?page=formulaire&statut=vip"><button >Faire une réservation</button></a>
    <a href ="index.php?page=formulaire&statut=vip"><button >Modifier une réservation</button></a>
<?php
}
?>



<!--  Pied de page -->
<?php require_once(PATH_VIEWS.'footer.php'); 