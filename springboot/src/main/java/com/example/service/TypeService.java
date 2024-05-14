package com.example.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.CalendarUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.*;
import com.example.exception.CustomException;
import com.example.mapper.*;
import com.example.utils.TokenUtils;
import com.example.utils.UserCF;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 房间类别表业务处理
 **/
@Service
public class TypeService {

    @Resource
    private RoomMapper roomMapper;
    @Resource
    private TypeMapper typeMapper;
    @Resource
    private OrdersMapper ordersMapper;
    @Resource
    private CollectMapper collectMapper;
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private HotelMapper hotelMapper;

    /**
     * 新增
     */
    public void add(Type type) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (ObjectUtil.isNull(currentUser)) {
            throw new CustomException(ResultCodeEnum.TOKEN_CHECK_ERROR);
        }
        type.setHotelId(currentUser.getId());
        typeMapper.insert(type);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        typeMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            typeMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Type type) {
        typeMapper.updateById(type);
    }

    /**
     * 根据ID查询
     */
    public Type selectById(Integer id) {
        return typeMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Type> selectAll(Type type) {
        return typeMapper.selectAll(type);
    }

    /**
     * 分页查询
     */
    public PageInfo<Type> selectPage(Type type, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (ObjectUtil.isNull(currentUser)) {
            throw new CustomException(ResultCodeEnum.TOKEN_CHECK_ERROR);
        }
        if (RoleEnum.HOTEL.name().equals(currentUser.getRole())) {
            type.setHotelId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Type> list = typeMapper.selectAll(type);
        return PageInfo.of(list);
    }

    public List<Type> selectByHotelId(Integer hotelId) {
        return typeMapper.selectByHotelId(hotelId);
    }

    public List<Type> recommend() {
        List<Collect> allCollects = collectMapper.selectAll(null);

        List<Orders> allOrders = ordersMapper.selectAllOKOrders();

        List<Comment> allComments = commentMapper.selectAll(null);

        List<User> allUsers = userMapper.selectAll(null);

        List<Type> allTypes = typeMapper.selectAll(null);

        List<Hotel> allHotels = hotelMapper.selectAll(null);

        List<RelateDTO> data = new ArrayList<>();

        List<Type> result = new ArrayList<>();

        for (Type type : allTypes) {
            Integer typeId = type.getId();
            for (User user : allUsers) {
                Integer userId = user.getId();
                int index = 1;
                Optional<Collect> collectOptional = allCollects.stream().filter(x -> x.getTypeId().equals(typeId) && x.getUserId().equals(userId)).findFirst();
                if (collectOptional.isPresent()) {
                    index += 1;
                }

                Optional<Orders> ordersOptional = allOrders.stream().filter(x -> x.getTypeId().equals(typeId) && x.getUserId().equals(userId)).findFirst();
                if (ordersOptional.isPresent()) {
                    index += 3;
                }

                Optional<Comment> commentOptional = allComments.stream().filter(x -> x.getTypeId().equals(typeId) && x.getUserId().equals(userId)).findFirst();
                if (commentOptional.isPresent()) {
                    index += 2;
                }

                if (index > 1){
                    RelateDTO relateDTO = new RelateDTO(userId, typeId, index);
                    data.add(relateDTO);
                }
            }
        }
        Account currentUser = TokenUtils.getCurrentUser();
        List<Integer> typeIds = UserCF.recommend(currentUser.getId(), data);
        //把商品Id转换成商品
        List<Type> recommendResult = typeIds.stream().map(typeId -> allTypes.stream()
                .filter(x -> x.getId().equals(typeId)).findFirst().orElse(null))
                .limit(10).collect(Collectors.toList());
//        if (CollectionUtil.isEmpty(recommendResult)) {
//            return getRandomTypes(10);
//        }
//        if (recommendResult.size() < 10) {
//            int num = 10 - recommendResult.size();
//            List<Type> list = getRandomTypes(num);
//            result.addAll(list);
//        }
//        return  recommendResult;

        System.out.println(allCollects);
        return recommendResult;
    }

    private List<Type> getRandomTypes(int num) {
        List<Type> list = new ArrayList<>(num);
        List<Type> types = typeMapper.selectAll(null);
        for (int i = 0;i < num; i++){
            int index = new Random().nextInt(types.size());
            list.add(types.get(index));
        }
        return list;
    }
}
