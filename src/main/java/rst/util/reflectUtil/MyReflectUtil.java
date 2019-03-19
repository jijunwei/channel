package rst.util.reflectUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;



public class MyReflectUtil {
	/**
	 * 取出bean 属性 、是否必填、顺序
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static Map<Integer, Object> getBeanRequiredAndOrder(Object obj)
			throws Exception {
		
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		Class<?> cls = obj.getClass();
		Field fields[] = cls.getDeclaredFields();
		for (Field field : fields) {
			MyAnnotation meta = field.getAnnotation(MyAnnotation.class);
			if (meta != null) {
				Object str[] = new Object[2];
				str[0] = field.getName();
				str[1] = meta.length();
				map.put(meta.order(), str);
			}
		}
		return map;
	}

	/**
	 * 取出bean 属性和值
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static Map<Object, Object> getFileValue(Object obj) throws Exception {
		Map<Object, Object> map = new HashMap<Object, Object>();
		Class<?> cls = obj.getClass();
		Method methods[] = cls.getDeclaredMethods();
		Field fields[] = cls.getDeclaredFields();

		for (Field field : fields) {

			String fldtype = field.getType().getSimpleName();
			String getMetName = pareGetName(field.getName(), fldtype);
			String result = "";
			if (!checkMethod(methods, getMetName)) {
				continue;
			}
			Method method = cls.getMethod(getMetName, null);
			Object object = method.invoke(obj, new Object[] {});
			if (null != object) {
				if (fldtype.equals("Date")) {
					result = fmlDate((Date) object);
				}
				result = String.valueOf(object);
			}
			map.put(field.getName(), result);
		}
		return map;
	}

	/**
	 * 设置bean 属性值
	 * 
	 * @param map
	 * @param bean
	 * @throws Exception
	 */
	public static Object setFieldValue(Map<Object, Object> map, Object bean) throws Exception {
		Class<?> cls = bean.getClass();
		Method methods[] = cls.getDeclaredMethods();
		Field fields[] = cls.getDeclaredFields();

		for (Field field : fields) {
			String fldtype = field.getType().getSimpleName();
			String fldSetName = field.getName();
			String setMethod = pareSetName(fldSetName);
			if (!checkMethod(methods, setMethod)) {
				continue;
			}
			Object value = map.get(fldSetName);
			if (value == null) {
				continue;
			}
			Method method = cls.getMethod(setMethod, field.getType());

			if (null != value) {
				if ("String".equals(fldtype)) {
					method.invoke(bean, (String) value);
				} 
			}
		}
		return bean;
	}

	/**
	 * 拼接某属性get 方法
	 * 
	 * @param fldname
	 * @return
	 */
	public static String pareGetName(String fldname, String fldtype) {
		if (null == fldname || "".equals(fldname)) {
			return null;
		}
		String pro = null;
		if ("boolean".equals(fldtype)) {
			pro = "is" + fldname.substring(0, 1).toUpperCase()+ fldname.substring(1);
		} else {
			pro = "get" + fldname.substring(0, 1).toUpperCase()+ fldname.substring(1);
		}
		return pro;
	}

	/**
	 * 拼接某属性set 方法
	 * 
	 * @param fldname
	 * @return
	 */
	public static String pareSetName(String fldname) {
		if (null == fldname || "".equals(fldname)) {
			return null;
		}
		String pro = "set" + fldname.substring(0, 1).toUpperCase()+ fldname.substring(1);
		return pro;
	}

	/**
	 * 判断该方法是否存在
	 * 
	 * @param methods
	 * @param met
	 * @return
	 */
	public static boolean checkMethod(Method methods[], String met) {
		if (null != methods) {
			for (Method method : methods) {
				if (met.equals(method.getName())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 把date 类转换成string
	 * 
	 * @param date
	 * @return
	 */
	public static String fmlDate(Date date) {
		if (null != date) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
			return sdf.format(date);
		}
		return null;
	}

	/**
	 * object 转 bigdecimal
	 * 
	 * @param value
	 * @return
	 */
	public static BigDecimal getBigDecimal(Object value) {
		BigDecimal ret = null;
		try {
			if (value != null) {
				if (value instanceof BigDecimal) {
					ret = (BigDecimal) value;
				} else if (value instanceof String) {
					ret = new BigDecimal((String) value);
				} else if (value instanceof BigInteger) {
					ret = new BigDecimal((BigInteger) value);
				} else if (value instanceof Number) {
					ret = new BigDecimal(((Number) value).doubleValue());
				} else {
					throw new ClassCastException("Not possible to coerce [" + value+ "] from class " + value.getClass()+ " into a BigDecimal.");
				}
			}
		} catch (Exception e) {
			throw new ClassCastException("类型转换错误！");
		}
		return ret;
	}

	public static void main(String[] args) {
		try {
			MyBean bean = new MyBean();
			
			Map<Integer, Object> mm = getBeanRequiredAndOrder(bean);
			
			Set<Integer> keySet = mm.keySet();
			for(Integer key : keySet){
				System.out.println(key+"--"+mm.get(key));
				Object[] strs = (Object[]) mm.get(key);
				System.out.println(strs[0]);
				System.out.println(strs[1]);
			}

			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("age", "27");
			map.put("name", "michael");
			
			MyReflectUtil.setFieldValue(map, bean);
			Map<Object, Object> m = MyReflectUtil.getFileValue(bean);
			System.out.println(m);
 
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
