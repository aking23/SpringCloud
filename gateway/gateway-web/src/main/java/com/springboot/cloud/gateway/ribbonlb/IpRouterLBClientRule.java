package com.springboot.cloud.gateway.ribbonlb;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public class IpRouterLBClientRule extends AbstractLoadBalancerRule {

    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            System.out.println("no load balancer");
            return null;
        }

        Server server = null;
        int count = 0;
        while (server == null && count++ < 10) {
            List<Server> reachableServers = lb.getReachableServers();
            List<Server> allServers = lb.getAllServers();
            int upCount = reachableServers.size();
            int serverCount = allServers.size();

            if ((upCount == 0) || (serverCount == 0)) {
                System.out.println("No up servers available from load balancer: " + lb);
                return null;
            }

            int nextServerIndex = ipUserHash(serverCount, (String) key);
            server = allServers.get(nextServerIndex);

            if (server == null) {
                /* Transient. */
                Thread.yield();
                continue;
            }

            if (server.isAlive() && (server.isReadyToServe())) {
                return (server);
            }

            // Next.
            server = null;
        }

        if (count >= 10) {
            System.out.println("No available alive servers after 10 tries from load balancer: "
                    + lb);
        }
        return server;

    }

    private int ipUserHash(int serverCount, String ipAddr) {
        String userTicket = ipAddr;
        String userIp = ipAddr;
        try {
            userIp = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
        }
        int userHashCode = Math.abs((userIp+userTicket).hashCode());
        return userHashCode%serverCount;
    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }
}
