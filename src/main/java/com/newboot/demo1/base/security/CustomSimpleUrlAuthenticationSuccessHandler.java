package com.newboot.demo1.base.security;

import com.newboot.demo1.standard.util.Ut;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import java.io.IOException;

public class CustomSimpleUrlAuthenticationSuccessHandler  extends SimpleUrlAuthenticationSuccessHandler {

    protected final Log logger = LogFactory.getLog(this.getClass());
    private RequestCache requestCache = new HttpSessionRequestCache();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SavedRequest savedRequest = this.requestCache.getRequest(request,response);
        clearAuthenticationAttributes(request);

        String targetUrl = savedRequest != null ? savedRequest.getRedirectUrl() : getDefaultTargetUrl();
        targetUrl+=(targetUrl.contains("?")?"&":"?")+"msg="+ Ut.url.encode("환영합니다.");
        getRedirectStrategy().sendRedirect(request,response,targetUrl);
    }
}
