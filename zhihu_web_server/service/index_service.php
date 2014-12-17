<?php
	header("Access-Control-Allow-Origin:*");//启用CORS，允许跨站访问
	header("Content-Type:text/html;charset=utf-8");
	class User{
		public $name;
		public $photo;
		
		function  __construct($name, $photo){
			$this->name =  $name;
			$this->photo = $photo;
		}
	}
	
	class Answer{
		public $id;
		public $content;
		public $agree_count;
		
		function  __construct($content, $agree_count){
			$this->content =  $content;
			$this->agree_count = $agree_count;
		}
	}
	
	class User_Interested_Question{
		public $id;
		public $title;
		public $content;
		public $action;//0-赞同；1-关注；2-提出; 3-回答
		public $user;
		public $answer;
		
		function  __construct($id, $title, $content, $action, $user, $answer=null){
			$this->id =  $id;
			$this->title =  $title;
			$this->content = $content;
			$this->action =  $action;
			$this->user = $user;
			$this->answer = $answer;
			
		}
	}
	
	$usr = new User("answer", "http://localhost/img/tt.png");//需要详细url
	
	$an = new Answer("这是一个答案", 34);
	
	$title = "why sun is so far";
	$content = "blablablablablablablablabla";
	$usr_ques = new User_Interested_Question(10032, $title, $content, 0, $usr, $an);
	
	$ques = json_encode($usr_ques);
	echo $ques;
	//var_dump($ques);
	
	
	