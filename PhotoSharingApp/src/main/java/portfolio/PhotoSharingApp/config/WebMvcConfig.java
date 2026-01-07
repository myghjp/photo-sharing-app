package portfolio.PhotoSharingApp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	/*まとめる*/
		
	@Value("${app.media.directory}")
	private String mediaDirectory;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/media/**").addResourceLocations("file:" + mediaDirectory);
	}
	
	/*ーーーーーーーーーーーーーーーーーーーーーーーー*/
	/*/resources/配下はコンパイルエラー*/
	/*staticとpublicは自動で探してくれる*/
	/*ーーーーーーーーーーーーーーーーーーーーーーーー*/ 

}
