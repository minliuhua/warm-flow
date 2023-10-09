package com.warm.flow.core.service;

import com.warm.flow.core.domain.entity.FlowSkip;
import com.warm.mybatis.core.service.IWarmService;

import java.util.List;

/**
 * 节点跳转关联Service接口
 *
 * @author warm
 * @date 2023-03-29
 */
public interface SkipService extends IWarmService<FlowSkip> {

    /**
     * 根据nodeId删除
     *
     * @param nodeId 需要删除的nodeId
     * @return 结果
     */
    boolean deleteByNodeId(Long nodeId);

    /**
     * 根据nodeIds删除
     *
     * @param nodeIds 需要删除的nodeIds
     * @return 结果
     */
    boolean deleteByNodeIds(List<Long> nodeIds);

    /**
     * 获取当前节点跳转
     *
     * @param definitionId
     * @param nowNodeCode
     * @return
     */
    List<FlowSkip> queryByDefAndCode(Long definitionId, String nowNodeCode);
}
