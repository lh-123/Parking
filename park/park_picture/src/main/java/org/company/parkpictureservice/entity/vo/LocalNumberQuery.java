package org.company.parkpictureservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LocalNumberQuery {
    @ApiModelProperty(value = "地点，模糊查询")
    private String place;

    @ApiModelProperty(value = "距离当前地点距离")
    private String  distance;

    @ApiModelProperty(value = "免费　1免费 0收费")
    private Integer isFree;

    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin;

    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;
}
