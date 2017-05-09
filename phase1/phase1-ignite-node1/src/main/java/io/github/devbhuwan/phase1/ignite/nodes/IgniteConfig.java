package io.github.devbhuwan.phase1.ignite.nodes;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.springframework.stereotype.Component;

/**
 * @author Bhuwan Upadhyay
 * @date 2017/05/10
 */
@Component
public class IgniteConfig {

    public IgniteConfig() {
        Ignite start = Ignition.start("ignite-node-config.xml");
    }

}
