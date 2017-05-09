package io.github.devbhuwan.phase1.app2.controller;

import io.github.devbhuwan.phase1.app.domain.Payment;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

import static io.github.devbhuwan.phase1.app2.ignite.IgniteCfg.PAYMENT_CACHE;

/**
 * @author Bhuwan Upadhyay
 * @date 2017/05/07
 */
@RestController
public class PaymentController {

    @Autowired
    private Ignite ignite;

    @GetMapping("payment/add")
    public String add() {
        IgniteCache<Long, Payment> paymentIgniteCache = ignite.getOrCreateCache(PAYMENT_CACHE);
        long key = new Random().nextLong();
        paymentIgniteCache.put(key,
                Payment.builder().id(key).purpose("Transfer-" + key)
                        .amount(BigDecimal.valueOf(key))
                        .creationDate(LocalDate.now())
                        .build());
        return "Ok";
    }

}
