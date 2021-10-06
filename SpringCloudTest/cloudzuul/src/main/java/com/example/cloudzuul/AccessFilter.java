package com.example.cloudzuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 会话实现访问权限控制，在分布式应用的问题
 * 1.如果没有配套的服务，传统的会话访问控制将会失效
 * 2.会话控制方式有安全和性能问题
 *          安全:会话方式 ==>Cookie ==> JSESSIONID(会话id  这个有可能被窃取
 *            )
 *           性能:服务存储会话对象，用户量大的情况下，会造成大量内存消耗
 *
 *  3.解决上面的方案: 使用令牌
 *           服务器每次给与响应的时候，根据浏览器的请求，动态的生成一个令牌(加密字符串)
 *           浏览器下一次发送请求时，必须携带令牌回服务器
 *
 *  当前的例子，没有生成令牌的业务逻辑，只是简单的验证令牌有没有
 * */

@Component
public class AccessFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre"; //前置过滤器
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true; //过滤器是否执行 true代表要过率
    }
    /**
     * ThreadLocal 本地线程变量
     * */

    @Override
    public Object run() throws ZuulException {
        //zuul会为每个请求创建一个请求上下文
        RequestContext ctx=RequestContext.getCurrentContext();
        //通过请求上下文获取servlet请求对象
        HttpServletRequest request=ctx.getRequest();
        //获取传来的参数accessToken
        Object accessToken=request.getParameter("accessToken");
        if(accessToken==null){
            System.out.println("access token is empty");
            //过滤该请求，不往下级服务转发请求，到处结束
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("{\"result\":\"accessToken is empty!\"}");
            return null;
        }
        //如果有token 则进行路由转发，这里return的值没有意义，zuul框架没有使用返回值
        return null;
    }
}
