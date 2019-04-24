package com.k2data.kbc.auth.service;

import com.k2data.kbc.api.KbcBizException;
import com.k2data.kbc.auth.dao.OwnerPermissionMapper;
import com.k2data.kbc.auth.dao.OwnerRoleMapper;
import com.k2data.kbc.auth.dao.RoleMapper;
import com.k2data.kbc.auth.dao.RolePermissionMapper;
import com.k2data.kbc.auth.dao.condition.RoleCondition;
import com.k2data.kbc.auth.model.OwnerPermission;
import com.k2data.kbc.auth.model.OwnerRole;
import com.k2data.kbc.auth.model.Resource;
import com.k2data.kbc.auth.model.Role;
import com.k2data.kbc.auth.model.RolePermission;
import com.k2data.kbc.auth.service.request.CreateRoleRequest;
import com.k2data.kbc.auth.service.request.ModifyPermissionRequest;
import com.k2data.kbc.auth.service.response.OwnerRoleResponse;
import com.k2data.kbc.auth.service.response.PermissionResponse;
import com.k2data.kbc.auth.service.response.RolePermissionResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PermmgrSevice {

    @Autowired
    ResmgrService resmgrService;

    @Autowired
    OwnerPermissionMapper ownerPermissionMapper;

    @Autowired
    OwnerRoleMapper ownerRoleMapper;

    @Autowired
    RolePermissionMapper rolePermissionMapper;

    @Autowired
    RoleMapper roleMapper;

    public void createRole(CreateRoleRequest createRoleRequest) {
        Role role = new Role();
        role.setName(createRoleRequest.getName());
        role.setDescription(createRoleRequest.getDescription());

        roleMapper.insert(role);
    }

    public void deleteRole(Integer roleId) throws KbcBizException {
        List<Integer> ownerIds = ownerRoleMapper.getOwnerIdsByRoleId(roleId);
        if (null != ownerIds && !ownerIds.isEmpty()) {
            throw new KbcBizException("有关联此角色的用户组，不能删除");
        }

        roleMapper.delete(roleId);
    }

    public void deletePermissionsByOwnerId(Integer ownerId) {
        ownerPermissionMapper.deleteByOwnerId(ownerId);
    }

    public void deleteRolesByOwnerId(Integer ownerId) {
        ownerRoleMapper.deleteByOwnerId(ownerId);
    }

    public void deleteOwnerRole(Integer roleOwnerId, Integer roleId) {
        ownerRoleMapper.deleteByOwnerIdAndRoleId(roleOwnerId, roleId);
    }

    public void deleteOwnerPermission(Integer ownerId, Integer resourceId) {
        ownerPermissionMapper.delete(ownerId, resourceId);
    }

    public void deleteRolePermission(Integer roleId, Integer resourceId) {
        rolePermissionMapper.delete(roleId, resourceId);
    }

    @Transactional
    public void modifyOwnerPermissions(Integer permOwnerId,
        ModifyPermissionRequest modifyPermissionRequest) {

        String[] roleIdStrs = null == modifyPermissionRequest.getResourceIds() ? new String[0]
            : modifyPermissionRequest.getResourceIds().split(",");

        List<OwnerPermission> ownerPermissions = ownerPermissionMapper.getByOwnerId(permOwnerId);

        // 新增
        for (String resourceIdStr : roleIdStrs) {
            Integer resourceId = Integer.valueOf(resourceIdStr);
            boolean existed = false;
            for (OwnerPermission ownerPermission : ownerPermissions) {
                if (ownerPermission.getResourceId() == resourceId) {
                    existed = true;
                }
            }

            if (!existed) {
                OwnerPermission newPermission = new OwnerPermission();
                newPermission.setOwnerId(permOwnerId);
                newPermission.setResourceId(resourceId);
                newPermission.setOperations(modifyPermissionRequest.getOperations());
                ownerPermissionMapper.insert(newPermission);
            }
        }

        for (OwnerPermission ownerPermission : ownerPermissions) {
            boolean existed = false;
            for (String resourceIdStr : roleIdStrs) {
                if (ownerPermission.getResourceId() == Integer.valueOf(resourceIdStr)) {
                    existed = true;
                }
            }

            if (!existed) {
                ownerPermissionMapper.delete(permOwnerId, ownerPermission.getResourceId());
            }
        }
    }

    @Transactional
    public void modifyOwnerRoles(Integer roleOwnerId, String roleIdsStr) {
        String[] roleIdStrs = null == roleIdsStr ? new String[0] : roleIdsStr.split(",");

        List<Integer> existRoleIds = ownerRoleMapper.getRoleIdsByOwnerId(roleOwnerId);

        // 获取新关联的roleId
        for (String roleIdStr : roleIdStrs) {
            Integer roleId = Integer.valueOf(roleIdStr);

            boolean existed = false;
            for (Integer existRoleId : existRoleIds) {
                if (roleId == existRoleId) {
                    existed = true;
                    break;
                }
            }

            if (!existed) {
                OwnerRole ownerRole = new OwnerRole();
                ownerRole.setOwnerId(roleOwnerId);
                ownerRole.setRoleId(Integer.valueOf(roleIdStr));
                ownerRoleMapper.insert(ownerRole);
            }
        }

        // 获取已剔除的roleId
        for (Integer existRoleId : existRoleIds) {
            boolean existed = false;

            for (String roleIdStr : roleIdStrs) {
                if (Integer.valueOf(roleIdStr) == existRoleId) {
                    existed = true;
                    break;
                }
            }

            if (!existed) {
                ownerRoleMapper.deleteByOwnerIdAndRoleId(roleOwnerId, existRoleId);
            }
        }
    }

    @Transactional
    public void modifyOwnersForRole(Integer roleId, String roleOwnerIdsStr) {
        String[] ownerIdStrs = null == roleOwnerIdsStr ? new String[0] : roleOwnerIdsStr.split(",");

        List<Integer> existOwnerIds = ownerRoleMapper.getOwnerIdsByRoleId(roleId);

        // 获取新关联
        for (String ownerIdStr : ownerIdStrs) {
            Integer ownerId = Integer.valueOf(ownerIdStr);

            boolean existed = false;
            for (Integer existOwnerId : existOwnerIds) {
                if (ownerId == existOwnerId) {
                    existed = true;
                    break;
                }
            }

            if (!existed) {
                OwnerRole ownerRole = new OwnerRole();
                ownerRole.setOwnerId(ownerId);
                ownerRole.setRoleId(roleId);
                ownerRoleMapper.insert(ownerRole);
            }
        }

        // 获取已剔除
        for (Integer existOwnerId : existOwnerIds) {
            boolean existed = false;

            for (String ownerIdStr : ownerIdStrs) {
                if (Integer.valueOf(ownerIdStr) == existOwnerId) {
                    existed = true;
                }
            }

            if (!existed) {
                ownerRoleMapper.deleteByOwnerIdAndRoleId(existOwnerId, roleId);
            }
        }
    }

    @Transactional
    public void modifyRolePermssions(Integer roleId, String resourceIdsStr, String operations) {
        String[] resourceIdStrs = null == resourceIdsStr ? new String[0] : resourceIdsStr.split(",");

        List<Integer> existResourceIds = rolePermissionMapper.getResourceIdByRoleId(roleId);

        // 获取新关联
        for (String resourceIdStr : resourceIdStrs) {
            Integer resourceId = Integer.valueOf(resourceIdStr);

            boolean existed = false;
            for (Integer existResourceId : existResourceIds) {
                if (resourceId == existResourceId) {
                    existed = true;
                    break;
                }
            }

            if (!existed) {
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRoleId(roleId);
                rolePermission.setResourceId(resourceId);
                rolePermission.setOperations(operations);
                rolePermissionMapper.insert(rolePermission);
            }
        }

        // 获取已剔除
        for (Integer existResourceId : existResourceIds) {
            boolean existed = false;

            for (String resourceIdStr : resourceIdStrs) {
                if (Integer.valueOf(resourceIdStr) == existResourceId) {
                    existed = true;
                }
            }

            if (!existed) {
                rolePermissionMapper.delete(roleId, existResourceId);
            }
        }
    }

    public void availableOwnerRole(Integer roleOwnerId, Integer roleId, Long effectTime,
        Long expireTime, Boolean disabled) {
        OwnerRole ownerRole = new OwnerRole();
        ownerRole.setOwnerId(roleOwnerId);
        ownerRole.setRoleId(roleId);
        if (null != effectTime) {
            ownerRole.setEffectTime(new Date(effectTime));
        }
        if (null != expireTime) {
            ownerRole.setExpireTime(new Date(expireTime));
        }
        ownerRole.setDisabled(disabled);
        ownerRoleMapper.update(ownerRole);
    }

    public void availableOwnerPermission(Integer ownerId, Integer resourceId, Long effectTime,
        Long expireTime, Boolean disabled) {
        OwnerPermission ownerPermission = new OwnerPermission();
        ownerPermission.setOwnerId(ownerId);
        ownerPermission.setResourceId(resourceId);
        if (null != effectTime) {
            ownerPermission.setEffectTime(new Date(effectTime));
        }
        if (null != expireTime) {
            ownerPermission.setExpireTime(new Date(expireTime));
        }
        ownerPermission.setDisabled(disabled);
        ownerPermissionMapper.update(ownerPermission);
    }

    public List<PermissionResponse> getPermissions(Integer permOwnerId,
        List<Integer> roleOwnerIds) {
        List<PermissionResponse> result = new ArrayList<>();

        if (null != permOwnerId) {
            result.addAll(getPermissionsByPermOwnerId(permOwnerId));
        }

        // 获取role下的所有权限
        if (null != roleOwnerIds) {
            for (Integer roleOwnerId : roleOwnerIds) {
                result.addAll(getPermissionsByRoleOwnerId(roleOwnerId));
            }
        }

        // 去重
        return filterDuplicatePermissions(result);
    }

    public List<Integer> getRoleOwnerIds(Integer roleId) {
        return ownerRoleMapper.getOwnerIdsByRoleId(roleId);
    }

    public List<Role> listRoles(String fuzzyName) {
        RoleCondition condition = new RoleCondition();
        condition.setFuzzyName(fuzzyName);

        return roleMapper.list(condition);
    }

    public List<RolePermissionResponse> listRolePermissions(Integer roleId, Integer resourceTypeId, String fuzzyResName) {
        List<RolePermissionResponse> result = new ArrayList<>();

        List<Resource> resources = resmgrService.list(resourceTypeId, fuzzyResName);
        Map<Integer, Resource> resourceMap = new HashMap<>();
        for (Resource resource : resources) {
            resourceMap.put(resource.getId(), resource);
        }

        List<RolePermission> rolePermissions = rolePermissionMapper.getByRoleId(roleId);
        for (RolePermission rolePermission : rolePermissions) {
            Resource resource = resourceMap.get(rolePermission.getResourceId());
            if (null == resource) {
                continue;
            }

            result.add(new RolePermissionResponse(rolePermission, resource));
        }
        return result;
    }

    public List<OwnerRoleResponse> listOwnerRoles(String fuzzyName, Integer ownerId) {
        List<OwnerRoleResponse> result = new ArrayList<>();
        if (null == ownerId) {
            return result;
        }

        // 查owner下所有role信息
        List<OwnerRole> ownerRoles = ownerRoleMapper.getByOwnerId(ownerId);

        // 查roleMap
        RoleCondition condition = new RoleCondition();
        condition.setFuzzyName(fuzzyName);
        List<Integer> roleIds = new ArrayList<>();
        for (OwnerRole ownerRole : ownerRoles) {
            roleIds.add(ownerRole.getRoleId());
        }
        condition.setIds(roleIds);
        List<Role> roles = roleMapper.list(condition);
        Map<Integer, Role> roleMap = new HashMap<>();
        for (Role role : roles) {
            roleMap.put(role.getId(), role);
        }

        for (OwnerRole ownerRole : ownerRoles) {
            Role role = roleMap.get(ownerRole.getRoleId());
            // 过滤掉非fuzzyName
            if (null == role) {
                continue;
            }
            result.add(new OwnerRoleResponse(ownerRole, role));
        }
        return result;
    }

    private List<PermissionResponse> filterDuplicatePermissions(
        List<PermissionResponse> permissions) {
        List<PermissionResponse> result = new ArrayList<>();
        if (null == permissions) {
            return result;
        }

        Map<Integer, PermissionResponse> permissionMap = new HashMap<>();
        for (PermissionResponse permission : permissions) {
            PermissionResponse savedPermission = permissionMap.get(permission.getResourceId());
            if (null == savedPermission) {
                permissionMap.put(permission.getResourceId(), permission);
                continue;
            }

            boolean isSavedDisabled =
                null == savedPermission.getDisabled() ? false : savedPermission.getDisabled();
            boolean isCurDisabled =
                null == permission.getDisabled() ? false : permission.getDisabled();
            if (isSavedDisabled && !isCurDisabled) {
                permissionMap.put(permission.getResourceId(), permission);
                continue;
            }
            if (null == permission.getEffectTime() || permission.getEffectTime() < savedPermission
                .getEffectTime()) {
                savedPermission.setEffectTime(permission.getEffectTime());
            }
            if (null == permission.getExpireTime() || permission.getExpireTime() > savedPermission
                .getExpireTime()) {
                savedPermission.setExpireTime(permission.getExpireTime());
            }
        }

        result.addAll(permissionMap.values());
        return result;
    }
    private List<PermissionResponse> getPermissionsByPermOwnerId(Integer permOwnerId) {
        return getPermissionsByPermOwnerId(permOwnerId, null, null);
    }

    public List<PermissionResponse> getPermissionsByPermOwnerId(Integer permOwnerId, Integer resourceTypeId, String fuzzyResName) {
        List<PermissionResponse> result = new ArrayList<>();

        List<OwnerPermission> ownerPermissions = null;
        if (null != permOwnerId) {
            ownerPermissions = ownerPermissionMapper.getByOwnerId(permOwnerId);
        }

        if (null == ownerPermissions) {
            return result;
        }

        Map<Integer, Resource> resourceMap = new HashMap<>();
        List<Resource> resources = resmgrService.list(resourceTypeId, fuzzyResName);
        for (Resource resource : resources) {
            resourceMap.put(resource.getId(), resource);
        }


        for (OwnerPermission ownerPermission : ownerPermissions) {
            Resource resource = resourceMap.get(ownerPermission.getResourceId());
            if (null == resource) {
                continue;
            }
            PermissionResponse permissionResponse = new PermissionResponse();
            permissionResponse.setResourceId(ownerPermission.getResourceId());
            permissionResponse
                .setResourceName(resource.getName());
            if (null != ownerPermission.getEffectTime()) {
                permissionResponse.setEffectTime(ownerPermission.getEffectTime().getTime());
            }
            if (null != ownerPermission.getExpireTime()) {
                permissionResponse.setExpireTime(ownerPermission.getExpireTime().getTime());
            }
            permissionResponse.setOperations(ownerPermission.getOperations());
            result.add(permissionResponse);
        }
        return result;
    }

    private List<PermissionResponse> getPermissionsByRoleOwnerId(Integer roleOwnerId) {
        List<PermissionResponse> result = new ArrayList<>();

        List<OwnerRole> ownerRoles = null;
        if (null != roleOwnerId) {
            ownerRoles = ownerRoleMapper.getByOwnerId(roleOwnerId);
        }
        if (null == ownerRoles) {
            return result;
        }

        // 过滤掉不可用的
        for (OwnerRole ownerRole : ownerRoles) {
            if (ownerRole.getDisabled()) {
                continue;
            }

            List<RolePermission> rolePermissions = rolePermissionMapper
                .getByRoleId(ownerRole.getRoleId());
            List<PermissionResponse> rolePermissionResponse = buildPermissionResponses(ownerRole,
                rolePermissions);
            result.addAll(rolePermissionResponse);
        }

        return result;
    }

    private List<PermissionResponse> buildPermissionResponses(OwnerRole ownerRole,
        List<RolePermission> rolePermissions) {
        List<PermissionResponse> result = new ArrayList<>();
        if (null == ownerRole || null == rolePermissions) {
            return result;
        }

        for (RolePermission rolePermission : rolePermissions) {
            PermissionResponse permissionResponse = new PermissionResponse();
            permissionResponse.setResourceId(rolePermission.getResourceId());
            permissionResponse
                .setResourceName(resmgrService.getNameById(rolePermission.getResourceId()));
            if (null != ownerRole.getEffectTime()) {
                permissionResponse.setEffectTime(ownerRole.getEffectTime().getTime());
            }
            if (null != ownerRole.getExpireTime()) {
                permissionResponse.setExpireTime(ownerRole.getExpireTime().getTime());
            }
            permissionResponse.setOperations(rolePermission.getOperations());
            result.add(permissionResponse);
        }
        return result;
    }
}
