package com.eon.springbootdatamanagement.configuration;

/*
 * @Created At 31/03/2025
 * @Author suroj.awal
 */

import com.eon.springbootdatamanagement.util.Constant;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * A filter that logs user ID and IP address for each HTTP request.
 * This class extends OncePerRequestFilter to ensure the filter is executed only once per request.
 */
@WebFilter(urlPatterns = "/*")
@Slf4j
public class MDCFilter extends OncePerRequestFilter {
    /**
     * Filter method to log userId and ipAddress for each incoming HTTP request.
     * Ensures that these values are added to the MDC for structured logging.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @param chain    the filter chain
     * @throws ServletException if a servlet error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull FilterChain chain) throws ServletException, IOException {
        try {
            // Extract userId from headers, query parameters, or session (adjust as per your requirement)
            String userId = SecurityContextHolder.getContext().getAuthentication().getName();

            // Get the IP address of the request
            String ipAddress = getClientIpAddress(request);

            // Add values to MDC
            MDC.put("userId", userId);
            MDC.put("requestId", request.getHeader("RequestId"));
            MDC.put("ipAddress", ipAddress);

            // Proceed with the request
            chain.doFilter(request, response);
        } finally {
            // Clear MDC to prevent data leakage in subsequent requests
            MDC.clear();
        }
    }

    /**
     * Extracts the client IP address from the request, considering possible proxies.
     */
    private String getClientIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || Constant.UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || Constant.UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || Constant.UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        // If multiple IPs in X-Forwarded-For, use the first one
        if (ipAddress != null && ipAddress.contains(",")) {
            ipAddress = ipAddress.split(",")[0].trim();
        }
        return ipAddress;
    }
}