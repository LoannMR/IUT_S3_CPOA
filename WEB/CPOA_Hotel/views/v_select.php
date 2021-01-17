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
    <h2>Listes des hotels</h2>
    <?php
    foreach($hotels as $hotel)
    {
        ?>
        <div class="listes">
            <h3><?=$hotel['nomHotel']?></h3>  </br> 
             
            <p> Nombre de chambres : <?= $hotel['nbChambre']?> </br>
                Classification : <?= $hotel['classification']?> </br>
                Caracteristiques : <?=$hotel['caracteristique']?> </br>
            </p>
        </div>
        <?php
        
    }
    ?>
    <button>Retour</button>

    <?php
    //Footer
    require_once(PATH_VIEWS.'footer.php');