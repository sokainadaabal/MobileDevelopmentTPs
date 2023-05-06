<?php
 

     // Se connecter à la base de données  
    $conn = new mysqli("localhost", "root", "", "contact");

     // Importer les paramétres
    $id=$_POST["id"];
    $first_name =$_POST["first_name"];
    $last_name =$_POST["last_name"];
    $job =$_POST["job"];
    $email =$_POST["email"];
    $phone =$_POST["phone"];
    $photo=$_POST["photo"];

    // Préparer la requête SQL pour insérer un nouveau contact
   
    $stmt ="UPDATE  contactmodel SET first_name='$first_name',last_name='$last_name',job='$job',email='$email',phone='$phone' WHERE id=$id";
    $result=$conn->query($stmt);
    $resultJson["result"]=$result;
    echo json_encode($resultJson);
        
    // Fermer la connexion à la base de données

    $conn->close();

?>