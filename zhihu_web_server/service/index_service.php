<?php
	header("Access-Control-Allow-Origin:*");//����CORS�������վ����
	class User{
		public $name;
		public $photo;
		
		function  __construct($name, $photo){
			$this->name =  $name;
			$this->photo = $photo;
		}
	}
	class User_Interested_Question{
		public $title;
		public $content;
		public $action;//0-��ͬ��1-��ע��2-���
		public $user;
		
		function  __construct($title, $content, $action, $user){
			$this->title =  $title;
			$this->content = $content;
			$this->action =  $action;
			$this->user = $user;
		}
	}
	
	$an = new User("answer", "http://localhost/img/tt.png");//��Ҫ��ϸurl
	
	$title = "why sun is so far";
	$content = "blablablablablablablablabla";
	$usr_ques = new User_Interested_Question($title, $content, 1, $an);
	
	$ques = json_encode($usr_ques);
	echo $ques;
	//var_dump($ques);
	
	
	