package com.sky.interceptor;

import com.sky.properties.JwtProperties;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * jwt令牌校验的拦截器
 */
@Component
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    private Logger logger = LoggerFactory.getLogger(JwtTokenAdminInterceptor.class);

    /**
     * 校验jwt
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前拦截到的是Controller的方法还是其他资源，用于放行Swaager资源
        String urlString = request.getRequestURL().toString();
        logger.info("url:{}", urlString);
        //用于获取经过nginx反向代理后的客户端真实ip
        String clientAddress = "";
        String reqMethod = request.getMethod();//请求方式
        String reqUrI = request.getRequestURI();//资源
        String xRealIp = request.getHeader("X-Real-IP");//获取真实ip
        if (Objects.isNull(xRealIp))
            clientAddress = request.getRemoteAddr();
        else
            clientAddress = xRealIp;
        if (urlString.contains("/login") || urlString.contains("/regedit")) {
            logger.info("{} {} {} 通过成功", clientAddress, reqMethod, reqUrI);
            return true;
        }

        if (!(handler instanceof HandlerMethod)) {
            logger.info("{} {} {} 通过成功", clientAddress, reqMethod, reqUrI);
            //当前拦截到的不是动态方法，直接放行
            return true;
        }
        // 如果isHidden属性值true就屏蔽,直接返回true
		if(!jwtProperties.getIsHidden()) {
			//1、从请求头中获取令牌
			String token = request.getHeader(jwtProperties.getAdminTokenName());
			//2、校验令牌
			try {
//            log.info("jwt校验:{}", token);
				Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
				Long empId = Long.valueOf(claims.get(JwtClaimsConstant.EMP_ID).toString());
//            log.info("当前员工id：", empId);
				//3、通过，放行
				logger.info("{} {} {} 通过成功",clientAddress,reqMethod,reqUrI);
				return true;
			} catch (Exception ex) {
				//4、不通过，响应401状态码
				response.setStatus(401);
				logger.info("{} {} {} 通过失败",clientAddress,reqMethod,reqUrI);
				return false;
			}
		}
		return true;
	}
}
