package io.github.devbhuwan.phase1.app2.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Bhuwan Upadhyay
 * @date 2017/05/07
 */
@Configuration
public class IgniteClientCfg {

    @Bean
    public Ignite ignite() {
        Ignition.setClientMode(true);
        return Ignition.start();
    }

}
