package org.spring.cloud.alibaba.learn.security.authorization.server.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author xianglujun
 * @date 2023/6/7 16:59
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomClientDetails implements ClientDetails {

    private String clientId;
    private Set<String> resourceIds;
    private boolean secretRequired;
    private String clientSecret;
    private boolean scoped;
    private Set<String> scope;
    private Set<String> authorizedGrantTypes;
    private Set<String> registeredRedirectUri;
    private boolean autoApprove;
    private Collection<GrantedAuthority> authorities;
    private Map<String, Object> additionalInformation;
    private Integer refreshTokenValiditySeconds;
    private Integer accessTokenValiditySeconds;

    @Override
    public boolean isAutoApprove(String scope) {
        return this.autoApprove;
    }

    public String getClientId() {
        return this.clientId;
    }

    public Set<String> getResourceIds() {
        return this.resourceIds;
    }

    public boolean isSecretRequired() {
        return this.secretRequired;
    }

    public String getClientSecret() {
        return this.clientSecret;
    }

    public boolean isScoped() {
        return scoped;
    }

    public Set<String> getScope() {
        return this.scope;
    }

    public Set<String> getAuthorizedGrantTypes() {
        return this.authorizedGrantTypes;
    }

    public Set<String> getRegisteredRedirectUri() {
        return this.registeredRedirectUri;
    }

    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public Integer getAccessTokenValiditySeconds() {
        return this.accessTokenValiditySeconds;
    }

    public Integer getRefreshTokenValiditySeconds() {
        return this.refreshTokenValiditySeconds;
    }

    public Map<String, Object> getAdditionalInformation() {
        return this.additionalInformation;
    }
}
