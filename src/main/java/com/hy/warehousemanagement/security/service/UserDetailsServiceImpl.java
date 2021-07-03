package com.hy.warehousemanagement.security.service;

import com.hy.warehousemanagement.mapper.PermissionManagementsMapper;
import com.hy.warehousemanagement.mapper.PersonnelManagementMapper;
import com.hy.warehousemanagement.pojo.PermissionManagement;
import com.hy.warehousemanagement.pojo.PersonnelManagement;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author heyin
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private PersonnelManagementMapper personnelManagementMapper;

    @Resource
    private PermissionManagementsMapper permissionManagementsMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        PersonnelManagement user = personnelManagementMapper.getPersonnelManagementByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        PermissionManagement permissionManagement = permissionManagementsMapper.queryPermissionManagementByPermissionId(user.getPermissionLevel());

        System.out.println("当前用户的权限为"+user.getUserName()+"~~~~~~"+permissionManagement.getAuthorityList());
        System.out.println("数据库密码="+user.getUserPassword());

        return new User(username,passwordEncoder.encode(user.getUserPassword()),
                AuthorityUtils.commaSeparatedStringToAuthorityList(permissionManagement.getAuthorityList()));
    }
}
