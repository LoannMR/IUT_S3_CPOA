<?php
  
//Header
require_once(PATH_VIEWS.'header.php');

//Contenu
?><h2> Ajout Réussi ! </h2> <?php
echo $_SESSION['donneeFormulaire']['nom'];

//Footer
require_once(PATH_VIEWS.'footer.php');
?>