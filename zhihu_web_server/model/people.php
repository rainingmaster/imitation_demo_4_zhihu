<?php
    /**
    *people.php
    *用户表实体类
    **/
    
    require_once("base_model.php");
    
    class people extends base_model{
        public $id__auto;
        public $name;
        public $introduce;
        public $photo;
        public $init_time;
    };