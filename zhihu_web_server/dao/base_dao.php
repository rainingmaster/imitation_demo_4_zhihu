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
            //echo $query;
            $result = mysql_query($query);
            if (!$result){
                $this->log->setError("wrong in insert " . mysql_errno() . ": " . mysql_error(), $this->model_name . "_dao.php");
				mysql_free_result($result);
                return false;
            }
			mysql_free_result($result);
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
				mysql_free_result($result);
                return false;
            }
			
			$ref = array();
			while($row = mysql_fetch_object($result)) {
				$ref[] = $row;
				var_dump($row);
			}
			return $ref;
        }
		
		private function isNormalKey($key) {
			if(strpos($key, '__auto') !== false) {//非自增长属性值
                return true;
            }
            return false;
		}
    }