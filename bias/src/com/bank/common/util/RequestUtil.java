package com.bank.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.bank.common.AppConstants;
import com.bank.common.AppConstants.HttpMethod;


public class RequestUtil {

    private static String ENCODE_NAME = "UTF-8";

    public static Entry<String, Integer> splitBaseUrl(String baseURL) {
        String[] hostInfo = baseURL.split(":");
        if (hostInfo.length < 2) {
            return new SimpleEntry<String, Integer>(hostInfo[0], 80);
        } else {
            return new SimpleEntry<String, Integer>(hostInfo[0], Integer.parseInt(hostInfo[1]));
        }
    }

    public static Entry<String, Integer> splitHttpsBaseUrl(String baseURL) {
        String[] hostInfo = baseURL.split(":");
        if (hostInfo.length < 2) {
            return new SimpleEntry<String, Integer>(hostInfo[0], 443);
        } else {
            return new SimpleEntry<String, Integer>(hostInfo[0], Integer.parseInt(hostInfo[1]));
        }
    }

    public static HttpMethod getHttpMethod(String method) {
        if (method.equalsIgnoreCase("get")) {
            return HttpMethod.GET;
        } else if (method.equalsIgnoreCase("post")) {
            return HttpMethod.POST;
        } else if (method.equalsIgnoreCase("delete")) {
            return HttpMethod.DELETE;
        } else if (method.equalsIgnoreCase("put")) {
            return HttpMethod.PUT;
        } else {
            throw new IllegalArgumentException("unsupport HttpMethod:" + method);
        }
    }

    public static String doRequest(String protocol, Integer port, String baseURL, String path,
            Map<String, String> params, HttpMethod method, Map<String, String> header) {
        URIBuilder urlBuilder = new URIBuilder();
        if (protocol == null) {
            protocol = AppConstants.HTTP_PROTOCOL;
        }
        if (port == null) {
            port = 80;
        }
        String newPath = StringUtil.convertPath(path, params);
        urlBuilder = urlBuilder.setHost(baseURL).setScheme(protocol).setPort(port).setPath(newPath);
        if (method == HttpMethod.GET || method == HttpMethod.DELETE) {
            for (String key : params.keySet()) {
                urlBuilder = urlBuilder.setParameter(key, params.get(key));
            }
        }

        URI uri;
        try {
            uri = urlBuilder.build();
        } catch (URISyntaxException e1) {
            throw new IllegalArgumentException("cannot build the uri, please check the arguments");
        }

        HttpClient httpClient = new DefaultHttpClient();
        if (AppConstants.HTTPS_PROTOCOL.equals(protocol)) {
            httpClient = SSLWrapper.wrapClient(httpClient, port);
        }

        HttpRequestBase request = buildRequest(method, uri, params);
        if (header != null) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                request.addHeader(entry.getKey(), entry.getValue());
            }
        }

        String response = "";
        try {
            HttpResponse res = httpClient.execute(request);
            HttpEntity entity = res.getEntity();
            response = EntityUtils.toString(entity);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            request.releaseConnection();
        }
        return response;
    }

    private static void setEntityForPostAndPut(Map<String, String> params, HttpEntityEnclosingRequestBase request) {
        if (params != null) {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            try {
                request.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            } catch (UnsupportedEncodingException e) {
                // may no happend.
            }
        }
    }

    private static HttpRequestBase buildRequest(HttpMethod method, URI uri, Map<String, String> params) {
        HttpRequestBase request = null;
        switch (method) {
            case GET :
                request = new HttpGet(uri);
                break;
            case POST :
                HttpEntityEnclosingRequestBase postRequest = new HttpPost(uri);
                setEntityForPostAndPut(params, postRequest);
                request = new HttpPost(uri);
                break;
            case PUT :
                HttpEntityEnclosingRequestBase putRequest = new HttpPut(uri);
                setEntityForPostAndPut(params, putRequest);
                request = new HttpPut(uri);
                break;
            case DELETE :
                request = new HttpDelete(uri);
                break;
            default :
                throw new IllegalArgumentException("not support http method:" + method);
        }
        return request;
    }

    public static String doPost(String url, Map<String, String> params, Map<String, String> header) {
        String response = "";
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        try {
            // add parameters.
            if (params != null) {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                Iterator<String> iterator = params.keySet().iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    nameValuePairs.add(new BasicNameValuePair(key, params.get(key)));
                }
                post.setEntity(new UrlEncodedFormEntity(nameValuePairs, Charset.forName(ENCODE_NAME)));
            }
            // add headers.
            if (header != null) {
                Iterator<String> headIterator = header.keySet().iterator();
                while (headIterator.hasNext()) {
                    String key = headIterator.next();
                    post.addHeader(key, header.get(key));
                }
            }

            HttpResponse res = client.execute(post);
            response = EntityUtils.toString(res.getEntity());

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            post.releaseConnection();
        }

        return response;
    }

    public static String doPost(String url, Map<String, String> params) {
        return RequestUtil.doPost(url, params, null);
    }

    /**
     * public static String doGet(String url, Map<String, String> params) {
     * return RequestUtil.doGet(url, params, null); }
     *
     * public static String doGet(String url) { return RequestUtil.doGet(url,
     * null, null); } public static String doGet(String url, Map<String, String>
     * params, Map<String, String> header) { // add params if (params != null) {
     * String paramString = ""; Iterator<String> iterator =
     * params.keySet().iterator(); while (iterator.hasNext()) { String key =
     * iterator.next(); paramString += key + "=" + params.get(key) + "&"; }
     * String concat = url.indexOf("?") == -1 ? "?" : "&"; url += concat +
     * paramString;
     *
     * }
     *
     * HttpClient client = new DefaultHttpClient(); HttpGet get = new
     * HttpGet(url); // add headers. if (header != null) { Iterator<String>
     * headIterator = header.keySet().iterator(); while (headIterator.hasNext())
     * { String key = headIterator.next(); get.addHeader(key, header.get(key));
     * } }
     *
     * String response = ""; try { HttpResponse res = client.execute(get);
     * response = EntityUtils.toString(res.getEntity()); } catch (Exception e) {
     * e.printStackTrace(); } finally { get.releaseConnection(); } return
     * response; }
     **/

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("Proxy-Client-IP");
        if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("WL-Proxy-Client-IP");
        if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getRemoteAddr();
        if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return "";
    }
}
