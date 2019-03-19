package rst.service;

import rst.tcp.model.route.SourceMsg;

public interface SourceMsgService {
    /**
     * 根据渠道交易号查询业务处理结果
     * @return
     */
    SourceMsg getSourceMsgResultByChannelSeq(String channelSeq);

    int addSourceMsgInfodb(SourceMsg sourceMsg);
    int addSourceMsgString(String sourceMsgString);

    int updateSourceMsgBeanInfodb(SourceMsg sourceMsg,String result);
    int updateSourceMsgString(SourceMsg sourceMsg,String result);


}
