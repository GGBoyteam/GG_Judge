package com.zhangsiyao.judge.compiler;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@ResponseBody
public class JudgeResult implements Serializable {
    private Status status;
    private String error; // 详细错误信息
    private int exitStatus; // 程序返回值
    private long time; // 程序运行 CPU 时间，单位纳秒
    private long memory; // 程序运行内存，单位 byte
    private long runTime; // 程序运行现实时间，单位纳秒

    private Map<String, String> files; // copyOut 和 pipeCollector 指定的文件内容
    private Map<String, String> fileIds; // copyFileCached 指定的文件 id
    private List<FileError> fileError; // 文件错误详细信息

    public String getOutput(){
        if(status!=Status.Accepted){
            return files.get("stderr");
        }else {
            return files.get("stdout");
        }
    }

    @Data
    public static class FileError implements Serializable{
        private String name; // 错误文件名称
        private FileErrorType type; // 错误代码
        private String message; // 错误信息

        // getters and setters
    }

    public enum FileErrorType{

        CopyInOpenFile("CopyInOpenFile"),
        CopyInCreateFile("CopyInCreateFile"),
        CopyInCopyContent("CopyInCopyContent"),
        CopyOutOpen("CopyOutOpen"),
        CopyOutNotRegularFile("CopyOutNotRegularFile"),
        CopyOutSizeExceeded("CopyOutSizeExceeded"),
        CopyOutCreateFile("CopyOutCreateFile"),
        CopyOutCopyContent("CopyOutCopyContent"),
        CollectSizeExceeded("CollectSizeExceeded");

        private String name;

        FileErrorType(String name) {
            this.name = name;
        }

        @JsonValue
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public enum Status{

        Accepted("Accepted"),
        MemoryLimitExceeded("Memory Limit Exceeded"),
        TimeLimitExceeded("Time Limit Exceeded"),
        OutputLimitExceeded("Output Limit Exceeded"),
        FileError("File Error"),
        NonzeroExitStatus("Nonzero Exit Status"),
        Signalled("Signalled"),
        InternalError("Internal Error");

        private String name;

        Status(String name) {
            this.name = name;
        }

        @JsonValue
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
