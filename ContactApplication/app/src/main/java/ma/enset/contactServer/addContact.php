<?php
    // initilaition des parametres
    $first_name =$_POST["first_name"];
    $last_name =$_POST["last_name"];
    $job =$_POST["job"];
    $email =$_POST["email"];
    $phone =$_POST["phone"];
    $photo=$_POST["photo"];
    // Se connecter à la base de données
    $conn = new mysqli("localhost", "root", "", "contact");

    // Vérifier si la connexion à la base de données a réussi
    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }

    // Préparer la requête SQL pour insérer un nouveau contact
    $stmt ="INSERT INTO contactmodel (first_name,last_name,job,email, phone, photo) VALUES ('$first_name','$last_name','$job','$email','$phone','$photo')";
    $result=$conn->query($stmt);
    $resultJson["result"]=$result;
    echo json_encode($resultJson);
    // Fermer la connexion à la base de données

    $conn->close();

?>
