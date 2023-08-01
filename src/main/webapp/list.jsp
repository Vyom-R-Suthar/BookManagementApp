<%@ page import="com.book.manage.*" %>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Record</title>
<style type="text/css">
	#fcontainer{
		display : flex;
		align-items : center;
		justify-content : center;
		height:100vh;
		width:100%;
	}
	#tableContainer{
		height:auto;
		width:450px;
		/*border:1px solid yellow;*/
		text-align : center;
		/*display:flex;*/
		/*justify-content:center;*/
		/*align-items:center;*/
		/*overflow: scroll;*/
	}
	table,th,td{
		border : 2px solid white;
		border-collapse : collapse;
		color : white;
	}
	th{
		color : aquamarine;
		border-color : white;
	}
	#tableContainer{
		/*text-align : center;*/
	}
	#table1{
		/*margin-left : 500px;*/
		display : block;
		margin-left:20px;
		margin-right:20px;
	}
	.buttons{
        background-color: aquamarine;
        border: 1px solid white;
        border-radius: 5px;
        height: 30px;
        margin-right:10px;
        font-weight:bold;
        cursor:pointer;
    	}
    .buttonContainer{
    	margin-top : 30px;
    	/*margin-left: 630px ;*/
    	display:block;
    }
    .buttons:hover{
    	background-color : black;
    	color : aquamarine;
    	border : 1px solid aquamarine;
    }
    
    #sortContainer{
    	display:block;
    }
    #searchContainer{
    	display:block;
    }
    body{
    	background-size:cover;
    }
    @media(max-width:600px){
    	#tableContainer{
    		/*height:900px;*/
    	}
    }
    #tableOuter{
    	height:60vh;
		width:450px;
		overflow : scroll;
		/*border:1px solid green;*/
    }
</style>
</head>
<body background="book1.jpg"  bgproperties="fixed">
<div id="fcontainer">
<div id="tableContainer">
<% //ArrayList<Book> books = (ArrayList<Book>) request.getAttribute("books");
	ArrayList<Book> books = null;;
	if(request.getAttribute("sort") != null && ((String)request.getAttribute("sort")).equals("price")){
		books = (ArrayList<Book>) BookDao.getSortedPriceList();
	}else if(request.getAttribute("sort") != null && ((String)request.getAttribute("sort")).equals("title")){
		books = (ArrayList<Book>) BookDao.getSortedTitleList();
	}else if(request.getAttribute("search") != null){
		books = (ArrayList<Book>)BookDao.searchParticular((String)request.getAttribute("search"));
	}else{
	  books = (ArrayList<Book>)BookDao.getList();
	}
   if(books.isEmpty()){
%> <h2 style="color:white;"> No Books to show !</h2>
<% }else{
%> 

<h1 style="color:white;display:block">Books Record</h1>

<div id="sortContainer">
<a href="SortPriceServlet"><button style="margin-bottom : 20px;" class="buttons">Sort according to price</button></a>
<a href="SortTitleServlet"><button style="margin-bottom : 20px;" class="buttons">Sort according to title</button></a>
</div>

<form id="searchContainer" action="SearchServlet" method="post">
	<input style="border-radius:5px;" type="text" placeholder="   book title"  name = "bookTitle"><input class="buttons" style="height:20px;margin-bottom : 20px;" type="submit" value = "search">
</form>
<div id="tableOuter">
<table id="table1">
	<tr><th>Sr no.</th><th>Book Title</th><th>Book Author</th><th>Book Publisher</th><th>Book Price</th></tr>
<%  int i = 1; 
	for(Book b : books){
%>  <tr> <td><%=i++%></td><td><%= b.getTitle() %></td><td><%= b.getAuthor() %></td><td><%= b.getPublisher() %></td><td><%= b.getPrice() %></td></tr>
<% }
%>
</table>
</div>
<%} 
%>		<div class="buttonContainer">
		<a href="index.html"><button class="buttons">Home</button></a>
		<a href="insert.html"><button class="buttons">Insert</button></a>
		<a href="update.html"><button class="buttons">Update</button></a>
		<a href="delete.html"><button class="buttons">Delete</button></a>
		</div>
</div>
</div>
</body>
</html>