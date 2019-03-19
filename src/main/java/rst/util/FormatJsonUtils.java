package rst.util;

public class FormatJsonUtils {

		/**
		 * 打印输入到控制台
		 * @param jsonStr
		 * @author   lizhgb
		 * @Date   2015-10-14 下午1:17:22
		 */
		public static void printJson(String jsonStr){
			System.out.println(formatJson(jsonStr));
		}
	   
		/**
		 * 格式化
		 * @param jsonStr
		 * @return
		 * @author   lizhgb
		 * @Date   2015-10-14 下午1:17:35
		 */
	    public static String formatJson(String jsonStr) {
	        if (null == jsonStr || "".equals(jsonStr)) return "";
	        StringBuilder sb = new StringBuilder();
	        char last = '\0';
	        char current = '\0';
	        int indent = 0;
	        for (int i = 0; i < jsonStr.length(); i++) {
	            last = current;
	            current = jsonStr.charAt(i);
	            switch (current) {
	                case '{':
	                case '[':
	                    sb.append(current);
	                    sb.append('\n');
	                    indent++;
	                    addIndentBlank(sb, indent);
	                    break;
	                case '}':
	                case ']':
	                    sb.append('\n');
	                    indent--;
	                    addIndentBlank(sb, indent);
	                    sb.append(current);
	                    break;
	                case ',':
	                    sb.append(current);
	                    if (last != '\\') {
	                        sb.append('\n');
	                        addIndentBlank(sb, indent);
	                    }
	                    break;
	                default:
	                    sb.append(current);
	            }
	        }

	        return sb.toString();
	    }

	    /**
	     * 添加space
	     * @param sb
	     * @param indent
	     * @author   lizhgb
	     * @Date   2015-10-14 上午10:38:04
	     */
	    private static void addIndentBlank(StringBuilder sb, int indent) {
	        for (int i = 0; i < indent; i++) {
	            sb.append('\t');
	        }
	    }
	    public static void main(String[] args) {
			String json = "{\"channelDate\":\"20161011\",\"hf_user_id\":\"\",\"bank_name\":\"工商银行\",\"schdcd\":\"210000\",\"drawdn_amt\":2900.00,\"channelSeq\":\"T200000720161011000000238475\",\"id_type\":\"0\",\"consume_mode\":\"02\",\"prod_cd\":\"sanbao\",\"systemId\":\"4000002\",\"client_user_id\":\"140302196306281237\",\"per_name\":\"阳泉市乐语冠芝霖通讯设备有限公司\",\"severId\":\"2000007\",\"channelCode\":\"0007\",\"mrty_dt\":\"\",\"int_vari\":\"\",\"prcscd\":\"loanpy\",\"apsvtp\":\"\",\"card_no\":\"0502122119024857203\",\"bank_code\":\"102100099996\",\"credit_no\":\"201610111243503874\",\"service_version\":\"V1.0.0\",\"apply_no\":\"T200000720161011000000238475\",\"tranInfo\":\"\",\"term\":10,\"channelTime\":\"134813136\",\"reserve\":\"\",\"id_no\":\"140302196306281237\"}";
			System.out.println(formatJson(json));
			
		}
	}
