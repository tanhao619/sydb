package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dto.common.KeyValue;
import com.cdyoue.oddJobs.dto.ggfw.CommunitySummary;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luanyu on 2017/5/23.
 */
@Component
public class CommunityMapper {
    public CommunitySummary entityToDto(Object entity){
        CommunitySummary dto = new CommunitySummary();
        Object[] cells = (Object[]) entity;
        dto.setId((Integer) cells[0]);
        dto.setTitle((String) cells[1]);
        dto.setSummary((String) cells[2]);
        dto.setCoverImg((String) cells[3]);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(cells[4]!=null){
            String time = df.format(cells[4]);
            time = time.replace("-","/");
            dto.setPublishTime(time);
        }
        BigInteger tmpInt = (BigInteger) cells[5];
        dto.setType(tmpInt.intValue());


        List<KeyValue> list = new ArrayList<>();
        if(dto.getType()==2){
            KeyValue location = new KeyValue();
            location.setName("location");
            location.setValue((String)cells[6]);
            list.add(location);

            if(cells[7]!=null){
                KeyValue scale = new KeyValue();
                scale.setName("scale");
                scale.setValue(String.valueOf((Integer) cells[7]));
                list.add(scale);
            }
        }

        if(dto.getType()==1){
            KeyValue unit = new KeyValue();
            unit.setName("unit");
            unit.setValue((String)cells[8]);
            list.add(unit);
        }
        Byte approveStatus = (Byte) cells[9];
        dto.setApproveStatus(approveStatus.intValue());
        dto.setAddProperties(list);
        return  dto;

    }
}
