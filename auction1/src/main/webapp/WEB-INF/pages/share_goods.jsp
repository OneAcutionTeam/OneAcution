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
	<script src="../js/jquery.min.js"></script>
	<script src="../js/jquery.cycle.all.js"></script>
   		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>


		

<%
//	Interval good =(Interval) request.getAttribute("goods");
	List<Goods> goods = (List<Goods>)request.getAttribute("goods");
	//out.println(g.getgName());
	List<Contact> contacts = (List<Contact>)request.getAttribute("contacts");
	

	


%>
<style type="text/css">

th {
text-align: center;
color: #555555;
background-color: #fcfcfc;
}
tr {
text-align: center;
color: #1ABC9C;
background-color: #fcfcfc;
}
.color-lightgray{
background-color: #f5f5f5;
}

table{
border-collapse: separate;
border-spacing: 1px;
border-color: #16A085;
background-color: #999999;
}
.clear{
background-color: #fcfcfc;
width: 100%;
height:20px;
}



</style>

<script type="text/javascript">


$(function() {
    $('.pics').cycle({
       	fx:        'cover',
       	delay:    -2000,
		before: function(curr, next, opts) {
			opts.animOut.opacity = 0;
		}
    });
   
});


</script>

</head>

<body >
<%for(int i=0;i<goods.size();i++){
	Goods g = goods.get(i);
	String[] imgs = g.getgImages().split(";");
	
	%>
<table width="100%"  border="0">
  <tr class="color-lightgray" height=40>
    <th colspan="4" scope="col" ><%=g.getgName() %></th>
  </tr>
  <tr>
    <th colspan="4" scope="row" height=260 >
					
		
	
            
   <div id="cover<%= i%>" class="pics" style="position: relative; overflow: hidden;width:100%;;" align="center">
            <img src="../images/<%=imgs[0] %>"  style="position: absolute; top: 0px; left: 0px; z-index: 4; opacity: 1; width: 100%; height: 250px; display: block;">
            <img src="../images/<%=imgs[1] %>"  style="position: absolute; top: 0px; left: 0px; z-index: 3; opacity: 0; width: 100%; height: 250px; display: none;">
            <img src="../images/<%=imgs[2] %>" style="position: absolute; top: 0px; left: 0px; z-index: 3; opacity: 0; width: 100%; height: 250px;">
       </div>
   
		

    </th>
  </tr>
  <tr height=40>
    <th width="201" scope="row"class="color-lightgray">原价</th>
    <td width="411"><%=g.getgOrigionPrice() %></td>
    <th width="209"class="color-lightgray">现价</th>
    <td width="366"><%=g.getgPrice() %></td>
  </tr>
  <tr height=150>
    <th scope="row"class="color-lightgray">描述</th>
    <td colspan="3"><%=g.getgDetailDesc() %></td>
  </tr>
  <tr height=40>
    <th scope="row"class="color-lightgray">联系人</th>
    <td colspan="3"><%=contacts.get(i).getcName() %>——电话<%=contacts.get(i).getcPhone() %></td>
  </tr>
  <tr height=40>
    <th scope="row"class="color-lightgray">地点</th>
    <td colspan="3"><%=contacts.get(i).getcCity() %>——<%=contacts.get(i).getcRegion() %></td>
  </tr>
  
</table>
<div class="clear"></div>
	<%} %>

</body>
</html>