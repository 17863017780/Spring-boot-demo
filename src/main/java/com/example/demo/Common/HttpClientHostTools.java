package com.example.demo.Common;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.thymeleaf.util.MapUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author ccjh1
 * @creat 2020/1/19
 */
public class HttpClientHostTools {
    private static final  RequestConfig  requestConfig =RequestConfig.custom()
            .setConnectTimeout(3000)   //服务器响应超时时间
            .setSocketTimeout(3000)   //连接服务器相应时间
            .build();
    /**
     * post请求传输map数据
     *
     * @param url
     * @param map
     * @param encoding
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String sendPostDataByMap(String url, Map<String, String> map, String encoding) throws ClientProtocolException, IOException {
        String result = "";
        try {
            // 创建httpclient对象

        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        // 装填参数
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        if (map != null) {
            for (Entry<String, String> entry : map.entrySet()) {
                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        // 设置参数到请求对象中
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, encoding));
        // 设置header信息
        // 指定报文头【Content-type】、【User-Agent】
        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        // 执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = httpClient.execute(httpPost);
        // 获取结果实体
        // 判断网络连接状态码是否正常(0--200都数正常)
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        }
        // 释放链接
        response.close();
        return result;
        }catch (Exception e){
            return "【Exception】#HttpClientHostTools.sendPostDataByMap=="+e.getMessage();
        }
    }
    /**
     * post请求传输json数据
     *
     * @param url
     * @param json
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String sendPostDataByJson(String url, String json) throws ClientProtocolException, IOException {
        String result = "";
        // 创建httpclient对象
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            //url转换为uri对象
            URL webUrl = new URL(url);
            URI uri = new URI(webUrl.getProtocol(), webUrl.getHost(), webUrl.getPath(), webUrl.getQuery(), null);
            // 创建post方式请求对象
            HttpPost httpPost = new HttpPost(uri);
            // 设置参数到请求对象中
            StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
            stringEntity.setContentEncoding("utf-8");
            httpPost.setEntity(stringEntity);
            // 执行请求操作，并拿到结果（同步阻塞）
            CloseableHttpResponse response = httpClient.execute(httpPost);
            // 获取结果实体
            // 判断网络连接状态码是否正常(0--200都数正常)
            Integer code =response.getStatusLine().getStatusCode();
            if (code== HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity(), "utf-8");
            }else {
                return "【Url】"+url+"responseCode=="+code;

            }
            // 释放链接
            response.close();
            return result;
        } catch (URISyntaxException e) {
            return "【Exception】#HttpClientHostTools.sendPostDataByJson=="+e.getMessage();
        }
    }
        /**
     * get请求传输数据
     *
     * @param url
     * @param params
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String sendGetData(String url, Map<String, String> params) throws ClientProtocolException, IOException {
        String result = "";
        // 创建httpclient对象
        CloseableHttpClient httpClient=null;
        HttpGet httpGet=null;
        CloseableHttpResponse response =null;
        try {
            RequestConfig globalConfig = RequestConfig.custom().
                    setConnectTimeout(5000).
                    setSocketTimeout(5000).
                    build();
            httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
            // 创建get方式请求对象
//            url = URLEncoder.encode(url, "utf-8");
            URL webUrl = new URL(url);
            URI uri = new URI(webUrl.getProtocol(), webUrl.getHost(), webUrl.getPath(), webUrl.getQuery(), null);
            httpGet = new HttpGet(uri);
           httpGet.setConfig(requestConfig);
           httpGet.addHeader("Content-type", "application/json");
           if (!MapUtils.isEmpty(params)) {
               for (String key : params.keySet()) {
                   httpGet.addHeader(key, params.get(key));
               }
           }
            // 通过请求对象获取响应对象
             response = (CloseableHttpResponse)httpClient.execute(httpGet);
            // 获取结果实体
            // 判断网络连接状态码是否正常(0--200都数正常)
            Integer code =response.getStatusLine().getStatusCode();
            if (code== HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity(), "UTF-8");
            }else {
                return "【Url】"+url+"responseCode=="+code;
            }
            // 释放链接
            response.close();
        }catch (Exception e){
            return "【Exception】#HttpClientHostTools.sendGetData=="+e.getMessage();
        }
        return result;
    }

    public static void main(String[] args){
        String url="https://api.m.jd.com/api?appid=auction-front&functionId=queryAreaItemConfigurableForM&body={\"areaId\":11501}";
        String cookie="shshshfpb=dSAnuRczaC7u3ZpzqJ9Nt8Q%3D%3D; shshshfpa=aae043a3-b36c-7959-8f2e-8a522f98c0e8-1571974159; whwswswws=; areaId=1; user-key=61ab3372-5841-4ccc-b0b9-a493e7db6352; ipLoc-djd=1-2809-51216-0; cn=1; jcap_dvzw_fp=ac32b3a84ad6a465c30cb427dffab519$637219464486; unpl=V2_ZzNtbUpXQh19DhJWfBwMVWIBEl0SVEpAdFoVVHscXQQ1AkVbclRCFnQUR1RnGFUUZwQZXUVcQxVFCEdkeBBVAWMDE1VGZxBFLV0CFSNGF1wjU00zQwBBQHcJFF0uSgwDYgcaDhFTQEJ2XBVQL0oMDDdRFAhyZ0AVRQhHZHsdWgNvARJeQVFCHHABRFN6EFwBbgoibUVncyV2DU9XexpsBFcCIh8WC0UVdQ9BXTYZWANhCxBdQVRFFHwNT1Z8GFUFYwobbUNnQA%3d%3d; __jdv=76161171|baidu-pinzhuan|t_288551095_baidupinzhuan|cpc|0f3d30c8dba7459bb52f2eb5eba8ac7d_0_910997e364aa4311a28d0cb11400c0f7|1578471372010; 3AB9D23F7A4B3C9B=DKKE4YGKMB2UMLITZCTYZPNT7IJGZC7PY34VAPYT2WDQIFIXXNIIYGYBD2SIPSKZES6SIZRHVQXNBC6V3VYJIHJP54; jd.erp.lang=zh_CN; mba_muid=1577931227084836081588; wxa_level=1; language=zh_CN; __jdu=1577931227084836081588; TrackID=1exDokGmi0bDi8suXl5R7Z7DCKj1gepmVJAk44Hx3gAjnkL204qNmsoBplSZ5SIG4hN2cjsgXeRYRRrKsVYLZ_B-KK_5UZZ1OwWfClEmxE_o; pinId=pVvgI0wl9Kji44gGM6yUiLKS7MJkIcs6; pin=test_pop_paimaizc; unick=test_pop_paimaizc; ceshi3.com=000; _tp=TMY%2F%2F%2BzQUino2S%2Bvtqst7YIoef8xs5rNnTKeBwMGMBw%3D; _pst=test_pop_paimaizc; _base_=YKH2KDFHMOZBLCUV7NSRBWQUJPBI7JIMU5R3EFJ5UDHJ5LCU7R2NILKK5UJ6GLA2RGYT464UKXAI4Z6HPCTN4UQM3WHVQ4ENFP57OCZBGEA3FSJAGDRIDTKGBFHOJ7HZCQAVH6KGSNCO5NUZKVBDP5EJXNZELA6E4S4L2GLWBLTAIW5N6ZGEONMNNA5DQRDPVL52KNRE2QP7OQX7UDXBJQAN7MG4MEKLVTSGRNB3AY2NNXFWQMKMVLAV4RKQX7K6I4LBSJEVTDOGTZY6LSTOJKBSZMBDTGHQHRURVFLV7KB25DDROY4M5YBMIZUOK27PLHAJP3JGI6EXH27VZWR3BET7NBBQ4O2U2XKPNWEWARVOC2SKY5BQ; RT=\"z=1&dm=jd.com&si=nl4mqtr159i&ss=k56bluc1&sl=1&tt=1li&ul=1hg9r&ld=2nd&hd=1hg9t\"; autoOpenApp_downCloseDate_auto=1578552361979_21600000; shshshfp=05bed35649ddefdfc5c1a165316bd452; __jd_ref_cls=cebianlan_h%7Cload; erp1.jd.com=C7A6FCB071E8E566AE18BCFA02E743105FB08266B5F55C93218B3B186DF8D669E0A2B0888AEB13B16FF22A39078A4D5AE09F7E11E19914BB4FD3DC4B3F22B01090EA019B6C7C0636BEC4C80F6F31F0C0; sso.jd.com=BJ.bb4d0f64b1124c8ba47acca62cf74a16; __jda=122270672.1577931227084836081588.1577931227.1578572558.1578619493.28; __jdb=122270672.2.1577931227084836081588|28.1578619493; __jdc=122270672; thor=36B4BE899EB115DE82E4DA9FACCE714D898F32E639D6D0285E1C7C5B38016A63B8608126874F61C577BEF2AB10E9C85A99ED4FA28EBA329F419148B6A15A2130952C4616D8B45ADF0BABEF727DAF81A047E8B2DD236629DA309C371DB067330033301FC9DB204FC3CBB490484A0CA009D1DFFB5D2DC4437D55F74C4B7D991B9A21401737FAED55B5D0C4D6EF1E6899E0DD6A92D368E8A30C1184B0631F54B61F";
        String referer="https://paimai.jd.com/112411815";
        Map<String,String> a =new HashMap<>();
        a.put("Referer",referer);
        a.put("Cookie",cookie);
        try {
            System.out.println(sendGetData(url,a));
//            System.out.println(HostTools.responseText(url,"get",a));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
