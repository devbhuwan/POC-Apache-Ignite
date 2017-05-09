package io.github.devbhuwan.poc.apache.ignite.mongodb.pesistence.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.ignite.cache.query.annotations.QuerySqlField;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author Bhuwan Upadhyay
 * @date 2017/05/06
 */
@AllArgsConstructor
@Getter
@Setter
public class Payment implements Serializable {

    @QuerySqlField(index = true)
    private Long batchId;

}
