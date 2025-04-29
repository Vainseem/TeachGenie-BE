package com.ai.service;

import com.ai.entity.dto.Alert;
import com.ai.entity.vo.request.AlertReqVO;
import com.ai.entity.vo.response.AlertResVO;
import com.ai.repo.alertRepo.AlertRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AlertService {
    @Resource
    AlertRepository alertRepository;

    public void addAlert(String uid, AlertReqVO alertReqVO) {
        Integer seq = alertRepository.findMaxSeqByUid(uid);
        if (seq == null) {
            seq = 0;
        }
        alertRepository.save(alertReqVO.toAlert(uid, seq + 1));
    }

    public String finishAlert(String uid, int seq) {
        Alert alert = alertRepository.findByUidAndSeq(uid, seq);
        if (alert == null) {
            return "该提醒本身不存在（你传了个空seq或不合法seq）";
        }
        alert.setState(false);
        alertRepository.save(alert);
        return null;
    }


    public AlertResVO getAlertHistory(String uid) {
        if (!alertRepository.existsByUid(uid)) {
            return new AlertResVO(uid,  List.of(), List.of(), List.of(), List.of());
        }
        List<Alert> alerts = alertRepository.findByUidOrderBySeq(uid);
        List<String> allInfo = alerts.stream().map(Alert::getInfo).toList();
        List<Date> allDdl = alerts.stream().map(Alert::getDdl).toList();
        List<Boolean> allState = alerts.stream().map(Alert::isState).toList();
        List<Integer> allSeq = alerts.stream().map(Alert::getSeq).toList();
        return new AlertResVO(uid, allInfo, allDdl, allState, allSeq);
    }
}
