package com.example.controller;

import com.example.common.Result;
import com.example.entity.Admin;
import com.example.entity.Hotel;
import com.example.service.HotelService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 酒店前端操作接口
 **/
@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Resource
    private HotelService hotelService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Hotel hotel) {
        hotelService.add(hotel);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Hotel hotel) {
        hotelService.update(hotel);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        hotelService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/selectById")
    public Result selectById(@RequestParam Integer id) {
        Hotel hotel = hotelService.selectById(id);
        return Result.success(hotel);
    }

    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        hotelService.deleteBatch(ids);
        return Result.success();
    }

    @GetMapping("/selectByName")
    public Result selectByName(@RequestParam String name) {
        List<Hotel> list = hotelService.selectByName(name);
        return Result.success(list);
    }

    @GetMapping("/selectAll")
    public Result selectAll(Hotel hotel) {
        List<Hotel> list = hotelService.selectAll(hotel);
        return Result.success(list);
    }
    @GetMapping("/selectAllOK")
    public Result selectAllOK() {
        List<Hotel> list = hotelService.selectAllOK();
        return Result.success(list);
    }


    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Hotel hotel,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Hotel> page = hotelService.selectPage(hotel, pageNum, pageSize);
        return Result.success(page);
    }

}
