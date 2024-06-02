<?php
include 'connection.php'; // Ensure you have the database connection
require("validate.php");

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Check if the 'username' and 'password' keys are set in $_GET
    if (isset($_POST['username'], $_POST['password'])) {
        $username = validate($_POST['username']);
        $password = validate($_POST['password']);

        // setting up the cookie to retrieve it in any other pages we want
        setcookie("varus_name", $username, time() + 3600);

        // Perform your login verification against the database
        // Example: Check if username and password match a record in the database
        // IMPORTANT: Use prepared statements for security

        // Example query (replace with your actual query and connection code)
        $sql = "SELECT * FROM admintable WHERE adminid='$username' AND password='$password'";
        $result = $conn->query($sql);

        if ($result->num_rows > 0) {
            // Login successful
            $response = array('success' => true);
            echo json_encode($response);
        } else {
            // Login failed
            $response = array('success' => false);
            echo json_encode($response);
        }
    } else {
        // Handle the case when 'username' or 'password' is not set in $_GET
        $response = array('success' => false, 'error' => 'Username or password not provided');
        echo json_encode($response);
    }
}
?>
