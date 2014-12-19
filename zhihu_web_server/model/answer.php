<?php
    /**
    *answer.php
    *回答表实体类
    **/
    
    require($_SERVER['DOCUMENT_ROOT']."/model/base_model.php");
    
    class answer extends base_model{
        public $content;
        public $question_id;
        public $user_id;
        public $agree_count;
        public $anonymous_tag;
        /*
        function answer($content, $question_id, $user_id, $agree_count = 0, $anonymous_tag = true) {
            $this->content = $content;
            $this->question_id = $question_id;
            $this->user_id = $user_id;
            $this->init_time = $init_time;
            $this->anonymous_tag = $anonymous_tag;
        }*/
        
    }