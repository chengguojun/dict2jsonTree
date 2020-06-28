package com.taiji.zhzf.dict2json.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeMenuNode implements Serializable {
    private String prop;
    private String parentId;
    private String label;
    private List<TreeMenuNode> children;

}
