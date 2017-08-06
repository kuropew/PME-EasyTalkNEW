<?php
	require("password.php");																			//need to encrypt the password
	
	
	$con = mysqli_connect("localhost","id2466579_dbroot", "dbroot", "id2466579_easytalkdb");
	$username = $_POST["username"];																		//getting username from volley request(app)
	$password = $_POST["password"];																		//getting password from volley request(app)
	
	$statement = mysqli_prepare($con, "SELECT * FROM user WHERE Username = ?");							//prepare query for the msql database
	mysqli_stmt_bind_param($statement, "s", $username);													//bind the params to the query
	mysqli_stmt_execute($statement);
	
	mysqli_stmt_store_result($statement);
	mysqli_stmt_bind_result($statement, $username, $passwordhash, $user_id);							//bind results for use in fetch
	
	$response = array();					
	$response["success"] = false;																		//add the boolean variable for success to the array
	
	while(mysqli_stmt_fetch($statement))																//get the data from the database to variables in the response array
	{
		if(password_verify($password, $passwordhash)){
			$response["success"] = true;
			$response["username"] = $username;
			//$response["password"] = $password;
			$response["user_id"] = $user_id;
		}
	}

	echo json_encode($response);																		//encode the array in a JSON Object for the app to use							
    mysqli_close($con);																					//close the connection to the database
?>