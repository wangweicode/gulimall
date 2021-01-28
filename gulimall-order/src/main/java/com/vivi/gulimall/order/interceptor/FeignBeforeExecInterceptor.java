package com.vivi.gulimall.order.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangwei
 * 2021/1/18 20:23
 */
@Component
public class FeignBeforeExecInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            // 拿到原始请求头数据
            HttpServletRequest request = requestAttributes.getRequest();
            String cookie = request.getHeader("Cookie");
            if (!StringUtils.isEmpty(cookie)) {
                // 同步
                template.header("Cookie", cookie);
            }
        }
    }
}
