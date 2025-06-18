package com.zsy;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Compile {

    public static void compile(File javaFile) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        // 获取文件管理器
        try (StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null)) {

            // 获取要编译的文件对象
            Iterable<? extends JavaFileObject> compilationUnits =
                    fileManager.getJavaFileObjectsFromFiles(List.of(javaFile));

            // 设置编译选项（可选，例如指定输出目录）
            List<String> options = Arrays.asList("-d", "./target/classes");

            // 创建编译任务
            JavaCompiler.CompilationTask task = compiler.getTask(
                    null,
                    fileManager,
                    null,
                    options,
                    null,
                    compilationUnits);

            // 执行编译
            boolean success = task.call();

            if (success) {
                System.out.println("编译成功！");
            } else {
                System.out.println("编译失败！");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
