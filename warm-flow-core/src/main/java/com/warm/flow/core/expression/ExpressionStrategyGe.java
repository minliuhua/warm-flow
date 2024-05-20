package com.warm.flow.core.expression;

import com.warm.flow.core.constant.FlowCons;
import com.warm.flow.core.utils.MathUtil;

/**
 * 条件表达式大于等于
 *
 * @author warm
 */
public class ExpressionStrategyGe extends ExpressionStrategyAbstract {

    public String getType() {
        return FlowCons.splitAt + "ge" + FlowCons.splitAt;
    }

    @Override
    public boolean afterEval(String[] split, String value) {
        if (MathUtil.isNumeric(split[2].trim())) {
            return MathUtil.determineSize(value, split[2].trim()) > 0 || MathUtil.determineSize(value, split[2].trim()) == 0;
        } else {
            return false;
        }
    }

}
