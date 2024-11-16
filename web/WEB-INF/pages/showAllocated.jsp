<%-- 
    Document   : show
    Created on : May 4, 2024, 1:09:36 AM
    Author     : suhaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <!--<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">-->
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
                background: linear-gradient(43deg, rgba(76,160,233,1) 0%, rgba(199,74,233,1) 66%); */
                height: 100vh;
                background-image: linear-gradient(lightblue, darkblue);
            }
            .table{
                text-align: center;
                border-collapse: collapse;
                background-color: #fafafa;
                border-radius: 12px;
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
        </style>
        
    </head>
    <body>
       
        <!--nav bar starts-->
        <nav class="navbar navbar-expand-lg navbar-inverse" style="height: 65px; background-image: linear-gradient(15deg, #13547a 0%, #80d0c7 100%); border: 0; border-radius: 0">
            <div class="container-fluid" style="margin-top: 5px;">
                <div class="navbar-header" style="padding-left: 5px; background-color: #003879; border-radius: 4px;">
                    <a class="navbar-brand" href="#">Subject Allocator</a>
                </div>

                <ul class="nav navbar-nav">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="color: white">
                          Profile
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                              <span class="glyphicon glyphicon-user"></span>  Hello, Admin
                        </div>
                    </li>      
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="fc?type=model&page=LogoutModel" style="color: white"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                </ul>
            </div>
        </nav>
  
        <!--nav bar ends-->
        
        <div class="filter"></div>
        
        <div style="display: flex; justify-content: center; height: 750px">
            <div style="width: 60%; height: fit-content; padding: 30px;" >
                <!--<div style="text-align: center; margin-bottom: 10px" ><h1 style="display: inline" >Allocated Subjects</h1></div>-->
                <header class="ScriptHeader">
                    <div class="rt-container">
                        <div class="col-rt-12">
                                <div class="rt-heading">
                                <h1>Allocated Subjects</h1>
                                <p style="font-size: 18px;">All the subjects allocated to teachers are shown here.</p>
                            </div>
                        </div>
                    </div>
                </header>
                <!--<div style="border: 2px solid black;">-->
                    <table class="table">
                        <tr>
                            <th width="25%">Username</th>
                            <th width="25%">Teacher Name</th>
                            <th width="25%">Subject Code</th>
                            <th width="25%">Subject Name</th>
                        </tr>
                        <c:forEach var="allocation" items="${allocations}">
                            <!-- Code to be executed for each item in the collection -->
                            <tr>
                                <td width="25"> ${allocation.getTeacher().getUsername()} </td>
                                <td width="25"> ${allocation.getTeacher().getName()} </td>
                                <td width="25%"> ${allocation.getSubject().getCode()} </td>
                                <td width="25%"> ${allocation.getSubject().getName()} </td>
                            </tr>
                        </c:forEach>
                    </table>
                <!--</div>-->
                
            </div>
        </div>
    </body>
</html>
