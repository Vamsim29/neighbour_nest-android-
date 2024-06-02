<?php
// Include the database connection file (replace with your actual database connection code)
include 'connection.php';
require("validate.php");

// Check if the request is a POST request
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Get the JSON data from the request body
    $json_data = file_get_contents("php://input");

    // Parse the JSON data into an associative array
    $data = json_decode($json_data, true);
    date_default_timezone_set('Asia/Kolkata');
    $orderstatus = "booked";
    $myDate = date('Y/m/d');
    $myTime = date('H:i:s');
    $datetime = $myDate . " " . $myTime;

    // Check if the JSON data was successfully parsed
    if ($data !== null) {
        // Extract data from the JSON
        $field0 = $data['field0'];
        $field1 = $data['field1'];
        $field2 = $data['field2'];
        $field3 = $data['field3'];
        $field4 = "booked";
        $field5 = $datetime;
        // Perform the operation to add the store service request to the database
        $sql = "INSERT INTO servicestore (roomid, name, mblno, service, status, datetime)
                VALUES ('$field0', '$field1', '$field2', '$field3', '$field4', '$field5')";
        $stmt=mysqli_query($conn, $sql);
        echo json_encode(array("status"=> "success","message"=> "Done Successfully"));

        
    } else {
        $response = array('message' => 'Invalid JSON data');
        echo json_encode($response);
        
    }
} else {
    $response = array('message' => 'Invalid request method');
    echo json_encode($response);
}
?>
