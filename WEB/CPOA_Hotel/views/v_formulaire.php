<form action="index.php?page=check_formulaire" method="post">
  
  <input type="text" name="nom" placeholder="Nom de l'hôtel">
  <input type="text" name="nb_personne" placeholder="Nombre de personne">
  <input type="text" name="nb_chambre" placeholder="Nombre de chambre">
  <input type="text" name="adresse" placeholder="Adresse de l'hôtel">

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