<?php
define("LOG_FILE_PATH", $_SERVER['DOCUMENT_ROOT']."/log/zhihu_webserver_");
define("LOG_SWITCH", true);
define("LOG_MAX_LEN", 500);

/**
 * ��־������
 */

class Log{

    //����ģʽ
    private static $instance    = NULL;
    //�ļ����
    private static $handle      = NULL;
    //��־����
    private $log_switch     = NULL;
    //��־���Ŀ¼
    private $log_file_path      = NULL;
    //��־�ļ���󳤶ȣ������������½����ļ�
    private $log_max_len        = NULL;
    //��־�ļ�ǰ׺,�� log_0
    private $log_file_pre       = 'log_';

        
    /**
     * ���캯��
     */
    protected function __construct(){//ע�⣺�����������ļ��еĳ���

        $this->log_file_path     = LOG_FILE_PATH; //��־ȫ·��

        $this->log_switch     = LOG_SWITCH; //��־����

        $this->log_max_len    = LOG_MAX_LEN; //�����ļ���־�������

    }

    /**
     * ����ģʽ
     */
    public static function get_instance(){
        if(!self::$instance instanceof self){
            self::$instance = new self;
        }
        return self::$instance;
    }

    /**
     *
     * ��־��¼
     *
     * @param int $type  0 -> ��¼(NOTICE LOG) / 1 -> ����(ERROR LOG) / 2 -> �澯(WARM LOG)
     * @param string $desc ��������
     * @param string $tag  �����ʾ
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
     * ������־
     * @param string $desc ��������
     *
     */
    public function setNotice($desc, $tag = 'message') {
        self::log(0, $tag, $desc);
    }
    
    /**
     * ������־
     * @param string $desc ��������
     *
     */
    public function setError($desc, $tag = 'message') {
        self::log(1, $tag, $desc);
    }
    
    /**
     * ������־
     * @param string $desc ��������
     *
     */
    public function setWarm($desc, $tag = 'message') {
        self::log(2, $tag, $desc);
    }

    /**
     * ��ȡ��ǰ��־�������ĵ��ĺ�׺
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
                //�ض��ļ�
                if( file_exists($this->log_file_path . $this->log_file_pre . $log_file_suf) && filesize($this->log_file_path . $this->log_file_pre . $log_file_suf) >= $this->log_max_len){
                    $log_file_suf = intval($log_file_suf) + 1;
                }

                return $log_file_suf;
            }  
        }

        return 0;

    }

    /**
     * �ر��ļ����
     *
     */
    public function close(){
        fclose(self::$handle);
    }
}
?>