<?php

/*
 *判断普通属性
 *判断是不是自动生成的属性值，例如id
 */	
function isNormalKey($key) {
    if(strpos($key, '__auto') !== false) {//非自增长属性值
        return false;
    }
    return true;
}