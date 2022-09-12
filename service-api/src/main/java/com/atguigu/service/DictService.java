package com.atguigu.service;

        import entity.Dict;

        import java.util.List;
        import java.util.Map;

public interface DictService {
    List<Map<String,Object>> findZnodes(Long id);

    /**
     * 根据编码获取子节点数据列表
     * @param dictCode
     * @return
     */
    List<Dict> findListByDictCode(String dictCode);

    List<Dict> findListByParentId(Long parentId);

    String getNameById(Long houseTypeId);
}
