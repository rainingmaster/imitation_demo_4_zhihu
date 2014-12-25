var IP_ADDR;//服务器IP地址
var TITLE_ID;//题目ID
var TITLE;//问题题目

function initPage(data) {//初始化页面
    var json = eval('('+data+')');
	IP_ADDR = json.ip;
    TITLE_ID = json.id;
    TITLE = json.title;
    $("#title").html(TITLE);
}


function getFromAndroid(obj) {
  window.main_json=eval('('+obj+')');
  $("#title").html(window.main_json.title);
}

function initRow(json_row) {
    //生成各项div
    var all_div = $('<div id="'+json_row.id+'" class="push"></div>');
    var introduce_div = $('<div class="introduce"></div>');
    var user_a = $('<a class="user" href="#">'+json_row.user.name+'</a>');
    var action_str;
    switch(parseInt(json_row.action)) {
        case 0:
            action_str = "赞同该回答";
            break;
        case 1:
            action_str = "关注了该问题";
            break;
        case 2:
            action_str = "提出了该问题";
            break;
        case 3:
            action_str = "回答了该问题";
            break;
        default:
            break;
    }
    var action_span = $('<span class="action">'+action_str+'</span>');
    var photo_img = $('<img class="photo" src="'+json_row.user.photo+'" />')
    var title_div = $('<div class="title">'+json_row.title+'</div>');
    if (parseInt(json_row.action) == 0) {
        var answer_div = $('<div class="answer"></div>');
        var count_div = $('<div class="a_count">'+json_row.answer.agree_count+'</div>');
        var text_div = $('<div class="a_text">'+json_row.answer.content+'</div>');
        answer_div.append(count_div);
        answer_div.append(text_div);
    }
    var line_div = $('<div class="line"></div>');
    //拼装整理
    introduce_div.append(user_a);
    introduce_div.append(action_span);
    introduce_div.append(photo_img);
    
    all_div.append(introduce_div);
    all_div.append(title_div);
    if(typeof(answer_div) != "undefined")
        all_div.append(answer_div);
    all_div.append(line_div);
    
    return all_div;
}

function refresh(tag) { //刷新页面，将从服务端获得最新/更多内容
	var cont = "test";
	$.ajax({
		url:'http://'+IP_ADDR+'/service/index_service.php',
		type:'post',
		dataType:'json',
		data:cont,
		success:function(data){
			var json = eval(data);
			if (tag == "more") {
                addMore(json);
            } else {
                addNew(json);
            }
            refLock = false;//解刷新锁
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			alert(errorThrown);
            refLock = false;//解刷新锁
        }
	});
}

function addNew(json) {//将获取到的内容从顶部加入
    var new_row;
    for(var i = 0; i < json.length; i++) {
        new_row = initRow(json[i]);
        $("#main").prepend(new_row);//加到顶部
    }
}

function addMore(json) {//将获取到的内容从底部加入
    var new_row;
    for(var i = 0; i < json.length; i++) {
        new_row = initRow(json[i]);
        $("#main").append(new_row);//加到底部
    }
}

function bindTouch() {
    /*relative模块*/
    $("#relative").bind("touchstart", function(){
        $(this).addClass("touch");
    });
    $("#relative").bind("touchmove touchend", function(){
        $(this).removeClass("touch");
    });
    /*oper_btn模块*/
    $(".oper_btn").bind("touchstart", function(){
        $(this).addClass("touch");
    });
    $(".oper_btn").bind("touchmove touchend", function(){
        $(this).removeClass("touch");
    });
    /*answer模块*/
    $(".answer").bind("touchstart", function(){
        $(this).addClass("touch");
        $(this).children(".a_count").addClass("touch");
    });
    $(".answer").bind("touchmove touchend", function(){
        $(this).removeClass("touch");
        $(this).children(".a_count").removeClass("touch");
    });
}

function bindJump() {//绑定点击域
    $("#addanser").click(function(){
      sendToAndroid("jumpToAnswer");
    });
}

$(document).ready(function(){
    bindJump();
    
    //bindDropNew();//绑定下拉刷新，按需绑定
    bindTouch();//绑定点击时效果
});