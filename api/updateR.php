<?php

// Assuming you have a MySQL database connection
require("connection.php");

// Get parameters from the request
$roomid = $_POST["roomid"];
$dttm = $_POST["dttm"];
$status = $_POST["status"];

// Perform the update query
$sql = "UPDATE servicestore SET status = '$status' WHERE roomid = '$roomid' and datetime='$dttm'";

if ($conn->query($sql) === TRUE) {
    echo "Update successful";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

// Close the database connection
$conn->close();

?>
