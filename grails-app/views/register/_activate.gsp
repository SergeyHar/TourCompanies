
<div id="div_activate">


    <lable  style="    font-size: 30px;">Activate account</lable>


    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.user}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.user}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>



    <g:form controller="register" action="activate" method="GET">
        <div class="form-group">




            <div class="form-control" style="height: auto; background: transparent; border:none">

                <g:if test="${!user?.username}">
                    <lable from="username" style="font-size: 18px;">Login</lable>

                    <g:textField name="username" value="" id="username" style="width: 100%; border-right: 15px;"/>
                </g:if>
                <g:else>
                    <g:hiddenField name="username" value="${user?.username}" />
                </g:else>



                <lable from="confirmCode" style="    font-size: 18px;">Code activation</lable>
                <g:textField name="confirmCode" placheholder="code" id="code" style="width: 100%; border-right: 15px;"/>
            </div>
        </div>

        <div class="form-group" style="text-align: center;">
            <g:submitButton value="Submit" class="btn btn-success" name="submit"/>
        </div>

    </g:form>

</div>