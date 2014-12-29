<?php
    /**
    *pub_value.php
    *公共值信息值。
    **/
    
    //由于在sae上用$_SERVER['SERVER_ADDR']获得的值是不对的，因为分布式不一定是本台计算机，需要用到域名
    //因此在服务器发布的时候自己测用IP，在sae要用域名
    defined("SERVER_ADDR") || define("SERVER_ADDR", "rainmaster.sinaapp.com");//$_SERVER['SERVER_ADDR']