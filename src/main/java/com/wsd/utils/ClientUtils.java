package com.wsd.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
/**
 * @author tm
 * @version 1.0.0
 * @description 获取客户端信息工具类
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-3 16:05
 * @updateDate 2020-3-3 16:05
 */
public class ClientUtils {

    /**
     * 获取ip信息
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (ip != null && !"".equals(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (ip != null && !"".equals(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }

    /**
     * 获取浏览器信息
     * @param request
     * @return
     */
    public static String getRequestBrowserInfo(HttpServletRequest request) {
        String browserVersion = null;
        String header = request.getHeader("user-agent");
        if (header == null || header.equals("")) {
            return "";
        }
        if (header.indexOf("MSIE") > 0) {
            browserVersion = "IE";
        } else if (header.indexOf("Firefox") > 0) {
            browserVersion = "Firefox";
        } else if (header.indexOf("Chrome") > 0) {
            browserVersion = "Chrome";
        } else if (header.indexOf("Safari") > 0) {
            browserVersion = "Safari";
        } else if (header.indexOf("Camino") > 0) {
            browserVersion = "Camino";
        } else if (header.indexOf("Konqueror") > 0) {
            browserVersion = "Konqueror";
        }
        return browserVersion;

    }

    /**
     * 获取操作系统信息
     * @param request
     * @return
     */
    public static String getRequestSystemInfo(HttpServletRequest request) {
        String systenInfo = null;
        String header = request.getHeader("user-agent");
        if (header == null || header.equals("")) {
            return "";
        }
        //得到用户的操作系统
        if (header.indexOf("NT 6.0") > 0) {
            systenInfo = "Windows Vista/Server 2008";
        } else if (header.indexOf("NT 5.2") > 0) {
            systenInfo = "Windows Server 2003";
        } else if (header.indexOf("NT 5.1") > 0) {
            systenInfo = "Windows XP";
        } else if (header.indexOf("NT 6.0") > 0) {
            systenInfo = "Windows Vista";
        } else if (header.indexOf("NT 6.1") > 0) {
            systenInfo = "Windows 7";
        } else if (header.indexOf("NT 6.2") > 0) {
            systenInfo = "Windows Slate";
        } else if (header.indexOf("NT 6.3") > 0) {
            systenInfo = "Windows 9";
        } else if (header.indexOf("NT 10.0") > 0) {
            systenInfo = "Windows 10";
        }else if (header.indexOf("NT 5") > 0) {
            systenInfo = "Windows 2000";
        } else if (header.indexOf("NT 4") > 0) {
            systenInfo = "Windows NT4";
        } else if (header.indexOf("Me") > 0) {
            systenInfo = "Windows Me";
        } else if (header.indexOf("98") > 0) {
            systenInfo = "Windows 98";
        } else if (header.indexOf("95") > 0) {
            systenInfo = "Windows 95";
        } else if (header.indexOf("Mac") > 0) {
            systenInfo = "Mac";
        } else if (header.indexOf("Unix") > 0) {
            systenInfo = "UNIX";
        } else if (header.indexOf("Linux") > 0) {
            systenInfo = "Linux";
        } else if (header.indexOf("SunOS") > 0) {
            systenInfo = "SunOS";
        }
        return systenInfo;
    }

    /**
     * 获取主机信息
     * @param ip
     * @return
     */
    public static String getHostName(String ip) {
        InetAddress inet = null;
        try {
            inet = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            //logger.error("UnknownHostException", e);
        }
        return inet.getHostName();

    }

    /**
     * 根据IP获取地理位置信息
     * @param content
     * @param encodingString
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getLocationInfo(String content, String encodingString)
            throws UnsupportedEncodingException {

        String urlStr = "http://whois.pconline.com.cn/ip.jsp";
        String returnStr = getResult(urlStr, content, encodingString);

        if (returnStr != null) {
            // 处理返回的省市区信息
            returnStr = decodeUnicode(returnStr);
            String[] temp = returnStr.split(",");
            if(temp.length < 3){
                return "0";
            }
            return returnStr;
        }
        return null;
    }

    /**
     * 调用地址api接口
     * @param urlStr
     * @param content
     * @param encoding
     * @return
     */
    public static String getResult(String urlStr, String content, String encoding) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(2000);
            connection.setReadTimeout(2000);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.connect();
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(content);
            out.flush();
            out.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), encoding));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

    /**
     * 解析地址信息
     * @param theString
     * @return
     */
    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed      encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't') {
                        aChar = '\t';
                    } else if (aChar == 'r') {
                        aChar = '\r';
                    } else if (aChar == 'n') {
                        aChar = '\n';
                    } else if (aChar == 'f') {
                        aChar = '\f';
                    }
                    outBuffer.append(aChar);
                }
            } else {
                outBuffer.append(aChar);
            }
        }
        return outBuffer.toString();
    }
}
