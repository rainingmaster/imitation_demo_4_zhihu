<?php

class Person{ //������һ��Person��

public $name; //��������name

public $age; //��������age

function  __construct($name,$age){

    $this->name =  $name;

    $this->age = $age;

}

function say( ) //���巽��say( )

{ echo "my name is ".$this->name."<br>";  }

}

$zhangsan=new Person(); //���ɶ���zhangsan

$zhangsan->name="zhangsan"; //�������е�name��ֵ

$zhangsan->age=26; //�������е�age��ֵ

$zhangsan->say(); //���÷���say()

echo "age is ".$zhangsan->age; //���ageֵ

?>