<%@page import="com.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>

<nav class="navbar navbar-dark bg-dark" style="background-color: #e3f2fd;">
        <a class="navbar-brand" href="#">User Management</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

       
    </nav>

<meta charset="ISO-8859-1">


<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/users.js"></script>

</head>
<body>

<div class="p-3 mb-2 bg-secondary text-white">

<div class="container">
	<div class="row">
		<div class="col-8">
		
			 <h3> <span class="badge badge-secondary">Add User</span></h3>
			 
			   <div class="p-3 mb-2 bg-light text-dark">
			
			<form id="formUser" name="formUser" method="post" action="users.jsp">
			
			
			
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                        
                      01) User ID:
				<input id="userCode" name="userCode" type="text" class="form-control form-control-sm" placeholder="001">
			
                            
                        </div>
                    </div>
                    <!--  col-md-6   -->

                    <div class="col-md-6">
                        <div class="form-group">
                        
                        	02) First Name :
				<input id="userName" name="userName" type="text" class="form-control form-control-sm" placeholder="First Name">
				<br>
                            
                        </div>
                    </div>
                    <!--  col-md-6   -->
                </div>
                
                  <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                        
                    03) Last Name:
				<input id="fName" name="fName" type="text" class="form-control form-control-sm" placeholder="Last Name">
				<br>
			
                            
                        </div>
                    </div>
                    <!--  col-md-6   -->

                    <div class="col-md-6">
                        <div class="form-group">
                        
                        04)	Phone:
				<input id="userCont" name="userCont" type="text" class="form-control form-control-sm" placeholder="0711234568">
				<span style="color:red;" id="error_contact"></span>
				
                        </div>
                    </div>
                    <!--  col-md-6   -->
                </div>
			
				
				
				05) Address:
				<input id="userAdd" name="userAdd" type="text" class="form-control form-control-sm" placeholder="address">
				<br>
				
				06) Email Address:
				<input id="userEmail" name="userEmail" type="text" class="form-control form-control-sm" placeholder="abc@gmail.com">
				<span style="color:red;" id="error_email"></span><br>
				 
				07) Password:
				<input id="userPwd" type="password" name="userPwd" type="text" class="form-control form-control-sm" placeholder="Password">
				<br>
				
				
				<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"><br>
				<input type="hidden" id="hidUserIDSave" name="hidUserIDSave" value=""><br>
			</form>
			
			<div id="alertSuccess" class="alert alert-success"></div>
			<div id="alertError" class="alert alert-danger"></div>
			<br>

			<div id="divUsersGrid">
				<%
					User userObj = new User();
									out.print(userObj.readUsers());
				%>
			</div>
		</div>
	</div>
</div>
</div>
</body>
</html>