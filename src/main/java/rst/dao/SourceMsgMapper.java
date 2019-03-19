package rst.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.security.access.method.P;
import rst.tcp.model.route.SourceMsg;

@Mapper
@CacheConfig(cacheNames = "sourceMsg")
public interface SourceMsgMapper {


    @Delete("delete from sourcemsg where id=#{id}")
    void deleteSourceMsgById(int id);
    @Update("update sourcemsg set result=#{result} where msg=#{sourcemsg.msg}")
    int updateSourceMsgString(@Param("sourcemsg") SourceMsg sourceMsg, String result);

    @Update("update sourcemsgbean set result=#{result} where channelSeq=#{sourcemsg.channelSeq}")
     int updateSourceMsgBeanSeq(@Param("sourcemsg") SourceMsg sourceMsg, @Param("result") String result);

    @Update("update sourcemsg set result=#{result} where id=#{id}")
    void updateSourceMsgBySeq(int id, String result);

    @Update("update sourcemsgbean set result=#{result} where id=#{id}")
    void updateSourceMsgBeanById(int id, String result);

    @Insert("insert into sourcemsg(msg,material,result) "
            + " VALUES(#{msg},#{material},#{result})")
    int addSourceMsgString(String sourceMsg);
    @Insert("insert into sourcemsgbean(channelDate,channelTime,channelSeq,channelCode,msg,material,result) "
            + " VALUES(#{channelDate},#{channelTime},#{channelSeq},#{channelCode},#{msg},#{material},#{result})")
    int add(SourceMsg sourceMsg);
    @Select("select * from sourcemsgbean where channelSeq=#{channelSeq}")
    @Results(id = "sourcemsgbean", value = {
            @Result(property = "channelDate", column = "channelDate", javaType = String.class),
            @Result(property = "channelTime", column = "channelTime", javaType = String.class),
            @Result(property = "channelSeq", column = "channelSeq", javaType = String.class),
            @Result(property = "channelCode", column = "channelCode", javaType = String.class),
            @Result(property = "msg", column = "msg", javaType = String.class),
            @Result(property = "material", column = "material", javaType = String.class),
            @Result(property = "result", column = "result", javaType = String.class)
    })
    SourceMsg querySourceMsgById(String  channelSeq);
    @Select("select * from sourcemsg where id=#{id}")
    @Results(id = "sourcemsg", value = {
            @Result(property = "msg", column = "msg", javaType = String.class),
            @Result(property = "material", column = "material", javaType = String.class),
            @Result(property = "result", column = "result", javaType = String.class)
    })
    SourceMsg querySourceMsgStringById(int id);
}
