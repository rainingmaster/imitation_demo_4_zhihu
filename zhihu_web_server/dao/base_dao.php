<?php
    /**
    *base_dao.php
    *数据库基础对象操作类
    **/
    
    require_once($_SERVER['DOCUMENT_ROOT']."/log/log.class.php");
    require_once($_SERVER['DOCUMENT_ROOT']."/conn/conn.php");
     
    class base_dao{
        protected $model_name;
        protected $log;
        
        function base_dao() {
            $this->model_name = substr(get_class($this) , 0, -4);//删掉最后的'_dao'
            $this->log = Log::get_instance();
        }
        
        /**
        * 往table中插入一条记录
        * @param 需要插入的对象--其中对象类名要和表名对应
        **/
        public function insert($table) {
            if($this->model_name != get_class($table)) {//对比操作的对象类型是否正确
                $this->log->setError("DAO is different from model", "base_dao");
                return false;
            }
            $key_str = '(';
            $val_str = '(';
            foreach ($table as $key => $value) {
                $key_str = $key_str . $key . ',';
                $val_str = $val_str . '"' . $value . '",';
            }
            $key_str = substr($key_str, 0, -1) . ')';
            $val_str = substr($val_str, 0, -1) . ')';
            
            //字符拼接
            $query = "INSERT INTO " . $this->model_name . $key_str . " VALUES" . $val_str . ";";
            //echo $query;
            $result = mysql_query($query);
            if (!$result){
                $this->log->setError(mysql_errno() . ": " . mysql_error(), $this->model_name);
                return false;
            }
            return true;
        }
        
        /**
        * 往table中插入一条记录
        * @param 需要插入的对象--其中对象类名要和表名对应
        **/
        public function select($condition, $attr = "*") {
            $query = "SELECT " . $attr . " FROM " . $this->model_name . " WHERE " . $condition . ";";
        }
    }