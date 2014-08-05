<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<title>HILIFE接口</title>
</head>

<body>
<table align="center" cellpadding="0" cellspacing="0" border="1">
 
  <tr>
    <td colspan="8" align="center">HILIFE 接口说明</td>
  </tr>
  <tr>
    <td width="20">序号</td>
    <td width="42">模块</td>
    <td width="70">界面</td>
    <td width="100">功能点</td>
    <td width="100">对应接口</td>
    
    <td width="116">接口方法名</td>
    <td width="85">接口地址示例</td>
  </tr>
  <tr>
    <td>1</td>
    <td width="42">首页</td>
    <td>首页</td>
    <td>分享列表</td>
    <td>查询分享列表</td>
    <td>index/nesFeed</td>
    <td><a href="http://hilife.weidays.com:8080/hilifeserver/index/newsFeed.aspx">http://hilife.weidays.com:8080/hilifeserver/index/newsFeed.aspx</a></td>
  </tr>
  <tr>
    <td>2</td>
    <td rowspan="16" width="42">用户信息管理</td>
    <td rowspan="2">注册界面</td>
    <td>用户注册</td>
    <td>用户注册</td>
    <td>regist</td>
    <td><a href="http://hilife.weidays.com:8080/hilifeserver/user/userRegist.aspx?userInfo.userName=1">http://hilife.weidays.com:8080/hilifeserver/user/userRegist.aspx?userInfo.userName=1</a></td>
  </tr>
  <tr>
    <td>3</td>
    <td>使用条款显示(待定)</td>
    <td>　</td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td>4</td>
    <td rowspan="3">登录界面</td>
    <td>用户登录</td>
    <td>用户登录</td>
    <td>login</td>
    <td><a href="http://hilife.weidays.com:8080/hilifeserver/user/login.aspx?userInfo.userName=1">http://hilife.weidays.com:8080/hilifeserver/user/login.aspx?userInfo.userName=1</a></td>
  </tr>
  <tr>
    <td>5</td>
    <td>微博登录(后期)</td>
    <td>　</td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td>6</td>
    <td>忘记密码(待定)</td>
    <td>　</td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td>7</td>
    <td rowspan="9">个人资料设置</td>
    <td>资料显示</td>
    <td>查询个人资料</td>
    <td>getUserDetail</td>
    <td><a href="http://hilife.weidays.com:8080/hilifeserver/user/getUserDetail.aspx?userInfo.hilifeId=1">http://hilife.weidays.com:8080/hilifeserver/user/getUserDetail.aspx?userInfo.hilifeId=1</a></td>
  </tr>
  <tr>
    <td>8</td>
    <td>更新头像</td>
    <td>头像更新</td>
    <td>updateUserInfo</td>
    <td></td>
  </tr>
  <tr>
    <td>9</td>
    <td>更新昵称</td>
    <td>更新昵称</td>
    <td>updateUserInfo</td>
    <td><a href="http://hilife.weidays.com:8080/hilifeserver/user/updateUserInfo.aspx?updateContent=nickname&amp;userInfo.hilifeId=1&amp;userInfo.nickname=99">http://hilife.weidays.com:8080/hilifeserver/user/updateUserInfo.aspx?updateContent=nickname&amp;userInfo.hilifeId=1&amp;userInfo.nickname=99</a></td>
  </tr>
  <tr>
    <td>10</td>
    <td>更新签名</td>
    <td>更新签名</td>
    <td>updateUserInfo</td>
    <td></td>
  </tr>
  <tr>
    <td>11</td>
    <td>更新背景图片</td>
    <td>更新背景图片</td>
    <td>updateUserInfo</td>
    <td></td>
  </tr>
  <tr>
    <td>15</td>
    <td>更新血型</td>
    <td>更新血型</td>
    <td>updateUserInfo</td>
    <td></td>
  </tr>
  <tr>
    <td>　</td>
    <td>更新邮箱</td>
    <td>更新邮箱</td>
    <td>updateUserInfo</td>
    <td></td>
  </tr>
  <tr>
    <td>　</td>
    <td>更新手机</td>
    <td>更新手机</td>
    <td>updateUserInfo</td>
    <td></td>
  </tr>
  <tr>
    <td>16</td>
    <td>更新生日</td>
    <td>更新生日</td>
    <td>updateUserInfo</td>
    <td></td>
  </tr>
  <tr>
    <td>17</td>
    <td rowspan="2">城市切换</td>
    <td>城市列表</td>
    <td>城市列表</td>
    <td>getOpenedAreaList</td>
    <td><a href="http://hilife.weidays.com:8080/hilifeserver/user/getOpenedAreaList.aspx">http://hilife.weidays.com:8080/hilifeserver/user/getOpenedAreaList.aspx</a></td>
  </tr>
  <tr>
    <td>18</td>
    <td>城市保存</td>
    <td>用户城市更新</td>
    <td>updateUserInfo</td>
    <td><a href="http://hilife.weidays.com:8080/hilifeserver/user/updateUserInfo.aspx?updateContent=areaCode&amp;userInfo.hilifeId=1&amp;userInfo.areaCode=99">http://hilife.weidays.com:8080/hilifeserver/user/updateUserInfo.aspx?updateContent=areaCode&amp;userInfo.hilifeId=1&amp;userInfo.areaCode=99</a></td>
  </tr>
  <tr>
    <td>19</td>
    <td rowspan="11" width="42">个人中心</td>
    <td rowspan="7">我的主页</td>
    <td>资料显示</td>
    <td>查询个人资料</td>
    <td>getUserDetail</td>
    <td><a href="http://hilife.weidays.com:8080/hilifeserver/user/getUserDetail.aspx?userInfo.hilifeId=1">http://hilife.weidays.com:8080/hilifeserver/user/getUserDetail.aspx?userInfo.hilifeId=1</a></td>
  </tr>
  <tr>
    <td>20</td>
    <td>最近动态</td>
    <td>最新动态</td>
    <td>self/newsFeed</td>
    <td><a href="http://hilife.weidays.com:8080/hilifeserver/self/newsFeed.aspx?userInfo.hilifeId=12&amp;page=1">http://hilife.weidays.com:8080/hilifeserver/self/newsFeed.aspx?userInfo.hilifeId=12&amp;page=1</a></td>
  </tr>
  <tr>
    <td>21</td>
    <td>更多动态</td>
    <td>更多动态</td>
    <td>self/newsFeed</td>
    <td><a href="http://hilife.weidays.com:8080/hilifeserver/self/newsFeed.aspx?userInfo.hilifeId=12&amp;page=2">http://hilife.weidays.com:8080/hilifeserver/self/newsFeed.aspx?userInfo.hilifeId=12&amp;page=2</a></td>
  </tr>
  <tr>
    <td>22</td>
    <td>显示赞</td>
    <td>统计赞</td>
    <td>getFavourCount</td>
    <td><a href="http://hilife.weidays.com:8080/hilifeserver/log/getFavourCount.aspx?favourLog.busiId=11&amp;favourLog.busiType=01">http://hilife.weidays.com:8080/hilifeserver/log/getFavourCount.aspx?favourLog.busiId=11&amp;favourLog.busiType=01</a></td>
  </tr>
  <tr>
    <td>23</td>
    <td>点赞</td>
    <td>更新赞</td>
    <td>favourIt</td>
    <td><a href="http://hilife.weidays.com:8080/hilifeserver/log/favourIt.aspx?favourLog.busiId=11&amp;favourLog.busiType=01">http://hilife.weidays.com:8080/hilifeserver/log/favourIt.aspx?favourLog.busiId=11&amp;favourLog.busiType=01</a></td>
  </tr>
  <tr>
    <td>24</td>
    <td>分享</td>
    <td>　</td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td>25</td>
    <td>显示评论</td>
    <td>统计评论数</td>
    <td>getCommentCount</td>
    <td><a href="http://hilife.weidays.com:8080/hilifeserver/log/getCommentCount.aspx?commentLog.busiId=11&amp;commentLog.busiType=01">http://hilife.weidays.com:8080/hilifeserver/log/getCommentCount.aspx?commentLog.busiId=11&amp;commentLog.busiType=01</a></td>
  </tr>
  <tr>
    <td>26</td>
    <td rowspan="3">与我相关</td>
    <td>与我相关列表</td>
    <td>查询与我相关内容</td>
    <td>　</td>
    <td>　</td>
  </tr>
  <tr>
    <td>27</td>
    <td>与我相关更多</td>
    <td>查询更多与我相关内容</td>
    <td>　</td>
    <td>　</td>
  </tr>
  <tr>
    <td>28</td>
    <td>回复</td>
    <td>保存回复</td>
    <td>commentIt</td>
    <td><a href="http://hilife.weidays.com:8080/hilifeserver/log/commentIt.aspx?commentLog.busiId=11&amp;commentLog.busiType=01">http://hilife.weidays.com:8080/hilifeserver/log/commentIt.aspx?commentLog.busiId=11&amp;commentLog.busiType=01</a></td>
  </tr>
  <tr>
    <td>37</td>
    <td>TA的个人主页</td>
    <td>同我的主页</td>
    <td>　</td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td>29</td>
    <td width="42">　</td>
    <td>分类界面</td>
    <td>分享列表</td>
    <td>查询分享列表</td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td>30</td>
    <td rowspan="5" width="42">系统管理</td>
    <td rowspan="2">系统设置</td>
    <td>系统设置相关功能</td>
    <td>　</td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td>31</td>
    <td>意见反馈</td>
    <td>意见反馈</td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td>　</td>
    <td rowspan="3">消息中心</td>
    <td></td>
    <td>消息推送（后期）</td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td>32</td>
    <td>消息列表</td>
    <td>查询消息列表</td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td>33</td>
    <td>消息详情</td>
    <td>查询消息详情</td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td>34</td>
    <td rowspan="9" width="42">图片分享与查看</td>
    <td rowspan="4">发布分享</td>
    <td>分享填写</td>
    <td>　</td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td>　</td>
    <td>分享转载</td>
    <td>分享转载</td>
    <td>reshipPic</td>
    <td></td>
  </tr>
  <tr>
    <td>35</td>
    <td>分享发表</td>
    <td>分享发表</td>
    <td>sharePic</td>
    <td><a href="http://hilife.weidays.com:8080/hilifeserver/pic/sharePic.aspx?picShare.hilifeId=11&amp;sharePic.title=sss&amp;sharePic.content=iii&amp;sharePic.sendCity=22&amp;sharePic.sentLocation=999&amp;sharePic.longitude=99.9&amp;sharePic.latitude=00.1&amp;sharePic.picIds=11$22$33$55">http://hilife.weidays.com:8080/hilifeserver/pic/sharePic.aspx?picShare.hilifeId=11&amp;sharePic.title=sss&amp;sharePic.content=iii&amp;sharePic.sendCity=22&amp;sharePic.sentLocation=999&amp;sharePic.longitude=99.9&amp;sharePic.latitude=00.1&amp;sharePic.picIds=11$22$33$55</a></td>
  </tr>
  <tr>
    <td>36</td>
    <td>分享同步(后期)</td>
    <td>　</td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td>38</td>
    <td rowspan="5">动态详情</td>
    <td>动态显示</td>
    <td>查询明细</td>
    <td>shareDetail</td>
    <td><a href="http://hilife.weidays.com:8080/hilifeserver/pic/shareDetail.aspx?picShare.shareId=11">http://hilife.weidays.com:8080/hilifeserver/pic/shareDetail.aspx?picShare.shareId=11</a></td>
  </tr>
  <tr>
    <td>39</td>
    <td>动态评论显示</td>
    <td>查询评论</td>
    <td>getComment</td>
    <td><a href="http://hilife.weidays.com:8080/hilifeserver/log/getComment.aspx?commentLog.busiId=11&amp;commentLog.busiType=01">http://hilife.weidays.com:8080/hilifeserver/log/getComment.aspx?commentLog.busiId=11&amp;commentLog.busiType=01</a></td>
  </tr>
  <tr>
    <td>40</td>
    <td>赞显示</td>
    <td>统计赞数</td>
    <td>getFavourCount</td>
    <td><a href="http://hilife.weidays.com:8080/hilifeserver/log/getFavourCount.aspx?commentLog.busiId=11&amp;commentLog.busiType=01">http://hilife.weidays.com:8080/hilifeserver/log/getFavourCount.aspx?commentLog.busiId=11&amp;commentLog.busiType=01</a></td>
  </tr>
  <tr>
    <td>41</td>
    <td>点赞</td>
    <td>更新赞</td>
    <td>favourIt</td>
    <td><a href="http://hilife.weidays.com:8080/hilifeserver/log/favourIt.aspx?favourLog.busiId=11&amp;favourLog.busiType=01">http://hilife.weidays.com:8080/hilifeserver/log/favourIt.aspx?favourLog.busiId=11&amp;favourLog.busiType=01</a></td>
  </tr>
  <tr>
    <td>42</td>
    <td>分享(后期)</td>
    <td>　</td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td></td>
    <td rowspan="2" width="42">资源管理</td>
    <td></td>
    <td>文件上传</td>
    <td>文件上传</td>
    <td>uploadPic</td>
    <td><a href="http://hilife.weidays.com:8080/hilifeserver/page/upload.jsp">http://hilife.weidays.com:8080/hilifeserver/page/upload.jsp</a></td>
  </tr>
  <tr>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td>file--&gt;image</td>
  </tr>
  <tr>
    <td></td>
    <td width="42"></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td>other--&gt;picInfo.xxx</td>
  </tr>
</table>
</body>
</html>
