package rangers.dubbo.zk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;



@SpringBootApplication
@EnableDubbo
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}
}
