<?php
require_once(PATH_LIB.'foncBase.php');
  
//Header
require_once(PATH_VIEWS.'header.php');

//Contenu
switch($_SESSION['statut'])
{
  case 'gerant':
    if(isset($_GET['hotel']))
    {
      ?> <h2>Modifier un hotel </h2> <?php
    }
    else
    {
      ?> <h2>Creer un hotel </h2> <?php
    }
      
    break;
    case 'staff':
      if(isset($_GET['reservation']))
    {
      ?> <h2>Modifier une réservation </h2> <?php
    }
    else
    {
      ?> <h2>Faire une réservation </h2> <?php
    }
      break;
}
require_once('libs/v_forms.php');

//Footer
require_once(PATH_VIEWS.'footer.php');
?>