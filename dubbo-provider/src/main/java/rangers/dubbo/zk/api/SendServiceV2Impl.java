package rangers.dubbo.zk.api;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.config.annotation.Service;

import rangers.dubbo.zk.service.SendService;

@Service(loadbalance = "roundrobin", retries = 2, cluster = "failover", version = "2.0.0", application = "${dubbo.application.id}", protocol = "${dubbo.protocol.id}", registry = "${dubbo.registry.id}")
@Component
public class SendServiceV2Impl implements SendService {
	private static AtomicInteger callNo = new AtomicInteger(0);// 调用次数

	private static final String WELCOME = "sendService with version(2.0.0),\n dubbo port=";
	private static final String NOTHING = "空空如也~~~\n";

	@Value("${dubbo.protocol.port}")
	private String dubboProtocolPort;

	@Override
	public String saySomething(String something) {
		String remarkInfo = WELCOME + dubboProtocolPort + ",\n callNo=" + callNo.incrementAndGet() + ", \n say:";
		return StringUtils.isEmpty(something) ? remarkInfo + NOTHING : remarkInfo + something + "\n";
	}
}