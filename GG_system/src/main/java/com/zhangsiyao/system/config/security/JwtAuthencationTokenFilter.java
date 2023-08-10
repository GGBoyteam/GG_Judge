package com.zhangsiyao.system.config.security;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangsiyao.common.entity.auth.dao.UserLogin;
import com.zhangsiyao.common.entity.system.dao.UserInfo;
import com.zhangsiyao.system.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtAuthencationTokenFilter extends OncePerRequestFilter {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    IUserInfoService userInfoService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader("Authorization");
        if(StringUtils.isEmpty(token)){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        String userinfo = redisTemplate.opsForValue().get(token);
        if(StringUtils.isEmpty(userinfo)){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        ObjectMapper objectMapper=new ObjectMapper();
        UserLogin user=objectMapper.readValue(userinfo, UserLogin.class);
        LambdaQueryWrapper<UserInfo> queryWrapper=new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getUsername,user.getUsername());
        UserInfo userInfo = userInfoService.getBaseMapper().selectOne(queryWrapper);
        if(userInfo==null){
            userInfo=new UserInfo();
            userInfo.setUsername(user.getUsername());
            this.userInfoService.save(userInfo);
            userInfo = userInfoService.getBaseMapper().selectOne(queryWrapper);
        }
        List<GrantedAuthority> permissions=new ArrayList<>();
        permissions.add(new SimpleGrantedAuthority("dawd"));
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userinfo,null,permissions);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
