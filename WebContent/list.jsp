<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
            	<%@ taglib uri="/struts-tags" prefix="s" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table border="1px" cellpadding="8px">
	<tr>
		<th>Employee Id</th>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Salary</th>
		<th>Delete</th>
		<th>Update</th>
	</tr>
	<s:iterator value="employeeList" status="userStatus" >
		<tr>
			<td><s:property value="id" /></td>
			<td><s:property value="firstName" /></td>
			<td><s:property value="lastName" /></td>
			<td><s:property value="salary" /></td>
			<td>
                <s:url id="deleteURL" action="deleteEmployee">
					<s:param name="id" value="%{id}"></s:param>
				</s:url>
                <s:a href="%{deleteURL}">Delete</s:a>
             </td>
             
             <td>
                <s:url id="updateURL" action="updateEmployee">
					<s:param name="id" value="%{id}"></s:param>
				</s:url>
                <s:a href="%{updateURL}">Update</s:a>
             </td>
		</tr>
	</s:iterator>
	</table>
</body>
</html>