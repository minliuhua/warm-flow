package com.warm.flow.core.service.impl;

import com.warm.flow.core.FlowFactory;
import com.warm.flow.core.constant.ExceptionCons;
import com.warm.flow.core.dao.FlowUserDao;
import com.warm.flow.core.dto.FlowParams;
import com.warm.flow.core.entity.HisTask;
import com.warm.flow.core.entity.Node;
import com.warm.flow.core.entity.Task;
import com.warm.flow.core.entity.User;
import com.warm.flow.core.enums.UserType;
import com.warm.flow.core.orm.service.impl.WarmServiceImpl;
import com.warm.flow.core.service.UserService;
import com.warm.flow.core.utils.AssertUtil;
import com.warm.tools.utils.CollUtil;
import com.warm.tools.utils.StreamUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 流程用户Service业务层处理
 *
 * @author xiarg
 * @date 2024/5/10 13:57
 */
public class UserServiceImpl extends WarmServiceImpl<FlowUserDao<User>, User> implements UserService {

    @Override
    public UserService setDao(FlowUserDao<User> warmDao) {
        this.warmDao = warmDao;
        return this;
    }

    @Override
    public List<User> setUser(List<HisTask> hisTasks, List<Task> addTasks, FlowParams flowParams) {
        List<User> hisTaskUserList = null;
        if(CollUtil.isNotEmpty(hisTasks)){
            hisTaskUserList = StreamUtils.toList(hisTasks, hisTask -> hisTaskAddUser(hisTask, flowParams));
        }
        List<List<User>> taskUserList = null;
        if(CollUtil.isNotEmpty(addTasks)){
            taskUserList = StreamUtils.toList(addTasks, task -> taskAddUser(task, flowParams));
        }
        return CollUtil.listAddListsToNew(hisTaskUserList, taskUserList);
    }

    @Override
    public List<User> setSkipUser(List<HisTask> hisTasks, List<Task> addTasks, FlowParams flowParams) {
        // 删除已执行的代办任务的权限人
        delUser(CollUtil.toList(flowParams.getTaskId()));
        return setUser(hisTasks, addTasks, flowParams);
    }

    @Override
    public User hisTaskAddUser(HisTask hisTask, FlowParams flowParams) {
        User user = FlowFactory.newUser()
                        .setType(UserType.APPROVER.getKey())
                        .setProcessedBy(flowParams.getCreateBy())
                        .setAssociated(hisTask.getId());
        FlowFactory.dataFillHandler().idFill(user);
        return user;
    }

    @Override
    public List<User> taskAddUser(Task task, FlowParams flowParams) {
        List<User> userList = new ArrayList<>();
        // 获取审批人权限集合
        List<String> permissionList = task.getPermissionList();
        // 审批人权限不能为空
        AssertUtil.isTrue(CollUtil.isEmpty(permissionList), ExceptionCons.LOST_NEXT_PERMISSION);
        // 遍历权限集合，生成流程用户
        User user;
        for (String permission : permissionList) {
            user = FlowFactory.newUser()
                    .setType(UserType.APPROVAL.getKey())
                    .setProcessedBy(permission)
                    .setAssociated(task.getId());
            FlowFactory.dataFillHandler().idFill(user);
            userList.add(user);
        }
        return userList;
    }

    @Override
    public void delUser(List<Long> ids) {
        getDao().deleteByTaskIds(ids);
    }

    @Override
    public List<String> getPermission(long id) {
        return FlowFactory.userService()
                .list(FlowFactory.newUser().setAssociated(id))
                .stream()
                .map(User::getProcessedBy)
                .collect(Collectors.toList());
    }

    @Override
    public boolean updatePermissionByTaskId(Long taskId, List<String> permissions) {
        // 先删除当前任务的权限人
        delUser(CollUtil.toList(taskId));
        // 再新增权限人
        List<User> userList = new ArrayList<>();
        for (String permission : permissions) {
            User user = FlowFactory.newUser()
                    .setType(UserType.ASSIGNEE.getKey())
                    .setProcessedBy(permission)
                    .setAssociated(taskId);
            userList.add(user);
        }
        saveBatch(userList);
        return true;
    }
}
