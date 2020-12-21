package com.example.demo.Common.utils;
//
//import cn.gjing.http.HttpClient;
import cn.gjing.http.HttpMethod;
import cn.gjing.http.HttpClient;
import com.alibaba.fastjson.JSONObject;
//import org.openqa.selenium.remote.http.HttpMethod;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class HttpUtils {

    public static void main(String[] args) {
        String startTime = "2020-11-11 16:00:00";
        String endTime = "2020-11-11 22:00:00";
        String cookie = "language=zh_CN; __jdv=95931165|direct|-|none|-|1605078717348; __jdu=16050787173421286259664; 3AB9D23F7A4B3C9B=DKKE4YGKMB2UMLITZCTYZPNT7IJGZC7PY34VAPYT2WDQIFIXXNIIYGYBD2SIPSKZES6SIZRHVQXNBC6V3VYJIHJP54; TrackID=1FeAtXbQviiIMFl4bXC5kg4PS1Q5wpnPKhFYxau9wa-TkDp9tY-bNfmWBcFeZAobrRWIh0REoaWSEQJYF-1QK3LVaOOIdOUmmmd38YrMKAGo; pinId=pVvgI0wl9KjiWlelGPVRBLV9-x-f3wj7; pin=test_pop_paimai1; unick=%E6%B5%8B%E8%AF%95%E5%95%86%E5%AE%B61; ceshi3.com=000; _tp=4yIXobdhuXVqsjkFx0P2iMASYOkeh8xNZG8nsLU6vrc%3D; _pst=test_pop_paimai1; _BELONG_CLIENT_=WPSC4XJXWK5USS4JNZY2X7VRLR5MCBKRSVHEXABGTHDGISIQK5YOLZUXYE7IOIM7MOKO74H6CRN6WHAAR4TMDV3XZWMXZRCRT5XRNE3V356BTOB2Y7LPK66VWQK6HPTGWVXIDXDCPVE3W5WMHAIO6AT2LX2XXVNUCXR34ZWFK6HY45CORGIKOSYDYZBF27WOKTUX6BS4FZMIJWNUX6CB4JAA25ZLF7ZEKYOO4QV5HTSBXGNRM3E242MBI6V5D4C5VJDQ3EOYCOW5BMTUJZACIBHXQFAVLRF76VQY5PNJGGJNBEZHSFYYJA3YORRT7FB5AHCOIFQKF3W5RWNUX6CB4JAA26JNMO7AYWNUPZF5HTSBXGNRM3E242MBI6V5D4C5VJDQ3EOYCOW5BWZDKMOJ5BS6II53ERY6ALV3ZWPF42L4CPUHEGPYIII35KDC4FCNVCORCXFD6IVNLBEDPB2GGP4UHWNRUDOQBDIW7RZJXBA2WV5ANZOTEGUCDWYRVQS2YUTIZNZ276PRYG4N56V6YTII7MBKBC7LYHO7C555HTSBXGNRM3E466AYN67DHWVM5HQFJ4NFDO5BS5WPDRIY4YIBTIMAAYOR5OOXALQ; b-sec=TKHUGSSW2BNIBAFPUWCHWBLNP6DQAR5RQ6B5LRYUXZYA43YPJMA7EAFBR7TVHUJIK4X5MSJZEHZIQ; __jda=29107517.16050787173421286259664.1605078717.1605078717.1605078717.1; __jdb=29107517.4.16050787173421286259664|1.1605078717; __jdc=29107517; _vender_new_=GI63BGTJFDBQ4ZYXZGVI52733ZIR2J55JRRKCFW54APLAACXLVQ75HEDDXCPGKXUKENQPPV3VW2WHGHJLDJTXGZWX3VANSQWGCZ6NW5Q3PD3ZGYEASLJQJJM2EBHK2DI2GHPQYHTYDMM354RJA5WHIDP47QVWKB2HKGFMVPSG7EAQ7LLJI6NQS7LR6FFFCMF333X5RLOEFIQHB4VLXML7BSFQRONXA6RMV7A3W6RQHU63BU24NL7HVMIOKJYEN4VFBEQFBKSTIUNGR4KLEN47XBDTVI2QLM73HVPBBMOI4H56OBL6RHDJJANAN45TKCGASNB6PRQLETFCKWYQINMCORXME2KIDIDPHM2QRQETIPT4MCZEZIZZY63ACBHVZA3IWQ2V5Z7IROBWJNXB4XLXKIO5HXLSDKOHAD4AOTRKHEBBLTHQD6QXZO3VXEQXFLVNE3NSPYQ4FSQPA3ILE45O7DOJPKQUJKRIB5WRZ5K7OSTMJGUD73QUXWDCH6C27OFBR5KUE2QPIFNEUWQ3HLTE4YZOTYNVH7FBA6Z4SUM7CYLVWED7YFOEIWWU6O2AY4ARJW4LMPJEHZT7QC3YGZPM6KFTHI4LTIB4N4RT3INUXCRTADP6VCVDRS4JXTZNLYQ; thor=935FBA7C2CBBA0CC0BFB4218D06515C7D13948E2E1FE4F74580E1C256167763DB90EAF17EFBFC61BE8F57AE3E5C024300A7ACCF9473646C3CCDBE824BE2EB57D9D061F7C8E981635EE5AB3F7A7AAB821A5223EAF7172C740D5A02BFDABD316DC0B3301360A987F0EA9972BB89A1C14A66052A893E3AEE15ACA02C11649950E73D5C4EA55637BAC13439AD3DAC7A3BF57488E936843F1CEFDC505C132F14C94E3; _base_=YKH2KDFHMOZBLCUV7NSRBWQUJPBI7JIMU5R3EFJ5UDHJ5LCU7R2NILKK5UJ6GLA2RGYT464UKXAI4Z6HPCTN4UQM3WHVQ4ENFP57OC64HEPQIANNMJPZ5CL7PFAE42P4SCF555UOYV4EADZJ5A77M4CWNNZELA6E4S4L2GLWBLTAIW5N6ZGEONMNNA5DQRDPVL52KNRE2QP7PNCDJAGH7WWXI4G4MEKLVTSGRNB3AY2NNXFWQMKNCRLT5NYD24YVLARKH46PYMN45BL6RPE575JRRF7FRWSCISIZTBJBGOSEMO4EIQF35XT3JRURFSU52YAIELLNZNMASAKGXBBFJ2MUY6O4A3GKJDAGR5UWARVOC2SKY5BQ";
        for (int i = 10; i <= 12; i++) {
//            String  time =Calendar.getInstance().toString();
            System.out.println("===========第" + i + "次执行=============");
            doPost("测试拍品专场用" + i,startTime,endTime,cookie);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("=====================================");
        }
        for (int i = 3; i <= 3; i++) {
            System.out.println("===========第" + i + "次执行=============");
            doPost3("测试专场用" + i,startTime,endTime,cookie);
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("=====================================");
        }

    }

    public static void doPost(String name, String start, String end, String cookie) {
        SaveToWareHouseAndPublishReqDto saveToWareHouseAndPublishReqDto = new SaveToWareHouseAndPublishReqDto();
        saveToWareHouseAndPublishReqDto.setPlatformSkuId("");
        saveToWareHouseAndPublishReqDto.setSkuDetailId("");
        saveToWareHouseAndPublishReqDto.setSkuName(name);

        List list1 = new ArrayList<String>();
        AttributFristObj attributFristObj1 = new AttributFristObj();
        attributFristObj1.setAttrId(110063);
        attributFristObj1.setIndex(1);
        attributFristObj1.setLevel(0);
        attributFristObj1.setInputType(1);

        AttributFristObj attributFristObj2 = new AttributFristObj();
        attributFristObj2.setAttrId(110064);
        attributFristObj2.setIndex(2);
        attributFristObj2.setLevel(0);
        attributFristObj2.setInputType(1);

        AttributFristObj attributFristObj3 = new AttributFristObj();
        attributFristObj3.setAttrId(110065);
        attributFristObj3.setIndex(3);
        attributFristObj3.setLevel(0);
        attributFristObj3.setInputType(1);

        AttributFristObj attributFristObj4 = new AttributFristObj();
        attributFristObj4.setAttrId(110066);
        attributFristObj4.setIndex(4);
        attributFristObj4.setLevel(0);
        attributFristObj4.setInputType(1);

        AttributFristObj attributFristObj5 = new AttributFristObj();
        attributFristObj5.setAttrId(110068);
        attributFristObj5.setIndex(5);
        attributFristObj5.setLevel(0);
        attributFristObj5.setInputType(1);

//        list1.add(attributFristObj5);
//        list1.add(attributFristObj4);
//        list1.add(attributFristObj3);
//        list1.add(attributFristObj2);
//        list1.add(attributFristObj1);

        saveToWareHouseAndPublishReqDto.setAttributFristObj(list1);
        saveToWareHouseAndPublishReqDto.setAuctionType(1);
        saveToWareHouseAndPublishReqDto.setBail(2);
        saveToWareHouseAndPublishReqDto.setBusinessCode("gemCycleIncr");
        saveToWareHouseAndPublishReqDto.setCategoryId(12723);
        saveToWareHouseAndPublishReqDto.setCycleType(1);
        saveToWareHouseAndPublishReqDto.setDelayPeriod(0);
        saveToWareHouseAndPublishReqDto.setDescription("<p>测试商品</p>");
        saveToWareHouseAndPublishReqDto.setDurationTime("");
        saveToWareHouseAndPublishReqDto.setStartTime(start);
        saveToWareHouseAndPublishReqDto.setEndTime(end);
        saveToWareHouseAndPublishReqDto.setInitialPrice("1.00");
        saveToWareHouseAndPublishReqDto.setMainImg("jfs/t1/155440/24/545/15086/5f6a0524E622d91c7/95b6f8afc4d9b5dc.jpg");
        saveToWareHouseAndPublishReqDto.setStockNum("1");
        PropertiesJson propertiesJson = new PropertiesJson();
        propertiesJson.setPriceLowerOffset("1");
        saveToWareHouseAndPublishReqDto.setPropertiesJson(propertiesJson);
        List list2 = new ArrayList<String>();
        list2.add("jfs/t1/155440/24/545/15086/5f6a0524E622d91c7/95b6f8afc4d9b5dc.jpg");
        saveToWareHouseAndPublishReqDto.setWareImgs(list2);
        List list3 = new ArrayList<String>();
        saveToWareHouseAndPublishReqDto.setPropStr(list3);

        saveToWareHouseAndPublishReqDto.setDelayPeriod(5);
        String requestJson = JSONObject.toJSONString(saveToWareHouseAndPublishReqDto);
        System.out.println(requestJson);
        Map header = new HashMap();
        header.put("referer", "https://service.vapp.jd.com/2275960CFB7D63671A7FEDF96C380602/1/page-frame.html");
        header.put("Content-Type", "application/json");
        header.put("Accept", " */*");
        header.put("Accept-Encoding", "gzip");
        header.put("Cookie", cookie);
        header.put("Connection", "keep-alive");
        header.put("Content-Length", "917");
        header.put("User-Agent", "JMApp/5.5.0 (iPhone; iOS 12.4.1; Scale/2.00)");
        header.put("Accept-Language", "zh-Hans;q=1");
        header.put("X-MLAAS-AT", "wl=0");
        header.put("Host", "horus.jd.com");
        header.put("Charset", "utf-8");
        String url = "https://horus.jd.com/platform/gem/skuDetail/jAppletSaveToWareHouseAndPublish.action";
        HttpClient.builder(url, HttpMethod.POST, Map.class)
                .header(header)
                .body(requestJson)
                .connectTimeout(7000)
                .execute()
                .listener(System.out::print);
    }

    //0元起
    public static void doPostNewP(String name, String start, String end, String cookie) {
        SaveToWareHouseAndPublishReqDto saveToWareHouseAndPublishReqDto = new SaveToWareHouseAndPublishReqDto();
        saveToWareHouseAndPublishReqDto.setPlatformSkuId("");
        saveToWareHouseAndPublishReqDto.setSkuDetailId("");
        saveToWareHouseAndPublishReqDto.setSkuName(name);

        List list1 = new ArrayList<String>();

        saveToWareHouseAndPublishReqDto.setAttributFristObj(list1);
        saveToWareHouseAndPublishReqDto.setAuctionType(1);
        saveToWareHouseAndPublishReqDto.setBail(0);
        saveToWareHouseAndPublishReqDto.setBusinessCode("gemCycleIncr");
        saveToWareHouseAndPublishReqDto.setCategoryId(13135);
        saveToWareHouseAndPublishReqDto.setCycleType(1);
        saveToWareHouseAndPublishReqDto.setDelayPeriod(0);
        saveToWareHouseAndPublishReqDto.setDescription("<p>测试商品</p>");
        saveToWareHouseAndPublishReqDto.setDurationTime("");
        saveToWareHouseAndPublishReqDto.setStartTime(start);
        saveToWareHouseAndPublishReqDto.setEndTime(end);
        saveToWareHouseAndPublishReqDto.setInitialPrice("0.00");
        saveToWareHouseAndPublishReqDto.setMainImg("jfs/t1/154615/3/1037/44629/5f72a637E0920b908/30604c14fddef714.jpg");
        saveToWareHouseAndPublishReqDto.setStockNum("1");
        PropertiesJson propertiesJson = new PropertiesJson();
        propertiesJson.setPriceLowerOffset("1");
        saveToWareHouseAndPublishReqDto.setPropertiesJson(propertiesJson);
        List list2 = new ArrayList<String>();
        list2.add("jfs/t1/155440/24/545/15086/5f6a0524E622d91c7/95b6f8afc4d9b5dc.jpg");
        saveToWareHouseAndPublishReqDto.setWareImgs(list2);
        List list3 = new ArrayList<String>();
        saveToWareHouseAndPublishReqDto.setPropStr(list3);

        saveToWareHouseAndPublishReqDto.setDelayPeriod(0);
        String requestJson = JSONObject.toJSONString(saveToWareHouseAndPublishReqDto);
        System.out.println(requestJson);
        Map header = new HashMap();
        header.put("referer", "https://service.vapp.jd.com/2275960CFB7D63671A7FEDF96C380602/1/page-frame.html");
        header.put("Content-Type", "application/json");
        header.put("Accept", " */*");
        header.put("Accept-Encoding", "gzip");
        header.put("Cookie", cookie);
        header.put("Connection", "keep-alive");
        header.put("Content-Length", "917");
        header.put("User-Agent", "JMApp/5.5.0 (iPhone; iOS 12.4.1; Scale/2.00)");
        header.put("Accept-Language", "zh-Hans;q=1");
        header.put("X-MLAAS-AT", "wl=0");
        header.put("Host", "horus.jd.com");
        header.put("Charset", "utf-8");
        String url = "https://horus.jd.com/platform/gem/skuDetail/jAppletSaveToWareHouseAndPublish.action";
        HttpClient.builder(url, HttpMethod.POST, Map.class)
                .header(header)
                .body(requestJson)
                .connectTimeout(7000)
                .execute()
                .listener(System.out::print);
    }

    public static void doPost2(String name, String cookie) {
        SaveToWareHouseAndPublishReqDto saveToWareHouseAndPublishReqDto = new SaveToWareHouseAndPublishReqDto();
        saveToWareHouseAndPublishReqDto.setPlatformSkuId("");
        saveToWareHouseAndPublishReqDto.setSkuDetailId("");
        saveToWareHouseAndPublishReqDto.setCategoryId(12723);
        saveToWareHouseAndPublishReqDto.setSkuName(name);
        saveToWareHouseAndPublishReqDto.setStockNum("10");
//        AttributFristObj attributFristObj = new AttributFristObj();
//        attributFristObj.setAttrId(110105);
//        attributFristObj.setAttrValues("");
//        attributFristObj.setIndex(1);
//        attributFristObj.setLevel(0);
//        attributFristObj.setInputType(1);
        List list1 = new ArrayList<String>();
//        attributFristObj.setAttrValuesName(list1);
        saveToWareHouseAndPublishReqDto.setAttributFristObj(list1);
        saveToWareHouseAndPublishReqDto.setBail(0);
        saveToWareHouseAndPublishReqDto.setMainImg("jfs/t1/125788/7/12694/13509/5f602b88Ec87169a3/05f2bece0c9d5d8d.jpg");
        List list2 = new ArrayList<String>();
        list2.add("jfs/t1/125788/7/12694/13509/5f602b88Ec87169a3/05f2bece0c9d5d8d.jpg");
        saveToWareHouseAndPublishReqDto.setWareImgs(list2);
        saveToWareHouseAndPublishReqDto.setDescription("测试商品");
        List list3 = new ArrayList<String>();
        saveToWareHouseAndPublishReqDto.setPropStr(list3);
        saveToWareHouseAndPublishReqDto.setAuctionType(6);
        saveToWareHouseAndPublishReqDto.setInitialPrice("0.01");
        saveToWareHouseAndPublishReqDto.setDelayPeriod(0);
        saveToWareHouseAndPublishReqDto.setCycleType(1);
        saveToWareHouseAndPublishReqDto.setStartTime("");
        saveToWareHouseAndPublishReqDto.setEndTime("");
        saveToWareHouseAndPublishReqDto.setBusinessCode("exclusiveForNew");
        PropertiesJson propertiesJson = new PropertiesJson();
//        propertiesJson.setServiceSupport("");
        saveToWareHouseAndPublishReqDto.setPropertiesJson(propertiesJson);
        saveToWareHouseAndPublishReqDto.setDurationTime("");
        saveToWareHouseAndPublishReqDto.setReservedPrice("");
        saveToWareHouseAndPublishReqDto.setJdPrice("");

        String requestJson = JSONObject.toJSONString(saveToWareHouseAndPublishReqDto);
        Map header = new HashMap();
        header.put("referer", "https://service.vapp.jd.com/2275960CFB7D63671A7FEDF96C380602/1/page-frame.html");
        header.put("Content-Type", "application/json");
        header.put("Accept", " */*");
        header.put("Accept-Encoding", "gzip");
//        header.put("Cookie", "_pst=test_pop_paimai1; thor=935FBA7C2CBBA0CC0BFB4218D06515C7D13948E2E1FE4F74580E1C256167763D1DBD38ADCDB19E2A9FF56A71F373841BFBE0B7CFFF9E947F8C824C6CF03E3DABB2C47D22B0C2F8169408ECB7E72BA7F86523A0BB972B5D010B44AF5D49D99008710FA7F23185ECF8831EE81BEAEC6BCE7938DDB5158401AAE79CE4D328D32CFFF41CF86A007347C85ED39F7C136BFB50679812D1815B6731F57725F1DDB0A3D8; pin=test_pop_paimai1; unick=%E6%B5%8B%E8%AF%95%E5%95%86%E5%AE%B61; _tp=4yIXobdhuXVqsjkFx0P2iMASYOkeh8xNZG8nsLU6vrc%3D; pinId=pVvgI0wl9KjiWlelGPVRBLV9-x-f3wj7; TrackID=1UswVh_JAEDEANowXuVCss4-tfdGiMbVCo9kDUwB78K45MlwMuSspfCGmPONt0kX2; logining=1; ceshi3.com=000; b-sec=TKHUGSSW2BNIBAFPUWCHWBLNP7S2SSORKPGDHVCA63IABEGKTCURSJX5U5RPHTP7AITIB4LAMCYQHD6NRWBX675VTE; b_belong=THRW7N5ZDBQJRINKOCMRSBCJP6HJVJTG6SL37S7NMJ7R4YDZVU2HPX4LBBE5SWR3RY5U4AVVYG7HY5U77MZZ35RRMBZKZY3MVQFSF6AZZ6D26LUKVGLT6I77TZCXVZYPOHKZSFBO4Q3VZQQSSD7ZDG3NRU");
        header.put("Cookie", cookie);
        header.put("Connection", "keep-alive");
        header.put("Content-Length", "917");
        header.put("User-Agent", "JMApp/5.5.0 (iPhone; iOS 12.4.1; Scale/2.00)");
        header.put("Accept-Language", "zh-Hans;q=1");
        header.put("X-MLAAS-AT", "wl=0");
        header.put("Host", "horus.jd.com");
        header.put("Charset", "utf-8");
        String url = "https://horus.jd.com/platform/gem/skuDetail/jAppletSaveToWareHouseAndPublish.action";
        HttpClient.builder(url, HttpMethod.POST, Map.class)
                .header(header)
                .body(requestJson)
                .connectTimeout(7000)
                .execute()
                .listener(System.out::print);
    }

    public static void doPost3(String name, String start, String end, String cookie) {
        SaveToCustReqDto saveToCustReqDto = new SaveToCustReqDto();
        saveToCustReqDto.setId("");
        saveToCustReqDto.setActivityName(name);
        saveToCustReqDto.setActivityBeginTime(start);
        saveToCustReqDto.setActivityEndTime(end);
        saveToCustReqDto.setTwoCategoryId(12673);
        saveToCustReqDto.setImgs("jfs/t1/136027/19/11509/207593/5f741d6eE9e75e7ed/f73a78ce0371cd97.jpg");

        String requestJson = JSONObject.toJSONString(saveToCustReqDto);
        Map header = new HashMap();
        header.put("referer", "https://service.vapp.jd.com/2275960CFB7D63671A7FEDF96C380602/1/page-frame.html");
        header.put("Content-Type", "application/json");
        header.put("Accept", " */*");
        header.put("Accept-Encoding", "gzip");
        header.put("Cookie", cookie);
        header.put("Connection", "keep-alive");
        header.put("Content-Length", "917");
        header.put("User-Agent", "JMApp/5.5.0 (iPhone; iOS 12.4.1; Scale/2.00)");
        header.put("Accept-Language", "zh-Hans;q=1");
        header.put("X-MLAAS-AT", "wl=0");
        header.put("Host", "horus.jd.com");
        header.put("Charset", "utf-8");
        String url = "https://horus.jd.com/platform/gem/activity/jAppletSaveCust.action";
        HttpClient.builder(url, HttpMethod.POST, Map.class)
                .header(header)
                .body(requestJson)
                .connectTimeout(7000)
                .execute()
                .listener(System.out::print);
    }

