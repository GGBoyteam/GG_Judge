<template>
  <div style="margin: 0px 0px 15px 0px;font-size: 14px;position: relative">
    <el-row class="header" id="js-right-header">
      <el-col :xs="24" :sm="16" :md="16" :lg="16">
        <div class="select-row">
          <span>语言:</span>
          <span>
            <el-select v-model="options.language" @change="onLangChange" class="left-adjust" size="small">
              <el-option v-for="item in this.$props.languages" :key="item" :value="item">
                {{ item }}
              </el-option>
            </el-select>
          </span>
          <span>
            <el-tooltip content="重置代码" placement="top">
              <el-button icon="Refresh" @click="onResetClick" size="small"></el-button>
            </el-tooltip>
          </span>
          <span>
            <el-tooltip content="获取最近通过的代码" placement="top">
              <el-button icon="Download" size="small" @click="getUserLastAccepetedCode"></el-button>
            </el-tooltip>
          </span>
        </div>
      </el-col>
      <el-col :xs="24" :sm="8" :md="8" :lg="8">
        <div class="select-row fl-right">
          <span>
            <el-tooltip content="上传文件" placement="bottom">
              <el-button icon="Upload" @click="onUploadFile" size="small"></el-button>
            </el-tooltip>
          </span>
          <span>
            <input type="file" id="file-uploader" style="display: none" @change="onUploadFileDone"/>
          </span>
          <span>
            <el-tooltip content="代码框主题" placement="top">
              <el-popover placement="bottom" width="300" trigger="click">
                <el-button slot="reference" icon="el-icon-s-tools" size="small"></el-button>
                <div class="setting-title">m.Setting</div>
                <div class="setting-item">
                  <span class="setting-item-name">
                    <i class="fa fa-tachometer"></i>
                    m.Theme
                  </span>
                  <el-select
                      :value="this.theme"
                      @change="onThemeChange"
                      class="setting-item-value"
                      size="small"
                  >
                    <el-option
                        v-for="item in themes"
                        :key="item.label"
                        :label="'m.' + item.label"
                        :value="item.value"
                    >'m.' + item.label
                    </el-option>
                  </el-select>
                </div>
                <div class="setting-item">
                  <span class="setting-item-name">
                    <i class="fa fa-font"></i>
                    m.FontSize'
                  </span>
                  <el-select :value="fontSize" @change="onFontSizeChange" class="setting-item-value" size="small">
                    <el-option
                        v-for="item in fontSizes"
                        :key="item"
                        :label="item"
                        :value="item"
                    >{{ item }}
                    </el-option>
                  </el-select>
                </div>
                <div class="setting-item">
                  <span class="setting-item-name">
                    <svg
                        focusable="false"
                        viewBox="0 0 1024 1024"
                        fill="currentColor"
                        width="1.2em"
                        height="1.2em"
                        style="vertical-align: text-bottom;"
                        aria-hidden="true"
                    >
                      <g transform="translate(101.57 355.48)">
                        <rect
                            width="812.53"
                            height="152.35"
                            x="0"
                            y="0"
                            rx="50.78"
                        ></rect>
                        <rect
                            width="812.53"
                            height="50.78"
                            x="0"
                            y="253.92"
                            rx="25.39"
                        ></rect>
                        <rect
                            width="50.78"
                            height="203.13"
                            x="0"
                            y="177.74"
                            rx="25.39"
                        ></rect>
                        <rect
                            width="50.78"
                            height="203.13"
                            x="761.75"
                            y="177.74"
                            rx="25.39"
                        ></rect>
                      </g>
                    </svg> m.TabSize
                  </span>
                  <el-select
                      :value="tabSize"
                      @change="onTabSizeChange"
                      class="setting-item-value"
                      size="small"
                  >
                    <el-option
                        :label="'m.Two_Spaces' "
                        :value="2"
                    >
                      m.Two_Spaces
                    </el-option>
                    <el-option
                        :label="'m.Four_Spaces'"
                        :value="4"
                    >
                      m.Four_Spaces
                    </el-option>
                    <el-option
                        :label="'m.Eight_Spaces'"
                        :value="8"
                    >
                     m.Eight_Spaces
                    </el-option>
                  </el-select>
                </div>
              </el-popover>
            </el-tooltip>
          </span>
          <div v-if="supportFocusMode">
            <span
                v-if="!openFocusMode"
                class="hidden-sm-and-down"
            >
              <el-tooltip
                  :content="'m.Enter_Focus_Mode'"
                  placement="bottom"
              >
                <el-button
                    icon="el-icon-full-screen"
                    @click="switchFocusMode(true)"
                    size="small"
                ></el-button>
              </el-tooltip>
            </span>
            <span
                v-else
                class="hidden-sm-and-down"
            >
              <el-tooltip
                  :content="'m.Exit_Focus_Mode'"
                  placement="bottom"
              >
                <el-button
                    @click="switchFocusMode(false)"
                    size="small"
                >
                  <svg
                      focusable="false"
                      viewBox="0 0 1024 1024"
                      fill="currentColor"
                      width="0.95em"
                      height="0.95em"
                      aria-hidden="true"
                  >
                    <path d="M463.04 863.32h-88.51V641.14H152.35v-88.87H463.4l-.36 311.05zM863.32 463.4H552.27l.31-311.05h88.56v222.18h222.18v88.87z">
                    </path>
                  </svg>
                </el-button>
              </el-tooltip>
            </span>
          </div>
        </div>
      </el-col>
    </el-row>
    <div class="input-body">
      <codemirror
          class="js-right"
          v-model="options.code"
          :extensions="options.extensions"
          :autofocus="true"
          :style="{ height: '648px' }"
          ref="myEditor"
      >
      </codemirror>
      <div class="my-drawer">
        <el-drawer
            class="my-drawer"
            custom-class="my-drawer"
            :close-on-click-modal="false"
            v-model="drawerVisible"
            style="position: absolute"
            :with-header="false"
            :append-to-body="true"
            modal-class="my-drawer"
            on-opened="OnDrawerOpen"
        >
        </el-drawer>
      </div>
    </div>
    <el-button @click="openDrawer"></el-button>
  </div>
