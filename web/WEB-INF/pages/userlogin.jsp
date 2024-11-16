<%-- 
    Document   : user
    Created on : Apr 28, 2024, 10:58:48 PM
    Author     : suhaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta charset="utf-8">
        <title></title>
        <link rel="stylesheet" type="text/css" href="css/style_1.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>

    </head>

<body>
  <div class="login-root">
    <div class="box-root flex-flex flex-direction--column" style="min-height: 100vh;flex-grow: 1;">
      <div class="box-root padding-top--0 flex-flex flex-direction--column" style="flex-grow: 1; z-index: 9;">
          
          <!-- navbar starts -->  
        <nav class="navbar navbar-expand-lg bg-dark rounded" >
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#" style="color: white">Subject Allocator</a>
                </div>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarNavDropdown">
                    <ul class="navbar-nav">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false" style="color: white">
                                
                              Profile
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                                    <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0"/>
                                    <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8m8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1"/>
                                </svg><%--${teacher_name} --%>  
                                    <% String teacher_name = (String)session.getAttribute("teacher_name"); %>
                                    <%= teacher_name %>
                            </div>
                        </li>
                    </ul>
                </div>
            <ul class="nav navbar-nav navbar-right">
              <li><a href="fc?type=model&page=LogoutModel" style="color: white"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
            </ul>
            </div>
    </nav>
    <!-- navbar ends -->
          <div></div>
            <div class="box-root padding-top--48 padding-bottom--24 flex-flex flex-justifyContent--center">

                <img src="images/IntegralUniversityLogo.png" width="450" height="80" alt="alt"/>

            </div>
            <div class="formbg-outer">
              <div class="formbg">
                <div class="formbg-inner padding-horizontal--48">

                    <div class="d-flex justify-content-center align-items-center"><h1 style="color: black" >Select Subjects</h1></div>
                    <form id="stripe-login" action="fc?type=model&page=GetPreference" method="POST">

                        <div class="field padding-bottom--24">
                            <label for="pref1">Preference 1</label>
                            <select name="pref1" class="form-select">
                                <option selected hidden>Preference 1</option>
                                <option value="CS-301">Design and Analysis of Algorithms</option>
                                <option value="CS-314">Compiler Design</option>
                                <option value="EC-209">Digital Electronics</option>
                                <option value="CS-281">Graph Theory & Applications</option>
                                <option value="CS-304">Theory of Automata & Formal Languages</option>
                                <option value="CS-305">Computer Network</option>
                            </select>
                        </div>
                        <div class="field padding-bottom--24">
                            <label for="pref2">Preference 2</label>
                            <select name="pref2" class="form-select">
                                <option selected hidden>Preference 2</option>
                                <option value="CS-301">Design and Analysis of Algorithms</option>
                                <option value="CS-314">Compiler Design</option>
                                <option value="EC-209">Digital Electronics</option>
                                <option value="CS-281">Graph Theory & Applications</option>
                                <option value="CS-304">Theory of Automata & Formal Languages</option>
                                <option value="CS-305">Computer Network</option>
                            </select>
                        </div>
                        <div class="field padding-bottom--24">
                            <label for="pref3">Preference 3</label>
                            <select name="pref3" class="form-select">
                                <option selected hidden>Preference 3</option>
                                <option value="CS-301">Design and Analysis of Algorithms</option>
                                <option value="CS-314">Compiler Design</option>
                                <option value="EC-209">Digital Electronics</option>
                                <option value="CS-281">Graph Theory & Applications</option>
                                <option value="CS-304">Theory of Automata & Formal Languages</option>
                                <option value="CS-305">Computer Network</option>
                            </select>
                        </div>

                        <div class="field padding-bottom--24">
                          <input type="submit" name="submit" value="Continue" style="background-color: black; color: white">
                        </div>

                    </form>

                </div>
            </div>
        </div>
      </div>
    </div>
  </div>
    
</body>

</html>