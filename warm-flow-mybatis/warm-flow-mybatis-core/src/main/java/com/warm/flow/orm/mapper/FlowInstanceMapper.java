package com.warm.flow.orm.mapper;

import com.warm.flow.core.entity.Instance;

import java.util.List;

/**
 * 流程实例Mapper接口
 *
 * @author warm
 * @date 2023-03-29
 */
public interface FlowInstanceMapper extends WarmMapper<Instance> {

    List<Instance> getByIdWithLock(List<Long> ids);

}
