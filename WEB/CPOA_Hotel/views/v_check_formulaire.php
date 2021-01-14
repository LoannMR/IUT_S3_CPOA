Votre choix ....
<?php 
$nom = $_POST['nom'];
$nb_personne = $_POST['nb_personne'];
$nb_chambre = $_POST['nb_chambre'];
$adresse = $_POST['adresse'];



$formulaire = array (
    'Nom' => $nom,
    'Nombre de personnes' => $nb_personne,
    'Nombre de chambre' => $nb_chambre,
    'Adresse' => $adresse,
);

?>
<p><?=$formulaire['Nom']?></p>
<p><?=$formulaire['Nombre de personnes']?></p>
<p><?=$formulaire['Nombre de chambre']?></p>
<p><?=$formulaire['Adresse']?></p>

<?php

if(isset($_POST['plage']))  echo "Plage<br/>";
if(isset($_POST['luxe']))  echo "Luxe<br/>";
if(isset($_POST['montagne']))  echo "Montagne<br/>";
if(isset($_POST['resto']))  echo "Restaurant<br/>";
if(isset($_POST['piscine']))  echo "Piscine<br/>";
if(isset($_POST['sport']))  echo "Salle de sport<br/>";


?>
<form>
<input type="submit" name="valider" value="Valider">
</form>