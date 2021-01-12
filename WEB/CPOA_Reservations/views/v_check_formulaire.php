Valider la réservation ?

<?php 
$nom = $_POST['nom'];
$nb_personne = $_POST['nb_personne'];
$prenom = $_POST['prenom'];
$nb_chambre = $_POST['nb_chambre'];

$formulaire = array (
    'Nom' => $nom,
    'Prenom' => $prenom,
    'Nombre de personnes' => $nb_personne,
    'Nombre de chambre' => $nb_chambre,
);

?>
<p><?=$formulaire['Nom']?></p>
<p><?=$formulaire['Prenom']?></p>
<p><?=$formulaire['Nombre de personnes']?></p>
<p><?=$formulaire['Nombre de chambre']?></p>

<?php

if(isset($_POST['plage']))  echo "Plage<br/>";
if(isset($_POST['luxe']))  echo "Luxe<br/>";
if(isset($_POST['montagne']))  echo "Montagne<br/>";
if(isset($_POST['resto']))  echo "Restaurant<br/>";
if(isset($_POST['piscine']))  echo "Piscine<br/>";
if(isset($_POST['sport']))  echo "Salle de sport<br/>";

?>

<button>Retour</button>
<input type="submit" name="valider">