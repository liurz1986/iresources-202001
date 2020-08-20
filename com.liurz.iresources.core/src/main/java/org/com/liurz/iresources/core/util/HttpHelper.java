package org.com.liurz.iresources.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class HttpHelper {

	// private static Logger log=Logger.getLogger(HttpHelper.class);
	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			// log.info("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param jsonparam
	 *            请求参数，json字符串
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String jsonparam) {
		OutputStream out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			connection.setConnectTimeout(3000); // 连接超时时间
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("connection", "Keep-Alive");// 设置维持长连接
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.setRequestProperty("Charset", "UTF-8");// 设置文件字符集:
			// 发送POST请求必须设置如下两行,因为这个是post请求，参数要放在http正文内，因此需要设为true
			connection.setDoOutput(true);// 向服务器写数据
			connection.setDoInput(true);// 读取数据

			// 默认是 GET方式
			connection.setRequestMethod("POST");// 以post请求方式提交

			// Post 请求不能使用缓存
			connection.setUseCaches(false);

			// 配置本次连接的Content-type，配置为application/x-www-form-urlencoded 文本格式
			// 进行编码。json格式
			connection.setRequestProperty("Content-type", "application/json");
			// 转换为字节数组
			byte[] data = jsonparam.getBytes();
			// 设置文件长度
			connection.setRequestProperty("Content-Length", String.valueOf(data.length));
			// 获取URLConnection对象对应的输出流
			// 开始连接请求
			// connection.connect();getOutputStream()已经包括该方法了
			out = connection.getOutputStream();
			// 发送请求参数
			out.write(data);
			// flush输出流的缓冲
			out.flush();

			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			// log.info("发送 POST 请求出现异常！"+e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public static void main(String[] args) {
		String url = "http://localhost:8080/ssm/rest/v1/role/saveRole?userId=admin";
		JSONObject jsonparams = new JSONObject();
		jsonparams.put("description", "description");
		jsonparams.put("name", "liurz");
		jsonparams.put("createTime", "2019-07-06 22:31:32");
		jsonparams.put("updatetime", "2019-07-07");
		// JSONObject jsonparams2=new JSONObject();
		// jsonparams2.put("id", 123);
		// jsonparams2.put("name", "liurz22");
		// jsonparams2.put("age", 11);
		// List<JSONObject> list=new ArrayList<JSONObject>();
		// list.add(jsonparams);
		// list.add(jsonparams2);
		// System.out.println(jsonparams.toString());
		String res = HttpHelper.sendPost(url, jsonparams.toJSONString());
		System.out.println(res);

	}
}
