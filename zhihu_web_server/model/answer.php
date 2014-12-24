<?php
    /**
    *answer.php
    *回答表实体类
    **/
    
    require_once("base_model.php");

    class answer extends base_model{
        public $content;
        public $question_id;
        public $user_id;
        public $agree_count;
        public $anonymous_tag;
        public $init_time;
    }