package com.hm.activity;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {
        // 1. 数据库连接（与 application.properties 一致）
        String url = "jdbc:mysql://localhost:3306/hm_campus_activity?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8";
        String username = "root";
        String password = "admin";

        FastAutoGenerator.create(url, username, password)
                // 全局配置
                .globalConfig(builder -> builder
                        .author("肖羽欣") // 作者
                        .outputDir(System.getProperty("user.dir") + "/src/main/java") // 输出路径
                        .enableSwagger() // 启用 Swagger 注解
                        .disableOpenDir()
                )
                // 包配置
                .packageConfig(builder -> builder
                        .parent("com.hm.activity") // 父包名
                        .entity("entity")
                        .mapper("mapper")
                        .service("service")
                        .serviceImpl("service.impl")
                        .controller("controller")
                        // XML 文件输出路径
                        .pathInfo(Collections.singletonMap(OutputFile.xml,
                                System.getProperty("user.dir") + "/src/main/resources/mybatis-plus"))
                )
                // 策略配置：生成所有 11 张表
                .strategyConfig(builder -> builder
                        .addInclude(
                                "sys_user", "activity", "activity_apply", "activity_schedule",
                                "activity_staff", "activity_material", "activity_feedback",
                                "user_tag", "user_behavior_log", "ai_chat_history", "notification"
                        )
                        .entityBuilder()
                        .enableLombok() // 必须开启，简化代码
                        .enableTableFieldAnnotation()
                        .controllerBuilder()
                        .enableRestStyle() // 生成 @RestController
                )
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}