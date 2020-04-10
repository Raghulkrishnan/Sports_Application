/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.web;

import edu.iit.sat.itmd4515.rbalasubramanian1.model.security.User;
import edu.iit.sat.itmd4515.rbalasubramanian1.service.UserService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author raghul
 */
@Named
@RequestScoped
public class LoginController {
    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());

    @Inject
    private SecurityContext securityContext;
    @Inject
    private FacesContext facesContext;
    @Inject
    private UserService userServ;

    private User user;

    public LoginController() {
    }

    @PostConstruct
    private void postContruct() {
        LOG.info("Inside the LoginController.postConstruct method: ");
        user = new User();
    }

    // helper methods
    public String getAuthenticatedUser() {
        return facesContext.getExternalContext().getRemoteUser();
    }

    public String getAuthenticatedUserGroups() {
        user = userServ.findByUsername(getAuthenticatedUser());
        LOG.info("User as group count of...: " + user.getGroups().size());
        return user.getGroups().toString();
    }

    public boolean isAdmin() {
        return securityContext.isCallerInRole("ADMIN_ROLE");
    }

    public boolean isCoach() {
        return securityContext.isCallerInRole("COACH_ROLE");
    }

    public boolean isOwner() {
        return securityContext.isCallerInRole("OWNER_ROLE");
    }

    // action method
    public String doLogin() {
        LOG.info("LoginController.doLogin for user: " + user.getUserName());
        
        Credential credential = new UsernamePasswordCredential(user.getUserName(), new Password(user.getPassword()));
        AuthenticationStatus status = securityContext.authenticate(
            (HttpServletRequest) facesContext.getExternalContext().getRequest(),
            (HttpServletResponse) facesContext.getExternalContext().getResponse(),
            AuthenticationParameters.withParams().credential(credential)
        );
        
        LOG.info("Auth Status: " + status.toString());

        switch (status) {
            case SEND_CONTINUE:
                LOG.info("Received SEND_CONTINUE");
                break;
            case SEND_FAILURE:
                LOG.info("Received SEND_FAILURE");
                return "/error.xhtml";
            case SUCCESS:
                LOG.info("Received SUCCESS");
                break;
            case NOT_DONE:
                LOG.info("Received NOT_DONE");
                return "/error.xhtml";
        }
//      on successful login... navigation case
        return "/welcome.xhtml?faces-redirect=true";
    }

    public String doLogout() {
        LOG.info("LoginController.doLogout for user: " + getAuthenticatedUser());
        HttpServletRequest req = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        
        try {
            req.logout();
        } catch (ServletException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return "/error.xhtml";
        }
//        on successful logout - navigation case
        return "/login.xhtml?faces-redirect=true";
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
