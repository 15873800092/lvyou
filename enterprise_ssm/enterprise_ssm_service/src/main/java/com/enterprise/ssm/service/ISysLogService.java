package com.enterprise.ssm.service;

import com.enterprise.ssm.domain.SysLog;

import java.util.List;

public interface ISysLogService {

    void save(SysLog sysLog) throws Exception;

    List<SysLog> findAll(Integer page, Integer size) throws Exception;
}
