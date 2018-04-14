package rangers.dubbo.zk.api;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.config.annotation.Service;

import rangers.dubbo.zk.service.ListenService;

//@Service(interfaceClass = IHelloService.class)
@Service(version = "1.0.0", application = "${dubbo.application.id}", protocol = "${dubbo.protocol.id}", registry = "${dubbo.registry.id}")
@Component
public class V1ListenServiceImpl implements ListenService {

	private static final String LISTEN = "I am the implements of ListenService and version(1.0.0) listen : ";
	private static final String NOTHING = "空空如也~~~";

	@Override
	public String doSomething(String something) {
		return StringUtils.isEmpty(something) ? NOTHING : LISTEN + something;
	}
}