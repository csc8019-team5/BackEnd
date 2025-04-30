package uk.ac.ncl.team5project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.ncl.team5project.entity.Admin;
import uk.ac.ncl.team5project.entity.AdminExample;
import uk.ac.ncl.team5project.mapper.AdminMapper;
import uk.ac.ncl.team5project.service.AdminService;

/**
 * @file AdminServiceImpl.java
 * @date 2025-04-10 11:08
 * @function_description: This is an additional function for administors display.
 * @discussion: `display()` can show all administors if is needed
 * @development_history: 
 *     @designer Qingyu Cao 
 *     @reviewer: Qingyu Cao
 *     @review_date: 30/04
 *     @modification_date: 30/04
 *     @description: As it was decided through discussion that administrator accounts
 *                   would be pre-configured and stored in the database, 
 *                   this class will not currently include functions for adding, deleting, or modifying them.
 *                   If necessary during future testing, relevant methods can be added.
 */

 @Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<Admin> display() {
        // TODO Auto-generated method stub
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria();
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        return admins;
    }


}
