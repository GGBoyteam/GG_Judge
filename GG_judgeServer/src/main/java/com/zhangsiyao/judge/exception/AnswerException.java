package com.zhangsiyao.judge.exception;

import com.zhangsiyao.common.constant.Language;
import lombok.Data;

import java.io.Serializable;

public class AnswerException extends Exception{
    @Data
    public static class AnswerExceptionDto implements Serializable{

        Language language;

        String version;

        String code;

        String input;

        String output;

        String trueOutput;
    }

    private final AnswerExceptionDto answerExceptionDto;

    public AnswerException(Language language, String version, String code, String input,String output,String trueOutput) {
        this.answerExceptionDto = new AnswerExceptionDto();
        this.answerExceptionDto.setLanguage(language);
        this.answerExceptionDto.setVersion(version);
        this.answerExceptionDto.setCode(code);
        this.answerExceptionDto.setInput(input);
        this.answerExceptionDto.setOutput(output);
        this.answerExceptionDto.setTrueOutput(trueOutput);
    }

    public AnswerExceptionDto getAnswerExceptionDto() {
        return answerExceptionDto;
    }
}
