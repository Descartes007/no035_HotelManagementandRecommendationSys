<template>
  <div class="main-content">
    <div style="width: 75%; margin: 10px auto;">
      <div style=" font-weight: 700; font-size: 16px; padding: 15px 30px; border-bottom: 1px solid #eeeeee">详情信息</div>
      <div style="display: flex; margin-top: 20px; padding: 0 60px">
        <div style="flex: 3; margin-right: 10px">
          <img :src="typeData.img" alt="" style="width: 100%; height: 500px; border-radius: 10px">
        </div>
        <div style="flex: 2; margin-left: 10px">
          <div style="font-size: 16px; font-weight: bold; margin: 0 15px">基本信息</div>
          <div style="font-size: 14px; margin: 15px 15px; display: flex; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
            <span style="flex: 1">
              <i class="el-icon-circle-check"></i>类型：{{typeData.name}}
            </span>
            <span style="flex: 1">
              <i class="el-icon-circle-check"></i>间数：{{typeData.num}}
            </span>
            <span style="flex: 1">
              <i class="el-icon-circle-check"></i>酒店：{{typeData.hotelName}}
            </span>
          </div>

          <div style="font-size: 16px; font-weight: bold; margin: 10px 15px">洗浴用品</div>
          <div style="font-size: 14px; margin: 15px 15px;">
            <div style="display: flex; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
            <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 牙刷
            </span>
              <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 牙膏
            </span>
              <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 牙膏
            </span>
              <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 洗发水
            </span>
            </div>
            <div style="display: flex; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
            <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 牙刷
            </span>
              <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 牙膏
            </span>
              <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 牙膏
            </span>
              <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 洗发水
            </span>
            </div>
          </div>

          <div style="font-size: 16px; font-weight: bold; margin: 10px 15px">洗浴用品</div>
          <div style="font-size: 14px; margin: 15px 15px;">
            <div style="display: flex; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
            <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 牙刷
            </span>
              <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 牙膏
            </span>
              <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 牙膏
            </span>
              <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 洗发水
            </span>
            </div>
            <div style="display: flex; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
            <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 牙刷
            </span>
              <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 牙膏
            </span>
              <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 牙膏
            </span>
              <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 洗发水
            </span>
            </div>
          </div>

          <div style="font-size: 16px; font-weight: bold; margin: 10px 15px">洗浴用品</div>
          <div style="font-size: 14px; margin: 15px 15px;">
            <div style="display: flex; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
            <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 牙刷
            </span>
              <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 牙膏
            </span>
              <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 牙膏
            </span>
              <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 洗发水
            </span>
            </div>
          </div>


          <div style="font-size: 16px; font-weight: bold; margin: 10px 15px">洗浴用品</div>
          <div style="font-size: 14px; margin: 15px 15px;">
            <div style="display: flex; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
            <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 牙刷
            </span>
              <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 牙膏
            </span>
              <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 牙膏
            </span>
              <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 洗发水
            </span>
            </div>
            <div style="display: flex; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
            <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 牙刷
            </span>
              <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 牙膏
            </span>
              <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 牙膏
            </span>
              <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 洗发水
            </span>
            </div>
          </div>

          <div style="margin-left: 15px; margin-top: 25px; color: red; font-size: 16px">价格：￥ {{typeData.price}} / 晚</div>

          <div style="margin-left: 15px; margin-top: 25px">
            <el-button type="warning" @click="addCollect">加入收藏</el-button>
            <el-button type="success" @click="initDialog">立即预订</el-button>
          </div>
        </div>
      </div>
      <div style=" font-weight: 700; font-size: 16px; padding: 15px 30px; margin-top: 20px; border-bottom: 1px solid #eeeeee">评论信息</div>
      <div style="margin-top: 10px; padding: 0 70px; margin-bottom: 1000px">
        <div style="margin: 20px 0" v-for="item in commentData">
          <el-row :gutter="20">
            <el-col :span="4">
              <div style="display: flex; align-items: center;">
                <img :src="item.avatar" alt="" style="height: 50px; width: 50px; border-radius: 50%">
                <div style="flex: 1; margin-left: 10px; color: #5E5C5CFF">{{item.userName}}</div>
              </div>
            </el-col>
            <el-col :span="20">
              <el-row :gutter="20">
                <el-col :span="20">
                  <div style="height: 50px; line-height: 50px">{{item.content}}</div>
                </el-col>
                <el-col :span="4">
                  <div style="height: 50px; line-height: 50px; text-align: right">{{item.time}}</div>
                </el-col>
              </el-row>
              <!--  回复-->
              <el-row :gutter="20" style="margin-top: 15px" v-for="child in item.children">
                <el-col :span="4">
                  <div style="display: flex; align-items: center;">
                    <img :src="child.avatar" alt="" style="height: 50px; width: 50px; border-radius: 50%">
                    <div style="flex: 1; margin-left: 10px; color: #5E5C5CFF">{{child.userName}} <span style="font-weight: bold">回复：</span></div>
                  </div>
                </el-col>
                <el-col :span="16">
                  <div style="height: 50px; line-height: 50px">{{child.content}}</div>
                </el-col>
                <el-col :span="4">
                  <div style="height: 50px; line-height: 50px; text-align: right">{{child.time}}</div>
                </el-col>
              </el-row>
              <!-- 回复评论框框 -->
              <el-row :gutter="20" style="margin-top: 20px">
                <el-col :span="20">
                  <el-input style="width: 70%" v-model="item.reply"></el-input>
                  <el-button type="primary" style="margin-left: 15px" @click="replyComment(item.id, item.reply)">回复</el-button>
                </el-col>
                <el-col :span="4"></el-col>
              </el-row>
            </el-col>
          </el-row>
        </div>
      </div>
    </div>
    <el-dialog title="请选择时间" :visible.sync="fromVisible" width="25%" :close-on-click-modal="false" destroy-on-close>
      <el-form label-width="100px" style="padding-right: 50px">
        <el-form-item prop="inTime" label="入住时间">
          <el-date-picker v-model="inTime" type="date" placeholder="选择日期" value-format="yyyy-MM-dd" style="width: 100%"></el-date-picker>
        </el-form-item>
        <el-form-item prop="outTime" label="离开时间">
          <el-date-picker v-model="outTime" type="date" placeholder="选择日期" value-format="yyyy-MM-dd" style="width: 100%"></el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="fromVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>

