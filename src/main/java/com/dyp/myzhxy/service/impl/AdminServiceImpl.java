package com.dyp.myzhxy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyp.myzhxy.mapper.AdminMapper;
import com.dyp.myzhxy.pojo.Admin;
import com.dyp.myzhxy.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @param
 * @Description: (描述此类的功能)
 * @return
 * @throws
 * @author: duyapeng
 * @date:
 */
@Service
@Transactional
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
}
