<?php
	$con = mysqli_connect("localhost","id2466579_dbroot", "dbroot", "id2466579_easytalkdb");
	
	$username = $_POST["username"];
	$message = $_POST["message"];
	$user_id = $_POST["user_id"];
	
	
	$statement = mysqli_prepare($con, "INSERT INTO messages (message, users_id) VALUES (?,?)");					//write the send message into the table in the database
	mysqli_stmt_bind_param($statement, "si", $message, $user_id);
	mysqli_stmt_execute($statement);
	
	$response = array();
	$response["success"] = false;																				//set success default to false maybe the database wont response

	$statementResponse = mysqli_prepare($con, "SELECT * FROM messages WHERE users_id = ?");						//getting the new message data from the database
	mysqli_stmt_bind_param($statementResponse, "i", $user_id);
	mysqli_stmt_execute($statementResponse);
	
	mysqli_stmt_store_result($statementResponse);
	mysqli_stmt_bind_result($statementResponse, $message_id, $message, $user_id, $timestamp);
	
	while(mysqli_stmt_fetch($statementResponse))																//write the new data in the array response and set the success on true
	{
		$response["success"] = true;
		$response["user_id"] = $user_id;
		$response["username"] = $username;
		$response["timestamp"] = $timestamp;
		$response["message"] = $message;
	}


	echo json_encode($response);																				//encode the array to a JSON Object
	
	mysqli_close($con);
?>