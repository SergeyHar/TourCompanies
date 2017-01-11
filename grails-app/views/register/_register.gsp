
<g:hasErrors bean="${this.user}">
    <ul class="errors" role="alert">
        <g:eachError bean="${this.user}" var="error">
            <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                    error="${error}"/></li>
        </g:eachError>
    </ul>
</g:hasErrors>

<g:formRemote  autocomplete='off' class="form-signin" update="reg"
               style="max-width: 300px;  margin: 0 auto;" name="myForm" url="[controller: 'register', action: 'save']" onSuccess="show_active()" >
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <div style="text-align: center;">

        <div class="form-group">
            <label for='username' class="control-label">User Name:</label>

            <div class="controls">
                <g:textField name="username" value="${user?.username}"/>
            </div>
        </div>

        <div class="form-group">
            <label for='email' class="control-label">Email:</label>

            <div class="controls">
                <g:textField ttype="email" name="email" placeholder="email" id="email" value="${user?.email}"/>
            </div>
        </div>

        <div class="form-group">
            <label for='password' class="control-label">Password:</label>

            <div class="controls">
                <g:passwordField name="password" placeholder="password" id="password"/>
            </div>
        </div>

        <div class="form-group">
            <label for='password2' class="control-label">Password:</label>

            <div class="controls">
                <g:passwordField  name="password2" placeholder="password" id="password2"/>
            </div>
        </div>

        <div class="form-group">
            %{--<g:remoteLink controller="register" action="save" update="div_activate">Save</g:remoteLink>--}%

            <g:actionSubmit value="Save" id="submit" class="btn btn-success"/>
        </div>
    </div>
</g:formRemote>
