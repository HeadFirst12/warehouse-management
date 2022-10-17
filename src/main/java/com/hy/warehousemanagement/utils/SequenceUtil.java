package com.hy.warehousemanagement.utils;

import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: hy
 * @version: com.hy.warehousemanagement.utils.java, v1.0 2022年10月17日 17:34 hy Exp $
 **/
public class SequenceUtil {

    @Value("")
    private String length;

    private SequenceUtil() {

    }

    private static String createSequence(String prefix, String template, int length) {
        List<String> sequenceList = new ArrayList<>();
        return null;
    }
}
