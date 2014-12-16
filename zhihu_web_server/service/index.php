<?php

class Person{ //定义了一个Person类

public $name; //定义属性name

public $age; //定义属性age

function  __construct($name,$age){

    $this->name =  $name;

    $this->age = $age;

}

function say( ) //定义方法say( )

{ echo "my name is ".$this->name."<br>";  }

}

$zhangsan=new Person(); //生成对象zhangsan

$zhangsan->name="zhangsan"; //给对象中的name赋值

$zhangsan->age=26; //给对象中的age赋值

$zhangsan->say(); //调用方法say()

echo "age is ".$zhangsan->age; //输出age值

?>