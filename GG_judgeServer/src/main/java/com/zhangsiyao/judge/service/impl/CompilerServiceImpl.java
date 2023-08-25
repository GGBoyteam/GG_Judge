package com.zhangsiyao.judge.service.impl;

import com.zhangsiyao.common.constant.Language;
import com.zhangsiyao.common.entity.judge.dto.CodeCompileAndRunResultDto;
import com.zhangsiyao.common.entity.judge.dto.CompilerDto;
import com.zhangsiyao.common.entity.judge.vo.CodeCompileRunVo;
import com.zhangsiyao.judge.compiler.CppCompiler;
import com.zhangsiyao.judge.compiler.ICompiler;
import com.zhangsiyao.judge.compiler.JudgeResult;
import com.zhangsiyao.judge.compiler.annotation.Compiler;
import com.zhangsiyao.judge.service.ICompilerService;
import lombok.SneakyThrows;
import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class CompilerServiceImpl implements ICompilerService {


    public ICompiler compiler(Language language){
        if(language==Language.CPP){
            return new CppCompiler();
        }else {
            return null;
        }
    }

    @SneakyThrows
    @Override
    public CodeCompileAndRunResultDto compileAndRun(CodeCompileRunVo codeCompileRunVo) {
        JudgeResult result=compileAndRun(codeCompileRunVo.getLanguage(),codeCompileRunVo.getVersion(),codeCompileRunVo.getCode(),codeCompileRunVo.getInput());
        CodeCompileAndRunResultDto codeResult=new CodeCompileAndRunResultDto();
        if(result.getStatus()== JudgeResult.Status.Accepted&&result.getFiles().get("stderr").length()!=0){
            codeResult.setStatus(JudgeResult.Status.NonzeroExitStatus.getName());
        }else {
            codeResult.setStatus(result.getStatus().getName());
        }
        codeResult.setCode(codeResult.getCode());
        codeResult.setOutput(result.getOutput());
        codeResult.setLanguage(codeCompileRunVo.getLanguage());
        codeResult.setVersion(codeCompileRunVo.getVersion());
        codeResult.setTime(result.getRunTime());
        codeResult.setMemory(result.getMemory());
        return codeResult;
    }

    @SneakyThrows
    @Override
    public JudgeResult compileAndRun(Language language, String version, String code, String input) {
        ICompiler compiler=compiler(language);
        compiler.compile(code, version);
        JudgeResult result = compiler.run(input, 10000000L, 268435456L);
        compiler.removeFiles();
        return result;
    }

    @SneakyThrows
    @Override
    public JudgeResult.Status compile(String code,Language language,String version) {
        ICompiler compiler=compiler(language);
        JudgeResult.Status status = compiler.compile(code, version);
        compiler.removeFiles();
        return status;
    }




    @Override
    @Cacheable(value = "compiler",key = "#root.methodName")
    public List<CompilerDto> compilerList() {
        Reflections reflections=new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage("com.zhangsiyao.judge"))
                .setScanners(new TypeAnnotationsScanner()));
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Compiler.class);
        List<CompilerDto> list=new ArrayList<>();
        for(Class c:classes){
            Compiler annotation = (Compiler) c.getAnnotation(Compiler.class);
            CompilerDto compilerDto=new CompilerDto();
            compilerDto.setVersion(Arrays.asList(annotation.version()));
            compilerDto.setLanguage(annotation.language());
            list.add(compilerDto);
        }
        return list;
    }
}
