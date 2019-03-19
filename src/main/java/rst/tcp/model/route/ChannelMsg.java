package rst.tcp.model.route;

import lombok.Data;
import rst.tcp.model.Student;
import rst.tcp.model.XmlElementAnno;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

@Data

public class ChannelMsg implements Serializable {

    //渠道系统标识:合作方系统标识
     String channelCode;
     String channel;
     String channelDate;
     String channelTime;
     String channelSeq;
     Student student;


    List<String> material;



}