<?php
    /**
    *topic.php
    *话题表实体类
    **/
    
    require_once("base_model.php");
    
    class topic extends base_model{
        public $id__auto;
        public $topic;//话题
        public $introduce;//介绍
        public $parent_id;//父话题id
    }