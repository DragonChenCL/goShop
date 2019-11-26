package com.dragon.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("spring-test-interface")
                //加载配置信息
                .apiInfo(apiInfo())
//                //设置全局参数
//                .globalOperationParameters(globalParamBuilder())
//                //设置全局响应参数
//                .globalResponseMessage(RequestMethod.GET, responseBuilder())
//                .globalResponseMessage(RequestMethod.POST, responseBuilder())
//                .globalResponseMessage(RequestMethod.PUT, responseBuilder())
//                .globalResponseMessage(RequestMethod.DELETE, responseBuilder())
                .select()
                //加载swagger 扫描包
                .apis(RequestHandlerSelectors.basePackage("com.dragon"))
                .paths(PathSelectors.any()).build();
        //设置安全认证
//                .securitySchemes(security());

    }

    /**
     * 获取swagger创建初始化信息
     *
     * @return
     */
    private ApiInfo apiInfo() {
        Contact contact = new Contact("dragonChen", "", "chenlong_qrr@163.com");
        return new ApiInfoBuilder().title("swagger 测试文档").description("dev by dragonChen").contact(contact)
                .version("1.0.0").build();
    }
}
