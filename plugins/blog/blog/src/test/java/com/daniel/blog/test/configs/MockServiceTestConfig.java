package com.daniel.blog.test.configs;

import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.daniel.blog.services.PhotoBlogService;
 
@Configuration
public class MockServiceTestConfig {
 
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
 
        messageSource.setBasename("i18n/messages");
        messageSource.setUseCodeAsDefaultMessage(true);
 
        return messageSource;
    }
 
    @Bean
    public PhotoBlogService photoBlogService() {
        return Mockito.mock(PhotoBlogService.class);
    }
}