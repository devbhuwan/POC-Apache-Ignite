package io.github.devbhuwan.phase1.app1.controller;

import io.github.devbhuwan.phase1.app.domain.Payment;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static io.github.devbhuwan.phase1.app.domain.ignite.PaymentStore.PAYMENT_CACHE;


/**
 * @author Bhuwan Upadhyay
 * @date 2017/05/07
 */
@RestController
public class BankController {

    @Autowired
    private Ignite ignite;

    @PostMapping("/payment/approve/{id}")
    @ResponseBody
    public String add(@PathVariable("id") Long id) {
        IgniteCache<Long, Payment> paymentCache = ignite.getOrCreateCache(PAYMENT_CACHE);
        paymentCache.put(id, cloneAndUpdateStatus(paymentCache.get(id), "APPROVED"));
        return "Approved";
    }

    @PostMapping("/payment/reject/{id}")
    @ResponseBody
    public String reject(@PathVariable("id") Long id) {
        IgniteCache<Long, Payment> paymentCache = ignite.getOrCreateCache(PAYMENT_CACHE);
        paymentCache.put(id, cloneAndUpdateStatus(paymentCache.get(id), "REJECTED"));
        return "Rejected";
    }

    private Payment cloneAndUpdateStatus(Payment payment, String status) {
        return Payment.builder().id(payment.getId()).purpose(payment.getPurpose())
                .amount(payment.getAmount())
                .creationDate(payment.getCreationDate())
                .status(status)
                .build();
    }
}
