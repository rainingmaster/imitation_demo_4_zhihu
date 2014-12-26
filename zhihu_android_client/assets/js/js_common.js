var refLock = false;//刷新锁
var REFRESH = 45;//刷新有效距离
var positionY;//点击Y坐标

function getFresh() {//获取最新，将在顶部
    if (!refLock) {
        refLock = true;
        refresh("new");
    }
}

function getMore() {//获取更多，将在底部加入
    if (!refLock) {
        refLock = true;
        refresh("more");
    }
}

/**
*顶端下拉刷新方法
*/
function handleTouchEvent(event) {//处理点击的句柄
    switch (event.type) {
        case "touchstart":
            if($(document).scrollTop() == 0) {
                //$("#1001").html("Touch started (" + event.touches[0].clientX + "," + event.touches[0].clientY + ")");
                positionY = event.touches[0].clientY;
            }
            break;
        case "touchmove":
            //需要做下拉时的动画，并告诉android的titlebar改变
            if (positionY + REFRESH <= event.changedTouches[event.changedTouches.length - 1].clientY && $(document).scrollTop() == 0) {
                getFresh();//获取新的
            }
            break;
    }
}

function bindDropNew() {
    document.addEventListener("touchstart", handleTouchEvent, false);
    document.addEventListener("touchmove", handleTouchEvent, false);
}

/**
*底端获得更多刷新方法
*/
function bindPullMore() {
    $(document).scroll( function() { //判断拉到底部时的动作
        if($(document).scrollTop() + $(window).height() >= $(document).height() - 20){//判断到底，需要增加一定缓冲范围刷新，为20px
            getMore();//获取更多
        }
    } )
}

function sendToAndroid(fun, data) {
    if(data == undefined)
        window.AndroidFun.getFromWebView(fun, "");
    else
        window.AndroidFun.getFromWebView(fun, data);//设置成两个参数，第一个函数名，后面那个json参数
}