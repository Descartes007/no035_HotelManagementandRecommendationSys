package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.common.enums.StatusEnum;
import com.example.entity.*;
import com.example.exception.CustomException;
import com.example.mapper.*;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 酒店业务处理
 **/
@Service
public class HotelService {

    @Resource
    private HotelMapper hotelMapper;

    @Resource
    private OrdersMapper ordersMapper;

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private CollectMapper collectMapper;

    @Resource
    private TypeMapper typeMapper;

    public void add(Hotel hotel) {
        // 1. 做一下重复性校验
        Hotel hotelUser = hotelMapper.selectByUsername(hotel.getUsername());
        if (ObjectUtil.isNotEmpty(hotelUser)) {
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }
        if (ObjectUtil.isEmpty(hotel.getPassword())) {
            hotel.setPassword("123456");
        }
        if (ObjectUtil.isEmpty(hotel.getRole())) {
            hotel.setRole(RoleEnum.HOTEL.name());
        }
        if (ObjectUtil.isEmpty(hotel.getAvatar())) {
            hotel.setAvatar("http://localhost:9091/files/1697438073596-avatar.png");
        }
        hotel.setStatus(StatusEnum.CHECKING.status);
        hotelMapper.insert(hotel);
    }

    public PageInfo<Hotel> selectPage(Hotel hotel, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Hotel> hotels = hotelMapper.selectAll(hotel);
        return PageInfo.of(hotels);
    }

    public void update(Hotel hotel) {
        hotelMapper.updateById(hotel);
    }

    public void deleteById(Integer id) {
        hotelMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> list) {
        for (Integer id : list) {
            deleteById(id);
        }
    }

