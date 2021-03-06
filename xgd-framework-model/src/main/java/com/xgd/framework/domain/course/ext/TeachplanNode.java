package com.xgd.framework.domain.course.ext;

import com.xgd.framework.domain.course.Teachplan;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Created by admin on 2018/2/7.
 * 树形结构类型
 */
@Data
@ToString
public class TeachplanNode extends Teachplan {

    List<TeachplanNode> children;

    // 媒资文件id
    String mediaId;
    // 媒资文件原始名称
    String mediaFileoriginname;


}
