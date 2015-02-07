<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="com.siims.auction.domain.Goods"%>
   <%@ page import="java.util.List"%>
   <%@ page import="com.siims.auction.domain.Contact" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的商品</title>
	<script src="../js/jquery-1.9.1.min.js"></script>
  <!-- End SlidesJS Required -->

  <!-- SlidesJS Required: Link to jquery.slides.js -->
  <script src="../js/jquery.slides.min.js"></script>
   		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
 <link rel="stylesheet" href="../css/example.css">
  <link rel="stylesheet" href="../css/font-awesome.min.css">
  <link rel="stylesheet" href="../css/style.css">
 <style>
    body {
      -webkit-font-smoothing: antialiased;
      font: normal 15px/1.5 "Helvetica Neue", Helvetica, Arial, sans-serif;
      color: #232525;
      padding-top:5px;
    }

    #slides {
      display: none
    }

    #slides .slidesjs-navigation {
      margin-top:5px;
    }

    a.slidesjs-next,
    a.slidesjs-previous,
    a.slidesjs-play,
    a.slidesjs-stop {
   
      background-repeat: no-repeat;
      display:block;
      width:12px;
      height:18px;
      overflow: hidden;
      text-indent: -9999px;
      float: left;
      margin-right:5px;
    }

    a.slidesjs-next {
      margin-right:10px;
      background-position: -12px 0;
    }

    a:hover.slidesjs-next {
      background-position: -12px -18px;
    }

    a.slidesjs-previous {
      background-position: 0 0;
    }

    a:hover.slidesjs-previous {
      background-position: 0 -18px;
    }

    a.slidesjs-play {
      width:15px;
      background-position: -25px 0;
    }

    a:hover.slidesjs-play {
      background-position: -25px -18px;
    }

    a.slidesjs-stop {
      width:18px;
      background-position: -41px 0;
    }

    a:hover.slidesjs-stop {
      background-position: -41px -18px;
    }

    .slidesjs-pagination {
      margin: 7px 0 0;
      float: right;
      list-style: none;
    }

    .slidesjs-pagination li {
      float: left;
      margin: 0 1px;
    }

    .slidesjs-pagination li a {
      display: block;
      width: 13px;
      height: 0;
      padding-top: 13px;
      background-image: url(../images/pagination.png);
      background-position: 0 0;
      float: left;
      overflow: hidden;
    }

    .slidesjs-pagination li a.active,
    .slidesjs-pagination li a:hover.active {
      background-position: 0 -13px
    }

    .slidesjs-pagination li a:hover {
      background-position: 0 -26px
    }

    #slides a:link,
    #slides a:visited {
      color: #333
    }

    #slides a:hover,
    #slides a:active {
      color: #9e2020
    }

    .navbar {
      overflow: hidden
    }
    
     #slides {
      display: none
    }

    .container {
      margin: 0 auto
    }

    /* For tablets & smart phones */
    @media (max-width: 767px) {
      body {
        padding-left: 20px;
        padding-right: 20px;
      }
      .container {
        width: auto
      }
    }

    /* For smartphones */
    @media (max-width: 480px) {
      .container {
        width: auto
      }
    }

    /* For smaller displays like laptops */
    @media (min-width: 768px) and (max-width: 979px) {
      .container {
        width: 724px
      }
    }

    /* For larger displays */
    @media (min-width: 1200px) {
      .container {
        width: 1170px
      }
    }
    
  </style>
		

<%
//	Interval good =(Interval) request.getAttribute("goods");
	List<Goods> goods = (List<Goods>)request.getAttribute("goods");
	//out.println(g.getgName());
	List<Contact> contacts = (List<Contact>)request.getAttribute("contacts");
	

	


%>


<script type="text/javascript">
$(function() {
   $('.imgsdiv').slidesjs({
     width: 360,
     height:350,
     navigation: false,
     play: {
       active: false,
       auto: true,
       interval: 4000,
       swap: true
    }
  });

   $("#buy").click(function(){
		$(this).css("  background-image: url(../images/btn_cirl_green_pressed.png)");

	   });
});



</script>

</head>

<body >
<%for(int i=0;i<goods.size();i++){
	Goods g = goods.get(i);
	String[] imgs = g.getgImages().substring(0,g.getgImages().length()-1).split(";");
	Contact c = contacts.get(i);
	%>
	<div class="container">
	<%if(imgs.length==1){
		%>
		<div id="slides"style="overflow: hidden; display:inherit;margin-bottom: 10px;" >
<% 	} else{%>
    <div id="slides" class="imgsdiv">
    <%} %>
    <%for(int j = 0;j<imgs.length;j++) {%>
      <img src="../images/<%=imgs[j] %>" alt="<%=j%>">
   <%} %>
    </div>
    <%if(g.getgVideo()!=null||!g.getgVideo().equals("")) {%>
    <video class="" controls preload="none" width="100%" height="264" poster="../images/<%=g.getgVideoCover()%>"data-setup="{}">
    <source src="../video/<%=g.getgVideo() %>" type="video/mp4"/>
    
    </video>
    <%} %>
  </div>
  
	 <div class="item-info">
     <div class="item-price">
        <span class="new">
            <span class="rmb">￥</span><%=g.getgPrice() %>
        </span>
		&nbsp;&nbsp;
        <span class="old">
            <span class="rmb">￥</span><%=g.getgOrigionPrice() %>
        </span>
		&nbsp;&nbsp;
		<input class="anniu" type="button" id="buy" value="立即购买">
     </div>

	<hr>
	 <div class="item-name">
	   <span><%=g.getgName() %></span>
	</div>

	<div class="item-detail">
    <p class="item-detail-cont">
        <%=g.getgDetailDesc() %>
    </p>
    </div>
   </div>
	<div class="fill"></div><br/>
  
	
    <div class="item-user">
	   <p  class="item-user-info">卖家联系方式</p><hr>
        <p class="item-user-name">卖家：<span style="color:gray;"><%=c.getcName() %></span></p>
		<p class="item-user-telenum">电话：<span style="color:gray;"><%=c.getcPhone() %></span></p>
        <div class="item-user-meta">
          <p class="item-user-addr">地址：<span style="color:gray;"><%=c.getcCity().replace(";", "  ")+"  "+c.getcRegion()%></span></p>
        </div>
    </div>
	
<%} %>

</body>
</html>