//    public static void main(String[] args) {
//
//        for (int i = 0; i < 200; i++) {
//            System.out.println("zzx_test_" + i);
//        }
//        List<Integer> list2 = new ArrayList<Integer>();
//        for(int i = 0 ;i<lit.size();i++){
//            list2.add(lit.get(i));
//            if(list2.size() == 10){
//                for(int k = 0 ;k<list2.size();k++){
//                    if((k+1)==list2.size()){
//                        System.out.print(list2.get(k));
//                    }else{
//                        System.out.print(list2.get(k)+",");
//                    }
//                }
//                list2.clear();
//                System.out.println();
//            }
//        }
//        for(int i = 0; i < list.size(); i++){
//            Xia xia = JSONObject.parseObject(list.get(i).toString(), Xia.class);
//            System.out.println(xia.get_id() + "   " + xia.get_source().getVendorName());
//        }
//        String url = "https://beta-api.m.jd.com//api?appid=paimai&functionId=queryNewComerHotRecommendProductsForM&body={}";
//        int i = 0;
//        while (true){
//            HttpClient<HashMap> result = HttpClient.<HashMap>builder(url, HttpMethod.GET, HashMap.class)
//                    .connectTimeout(7000)
//                    .execute();
//            HashMap map = result.get();
//            List data = (List) map.get("data");
//            System.out.println(data.size());
//            if(data.size() != 101){
//                System.out.println("============>>>>>" + data.size());
//            }
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        ArrayList<String> list = new ArrayList<String>();
//        list.add("a");
//        list.add("b");
//        list.add("c");
//        list.add("d");
//        Iterator<String> iterator = list.iterator();
//
//        while (iterator.hasNext()){
//            String string  = iterator.next();
//            if(string.equals("a")){
//                iterator.remove();
//            }
//            System.out.println(string);
//        }
//        System.out.println(list.toString());
//        Map header = new HashMap();
//        header.put("referer", "http://auction.shop.jd.com/paimai/bankruptcy/toBankruptcyVm.do");
//        header.put("Content-Type", "application/json");
//        header.put("Accept", " */*");
//        header.put("Accept-Encoding", "gzip");
//        header.put("Cookie", "language=zh_CN; __jdv=95931165|direct|-|none|-|1603250295300; pinId=pVvgI0wl9KjiWlelGPVRBLV9-x-f3wj7; pin=test_pop_paimai1; unick=%E6%B5%8B%E8%AF%95%E5%95%86%E5%AE%B61; ceshi3.com=000; _tp=4yIXobdhuXVqsjkFx0P2iMASYOkeh8xNZG8nsLU6vrc%3D; _pst=test_pop_paimai1; 3AB9D23F7A4B3C9B=EL26QKLSF42QY5VQDB24DLH6UDN6WAJPHBWZ4FADFE4KELAH3X2ANJOAVPSHLDKPWSRYF7RQV2VC52BH3VOA7BO77M; _pmvt=B8C37E33DEFDE51CF91E1E03E51657DA; _BELONG_CLIENT_=WPSC4XJXWK5USS4JNZY2X7VRLR5MCBKRSVHEXABGTHDGISIQK5YOLZUXYE7IOIM7MOKO74H6CRN6WHAAR4TMDV3XZWMXZRCRT5XRNE3V356BTOB2Y7LPK66VWQK6HPTGWVXIDXDCPVE3W5WMHAIO6AT2LX2XXVNUCXR34ZWFK6HY45CORGIKOSYDYZBF27WOKTUX6BS4FZMIJWNUX6CB4JAA25ZLF7ZEKYOO4QV5HTSBXGNRM3E242MBI6V5D4C5VJDQ3EOYCOW5BMTUJZACIBHXQFAVLRF76VQY5PNJGGJNBEZHSFYYJA3YORRT7FB5AHCOIFQKF3W5RWNUX6CB4JAA26JNMO7AYWNUPZF5HTSBXGNRM3E242MBI6V5D4C5VJDQ3EOYCOW5BWZDKMOJ5BS6II53ERY6ALV3ZWPF42L4CPUHEGPYIII35KDC4FCNVCORCXFD6IVNLBEDPB2GGP4UHWNRUDOQBDIW7RZJXBA2WV5ANZOTEGUCDWYRVQS2YUTIZNZ276PRYG4N56V6YTII7MBKBC7LYHO7C555HTSBXGNRM3E466AYN67DHWVM5HQFJ4NFDO5BS25MURJ77JCSIV3MN5FETFK7LEQ; __jdu=16032502952991025710679; TrackID=1tbnkk-u4I9SCAzDWWHu2EnK1tb-cfqq4awU5KgGAqBRGZ6O80oDDdFMdMhOY11YTgt8UxW0sNq6KvgZSCtf3UzKR20_Y2hLG_GL8u5_RuxY; thor=935FBA7C2CBBA0CC0BFB4218D06515C7D13948E2E1FE4F74580E1C256167763D7FE85EBAE195D06FC85FED5EB7D587807549820588656CF6CB8C347521F288431CF66D0B9F8C8451445900845AD3F52BD69BF35359316219353183EA1375F22D4A65FBE657F17B80FF74F71201FAC3873CBAE8638420D7B7B18A8666C9437BAEEC832306E65F05626F1DBC401F729C8DBA91D61C0F3C9B24823B4D5628156CE3; b-sec=TKHUGSSW2BNIBAFPUWCHWBLNP6UCUK2OKGI6MIH6LZ2UA5AFRON5K5N4VQVGQXOHDUOACDS4TJUX4; __jda=105283731.16032502952991025710679.1603250295.1603269408.1603278625.5; __jdb=105283731.3.16032502952991025710679|5.1603278625; __jdc=105283731; _base_=YKH2KDFHMOZBLCUV7NSRBWQUJPBI7JIMU5R3EFJ5UDHJ5LCU7R2NILKK5UJ6GLA2RGYT464UKXAI4Z6HPCTN4UQM3WHVQ4ENFP57OC64HEPQIANNMJPZ5CL7PFAE42P4SCF555UOYV4EADZJ5A77M4CWNNZELA6E4S4L2GLWBLTAIW5N6ZGEONMNNA5DQRDPVL52KNRE2QP7PNCDJAGH7WWXI4G4MEKLVTSGRNB3AY2NNXFWQMKNCRLT5NYD24YVLARKH46PYMN45BL6RPE575JRRF7FRWSCISIZTBJILVI37ZLU324OUG7ZYWTE6PYH2YAIELLNZNMASAKGXBBFJ2MUY6O4A3GKJDAGR5UWARVOC2SKY5BQ; _vender_new_=GI63BGTJFDBQ4ZYXZGVI52733ZIR2J55JRRKCFW54APLAACXLVQ75HEDDXCPGKXUKENQPPV3VW2WHGHJLDJTXGZWX3VANSQWGCZ6NW5Q3PD3ZGYEASLJQJJM2EBHK2DI2GHPQYHTYDMM354RJA5WHIDP47QVWKB2HKGFMVPSG7EAQ7LLJI6NQS7LR6FFFCMF333X5RLOEFIQHB4VLXML7BSFQRONXA6RMV7A3W6RQHU63BU24NL7HVMIOKJYEN4VFBEQFBKSTIUNGR4KLEN47XBDTVI2QLM73HVPBBMOI4H56OBL6RHDJJANAN45TKCG37YUYDLU6AOWN6GTGLNKJSDLCA2KIDIDPHM2QRW76FGA25HQDVTEOXWYHPKMTUATIWQ2V5Z7IROBXNYL3NASOYBCEGATV65XAXG72Z3QJ45MXA6IP2XTIKZ4WBLFFDKRW6N5ONAZYRXVFNB7GLAKQRXLD53FZTKH64D3L3EKRYK3AHL6ZQPGFFWVY4SQ4FAZP45VDAIFTJBCRJ6FNSKOMYPFKSPKELYNRDWO7AMO4NZCUQYJJ7GSLPJXOJMQ26BOEWKMRXOH6TK7UPDHCBXAJNU4ITQVVCPP7QXSUNA");
//        header.put("Connection", "keep-alive");
//        header.put("Content-Length", "917");
//        header.put("User-Agent", "JMApp/5.5.0 (iPhone; iOS 12.4.1; Scale/2.00)");
//        header.put("Accept-Language", "zh-Hans;q=1");
//        header.put("X-MLAAS-AT", "wl=0");
//        header.put("Host", "horus.jd.com");
//        header.put("Charset", "utf-8");
//
//
//
//
//
//        String url = "http://auction.shop.jd.com/paimai/bankruptcy/queryBankruptcyList?pageNo=1&pageSize=3";
//        HttpClient<HashMap> result = HttpClient.<HashMap>builder(url, HttpMethod.GET, HashMap.class)
//                .header(header)
//                .connectTimeout(7000)
//                .execute();
//            HashMap resultMap = result.get();
//            HashMap dateMap = (HashMap) resultMap.get("data");
//            System.out.println("当前页数=====>>>  " + dateMap.get("pageNo"));
//            System.out.println("每页数量=====>>>  " + dateMap.get("pageSize"));
//            System.out.println("总条数=====>>>  " + dateMap.get("totalCount"));
//            System.out.println("总页数=====>>>  " + dateMap.get("totalPage"));
//            List list = (List) dateMap.get("data");
//            System.out.println("信息=====>>>  " + list.toString());
//            System.out.println("信息数量=====>>>  " + list.size());


