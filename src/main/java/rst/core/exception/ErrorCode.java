/**
 * Project Name:rst-trunk
 * File Name:ErrorCode.java
 * Package Name:rst.core.exception
 * Date:2016-8-29上午11:46:22
 * Copyright (c) 2016, chenjw@sunline.cn All Rights Reserved.
 *
 */

package rst.core.exception;

/**
 * ClassName:ErrorCode <br/>
 * 
 * @author Kevin Chen <br/>
 *         Date: 2016-8-29 上午11:46:22 <br/>
 * @version
 * @since JDK 1.6
 * @see
 */
public enum ErrorCode {

	/**
	 * 本地缓存中无文件
	 */
	E_1001("1001","本地缓存中无文件"),
	
	/**
	 * http连接异常,无法连接恒丰银行前置
	 */
	E_1002("1002","http连接异常,无法连接恒丰银行前置"),
	
	/**
	 * 银行返回报文解析失败
	 */
	E_1003("1003","银行返回报文解析失败"),
	
	/**
	 * 银行返回报文解析失败
	 */
	E_1004("1004","调用银行报文加签失败"),
	
	/**
	 * 调用银行对报文加密失败
	 */
	E_1005("1005","调用银行报文加密失败"),
	
	/**
	 * 调用银行对报文解密失败
	 */
	E_1006("1006","银行返回报文解密失败"),
	
	/**
	 * Json数据格式不正确
	 */
	E_1007("1007","Json数据格式不正确"),
	
	/**
	 * 无此业务类型
	 */
	E_1008("1008","无此业务类型"),
	
	
	/**
	 * 银行返回报文 处理失败
	 */
	E_1009("1009","银行返回报文 处理失败"),
	
	/**
	 * 银行返回报文验签异常，请检查相关秘
	 */
	E_1010("1010","银行返回报文验签异常，请检查相关秘钥"),
	
	/**
	 * 协议与前置的地址不一致
	 * http
	 */
	E_1011("1011","http协议与地址不一致，请检查资金方前置地址是否正确"),
	
	/**
	 * 系统内部异常
	 */
	E_9998("9998", "系统处理异常"),
	/**
	 * 后端系统超时
	 */
	E_9999("9999", "后端系统超时");

	private String code;
	private String message;

	private ErrorCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
