package com.bing.utils.utilstool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;


/**
 * 常用获取客户端信息的工具
 * 
 */
public final class NetworkUtil {

  private static final Logger LOG = LoggerFactory.getLogger(NetworkUtil.class);

  /**
   * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
   * 
   * @param request
   * @return
   */
  public final static String getIpAddress(HttpServletRequest request) {
    // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址

    String ip = request.getHeader("X-Forwarded-For");
    if (LOG.isInfoEnabled()) {
      LOG.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);
    }

    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("proxy-Client-IP");
        if (LOG.isInfoEnabled()) {
          LOG.info("getIpAddress(HttpServletRequest) - proxy-Client-IP - String ip=" + ip);
        }
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("WL-proxy-Client-IP");
        if (LOG.isInfoEnabled()) {
          LOG.info("getIpAddress(HttpServletRequest) - WL-proxy-Client-IP - String ip=" + ip);
        }
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("HTTP_CLIENT_IP");
        if (LOG.isInfoEnabled()) {
          LOG.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip=" + ip);
        }
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        if (LOG.isInfoEnabled()) {
          LOG.info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip=" + ip);
        }
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getRemoteAddr();
        if (LOG.isInfoEnabled()) {
          LOG.info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip=" + ip);
        }
      }
    } else if (ip.length() > 15) {
      String[] ips = ip.split(",");
      for (int index = 0; index < ips.length; index++) {
        String strIp = (String) ips[index];
        if (!("unknown".equalsIgnoreCase(strIp))) {
          ip = strIp;
          break;
        }
      }
    }
    return ip;
  }


  /**
   * @Description 获取网络内网ip
   * @author 刘博华
   * @date 2017年11月6日 下午2:19:46
   * @action getRealIps
   * @return String
   */
  public static String getRealIps() {
    StringBuffer ipps = new StringBuffer();
    String localip = null;// 本地IP，如果没有配置外网IP则返回它
    String netip = null;// 外网IP
    Enumeration<NetworkInterface> netInterfaces;
    try {
      netInterfaces = NetworkInterface.getNetworkInterfaces();
    } catch (SocketException e) {
      return null;
    }
    InetAddress ip = null;
    boolean finded = false;// 是否找到外网IP
    while (netInterfaces.hasMoreElements() && !finded) {
      NetworkInterface ni = netInterfaces.nextElement();
      Enumeration<InetAddress> address = ni.getInetAddresses();
      while (address.hasMoreElements()) {
        ip = address.nextElement();
        if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
            && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
          netip = ip.getHostAddress();
          ipps.append(netip);
          finded = true;
          break;
        } else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
            && ip.getHostAddress().indexOf(":") == -1) {// 内网IP
          localip = ip.getHostAddress();
          ipps.append(localip);
        }
      }
    }
    return ipps.toString();
  }


}
