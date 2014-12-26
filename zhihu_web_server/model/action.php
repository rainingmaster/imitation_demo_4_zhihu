<?php
    /**
    *action.php
    *动作表实体类
    **/
    
    require_once("base_model.php");
    
    class action extends base_model{
        public $taget_id;//动作目标id
        public $initiator_id;//发起人id
        public $init_time;//建立时间
        public $type;//动作类型
    }