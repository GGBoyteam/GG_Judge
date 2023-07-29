package com.zhangsiyao.gateway.filter;

import cn.hutool.json.JSONUtil;
import com.zhangsiyao.gateway.entity.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@Order(-1)
public class AuthorizeFilter implements GlobalFilter{

    @Autowired
    StringRedisTemplate redisTemplate;

    private final String USER_TOKEN = "Authorization";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        List<String> token = request.getHeaders().get(USER_TOKEN);
        String path=request.getURI().getPath();
        System.out.println(path.matches("/auth/.*"));
        if(path.matches("/auth/.*")){
            if(token!=null&&token.size()>0){
                String t=token.get(0);
                String key=redisTemplate.opsForValue().get(t);
                if (key!=null){
                    return out(response,"你已经登陆过了");
                }
            }
            return chain.filter(exchange);
        }
        if(token==null||token.size()==0){
            return out(response,"验证失败");
        }
        String t=token.get(0);
        String key=redisTemplate.opsForValue().get(t);
        if (key==null){
            return out(response,"验证失败");
        }
        return chain.filter(exchange);
    }

    public Mono<Void> out(ServerHttpResponse response, String msg) {
        R<String> error = R.error(HttpStatus.UNAUTHORIZED.value(), msg);
        String resMessage = JSONUtil.toJsonStr(error);
        byte[] bytes = resMessage.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        //指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        //输出http响应
        return response.writeWith(Mono.just(buffer));
    }

}
