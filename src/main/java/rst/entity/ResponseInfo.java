package rst.entity;

import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class ResponseInfo {
public String status;
public String result;

public String getResult() {
	return result;
}
public void setResult(String result) {
	this.result = result;
}
HashMap<String,String> attribute=new HashMap<String,String>();
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public HashMap<String, String> getAttribute() {
	return attribute;
}
public void setAttribute(HashMap<String, String> attribute) {
	this.attribute = attribute;
}



}
