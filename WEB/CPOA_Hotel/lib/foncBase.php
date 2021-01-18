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

      //Pour cocher les cases
      $toCheck = false;
      if(!empty($_SESSION['donneeFormulaire']))
      {
        //On récupère la variable qui stock les caractéristiques cochées
        foreach($_SESSION['donneeFormulaire']['caracteristique'] as $check)
        {
            if($min == $check)
              $toCheck = true;
            
            break;
        }
      }
      //ajout des checkboxs
        ?>
        <label><?=($mot == 'Sport')?'Salle de Sport' : $mot ?>
            <input type="checkbox" name="<?= $min ?>" <?= ($toCheck == true)? 'checked' : 'unchecked' ?> >
        </label>
        <?php
        
    }
}

function validate()
{
  $erreur = null;
  if($_SESSION['statut']=='gerant')
    {
      if(empty($_POST['nom']) || empty($_POST['nb_chambre']) || empty($_POST['classification']))
        $erreur = "Tous les champs doivent être remplis hormis les caractéristiques !";
    }
    //Si formulaire reservation
    if($_SESSION['statut']=='vip')
    {
      if(empty($_POST['nom']) || empty($_POST['prenom']) || empty($_POST['nb_personne']) || empty($_POST['nb_chambre']) || empty($_POST['type']) || empty($_POST['d_arr']) || empty($_POST['d_dep'])){

        if(!isset($_SESSION["donneeFormulaire"]))
        $erreur = "Tous les champs doivent être remplis hormis les caractéristiques !";
      }
    }

  return $erreur;
}