</template>

<script>
import {defineComponent, ref, watch} from "vue";
import { Codemirror } from 'vue-codemirror'
import { javascript } from '@codemirror/lang-javascript'
import {java} from '@codemirror/lang-java'
import {cpp} from '@codemirror/lang-cpp'
import {python} from '@codemirror/lang-python'
import { oneDark } from '@codemirror/theme-one-dark'

export default defineComponent({
  name: "CodeInput",
  components:{
    Codemirror
  },
  props: {
    modelValue:{
      type: String,
      default: undefined
    },
    language:{
      type:String,
      default: 'C'
    },
    languages: {
      type: Array,
      default: () => {
        return ["C", "C++", "Java", "Python3", "Python2"];
      },
    }
  },
  setup(props,context){
    // 编辑器选项
    const options=ref({
        language: 'C',
        code: '测试',
        extensions: [cpp(),oneDark]
    });

    const drawerVisible=ref(false)

    function openDrawer(){
      drawerVisible.value=!drawerVisible.value
    }

    function OnDrawerOpen() {
      console.log('dawdawdaw')
    }

    function onLangChange(newVal) {

    }
    function onResetClick(){
      options.value.code=undefined;
    }
    function getUserLastAccepetedCode(){

    }
    watch(()=>options.value.language,(newVal,oldVal)=>{
      if(newVal=='Java'){
        options.value.extensions=[java(),oneDark]
      }else if(newVal=='C'||newVal=='C++'){
        options.value.extensions=[cpp(),oneDark]
      }else if(newVal=='Python3'||newVal=='Python2'){
        options.value.extensions=[python(),oneDark]
      }
      context.emit('update:language',newVal);
    })
    watch(()=>options.value.code,(newVal,oldVal)=>{
      context.emit('update:modelValue',newVal);
    })
    return{
      options,
      drawerVisible,
      openDrawer,
      onLangChange,
      onResetClick,
      getUserLastAccepetedCode,
      OnDrawerOpen
    }
  },



})
</script>

<style scoped>
.my-drawer {
  position: relative !important;
  :deep .el-overlay{
     background-color: red !important;
    font-size: 32px;
  }
}

.input-body{
  line-height: 1.5;
  font-size:15px;
  position: relative;
}



.header {
  margin-bottom: 10px;
  margin-right: 5px;
  margin-left: 5px;
}
.header .left-adjust {
  width: 170px;
  margin-left: 5px;
}
.setting-title {
  border-bottom: 1px solid #f3f3f6;
  color: #000;
  font-weight: 700;
  padding: 10px 0;
}
.setting-item {
  display: flex;
  padding: 15px 0 0;
}
.setting-item-name {
  flex: 2;
  color: #333;
  font-weight: 700;
  font-size: 13px;
  margin-top: 7px;
}
.setting-item-value {
  width: 140px;
  margin-left: 15px;
  flex: 5;
}

.select-row {
  margin-top: 4px;
}
/deep/.el-drawer__body {
  border: 1px solid rgb(240, 240, 240);
}
.tj-btn {
  font-size: 13px;
  font-weight: 600;
  border: 1px solid #32ca99;
}
.tj-btn:hover {
  background-color: #d5f1eb;
}
.tj-test-tag {
  margin-right: 15px;
  cursor: pointer;
}
.tj-test-tag:hover {
  font-weight: 600;
}
.tj-res-tab {
  padding-right: 15px;
}
.tj-res-item {
  display: flex;
  margin-top: 10px;
}
.tj-res-item .name {
  flex: 2;
  text-align: center;
  line-height: 34px;
  font-size: 12px;
}
.tj-res-item .value {
  flex: 10;
}
/deep/.el-textarea__inner[readonly] {
  background-color: #f7f8f9 !important;
}
.color-gray {
  color: #999;
}
.mr-5 {
  margin-right: 5px;
}
.mt-10 {
  margin-top: 10px;
}
@media screen and (max-width: 768px) {
  .select-row span {
    margin-right: 2px;
  }
  .tj-res-item .name {
    flex: 2;
  }
  .tj-res-item .value {
    flex: 5;
  }
}
@media screen and (min-width: 768px) {
  .select-row span {
    margin-right: 3px;
  }
  .fl-right {
    float: right;
  }
}
/deep/.el-tabs__content {
  position: absolute;
  top: 40px;
  bottom: 2px;
  left: 0;
  right: 0;
  overflow-y: auto;
}
/deep/.el-card__header {
  padding: 10px 25px;
  background-color: antiquewhite;
}
.ce-title {
  color: rgb(255, 153, 0);
  font-size: 15px;
  font-weight: 600;
}
.status-title {
  font-size: 15px;
  font-weight: 700;
}
</style>

<style>
@media screen and (max-width: 992px) {
  .CodeMirror{
    height: 550px;
  }
}
.cm-s-monokai .cm-matchhighlight {
  background-color: rgba(73, 72, 62, 0.99);
}
.cm-s-solarized .cm-matchhighlight {
  background-color: #d7d4f0;
}
.cm-s-material .cm-matchhighlight {
  background-color: rgba(128, 203, 196, 0.2);
}
</style>