<?php
   $con=mysqli_connect("localhost","root","root","workshop_barbershop");

   if (mysqli_connect_errno($con)) {
      echo "Failed to connect to MySQL: " . mysqli_connect_error();
   }

   $email = $_POST["email"];
   $password = $_POST["password"];
   $dateCreated = date('2020/08/10');
   $name = $_POST["name"];
   $firstName = $_POST["firtName"];
   $number = $_POST["number"];
   // INSERT INTO `user`(`email`,`roles`,`password`, `date_created`, `name`, `first_name`, `number`) VALUES ("email",'[]',"password",'2020/12/23',"Doudou","adel",0611151380)
   $mysql_qry= "INSERT INTO user(email,roles,password, date_created, name, first_name, number) VALUES ('$email','[]','$password','$dateCreated','$name','$firstName','$number')";

   if($con->query($mysql_qry) === true){
      echo "Insert success";
   } else{
      echo "Error: ". $mysql_qry ."<br>". $con->error;
   }

   $con->close();
