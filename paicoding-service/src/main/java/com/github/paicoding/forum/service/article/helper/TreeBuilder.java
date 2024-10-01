package com.github.paicoding.forum.service.article.helper;

import com.github.paicoding.forum.api.model.vo.article.dto.ColumnArticleGroupDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/** */
public class TreeBuilder {

    public static <T> List<T> buildTree(List<T> list
            , Function<T, Long> idKey
            , Function<T, Long> pidKey
            , Function<T, Integer> sortFunc
            , Function<T, List<T>> childGetFunc
            , BiConsumer<T, List<T>> updateChildFunc
    ) { return null; }

    /**
     * 递归对所有节点的子节点按section排序
     *
     * @param nodes 节点集合
     */
    private static <T> void sortChildrenBySection(Collection<T> nodes
            , Function<T, Integer> sortFunc
            , Function<T, List<T>> childGetFunc) {}

    /**
     * 将列表转换为树结构
     *
     * @param list 原始列表数据
     * @return 树结构的根节点列表
     */
    public static List<ColumnArticleGroupDTO> buildTree(List<ColumnArticleGroupDTO> list) { return null; }

    /**
     * 递归对所有节点的子节点按section排序
     *
     * @param nodes 节点集合
     */
    private static void sortChildrenBySection(Collection<ColumnArticleGroupDTO> nodes) {}
}