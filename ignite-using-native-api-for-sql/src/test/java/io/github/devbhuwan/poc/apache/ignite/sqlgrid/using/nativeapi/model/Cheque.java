package io.github.devbhuwan.poc.apache.ignite.sqlgrid.using.nativeapi.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Bhuwan Upadhyay
 * @date 2017/05/06
 */
@Getter
@Setter
public class Cheque implements Serializable {

    private int id;
    private String chequeType;

}
