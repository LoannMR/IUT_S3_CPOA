<?php
/** Création des formulaires
 * Soit pour un Gerant ou un VIP
 */


require_once(PATH_LIB.'foncBase.php');
if($_SESSION['statut'] == "gerant")
{ ?>
  <!-- Hotel-->
  <form action="index.php?page=check_formulaire" method="post">
    
    <input type="text" name="nom" placeholder="Nom de l'hôtel">
    <input type="text" name="nb_personne" placeholder="Nombre de personne">
    <input type="text" name="nb_chambre" placeholder="Nombre de chambre">
    <input type="text" name="adresse" placeholder="Adresse de l'hôtel">

    <fieldset>
      <?php createCheckboxs($listCaracteristique)?>
    </fieldset>

    <input type="submit" name="valider">
  </form>

  <?php
}
else
{ ?>
  <!-- Reservation-->
  <form action="index.php?page=check_formulaire" method="post">

    <input type="text" name="nom" placeholder="Nom">
    <input type="text" name="prenom" placeholder="Prénom">
    <input type="text" name="nb_personne" placeholder="Nombre de personne">
    <input type="text" name="nb_chambre" placeholder="Nombre de chambre">

    <label>Type :</label>

    <select name="Type">
        <option value="">--selectionnez une catégorie--</option>
        <option value="jury">Jury</option>
        <option value="vip">VIP</option>
    </select>

    <input type="text" name="d_arr" placeholder="Date d'arrivée (jj/mm/aaaa)">
    <input type="text" name="d_dep" placeholder="Date de départ (jj/mm/aaaa)">

    <fieldset>
      <?php createCheckboxs($listCaracteristique)?>
    </fieldset>

    <input type="submit" name="valider">
  </form>
<?php
}




