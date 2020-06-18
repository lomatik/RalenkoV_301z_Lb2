<%-- 
    Document   : index
    Created on : 18.06.2020, 20:04:19
    Author     : lomatik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Система керування довідниками бібліотеки</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div><h1>Вітаємо у системі керування довідниками бібліотеки</h1></div>
        <%
        String message = null;
        String sessionID = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
        for(Cookie cookie : cookies){
	if(cookie.getName().equals("message")) message = cookie.getValue();
	if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
        }
        }
        %>
<h2><%=message%></h2>
<h4>Session ID = <%=sessionID %></h4>
        <form action="Show_genres_to_insert">
        <input type="submit" name="insert" value="Створити книгу"><br>
        </form>
        <form>
        <input type="button" name="update" id ="up_btn" value="Модифікувати книгу"><br>
        </form>
        <form>
        <input type="button" name="delete" id ="del_btn" value="Видалити книги"><br>
        </form>
        <form>
        <input type="button" name="show_book" id ="shw_btn" value="Показати книги"><br>
        </form>
        <form action="insert_genre.html">
        <input type="submit" name="show_book" value="Створити жанр"><br>
        </form>
        <form action="update_genre.html">
        <input type="submit" name="show_book" value="Оновити жанр"><br>
        </form>
        <form action="delete_genre.html">
        <input type="submit" name="show_book" value="Видалити жанри"><br>
        </form>
        <form action="show_genre.html">
        <input type="submit" name="show_book" value="Показати жанри"><br>
        </form>
        <form action="LogOutFromSystem" method="post">
        <input type="submit" value="Вийти" >
        </form>
        <script>
            up_btn.onclick = function() {
                location.href = "/laba2-war/Show_genres_to_insert?next_jsp=update";
            };
            del_btn.onclick = function() {
                location.href = "/laba2-war/Show_genres_to_insert?next_jsp=delete";
            };
            shw_btn.onclick = function() {
                location.href = "/laba2-war/Show_genres_to_insert?next_jsp=show_book";
            };
        </script>
    </body>
</html>
