package com.ai.entity.vo.request;

import com.ai.entity.dto.Alert;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class AlertReqVO {
    String info;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date ddl;

    public Alert toAlert(String uid, int seq) {
        Alert alert = new Alert();
        alert.setUid(uid);
        alert.setInfo(info);
        alert.setDdl(ddl);
        alert.setState(true);
        alert.setSeq(seq);
        return alert;
    }
}
