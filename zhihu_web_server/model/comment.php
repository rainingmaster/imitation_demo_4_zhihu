<?php
    /**
    *comment.php
    *评论表实体类
    **/
    
    require_once("base_model.php");
    
    class comment extends base_model{
        public $content;
        public $user_id;
        public $init_time;
    }