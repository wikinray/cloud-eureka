package com.xmylx.cloud.demo.consumer.discoverclient;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.catalog.CatalogServicesRequest;
import com.ecwid.consul.v1.health.HealthServicesRequest;
import com.ecwid.consul.v1.health.model.HealthService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryClient;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.cloud.consul.discovery.ConsulServerUtils.findHost;
import static org.springframework.cloud.consul.discovery.ConsulServerUtils.getMetadata;

public class MyConsulDiscoveryClient implements DiscoveryClient {

    private static final Log log = LogFactory.getLog(ConsulDiscoveryClient.class);

    private final ConsulClient client;

    private final ConsulDiscoveryProperties properties;

    public MyConsulDiscoveryClient(ConsulClient client, ConsulDiscoveryProperties properties) {
        this.client = client;
        this.properties = properties;
    }

    @Override
    public String description() {
        return "Spring Cloud Consul Discovery Client";
    }

    @Override
    public List<ServiceInstance> getInstances(final String serviceId) {
        return getInstances(serviceId,
                new QueryParams(this.properties.getConsistencyMode()));
    }

    public List<ServiceInstance> getInstances(final String serviceId,
                                              final QueryParams queryParams) {
        List<ServiceInstance> instances = new ArrayList<>();

        addInstancesToList(instances, serviceId, queryParams);

        return instances;
    }

    private void addInstancesToList(List<ServiceInstance> instances, String serviceId,
                                    QueryParams queryParams) {
        HealthServicesRequest request = HealthServicesRequest.newBuilder()
                .setTag(getTag(serviceId))
                .setPassing(this.properties.isQueryPassing()).setQueryParams(queryParams)
                .setToken(this.properties.getAclToken()).build();
        Response<List<HealthService>> services = this.client.getHealthServices(serviceId,
                request);

        for (HealthService service : services.getValue()) {
            String host = findHost(service);

            Map<String, String> metadata = getMetadata(service,
                    this.properties.isTagsAsMetadata());
            boolean secure = false;
            if (metadata.containsKey("secure")) {
                secure = Boolean.parseBoolean(metadata.get("secure"));
            }
            instances.add(new DefaultServiceInstance(service.getService().getId(),
                    serviceId, host, service.getService().getPort(), secure, metadata));
        }
    }

    public List<ServiceInstance> getAllInstances() {
        List<ServiceInstance> instances = new ArrayList<>();

        Response<Map<String, List<String>>> services = this.client
                .getCatalogServices(CatalogServicesRequest.newBuilder()
                        .setQueryParams(QueryParams.DEFAULT).build());
        for (String serviceId : services.getValue().keySet()) {
            addInstancesToList(instances, serviceId, QueryParams.DEFAULT);
        }
        return instances;
    }

    @Override
    public List<String> getServices() {
        String aclToken = this.properties.getAclToken();

        CatalogServicesRequest request = CatalogServicesRequest.newBuilder()
                .setQueryParams(QueryParams.DEFAULT)
                .setToken(this.properties.getAclToken()).build();
        return new ArrayList<>(
                this.client.getCatalogServices(request).getValue().keySet());
    }

    @Override
    public int getOrder() {
        return this.properties.getOrder();
    }

    //添加获取tag方法
    private String getTag(String serviceId){
        return this.properties.getQueryTagForService(serviceId);
    }
}
