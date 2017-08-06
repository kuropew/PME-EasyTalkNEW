<?php
	$con = mysqli_connect("localhost","id2466579_dbroot", "dbroot", "id2466579_easytalkdb");
	
	$user_id = $_POST["user_id"];
	$usernametmp="";
	$statement = mysqli_prepare($con, "SELECT * FROM messages WHERE users_id NOT LIKE ?");		//get the latest message which is not from the signed in user
	mysqli_stmt_bind_param($statement, "i", $user_id);
	mysqli_stmt_execute($statement);
	
	mysqli_stmt_store_result($statement);
	mysqli_stmt_bind_result($statement, $message_id, $message, $user_id, $timestamp);
	$response = array();
	$response["success"] = false;
	while(mysqli_stmt_fetch($statement))														//write the message, message_id and user_id in the response array and set the success to true
	{
		$response["success"] = true;
		$response["message"] = $message;
		$response["message_id"] = $message_id;
		$response["user_id"] = $user_id;

	}
	mysqli_stmt_close($statement);
	$statement2 = mysqli_prepare($con, "SELECT username FROM user WHERE user_id = ?");			//get the username from the latest message 
	mysqli_stmt_bind_param($statement2, "i", $response["user_id"]);
	mysqli_stmt_execute($statement2);
	mysqli_stmt_store_result($statement2);
	mysqli_stmt_bind_result($statement2, $username);
	while(mysqli_stmt_fetch($statement2))														//write the username to the array
	{
		$response["username"] = $username;
	}
	echo json_encode($response);																//encode the response array to a JSON Object
?>