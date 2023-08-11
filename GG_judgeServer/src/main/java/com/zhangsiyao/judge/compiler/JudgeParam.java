package com.zhangsiyao.judge.compiler;

import lombok.*;

import java.io.Serializable;
import java.util.*;

@Data
@SuppressWarnings("all")
public class JudgeParam implements Serializable {

    List<Cmd> cmd=new ArrayList<Cmd>();

    @Data
    public static class Cmd implements Serializable{
        private List<String> args=new ArrayList<String>(); // 程序命令行参数
        private List<String> env= Arrays.asList("PATH=/usr/bin:/bin"); // 程序环境变量

        @Getter
        @Setter(AccessLevel.NONE)
        // 指定标准输入、标准输出和标准错误的文件
        private List<Object> files=new ArrayList<Object>(Arrays.<Object>asList(
                new JudgeParam.Collector("stdout",10240,false),
                new JudgeParam.Collector("stderr",10240,false)
        )); // LocalFile | MemoryFile | PreparedFile | Collector
        private boolean tty; // 开启 TTY (需要保证标准输出和标准错误为同一文件)同时需要指定 TERM 环境变量 (例如 TERM=xterm)

        // 资源限制
        private long cpuLimit; // CPU时间限制，单位纳秒
        private long clockLimit; // 等待时间限制，单位纳秒 (通常为 cpuLimit 两倍)
        private long memoryLimit; // 内存限制，单位 byte
        private long stackLimit; // 栈内存限制，单位 byte
        private int procLimit; // 线程数量限制
        private int cpuRateLimit; // 仅 Linux,CPU 使用率限制，1000 等于单核 100%
        private String cpuSetLimit; // 仅 Linux,限制 CPU 使用，使用方式和 cpuset cgroup 相同 (例如，`0` 表示限制仅使用第一个核)
        private boolean strictMemoryLimit; // 开启严格内存限制 (仅 Linux,设置 rlimit 内存限制)

        // 在执行程序之前复制进容器的文件列表
        private Map<String, Object> copyIn=new HashMap<String, Object>();

        // 在执行程序后从容器文件系统中复制出来的文件列表
        // 在文件名之后加入 '?' 来使文件变为可选，可选文件不存在的情况不会触发 FileError
        private List<String> copyOut=new ArrayList<String>();

        // 在文件名之后加入 '?' 可以使文件变为可选，可选文件不存在的情况不会触发 FileError。与 copyOut类似，但返回的是文件 ID 而不是内容。可以通过 "/file/:fileId" API下载内容。
        private List<String> copyOutCached=new ArrayList<String>();

        /**
         * copyOut的复制文件大小限制，单位byte.
         */
        private long copyOutMax;
    }

    @Data
    @AllArgsConstructor
    public static class LocalFile implements Serializable{
        private String src; // 文件绝对路径
    }

    @Data
    @AllArgsConstructor
    public static class MemoryFile implements Serializable{
        private String content; // 文件内容

    }

    @Data
    @AllArgsConstructor
    public static class PreparedFile implements Serializable{
        private String fileId; // 文件 id
    }

    @Data
    @AllArgsConstructor
    public static class Collector implements Serializable{
        private String name; // copyOut 文件名
        private int max; // 最大大小限制
        private boolean pipe; // 通过管道收集(默认值为false文件收集)
    }




}
