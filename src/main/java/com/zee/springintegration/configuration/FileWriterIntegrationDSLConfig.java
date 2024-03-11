package com.zee.springintegration.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.support.FileExistsMode;


import java.io.File;


/**
 * @author : Ezekiel Eromosei
 * @code @created : 11 Mar, 2024
 */
// DSL CONFIG
@Configuration
public class FileWriterIntegrationDSLConfig {


    @Bean
    public IntegrationFlow fileWriterFlow(){
        return IntegrationFlow
                .from(MessageChannels.direct("textInChannel"))  // created automatically
                .<String, String>transform(text -> text.toUpperCase())
                .channel(MessageChannels.direct("fileWriterChannel")) // OPTIONAL
                .handle(
                        Files
                                .outboundAdapter(new File("C:/Users/zikoz/Desktop/JAVA/MAVEN/2024_PROJECTS/march/spring-integration"))
                                .fileExistsMode(FileExistsMode.APPEND)
                                .appendNewLine(true))
                .get();
    }
}
