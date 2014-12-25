<?php
// we connect to localhost and socket e.g. /tmp/mysql.sock
$mysql_server_name = "localhost";//$_SERVER['SERVER_ADDR']; //数据库服务器名称/IP
$mysql_username = "root";             // 连接数据库用户名
$mysql_password = 'root';                 // 连接数据库密码
$mysql_database = "zhihu";             // 数据库的名字

// 连接到数据库
$conn = mysql_connect($mysql_server_name, $mysql_username,$mysql_password);
if (!$conn) {
    die('Could not connect: ' . mysql_error());
}
//选择数据库
mysql_select_db($mysql_database,$conn);
