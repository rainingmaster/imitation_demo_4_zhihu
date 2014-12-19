<?php
define("LOG_FILE_PATH", $_SERVER['DOCUMENT_ROOT']."/log/zhihu_webserver_");
define("LOG_SWITCH", true);
define("LOG_MAX_LEN", 500);

/**
 * 日志处理类
 */

class Log{

    //单例模式
    private static $instance    = NULL;
    //文件句柄
    private static $handle      = NULL;
    //日志开关
    private $log_switch     = NULL;
    //日志相对目录
    private $log_file_path      = NULL;
    //日志文件最大长度，超出长度重新建立文件
    private $log_max_len        = NULL;
    //日志文件前缀,入 log_0
    private $log_file_pre       = 'log_';

        
    /**
     * 构造函数
     */
    protected function __construct(){//注意：以下是配置文件中的常量

        $this->log_file_path     = LOG_FILE_PATH; //日志全路径

        $this->log_switch     = LOG_SWITCH; //日志开关

        $this->log_max_len    = LOG_MAX_LEN; //单个文件日志最大数量

    }

    /**
     * 单利模式
     */
    public static function get_instance(){
        if(!self::$instance instanceof self){
            self::$instance = new self;
        }
        return self::$instance;
    }

    /**
     *
     * 日志记录
     *
     * @param int $type  0 -> 记录(NOTICE LOG) / 1 -> 错误(ERROR LOG) / 2 -> 告警(WARM LOG)
     * @param string $desc 具体事项
     * @param string $tag  事项标示
     *
     */
    public function log($type = 0, $tag, $desc){
        if($this->log_switch){

            if(self::$handle == NULL){
                $filename = $this->log_file_pre . $this->get_max_log_file_suf();
                self::$handle = fopen($this->log_file_path . $filename, 'a');
            }
            switch($type){
                case 0:
                    $level = 'NOTICE LOG';
                    break;
                case 1:
                    $level = 'ERROR LOG';
                    break;
                case 2:
                    $level = 'WARM LOG';
                    break;
            }
            fwrite(self::$handle,  date("Y-m-d H:M:S "). ' ' . $level . ':' . ' ' . $tag . ' ' . $desc . chr(13));

        }
    }
    
    /**
     * 提醒日志
     * @param string $desc 具体事项
     *
     */
    public function setNotice($desc, $tag = 'message') {
        self::log(0, $tag, $desc);
    }
    
    /**
     * 错误日志
     * @param string $desc 具体事项
     *
     */
    public function setError($desc, $tag = 'message') {
        self::log(1, $tag, $desc);
    }
    
    /**
     * 警告日志
     * @param string $desc 具体事项
     *
     */
    public function setWarm($desc, $tag = 'message') {
        self::log(2, $tag, $desc);
    }

    /**
     * 获取当前日志的最新文档的后缀
     *
     */
    private function get_max_log_file_suf(){
        $log_file_suf = null;
        if(is_dir($this->log_file_path)){
            if($dh = opendir($this->log_file_path)){
                while(($file = readdir($dh)) != FALSE){
                    if($file != '.' && $file != '..'){
                        if(filetype( $this->log_file_path . $file) == 'file'){
                            $rs = split('_', $file);
                            if($log_file_suf < $rs[1]){
                                $log_file_suf = $rs[1];
                            }
                        }
                    }
                }

                if($log_file_suf == NULL){
                    $log_file_suf = 0;
                }
                //截断文件
                if( file_exists($this->log_file_path . $this->log_file_pre . $log_file_suf) && filesize($this->log_file_path . $this->log_file_pre . $log_file_suf) >= $this->log_max_len){
                    $log_file_suf = intval($log_file_suf) + 1;
                }

                return $log_file_suf;
            }  
        }

        return 0;

    }

    /**
     * 关闭文件句柄
     *
     */
    public function close(){
        fclose(self::$handle);
    }
}
?>