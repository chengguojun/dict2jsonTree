package com.taiji.zhzf.dict2json.service.impl;

import com.google.gson.Gson;
import com.taiji.zhzf.dict2json.bean.TreeMenuNode;
import com.taiji.zhzf.dict2json.dao.DictDao;
import com.taiji.zhzf.dict2json.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DictServiceImpl implements DictService {
    @Autowired
    private DictDao dictDao;

    @Override
    public Map<String, Object> findAll() {
        String tableName="ba_org";
        String pid = "ParentOrgId";
        String id = "OrgId";
        String name = "OrgName";

        List<Map<String, Object>> list = dictDao.findAll(tableName);
        // 根节点
        List<Map<String, Object>> rootList = list.stream().filter(s -> "".equals(s.get(pid)) || s.get(pid) == null).collect(Collectors.toList());
        List<Map<String, Object>> subList = list.stream().filter(s -> !"".equals(s.get(pid)) && s.get(pid) != null).collect(Collectors.toList());

        List<TreeMenuNode> treeNodeList = new ArrayList<>();
        List<TreeMenuNode> treeRootNodeList = new ArrayList<>();
        //--------------------------
        list2TreeObj(pid, id, name, subList, treeNodeList);
        list2TreeObj(pid, id, name, rootList, treeRootNodeList);
        //分组
        Map<String, List<TreeMenuNode>> collect = treeNodeList.stream().collect(Collectors.groupingBy(TreeMenuNode::getParentId));
        treeRootNodeList.forEach(t-> forEach(collect, t));
        Gson gson = new Gson();
        String s = gson.toJson(treeRootNodeList);
        System.out.println(s);
        return null;
    }
    private void list2TreeObj(String pid, String id, String name, List<Map<String, Object>> list, List<TreeMenuNode> treeNodeList) {
        list.forEach(t->{
            TreeMenuNode treeMenuNode = new TreeMenuNode();
            treeMenuNode.setProp(String.valueOf(t.get(id)));
            treeMenuNode.setParentId(String.valueOf(t.get(pid)));
            treeMenuNode.setLabel(String.valueOf(t.get(name)));
            treeNodeList.add(treeMenuNode);
        });
    }
    private static void forEach(Map<String, List<TreeMenuNode>> collect, TreeMenuNode treeMenuNode) {
        List<TreeMenuNode> treeMenuNodes = collect.get(treeMenuNode.getProp());
        if (treeMenuNodes != null) {
            treeMenuNode.setChildren(treeMenuNodes);
            treeMenuNode.getChildren().forEach(t -> forEach(collect, t));
        }
    }


}



