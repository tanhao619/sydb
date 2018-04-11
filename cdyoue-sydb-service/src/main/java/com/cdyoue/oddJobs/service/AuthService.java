package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.Reason;
import com.cdyoue.oddJobs.dto.oauth.ApplyInfoDto;
import com.cdyoue.oddJobs.dto.oauth.RealNameApplyInfo;
import com.cdyoue.oddJobs.en.ApplyTypeEnum;

import java.util.List;

/**
 * Created by dengshaojun on 2017/5/9.
 */
public interface AuthService {

    void saveRealNameInfo(Integer userid, RealNameApplyInfo realNameApplyInfo, ApplyTypeEnum type);

    void authApprove(Integer id, Reason reason);

    void authReject(Integer id, Reason reason);

    void updateRealNameInfo(Integer id, RealNameApplyInfo realNameApplyInfo);

    ApplyInfoDto getApplyInfo(String type);

    List<ApplyInfoDto> getIdentificationInfo(Integer id);

    void updLook(Integer id);
}
