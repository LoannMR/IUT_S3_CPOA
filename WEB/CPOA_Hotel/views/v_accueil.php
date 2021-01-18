<?php
//  En tête de page
?>
<?php require_once(PATH_VIEWS.'header.php');?>

<!--  Zone message d'alerte -->
<?php require_once(PATH_VIEWS.'alert.php');?>

<!--  Choix -->

    <div id="container">    
            <!-- zone de connexion -->
            
            <form action="index.php?page=verification.php" method="POST">
                <h3>Veuillez vous identifier</h3>
                <input type="text" placeholder="Nom d'utilisateur" name="username" required>
                <input type="password" placeholder="Mot de passe" name="password" required>
                <input type="submit" id='submit' value='Connexion' >
            </form>
    </div>


<a href ="index.php?page=choix&statut=staff"><button >Staff Reservateur</button></a>
<a href ="index.php?page=choix&statut=gerant"><button >Gérant</button></a>

<!--  Pied de page -->
<?php require_once(PATH_VIEWS.'footer.php'); 