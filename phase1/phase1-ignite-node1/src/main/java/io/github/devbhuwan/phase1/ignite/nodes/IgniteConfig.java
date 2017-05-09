package io.github.devbhuwan.phase1.ignite.nodes;

import org.apache.ignite.Ignition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Bhuwan Upadhyay
 * @date 2017/05/10
 */
@Component
public class IgniteConfig {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        if (jdbcTemplate != null)
            Ignition.start("ignite-node-config.xml");
    }

}
