<?php
//  En tête de page
?>
<?php require_once(PATH_VIEWS.'header.php');?>

<!--  Zone message d'alerte -->
<?php require_once(PATH_VIEWS.'alert.php');?>

<!--  Choix -->


<a href ="index.php?page=choix&statut=vip"><button >VIP</button></a>
<a href ="index.php?page=choix&statut=gerant"><button >Gérant</button></a>

<!--  Pied de page -->
<?php require_once(PATH_VIEWS.'footer.php'); 