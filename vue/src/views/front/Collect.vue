<template>
  <div class="main-content">
      <div style="width: 80%; margin: 25px auto">
        <el-row :gutter="20">
          <el-col :span="6" v-for="item in typeData" style="margin: 15px 0">
            <img :src="item.img" style="width: 100%; height: 240px; border-radius: 10px" alt="" @click="navToDetail(item.id)">
            <div style="font-size: 16px; margin-top: 10px; color: #455873FF">
              <span style="font-weight: bold;">{{item.name}}</span>
              <span style="font-weight: bold; font-size: 16px; color: red; margin-left: 10px">￥{{item.price}}</span>
              <span style="font-size: 14px; color: #455873FF; margin-left: 20px"><i class="el-icon-s-home"></i> 剩余 {{item.num}} 间</span>
              <el-button type="warning" style="margin-left: 30px" @click="cancelCollect(item.id)">取消收藏</el-button>
            </div>
          </el-col>
        </el-row>
      </div>
  </div>
</template>

<script>

export default {

  data() {
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      typeData: [],
    }
  },
  mounted() {
    this.loadCollect()
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    loadCollect() {
      this.$request.get('/collect/selectOwn?id=' + this.user.id).then(res => {
        if (res.code === '200') {
          this.typeData = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },

    cancelCollect(typeId) {
      this.$request.delete('/collect/deleteByTypeId?id=' + typeId).then(res => {
        if (res.code === '200') {
          this.$message.success('取消成功')
          this.loadCollect()
        } else {
          this.$message.error(res.msg)
        }
      })
    },

    navToDetail(id) {
      location.href = '/front/detail?id=' + id
    }
  }
}
</script>
