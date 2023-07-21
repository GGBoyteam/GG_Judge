package com.zhangsiyao.common.result;

import lombok.Data;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@ResponseBody
public class JudgeResult implements Serializable {
    private String status;
    private String error; // 详细错误信息
    private int exitStatus; // 程序返回值
    private long time; // 程序运行 CPU 时间，单位纳秒
    private long memory; // 程序运行内存，单位 byte
    private long runTime; // 程序运行现实时间，单位纳秒

    private Map<String, String> files; // copyOut 和 pipeCollector 指定的文件内容
    private Map<String, String> fileIds; // copyFileCached 指定的文件 id
    private List<FileError> fileErrors; // 文件错误详细信息


    @Data
    public static class FileError implements Serializable{
        private String name; // 错误文件名称
        private String type; // 错误代码
        private String message; // 错误信息

        // getters and setters
    }
}
