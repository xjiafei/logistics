
package com.winterframework.logistics.base.utils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import com.winterframework.logistics.base.model.Request;
import com.winterframework.modules.web.jsonresult.Pager;

public class SignUtil
{
	public static String sign(Map<String,String> params){
		sortMap(params);
		StringBuffer sb=new StringBuffer();
		String equalStr="=";
		String andStr="&";
		for(Map.Entry<String,String> entry:params.entrySet()){
			sb.append(entry.getKey()).append(equalStr).append(entry.getValue()).append(andStr);
		}
		if(sb.length()>0){
			return MD5Util.getMD5Format(sb.substring(0,sb.length()-1).toString());
		}
		return null;
	}
	public static String sign(String params){
		//md5(token+timestamp+data)
		if(params.length()>0){
			return MD5Util.getMD5Format(params);
		}
		return null;
	}
	public static boolean verify(String sign,Map<String,String> params){
		if(StringUtils.isNotEmpty(sign)){
			return sign.equals(sign(params));
		}
		return false;
	}
	public static boolean verify(String sign,String params){
		if(StringUtils.isNotEmpty(sign)){
			return sign.equals(sign(params));
		}
		return false;
	}
	private static Map<String,String> sortMap(Map<String,String> params){
		if (params == null || params.isEmpty()) {
            return null;
        }

        Map<String, String> sortMap = new TreeMap<String, String>(
                new Comparator<String>() {
                	@Override
                	public int compare(String o1, String o2) {
                		// TODO Auto-generated method stub
                		return o1.compareTo(o2);
                	}
				});

        sortMap.putAll(params);
        return sortMap;
	}
	public static void main(String[] s){
		Map<String,String> p=new HashMap<String,String>();
		p.put("b",2+"");
		p.put("a",1+"");
		p.put("c",3+"");
		char[] cs="{\"companyId\":2,\"deviceNumber\":null}"
				.toCharArray();
		Arrays.sort(cs);
		System.out.println(new String(cs));
		
		String ss=sign(p);
		System.out.println(ss);
		System.out.println(verify(ss,p));
		String kk="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJsb2dpbiIsImlhdCI6MTUxMzA3NDEyMSwic3ViIjoiLTEiLCJleHAiOjE1MTQ1NDUzNTAsIm5iZiI6MTUxMzA3NDEyMX0.Zc88CxsE8n_N5BNj9zMWH9zyENksT1IfdGcaKmjGGkY1514270521960{\"userName\":\"ivan\",\"passwd\":\"e833c14c7f490a431519cb0919407a7c\"},\"pager\":{\"startNo\":\"\",\"endNo\":\"\"}";
		String kk2="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyIiwiaWF0IjoxNTE0NDU1NDE4LCJzdWIiOiIyIiwiZXhwIjoxNTE1MDYwMjE4LCJuYmYiOjE1MTQ0NTU0MTh9.FsO_L-jlVbQGWDoUXydH0maC1ncdHI3D7YIvxCFMniM1514455725724"
				+new String(cs);
		System.out.println(kk2);
		System.out.println(SignUtil.sign(kk2));;
		Request<String> sss=new Request<String>();
		sss.setToken("32位string");
		sss.setTimestamp(1543345676722L);
		sss.setData("json obj");
		sss.setBizId(111L); //一般空
		Pager ps=new Pager();
		ps.setStartNo(1);
		ps.setEndNo(100);
		sss.setPager(ps);
		
		sss.setSign("md5(token+timestamp+data)");
	System.out.println(JsonUtils.toJson(sss));
	}
}