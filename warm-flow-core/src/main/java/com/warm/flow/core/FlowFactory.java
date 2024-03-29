package com.warm.flow.core;

import com.warm.flow.core.dao.*;
import com.warm.flow.core.entity.*;
import com.warm.flow.core.service.*;

import java.util.function.Supplier;

/**
 * 流程定义工程
 *
 * @author warm
 */
public class FlowFactory {


    private static DefService defService = null;
    private static HisTaskService hisTaskService = null;
    private static InsService insService = null;
    private static NodeService nodeService = null;
    private static SkipService skipService = null;
    private static TaskService taskService = null;


    private static Supplier<Definition> defSupplier;
    private static Supplier<HisTask> hisTaskSupplier;
    private static Supplier<Instance> insSupplier;
    private static Supplier<Node> nodeSupplier;
    private static Supplier<Skip> skipSupplier;
    private static Supplier<Task> taskSupplier;

    public FlowFactory(DefService defService, HisTaskService hisTaskService
            , InsService insService, NodeService nodeService
            , SkipService skipService, TaskService taskService) {
        FlowFactory.defService = defService;
        FlowFactory.hisTaskService = hisTaskService;
        FlowFactory.insService = insService;
        FlowFactory.nodeService = nodeService;
        FlowFactory.skipService = skipService;
        FlowFactory.taskService = taskService;
    }

    public static DefService defService() {
        return defService;
    }

    public static HisTaskService hisTaskService() {
        return hisTaskService;
    }

    public static InsService insService() {
        return insService;
    }

    public static NodeService nodeService() {
        return nodeService;
    }

    public static SkipService skipService() {
        return skipService;
    }

    public static TaskService taskService() {
        return taskService;
    }

    public static void setDefSupplier(Supplier<Definition> supplier) {
        FlowFactory.defSupplier = supplier;
    }

    public static Definition newDef() {
        return defSupplier.get();
    }

    public static void setHisTaskSupplier(Supplier<HisTask> supplier) {
        FlowFactory.hisTaskSupplier = supplier;
    }

    public static HisTask newHisTask() {
        return hisTaskSupplier.get();
    }

    public static void setInsSupplier(Supplier<Instance> supplier) {
        FlowFactory.insSupplier = supplier;
    }

    public static Instance newIns() {
        return insSupplier.get();
    }

    public static void setNodeSupplier(Supplier<Node> supplier) {
        FlowFactory.nodeSupplier = supplier;
    }

    public static Node newNode() {
        return nodeSupplier.get();
    }

    public static void setSkipSupplier(Supplier<Skip> supplier) {
        FlowFactory.skipSupplier = supplier;
    }

    public static Skip newSkip() {
        return skipSupplier.get();
    }

    public static void setTaskSupplier(Supplier<Task> supplier) {
        FlowFactory.taskSupplier = supplier;
    }

    public static Task newTask() {
        return taskSupplier.get();
    }
}
