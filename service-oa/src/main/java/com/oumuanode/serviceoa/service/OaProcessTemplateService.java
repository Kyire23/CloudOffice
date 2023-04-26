package com.oumuanode.serviceoa.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oumuanode.model.model.process.ProcessTemplate;

/**
 * <p>
 * 审批模板 服务类
 * </p>
 */
public interface OaProcessTemplateService extends IService<ProcessTemplate> {

    IPage<ProcessTemplate> selectPageProcessTempate(Page<ProcessTemplate> pageParam);

    void publish(Long id);
}