    public Account login(Account account) {
        Account hotel = hotelMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(hotel)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(hotel.getPassword())) {
            throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }
        // 生成token
        String tokenData = hotel.getId() + "-" + RoleEnum.HOTEL.name();
        String token = TokenUtils.createToken(tokenData, hotel.getPassword());
        hotel.setToken(token);
        return hotel;
    }

    public void register(Account account) {
        Hotel hotel = new Hotel();
        BeanUtils.copyProperties(account, hotel);
        add(hotel);
    }

    public Hotel selectById(Integer id) {
        return hotelMapper.selectById(id);
    }

    public void updatePassword(Account account) {
        Hotel dbHotel = hotelMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbHotel)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbHotel.getPassword())) {
            throw new CustomException(ResultCodeEnum.PARAM_PASSWORD_ERROR);
        }
        dbHotel.setPassword(account.getNewPassword());
        hotelMapper.updateById(dbHotel);
    }


    public JSONObject SortJsonObject(JSONObject jsonObject){
        // 将JSONObject中的键值对存储到List中


        // 创建一个空的Map用于存放键值对
        Map<String, Object> map = new LinkedHashMap<>();

        // 遍历JSONObject的键集合
        for (String key : jsonObject.keySet()) {
            // 根据键获取对应的值，并放入Map中
            map.put(key, jsonObject.get(key));
        }
        List<Map.Entry<String, Object>> list = new ArrayList<>(map.entrySet());

        // 按值排序
        Collections.sort(list, new Comparator<Map.Entry<String, Object>>() {
            @Override
            public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
                // 假设值是整数类型
                Double value1 = Double.parseDouble(o1.getValue().toString());
                Double value2 = Double.parseDouble(o2.getValue().toString());
                return value2.compareTo(value1);
            }
        });

        // 创建新的JSONObject并按照排序后的键值对逐个放入
        JSONObject sortedJsonObject = new JSONObject();
        for (Map.Entry<String, Object> entry : list) {
            sortedJsonObject.put(entry.getKey(), entry.getValue());
        }
        return sortedJsonObject;
    }

    public List<Hotel> selectAll(Hotel hotel) {
        Integer uId = hotel.getId();
        Hotel hdto = new Hotel();
        List<Hotel> ret = hotelMapper.selectAll(hdto);
        if(uId != null){ // 用户角色
            Orders odto = new Orders();
            Comment cdto = new Comment();
            Collect codto = new Collect();
            odto.setUserId(uId);
            cdto.setUserId(uId);
            codto.setUserId(uId);
            List<Orders> ol = ordersMapper.selectAll(odto);
            List<Comment> cl = commentMapper.selectAll(cdto);
            List<Collect> col = collectMapper.selectAll(codto);
            if(ol.isEmpty() && col.isEmpty()){
                return ret;
            }
//            开始统计 按照权重酒店分数 订单权重占比40% 评论占比40% 收藏占比20%
            JSONObject dict = new JSONObject();
            String[] keys = new String[ret.size()];
            Double[] rate = {0.4, 0.4, 0.2};
            int index = 0;
            for(Hotel h:ret){
                dict.set("hotelId:"+h.getId(), 0.0);
                keys[index] = "hotelId:"+h.getId();
                index++;
            }
            if(!ol.isEmpty()){
                JSONObject o_dict = new JSONObject();
                for(Orders o:ol){
                    if(o_dict.get("hotelId:"+Integer.valueOf(o.getHotelId()))!=null){
//                        键存在
                        Integer num = (Integer)o_dict.get("hotelId:"+Integer.valueOf(o.getHotelId())) + 1;
                        o_dict.set("hotelId:"+Integer.valueOf(o.getHotelId()), num);
                    } else {
                        o_dict.set("hotelId:"+Integer.valueOf(o.getHotelId()), 1);
                    }
                }
                for(String key:keys){
                    Integer num;
                    if(o_dict.get(key)!=null){
                        num = (Integer)o_dict.get(key);
                    }else{
                        num = 0;
                    }
                    dict.set(key, (Double)dict.get(key) + num * rate[0]);
                }
            }

            if(!cl.isEmpty()){
                JSONObject c_dict = new JSONObject();
                for(Comment c:cl){
                    if(c_dict.get("hotelId:"+Integer.valueOf(c.getHotelId()))!=null){
//                        键存在
                        Integer num = (Integer)c_dict.get("hotelId:"+Integer.valueOf(c.getHotelId())) + 1;
                        c_dict.set("hotelId:"+Integer.valueOf(c.getHotelId()), num);
                    } else {
                        c_dict.set("hotelId:"+Integer.valueOf(c.getHotelId()), 1);
                    }
                }
                for(String key:keys){
                    Integer num;
                    if(c_dict.get(key)!=null){
                        num = (Integer)c_dict.get(key);
                    }else{
                        num = 0;
                    }
                    dict.set(key, (Double)dict.get(key) + num * rate[1]);
                }
            }

            if(!col.isEmpty()){
                JSONObject c_dict = new JSONObject();
                for(Collect c:col){
                    Type t = typeMapper.selectById(c.getTypeId());
                    if(c_dict.get("hotelId:"+Integer.valueOf(t.getHotelId()))!=null){
//                        键存在
                        Integer num = (Integer)c_dict.get("hotelId:"+Integer.valueOf(t.getHotelId())) + 1;
                        c_dict.set("hotelId:"+Integer.valueOf(t.getHotelId()), num);
                    } else {
                        c_dict.set("hotelId:"+Integer.valueOf(t.getHotelId()), 1);
                    }
                }
                for(String key:keys){
                    Integer num;
                    if(c_dict.get(key)!=null){
                        num = (Integer)c_dict.get(key);
                    }else{
                        num = 0;
                    }
                    dict.set(key, (Double)dict.get(key) + num * rate[2]);
                }
            }

            for(String key:keys){
                System.out.println(key+" 分数="+dict.get(key));
            }

//            返回个性酒店信息 重新排序
//            排序
            JSONObject res = SortJsonObject(dict);
            // 创建一个空的Map用于存放键值对
            Map<String, Object> map = new LinkedHashMap<>();

            // 遍历JSONObject的键集合
            for (String key : res.keySet()) {
                // 根据键获取对应的值，并放入Map中
                map.put(key, res.get(key));
            }
            List<Map.Entry<String, Object>> list = new ArrayList<>(map.entrySet());
            List<Hotel> tmp = new ArrayList<>();
            for(Map.Entry<String, Object> m:list){
                for(Hotel h:ret){
                    if(m.getKey().split(":")[1].equals(String.valueOf(h.getId()))){
                        tmp.add(h);
                    }
                }
            }
            for(Hotel h:ret){
                if(dict.get("hotelId:"+h.getId())==null){
                    tmp.add(h);
                }
            }
            ret = tmp;
        }
        return ret;
    }

    public List<Hotel> selectByName(String name) {
        Hotel hotel = new Hotel();
        if (ObjectUtil.isNotEmpty(name) && !"null".equals(name)) {
            hotel.setName(name);
        }
        return hotelMapper.selectAll(hotel);
    }

    public List<Hotel> selectAllOK() {
        return hotelMapper.selectAllOK();
    }
}
