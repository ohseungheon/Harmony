package com.harmony.www_service.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        HttpSession session = request.getSession(false);

        if (savedRequest != null) {
            // 원래 요청한 URL로 리다이렉트 (인증이 필요해서 자동으로 로그인 페이지로 이동됐을 때)
            String targetUrl = savedRequest.getRedirectUrl();
            getRedirectStrategy().sendRedirect(request, response, targetUrl);
        } else {
            // 로그인 페이지에 요청이 왔을 때 저장된 session의 referer 헤더값을 사용하여 리다이렉트할 URL 설정
            // (메인이 아닌 페이지에서 직접 로그인 페이지로 이동해서 로그인했을 때)
            if (session != null) {
                String prevPage = (String) session.getAttribute("prevPage");
                if (prevPage != null) {
                    session.removeAttribute("prevPage");
                    System.out.println(prevPage);
                    System.out.println(prevPage);
                    response.sendRedirect(prevPage);
                    return;
                }
            }
            response.sendRedirect("/");
        }
    }
}
