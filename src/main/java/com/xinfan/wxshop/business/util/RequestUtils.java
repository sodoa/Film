package com.xinfan.wxshop.business.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UrlPathHelper;

import com.xinfan.wxshop.business.constants.BizConstants;
import com.xinfan.wxshop.common.base.DataMap;
import com.xinfan.wxshop.common.page.Pagination;

/**
 * HttpServletRequest帮助类
 * 
 */
public class RequestUtils {
	private static final Logger logger = LoggerFactory.getLogger(RequestUtils.class);

	public static HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}

	public static boolean ifWecatRequest() {
		String ua = getRequest().getHeader("user-agent").toLowerCase();
		if (ua != null && ua.indexOf("micromessenger") > 0) {// 是微信浏览器
			return true;
		}
		return false;
	}

	/**
	 * 获取会话对象.
	 * 
	 * @return the session from the request (request.getSession()).
	 */
	public static HttpSession getSession() {
		return getRequest().getSession();
	}

	/**
	 * 获取上下文对象 author:chenyong
	 * 
	 * @return
	 */
	public static ServletContext getServletContext() {
		return getSession().getServletContext();
	}

	public static String getRequestUrl(HttpServletRequest request) {
		StringBuffer requestUrl = request.getRequestURL();
		return requestUrl.toString();
	}

	public static void getRequestQueryParamter(HttpServletRequest request, DataMap paramter, String name) {
		String value = request.getParameter(name);
		if (StringUtils.isNotEmpty(value)) {
			paramter.put(name, value);
		}
	}

	public static String getRequestUri(HttpServletRequest request) {
		return request.getRequestURI();
	}

	/**
	 * 获取QueryString的参数，并使用URLDecoder以UTF-8格式转码。如果请求是以post方法提交的，
	 * 那么将通过HttpServletRequest#getParameter获取。
	 * 
	 * @param request
	 *            web请求
	 * @param name
	 *            参数名称
	 * @return
	 */
	public static String getQueryParam(HttpServletRequest request, String name) {
		if (StringUtils.isBlank(name)) {
			return null;
		}
		if (request.getMethod().equalsIgnoreCase("post")) {
			return request.getParameter(name);
		}
		String s = request.getQueryString();
		if (StringUtils.isBlank(s)) {
			return null;
		}
		try {
			s = URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("encoding UTF-8 not support?", e);
		}
		String[] values = parseQueryString(s).get(name);
		if (values != null && values.length > 0) {
			return values[values.length - 1];
		} else {
			return null;
		}
	}

	public static Map<String, String[]> getQueryParamMap(String s) {

		if (StringUtils.isBlank(s)) {
			return null;
		}
		try {
			s = URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("encoding UTF-8 not support?", e);
		}

		Map<String, String[]> values = parseQueryString(s);
		return values;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> getQueryParams(HttpServletRequest request) {
		Map<String, String[]> map;
		if (request.getMethod().equalsIgnoreCase("post")) {
			map = request.getParameterMap();
		} else {
			String s = request.getQueryString();
			if (StringUtils.isBlank(s)) {
				return new HashMap<String, Object>();
			}
			try {
				s = URLDecoder.decode(s, "post");
			} catch (UnsupportedEncodingException e) {
				logger.error("encoding UTF-8 not support?", e);
			}
			map = parseQueryString(s);
		}

		Map<String, Object> params = new HashMap<String, Object>(map.size());
		int len;
		for (Map.Entry<String, String[]> entry : map.entrySet()) {
			len = entry.getValue().length;
			if (len == 1) {
				params.put(entry.getKey(), entry.getValue()[0]);
			} else if (len > 1) {
				params.put(entry.getKey(), entry.getValue());
			}
		}
		return params;
	}

	/**
	 * 
	 * Parses a query string passed from the client to the server and builds a
	 * <code>HashTable</code> object with key-value pairs. The query string
	 * should be in the form of a string packaged by the GET or POST method,
	 * that is, it should have key-value pairs in the form <i>key=value</i>,
	 * with each pair separated from the next by a &amp; character.
	 * 
	 * <p>
	 * A key can appear more than once in the query string with different
	 * values. However, the key appears only once in the hashtable, with its
	 * value being an array of strings containing the multiple values sent by
	 * the query string.
	 * 
	 * <p>
	 * The keys and values in the hashtable are stored in their decoded form, so
	 * any + characters are converted to spaces, and characters sent in
	 * hexadecimal notation (like <i>%xx</i>) are converted to ASCII characters.
	 * 
	 * @param s
	 *            a string containing the query to be parsed
	 * 
	 * @return a <code>HashTable</code> object built from the parsed key-value
	 *         pairs
	 * 
	 * @exception IllegalArgumentException
	 *                if the query string is invalid
	 * 
	 */
	public static Map<String, String[]> parseQueryString(String s) {
		String valArray[] = null;
		if (s == null) {
			throw new IllegalArgumentException();
		}
		Map<String, String[]> ht = new HashMap<String, String[]>();
		StringTokenizer st = new StringTokenizer(s, "&");
		while (st.hasMoreTokens()) {
			String pair = (String) st.nextToken();
			int pos = pair.indexOf('=');
			if (pos == -1) {
				continue;
			}
			String key = pair.substring(0, pos);
			String val = pair.substring(pos + 1, pair.length());
			if (ht.containsKey(key)) {
				String oldVals[] = (String[]) ht.get(key);
				valArray = new String[oldVals.length + 1];
				for (int i = 0; i < oldVals.length; i++) {
					valArray[i] = oldVals[i];
				}
				valArray[oldVals.length] = val;
			} else {
				valArray = new String[1];
				valArray[0] = val;
			}
			ht.put(key, valArray);
		}
		return ht;
	}

	public static Map<String, String> getRequestMap(HttpServletRequest request, String prefix) {
		return getRequestMap(request, prefix, false);
	}

	public static Map<String, String> getRequestMapWithPrefix(HttpServletRequest request, String prefix) {
		return getRequestMap(request, prefix, true);
	}

	@SuppressWarnings("unchecked")
	private static Map<String, String> getRequestMap(HttpServletRequest request, String prefix, boolean nameWithPrefix) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<String> names = request.getParameterNames();
		String name, key, value;
		while (names.hasMoreElements()) {
			name = names.nextElement();
			if (name.startsWith(prefix)) {
				key = nameWithPrefix ? name : name.substring(prefix.length());
				value = StringUtils.join(request.getParameterValues(name), ',');
				map.put(key, value);
			}
		}
		return map;
	}

	public static DataMap getRequestDataMap() {
		return getRequestDataMap(getRequest());
	}

	@SuppressWarnings("unchecked")
	public static DataMap getRequestDataMap(HttpServletRequest request) {
		DataMap map = new DataMap();
		Enumeration<String> names = request.getParameterNames();
		String key, value;
		while (names.hasMoreElements()) {
			key = names.nextElement();
			value = request.getParameter(key);
			if (value != null) {
				value = value.trim();
			}
			map.put(key, value);
		}
		return map;
	}

	/**
	 * 获取访问者IP
	 * 
	 * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
	 * 
	 * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
	 * 如果还不存在则调用Request .getRemoteAddr()。
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
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
	 * 获得当的访问路径
	 * 
	 * HttpServletRequest.getRequestURL+"?"+HttpServletRequest.getQueryString
	 * 
	 * @param request
	 * @return
	 */
	public static String getLocation(HttpServletRequest request) {
		UrlPathHelper helper = new UrlPathHelper();
		StringBuffer buff = request.getRequestURL();
		String uri = request.getRequestURI();
		String origUri = helper.getOriginatingRequestUri(request);
		buff.replace(buff.length() - uri.length(), buff.length(), origUri);
		String queryString = helper.getOriginatingQueryString(request);
		if (queryString != null) {
			buff.append("?").append(queryString);
		}
		return buff.toString();
	}

	public static Pagination getPagination() {
		return getPagination(getRequest());
	}

	public static Pagination getPagination(HttpServletRequest request) {
		Pagination page = new Pagination();
		page.setPageNo(1);
		page.setPageSize(20);

		String pageno = request.getParameter("page");
		String pagesize = request.getParameter("rows");

		if (pageno != null) {
			try {
				page.setPageNo(Integer.parseInt(pageno));
			} catch (Exception e) {
				// logger.error(e.getMessage(), e);
			}
			try {
				page.setPageSize(Integer.parseInt(pagesize));
			} catch (Exception e) {
				// logger.error(e.getMessage(), e);
			}
		}
		return page;
	}

	public static Pagination getDataTablePagination(HttpServletRequest request) {
		Pagination page = new Pagination();
		page.setPageNo(1);
		page.setPageSize(20);

		String start = request.getParameter("start");
		String pagesize = request.getParameter("length");

		if (start != null) {
			try {

				int startInt = Integer.parseInt(start);
				int pagesizeInt = Integer.parseInt(pagesize);

				page.setPageNo(startInt / pagesizeInt + 1);

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			try {
				page.setPageSize(Integer.parseInt(pagesize));
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		return page;
	}

	public static DataMap getUserSessionMap() {
		DataMap sessionMap = (DataMap) getSession().getAttribute("sessionMap");
		return sessionMap;
	}

	public static String getSessionUserId() {
		DataMap sessionMap = getUserSessionMap();
		return sessionMap.getString("userid");
	}

	public static boolean isQtMultipartContent(HttpServletRequest request) {
		if (!"post".equalsIgnoreCase(request.getMethod())) {
			return false;
		}

		String contentType = request.getContentType();
		System.out.println(contentType);
		if (contentType == null) {
			return false;
		}
		if (contentType.toLowerCase(Locale.ENGLISH).startsWith("application/octet-stream")) {
			return true;
		}

		return false;
	}

	public static boolean isMultipartContent(HttpServletRequest request) {
		if (!"post".equalsIgnoreCase(request.getMethod())) {
			return false;
		}

		String contentType = request.getContentType();
		if (contentType == null) {
			return false;
		}
		if (contentType.toLowerCase(Locale.ENGLISH).startsWith("multipart/")) {
			return true;
		}

		return false;
	}

	public static DataMap getConsumerSessionMap() {
		return (DataMap) getSession().getAttribute(BizConstants.CUSTOMER_USER_SESSION_KEY);
	}

}
