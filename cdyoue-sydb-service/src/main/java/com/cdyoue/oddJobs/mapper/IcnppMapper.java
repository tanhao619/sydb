package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dto.icnpp.policy.PolicyBase;
import com.cdyoue.oddJobs.dto.icnpp.policy.PolicyDetail;
import com.cdyoue.oddJobs.dto.icnpp.policy.PolicySumary;
import com.cdyoue.oddJobs.entity.icnpp.PolicyEntity;
import com.cdyoue.oddJobs.utils.ImgCompressUtils;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017/5/8.
 */
@Component
public class IcnppMapper {
    private static final Logger logger = LoggerFactory.getLogger(IcnppMapper.class);


    @Value("${default.icnpp.file-address}")
    private String fileAddress;


    @Value("${default.commonUpload.server}")
    private String fileServerAddress;
    @Value("${default.commonUpload.fileAddress}")
    private String fileFloder;
    @Value("${default.commonUpload.driver}")
    private String dirverAddress;

    @Autowired
    private ImgCompressUtils imgCompressUtils;

    public PolicySumary policyEntityToPolicySumary(PolicyEntity pe) {
        PolicySumary ps = new PolicySumary();

        ps.setId(pe.getId());
        ps.setBase(this.policyEntityToPolicyBase(pe));
        ps.setLink("/H5/policyDetails.html?id=" + pe.getId());
        return ps;
    }

    public PolicyBase policyEntityToPolicyBase(PolicyEntity pe) {
        PolicyBase pb = new PolicyBase();
        pb.setOrganization(pe.getOrganization());
        pb.setPublishTime(pe.getIssuedTime());
        pb.setTitle(pe.getTitle());
        return pb;
    }

    public PolicyDetail policyEntityToPolicyDetail(PolicyEntity pe) {
        PolicyDetail pd = new PolicyDetail();
        PolicyBase pb = this.policyEntityToPolicyBase(pe);
        pd.setBase(pb);
        File folder = new File(new StringBuffer(fileAddress).append("/").append(pe.getId()).toString());
        if(folder.exists() && folder.isDirectory()){
            List<String> pds = Arrays.stream(folder.listFiles()).map(file ->this.getCompressUrls(file, pe.getId()))
                    .collect(Collectors.toList());
            pd.setContentAddress(pds);
        }
        return pd;
    }

    public String getCompressUrls(File file, String parentFolder) {
        StringBuffer suffix = new StringBuffer("/")
                .append(fileFloder)
                .append("/policy/")
                .append(parentFolder)
                .append("/");
        StringBuffer serverFullPath = new StringBuffer(fileServerAddress)
                .append(suffix);
        StringBuffer driverFullPath = new StringBuffer(dirverAddress)
                .append(suffix);
        String name = file.getName();
        File dest = new File(driverFullPath.append(name.substring(0, name.lastIndexOf(".")))
                .append("_mobile")
                .append(".")
                .append(name.substring(name.lastIndexOf(".") + 1))
                .toString());
        try {
            imgCompressUtils.compress(file, dest);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return serverFullPath.append(name).toString();
    }



}
