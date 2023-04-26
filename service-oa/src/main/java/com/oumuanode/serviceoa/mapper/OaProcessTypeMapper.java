package com.oumuanode.serviceoa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oumuanode.model.model.process.ProcessType;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 审批类型 Mapper 接口
 * </p>
 */
@Mapper
public interface OaProcessTypeMapper extends BaseMapper<ProcessType> {

}
