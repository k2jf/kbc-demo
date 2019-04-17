package com.k2data.kbc.audit.model;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class AuditLog {

    /**
     * 主键
     * 物理主键
     * isNullAble:0
     */
    private Integer id;

    /**
     * 创建者
     * isNullAble:0
     */
    private String creator;

    /**
     * 创建时间
     * isNullAble:0
     */
    private Date createDate;

    /**
     * isNullAble:1
     */
    private String classMethod;

    /**
     * 操作IP地址
     * isNullAble:0
     */
    private String ip;

    /**
     * 请求URI
     * isNullAble:0
     */
    private String requestURL;

    /**
     * 操作方式
     * isNullAble:0
     */
    private String requestMethod;

    /**
     * isNullAble:1
     */
    private String title;

    /**
     * isNullAble:0
     */
    private String userAgent;

    /**
     * isNullAble:1
     */
    private String params;

    /**
     * 日志类型
     * isNullAble:1
     */
    private String typeLog;


    // 日志类型（1：接入日志；2：错误日志）
    public static final String TYPE_ACCESS = "1";
    public static final String TYPE_EXCEPTION = "2";


    public void setParams(Map paramMap) {
        if (paramMap == null) {
            return;
        }
        StringBuilder params = new StringBuilder();
        for (Map.Entry<String, String[]> param : ((Map<String, String[]>) paramMap).entrySet()) {
            params.append(("".equals(params.toString()) ? "" : "&") + param.getKey() + "=");
            String paramValue = (param.getValue() != null && param.getValue().length > 0 ? param.getValue()[0] : "");
            params.append(this.abbr(StringUtils.endsWithIgnoreCase(param.getKey(), "password") ? "" : paramValue, 100));
        }
        this.params = params.toString();
    }

    public String abbr(String str, int length) {
        if (str == null) {
            return "";
        }
        try {
            StringBuilder sb = new StringBuilder();
            int currentLength = 0;
            for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
                currentLength += String.valueOf(c).getBytes("GBK").length;
                if (currentLength <= length - 3) {
                    sb.append(c);
                } else {
                    sb.append("...");
                    break;
                }
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String replaceHtml(String html) {
        if (isBlank(html)) {
            return "";
        }
        String regEx = "<.+?>";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(html);
        String s = m.replaceAll("");
        return s;
    }


    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getClassMethod() {
        return classMethod;
    }

    public void setClassMethod(String classMethod) {
        this.classMethod = classMethod;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRequestURL() {
        return requestURL;
    }

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getTypeLog() {
        return typeLog;
    }

    public void setTypeLog(String typeLog) {
        this.typeLog = typeLog;
    }

}