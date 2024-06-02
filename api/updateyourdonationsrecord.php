<?php

// Assuming you have a MySQL database connection
require("connection.php");
require("validate.php");

// Get parameters from the request
$roomid = $_POST["roomid"];
$status = $_POST["status"];
$dttm = $_POST["dttm"];

// Perform the update query
$sql = "UPDATE donations SET status = '$status' WHERE roomid = '$roomid' and datatime='$dttm'";

if ($conn->query($sql) === TRUE) {
    echo "Update successful";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

// Close the database connection
$conn->close();

?>