export default {

  data() {
    let typeId = this.$route.query.id
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      typeData: {},
      typeId: typeId,
      form: {},
      inTime: null,
      outTime: null,
      fromVisible: false,
      commentData: [],
    }
  },
  mounted() {
    this.loadType()
    this.loadComments()
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    loadComments() {
      this.$request.get('/comment/selectByTypeId?id=' + this.typeId).then(res => {
        if (res.code === '200') {
          this.commentData = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    replyComment(parentId, replyContent) {
      if (!replyContent) {
        this.$message.warning('请输入评价内容')
        return
      }
      let data = {
        content: replyContent,
        typeId: this.typeId,
        hotelId: this.typeData.hotelId,
        userId: this.user.id,
        role: this.user.role,
        parentId: parentId
      }
      this.$request.post('/comment/add', data).then(res => {
        if (res.code === '200') {
          this.$message.success('评价成功')
          this.loadComments()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    loadType() {
      this.$request.get('/type/selectById?id=' + this.typeId).then(res => {
        if (res.code === '200') {
          this.typeData = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    addCollect() {
      let data = {userId: this.user.id, typeId: this.typeId}
      this.$request.post('/collect/add', data).then(res => {
        if (res.code === '200') {
          this.$message.success('收藏成功')
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    initDialog() {
      this.fromVisible = true
    },
    save() {
      let data = {
        userId: this.user.id,
        typeId: this.typeId,
        hotelId: this.typeData.hotelId,
        inTime: this.inTime,
        outTime: this.outTime
      }
      this.$request.post('/orders/add', data).then(res => {
        if (res.code === '200') {
          this.$message.success('预订成功')
          this.fromVisible = false
        } else {
          this.$message.error(res.msg)
        }
      })
    }
  }
}
</script>
