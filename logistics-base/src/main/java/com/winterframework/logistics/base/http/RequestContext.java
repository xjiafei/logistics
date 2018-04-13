package com.winterframework.logistics.base.http;
 
import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.cookie.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartRequest;

import com.winterframework.modules.web.util.RequestUtils;
 
@SuppressWarnings("unused")
public class RequestContext {

	private final static Logger log = LoggerFactory.getLogger(RequestContext.class);
	private final static String UTF_8 = "UTF-8";
	private final static ThreadLocal<RequestContext> contexts = new ThreadLocal<RequestContext>() {
		{
		}
	};
	private ServletContext context;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Long userId;
	private Long companyId;

	public static RequestContext init(ServletContext ctx, HttpServletRequest req, HttpServletResponse res,Long userId) {
		RequestContext rc = new RequestContext();
		rc.context = ctx;
		rc.request = req;
		rc.response = res;
		rc.response.setCharacterEncoding(UTF_8);
		rc.userId=userId;
		rc.companyId=-1L;
		contexts.set(rc);
		return rc;
	}
	public static RequestContext init(Long userId) {
		RequestContext rc = new RequestContext();
		rc.context = null;
		rc.request = null;
		rc.response = null;
		//rc.response.setCharacterEncoding(UTF_8);
		rc.userId=userId;
		rc.companyId=-1L;
		contexts.set(rc);
		return rc;
	}

	/** 
	 * 获取当前请求的上下文 
	 * @return 
	 */
	public static RequestContext get() {
		return contexts.get();
	}

	public static Long getUserId() {
		if (contexts.get() != null) {
			if(contexts.get().userId!=null){
				return contexts.get().userId;
			}
		}
		return 0L; //游客
	}
	public static Long getCompanyId() {
		if (contexts.get() != null) {
			if(contexts.get().companyId!=null){
				return contexts.get().companyId;
			}
		}
		return -1L; //未设置
	}

	public void destroy() {
		this.context = null;
		this.request = null;
		this.response = null;
		contexts.remove();
	}

	public Locale locale() {
		return request.getLocale();
	}

	public void closeCache() {
		header("Pragma", "No-cache");
		header("Cache-Control", "no-cache");
		header("Expires", 0L);
	}

	public long id() {
		return param("id", 0L);
	}

	public String ip() {
		return RequestUtils.getRemoteAddr(request);
	}

	@SuppressWarnings("unchecked")
	public Enumeration<String> params() {
		return request.getParameterNames();
	}

	public String param(String name, String... def_value) {
		String v = request.getParameter(name);
		return (v != null) ? v : ((def_value.length > 0) ? def_value[0] : null);
	}

	public long param(String name, long def_value) {
		return NumberUtils.toLong(param(name), def_value);
	}

	public int param(String name, int def_value) {
		return NumberUtils.toInt(param(name), def_value);
	}

	public byte param(String name, byte def_value) {
		return (byte) NumberUtils.toInt(param(name), def_value);
	}

	public String[] params(String name) {
		return request.getParameterValues(name);
	}

	public long[] lparams(String name) {
		String[] values = params(name);
		if (values == null)
			return null;
		return (long[]) ConvertUtils.convert(values, long.class);
	}

	public String uri() {
		return request.getRequestURI();
	}

	public String contextPath() {
		return request.getContextPath();
	}

	public void redirect(String uri) throws IOException {
		response.sendRedirect(uri);
	}

	public void forward(String uri) throws ServletException, IOException {
		RequestDispatcher rd = context.getRequestDispatcher(uri);
		rd.forward(request, response);
	}

	public void include(String uri) throws ServletException, IOException {
		RequestDispatcher rd = context.getRequestDispatcher(uri);
		rd.include(request, response);
	}

	public boolean isUpload() {
		return (request instanceof MultipartRequest);
	}

	public boolean isRobot() {
		return RequestUtils.isRobot(request);
	}

	/** 
	 * 输出信息到浏览器 
	 * @param msg 
	 * @throws IOException 
	 */
	public void print(Object msg) throws IOException {
		if (!UTF_8.equalsIgnoreCase(response.getCharacterEncoding()))
			response.setCharacterEncoding(UTF_8);
		response.getWriter().print(msg);
	}

	public void output_json(String[] key, Object[] value) throws IOException {
		StringBuilder json = new StringBuilder("{");
		for (int i = 0; i < key.length; i++) {
			if (i > 0)
				json.append(',');
			boolean isNum = value[i] instanceof Number;
			json.append("\"");
			json.append(key[i]);
			json.append("\":");
			if (!isNum)
				json.append("\"");
			json.append(value[i]);
			if (!isNum)
				json.append("\"");
		}
		json.append("}");
		print(json.toString());
	}

	public void output_json(String key, Object value) throws IOException {
		output_json(new String[] { key }, new Object[] { value });
	}

	public void error(int code, String... msg) throws IOException {
		if (msg.length > 0)
			response.sendError(code, msg[0]);
		else
			response.sendError(code);
	}

	public void forbidden() throws IOException {
		error(HttpServletResponse.SC_FORBIDDEN);
	}

	public void not_found() throws IOException {
		error(HttpServletResponse.SC_NOT_FOUND);
	}

	public ServletContext context() {
		return context;
	}

	public HttpServletRequest request() {
		return request;
	}

	public HttpServletResponse response() {
		return response;
	}

	public static void main(String[] args) throws Exception {
		String sb = "22|user_test_1|-147789545|1389331690839";
		String keyss = Base64.encodeBase64String(sb.toString().getBytes("UTF-8"));
		//String cookie="MjJ8dXNlcl90ZXN0XzF8LTIwNDQwNjc4MzZ8MTM4OTMyMTUxNjQ4OQ==";
		//RequestContext rc=new RequestContext();
		//src.cookie("aaa", cookie, 1, true);
	}


	public String header(String name) {
		return request.getHeader(name);
	}

	public void header(String name, String value) {
		response.setHeader(name, value);
	}

	public void header(String name, int value) {
		response.setIntHeader(name, value);
	}

	public void header(String name, long value) {
		response.setDateHeader(name, value);
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	
}