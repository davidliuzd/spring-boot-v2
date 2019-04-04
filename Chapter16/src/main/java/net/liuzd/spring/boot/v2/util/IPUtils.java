package net.liuzd.spring.boot.v2.util;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class IPUtils {

    private static final String UNKNOWN = "unknown";

    public static String getIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }

            if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (String ipStr : ips) {
                if (!(UNKNOWN.equalsIgnoreCase(ipStr))) {
                    return ipStr;
                }
            }
        }
        return ip;
    }

    @SuppressWarnings("rawtypes")
    public static String getIP() {
        String serverIp = "";
        try {
            Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                for (InterfaceAddress i : ni.getInterfaceAddresses()) {
                    i.getAddress();
                    ip = InetAddress.getLocalHost();
                    if ((ip.isSiteLocalAddress()) && (!ip.isLoopbackAddress()) && (ip.getHostAddress().indexOf(
                            ":") == -1)) {
                        serverIp = ip.getHostAddress();
                        break;
                    }
                    serverIp = "1";
                }
            }
        } catch (SocketException e1) {
            log.error("SocketException", e1);
        } catch (UnknownHostException e) {
            log.error("UnknownHostException", e);
        }

        return serverIp;
    }

    public static String getLocalIP() {
        InetAddress addr = null;
        String serverIp = "";
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            log.error("UnknownHostException", e);
        }
        if ((addr.isSiteLocalAddress()) && (!addr.isLoopbackAddress()) && (addr.getHostAddress().indexOf(":") == -1))
            serverIp = addr.getHostAddress();
        else {
            serverIp = "0.0.0.0";
        }
        return serverIp;
    }
}