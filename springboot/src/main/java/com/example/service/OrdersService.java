package com.example.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.common.enums.OrdersEnum;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Orders;
import com.example.entity.Type;
import com.example.exception.CustomException;
import com.example.mapper.OrdersMapper;
import com.example.mapper.TypeMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 订单信息表业务处理
 **/
@Service
public class OrdersService {

    private static final Logger log = LoggerFactory.getLogger(OrdersService.class);

    @Resource
    private OrdersMapper ordersMapper;
    @Resource
    private TypeMapper typeMapper;

    /**
     * 新增
     */
    public void add(Orders orders) {

        if (ObjectUtil.isEmpty(orders.getInTime()) || ObjectUtil.isEmpty(orders.getOutTime())) {
            throw new CustomException(ResultCodeEnum.PARAM_LOST_ERROR);
        }

        orders.setStatus(OrdersEnum.STATUS_CHECKING.status);
        orders.setTime(DateUtil.now());
        orders.setOrderId(DateUtil.format(new Date(), "yyyyMMddHHmmss"));

        try {
            // 根据入住时间和离开时间 计算天数
            Date start = new SimpleDateFormat("yyyy-MM-dd").parse(orders.getInTime());
            Date end = new SimpleDateFormat("yyyy-MM-dd").parse(orders.getOutTime());
            // 比较两个时间的大小
            int result = start.compareTo(end);
            if (result >= 0) {
                throw new CustomException(ResultCodeEnum.TIME_CHECK_ERROR);
            }
            Long dayNum = getDayNum(orders.getInTime(), orders.getOutTime());
            orders.setDays(dayNum);

            Type type = typeMapper.selectById(orders.getTypeId());
            orders.setPrice(type.getPrice() * dayNum);

            ordersMapper.insert(orders);
        } catch (CustomException e) {
            throw new CustomException(ResultCodeEnum.TIME_CHECK_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Long getDayNum(String inTime, String outTime) throws ParseException {
        DateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date star = dft.parse(inTime);//开始时间
        Date endDay = dft.parse(outTime);//结束时间
        Long starTime = star.getTime();
        Long endTime = endDay.getTime();
        long num = endTime-starTime;//时间戳相差的毫秒数
        return num/24/60/60/1000;
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        ordersMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            ordersMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Orders orders) {
        ordersMapper.updateById(orders);
    }

    /**
     * 根据ID查询
     */
    public Orders selectById(Integer id) {
        return ordersMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Orders> selectAll(Orders orders) {
        return ordersMapper.selectAll(orders);
    }

    /**
     * 分页查询
     */
    public PageInfo<Orders> selectPage(Orders orders, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.HOTEL.name().equals(currentUser.getRole())) {
            orders.setHotelId(currentUser.getId());
        }
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            orders.setUserId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Orders> list = ordersMapper.selectAll(orders);
        return PageInfo.of(list);
    }

    public List<Orders> selectByUserId(Integer id) {
        Orders orders = new Orders();
        orders.setUserId(id);
        return ordersMapper.selectAll(orders);
    }

    public List<Orders> selectByCheckingAndHotelId(Integer id) {
        Orders orders = new Orders();
        orders.setHotelId(id);
        orders.setStatus(OrdersEnum.STATUS_CHECKING.status);
        return ordersMapper.selectAll(orders);
    }
}