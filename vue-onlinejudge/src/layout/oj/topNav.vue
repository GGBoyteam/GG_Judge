<template>
  <div id="header">
    <el-menu
        mode="horizontal"
        router
        active-text-color="#2196f3"
        text-color="#495060"
    >
      <div class="logo">
        <el-image
            style="width: 139px; height: 50px"
            :src="imgUrl"
            fit="scale-down"
            @click="changeWebLanguage"
        ></el-image>
      </div>
      <el-menu-item
              :class="getSelect()==0?'item_active':'el-menu-item'"
              index="/index"
              @click="changeSelect(0)">
          <el-icon><HomeFilled /></el-icon>
          首页
      </el-menu-item>
      <el-menu-item
              :class="getSelect()==1?'item_active':'el-menu-item'"
              index="/oj/algorithm"
              @click="changeSelect(1)">
          <el-icon><Grid /></el-icon>
          题目
      </el-menu-item>
      <el-menu-item
              :class="getSelect()==2?'item_active':'el-menu-item'"
              index="/oj/train"
              @click="changeSelect(2)">
          <el-icon><List /></el-icon>
          训练
      </el-menu-item>
      <el-menu-item
              :class="getSelect()==3?'item_active':'el-menu-item'"
              index="/oj/contest"
              @click="changeSelect(3)">
          <el-icon><TrendCharts /></el-icon>
          比赛
      </el-menu-item>
        <el-menu-item
                :class="getSelect()==4?'item_active':'el-menu-item'"
                index="/oj/contest"
                @click="changeSelect(4)">
            <el-icon><TrendCharts /></el-icon>
            面试
        </el-menu-item>
        <el-menu-item
                :class="getSelect()==5?'item_active':'el-menu-item'"
                index="/oj/contest"
                @click="changeSelect(5)">
            <el-icon><TrendCharts /></el-icon>
            博客
        </el-menu-item>
      <div class="flex-grow" />
      <div class="btn-menu" v-if="!isLogin()">
        <el-button
            type="primary"
            size="medium"
            round
            @click="this.$router.push('/login');"
        >登录</el-button>
        <el-button
            size="medium"
            round
            style="margin-left: 5px"
            @click="this.$router.push('/login');"
        >注册</el-button>
      </div>
      <el-sub-menu index="2" v-else>
        <template #title><el-avatar :size="30" :src="circleUrl" /></template>
        <el-menu-item index="/system">后台管理</el-menu-item>
        <el-menu-item>退出登录</el-menu-item>
      </el-sub-menu>
    </el-menu>

  </div>
</template>
<script>
import {getCurrentInstance} from "vue";
import useOjAppStore from "@/store/oj/ojApp";
import {getToken} from "@/utils/auth";

export default {
  setup(){
    const ojAppStore=useOjAppStore();

    function isLogin(){
      console.log(getToken())
      return getToken()!=undefined&&getToken()!='';
    }
    function getSelect(){
      return ojAppStore.topNav.select;
    }
    function changeSelect(data){
      ojAppStore.changeSelect(data);
    }


    return {
      ojAppStore,
      isLogin,
      getSelect,
      changeSelect
    }
  },
  beforeCreate() {
    const ojAppStore=useOjAppStore();
    const {proxy}=getCurrentInstance();
    const curRoute=proxy.$route;
    if(curRoute.path=='/home'){
      ojAppStore.changeSelect(0);
    }else if(curRoute.path.startsWith('/oj/algorithm')){
      ojAppStore.changeSelect(1);
    }else if(curRoute.path.startsWith('/oj/train')){
      ojAppStore.changeSelect(2);
    }else if(curRoute.path.startsWith('/oj/contest')){
      ojAppStore.changeSelect(3);
    }
  }
}
</script>
<style scoped>
#header {
  min-width: 300px;
  position: fixed;
  top: 0;
  left: 0;
  height: auto;
  width: 100%;
  z-index: 2000;
  background-color: #fff;
  box-shadow: 0 1px 5px 0 rgba(0, 0, 0, 0.1);
}
.mobile-nav {
  position: fixed;
  left: 0px;
  top: 0px;
  z-index: 2500;
  height: auto;
  width: 100%;
}

#drawer {
  position: fixed;
  left: 0px;
  bottom: 0px;
  z-index: 1000;
  width: 100%;
  box-shadow: 00px 0px 00px rgb(255, 255, 255), 0px 0px 10px rgb(255, 255, 255),
  0px 0px 0px rgb(255, 255, 255), 1px 1px 0px rgb(218, 218, 218);
}
.flex-grow {
  flex-grow: 1;
}

.logo {
  cursor: pointer;
  margin-left: 2%;
  margin-right: 2%;
  float: left;
  width: 139px;
  height: 42px;
  margin-top: 5px;
}
.el-dropdown-link {
  cursor: pointer;
  color: #409eff !important;
}
.el-icon-arrow-down {
  font-size: 18px;
}
.drop-menu {
  float: right;
  margin-right: 30px;
  position: relative;
  font-weight: 500;
  right: 10px;
  margin-top: 18px;
  font-size: 18px;
}
.drop-avatar {
  float: right;
  margin-right: 15px;
  position: relative;
  margin-top: 16px;
}
.drop-msg {
  float: right;
  font-size: 25px;
  margin-right: 10px;
  position: relative;
  margin-top: 13px;
}
.drop-msg-count {
  margin-left: 2px;
}
.btn-menu {
  font-size: 16px;
  float: right;
  margin-right: 10px;
  margin-top: 12px;
}
/deep/ .el-dialog {
  border-radius: 10px !important;
  text-align: center;
}
/deep/ .el-dialog__header .el-dialog__title {
  font-size: 22px;
  font-weight: 600;
  font-family: Arial, Helvetica, sans-serif;
  line-height: 1em;
  color: #4e4e4e;
}
.el-submenu__title i {
  color: #495060 !important;
}
.el-menu-item {
  padding: 0 13px;
}
.el-menu-item:hover, .el-menu .el-menu-item:hover,.el-menu .item_active{
  border-bottom: 2px solid #2474b5 !important;
}
.el-menu .el-menu-item:hover,
.el-menu .el-menu-item:hover i,
.el-submenu .el-submenu__title:hover,
.el-submenu .el-submenu__title:hover i{
  outline: 0 !important;
  color: #2E95FB !important;
  background: linear-gradient(270deg, #F2F7FC 0%, #FEFEFE 100%)!important;
  transition: all .2s ease;
}
.el-menu .el-menu-item.is-active,
.el-menu .el-menu-item.is-active i,
.el-submenu.is-active,
.el-submenu.is-active i
{
  color: #2E95FB !important;
  background: linear-gradient(270deg, #F2F7FC 0%, #FEFEFE 100%)!important;
  transition: all .2s ease;
}
.el-menu--horizontal .el-menu .el-menu-item:hover,
.el-submenu /deep/.el-submenu__title:hover ,.item_active{
  color: #2E95FB !important;
  background: linear-gradient(270deg, #F2F7FC 0%, #FEFEFE 100%)!important;
}
.el-menu-item i {
  color: #495060;
}
.is-active .el-submenu__title i,
.is-active {
  color: #2196f3 !important;
}
.el-menu-item.is-active i {
  color: #2196f3 !important;
}
.navbar-icon{
  margin-right: 5px !important;
  width: 24px !important;
  text-align: center !important;
}
</style>
