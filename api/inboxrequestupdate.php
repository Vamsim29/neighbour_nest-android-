<?php

// Assuming you have a MySQL database connection
require("connection.php");

// Get parameters from the request
$roomid = $_POST["roomid"];
$dttm = $_POST["dttm"];
$status = $_POST["status"];
$userid=$_POST["userid"];
$usercon=$_POST["userphno"];

// Perform the update query
$sql = "UPDATE requests SET status = '$status',accbyroomid='$userid',accbymobno='$usercon' WHERE roomid = '$roomid' and datatime='$dttm'";

if ($conn->query($sql) === TRUE) {
    echo "Update successful";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

// Close the database connection
$conn->close();

?>
