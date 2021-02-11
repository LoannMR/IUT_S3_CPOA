<?php
require_once(PATH_LIB.'foncBase.php');
  
//Header
require_once(PATH_VIEWS.'header.php');

?>
<h2>choix de l hotel</h2>
  <form action="index.php?page=check" method="post">
		<label> liste des hotels
            <select name="hotel">
              <?php
                if(isset($hotels)){
                  foreach ($hotels as $h) {
                    ?>
                      <option value="<?=$h["nomHotel"]?>"><?=$h["nomHotel"]?></option>
                    <?php
                  }
                }
              ?>
            </select>
        </label>
        <input type="submit" name="Valider" value="Valider">
    </form>
<?php

//Footer
require_once(PATH_VIEWS.'footer.php');
?>