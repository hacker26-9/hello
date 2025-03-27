<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Login Form</h2>
    <form method="post" action="welcome.php">
        Username: <input type="text" name="Username" required><br>
        Password: <input type="password" name="password" required><br>
        <input type="submit" name="submitBtn" value="Login">
    </form>

    <?php
    if (isset($_POST["submitBtn"])) {
        $con = mysqli_connect("localhost", "root", "", "college1");

        if (!$con) {
            die("Connection failed: " . mysqli_connect_error());
        }

        $Username = $_POST["Username"];
        $password = $_POST["password"];

        $query = "SELECT * FROM student_data WHERE username='$Username' AND password='$password'";
        $result = mysqli_query($con, $query);

        if ($result) {
            if (mysqli_num_rows($result) == 1) {
                echo "Login successful!";
            } else {
                echo "Invalid username or password.";
            }
        } else {
            echo "Query failed: " . mysqli_error($con);
        }

        mysqli_close($con);
    }
    ?>
</body>
</html>
