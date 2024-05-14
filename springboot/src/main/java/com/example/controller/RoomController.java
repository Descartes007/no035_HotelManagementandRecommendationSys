package com.example.controller;

import com.example.common.Result;
import com.example.entity.Room;
import com.example.service.RoomService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 客房信息表前端操作接口
 **/
@RestController
@RequestMapping("/room")
public class RoomController {

    @Resource
    private RoomService roomService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Room room) {
        roomService.add(room);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        roomService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        roomService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Room room) {
        roomService.updateById(room);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Room room = roomService.selectById(id);
        return Result.success(room);
    }

    @GetMapping("/selectByTypeId")
    public Result selectByTypeId(@RequestParam String orderId) {
        List<Room> room = roomService.selectByTypeId(orderId);
        return Result.success(room);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Room room ) {
        List<Room> list = roomService.selectAll(room);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Room room,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Room> page = roomService.selectPage(room, pageNum, pageSize);
        return Result.success(page);
    }



}