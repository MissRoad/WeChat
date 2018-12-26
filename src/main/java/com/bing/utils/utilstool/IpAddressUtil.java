//package com.bing.utils.utilstool;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.jfinal.kit.HttpKit;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * @Description TODO
// * @Author fzq
// * @Date 2017/12/18 19:47
// */
//public class IpAddressUtil {
//    private static final Logger LOG = LoggerFactory.getLogger(IpAddressUtil.class);
//
//
//    public IpAddressUtil() {
//    }
//
//    /**
//     * String ip = "123.131.1.126";
//     *
//     * @Description 获取用户真实ip并根据ip获取真实地理位置
//     * @Author fzq
//     * @Date 2017/12/19 13:58
//     */
//    public static String address(String ip) {
//        StringBuilder url = new StringBuilder("http://ip.taobao.com/service/getIpInfo.php?ip=");
////        String ip = IpKit.getRealIp(getRequest());
////        String ip = "40.131.1.126";
//        String erro = "1";
//        url.append(ip);
//        String result = HttpKit.get(url.toString());
//        JSONObject json = JSON.parseObject(result).getJSONObject("data");
//        String code = JSON.parseObject(result).getString("code");
//        if (erro.equals(code)) {
//            return "查询错误";
//        }
//        String country = json.getString("country");
//        String area = json.getString("area");
//        String city = json.getString("city");
//        String region = json.getString("region");
//        StringBuilder address = new StringBuilder().append(country).append(area).append(region).append(city);
//        Date now = new Date();
//        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String format = sf.format(now);
//        LOG.info("IP为{}", ip);
//        LOG.info("在{}来自 {}的用户访问了", format, address);
//        return address.toString();
//    }
//
//    public static void main(String[] args) {
//        IpAddressUtil ipAddressUtil = new IpAddressUtil();
//    }
//}
