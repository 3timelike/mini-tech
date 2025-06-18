package com.zsy;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.util.concurrent.atomic.AtomicInteger;

public class MyInterfaceFactory {

    private static final AtomicInteger count = new AtomicInteger();

    private static File createJavaFile(String className, MyHandler handler) throws IOException {
        String func1Body = handler.functionBody("func1");
        String func2Body = handler.functionBody("func2");
        String func3Body = handler.functionBody("func3");
        String context = "package com.zsy;\n" +
                "\n" +
                "public class " + className + " implements MyInterface {\n" +
                " MyInterface myInterface;\n" +
                "    @Override\n" +
                "    public void func1() {\n" +
                "       " + func1Body + "\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void func2() {\n" +
                "       " + func2Body + "\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void func3() {\n" +
                "       " + func3Body + "\n" +
                "    }\n" +
                "}\n";
        File outputDir = new File("C:\\Users\\24244\\Desktop\\mini-tech\\proxy\\src\\main\\java\\com\\zsy");
        if (!outputDir.exists()) {
            outputDir.mkdirs(); // 创建目录（包括不存在的父目录）
        }

        File javaFile = new File(outputDir, className + ".java");
        Files.writeString(javaFile.toPath(), context);
        return javaFile;
    }

    private static String getClassName() {
        return "MyInterface$proxy" + count.incrementAndGet();
    }
//    private static MyInterface newInstance(String className, MyHandler handler) throws Exception {
//        Class<?> aClass = MyInterfaceFactory.class.getClassLoader().loadClass(className);
//        Constructor<?> constructor = aClass.getConstructor();
//        MyInterface proxy = (MyInterface) constructor.newInstance();
//        handler.setProxy(proxy);
//        return proxy;
//    }

    private static MyInterface newInstance(String className, MyHandler handler) throws Exception {
        // 1. 创建新的 ClassLoader，指向 outputDir 的父目录（target/classes）
        File classesDir = new File("C:\\Users\\24244\\Desktop\\mini-tech\\proxy\\target\\classes\\com\\zsy");
        URLClassLoader classLoader = new URLClassLoader(
                new URL[]{classesDir.toURI().toURL()},
                MyInterface.class.getClassLoader()  // 父加载器
        );

        // 2. 用新的 ClassLoader 加载代理类
        Class<?> proxyClass = classLoader.loadClass(className);
        Constructor<?> constructor = proxyClass.getConstructor(); // 假设代理类构造方法接收 MyHandler
        MyInterface proxy = (MyInterface) constructor.newInstance();
        handler.setProxy(proxy);
        return proxy;
    }

    public static MyInterface createProxyObject(MyHandler myHandler) throws Exception {
        String className = getClassName();
        File javaFile = createJavaFile(className, myHandler);
        File srcFile = new File(javaFile.getPath());
        File outputDir = new File("C:\\Users\\24244\\Desktop\\mini-tech\\proxy\\target\\classes\\com\\zsy").getAbsoluteFile();
//        System.out.println(srcFile.getPath());
//        System.out.println(outputDir.getPath());
        ProcessBuilder pb = new ProcessBuilder(
                "javac",
                "-classpath", "C:\\Users\\24244\\Desktop\\mini-tech\\proxy\\target\\classes",
                "-d", outputDir.getPath(),
                srcFile.getAbsolutePath()
        );
        Process process = pb.start();
        int exitCode = process.waitFor();  // 确保等待编译完成

        if (exitCode != 0) {
            // 读取错误流获取编译错误信息
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.err.println(line);
                }
            }
            throw new RuntimeException("编译失败，退出码: " + exitCode);
        }
        return newInstance("com.zsy." + className, myHandler);
    }
}
