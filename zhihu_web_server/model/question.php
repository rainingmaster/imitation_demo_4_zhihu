<?php
    /**
    *question.php
    *问题表实体类
    **/
    class question{
        public $title;
        public $content;
        public $asker_id;
        public $init_time;
        public $anonymous_tag;
        
        function question($title, $content = "", $asker_id, $init_time = "", $anonymous_tag = "") {
            $this->title = $title;
            $this->content = $content;
            $this->asker_id = $asker_id;
            $this->init_time = $init_time;
            $this->anonymous_tag = $anonymous_tag;
        }
        
    }