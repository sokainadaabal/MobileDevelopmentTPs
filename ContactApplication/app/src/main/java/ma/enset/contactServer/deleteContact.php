<?php
  include "functionBD.php";
  try
    {
     
    // établire la connection avec la base de donnée.
        $cnx=connexionPDO();
    // réccuperer l'identifiant de contact  que voulent supprimer
        $data =$_GET['id'];
        $req= $cnx->prepare( "DELETE FROM contactmodel  where id=". $data );

        if ($req->execute()){
            echo "Le contact est supprimer avec succes";
        }
        else{
            echo "Erreur";
        }
    }catch(PDOException $e){
        print "Erreur ! ".$e->getMessage();
        die();
    }

?>