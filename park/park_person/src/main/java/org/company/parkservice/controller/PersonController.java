package org.company.parkservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.company.commonutils.R;
import org.company.parkservice.entity.Person;
import org.company.parkservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author lih
 * @since 2020-07-20
 */
@RestController
@RequestMapping("/parkservice/person")
@CrossOrigin
public class PersonController {
    @Autowired
    private PersonService personService;
    //1 查询讲师表所有数据

    @ApiOperation(value = "所有用户列表")
    @GetMapping("findAll")
    public R findAllPerson() {
        //调用service的方法实现查询所有的操作
        List<Person> list = personService.list(null);
        return R.ok().data("items",list);
    }

    //2 逻辑删除
    @ApiOperation(value = "逻辑删除")
    @DeleteMapping("{id}")
    public R removePerson(@ApiParam(name = "id", value = "用户ID", required = true)
                           @PathVariable String id) {
        boolean flag = personService.removeById(id);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @PostMapping("addPerson")
    public R addPerson(@RequestBody Person person) {
        boolean save = personService.save(person);
        if(save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

}

