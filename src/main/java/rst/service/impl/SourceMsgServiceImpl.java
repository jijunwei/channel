package rst.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rst.dao.SourceMsgMapper;
import rst.service.SourceMsgService;
import rst.tcp.model.route.SourceMsg;
import rst.util.SpringUtil;

@Service("sourceMsgService")
public class SourceMsgServiceImpl implements SourceMsgService {
    @Autowired
    SourceMsgMapper sourceMsgMapper;
    
    @Override
    public SourceMsg getSourceMsgResultByChannelSeq(String channelSeq) {
        return sourceMsgMapper.querySourceMsgById(channelSeq);
    }
    @Override
    public int addSourceMsgInfodb(SourceMsg sourceMsg){

        return sourceMsgMapper.add(sourceMsg);
    }
    @Override
    public int addSourceMsgString(String sourceMsgString){
        return sourceMsgMapper.addSourceMsgString(sourceMsgString);
    }
    @Override
    public int updateSourceMsgBeanInfodb(SourceMsg sourceMsg,String result){
        return sourceMsgMapper.updateSourceMsgBeanSeq(sourceMsg,result);
    }
    @Override
    public int updateSourceMsgString(SourceMsg sourceMsg,String result){
        return sourceMsgMapper.updateSourceMsgString(sourceMsg,result);
    }
}
