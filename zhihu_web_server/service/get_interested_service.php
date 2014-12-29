<?php
    /**
    *get_interested_service.php
    *获取新关注信息服务接口，用于首页
    *必要值:user_id,用户id
    *可选值:refresh_time,前一次刷新时间戳
    **/
    header("Access-Control-Allow-Origin:*");//启用CORS，允许跨站访问
    header("Content-Type:text/html;charset=utf-8");
    
    require_once($_SERVER['DOCUMENT_ROOT']."/common/pub_value.php");
    
    require_once($_SERVER['DOCUMENT_ROOT']."/dao/action_dao.php");
    require_once($_SERVER['DOCUMENT_ROOT']."/dao/question_dao.php");
    require_once($_SERVER['DOCUMENT_ROOT']."/dao/answer_dao.php");
    require_once($_SERVER['DOCUMENT_ROOT']."/dao/people_dao.php");
    
    /**************查找内容*******************/
    $my_action_dao = new action_dao();
    $my_people_dao = new people_dao();
    $my_question_dao = new question_dao();
    $my_answer_dao = new answer_dao();
    $action_list = $my_action_dao->findAll();//先提取所有动作，届时需要经过筛选，例如以自己关注列表筛选
    
    $arr = array();
    /*接收解析模板
    *{
    *   id:10000,
    *   title:"天才是如何养成的?",
    *   content:"",
    *   action:3,
    *   people:{
    *       id:11,
    *       name:"tempoo",
    *       photo:'http://'.SERVER_ADDR.'/img/tt.png',//需要自己拼接
    *   }
    *   answer:{
    *        agree_count:132,
    *        content:"好好学习，天天向上",
    *   }
    *}
    */
    for ($i = 0, $l = count($action_list); $i < $l; $i++) { //需要unset一些属性，减少不必要传输的内容
        $people = $my_people_dao->findOneByValue("id", $action_list[$i]["initiator_id"]);
        $people["photo"] = 'http://'.SERVER_ADDR.'/img/'.$people["photo"].'.png';
        switch($action_list[$i]["type"]) {
            case 0:{//赞同回答
                break;
            }
            case 1:{//关注了该问题
                break;
            }
            case 2: {//提出了该问题
                $question = $my_question_dao->findOneByValue("id", $action_list[$i]["target_id"]);
                $question["action"] = $action_list[$i]["type"];
                $question["people"] = $people;
                $arr[] = $question;
                break;
            }
            case 3:{//回答了该问题
                $answer = $my_answer_dao->findOneByValue("id", $action_list[$i]["target_id"]);
                $question = $my_question_dao->findOneByValue("id", $answer["question_id"]);
                $question["action"] = $action_list[$i]["type"];
                $question["people"] = $people;
                $question["answer"] = $answer;
                $arr[] = $question;
                break;
            }
        }
    }
    /**************end 查找内容*******************/
    
    /******************发送内容*****************/
    $ref = json_encode($arr);
    echo $ref;
    