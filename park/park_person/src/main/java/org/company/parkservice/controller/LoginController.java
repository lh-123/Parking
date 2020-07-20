package org.company.parkservice.controller;
import org.company.commonutils.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
    public class LoginController {

        @PostMapping("login")
        public R login(){
            return R.ok().data("taken","admin");

        }

        @GetMapping("info")
        public R info(){
            return R.ok().data("roles","[lihang]").data("name","lihang").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        }
    }
