package com.warm.flow.orm.dao;

import com.warm.flow.core.dao.FlowNodeDao;
import com.warm.flow.core.entity.Node;
import com.warm.flow.core.invoker.BeanInvoker;
import com.warm.flow.orm.mapper.FlowNodeMapper;

import java.util.List;


/**
 * 流程节点Mapper接口
 *
 * @author warm
 * @date 2023-03-29
 */
public class FlowNodeDaoImpl extends WarmDaoImpl<Node> implements FlowNodeDao {

    @Override
    public FlowNodeMapper getMapper() {
        return BeanInvoker.getBean(FlowNodeMapper.class);
    }

    /**
     * 跟进流程编码获取流程节点集合
     *
     * @param flowCode
     * @return
     */
    @Override
    public List<Node> getByFlowCode(String flowCode) {
        return getMapper().getByFlowCode(flowCode);
    }

    @Override
    public List<Node> getByNodeCodes(List<String> nodeCodes, Long definitionId) {
        return getMapper().getByNodeCodes(nodeCodes, definitionId);
    }

}
