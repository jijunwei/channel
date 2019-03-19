/**
 * Project Name:rst-trunk
 * File Name:ProcessException.java
 * Package Name:rst.core.exception
 * Date:2016-8-29上午11:44:55
 * Copyright (c) 2016, chenjw@sunline.cn All Rights Reserved.
 *
*/

package rst.core.exception;
/**
 * ClassName:ProcessException <br/>
 * @author   Kevin Chen <br/>Date:     2016-8-29 上午11:44:55 <br/>
 * @version  
 * @since    JDK 1.6
 * @see
 */
public class ProcessException extends RuntimeException {

		private static final long serialVersionUID = 1L;

		// 错误code
		public String errorCode;

		public ProcessException() {

		}

		public ProcessException(String message) {
			super(message);
		}
		
		public ProcessException(String errorCode, String message) {
			super(message);
			this.errorCode = errorCode;
		}
		
		public ProcessException(String errorCode, Throwable cause) {
			super(cause);
			this.errorCode = errorCode;
		}

		public String getErrorCode() {
			return errorCode;
		}

		
	}