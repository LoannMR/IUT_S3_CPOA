<?php
//Pour les alertes
function choixAlert($message)
{
  $alert = array();
  switch($message)
  {
    case 'query' :
      $alert['messageAlert'] = ERREUR_QUERY;
      break;
    case 'url_non_valide' :
      $alert['messageAlert'] = TEXTE_PAGE_404;
      break;
    default :
      $alert['messageAlert'] = MESSAGE_ERREUR;
  }
  return $alert;
}

function createCheckboxs($array)
{
    foreach($array as $mot)
    {
      //On met tout en minuscule
      $min = strtolower($mot);

        ?>
        <label><?=($mot == 'Sport')?'Salle de Sport' : $mot ?>
            <input type="checkbox" name="<?= $min ?>">
        </label>
        <?php
    }
}
