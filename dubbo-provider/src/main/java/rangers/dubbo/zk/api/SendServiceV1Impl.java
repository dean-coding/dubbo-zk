package rangers.dubbo.zk.api;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.config.annotation.Service;

import rangers.dubbo.zk.service.SendService;

/**
 * <p>
 * 集群容错
 * </p>
 * <li>cluster="failover" 失败自动切换，当出现失败，重试其它服务器。通常用于读操作，但重试会带来更长延迟</li>
 * <li>cluster="failfast" 通常用于非幂等性的写操作，比如新增记录,快速失败</li>
 * <li>cluster="failsafe" 出现异常时，直接忽略,通常用于写入审计日志等操作</li>
 * <li>cluster="failback" 失败自动恢复，后台记录失败请求，定时重发, 通常用于消息通知操作</li>
 * <li>cluster="forking" forks="2"
 * 并行调用多个服务器，只要一个成功即返回,通常用于实时性要求较高的读操作，但需要浪费更多服务资源</li>
 *
 * <p>
 * 服务均衡
 * </p>
 * <li>loadbalance="random" 按权重设置随机概率</li>
 * <li>loadbalance="roundrobin" 按公约后的权重设置轮循比率</li>
 * <li>loadbalance="leastactive" 最少活跃调用数，相同活跃数的随机，活跃数指调用前后计数差</li>
 * <li>loadbalance="consistentHash" 一致性Hash，相同参数的请求总是发到同一提供者</li>
 * 
 * @author fuhw/vencano
 * @date 2018-04-15
 */
@Service(loadbalance = "roundrobin", retries = 2, cluster = "failover", version = "1.0.0", application = "${dubbo.application.id}", protocol = "${dubbo.protocol.id}", registry = "${dubbo.registry.id}")
@Component
public class SendServiceV1Impl implements SendService {

	private static AtomicInteger callNo = new AtomicInteger(0);// 调用次数

	private static final String WELCOME = "sendService with version(1.0.0),\n dubbo port=";
	private static final String NOTHING = "空空如也~~~\n";

	@Value("${dubbo.protocol.port}")
	private String dubboProtocolPort;

	@Override
	public String saySomething(String something) {
		String remarkInfo = WELCOME + dubboProtocolPort + ",\n callNo=" + callNo.incrementAndGet() + ", \n say:";
		return StringUtils.isEmpty(something) ? remarkInfo + NOTHING : remarkInfo + something + "\n";
	}
}