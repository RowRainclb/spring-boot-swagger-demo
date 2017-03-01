package myproject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by clb on 17-3-1.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()  // 选择那些路径和api会生成document
				.apis(RequestHandlerSelectors.any()) // 对所有api进行监控
				.paths(PathSelectors.any()) // 对所有路径进行监控
				.build();
	}


	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Spring Boot中使用Swagger2构建RESTful APIs")
				.description("更多Spring Boot相关文章请关注：http://blog.didispace.com/")
				.termsOfServiceUrl("http://blog.didispace.com/")
				.contact("程序猿DD")
				.version("1.0")
				.build();
	}
}
