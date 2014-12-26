<?php
    /**
    *base_dao.php
    *数据库基础对象操作类
    **/
    
    require_once($_SERVER['DOCUMENT_ROOT']."/log/log.class.php");
    require_once($_SERVER['DOCUMENT_ROOT']."/common/conn.php");
    require_once($_SERVER['DOCUMENT_ROOT']."/common/tool.php");
     
    class base_dao{
        protected $model_name;
        protected $log;
        
        function base_dao() {
            $this->model_name = substr(get_class($this) , 0, -4);//删掉最后的'_dao'
            $this->log = Log::get_instance();
        }
        
        /**
        * 往表中插入一条记录row
        * @param 需要插入的对象--其中对象类名要和表名对应
        **/
        public function insert($row) {
            if($this->model_name != get_class($row)) {//对比操作的对象类型是否正确
                $this->log->setError("DAO is different from model", "base_dao");
                return false;
            }
            $key_str = '(';
            $val_str = '(';
            foreach ($row as $key => $value) {
                if(isNormalKey($key)) {//非自增长属性
                    $key_str .= $key . ',';
                    $val_str .= '"' . $value . '",';
                }
            }
            $key_str = substr($key_str, 0, -1) . ')';
            $val_str = substr($val_str, 0, -1) . ')';
            
            //字符拼接
            $query = "INSERT INTO " . $this->model_name . $key_str . " VALUES" . $val_str . ";";
            
            $result = mysql_query($query);
            if (!$result){
                $this->log->setError("wrong in insert " . mysql_errno() . ": " . mysql_error(), $this->model_name . "_dao.php");
                $this->log->setError("sql is '" . $query . "'", "------");
                return false;
            }
            return true;
        }
        
        /**
        * 查找表中内容基础方法
        * @param condition 查找条件
		* @param attr  需要查找的属性
        **/
        public function baseFind($condition = "", $attr = "*") {
            $query = "SELECT " . $attr . " FROM " . $this->model_name;//. " WHERE " . $condition . ";";
			if($condition == "") {
				$query .= ";";
			} else {
				$query .= " WHERE " . $condition . ";";
			}
			
			$result = mysql_query($query);
            if (!$result){
                $this->log->setError("wrong in select " . mysql_errno() . ": " . mysql_error(), $this->model_name . "_dao.php");
                $this->log->setError("sql is '" . $query . "'", "------");
                return false;
            }
			
			$ref = array();
			while($row = mysql_fetch_assoc($result)) {
				$ref[] = $row;
			}
			return $ref;
        }
        
        /**
        * 查找表中内容
        **/
        public function find($condition = "", $attr = "*") {
            return $this->baseFind($condition, $attr);
        }
        
        /**
        * 查找表中所有内容
        **/
        public function findAll() {
            return $this->baseFind();
        }
        
        /**
        * 通过键值查找表中单个内容(如果是有多个重复则获得第一个)
        **/
        public function findOneByValue($k, $v) {
            $ref = $this->baseFind($k . "=" . $v . " LIMIT 1");
            return $ref[0];
        }
        
        /**
        * 通过键值查找表中内容
        **/
        public function findByValue($k, $v) {
            return $this->baseFind($k . "=" . $v);
        }
        
    }