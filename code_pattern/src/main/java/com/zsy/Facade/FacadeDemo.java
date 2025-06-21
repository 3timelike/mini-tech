package com.zsy.Facade;

public class FacadeDemo {
    private static final Logger logger = LoggerFactory.getLogger();
    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade();
        computer.start();
        System.out.println("==========");
        computer.shutdown();
        // 使用门面接口记录日志，不关心具体实现
        logger.info("应用启动");
        logger.debug("调试信息");
        logger.error("发生错误");

        // 切换实现只需要修改系统属性，不修改客户端代码
        System.setProperty("logger.impl", "log4j");
        Logger logger2 = LoggerFactory.getLogger();
        logger2.info("使用Log4j实现记录日志");
    }
}
