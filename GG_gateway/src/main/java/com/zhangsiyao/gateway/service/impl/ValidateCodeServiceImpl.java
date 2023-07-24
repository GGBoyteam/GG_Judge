package com.zhangsiyao.gateway.service.impl;

import cn.hutool.core.codec.Base64;
import com.google.code.kaptcha.Producer;
import com.zhangsiyao.gateway.constant.Constants;
import com.zhangsiyao.gateway.entity.vo.R;
import com.zhangsiyao.gateway.service.ValidateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {
    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 生成验证码
     */
    @Override
    public R createCaptcha() throws IOException {
        R<HashMap<String, Object>> result = R.success(new HashMap<>());
        // 保存验证码信息
        String uuid = UUID.randomUUID().toString();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

        String capStr = null, code = null;
        BufferedImage image = null;

        capStr = code = captchaProducer.createText();
        image = captchaProducer.createImage(capStr);

        stringRedisTemplate.opsForValue().set(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", os);
        } catch (IOException e) {
            return R.error(e.getMessage());
        }
        result.getData().put("uuid", uuid);
        result.getData().put("img", Base64.encode(os.toByteArray()));
        return result;
    }

    /**
     * 校验验证码
     */
    @Override
    public void checkCaptcha(String code, String uuid) {
        if (!StringUtils.hasText(code)) {
            throw new RuntimeException("验证码不能为空");
        }
        if (!StringUtils.hasText(code)) {
            throw new RuntimeException("验证码已失效");
        }
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = stringRedisTemplate.opsForValue().get(verifyKey);
        stringRedisTemplate.delete(verifyKey);

        if (!code.equalsIgnoreCase(captcha)) {
            throw new RuntimeException("验证码错误");
        }
    }
}
