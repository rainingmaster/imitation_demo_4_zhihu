<?php
    /**
    *question_dao.php
    *问题表操作类
    **/
    
    require_once($_SERVER['DOCUMENT_ROOT']."/conn/conn.php");
    require_once($_SERVER['DOCUMENT_ROOT']."/dao/base_dao.php");
    require_once($_SERVER['DOCUMENT_ROOT']."/model/question.php"); 
    
    class question_dao extends base_dao{
        private $table_name = "question";
    }