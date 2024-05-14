import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

// 解决导航栏或者底部导航tabBar中的vue-router在3.0版本以上频繁点击菜单报错的问题。
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (location) {
  return originalPush.call(this, location).catch(err => err)
}

const routes = [
  {
    path: '/',
    name: 'Manager',
    redirect: '/login',
    component: () => import('../views/Manager.vue'),
    children: [
      { path: '403', name: 'NoAuth', meta: { name: '无权限' }, component: () => import('../views/manager/403') },
      { path: 'home', name: 'Home', meta: { name: '系统首页' }, component: () => import('../views/manager/Home') },
      { path: 'admin', name: 'Admin', meta: { name: '管理员信息' }, component: () => import('../views/manager/Admin') },
      { path: 'adminPerson', name: 'AdminPerson', meta: { name: '个人信息' }, component: () => import('../views/manager/AdminPerson') },
      { path: 'hotelPerson', name: 'HotelPerson', meta: { name: '个人信息' }, component: () => import('../views/manager/HotelPerson') },
      { path: 'password', name: 'Password', meta: { name: '修改密码' }, component: () => import('../views/manager/Password') },
      { path: 'notice', name: 'Notice', meta: { name: '公告信息' }, component: () => import('../views/manager/Notice') },
      { path: 'hotel', name: 'Hotel', meta: { name: '酒店信息' }, component: () => import('../views/manager/Hotel') },
      { path: 'user', name: 'User', meta: { name: '用户信息' }, component: () => import('../views/manager/User') },
      { path: 'type', name: 'Type', meta: { name: '分类信息' }, component: () => import('../views/manager/Type') },
      { path: 'room', name: 'Room', meta: { name: '客房信息' }, component: () => import('../views/manager/Room') },
      { path: 'orders', name: 'Orders', meta: { name: '订单信息' }, component: () => import('../views/manager/Orders') },
      { path: 'checkin', name: 'Checkin', meta: { name: '入住登记' }, component: () => import('../views/manager/Checkin') },
      { path: 'comment', name: 'Comment', meta: { name: '评论管理' }, component: () => import('../views/manager/Comment') },
    ]
  },
  {
    path: '/front',
    name: 'Front',
    component: () => import('../views/Front.vue'),
    children: [
      { path: 'home', name: 'Home', meta: { name: '系统首页' }, component: () => import('../views/front/Home') },
      { path: 'person', name: 'Person', meta: { name: '个人信息' }, component: () => import('../views/front/Person') },
      { path: 'hotel', name: 'Hotel', meta: { name: '酒店信息' }, component: () => import('../views/front/Hotel') },
      { path: 'detail', name: 'Detail', meta: { name: '客房信息' }, component: () => import('../views/front/Detail') },
      { path: 'collect', name: 'Collect', meta: { name: '收藏信息' }, component: () => import('../views/front/Collect') },
      { path: 'orders', name: 'Orders', meta: { name: '订单信息' }, component: () => import('../views/front/Orders') },
      { path: 'history', name: 'History', meta: { name: '历史入住' }, component: () => import('../views/front/History') },
      { path: 'search', name: 'Search', meta: { name: '搜索页面' }, component: () => import('../views/front/Search') },
    ]
  },
  { path: '/login', name: 'Login', meta: { name: '登录' }, component: () => import('../views/Login.vue') },
  { path: '/register', name: 'Register', meta: { name: '注册' }, component: () => import('../views/Register.vue') },
  { path: '*', name: 'NotFound', meta: { name: '无法访问' }, component: () => import('../views/404.vue') },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
