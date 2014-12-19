<?php
    /**
    *quiz_service.php
    *提问服务接口
    **/
    
    require_once($_SERVER['DOCUMENT_ROOT']."/log/log.class.php");
	require_once($_SERVER['DOCUMENT_ROOT']."/conn/conn.php");
    require_once($_SERVER['DOCUMENT_ROOT']."/model/question.php");
    require_once($_SERVER['DOCUMENT_ROOT']."/dao/question_dao.php");
    
    $log = Log::get_instance();
    $tag = "quiz_server report";
    
	if(isset($_POST["title"], $_POST["content"])){
		$title = $_POST["title"];//标题
		$content = $_POST["content"];//内容
		
		$new_question = new question($title, $content, "11", date('Y-n-j H:m:s'), true);
        $my_question_dao = new question_dao();
        $result = $my_question_dao->insert($new_question);
        
		if (!$result){
            $log->setError("insert into question is wrong", $tag);
			echo "Error!";
		}else{
			echo "Success!";
		}
		exit;
	}
    
    $log->setError("title or content is undefine", $tag);
    echo false;