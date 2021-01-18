<?php
  
//Header
require_once(PATH_VIEWS.'header.php');

//Contenu
?><h2> Ajout Réussi ! </h2> 
<form action='index.php?page=choix' method="post">
	<input type="submit" name="valider" value="Retour">
</form>
<?php


//Footer
require_once(PATH_VIEWS.'footer.php');
?>