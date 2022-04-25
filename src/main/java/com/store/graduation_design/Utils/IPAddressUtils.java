package com.store.graduation_design.Utils;

import java.net.InetAddress;

public class IPAddressUtils {

    public static String getLocalIP() throws Exception {
        try {
            System.out.println(InetAddress.getLocalHost().getHostAddress());
            return InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
            return "获取IP地址失败";
        }
    }
}
