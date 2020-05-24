package me.steell.miniproject.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
@EnableWebMvc
public class WebConfig {
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();


        ApplicationContext ctx = new GenericApplicationContext();
        Environment env = ctx.getEnvironment();
        String dLang = env.getProperty("-Dlang");
        if("kr".equals( dLang)) {
            sessionLocaleResolver.setDefaultLocale(Locale.KOREA);
        }else if("en".equals( dLang)) {
            sessionLocaleResolver.setDefaultLocale(Locale.US);
        }else{
            sessionLocaleResolver.setDefaultLocale(Locale.US);
        }

        return sessionLocaleResolver;
    }

    @Bean public ReloadableResourceBundleMessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/templates/message/message");
        messageSource.addBasenames("classpath:/templates/message/label");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(180); // 제공하지 않는 언어로 요청이 들어왔을 때 MessageSource에서 사용할 기본 언어정보.
        Locale.setDefault(Locale.US);
        return messageSource;
    }
}
