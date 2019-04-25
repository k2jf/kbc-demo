package com.k2data.kbc.auth.service.response;

import com.k2data.kbc.auth.model.ResourceType;
import org.springframework.util.StringUtils;

public class ResourceTypeResponse {

    private Integer id;
    private String name;
    private String[] operations;

    public ResourceTypeResponse() {
    }

    public ResourceTypeResponse(ResourceType resourceType) {
        this.id = resourceType.getId();
        this.name = resourceType.getName();
        String operationsStr = resourceType.getOperations();
        if (!StringUtils.isEmpty(operationsStr)) {
            this.operations = resourceType.getOperations().split(",");
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getOperations() {
        return operations;
    }

    public void setOperations(String[] operations) {
        this.operations = operations;
    }
}
