<?php
	require("password.php");
	
	$con = mysqli_connect("localhost","id2466579_dbroot", "dbroot", "id2466579_easytalkdb");
	$username = $_POST["username"];
	$password = $_POST["password"];
	
	$statementCheck = mysqli_prepare($con, "SELECT * FROM user WHERE username = ?"); 					//query for find already existing user
    mysqli_stmt_bind_param($statementCheck, "s", $username);
    mysqli_stmt_execute($statementCheck);
    mysqli_stmt_store_result($statementCheck);
    $count = mysqli_stmt_num_rows($statementCheck);														//count the rows if there is 1 row with the username -> the user already exist
    mysqli_stmt_close($statementCheck);
        if ($count < 1){																				//if the user not found in the database create the new user																	
			$passwordhash = password_hash($password, PASSWORD_DEFAULT);									//hash the password for protection
            $statement = mysqli_prepare($con, "INSERT INTO user (Username, Password) VALUES (?,?)");
			mysqli_stmt_bind_param($statement, "ss", $username, $passwordhash);
			mysqli_stmt_execute($statement);
			$response = array();
			$response["success"] = true;																//set the success true
			echo json_encode($response);																//encode the array in a JSON Object
			mysqli_close($con);
        }else {																							//else the user already exist dont create a new user and set the success to false
			$response = array();
			$response["success"] = false;
			echo json_encode($response);
			mysqli_close($con);
        }
	
?>