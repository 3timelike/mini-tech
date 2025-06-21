package com.zsy.Facade;

class LoggerFactory {
    // 模拟根据配置返回不同的日志实现
    public static Logger getLogger() {
        // 实际中这里会读取配置决定使用哪个实现
        String loggerImpl = System.getProperty("logger.impl", "logback");

        if ("log4j".equalsIgnoreCase(loggerImpl)) {
            return new Log4jLogger();
        } else {
            return new LogbackLogger();
        }
    }
}