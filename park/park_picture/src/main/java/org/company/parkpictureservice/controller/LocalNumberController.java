package org.company.parkpictureservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.company.commonutils.R;
import org.company.parkpictureservice.entity.LocalNumber;
import org.company.parkpictureservice.entity.vo.LocalNumberQuery;
import org.company.parkpictureservice.service.LocalNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 停车场地点 前端控制器
 * </p>
 *
 * @author lih
 * @since 2020-07-22
 */
@RestController
@RequestMapping("/parkpictureservice/local-number")
@CrossOrigin
public class LocalNumberController {
    @Autowired
    private LocalNumberService localNumberService;
    //1 查询讲师表所有数据
    //rest风格
    @ApiOperation(value = "停车场列表")
    @GetMapping("findAll")
    public R findAllTeacher() {
        //调用service的方法实现查询所有的操作
        List<LocalNumber> list = localNumberService.list(null);
        return R.ok().data("items",list);
    }

    //2 逻辑删除讲师的方法
    @ApiOperation(value = "逻辑删除地点")
    @DeleteMapping("{id}")
    public R removeTeacher(@ApiParam(name = "id", value = "停车场ID", required = true)
                           @PathVariable String id) {
        boolean flag = localNumberService.removeById(id);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @GetMapping("pageLocal/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current,
                             @PathVariable long limit) {
        Page<LocalNumber> pageTeacher = new Page<>(current,limit);

        localNumberService.page(pageTeacher,null);

        long total = pageTeacher.getTotal();//总记录数
        List<LocalNumber> records = pageTeacher.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current,@PathVariable long limit,
                                  @RequestBody(required = false) LocalNumberQuery localNumberQuery) {
        Page<LocalNumber> pageTeacher = new Page<>(current,limit);
        QueryWrapper<LocalNumber> wrapper = new QueryWrapper<>();
        String place = localNumberQuery.getPlace();
        String distance = localNumberQuery.getDistance();
        Integer isfree =localNumberQuery.getIsFree();
        String begin = localNumberQuery.getBegin();
        String end = localNumberQuery.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(place)) {
            //构建条件
            wrapper.like("place",place);
        }
        if(!StringUtils.isEmpty(distance)) {
            wrapper.eq("distance",distance);
        }if(!StringUtils.isEmpty(isfree)) {
            wrapper.eq("isfree",isfree);
        }
        if(!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create",end);
        }

        //排序
        wrapper.orderByDesc("gmt_create");

        //调用方法实现条件查询分页
        localNumberService.page(pageTeacher,wrapper);

        long total = pageTeacher.getTotal();//总记录数
        List<LocalNumber> records = pageTeacher.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    @PostMapping("addLocal")
    public R addTeacher(@RequestBody LocalNumber localNumber) {
        boolean save = localNumberService.save(localNumber);
        if(save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //根据讲师id进行查询
    @GetMapping("getLocal/{id}")
    public R getTeacher(@PathVariable String id) {
        LocalNumber localNumber=localNumberService.getById(id);
        return R.ok().data("localnumber",localNumber);
    }

    //讲师修改功能
    @PostMapping("updateLocal")
    public R updateTeacher(@RequestBody LocalNumber localNumber) {
        boolean flag = localNumberService.updateById(localNumber);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

