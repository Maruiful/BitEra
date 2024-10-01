package com.github.paicoding.forum.web.admin.rest;

import com.github.paicoding.forum.api.model.enums.PushStatusEnum;
import com.github.paicoding.forum.api.model.vo.PageVo;
import com.github.paicoding.forum.api.model.vo.ResVo;
import com.github.paicoding.forum.api.model.vo.article.ColumnArticleGroupReq;
import com.github.paicoding.forum.api.model.vo.article.ColumnArticleReq;
import com.github.paicoding.forum.api.model.vo.article.ColumnReq;
import com.github.paicoding.forum.api.model.vo.article.MoveColumnArticleOrGroupReq;
import com.github.paicoding.forum.api.model.vo.article.SearchColumnArticleReq;
import com.github.paicoding.forum.api.model.vo.article.SearchColumnReq;
import com.github.paicoding.forum.api.model.vo.article.SortColumnArticleByIDReq;
import com.github.paicoding.forum.api.model.vo.article.SortColumnArticleReq;
import com.github.paicoding.forum.api.model.vo.article.dto.ColumnArticleDTO;
import com.github.paicoding.forum.api.model.vo.article.dto.ColumnArticleGroupDTO;
import com.github.paicoding.forum.api.model.vo.article.dto.ColumnDTO;
import com.github.paicoding.forum.api.model.vo.article.dto.SimpleColumnDTO;
import com.github.paicoding.forum.api.model.vo.constants.StatusEnum;
import com.github.paicoding.forum.core.permission.Permission;
import com.github.paicoding.forum.core.permission.UserRole;
import com.github.paicoding.forum.service.article.repository.entity.ArticleDO;
import com.github.paicoding.forum.service.article.service.ArticleReadService;
import com.github.paicoding.forum.service.article.service.ColumnSettingService;
import com.github.paicoding.forum.service.image.service.ImageService;
import com.github.paicoding.forum.web.front.search.vo.SearchColumnVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 专栏后台
 * */
@RestController
@Slf4j
@Permission(role = UserRole.LOGIN)
@Api(value = "专栏及专栏文章管理控制器", tags = "专栏管理")
@RequestMapping(path = { "api/admin/column/", "admin/column/" })
public class ColumnSettingRestController {

    @Autowired
    private ColumnSettingService columnSettingService;

    @Autowired
    private ArticleReadService articleReadService;

    @Autowired
    private ImageService imageService;

    @Permission(role = UserRole.ADMIN)
    @PostMapping(path = "saveColumn")
    public ResVo<String> saveColumn(@RequestBody ColumnReq req) {
        return null;
    }

    /**
     * 维护专栏的分组情况
     *
     * @param req
     * @return
     */
    @Permission(role = UserRole.ADMIN)
    @PostMapping(path = "saveColumnGroup")
    public ResVo<Boolean> saveColumnArticleGroup(@RequestBody ColumnArticleGroupReq req) {
        return null;
    }

    @Permission(role = UserRole.ADMIN)
    @PostMapping(path = "saveColumnArticle")
    public ResVo<String> saveColumnArticle(@RequestBody ColumnArticleReq req) {
        return null;
    }

    @Permission(role = UserRole.ADMIN)
    @GetMapping(path = "deleteColumn")
    public ResVo<String> deleteColumn(@RequestParam(name = "columnId") Long columnId) {
        return null;
    }

    /**
     * 删除专栏文章分组
     *
     * @param groupId 分组id
     * @return
     */
    @Permission(role = UserRole.ADMIN)
    @GetMapping(path = "deleteColumnGroup")
    public ResVo<Boolean> deleteColumnGroup(@RequestParam(name = "groupId") Long groupId) {
        return null;
    }

    @Permission(role = UserRole.ADMIN)
    @GetMapping(path = "deleteColumnArticle")
    public ResVo<String> deleteColumnArticle(@RequestParam(name = "id") Long id) {
        return null;
    }

    @Permission(role = UserRole.ADMIN)
    @PostMapping(path = "sortColumnArticleApi")
    public ResVo<String> sortColumnArticleApi(@RequestBody SortColumnArticleReq req) {
        return null;
    }

    @Permission(role = UserRole.ADMIN)
    @PostMapping(path = "sortColumnArticleByIDApi")
    public ResVo<String> sortColumnArticleByIDApi(@RequestBody SortColumnArticleByIDReq req) {
        return null;
    }

    /**
     * 移动专栏中教程或者分组的位置
     * 
     * @param req 请求参数
     * @return
     */
    @Permission(role = UserRole.ADMIN)
    @PostMapping(path = "moveColumnArticleOrGroup")
    public ResVo<Boolean> moveColumnArticleOrGroup(@RequestBody MoveColumnArticleOrGroupReq req) {
        return null;
    }

    @ApiOperation("获取教程列表")
    @PostMapping(path = "list")
    public ResVo<PageVo<ColumnDTO>> list(@RequestBody SearchColumnReq req) {
        return null;
    }

    @ApiOperation("获取教程分组列表")
    @GetMapping(path = "listGroups")
    public ResVo<List<ColumnArticleGroupDTO>> listGroups(@RequestParam("columnId") Long columnId) {
        return null;
    }

    /**
     * 获取教程配套的文章列表
     * <p>
     * 请求参数有教程名、文章名
     * 返回教程配套的文章列表
     *
     * @return
     */
    @PostMapping(path = "listColumnArticle")
    public ResVo<PageVo<ColumnArticleDTO>> listColumnArticle(@RequestBody SearchColumnArticleReq req) {
        return null;
    }

    /**
     * 教程的文章，根据分组进行汇聚展示
     *
     * @param columnId
     * @return
     */
    @GetMapping(path = "listColumnByGroup")
    public ResVo<List<ColumnArticleGroupDTO>> listColumnArticlesByGroup(@RequestParam("columnId") Long columnId) {
        return null;
    }

    @ApiOperation("专栏搜索")
    @GetMapping(path = "query")
    public ResVo<SearchColumnVo> query(@RequestParam(name = "key", required = false) String key) {
        return null;
    }
}