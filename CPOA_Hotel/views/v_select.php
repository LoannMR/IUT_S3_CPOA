<?php

/**
     * Afficher les informations qu'il demande
     * Hotel en fonction des caractéristiques / types = hotelDisponible
     * Modifier Réservations = mesReservations
     * Modifier Hotels = mesHotels
     */

    //Header
    require_once(PATH_VIEWS.'header.php');

    //Contenu
    ?>
    
    
    <?php
    if($_SESSION['statut'] == "gerant"){
        ?>
        <h2>Listes des hotels</h2>
        <?php
        foreach($hotels as $hotel)
        {
            ?>
            <a href="index.php?page=formulaire&hotel=<?=$hotel['idHotel']?>">
            <div class="objet">
                <h3><?=$hotel['nomHotel']?></h3>  </br> 
                 
                <p> Nombre de chambres : <?= $hotel['nbChambre']?> </br>
                    Classification : <?= $hotel['classification']?> </br>
                    Caracteristiques : <?=$hotel['caracteristique']?> </br>
                </p>
            </div>
            </a>
            <?php
            
        }
    }
    else{
        ?>
        <h2>Listes des reservations</h2>
        <?php
        foreach($reservations as $reserv)
        {
            ?>
            <a href="index.php?page=formulaire&reservation=<?=$reserv['idReservation']?>">
            <div class="objet">
                <h3><?=$reserv['nom']?> <?=$reserv['prenom']?></h3>  </br> 
                 
                <p> Nombre de chambres : <?= $reserv['nbChambre']?> </br>
                    Type de personnes : <?= $reserv['type']?> </br>
                    date d'arrivé : <?=$reserv['date_debut']?> </br>
                    date de départ : <?=$reserv['date_fin']?> </br>
                    Hotel : 
                    <?php
                        foreach ($hotels as $key => $h) {
                            if($h['idHotel'] == $reserv['idHotel']){
                                ?>
                                <?=$h['nomHotel']?>
                                <?php
                            }
                        }
                    ?>
                </p>
            </div>
            </a>
            <?php
            
        }
    }
    
    ?>

    <?php
    //Footer
    require_once(PATH_VIEWS.'footer.php');