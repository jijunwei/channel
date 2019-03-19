package rst.util.reflectUtil;

public class MyBean {

	private String age;

	@MyAnnotation(order= 0,length = 100)
	private String name;


	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	 
	
}
