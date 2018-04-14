package rangers.dubbo.zk.api;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.config.annotation.Service;

import rangers.dubbo.zk.service.SendService;

//@Service(interfaceClass = IHelloService.class)
@Service(version = "1.0.0", application = "${dubbo.application.id}", protocol = "${dubbo.protocol.id}", registry = "${dubbo.registry.id}")
@Component
public class SendServiceV1Impl implements SendService {

	private static final String WELCOME = "I am the implements of sendService and version(1.0.0) welcome : ";
	private static final String NOTHING = "空空如也~~~";

	@Override
	public String saySomething(String something) {
		return StringUtils.isEmpty(something) ? NOTHING : WELCOME + something;
	}
}