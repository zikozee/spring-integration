package com.zee.springintegration.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;

import java.io.File;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 11 Mar, 2024
 */
// JAVA CONFIG
@Configuration
public class FileWriterIntegrationJavaConfig {


    @Bean
    @Transformer(inputChannel = "textInChannel", outputChannel = "fileWriterChannel")   // if not stated they are created automatically
    public GenericTransformer<String, String> upperCaseTransformer(){
        return text -> text.toUpperCase();
    }
    
    @Bean
    @ServiceActivator(inputChannel = "fileWriterChannel")
    public FileWritingMessageHandler fileWriter(){
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("C:/Users/zikoz/Desktop/JAVA/MAVEN/2024_PROJECTS/march/spring-integration"));
        handler.setExpectReply(false); // service activator shouldn't expect a reply. default true  (if you don't set this you will see error that no reply was configured)
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setAppendNewLine(true);
        return handler;
    }


    // create channels
    @Bean
    public MessageChannel textInChannel(){
        return new DirectChannel();
    }

    @Bean
    public MessageChannel fileWriterChannel(){
        return new DirectChannel();
    }
}
