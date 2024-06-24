package com.warm.flow.orm.utils;

/**
 * @author vanlin
 * @className OuterPredictesFunction
 * @description
 * @since 2024/5/12 0:42
 */
@FunctionalInterface
public interface JPAPredicateFunction<CB, ROOT, LIST> {
    void process(CB cb, ROOT root, LIST list);
}