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
 * @function_description: 
 * @interface_description: 
 *     @calling_sequence: 
 *     @arguments_description: 
 *     @list_of_subordinate_classes: 
 * @discussion: 
 * @development_history: 
 *     @designer Qingyu Cao 
 *     @reviewer: 
 *     @review_date: 
 *     @modification_date: 
 *     @description: 
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
