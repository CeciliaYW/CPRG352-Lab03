<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Note</title>
    </head>
    <body>
        <h1>Simple Note Keeper</h1>
        <h2>View Note</h2>
        <form method="POST" action="note">
        <p>Title: <input type="text" name="edit_title" value="${note.title}"></p>
        <p>Contents: <textarea name="edit_contents" rows="5">${note.content}</textarea></p>
        <input type="submit" value="Save">
        </form>
    </body>
</html>
