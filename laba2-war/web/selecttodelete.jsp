<%-- 
    Document   : selecttodelete
    Created on : 03.05.2020, 21:32:33
    Author     : lomatik
--%>

<%@page import="java.util.List"%>
<%@page import="com.library.Books"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Вибір даних для видалення</title>
        </head>
        <body>
            <h1>Зміст</h1>
            <h2>Оберіть дані для видалення, а потім натисніть кнопку "Видалити"</h2>
            <table>
            <tr><th>Вибір</th><th>Ім'я автора</th><th>Прізвище автора</th><th>Назва книги</th><th>Рік видавництва</th><th>Місто видавництва</th><th>Назва жанру</th></tr>
            <%if (request.getAttribute("Books") != null) {
                   for(Books item: (List<Books>) request.getAttribute("Books")) {
                       out.println("<tr><td><input type=\"radio\" name=\"item\" "
                + " id = \""+ item.getId()+"\" /></td><td>"+ item.getName_of_author() + "</td><td>" 
                + item.getSurname_of_author() + "</td><td>" + item.getName_of_book()
                + "</td><td>" + item.getYear_of_book() + "</td><td>"
                + item.getCity_of_print() + "</td><td>"+ item.getGenre() + "</td></tr>");
                   }
            }%>           
            </table>
            <input id="btn" type="button" name="snd_delete_btn" value="Видалити обране">
            <script>
                btn.onclick = function() {
                var s = document.getElementsByName("item");
                var idchecked;
                for (var i = 0; i < s.length; i++) {
                    if (s[i].checked) {
                        idchecked = s[i].id;
                    }
                }
                if (idchecked.trim() === '') {
                    alert("Ви нічого не обрали для видалення, повторіть спробу");
                }
                else{
                    location.href = "/laba2-war/DeleteSelected?idchecked=" + idchecked;
                };
            };
            </script>
        </body>
    </html>
