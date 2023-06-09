package org.spring.cloud.alibaba.learn.security.authorization.server.service;

import org.spring.cloud.alibaba.learn.security.authorization.server.entity.CustomClientDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author xianglujun
 * @date 2023/6/7 16:48
 */
@Service
public class ClientDetailService extends InMemoryClientDetailsService {

    private Map<String, ClientDetails> clientDetailsMap = new HashMap<>();

    @PostConstruct
    public void init() {
        System.out.println("初始化客户端授权数据..");
        Set<String> grantTypes = new HashSet<>();
        grantTypes.addAll(Arrays.asList("authorization_code", "implicit", "password", "client_credentials"));
        ClientDetails clientDetails = CustomClientDetails.builder()
                .clientSecret("{noop}aaaa")
                .authorities(Collections.singletonList(new SimpleGrantedAuthority("ADMIN")))
                .accessTokenValiditySeconds((int) TimeUnit.MINUTES.toSeconds(10))
                .autoApprove(false)
                .scope(Collections.singleton("serv"))
                .clientId("clientId")
                .scoped(true)
                .secretRequired(true)
                .resourceIds(Collections.singleton("test"))
                .authorizedGrantTypes(grantTypes)
                .refreshTokenValiditySeconds((int) TimeUnit.MINUTES.toSeconds(10))
                .registeredRedirectUri(Collections.singleton("http://localhost:8080/login"))
                .build();

        clientDetailsMap.put(clientDetails.getClientId(), clientDetails);
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        ClientDetails clientDetails = clientDetailsMap.get(clientId);
        if (Objects.isNull(clientDetails)) {
            throw new NoSuchClientException("No client with requested id: " + clientId);
        }
        return clientDetails;
    }
}
