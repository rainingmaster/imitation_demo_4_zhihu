<?php
	require_once($_SERVER['DOCUMENT_ROOT']."/conn/conn.php");
	
	if(isset($_POST["title"], $_POST["content"])){
		$title = $_POST["title"];//БъЬт
		$content = $_POST["content"];//ФкШн
		
		$query ="INSERT INTO question(title,content) VALUES('".$title."','".$content."');";
		$result = mysql_query($query);            
		if (!$result){
			echo"Error!";
		}else{
			echo"Success!"; 
		}
		exit;
	}
	else
		echo false;
?>