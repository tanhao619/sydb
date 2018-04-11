package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.advertisement.Advertisement;
import com.cdyoue.oddJobs.dto.advertisement.advertisementInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/5/10.
 */

public interface AdvertisementService {
    Advertisement getAdvertisement(String view, Integer id);

    void updateAdvertisement(String view, Integer id, advertisementInfo advertisement);

    List<Advertisement> findPageAll(String view);
}
