<?php
// we connect to localhost and socket e.g. /tmp/mysql.sock
$mysql_server_name = '192.168.0.101'; //���ݿ����������
$mysql_username = "root";             // �������ݿ��û���
$mysql_password = 'root';                 // �������ݿ�����
$mysql_database = "zhihu";             // ���ݿ������

// ���ӵ����ݿ�
$conn = mysql_connect($mysql_server_name, $mysql_username,$mysql_password);
if (!$conn) {
    die('Could not connect: ' . mysql_error());
}
//ѡ�����ݿ�
mysql_select_db($mysql_database,$conn);
