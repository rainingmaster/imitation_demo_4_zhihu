<?php
    /**
    *submit_quiz_service.php
    *提交提问服务接口
    *必要值:title,问题标题；content,问题的内容；user_id,提问者id
    *可选值:anonymous_tag,匿名标示
    **/
    header("Access-Control-Allow-Origin:*");//启用CORS，允许跨站访问
    header("Content-Type:text/html;charset=utf-8");
    
    require_once($_SERVER['DOCUMENT_ROOT']."/log/log.class.php");
    require_once($_SERVER['DOCUMENT_ROOT']."/dao/question_dao.php");
    require_once($_SERVER['DOCUMENT_ROOT']."/model/question.php");
    
    $log = Log::get_instance();
    $tag = "quiz_server.php";
    
	if(isset($_POST["title"], $_POST["content"], $_POST["user_id"])){
		$title = $_POST["title"];//标题
		$content = $_POST["content"];//内容
        $user_id = $_POST["user_id"];//用户id
		
		$new_question = new question("", $title, $content, $user_id, date('Y-n-j H:m:s'), true);
        $my_question_dao = new question_dao();
        $result = $my_question_dao->insert($new_question);
        
		if (!$result){
            $log->setError("insert into question is wrong", $tag . " line" . __LINE__);
			echo "Error!";
		}else{
			echo "Success!";
		}
		exit;
	}
    
    $log->setError("title or content is undefine", $tag);
    echo false;