<template>
  <div class="main-content">
    <div style="position: absolute; font-size: 40px; font-weight: bold; color: white; font-style: italic; top: 200px; left: 800px">欢迎您的到来.</div>
    <div>
      <img src="@/assets/imgs/title.jpg" style="width: 100%; height: 300px; border-bottom-left-radius: 40%; border-bottom-right-radius: 40%" alt="">
    </div>
    <div style="width: 60%; margin: 30px auto; text-align: center">
      <div style="text-align: center; font-size: 25px; font-weight: bold">酒店服务升级</div>
      <div style="display: flex; margin-top: 30px">
        <div style="flex: 1">
          <img src="@/assets/imgs/icon-1.png" style="width: 50px; height: 50px" alt="">
          <div style="font-size: 14px; margin-top: 10px; color: #455873FF">知名酒店，客房充足，选择多多</div>
        </div>
        <div style="flex: 1;">
          <img src="@/assets/imgs/icon-2.png" style="width: 50px; height: 50px" alt="">
          <div style="font-size: 14px; margin-top: 10px; color: #455873FF">专业服务，房间干净，放心入住</div>
        </div>
        <div style="flex: 1">
          <img src="@/assets/imgs/icon-3.png" style="width: 50px; height: 50px" alt="">
          <div style="font-size: 14px; margin-top: 10px; color: #455873FF">安全便捷，服务保障，用心生活</div>
        </div>
      </div>
    </div>
    <div style="width: 60%; margin: 50px auto; text-align: left">
      <div style="text-align: center; font-size: 25px; font-weight: bold">平台优质酒店</div>
      <div style="margin-top: 30px">
        <el-row :gutter="20">
          <el-col :span="6" v-for="item in hotelData">
            <img :src="item.avatar" style="width: 100%; height: 175px; border-radius: 10px" alt="" @click="navToDetail(item.id)">
            <div style="font-size: 16px; font-weight: bold; margin-top: 10px; color: #455873FF">{{item.name}}</div>
            <div style="margin-top: 10px">
              <span style="font-weight: bold; font-size: 16px; color: red">￥{{item.price}}</span> 起
              <span style="font-size: 14px; color: #455873FF; margin-left: 20px"><i class="el-icon-chat-line-square"></i> 1688点评</span>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>
<!--    此处是我添加的！-->
    <div style="width: 60%; margin: 50px auto; text-align: left">
      <div style="text-align: center; font-size: 25px; font-weight: bold">猜你喜欢</div>
      <div style="margin-top: 30px">
        <el-row :gutter="20">
          <el-col :span="6" v-for="item in hotelGuessData">
            <img :src="item.avatar" style="width: 100%; height: 175px; border-radius: 10px" alt="" @click="navToDetail(item.id)">
            <div style="font-size: 16px; font-weight: bold; margin-top: 10px; color: #455873FF">{{item.name}}</div>
            <div style="margin-top: 10px">
              <span style="font-weight: bold; font-size: 16px; color: red">￥{{item.price}}</span> 起
              <span style="font-size: 14px; color: #455873FF; margin-left: 20px"><i class="el-icon-chat-line-square"></i> 1688点评</span>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>


  </div>
</template>

<script>

export default {

  data() {
    return {
      hotelData: [],
      hotelGuessData: [],
      // recommendData: []
      // typeData: [],
    }
  },
  mounted() {
    this.loadHotels()
    // this.loadTypes()
    // this.loadrecommend()
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    // loadRecommend(){
    //   this.$message.get("/room/recommend").then(res => {
    //     if (res.code === '200') {
    //       this.recommendData = res.data
    //     } else {
    //       this.$message.error(res.msg)
    //     }
    //   })
    // },
    loadHotels() {
      this.$request.get("/hotel/selectAll").then(res => {
        if (res.code === '200') {
          this.hotelData = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
      // guess u like
      let user = JSON.parse(localStorage.getItem("xm-user"))  // 存储用户数据
      this.$request.get("/hotel/selectAll?id=" + user.id).then(res => {
        if (res.code === '200') {
          this.hotelGuessData = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    navToDetail(id) {
      location.href = '/front/hotel?id=' + id
    },
    // loadTypes() {
    //   this.$request.get('/type/selectByHotelId?id=' + this.hotelId).then(res => {
    //     if (res.code === '200') {
    //       this.typeData = res.data
    //     } else {
    //       this.$message.error(res.msg)
    //     }
    //   })
    // },
    // navToRoom(item) {
    //   if (item.num === 0) {
    //     this.$message.warning('该类房型目前没有空余房间了，请选择其他客房')
    //     return
    //   }
    //   location.href = '/front/detail?id=' + item.id
    // }

  }
}
</script>
