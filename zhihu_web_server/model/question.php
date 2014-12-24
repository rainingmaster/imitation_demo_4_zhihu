<?php
    /**
    *question.php
    *问题表实体类
    **/
    
    require_once("base_model.php");
    
    class question extends base_model{
        public $id__auto;
        public $title;
        public $content;
        public $asker_id;
        public $init_time;
        public $anonymous_tag;
    }