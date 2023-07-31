package com.zhangsiyao.gateway.filter;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zhangsiyao.common.entity.common.dto.R;
import com.zhangsiyao.gateway.service.ValidateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 验证码过滤器
 */
@Component
@Order(2)
public class ValidateCodeFilter extends AbstractGatewayFilterFactory<Object> {
    private final static String[] VALIDATE_URL = new String[]{"/login", "/register"};

    @Autowired
    private ValidateCodeService validateCodeService;

    private static final String CODE = "code";

    private static final String UUID = "uuid";

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            // 非登录/注册请求或验证码关闭，不处理
            if (!isOnStrings(request.getURI().getPath(), VALIDATE_URL)) {
                return chain.filter(exchange);
            }
            try {
                String rspStr = resolveBodyFromRequest(request);
                JSONObject obj = JSONUtil.parseObj(rspStr);
                validateCodeService.checkCaptcha(obj.get(CODE).toString(), obj.get(UUID).toString());
            } catch (Exception e) {
                return out(exchange.getResponse(), e.getMessage());
            }
            return chain.filter(exchange);
        };
    }

    private String resolveBodyFromRequest(ServerHttpRequest serverHttpRequest) {
        // 获取请求体
        Flux<DataBuffer> body = serverHttpRequest.getBody();
        AtomicReference<String> bodyRef = new AtomicReference<>();
        body.subscribe(buffer -> {
            CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
            DataBufferUtils.release(buffer);
            bodyRef.set(charBuffer.toString());
        });
        return bodyRef.get();
    }

    private boolean isOnStrings(String target, String[] strings){
        for (String string: strings) {
            if(target.equals(string)){
                return true;
            }
        }
        return false;
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