//        int[] intArray = {1,1,1,1,0,0,1,1,1,1};
//        int i = 1;
//        int k = intArray.length - 1;
//
//        if(intArray.length <= 2){
//            System.out.println("找到符合下标：" + k);
//            return;
//        }
//        int sumLeft = intArray[0];
//        int sumRight = 0;
//        while (true){
//            //todo 考虑下个值为0的情况，下标该怎么办？
//
//            if(sumLeft > sumRight){ //左边大于右边
//                sumRight = sumRight + intArray[k];
//                k--;
//            }else if(sumLeft < sumRight){ //右边大于左边
//                sumLeft = sumLeft + intArray[i];
//                i++;
//            } else if(sumLeft == sumRight){ //两边相等
//                sumRight = sumRight + intArray[k];
//                sumLeft = sumLeft + intArray[i];
//                k--;
//                i++;
//            }
//
//            if((k-i) == 0 && (sumRight == sumLeft)){
//                System.out.println("找到符合下标：" + k);
//                break;
//            }
//
//            if((k-i) < 1){
//                System.out.println("没有找到符合下标：-1" );
//                break;
//            }
//
//        }
//        String sum = "1.0E7";
//        BigDecimal big = new BigDecimal(sum);
//        big.toPlainString();


//        String string = txt2String();
//        HashMap map2 = JSONObject.parseObject(string, HashMap.class);
//        List<Object> list = (List<Object>) map2.get("data");
//        String s = list.get(0).toString();
//        HashMap map = JSONObject.parseObject(s, HashMap.class);
//        System.out.println(map.toString());
//        if ("cl".equals(map.get("typ"))) {
//            System.out.println("==========点击事件=============");
//            System.out.println("事件id：");
//            System.out.println(map.get("event_id"));
//            System.out.println("事件参数：");
//            System.out.println(map.get("event_param"));
//            System.out.println("json 参数：");
//            System.out.println(map.get("json_param"));
//            System.out.println("页面id：");
//            System.out.println(map.get("page_id"));
//            System.out.println("页面参数：");
//            System.out.println(map.get("page_param"));
//            System.out.println("==========================================");
//        }
//        if ("pv".equals(map.get("typ"))) {
//            System.out.println("============浏览事件====================");
//            System.out.println("事件id：");
//            System.out.println(map.get("event_id"));
//            System.out.println("事件参数：");
//            System.out.println(map.get("event_param"));
//            System.out.println("json 参数：");
//            System.out.println(map.get("json_param"));
//            System.out.println("页面id：");
//            System.out.println(map.get("page_id"));
//            System.out.println("页面参数：");
//            System.out.println(map.get("par"));
//            System.out.println(map.get("page_param"));
//            System.out.println("==========================================");
//        }
//        if ("ep".equals(map.get("typ"))) {
//            System.out.println("==========================================");
//            System.out.println("事件id：");
//            System.out.println(map.get("cls"));
//            System.out.println("事件参数：");
//            System.out.println(map.get("clp"));
//            System.out.println("json 参数：");
//            System.out.println(map.get("json_param"));
//            System.out.println("页面id：");
//            System.out.println(map.get("page_id"));
//            System.out.println("页面参数：");
//            System.out.println(map.get("par"));
//            System.out.println("==========================================");
//        }

//        List<String> listTt = txt3String("D:\\测试二维码\\photo\\paimaiId.txt");
//        List<String> listDh = txt3String("D:\\测试二维码\\photo\\dhdhdh.txt");
//        System.out.println(listDh.size());
//        System.out.println(listDh.toString());
//        System.out.println(listTt.size());
//        System.out.println(listTt.toString());
//        for(String string : listTt){
//            if(!listDh.contains(string)){
//                System.out.println(string);
//            }
//        }

//    }

    public static String txt2String() {
        File file = new File("D:\\测试二维码\\photo\\cs.txt");
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                result.append(System.lineSeparator() + s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public static List<String> txt3String(String path) {
        File file = new File(path);
        ArrayList<String> result = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                result.add(s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
