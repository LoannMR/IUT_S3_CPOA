<?php
require_once(PATH_LIB.'foncBase.php');
  
//Header
require_once(PATH_VIEWS.'header.php');

//Contenu
switch($_SESSION['statut'])
{
  case 'gerant':
    ?> <h2>Creer un hotel </h2> <?php
    break;
    case 'staff':
      ?> <h2>Faire une reservation </h2> <?php
      break;
}
require_once('libs/v_forms.php');

//Footer
require_once(PATH_VIEWS.'footer.php');
?>