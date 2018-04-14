package rangers.dubbo.zk.ctrl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;

import rangers.dubbo.zk.service.SendService;

@RestController
public class HelloCtrl {

	@Reference(version = "1.0.0", application = "${dubbo.application.id}", url = "dubbo://localhost:20881")
	private SendService helloService;

	@GetMapping("/welcome")
	public String welcome(@RequestParam(name = "something", required = false) String something) {
		return helloService.saySomething(something);
	}
}
