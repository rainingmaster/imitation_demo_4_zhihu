<?php
	if (isset($_POST["title"]) && isset($_POST["content"])) {
		$title = $_POST["title"]);
		$content = $_POST["content"]);
		
		$result = $tilte.$content;
		echo $resutl;
		exit;
	}
	else
		echo "false";
?>