package com.example.demo.Common.utils;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.platform.commons.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lijunfu on 15-8-13.
 */
public class HttpClientUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);

    private final static String UTF8 = "UTF-8";

    private final static String APPLICATION_JSON = "application/json";

    private final static Integer CONNECT_TIME_OUT = 10 * 1000;
    private final static Integer READ_TIME_OUT = 10 * 1000;

    private final static Integer CONNECT_REQUEST_TIME_OUT = 5 * 1000;

    private final static Integer SOCKET_TIME_OUT = 30 * 1000;

    private final static String ASK = "?";
    private static HttpClient client = null;

    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(128);
        cm.setDefaultMaxPerRoute(128);
        client = HttpClients.custom().setConnectionManager(cm).build();
    }

    /**
     * @param uri      请求的URL地址
     * @param paramStr ?以后的字符串
     * @param header   http请求头
     * @return 返回请求响应的内容
     * @throws
     */
    public static String doGet(final String uri, final String paramStr, final Map<String, String> header) throws IOException {
        String resultStr = "";
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(SOCKET_TIME_OUT.intValue())
                .setConnectTimeout(CONNECT_TIME_OUT.intValue())
                .setConnectionRequestTimeout(CONNECT_REQUEST_TIME_OUT.intValue())
                .setStaleConnectionCheckEnabled(true)
                .build();
        CloseableHttpClient client = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
        HttpGet get = null;
        try {
            String url = uri + ASK + paramStr;
            get = new HttpGet(url);
            if (header != null) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    get.setHeader(entry.getKey(), entry.getValue());
                }
            }
            CloseableHttpResponse response = client.execute(get);
            int statusCode = response.getStatusLine().getStatusCode();
            if (200 == statusCode) {
                resultStr = new String(EntityUtils.toByteArray(response.getEntity()), UTF8);
//                LOGGER.info(LogBuilderUtil.getBuilder("doGet", "请求", "成功")
//                        .appendParam("url", uri)
//                        .appendParam("statusCode", statusCode)
//                        .appendParam("paramStr", paramStr)
//                        .appendParam("header", (header == null ? null : header.toString()))
//                        .appendParam("resultStr", resultStr)
//                        .build());
            } else {
//                LOGGER.error(LogBuilderUtil.getBuilder("doGet", "请求", "失败")
//                        .appendParam("url", uri)
//                        .appendParam("paramStr", paramStr)
//                        .appendParam("header", (header == null ? null : header.toString()))
//                        .appendParam("statusCode", statusCode)
//                        .build());
            }
            client.close();
        } catch (SocketTimeoutException e) {
//            LOGGER.error(LogBuilderUtil.getBuilder("doGet", "请求", "SocketTimeoutException")
//                    .appendParam("url", uri)
//                    .appendParam("paramJson", paramStr)
//                    .appendParam("paramStr", (header == null ? null : header.toString()))
//                    .build(), e);
        } catch (IOException e) {
//            LOGGER.error(LogBuilderUtil.getBuilder("doGet", "请求", "IOException")
//                    .appendParam("url", uri)
//                    .appendParam("paramJson", paramStr)
//                    .appendParam("paramStr", (header == null ? null : header.toString()))
//                    .build(), e);
        } finally {
            if (get != null) {
                get.releaseConnection();
            }
            if (client != null) {
                client.close();
            }
        }
        return resultStr;
    }

    /***
     * 营销活动专属Header
     * @author Feiting.Liang
     * @date 2018/11/12 15:32
     */
    public static Map<String, String> getMarketActityHeader() {
        HashMap header = new HashMap();
        header.put("Referer", "referer");
        header.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63");
        return header;
    }

    public static String doPost(final String uri, final String paramJson, final Map<String, String> header) {

//        LOGGER.info(LogBuilderUtil.getBuilder("doPost", "请求数据", "")
//                .appendParam("paramJson", paramJson).build());
        String resultStr = "";
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(SOCKET_TIME_OUT.intValue())
                .setConnectTimeout(CONNECT_TIME_OUT.intValue())
                .setConnectionRequestTimeout(CONNECT_REQUEST_TIME_OUT.intValue())
                .setStaleConnectionCheckEnabled(true)
                .build();
        CloseableHttpClient client = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();

        HttpPost post = new HttpPost(uri);
        if (header != null) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                post.setHeader(entry.getKey(), entry.getValue());
            }
        }
        StringEntity se = new StringEntity(StringUtils.isBlank(paramJson) ? "{}" : paramJson, UTF8);
        se.setContentType(APPLICATION_JSON);
        post.setEntity(se);
        CloseableHttpResponse response = null;
        try {
            response = client.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            if (200 == statusCode) {
                resultStr = new String(EntityUtils.toByteArray(response.getEntity()), UTF8);
//                LOGGER.info(LogBuilderUtil.getBuilder("doPost", "请求", "成功")
//                        .appendParam("url", uri)
//                        .appendParam("statusCode", statusCode)
//                        .build());
            } else {
//                LOGGER.error(LogBuilderUtil.getBuilder("doPost", "请求", "失败")
//                        .appendParam("url", uri)
//                        .appendParam("header", (header == null ? null : header.toString()))
//                        .appendParam("statusCode", statusCode)
//                        .build());
            }
//            client.close();
        } catch (SocketTimeoutException e) {
//            LOGGER.error(LogBuilderUtil.getBuilder("doPost", "请求", "SocketTimeoutException")
//                    .appendParam("url", uri)
//                    //.appendParam("paramJson", paramJson)
//                    .appendParam("header", (header == null ? null : header.toString()))
//                    .build(), e);
        } catch (IOException e) {
//            LOGGER.error(LogBuilderUtil.getBuilder("doPost", "请求", "IOException")
//                    .appendParam("url", uri)
//                    .appendParam("header", (header == null ? null : header.toString()))
//                    .build(), e);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (Exception ex) {
                    LOGGER.error("类" + HttpClientUtil.class.getName() + "方法" + ex.getMessage());

                }
            }
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    LOGGER.error("类" + HttpClientUtil.class.getName() + "方法" + e.getMessage());
                }
            }

