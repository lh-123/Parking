package org.company.parklocalnumberservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.company.commonutils.R;
import org.company.parklocalnumberservice.entity.LocalNumber;
import org.company.parklocalnumberservice.entity.vo.LocalNumberQuery;
import org.company.parklocalnumberservice.service.LocalNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * <p>
 * 停车场地点 前端控制器
 * </p>
 *
 * @author lih
 * @since 2020-08-23
 */
@RestController
@RequestMapping("/parklocalnumberservice/local-number")
@CrossOrigin
public class LocalNumberController {
    @Autowired
    private LocalNumberService localNumberService;

    @ApiOperation(value = "所有停车地点列表")
    @GetMapping("findAll")
    public R findAllLocal() {
        //调用service的方法实现查询所有的操作
        List<LocalNumber> list =  localNumberService.list(null);
        return R.ok().data("items",list);
    }

    @ApiOperation(value = "逻辑删除地点")
    @DeleteMapping("{id}")
    public R removeLocal(@ApiParam(name = "id", value = "地点ID", required = true)
                         @PathVariable String id) {
        boolean flag = localNumberService.removeById(id);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @GetMapping("pageLocal/{current}/{limit}")
    public R pageListLocal(@PathVariable long current,
                           @PathVariable long limit) {
        Page<LocalNumber> pageLocal = new Page<>(current,limit);

        localNumberService.page(pageLocal,null);

        long total = pageLocal.getTotal();//总记录数
        List<LocalNumber> records = pageLocal.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    @PostMapping("pageLocalCondition/{current}/{limit}")
    public R pageLocalCondition(@PathVariable long current,@PathVariable long limit,
                                @RequestBody(required = false) LocalNumberQuery localQuery) {
        Page<LocalNumber> pageLocal = new Page<>(current,limit);
        QueryWrapper<LocalNumber> wrapper = new QueryWrapper<>();
        String place = localQuery.getPlace();
        String distance= localQuery.getDistance();
        String free=localQuery.getIsFree();
        String begin = localQuery.getBegin();
        String end = localQuery.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(place)) {
            //构建条件
            wrapper.like("place",place);
        }
        if(!StringUtils.isEmpty(distance)) {
            wrapper.like("distance",distance);
        }
        if(!StringUtils.isEmpty(free)) {
            wrapper.eq("free",free);
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
        localNumberService.page(pageLocal,wrapper);

        long total = pageLocal.getTotal();//总记录数
        List<LocalNumber> records =pageLocal.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    @PostMapping("addLocal")
    public R addLocal(@RequestBody LocalNumber localNumber) {
        boolean save = localNumberService.save(localNumber);
        if(save) {
            return R.ok();
        } else {
            return R.error();
        }
    }


    @GetMapping("getLocal/{id}")
    public R getLocal(@PathVariable String id) {
        LocalNumber localNumber = localNumberService.getById(id);
        return R.ok().data("form",localNumber);
    }

    //修改功能
    @PostMapping("updateLocal")
    public R updateLocal(@RequestBody LocalNumber localNumber) {
        boolean flag = localNumberService.updateById(localNumber);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

}

