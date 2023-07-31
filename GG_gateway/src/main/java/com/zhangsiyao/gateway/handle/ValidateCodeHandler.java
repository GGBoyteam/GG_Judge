package com.zhangsiyao.gateway.handle;

import com.zhangsiyao.common.entity.common.dto.R;
import com.zhangsiyao.gateway.service.ValidateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * 验证码获取
 *
 */
@Component
public class ValidateCodeHandler implements HandlerFunction<ServerResponse> {
    @Autowired
    private ValidateCodeService validateCodeService;

    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest) {
        R result;
        try {
            result = validateCodeService.createCaptcha();
        } catch (Exception e) {
            return Mono.error(e);
        }

        return ServerResponse.status(HttpStatus.OK).body(BodyInserters.fromValue(result));
    }

}