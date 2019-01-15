package com.van.Interceptor;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;


import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Component
public class MyMvcConfig implements WebMvcConfigurer{


/*

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/*")
                .excludePathPatterns("/static/**")
                .excludePathPatterns("/regs")
                .excludePathPatterns("/login")
                .excludePathPatterns("/shou")
                .excludePathPatterns("/index2")
                .excludePathPatterns("/gotoProduct")
                .excludePathPatterns("/fenlei")
                .excludePathPatterns("/users")
                .excludePathPatterns("/zt")
                .excludePathPatterns("/gx")
                .excludePathPatterns("/productClass")
                .excludePathPatterns("/order")
                .excludePathPatterns("/addProudctClass")
                .excludePathPatterns("/updateProduct")
                .excludePathPatterns("/delProductClass")
                .excludePathPatterns("/updateOrderStatus")
                .excludePathPatterns("/updateStatus")
                .excludePathPatterns("/updateOrder")
                .excludePathPatterns("/modify")
                .excludePathPatterns("/product");

    }
*/



}
