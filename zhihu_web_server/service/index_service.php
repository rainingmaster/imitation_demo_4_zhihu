<?php
    header("Access-Control-Allow-Origin:*");//启用CORS，允许跨站访问
    header("Content-Type:text/html;charset=utf-8");
    
    require_once($_SERVER['DOCUMENT_ROOT']."/dao/question_dao.php");
    require_once($_SERVER['DOCUMENT_ROOT']."/model/question.php");
    require_once($_SERVER['DOCUMENT_ROOT']."/dao/answer_dao.php");
    require_once($_SERVER['DOCUMENT_ROOT']."/model/answer.php");
    require_once($_SERVER['DOCUMENT_ROOT']."/dao/answer_dao.php");
    require_once($_SERVER['DOCUMENT_ROOT']."/model/people.php");
    
    /**************查找内容*******************/
    /**************end 查找内容*******************/
    
    /******************整理内容*****************/
    $people = new people('rainmaster', 'cool', 'http://'.$_SERVER['SERVER_ADDR'].'/img/tt.png', date('Y-n-j H:m:s'));
    $answer = new answer('好好学习天天向上', '11', '12', '32', true, date('Y-n-j H:m:s'));
    $question = new question('title', 'content', '11', date('Y-n-j H:m:s'), true);
    //packet
    unset($question->init_time);
    unset($answer->init_time);
    unset($question->init_time);
    $question->action = "0";
    $question->user = $people;
    $question->answer = $answer;
    
    $ar_usr = array();
    $ar_usr[0] = $question;
    $ar_usr[1] = $question;
    
    $ques = json_encode($ar_usr);
    flush();
    print($ques);
    