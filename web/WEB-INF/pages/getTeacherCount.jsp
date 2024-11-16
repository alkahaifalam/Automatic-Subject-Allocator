<%-- 
    Document   : getTeacherCount
    Created on : May 10, 2024, 12:48:08 AM
    Author     : suhaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

        <style>
            .filter{
                position: fixed;
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                z-index: -1;
                /*                background: rgb(76,160,233);
                                babackground: linear-gradient(43deg, rgba(76,160,233,1) 0%, rgba(199,74,233,1) 66%); */
                height: 100vh;
                background-image: linear-gradient(lightblue, darkblue);
            }
            .table{
                text-align: center;
                border-collapse: collapse;
                background-color: #fafafa;
                border-radius: 10px;
                overflow: hidden;
                box-shadow: 0 5px 12px rgba(32, 32, 32, .3);

            }
            .table > tbody > tr > td,th{
                padding: 12px;
                vertical-align: middle;
            }
            th{
                text-align: center;
                background-color: #003879;
                color: #fafafa;
                font-family: 'Roboto', sans-serif;
            }
            td{
                font-family: 'Open Sans', sans-serif;
            }
            tr:nth-child(odd){
                background-color: #eeeeee;
            }

            .header{
                text-align: center;
                background-color: #003879;
                border-radius: 10px 10px 0 0 ;
                padding: 3px;
            }

            input{
                border-radius: 5px;
                background-color: whitesmoke
            }
        </style>

    </head>
    <body>

        <div class="filter"></div>
        <div  style="display: flex; justify-content: center; height: 750px">
            <div style="width: 60%; height: fit-content; padding: 30px;" >
                <header class="ScriptHeader">
                    <div class="rt-container">
                        <div class="col-rt-12">
                            <div class="rt-heading">
                                <h1>Select required no. of teachers</h1>
                                <!--<p style="font-size: 18px;">All the preferences selected by teachers are shown here.</p>-->
                            </div>
                        </div>
                    </div>
                </header>
                <form action="fc?type=model&page=AllocateSubjects" method="POST">
                    <table class="table">
                        <tr>
                            <th width="33.33%">subject Code</th>
                            <th width="33.33%">Subject Name</th>
                            <th width="33.33%">Required Teachers</th>
                        </tr>
                        <%  int i = 1; %>
                        <c:forEach var="subject" items="${subjects}">
                            <!-- Code to be executed for each item in the collection -->
                            <tr>
                                <td width="33.33%"> ${subject.getCode()} </td>
                                <td width="33.33%"> ${subject.getName()} </td>
                                <td width="33.33%"> <input type="number" value="1" min="1" name="teacher_count<%=i%>" size="7" aria-describedby="basic-addon1"> </td>
                            </tr>
                            <% i++; %>
                        </c:forEach>
                    </table>
                    <div style="margin: 5px; text-align: right;">
                        <button class="btn btn-primary" style="box-shadow: 0 5px 12px rgba(32, 32, 32, .3);">Allocate Subjects</button>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
