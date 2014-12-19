<?php
    /**
    *base_model.php
    *数据库基础实体类
    **/
    
    require_once($_SERVER['DOCUMENT_ROOT']."/log/log.class.php");
     
    class base_model{
        function base_model() {//默认采用参数按顺序填入变量
            $numargs = func_num_args();//参数个数
            $arg_list = func_get_args();//参数数组
            
            $var_list = get_class_vars(get_class($this));
            
            for ($i = 0 ;$i < $numargs; $i++) {
                $cmd = '$this->' . key($var_list) . '=' . $arg_list[$i] . ';';
                eval($cmd);//执行拼接语句
                next($var_list);//key(array)指针位移
            }
        }
    }