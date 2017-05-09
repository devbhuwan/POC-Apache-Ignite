package io.github.devbhuwan.phase1.ignite.nodes;

import io.github.devbhuwan.phase1.app.domain.Payment;
import org.apache.ignite.Ignition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Bhuwan Upadhyay
 * @date 2017/05/07
 */
@SpringBootApplication
public class IgniteNode1 {

    public static void main(String[] args) {
        SpringApplication.run(IgniteNode1.class, args);
        igniteStart();
    }

    private static void igniteStart() {
        Ignition.start("ignite-node-config.xml");
    }

}
