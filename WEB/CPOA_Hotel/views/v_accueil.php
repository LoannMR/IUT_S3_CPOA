<?php
//  En tête de page
?>
<?php require_once(PATH_VIEWS.'header.php');?>

<!--  Zone message d'alerte -->
<?php require_once(PATH_VIEWS.'alert.php');?>

<!--  Choix -->

<div id="container">
            <!-- zone de connexion -->
            
            <form action="" method="POST">
                <h2>Connexion</h2>
                
                
                <input type="text" placeholder="Nom d'utilisateur" name="username" required>

               
                <input type="password" placeholder="Mot de passe" name="password" required>

                <input type="submit" id='submit' value='Connexion' >
                
            </form>
        </div>


<a href ="index.php?page=choix&statut=vip"><button >VIP</button></a>
<a href ="index.php?page=choix&statut=gerant"><button >Gérant</button></a>

<!--  Pied de page -->
<?php require_once(PATH_VIEWS.'footer.php'); 