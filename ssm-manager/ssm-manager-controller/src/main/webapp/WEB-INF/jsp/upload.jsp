<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SSM项目实战-上传</title>
<body>
	<form action="uploadFile" method="post" enctype="multipart/form-data">
		文件1: <input type="file" name="myfiles" /><br /> 文件2: <input
			type="file" name="myfiles" /><br /> 文件3: <input type="file"
			name="myfiles" /><br /> <input type="submit" value="上传">
	</form>
	<a href="download?fileName=20170206151413281020950798404189.jpg">下载</a>
</body>
</head>