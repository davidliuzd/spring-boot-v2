# 参考:https://my.oschina.net/wangkang80/blog/908070

##一.前言

###XSS(跨站脚本攻击)

跨站脚本攻击(Cross Site Scripting)，为不和层叠样式表(Cascading Style Sheets, CSS)的缩写混淆，故将跨站脚本攻击缩写为XSS。恶意攻击者往Web页面里插入恶意Script代码，当用户浏览该页之时，嵌入其中Web里面的Script代码会被执行，从而达到恶意攻击用户的目的。

##二.思路

###基于filter拦截,将特殊字符替换为html转意字符 (如: "<" 转意为 "&lt;") , 需要拦截的点如下:

请求头 requestHeader

请求体 requestBody

请求参数 requestParameter

##三.实现

### 创建XssHttpServletRequestWrapper类

在获取请求头,请求参数的这些地方,将目标值使用HtmlUtils.htmlEscape方法转意为html字符,而避免恶意代码参与到后续的流程中


## 解释

1. 什么是XSS? 为啥有这个呢？

全名：Cross Site Script，中文名：跨站脚本攻击。顾名思义，是指“HTML注入”纂改了网页，插入恶意的脚本，从而在用户用浏览网页的时候，控制用户浏览器的一种攻击。XSS根据攻击的稳定性可分为三种：反射型XSS, 存储型XSS,DOM Based XSS.
  

## 其它方法：[注解插件支持](https://github.com/techguy-bhushan/XssRequestFilters)
  
  