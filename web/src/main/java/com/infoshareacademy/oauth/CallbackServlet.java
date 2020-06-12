package com.infoshareacademy.oauth;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeCallbackServlet;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfoplus;
import com.infoshareacademy.context.ContextHolder;
import com.infoshareacademy.domain.dto.UserGoogleView;
import com.infoshareacademy.domain.dto.UserView;
import com.infoshareacademy.service.UserService;
import com.infoshareacademy.service.mapper.GoogleUserMapper;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/oauth2callback")
public class CallbackServlet extends AbstractAuthorizationCodeCallbackServlet {

    @EJB
    private OAuthBuilder oAuthBuilder;

    @EJB
    private UserService userService;

    @Inject
    private GoogleUserMapper googleUserMapper;

    @Override
    protected void onSuccess(HttpServletRequest req, HttpServletResponse resp, Credential credential) throws ServletException, IOException {
        Oauth2 oauth2 = oAuthBuilder.buildOauth(credential);
        Userinfoplus user = oauth2.userinfo().get().execute();
        UserGoogleView userGoogleView = googleUserMapper.mapGoogleResponseToUserGoogleView(user);
        UserView userView = userService.login(userGoogleView);
        new ContextHolder(req.getSession(), userView);
        resp.sendRedirect("/");
    }

    @Override
    protected AuthorizationCodeFlow initializeFlow() throws ServletException, IOException {
        return null;
    }

    @Override
    protected String getRedirectUri(HttpServletRequest httpServletRequest) throws ServletException, IOException {
        return null;
    }

    @Override
    protected String getUserId(HttpServletRequest httpServletRequest) throws ServletException, IOException {
        return null;
    }
}
