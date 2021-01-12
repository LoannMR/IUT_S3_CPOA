<form action="" method="post">
  <input id="POST-name" type="text" name="name" placeholder="Nom">
</form>

<form action="" method="post">
  <input id="POST-name" type="text" name="name" placeholder="Prénom">
</form>

<form action="" method="post">
  <input id="POST-name" type="text" name="name" placeholder="Nombre de personne">
</form>

<label for="Type">Type :</label>

<select name="Type" id="Type">
    <option value="">--selectionnez une catégorie--</option>
    <option value="dog">Jury</option>
    <option value="dog">VIP</option>
  </select>

<form action="" method="post">
  <label for="POST-name">Date séjour</label>
  <input id="POST-name" type="text" name="name">
</form>

<form action="" method="post">
  <label for="POST-name">Durée</label>
  <input id="POST-name" type="text" name="name">
</form>

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
