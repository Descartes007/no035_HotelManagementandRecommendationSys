<template>
  <div class="main-content">
      <div style="width: 60%; margin: 20px auto; display: flex">
        <div style="flex: 1">
          <img :src="hotelData.avatar" alt="" style="width: 100%; height: 250px; border-radius: 10px">
        </div>
        <div style="flex: 1; margin-left: 30px">
          <div style="font-size: 18px; font-weight: bold; color: #0F294DFF">{{hotelData.name}}</div>
          <div style="color: #0F294DFF; margin-top: 10px">酒店官网：<a :href="hotelData.url" target="_blank">{{hotelData.url}}</a></div>
          <div style="color: #0F294DFF; margin-top: 10px">酒店电话：{{hotelData.phone}}</div>
          <div style="color: #0F294DFF; margin-top: 10px">酒店邮箱：{{hotelData.email}}</div>
          <div style="color: #0F294DFF; margin-top: 10px">客房价格：<span style="color: red">￥{{hotelData.price}}</span> 起</div>
          <div style="color: #0F294DFF; margin-top: 10px; overflow: hidden; text-overflow: ellipsis; display: -webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp: 5;">酒店介绍：{{hotelData.description}}</div>
        </div>
        <div style="flex: 1"></div>
      </div>
      <div style="width: 60%; margin: 0 auto">
        <div style="text-align: center; font-size: 25px; font-weight: bold">酒店房型</div>
        <el-row :gutter="20">
          <el-col :span="6" v-for="item in typeData">
            <img :src="item.img" style="width: 100%; height: 200px; border-radius: 10px" alt="" @click="navToDetail(item)">
            <div style="font-size: 16px; margin-top: 10px; color: #455873FF">
              <span style="font-weight: bold;">{{item.name}}</span>
              <span style="font-weight: bold; font-size: 16px; color: red; margin-left: 10px">￥{{item.price}}</span>
              <span style="font-size: 14px; color: #455873FF; margin-left: 20px"><i class="el-icon-s-home"></i> 剩余 {{item.num}} 间</span>
            </div>
          </el-col>
        </el-row>
      </div>
    <div style="background-color:gold;width: 60%; margin: 0 auto;margin-top: 30px">
      <div style="text-align: center; font-size: 25px; font-weight: bold;">猜你喜欢</div>
      <el-row :gutter="20">
        <el-col :span="6" v-for="item in recommendData">
          <img :src="item.img" style="width: 100%; height: 200px; border-radius: 10px" alt="" @click="navToDetail(item)">
          <div style="font-size: 16px; margin-top: 10px; color: #455873FF">
            <span style="font-weight: bold;">{{item.name}}</span>
            <span style="font-weight: bold; font-size: 16px; color: red; margin-left: 10px">￥{{item.price}}</span>
            <span style="font-size: 14px; color: #455873FF; margin-left: 20px"><i class="el-icon-s-home"></i> 剩余 {{item.num}} 间</span>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>

export default {

  data() {
    let hotelId = this.$route.query.id
    return {
      hotelData: {},
      typeData: [],
      recommendData: [],
      hotelId: hotelId,
    }
  },
  mounted() {
    this.loadHotels()
    this.loadTypes()
    this.loadRecommend()
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
      async loadRecommend() {
        try {
          const response = await fetch("/type/recommend"); // 使用 fetch API 发送请求
          if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
          }
          const data = await response.json(); // 解析响应为 JSON 格式的数据
          this.recommendData = data; // 将数据赋值给 this.recommendData
          console.log('recommendData 加载成功:', this.recommendData); // 打印数据到控制台
        } catch (error) {
          console.error('加载 recommendData 时发生错误:', error);
        }
      },
    loadHotels() {
      this.$request.get('/hotel/selectById?id=' + this.hotelId).then(res => {
        if (res.code === '200') {
          this.hotelData = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    loadTypes() {
      this.$request.get('/type/selectByHotelId?id=' + this.hotelId).then(res => {
        if (res.code === '200') {
          this.typeData = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    navToDetail(item) {
      if (item.num === 0) {
        this.$message.warning('该类房型目前没有空余房间了，请选择其他客房')
        return
      }
      location.href = '/front/detail?id=' + item.id
    }
  }
}
</script>
