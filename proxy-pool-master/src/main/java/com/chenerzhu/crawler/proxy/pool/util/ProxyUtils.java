package com.chenerzhu.crawler.proxy.pool.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import javax.net.ssl.HttpsURLConnection;

import com.chenerzhu.crawler.proxy.pool.entity.IpBean;

import lombok.extern.slf4j.Slf4j;
import sun.net.www.protocol.https.Handler;

/**
 * @author chenerzhu
 * @create 2018-09-05 21:14
 **/
public class ProxyUtils {
    //private static final String VALIDATE_URL = "http://115.239.211.112";
    private static final String VALIDATE_URL = "https://www.baidu.com";

    public static boolean validateIp(String ip, int port, ProxyType proxyType) {
        boolean available = false;
        if (proxyType.getType().equalsIgnoreCase("http")) {
            available = validateHttp(ip, port);
        } else if (proxyType.getType().equalsIgnoreCase("https")) {
            available = validateHttps(ip, port);
        }
        return available;
    }

    public static boolean validateHttp(String ip, int port) {
        boolean available = false;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(VALIDATE_URL);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port));
            connection = (HttpURLConnection) url.openConnection(proxy);
            connection.setRequestProperty("accept", "");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
            connection.setConnectTimeout(2 * 1000);
            connection.setReadTimeout(3 * 1000);
            connection.setInstanceFollowRedirects(false);
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String s = null;
            StringBuilder sb = new StringBuilder();
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            if (sb.toString().contains("baidu.com") && connection.getResponseCode() == 200) {
                available = true;
            }
        } catch (Exception e) {
            //e.printStackTrace();
            available = false;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return available;
    }

    public static boolean validateHttps(String ip, int port) {
        boolean available = false;
        HttpsURLConnection httpsURLConnection = null;
        try {
            URL url = new URL(null, VALIDATE_URL, new Handler());
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port));
            httpsURLConnection = (HttpsURLConnection) url.openConnection(proxy);
            httpsURLConnection.setSSLSocketFactory(HttpsUtils.getSslSocketFactory());
            httpsURLConnection.setHostnameVerifier(HttpsUtils.getTrustAnyHostnameVerifier());
            httpsURLConnection.setRequestProperty("accept", "");
            httpsURLConnection.setRequestProperty("connection", "Keep-Alive");
            httpsURLConnection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
            httpsURLConnection.setConnectTimeout(2 * 1000);
            httpsURLConnection.setReadTimeout(3 * 1000);
            httpsURLConnection.setInstanceFollowRedirects(false);
            BufferedReader br = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
            String s = null;
            StringBuilder sb = new StringBuilder();
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            if (sb.toString().contains("baidu.com") && httpsURLConnection.getResponseCode() == 200) {
                available = true;
            }
        } catch (Exception e) {
            //e.printStackTrace();
            available = false;
        } finally {
            if (httpsURLConnection != null) {
                httpsURLConnection.disconnect();
            }
        }
        return available;
    }

    public static void main(String[] args) {
        AtomicInteger counter = new AtomicInteger(0);
        CountDownLatch latch = new CountDownLatch(100);
        List<IpBean> ipBeans = ReadFileUtils.getIps();
        for (IpBean ipBean : ipBeans) {
            String type = ipBean.getType();
            if (type.equals("http")) {
                boolean availableHttp = ProxyUtils.validateHttp(ipBean.getHost(), ipBean.getPort());
                if (availableHttp) {
                    System.out.println("可用:" + ipBean.toString());
                }
            } else {
                boolean availableHttp = ProxyUtils.validateHttps(ipBean.getHost(), ipBean.getPort());
                if (availableHttp) {
                    System.out.println("可用:" + ipBean.toString());
                }
            }
        }
    }

    public enum ProxyType {
        HTTP("HTTP"),
        HTTPS("HTTPS"),
        SOCKS("SOCKS");
        private String type;

        ProxyType(String proxyType) {
            this.type = proxyType;
        }

        public String getType() {
            return type;
        }
    }
}