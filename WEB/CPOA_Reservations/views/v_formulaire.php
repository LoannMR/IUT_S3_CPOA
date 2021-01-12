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
    <legend>Caractéristique</legend>
    <label>Plage<input type="checkbox" name="plage"> </label>
    <label>Luxe<input type="checkbox" name="luxe"> </label>
    <label>Montagne<input type="checkbox" name="montagne"> </label>
    <label>Restaurant<input type="checkbox" name="resto"> </label>
    <label>Piscine<input type="checkbox" name="piscine"> </label>
    <label>Salle de sport<input type="checkbox" name="sport"> </label>
  </fieldset>

<input type="submit" name="valider">
</form>
