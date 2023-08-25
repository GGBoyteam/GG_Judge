package com.zhangsiyao.common.utils;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PermissionUtil {

    public static Set<String> getPermissions(){
        Reflections reflections=new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage("com.zhangsiyao"))
                .setScanners(new MethodAnnotationsScanner()));
        Set<Method> annotated = reflections.getMethodsAnnotatedWith(PreAuthorize.class);
        Set<String> permission=new HashSet<String>();
        String pattern1="(hasAnyAuthority\\()(\\D+)(\\))";
        String pattern2="(hasAuthority\\(')('\\D+')('\\))";
        Pattern r = Pattern.compile(pattern1);
        Pattern r2=Pattern.compile(pattern2);
        for(Method m:annotated){
            PreAuthorize authorize = m.getAnnotation(PreAuthorize.class);
            System.out.println(authorize.value());
            Matcher matcher=r.matcher(authorize.value());
            Matcher matcher2=r2.matcher(authorize.value());
            if(matcher.find()){
                String[] strs = matcher.group(2).split(",");
                for(String str:strs){
                    System.out.println(str);
                    permission.add(str.replaceAll("'","").trim());
                }
            }
            if(matcher2.find()){
                permission.add(matcher2.group(2));
            }
        }
        return permission;
    }
}
