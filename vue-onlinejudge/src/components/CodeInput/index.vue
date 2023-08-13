<template>
  <div style="margin: 0px 0px 15px 0px;font-size: 14px;position: relative">
    <el-row class="header" id="js-right-header">
      <el-col :xs="24" :sm="16" :md="16" :lg="16">
        <div class="select-row">
          <span>语言:</span>
          <span>
            <el-select v-model="form.language" @change="onLangChange" class="left-adjust" size="small">
              <el-option v-for="item in languages" :key="item.language" :value="item.language" :label="item.language">
              </el-option>
            </el-select>
          </span>
            <span>版本:</span>
            <span>
            <el-select v-model="form.version" class="left-adjust" size="small">
                <el-option v-for="item in version.get(form.language)" :key="item" :value="item" :label="item">
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
        </div>
      </el-col>
    </el-row>
    <div class="input-body">
        <codemirror
                class="js-right"
                v-model="form.code"
                :extensions="extensions"
                :autofocus="true"
                style="height: 640px;z-index: 1"
                ref="myEditor"
        >
        </codemirror>
      <div class="my-drawer" v-if="$props.drawerVisible">
          <el-tabs v-model="activeName" class="demo-tabs" style="width: 100%;height: 100%" type="border-card">
              <el-tab-pane label="测试用例" name="test">
                  <div style="height: 50px;width: 100%" v-if="examples&&examples.length>0">

                  </div>
                  <div>
                      <el-input
                         style="font-size: 20px"
                         v-model="form.input"
                         :rows="(examples&&examples.length>0)?6:7"
                         resize="none"
                         type="textarea"
                      >
                      </el-input>
                  </div>
              </el-tab-pane>
              <el-tab-pane label="运行结果" name="result">
                  <el-input
                          style="font-size: 20px"
                          :disabled="true"
                          v-model="result"
                          :readonly="true"
                          :rows="7"
                          resize="none"
                          type="textarea"
                  >
                  </el-input>
              </el-tab-pane>
              <el-tab-pane :disabled="true">
                  <template #label>
                    <div style="display: flex">
                        <el-button @click="judgeTest" type="info" :loading="running">{{running?'运行中':'运行自测'}}</el-button>
                    </div>
                  </template>
              </el-tab-pane>
          </el-tabs>
      </div>
    </div>
  </div>
</template>

<script>
import {defineComponent, ref, watch} from "vue";
import { Codemirror } from 'vue-codemirror'
import {java} from '@codemirror/lang-java'
import {cpp} from '@codemirror/lang-cpp'
import {python} from '@codemirror/lang-python'
import { oneDark } from '@codemirror/theme-one-dark'
import {compileAndRun, compiler} from "@/api/oj/problem";

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
      version:{
        type: String,Number,
        default: '11'
      },
    examples:{
      type:Array,
      default: undefined
    },
    drawerVisible:{
      type: Boolean,
      default: false
    },
  },
  setup(props,context){
    // 编辑器选项
    const extensions= ref([cpp(),oneDark])
    const activeName=ref('test')
    const result=ref('');
      const form=ref({
          language: props.language,
          version: props.version,
          code: props.modelValue,
          input: undefined
      })
      const languages=ref([])
      const version=ref(new Map())
      const running=ref(false)

      getCompilers();
      function judgeTest(){
          running.value=true;
          if(!form.value.code||form.value.code.length==0){
              result.value='代码不能为空'
              activeName.value='result'
              running.value=false;
              return;
          }
          if(!form.value.code||form.value.code.length==0){
              result.value='代码不能为空'
              activeName.value='result'
              return;
          }
        compileAndRun(form.value).then(res=>{
            if(res.data.status=='Accepted'){
                result.value=res.data.output;
            }else {
                result.value=res.data.status
            }
        }).catch(reason => {
            result.value='编译错误,请在本地编译器查看代码是否正确，或者编译器选项是否选择正确'
        }).finally(()=>{
            activeName.value='result'
            running.value=false;
        })
      }

    function onLangChange(newVal) {

    }
    function onResetClick(){
      form.value.code=undefined;
    }
    function getUserLastAccepetedCode(){

    }

    function getCompilers() {
        compiler().then(res=>{
            languages.value=res.data
            res.data.forEach((language)=>{
                version.value.set(language.language,language.version)
            });
        })
    }
    //监听props分配给变量
    watch(()=>props.modelValue,(newVal,oldValue)=>{
       form.value.code=newVal
    })
    watch(()=>props.language,(newVal,oldVal)=>{
        form.value.language=newVal
    })
      watch(()=>props.version,(newVal,oldVal)=>{
          form.value.version=newVal
      })



    //监听变量，提交给父组件
    watch(()=>form.value.language,(newVal,oldVal)=>{
      context.emit('update:language',newVal);
      console.log(form.value.language)
      if(newVal=='Java'){
        extensions.value=[java(),oneDark]
      }else if(newVal=='C'||newVal=='C++'){
          extensions.value.extensions=[cpp(),oneDark]
      }else if(newVal=='Python3'||newVal=='Python2'){
          extensions.value.extensions=[python(),oneDark]
      }
    })

    watch(()=>form.value.code,(newVal,oldVal)=>{
      context.emit('update:modelValue',newVal);
    })
      watch(()=>form.value.version,(newVal,oldVal)=>{
          context.emit('update:version',newVal);
      })
    return{
      result,
      form,
      extensions,
      activeName,
      languages,
      version,
      running,
      judgeTest,
      onLangChange,
      onResetClick,
      getUserLastAccepetedCode,
    }
  },



})
</script>

<style scoped>
.my-drawer {
    position: relative;
    margin-top: -300px;
    z-index: 10;
    width: 100%;
    height: 300px;
    background-color: #FFFFFF;
    border: 1px solid #FEFEFE;
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
  width: 100px;
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