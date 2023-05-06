<?php
  include "functionBD.php";
    try{
        $cnx=connexionPDO();
        $req= $cnx->prepare("select *from contactmodel order by first_name");
        $req->execute();
        $array=array();
        while ($raw=$req->fetch(PDO::FETCH_ASSOC))
        {
            array_push($array,$raw);
        }
        echo json_encode($array);
    

    }catch(PDOException $e){
        print "Erreur ! ".$e->getMessage();
        die();
    }

  ?>
