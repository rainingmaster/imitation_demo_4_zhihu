<?php
    /**
    *base_model.php
    *数据库基础实体类
    **/
    
    require_once($_SERVER['DOCUMENT_ROOT']."/log/log.class.php");
     
    class base_model{
        
        function base_model() {//默认采用参数按顺序填入变量
            
            $numargs = func_num_args();//参数个数
            if($numargs == 0) {
                //日志控件
                $log = Log::get_instance();
                $log->setError("the count of args has to more than 0", get_class($this) . ".php");
                exit;
            }
            
            $arg_list = func_get_args();//参数数组
            
            $var_list = get_class_vars(get_class($this));
            
            for ($i = 0 ;$i < $numargs; $i++) {
                $cmd = '$this->' . key($var_list) . '=\'' . $arg_list[$i] . '\';';//将值完全当做字符
                eval($cmd);//执行拼接语句
                next($var_list);//key(array)指针位移
            }
        }
    }