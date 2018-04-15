package rangers.dubbo.zk.ctrl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;

import rangers.dubbo.zk.service.SendService;

@RestController
public class HelloCtrl {

	/**
	 * <li>如果指定了url,消费者采用默认的dubbo直连,不会注册到注册中心去:url = "dubbo://localhost:20881"</li>
	 * <li>反之会根据RegistryConfig到配置,订阅注册中心到消息</li>
	 */
	@Reference(version = "1.0.0", application = "${dubbo.application.id}")
	private SendService helloService;

	@GetMapping("/welcome")
	public String welcome(@RequestParam(name = "something", required = false) String something) {
		return helloService.saySomething(something);
	}
}
