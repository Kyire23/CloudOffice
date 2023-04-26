package com.oumuanode.serviceoa.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oumuanode.model.model.process.ProcessRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 审批记录 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2023-02-15
 */

@Mapper
public interface OaProcessRecordMapper extends BaseMapper<ProcessRecord> {

}
