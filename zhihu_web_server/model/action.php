<?php
    /**
    *action_dao.php
    *动作表操作类
    **/
    
    require_once("base_dao.php");
    require_once($_SERVER['DOCUMENT_ROOT']."/model/action.php"); 
    
	class action_dao extends base_dao{//--被去掉鸟，因为sae上没有不能create view，只有使用sql语句代替了
        
        /**
        * 查找表中内容基础方法
        * @param condition 查找条件
		* @param attr  需要查找的属性
        **/
        public function baseFind($condition = "", $attr = "*") {
            $query = 'SELECT id AS target_id,asker_id AS initiator_id,"2" AS type,init_time FROM question WHERE anonymous_tag = 1';
            $query .= ' UNION ALL ';
            $query .= 'SELECT id AS target_id,responder_id AS initiator_id,"3" AS type,init_time FROM answer WHERE anonymous_tag = 1';
            
            $query = 'SELECT ' . $attr . ' FROM (' . $query . ') AS total';
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
    }