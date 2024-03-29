package com.warm.flow.orm.mapper;

import com.warm.flow.core.entity.Node;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 流程节点Mapper接口
 *
 * @author warm
 * @date 2023-03-29
 */
public interface FlowNodeMapper extends WarmMapper<Node> {

    /**
     * 跟进流程编码获取流程节点集合
     *
     * @param flowCode
     * @return
     */
    List<Node> getByFlowCode(String flowCode);

    List<Node> getByNodeCodes(@Param("nodeCodes") List<String> nodeCodes, @Param("definitionId") Long definitionId);

}
