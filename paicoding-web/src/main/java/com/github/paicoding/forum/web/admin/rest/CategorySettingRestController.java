package com.github.paicoding.forum.web.admin.rest;

import com.github.paicoding.forum.api.model.enums.PushStatusEnum;
import com.github.paicoding.forum.api.model.vo.PageVo;
import com.github.paicoding.forum.api.model.vo.ResVo;
import com.github.paicoding.forum.api.model.vo.article.CategoryReq;
import com.github.paicoding.forum.api.model.vo.article.SearchCategoryReq;
import com.github.paicoding.forum.api.model.vo.article.dto.CategoryDTO;
import com.github.paicoding.forum.api.model.vo.constants.StatusEnum;
import com.github.paicoding.forum.core.permission.Permission;
import com.github.paicoding.forum.core.permission.UserRole;
import com.github.paicoding.forum.service.article.service.CategorySettingService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 分类后台
 * */
@RestController
@Permission(role = UserRole.LOGIN)
@Api(value = "文章类目管理控制器", tags = "类目管理")
@RequestMapping(path = {"api/admin/category/", "admin/category/"})
public class CategorySettingRestController {

    @Autowired
    private CategorySettingService categorySettingService;


    @Permission(role = UserRole.ADMIN)
    @PostMapping(path = "save")
    public ResVo<String> save(@RequestBody CategoryReq req) {return null;
    }


    @Permission(role = UserRole.ADMIN)
    @GetMapping(path = "delete")
    public ResVo<String> delete(@RequestParam(name = "categoryId") Integer categoryId) {
        return null;
    }


    @Permission(role = UserRole.ADMIN)
    @GetMapping(path = "operate")
    public ResVo<String> operate(@RequestParam(name = "categoryId") Integer categoryId,
                                 @RequestParam(name = "pushStatus") Integer pushStatus) {
                                    return null;
    }


    @PostMapping(path = "list")
    public ResVo<PageVo<CategoryDTO>> list(@RequestBody SearchCategoryReq req) {
        return null;
    }
}
