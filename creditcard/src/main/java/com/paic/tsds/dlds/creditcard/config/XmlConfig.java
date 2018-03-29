package com.paic.tsds.dlds.creditcard.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @description TODO
 * @log 日志
 *      <p>
 *      ====2018年3月29日----ZHANGQIKUN785====
 *      </p>
 *      <p>
 *      Note: TODO修改原因
 *      </p>
 */

@Configuration
@ImportResource(locations = { "classpath:creditcard-lifecycle.xml" })
public class XmlConfig {

}
