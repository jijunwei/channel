package rst.tcp.model;

import lombok.Data;

@Data
public class QueryMsg {
    String channel;
    String jinJianSeq;
    /*String datepicker_begin;
    String datepicker_end;
    int recordNum;*/
}
