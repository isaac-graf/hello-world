package com.sbna.vendorportal.dto.enums;

import com.sbna.vendorportal.config.ProjectConfig;
import org.springframework.web.util.UriTemplate;

public enum ApiUri {

    RESET_PASSWORD_PAGE(ProjectConfig.RESET_PASSWORD_URI_TEMPLATE);

    private String uriTemplateStr;
    private UriTemplate uriTemplate;

    ApiUri(String uriTemplateStr) {
        this.uriTemplateStr = uriTemplateStr;
        this.uriTemplate = new UriTemplate(uriTemplateStr);
    }

    public String getUriTemplateStr() {
        return uriTemplateStr;
    }

    public void setUriTemplateStr(String uriTemplateStr) {
        this.uriTemplateStr = uriTemplateStr;
    }

    public UriTemplate getUriTemplate() {
        return uriTemplate;
    }

    public void setUriTemplate(UriTemplate uriTemplate) {
        this.uriTemplate = uriTemplate;
    }
}
