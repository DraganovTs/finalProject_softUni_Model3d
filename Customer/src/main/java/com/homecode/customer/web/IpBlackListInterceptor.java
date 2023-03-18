package com.homecode.customer.web;


import com.homecode.library.service.impl.BlackListServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.View;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

import java.util.Locale;
import java.util.Map;

@Component
public class IpBlackListInterceptor implements HandlerInterceptor {

    private final BlackListServiceImpl blackListService;
    private final ThymeleafViewResolver tlvr;

    @Autowired
    public IpBlackListInterceptor(BlackListServiceImpl blackListService, ThymeleafViewResolver tlvr) {
        this.blackListService = blackListService;
        this.tlvr = tlvr;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler)
            throws Exception {
        String ip = getIpAddressFromRequest(request);

        if (this.blackListService.isBlacklisted(ip)) {
            View blockedView = tlvr.resolveViewName("error", Locale.getDefault());
            if (blockedView != null) {
                blockedView.render(Map.of(), request, response);
            }
            return false;
        }

        return true;
    }

    private String getIpAddressFromRequest(HttpServletRequest request) {

        String ipAddress = null;

        String xffHeader = request.getHeader("X-Forwarded-For");
        if (xffHeader != null && !xffHeader.equals("unknown")) {
            int commaIdx = xffHeader.indexOf(",");
            if (commaIdx > 0) {
                ipAddress = xffHeader.substring(0, commaIdx - 1);
            }
        }

        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        return ipAddress;
    }

}
