package com.nnk.springboot.app;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import com.nnk.springboot.util.RequestLoggingUtil;

/**
 * This class allows to log every incoming endpoints
 * 
 * @author Rémi Deronzier
 */
@Component
public class ApplicationRequestInterceptor extends HandlerInterceptorAdapter {

    private final static Logger LOGGER = LoggerFactory.getLogger(ApplicationRequestInterceptor.class);

    /**
     * @param request
     * @param response
     * @param handler
     * @return boolean
     * @throws IOException
     */
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws IOException {
        String postData;
        HttpServletRequest requestCacheWrapperObject = null;
        try {
            requestCacheWrapperObject = new ContentCachingRequestWrapper(request);
            requestCacheWrapperObject.getParameterMap();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            postData = RequestLoggingUtil.readPayload(requestCacheWrapperObject);
            LOGGER.info("REQUEST DATA: " + postData);
        }
        return true;
    }

    /**
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex) throws Exception {
        LOGGER.info("RESPONSE: " + response.getStatus());
    }
}
