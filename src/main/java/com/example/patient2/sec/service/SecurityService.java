package com.example.patient2.sec.service;

import com.example.patient2.sec.entities.AppRole;
import com.example.patient2.sec.entities.AppUser;

public interface SecurityService {
    AppUser saveNewUser(String username,String password, String rePassword);
    AppRole saveNewRole(String roleName,String description);
    void addRoleToUser(String username,String roleName);
    void removeRoleFromUser(String username,String roleName);
    AppUser loadUserByUserName(String username);
}
