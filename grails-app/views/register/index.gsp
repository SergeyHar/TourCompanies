<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'register.label', default: 'Register')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div id="create-user" class="content scaffold-create" role="main">
    <h1><g:message code="default.rgeister.label" default="Register form"/></h1>

    <div class="row" style="padding-top: 50px" id="reg">
        <g:render template="register" />

        %{--<g:remoteLink controller="event" action="showContacts" update="divContactList">Show Contacts!</g:remoteLink>--}%


    </div>
</div>
</body>
</html>