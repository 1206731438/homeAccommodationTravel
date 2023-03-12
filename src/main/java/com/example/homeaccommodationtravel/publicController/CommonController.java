package com.example.homeaccommodationtravel.publicController;

import com.example.homeaccommodationtravel.service.HomeService;
import com.example.homeaccommodationtravel.utils.ResultUtil;
import com.example.homeaccommodationtravel.vo.TypeNameList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private HomeService homeService;

    @PostMapping("/getTypeNameList")
    public ResultUtil getTypeNameList() {
        List<TypeNameList> typeNameLists = homeService.getTypeNameList();
        return ResultUtil.respinseSuccess("查询成功",typeNameLists);
    }
}
