package io.github.devbhuwan.phase1.app2.controller;

import io.github.devbhuwan.phase1.app.domain.Payment;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import static io.github.devbhuwan.phase1.app.domain.ignite.PaymentStore.PAYMENT_CACHE;


/**
 * @author Bhuwan Upadhyay
 * @date 2017/05/07
 */
@RestController
public class PaymentController {

    @Autowired
    private Ignite ignite;
    private AtomicLong atomicLong = new AtomicLong();

    @PostMapping("/payment/add")
    @ResponseBody
    public String add() {
        IgniteCache<Long, Payment> paymentIgniteCache = ignite.getOrCreateCache(PAYMENT_CACHE);
        long key = atomicLong.incrementAndGet();
        paymentIgniteCache.put(key,
                Payment.builder().id(key).purpose("Transfer-" + key)
                        .amount(BigDecimal.valueOf(key))
                        .creationDate(new Date())
                        .build());
        return "Created";
    }

    @GetMapping("/payment/{id}")
    @ResponseBody
    public Payment find(@PathVariable("id") Long id) {
        IgniteCache<Long, Payment> paymentIgniteCache = ignite.getOrCreateCache(PAYMENT_CACHE);
        return paymentIgniteCache.get(id);
    }

    @DeleteMapping("/payment/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") Long id) {
        IgniteCache<Long, Payment> paymentIgniteCache = ignite.getOrCreateCache(PAYMENT_CACHE);
        paymentIgniteCache.remove(id);
        return "Deleted";
    }

}
