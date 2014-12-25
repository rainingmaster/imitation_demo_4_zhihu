<?php
    /**
    *submit_answer_service.php
    *提交答案服务接口
    *必要值:question_id,回答问题的id；user_id,回答者id；content,回答的答案
    *可选值:anonymous_tag,匿名标示
    **/
    header("Access-Control-Allow-Origin:*");//启用CORS，允许跨站访问
    header("Content-Type:text/html;charset=utf-8");
    
    require_once($_SERVER['DOCUMENT_ROOT']."/log/log.class.php");
    require_once($_SERVER['DOCUMENT_ROOT']."/dao/answer_dao.php");
    require_once($_SERVER['DOCUMENT_ROOT']."/model/answer.php");
    
    $log = Log::get_instance();
    $tag = "answer_server.php";
    
	if(isset($_POST["question_id"], $_POST["user_id"], $_POST["content"])){
		$question_id = $_POST["question_id"];//标题id
        $user_id = $_POST["user_id"];//用户id
		$content = $_POST["content"];//内容
		
        $my_answer_dao = new answer_dao();
        $new_answer = new answer("", $content, $question_id, $user_id, 0, true, date('Y-n-j H:m:s'));
        $result = $my_answer_dao->insert($new_answer);
        
		if (!$result){
            $log->setError("insert into answer is wrong", $tag . " line" . __LINE__);
			echo "Error!";
		}else{
			echo "Success!";
		}
		exit;
	}
    
    $log->setError("question or content is undefine", $tag);
    echo false;