//            if (post != null) {
//                post.releaseConnection();
//            }
//            if (client != null) {
//                client = null;
//            }
        }
        return resultStr;
    }

    /**
     * 发送一个 Post 请求, 使用json.
     */
    public static String doPostJson(String url, String paramsJson, Map<String, String> headers) throws Exception {

        HttpClient client = null;
        HttpPost post = new HttpPost(url);
        InputStream in = null;
        try {
            if (paramsJson != null && !paramsJson.isEmpty()) {
                StringEntity se = new StringEntity(StringUtils.isBlank(paramsJson) ? "{}" : paramsJson, "UTF-8");
                se.setContentType("application/json");
                post.setEntity(se);
            }
//            headers.put("Connection", "keep-alive");
//            headers.put("Accept-Language", "zh-CN");
//            headers.put("Accept-Encoding", "gzip,deflate");
//            headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.16 Safari/537.36");
            if (headers != null && !headers.isEmpty()) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    post.addHeader(entry.getKey(), entry.getValue());
                }
            }

            // 设置参数
            RequestConfig.Builder customReqConf = RequestConfig.custom();
            customReqConf.setConnectTimeout(CONNECT_TIME_OUT);
            customReqConf.setSocketTimeout(READ_TIME_OUT);
            post.setConfig(customReqConf.build());
            HttpResponse res = null;
            if (url.startsWith("https")) {
                // 执行 Https 请求.
                client = createSSLInsecureClient();
//                LOGGER.info(LogBuilderUtil.getBuilder("doPostJson", "请求", "参数打印")
//                        .appendParam("url", url)
//                        .appendParam("header", (headers == null ? null : headers.toString())).build());
                res = client.execute(post);
            } else {
                // 执行 Http 请求.
                client = HttpClientUtil.client;
                res = client.execute(post);
            }
            in = res.getEntity().getContent();
//            return IOUtils.toString(in, "UTF-8");
        } catch (IOException e) {
//            LOGGER.error(LogBuilderUtil.getBuilder("doPostJson", "请求", "IOException")
//                    .appendParam("url", url)
//                    .appendParam("header", (headers == null ? null : headers.toString()))
//                    .build(), e);
            return "";
        } finally {
            post.releaseConnection();
            if (url.startsWith("https") && client != null && client instanceof CloseableHttpClient) {
                ((CloseableHttpClient) client).close();
            }
            if (in != null) {
                in.close();
            }
            return  null ;
        }
    }

    /**
     * 创建 SSL连接
     *
     * @return
     * @throws GeneralSecurityException
     */
    private static CloseableHttpClient createSSLInsecureClient() throws GeneralSecurityException {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {

                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }

                @Override
                public void verify(String host, SSLSocket ssl)
                        throws IOException {
                }

                @Override
                public void verify(String host, X509Certificate cert)
                        throws SSLException {
                }

                @Override
                public void verify(String host, String[] cns,
                                   String[] subjectAlts) throws SSLException {
                }

            });

            return HttpClients.custom().setSSLSocketFactory(sslsf).build();

        } catch (GeneralSecurityException e) {
            throw e;
        }
    }

    public static void main(String[] args) {

    }

}