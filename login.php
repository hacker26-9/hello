<html>
<head>
<title>My Website</title>
</head>
<body>
<h1>Welcome to my classes</h1>
<form method="post" >
Name: <input type="text" name="name">
Email:<input type="text" name="mail">
Password: <input type="password" name="pass">
Submit: <input type="submit" name="sb">
</form>
<?php
$con = mysqli_connect('localhost' , 'root','', 'sachin');
if(!$con)
{ die("Connection failed:
".mysqli_connect_error());
}
if(isset($_POST['sb']))
{
$name = $_POST['name'];
$email = $_POST['mail'];
$password = $_POST['pass'];
$query = "INSERT INTO student(name, email, password) VALUES
('$name','$email', '$password')";
$execute = mysqli_query($con,$query);
}
?>
</body>
</html>