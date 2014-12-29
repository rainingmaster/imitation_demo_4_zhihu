<?php
// we connect to localhost and socket e.g. /tmp/mysql.sock
/*$mysql_server_name = "localhost";//$_SERVER['SERVER_ADDR']; //数据库服务器名称/IP
$mysql_username = "root";             // 连接数据库用户名
$mysql_password = 'root';                 // 连接数据库密码
$mysql_database = "zhihu";             // 数据库的名字*/

$mysql_server_name = SAE_MYSQL_HOST_M;//$_SERVER['SERVER_ADDR']; //数据库服务器名称/IP
$mysql_username = SAE_MYSQL_USER;             // 连接数据库用户名
$mysql_password = SAE_MYSQL_PASS;                 // 连接数据库密码
$mysql_database =  SAE_MYSQL_DB;             // 数据库的名字

// 连接到数据库
//$conn = mysql_connect($mysql_server_name, $mysql_username,$mysql_password);
$conn = mysql_connect(SAE_MYSQL_HOST_M.':'.SAE_MYSQL_PORT,SAE_MYSQL_USER,SAE_MYSQL_PASS);
if (!$conn) {
    die('Could not connect: ' . mysql_error());
}
//选择数据库
mysql_select_db($mysql_database,$conn);
