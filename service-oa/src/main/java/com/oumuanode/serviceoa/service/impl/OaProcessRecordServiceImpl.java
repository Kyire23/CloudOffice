package com.oumuanode.serviceoa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oumuanode.model.model.process.ProcessRecord;
import com.oumuanode.model.model.system.SysUser;
import com.oumuanode.serviceoa.mapper.OaProcessRecordMapper;
import com.oumuanode.serviceoa.service.OaProcessRecordService;
import com.oumuanode.serviceoa.service.SysUserService;
import com.oumuanode.springsecurity.custom.LoginUserInfoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 审批记录 服务实现类
 * </p>
 */
@Service
public class OaProcessRecordServiceImpl extends ServiceImpl<OaProcessRecordMapper, ProcessRecord> implements OaProcessRecordService {
    @Autowired
    private SysUserService sysUserService;

    @Override
    public void record(Long processId, Integer status, String description) {
        Long userId = LoginUserInfoHelper.getUserId();
        SysUser sysUser = sysUserService.getById(userId);
        ProcessRecord processRecord = new ProcessRecord();
        processRecord.setProcessId(processId);
        processRecord.setStatus(status);
        processRecord.setDescription(description);
        processRecord.setOperateUser(sysUser.getName());
        processRecord.setOperateUserId(userId);
        baseMapper.insert(processRecord);
    }

}
