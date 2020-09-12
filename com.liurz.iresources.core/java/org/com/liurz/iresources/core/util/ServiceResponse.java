package org.com.liurz.iresources.core.util;

/**
 * 返回结果对象
 * 
 * @author admin
 *
 * @param <T>
 */
public class ServiceResponse<T> {
	/**
	 * 返回的id
	 */
	private String serviceId;
	/**
	 * 状态 ：0表示成功-1表示失败
	 */
	private Long status;
	/**
	 * 信息 ：特别是错误信息
	 */
	private String message;
	/**
	 * 数据对象
	 */
	private T data;

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
