<form action="index.php?page=check_formulaire" method="post">
  <label for="POST-name">Nom :</label>
  <input type="text" name="nom">

  <label for="POST-name">Nombre de personnes</label>
  <input type="text" name="nb_personne">

  <label for="POST-name">nombre de chambre</label>
  <input type="text" name="nb_chambre">

  <label for="POST-name">Adresse</label>
  <input type="text" name="adresse">

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