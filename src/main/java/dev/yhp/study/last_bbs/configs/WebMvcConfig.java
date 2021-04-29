package dev.yhp.study.last_bbs.configs;

import dev.yhp.study.last_bbs.interceptors.AutoSignInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final AutoSignInterceptor autoSignInterceptor;

    @Autowired
    public WebMvcConfig(AutoSignInterceptor autoSignInterceptor) {
        this.autoSignInterceptor = autoSignInterceptor;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/WEB-INF/statics/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(autoSignInterceptor).addPathPatterns("/**").excludePathPatterns("/**.js", "/**.css");
    }
}
