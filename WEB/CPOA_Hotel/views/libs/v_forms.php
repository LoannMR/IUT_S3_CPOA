<?php
/** Création des formulaires
 * Soit pour un Gerant ou un VIP
 */
require_once(PATH_LIB.'foncBase.php');

//Désactivation des formulaire si demandé
if(isset(($_POST["desactivate"])))
{
  $desactivate = true ;
}

//Variables à récupérer :
$nom;
//Hotel
$nb_personne;
$nb_chambre;
$adresse;
//checkboxs --> conf config;
//Reservation
$prenom;
$nb_personne;
$nb_chambre;
$type;
$d_arr;
$d_dep;
//checkboxs --> conf config;


$type = "vip";

if($_SESSION['statut'] == "gerant")
{ ?>
  <!-- Hotel-->
  <form action="index.php?page=check" method="post">
    
    <fieldset class="form" <?=isset($desactivate)?  'disabled' : 'enabled'?>>
      <label> Nom : 
      <input type="text" name="nom" placeholder="Nom" value=<?=isset($donneeFormulaire['nom'])? $donneeFormulaire['nom'] : ''?>> </label> </br>
      <label> Nombre de personne :
      <input type="number" min="1" name="nb_personne" placeholder="1" value=<?=isset($donneeFormulaire['nb_personne'])? $donneeFormulaire['nb_personne'] : ''?>> </label> </br>
      <label> Nombre de chambre :
      <input type="number" min="1" name="nb_chambre" placeholder="1" value=<?=isset($donneeFormulaire['nb_chambre'])? $donneeFormulaire['nb_chambre'] : ''?>> </label> </br>
      <label> Localisation :
      <input type="text" name="adresse" placeholder="Adresse" value=<?=isset($donneeFormulaire['adresse'])? $donneeFormulaire['adresse'] : ''?>> </label> </br> </br>
      <fieldset>
        <label> Caracteristiques de l'hotel </br>
        <?php createCheckboxs($listCaracteristique)?> </label>
      </fieldset>

    </fieldset>

    <input type="submit" name="valider">
  </form>

  <?php
}
else
{ ?>
  <!-- Reservation-->
  <form action="index.php?page=check" method="post">

    <fieldset class="form" <?=isset($desactivate)?  'disabled' : 'enabled'?>>
      <label> Nom :
      <input type="text" name="nom" placeholder="Nom" value=<?=isset($donneeFormulaire['nom'])? $donneeFormulaire['nom'] : ''?>> </label> </br>
      <label> Prenom :
      <input type="text" name="prenom" placeholder="Prénom" value=<?=isset($donneeFormulaire['prenom'])? $donneeFormulaire['prenom'] : ''?>> </label> </br>
      <label> Nombre de personne :
      <input type="number" min="1" name="nb_personne" placeholder="1" value=<?=isset($donneeFormulaire['nb_personne'])? $donneeFormulaire['nb_personne'] : ''?>> </label> </br>
      <label> Nombre de chambres :
      <input type="number" min="1" name="nb_chambre" placeholder="1" value=<?=isset($donneeFormulaire['nb_chambre'])? $donneeFormulaire['nb_chambre'] : ''?>> </label> </br>

      <label>Type de personnes :</label>
      <!--La selection -->
      <?php
      if(isset($type))
      {
        if($type == "vip")
        {
          ?>
          <select name="type">
            <option value="vip">VIP</option> 
            <option value="jury">Jury</option>
          </select>
          <?php
        }else
        {
          ?>
          <select name="type">
            <option value="vip">Jury</option> 
            <option value="jury">VIP</option>
          </select>
          <?php
        }
      }else
      {
        ?>
        <select name="type">
          <option value="jury">Jury</option> 
          <option value="vip">VIP</option>
        </select>
        <?php
      } ?>
      </br>
      <label> Date d'arrivée :
      <input type="date" name="d_arr" value=<?=isset($donneeFormulaire['d_arr'])? $donneeFormulaire['d_arr'] : ''?>> </label> </br>
      <label> Date de départ:
      <input type="date" name="d_dep" value=<?=isset($donneeFormulaire['d_dep'])? $donneeFormulaire['d_dep'] : ''?>> </label> </br> </br>

      <fieldset>
        <label> Caractéristique voulue </br>
        <?php createCheckboxs($listCaracteristique)?> </label>
      </fieldset>

    </fieldset>

    <input type="submit" name="valider">
  </form>
<?php
}




