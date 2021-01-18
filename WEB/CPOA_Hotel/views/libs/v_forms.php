<?php
/** Création des formulaires
 * Soit pour un Gerant ou un staff
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
  <form action=<?=isset($desactivate)? 'index.php?page=exec' : 'index.php?page=check'?> method="post">
    
    <fieldset class="form" <?=isset($desactivate)?  'disabled' : 'enabled'?>>
      <label> Nom : 
      <input type="text" name="nom" placeholder="Nom" value=<?=isset($donneeFormulaire['nom'])? $donneeFormulaire['nom'] : ''?>> </label> </br>
      <label> Nombre de chambre :
      <input type="number" min="1" name="nb_chambre" value=<?=isset($donneeFormulaire['nb_chambre'])? $donneeFormulaire['nb_chambre'] : 1?>> </label> </br>
      <label> Classification :
      <input type="number" min="0" max="5" name="classification" value=<?=isset($donneeFormulaire['classification'])? $donneeFormulaire['classification'] : 0?>> </label> </br> </br>
      <fieldset>
        <label> Caracteristiques de l'hotel </br>
        <?php createCheckboxs($listCaracteristique)?> </label>
      </fieldset>

    </fieldset>

    <input type="submit" name="valider" value="Valider">
  </form>

  <?php
}
else
{ ?>
  <!-- Reservation-->
  <form action=<?=isset($desactivate)? 'index.php?page=exec' : 'index.php?page=searchHotel'?> method="post">
    <input type="hidden" name="idHotel" value=<?=isset($hotel)? $hotel[0]["idHotel"] : ''?>>
    <fieldset class="form" <?=isset($desactivate)?  'disabled' : 'enabled'?>>
      <label> Nom :
      <input type="text" name="nom" placeholder="Nom" value=<?=isset($_SESSION['donneeFormulaire']['nom'])? $_SESSION['donneeFormulaire']['nom'] : ''?>> </label> </br>
      <label> Prenom :
      <input type="text" name="prenom" placeholder="Prénom" value=<?=isset($_SESSION['donneeFormulaire']['prenom'])? $_SESSION['donneeFormulaire']['prenom'] : ''?>> </label> </br>
      <label> Nombre de personne :
      <input type="number" min="1" name="nb_personne" value=<?=isset($_SESSION['donneeFormulaire']['nb_personne'])? $_SESSION['donneeFormulaire']['nb_personne'] : 1?>> </label> </br>
      <label> Nombre de chambres :
      <input type="number" min="1" name="nb_chambre" value=<?=isset($_SESSION['donneeFormulaire']['nb_chambre'])? $_SESSION['donneeFormulaire']['nb_chambre'] : 0?>> </label> </br>

      <label>Type de personnes :</label>
      <!--La selection -->
      <?php
      if(isset($_SESSION['donneeFormulaire']['type']))
      {
        if($_SESSION['donneeFormulaire']['type'] == "staff")
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
          <option value="vip">VIP</option> 
          <option value="jury">Jury</option>
        </select>
        <?php
      } ?>
      </br>
      <label> Date d'arrivée :
      <input type="date" name="d_arr" value=<?=isset($_SESSION['donneeFormulaire']['d_arr'])? $_SESSION['donneeFormulaire']['d_arr'] : ''?>> </label> </br>
      <label> Date de départ:
      <input type="date" name="d_dep" value=<?=isset($_SESSION['donneeFormulaire']['d_dep'])? $_SESSION['donneeFormulaire']['d_dep'] : ''?>> </label> </br> </br>

      <fieldset>
        <label> Caractéristique voulue </br>
        <?php createCheckboxs($listCaracteristique)?> </label>
        <?php 
          if(isset($desactivate)){
        ?>
            <input type="hidden" name="idHotel" value=<?=isset($hotel)? $hotel[0]["idHotel"] : ''?>>
            <label>Hotel <input type="text" name="nomHotel" value=<?=isset($hotel)? $hotel[0]["nomHotel"] : ''?>> </label>
        <?php
          }
        ?>
      </fieldset>

    </fieldset>

    <input type="submit" name="valider" value="Valider">
  </form>
<?php
}




