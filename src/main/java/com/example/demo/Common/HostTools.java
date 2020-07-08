package com.example.demo.Common;

import org.thymeleaf.util.MapUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author ccjh1
 * @creat 2020/1/9
 */
public class HostTools {

    private static final int DEFAULT_TIMEOUT = 50000; //默认httpclient超时时间
    private final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

    public static HttpURLConnection getHttpURLConn(String queryURL, String host, String ip) throws Exception{
        HttpURLConnection httpUrlConn = null;
        URL url = new URL(queryURL);
        if(ip != null){
            String[] str = ip.split("\\.");
            byte[] b = new byte[str.length];
            for(int i=0; i<str.length; i++){
                b[i] = (byte)Integer.parseInt(str[i],10);
            }
            /**
             * 设置代理
             */
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(InetAddress.getByAddress(b), 80));
            httpUrlConn = (HttpURLConnection)url.openConnection(proxy);
        }else{
            httpUrlConn = (HttpURLConnection)url.openConnection();
        }
        return httpUrlConn;
    }

    public static String responseText(String webUrl,String requestMethod,Map<String,String> params){
        try {
            FutureTask<String> task=new FutureTask<String>((Callable<String>)()-> {
                if (requestMethod.equals("post")) {
                    return postResponseText(webUrl, params);
                }
                else {
                    return getResponseText(webUrl, params);
                }
            });
            Thread t1=new Thread(task,"");
            t1.start();
            return task.get();
        }catch (Exception e){
            return "【Exception】#HostTools.responseText=="+e.getMessage();
        }
    }

    public static String getResponseText(String webUrl,Map<String,String> params) throws Exception {
        InputStream is = null;
        String result ="";
        URL url = new URL(webUrl);
        HttpURLConnection conn = null;
        // 通过请求地址判断请求类型(http或者是https)
        if (url.getProtocol().toLowerCase().equals("https")) {
            HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
            //访问https链接忽略证书
            https.setHostnameVerifier(DO_NOT_VERIFY);
            conn = https;
        } else {
            conn = (HttpURLConnection) url.openConnection();
        }
        // 设置通用的请求属性
        conn.setConnectTimeout(DEFAULT_TIMEOUT);
        conn.setReadTimeout(DEFAULT_TIMEOUT);
        //设置对于当前的URL是否使用缓存
        conn.setDefaultUseCaches(false);
        conn.setUseCaches(false);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("user-agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        conn.setRequestProperty("Content-Type", "text/plain;charset=utf-8");
        try {
            if (!MapUtils.isEmpty(params)){
                for(String key:params.keySet()){
                    conn.setRequestProperty(key,params.get(key));
                }
            }
            conn.connect();
            is=conn.getInputStream();
            int responseCode = conn.getResponseCode();
            if(responseCode > 400){
                return "#failed: getResonseText from Url: " + url + " got responseCode: " + responseCode;
            }
            byte[] data = toByteArray(is);
            result = new String(data, "utf-8");
        }catch (Exception e){
            System.out.println("【Exception】#HostTools.getResponseText=="+e.getMessage());
            return "【Exception】#HostTools.getResponseText=="+e.getMessage();
        }
        return  result;
    }

    public static String postResponseText(String webUrl,Map<String,String> params) throws Exception {
        InputStream is = null;
        String result ="";
        String body="";
        URL url = new URL(webUrl);
        HttpURLConnection conn = null;
        // 通过请求地址判断请求类型(http或者是https)
        if (url.getProtocol().toLowerCase().equals("https")) {
            HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
            https.setHostnameVerifier(DO_NOT_VERIFY);
            conn = https;
        } else {
            conn = (HttpURLConnection) url.openConnection();
        }
        // 设置通用的请求属性
        conn.setConnectTimeout(DEFAULT_TIMEOUT);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setReadTimeout(DEFAULT_TIMEOUT);
        conn.setDoOutput(true); //设置是否向httpUrlConnection输出，默认情况下是false
        conn.setDoInput(true); // 设置是否从httpUrlConnection读入，默认情况下是true;
        conn.setDefaultUseCaches(false);
        //设置是否使用缓存
        conn.setUseCaches(false);
        try {
            if (!MapUtils.isEmpty(params)){
                //填入请求头
                for(String key:params.keySet()){
                    if (key.equals("body")||key.equals("Body")){
                        body=params.get(key);
                    }else {
                        conn.setRequestProperty(key, params.get(key));
                    }
                }
            }
            conn.connect();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
            writer.write(body);
            writer.close();
            is=conn.getInputStream();
            int responseCode = conn.getResponseCode();
            if(responseCode > 400){
                return "#failed: postResponseText from Url: " + url + " got responseCode: " + responseCode;
            }
            byte[] data = toByteArray(is);
            result = new String(data, "utf-8");
        }catch (Exception e){
            System.out.println("【Exception】#HostTools.postResponseText=="+e.getMessage());
            return "【Exception】#HostTools.postResponseText=="+e.getMessage();
        }
        return  result;
    }


    private static byte[] toByteArray(InputStream input){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n = 0;
        try {
            while (-1 != (n = input.read(buffer))) {
                output.write(buffer, 0, n);
            }
        }catch (IOException e){
            System.out.println("【IOException】#HostTools.toByteArray=="+e.getMessage());
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
    }
    
    
    public static void main(String[] args){
        runTime();
    }

    public static void runTime(){
        long startTime=System.currentTimeMillis();   //获取开始时间
            runTest(2);
        long endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间"+(endTime-startTime)+"ms");
    }
    
    public static void runTest(int i){
        String url="https://api.m.jd.com/api?appid=auction-front&functionId=queryAreaItemConfigurableForM&body={%22areaId%22:11501}";
        String cookie="shshshfpb=dSAnuRczaC7u3ZpzqJ9Nt8Q%3D%3D; shshshfpa=aae043a3-b36c-7959-8f2e-8a522f98c0e8-1571974159; whwswswws=; areaId=1; user-key=61ab3372-5841-4ccc-b0b9-a493e7db6352; ipLoc-djd=1-2809-51216-0; cn=1; jcap_dvzw_fp=ac32b3a84ad6a465c30cb427dffab519$637219464486; unpl=V2_ZzNtbUpXQh19DhJWfBwMVWIBEl0SVEpAdFoVVHscXQQ1AkVbclRCFnQUR1RnGFUUZwQZXUVcQxVFCEdkeBBVAWMDE1VGZxBFLV0CFSNGF1wjU00zQwBBQHcJFF0uSgwDYgcaDhFTQEJ2XBVQL0oMDDdRFAhyZ0AVRQhHZHsdWgNvARJeQVFCHHABRFN6EFwBbgoibUVncyV2DU9XexpsBFcCIh8WC0UVdQ9BXTYZWANhCxBdQVRFFHwNT1Z8GFUFYwobbUNnQA%3d%3d; __jdv=76161171|baidu-pinzhuan|t_288551095_baidupinzhuan|cpc|0f3d30c8dba7459bb52f2eb5eba8ac7d_0_910997e364aa4311a28d0cb11400c0f7|1578471372010; 3AB9D23F7A4B3C9B=DKKE4YGKMB2UMLITZCTYZPNT7IJGZC7PY34VAPYT2WDQIFIXXNIIYGYBD2SIPSKZES6SIZRHVQXNBC6V3VYJIHJP54; jd.erp.lang=zh_CN; mba_muid=1577931227084836081588; wxa_level=1; language=zh_CN; __jdu=1577931227084836081588; TrackID=1exDokGmi0bDi8suXl5R7Z7DCKj1gepmVJAk44Hx3gAjnkL204qNmsoBplSZ5SIG4hN2cjsgXeRYRRrKsVYLZ_B-KK_5UZZ1OwWfClEmxE_o; pinId=pVvgI0wl9Kji44gGM6yUiLKS7MJkIcs6; pin=test_pop_paimaizc; unick=test_pop_paimaizc; ceshi3.com=000; _tp=TMY%2F%2F%2BzQUino2S%2Bvtqst7YIoef8xs5rNnTKeBwMGMBw%3D; _pst=test_pop_paimaizc; _base_=YKH2KDFHMOZBLCUV7NSRBWQUJPBI7JIMU5R3EFJ5UDHJ5LCU7R2NILKK5UJ6GLA2RGYT464UKXAI4Z6HPCTN4UQM3WHVQ4ENFP57OCZBGEA3FSJAGDRIDTKGBFHOJ7HZCQAVH6KGSNCO5NUZKVBDP5EJXNZELA6E4S4L2GLWBLTAIW5N6ZGEONMNNA5DQRDPVL52KNRE2QP7OQX7UDXBJQAN7MG4MEKLVTSGRNB3AY2NNXFWQMKMVLAV4RKQX7K6I4LBSJEVTDOGTZY6LSTOJKBSZMBDTGHQHRURVFLV7KB25DDROY4M5YBMIZUOK27PLHAJP3JGI6EXH27VZWR3BET7NBBQ4O2U2XKPNWEWARVOC2SKY5BQ; RT=\"z=1&dm=jd.com&si=nl4mqtr159i&ss=k56bluc1&sl=1&tt=1li&ul=1hg9r&ld=2nd&hd=1hg9t\"; autoOpenApp_downCloseDate_auto=1578552361979_21600000; shshshfp=05bed35649ddefdfc5c1a165316bd452; __jd_ref_cls=cebianlan_h%7Cload; erp1.jd.com=C7A6FCB071E8E566AE18BCFA02E743105FB08266B5F55C93218B3B186DF8D669E0A2B0888AEB13B16FF22A39078A4D5AE09F7E11E19914BB4FD3DC4B3F22B01090EA019B6C7C0636BEC4C80F6F31F0C0; sso.jd.com=BJ.bb4d0f64b1124c8ba47acca62cf74a16; __jda=122270672.1577931227084836081588.1577931227.1578572558.1578619493.28; __jdb=122270672.2.1577931227084836081588|28.1578619493; __jdc=122270672; thor=36B4BE899EB115DE82E4DA9FACCE714D898F32E639D6D0285E1C7C5B38016A63B8608126874F61C577BEF2AB10E9C85A99ED4FA28EBA329F419148B6A15A2130952C4616D8B45ADF0BABEF727DAF81A047E8B2DD236629DA309C371DB067330033301FC9DB204FC3CBB490484A0CA009D1DFFB5D2DC4437D55F74C4B7D991B9A21401737FAED55B5D0C4D6EF1E6899E0DD6A92D368E8A30C1184B0631F54B61F";
        String referer="https://paimai.jd.com/112411815";
        Map<String,String> a =new HashMap<>();
        a.put("Referer",referer);
        a.put("Cookie",cookie);
        try {
            for (int j=0;j<10;j++){
                if (i==1){
                    responseText(url,"get",a);
                }
                if (i==2){
                    getResponseText(url,a);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
