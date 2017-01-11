package tourcompanies

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import grails.web.mapping.LinkGenerator
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication

import static org.springframework.http.HttpStatus.NO_CONTENT

@Secured(["permitAll"])
class RegisterController {

    UserController userController
    def mailService
    def roleCompany = Role.findByAuthority("ROLE_COMPANY")

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
//        def confirmCode = UUID.randomUUID().toString()
//        println confirmCode
//        try {
//            mailService.sendMail {
//                to "sergeyhk89@rambler.ru"
//                subject "Hello Fred"
//                text 'How are you?'
//            }
//        }
//        catch (Exception e){
//            println e
//
//        }

    }

    @Transactional
    def save() {

        // Check to see if the username already exists
        def user = User.findByUsername(params.username)
        if (user) {
            flash.message = "User already exists with the username '${params.username}'"

            println user.toString()
            render(template: 'register', model: [user: user])

//            redirect(action: 'index')
        }

        // User doesn't exist with username. Let's create one
        else {

            // Make sure the passwords match
            if (!params.password || params.password != params.password2) {
                flash.message = "Passwords do not match"

//                return
                render(template: 'register', model: [user: user])
            }

            // Passwords match. Let's attempt to save the user
            else {
                // Create user
                user = new User(params)

                if (user == null) {
                    transactionStatus.setRollbackOnly()
                    notFound()
                    return
                }

                def confirmCode = UUID.randomUUID().toString()
                user.confirmCode = confirmCode



                if (user.save(faush: true)) {
//                   LinkGenerator grailsLinkGenerator
                    try {
                        mailService.sendMail {
//                    multipart true
                            to "sergeyhk89@mail.ru"
//                            bcc "sergeyhk89@mail.ru"
                            cc "serghar89@gmail.com"
                            subject "Activation Confirmation"
                            html """<h3>Dear  ${user.username}</h3>
                                    <p>Thank you for register in our website.</p>
                                    
                                    <p>Your activation account code is <span>${user.confirmCode}</span></p>
                                    <p>Activation page link <a href="http://localhost:8080${createLink(action:'activate',params:[username: user.username])}">slink</a></p>
                                    """
//                    attachBytes "Some-File-Name.xml", "text/xml", contentOrder.getBytes("UTF-8")

//                    html g.render(template:"mailtemplate",model:[code:userInstance.confirmCode])

                        }
                    }
                    catch (Exception e) {
                        println e
                    }

                    UserRole.create(user, roleCompany)

                    flash.message = "Please activate your account"
//                redirect action: "activate", register_user: user

//                [register_user: user]
                    render(template: 'activate', model: [user: user])
                } else {
                    render(template: 'register', model: [user: user])

                }

//                redirect controller: "user", action: "save" , params: [user: user]
//                if(user.save()){
//                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.username, user.password);
////                    Authentication authentication = authManager.authenticate(token);
//                    mailService.sendMail {
//                        to user.email
//                        subject "New User Confirmation 2"
//                        body "${token}  test"
//                    }
//                }
//                if (user.save(flush: true)) {
//                    UserRole.create(user, Role.findByAuthority("ROLE_COMPANY"))
//
////                    // Login user
////                    render([success: true, username: springSecurityService.authentication.name]
////                            as JSON)
////
////                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.username, user.password);
////                    Authentication authentication = authManager.authenticate(token);
////                    SecurityContextHolder.getContext().setAuthentication(authentication);
////                    //this step is important, otherwise the new login is not in session which is required by Spring Security
////                    request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
//                    mailService.sendMail {
//                        to user.email
//                        subject "New User Confirmation"
//                           body "sdfsfdsf222 33"
////                        html g.render(template:"mailtemplate",model:[code:userInstance.confirmCode])
//                    }
//
//
//
//                    redirect(controller: 'user', action: 'index')
//                }
//            else {
//
//                    redirect(controller: 'auth', action: 'login')
//                }

            }
        }
    }

    def activate = {

        if (!User.findByUsername(params?.username)) {

            flash.message = "You account is successfully activated  2."

            render(view: "activate")
            return
        }


        def user = User.findByUsername(params?.username)

        if (user?.confirmCode == params?.confirmCode) {
            user.enabled = true
            user.save()

            flash.message = "You account is successfully activated."

            redirect(controller: "login", action: "index")
            return

        }

        flash.message = "User name or code activation not correct."

        render(view: "activate")


    }

}


