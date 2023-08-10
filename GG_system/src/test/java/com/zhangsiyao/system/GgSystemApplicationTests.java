package com.zhangsiyao.system;

import com.zhangsiyao.common.entity.system.vo.RoleQueryVo;
import com.zhangsiyao.system.service.IRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GgSystemApplicationTests {

    @Autowired
    IRoleService roleService;

    @Test
    void contextLoads() {
        RoleQueryVo roleQueryVo=new RoleQueryVo();
        roleQueryVo.setPageNum(1L);
        roleQueryVo.setPageSize(20L);
        roleService.list(roleQueryVo);
    }

}
