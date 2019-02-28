package ua.com.bpgdev.autosolver.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class RiaControllerInterceptorConfig implements WebMvcConfigurer {
    private SaveCarInterceptor saveCarInterceptor;

    @Autowired
    public RiaControllerInterceptorConfig(SaveCarInterceptor saveCarInterceptor) {
        this.saveCarInterceptor = saveCarInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(saveCarInterceptor);
    }
}
