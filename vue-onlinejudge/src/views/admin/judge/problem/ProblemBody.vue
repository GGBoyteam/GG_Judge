<template>
  <div class="problem-body">
    <div id="problem-main">
      <!--problem main-->
      <el-row class="problem-box"
              :id="'problem-box' + '-' + $route.name">
        <el-col :sm="24" :md="11" :lg="12" class="problem-left" :id="'problem-left'+'-'+ $route.name" style="height: 100%">
          <el-tabs model-value="problemDetail" type="border-card">
            <el-tab-pane name="problemDetail" :v-loading="false">
              <template #label>
                  <span class="custom-tabs-label">
                    <el-icon><calendar /></el-icon>
                    <span>题目编辑</span>
                   </span>
              </template>
              <div style="padding: 10px 10px 10px 10px" type="shadow" :id="'js-left'+'-'+ $route.name" class="js-left">
                <div id="problem-content">
                  <div>
                    <p class="title">描述</p>
                    <VditorEdit name="description" v-model="problemInfo.description"></VditorEdit>
                  </div>

                  <div>
                    <p class="title">输入描述</p>
                    <VditorEdit name="input_description" v-model="problemInfo.inputDescription"></VditorEdit>
                  </div>

                  <div>
                    <p class="title">输出描述</p>
                    <VditorEdit name="output_description" v-model="problemInfo.outputDescription"></VditorEdit>
                  </div>
                  <div>
                    <p class="title">样例描述</p>
                    <VditorEdit name="example_description" v-model="problemInfo.exampleDescription"></VditorEdit>
                  </div>
                </div>
              </div>
            </el-tab-pane>
          </el-tabs>
        </el-col>
        <el-col :sm="24" :md="11" :lg="12 " class="problem-left" :id="'problem-left'+'-'+ $route.name" style="height: 100%">
          <el-tabs model-value="problemDetail" type="border-card">
            <el-tab-pane name="problemDetail" :v-loading="false">
              <template #label>
                  <span class="custom-tabs-label">
                    <el-icon><calendar /></el-icon>
                    <span>题目预览</span>
                   </span>
              </template>
              <div style="padding: 10px 10px 10px 10px" type="shadow" :id="'js-left'+'-'+ $route.name" class="js-left">
                <div slot="header" class="panel-title">
                  <span style="font-size: 20px">{{problemInfo.title}}</span><br />
                  <div class="problem-tag">
                    <span style="padding-right: 10px">
                      <el-popover placement="bottom" trigger="hover">
                        <template #reference>
                          <el-tag type="warning" style="cursor: pointer;" effect="dark">
                            <el-icon><Document /></el-icon>
                            文件IO
                          </el-tag>
                        </template>
                        <table style="white-space: nowrap;">
                          <tbody>
                            <tr>
                              <td align="right" style="padding-right: 10px"><strong>输入文件</strong></td>
                              <td>a+b.in</td>
                            </tr>
                            <tr>
                              <td align="right" style="padding-right: 10px"><strong>输出文件</strong></td>
                              <td>a+b.out</td>
                            </tr>
                          </tbody>
                        </table>
                      </el-popover>
                    </span>
                    <span style="padding-right: 10px">
                      <el-tag effect="plain">竞赛题目</el-tag>
                    </span>
                    <span style="padding-right: 10px" v-if="problemInfo.tags&&problemInfo.tags.length>0">
                      <el-popover
                          placement="right-start"
                          width="60"
                          trigger="hover"
                      >
                        <template #reference>
                          <el-tag
                              style="cursor: pointer;"
                              effect="plain"
                          >显示标签 <el-icon><CaretBottom /></el-icon></el-tag>
                        </template>
                        <el-tag
                            effect="dark"
                            v-for="(tag,index) in problemInfo.tags"
                            :key="index"
                            :color="tag.color? tag.color : '#409eff'"
                            style="margin-right:5px;margin-top:2px;border: 0"
                        >{{tag.title}}</el-tag>
                      </el-popover>
                    </span>
                    <span v-else>
                      <el-tag effect="plain">没有标签</el-tag>
                    </span>
                  </div>

                  <div class="problem-menu">
                    <span>
                      <el-link
                          type="primary"
                          :underline="false"
                      ><el-icon><Comment /></el-icon>题目讨论</el-link>
                    </span>
                    <span>
                      <el-link
                          type="primary"
                          :underline="false"
                      ><el-icon><Histogram /></el-icon>题目统计</el-link>
                    </span>
                    <span>
                      <el-link
                          type="primary"
                          :underline="false"
                      ><el-icon><List /></el-icon>全部提交</el-link>
                    </span>
                  </div>
                  <div class="question-intr">
                    <span>时间限制：C/C++ 1000MS，其他语言2000MS</span><br />
                    <span>内存限制：C/C++256MB，其他语言512MB</span><br />
                    <span>难度：入门</span><br />
                    <span>作者：xiaozhang</span>
                  </div>
                </div>
                <div id="problem-content">
                  <div>
                    <p class="title">描述</p>
                    <VditorPreview name="description_preview" v-model="problemInfo.description"></VditorPreview>
                  </div>

                  <div>
                    <p class="title">输入描述</p>
                    <VditorPreview name="input_description_preview" v-model="problemInfo.inputDescription"></VditorPreview>
                  </div>

                  <div>
                    <p class="title">输出描述</p>
                    <VditorPreview name="output_descriptionp_preview" v-model="problemInfo.outputDescription"></VditorPreview>
                  </div>

                  <div>
                    <div v-for="(example,index) in problemInfo.examples" :key="index">
                      <div class="flex-container example">
                        <div class="example-input">
                          <p class="title">
                            用户输入{{index}}
                            <!--                            v-clipboard:copy="example.input"-->
                            <!--                            v-clipboard:success="onCopy"-->
                            <!--                            v-clipboard:error="onCopyError"-->
                            <a class="copy">
                              <el-icon><CopyDocument /></el-icon>
                            </a>
                          </p>
                          <pre>{{example.input}}</pre>
                        </div>
                        <div class="example-output">
                          <p class="title">
                            用户输出{{index}}
                            <a class="copy">
                              <el-icon><CopyDocument /></el-icon>
                            </a>
                          </p>
                          <pre>{{example.output}}</pre>
                        </div>
                      </div>
                    </div>
                  </div>

                  <div v-show="problemInfo.exampleDescription&&problemInfo.exampleDescription.length>1">
                    <p class="title">样例描述</p>
                    <VditorPreview name="example_description_preview" v-model="problemInfo.exampleDescription"></VditorPreview>
                  </div>
                </div>
              </div>
            </el-tab-pane>
          </el-tabs>
        </el-col>
      </el-row>
    </div>
    <div class="problem-bottom">
      <div class="button-group">
        <el-button type="primary" @click="Submission">更新题面</el-button>
        <el-button @click="Cancel">取消设置</el-button>
      </div>
    </div>
  </div>
</template>
<script >
import {ref, watch} from "vue";
import VditorPreview from "@/components/VditorPreview/index.vue";
import CodeInput from "@/components/CodeInput/index.vue";
import {getToken} from "@/utils/auth";
import {getProblem, updateProblemBody} from "@/api/oj/problem";
import {delConfig} from "@/api/system/config";
import router from "@/router";
import {useRouter} from "vue-router";
export default {
  components: {CodeInput, VditorPreview},
  setup(){
    const pid=ref(undefined);
    const { proxy } = getCurrentInstance();
    const router = useRouter();
    const problemInfo=ref({
      pid: undefined,
      title: '测试标题',
      tags: [
        {
          name:'标签1',
          color: '#D9D9F3'
        },
        {
          name:'标签2',
          color: '#D9D9F3'
        }
      ],
      description: '题目描述',
      inputDescription: '输入描述',
      outputDescription: '输出描述',
      exampleDescription: '样例描述',
      examples:[]
    });
    function getProblemInfo(pid){
      getProblem(pid).then((res)=>{
        this.problemInfo=res.data;
      })
    }

    function Submission(){
      problemInfo.value.pid=pid.value
      updateProblemBody(problemInfo.value).then(res=>{
        getProblem(pid.value)
        proxy.$modal.confirm('更新成功,是否离开当前页面？').then(function () {
          let obj={ path: "/judge/problem" }
          proxy.$tab.closeOpenPage(obj);
        }).then(function (){
          router.push('/admin/judge/problem')
        }).catch(() => {})
      })
    }
    function Cancel(){
      proxy.$modal.confirm('是否离开当前页面？').then(function () {
        let obj={ path: "/judge/problem" }
        proxy.$tab.closeOpenPage(obj);
      }).then(function (){
        router.push('/admin/judge/problem')
      }).catch(() => {})
    }

    return{
      pid,
      problemInfo,
      getProblemInfo,
      Submission,
      Cancel
    };
  },
  created() {
    this.pid=this.$route.query.pid;
    this.getProblemInfo(this.pid)
  }
}

</script>



<style scoped>
.katex .katex-mathml {
  display: none;
}
</style>

<style scoped>
.problem-menu {
  float: left;
}
a {
  color: #3091f2 !important ;
}
.problem-menu span {
  margin-left: 5px;
}
.el-link {
  font-size: 16px !important;
}
.author-name {
  font-size: 14px !important;
  color: #909399 !important;
}
.question-intr {
  margin-top: 30px;
  border-radius: 4px;
  border: 1px solid #ddd;
  border-left: 2px solid #3498db;
  background: #fafafa;
  padding: 10px;
  line-height: 1.8;
  margin-bottom: 10px;
  font-size: 14px;
}

.extra-file {
  margin: 10px;
  cursor: pointer;
}
.file-download {
  vertical-align: bottom;
  float: right;
  margin-right: 5px;
}

.submit-detail {
  height: 100%;
}

/deep/.el-tabs--border-card > .el-tabs__content {
  padding-top: 0px;
  padding-right: 0px;
  padding-bottom: 0px;
}

.js-left {
  padding-right: 15px;
}
@media screen and (min-width: 992px) {
  .problem-body {
  }
  .js-left {
    height: 730px !important;
    overflow-y: auto;
  }
  #js-extraFile {
    overflow-y: auto;
  }
  #js-submission {
    overflow-y: auto;
  }
  .submit-detail {
    overflow-y: auto;
  }
  .js-right {
    height: 635px !important;
  }
  #js-right-bottom {
    height: 49px;
  }
  .problem-tag {
    display: inline;
  }
  .problem-menu {
    float: right;
  }
  .problem-menu span {
    margin-left: 10px;
  }
  .question-intr {
    margin-top: 6px;
  }
}

@media screen and (min-width: 992px) {
  .problem-box {
    width: 100%;
    height: 100%;
    overflow: hidden;
  }
  .problem-left {
    width: 50%; /*左侧初始化宽度*/
    height: 100%;
    overflow-y: auto;
    overflow-x: hidden;
    float: left;
  }
  .problem-resize {
    cursor: col-resize;
    position: absolute;
    top: 330px;
    left: 50%;
    background-color: #d6d6d6;
    border-radius: 5px;
    width: 10px;
    height: 50px;
    background-size: cover;
    background-position: center;
    font-size: 32px;
    color: white;
  }
  .problem-resize:hover .right-fold {
    display: block;
  }
  .problem-resize:hover .fold:before {
    content: "";
    position: absolute;
    display: block;
    width: 6px;
    height: 24px;
    left: -6px;
  }
  .right-fold {
    position: absolute;
    display: none;
    font-weight: bolder;
    margin-left: 15px;
    margin-top: -35px;
    cursor: pointer;
    z-index: 1000;
    text-align: center;
  }
  .left-fold {
    position: absolute;
    font-weight: bolder;
    margin-left: -40px;
    margin-top: 10px;
    cursor: pointer;
    z-index: 1000;
    text-align: center;
  }
  .fold:hover {
    color: #409eff;
    background: #fff;
  }

  /*拖拽区鼠标悬停样式*/
  .problem-resize:hover {
    color: #444444;
  }
  .problem-right {
    height: 100%;
    float: left;
    width: 50%;
  }
}

@media screen and (max-width: 992px) {
  .submit-detail {
    padding-top: 20px;
  }
  .submit-detail {
    height: 100%;
  }
}
/deep/ .el-card__header {
  border-bottom: 0px;
  padding-bottom: 0px;
}
/deep/ .el-card__body{
  padding-bottom: 5px !important;
}
#right-column {
  flex: none;
  width: 220px;
}

#problem-content {
}
#problem-content .title {
  font-size: 16px;
  font-weight: 600;
  margin: 25px 0 8px 0;
  color: #3091f2;
}
#problem-content .copy {
  padding-left: 8px;
}

.hint-content {
  margin: 1em 0;
  font-size: 15px !important;
}

.md-content {
  margin: 1em;
  font-size: 15px;
}
.flex-container {
  display: flex;
  width: 100%;
  max-width: 100%;
  justify-content: space-around;
  align-items: flex-start;
  flex-flow: row nowrap;
}

.example {
  align-items: stretch;
}
.example-input,
.example-output {
  width: 50%;
  flex: 1 1 auto;
  display: flex;
  flex-direction: column;
}
.example pre {
  flex: 1 1 auto;
  align-self: stretch;
  border-style: solid;
  background: transparent;
  padding: 5px 10px;
  white-space: pre;
  margin-top: 10px;
  margin-bottom: 10px;
  background: #f1f1f1;
  border: 1px dashed #e9eaec;
  overflow: auto;
  font-size: 1.1em;
  margin-right: 7%;
}
#submit-code {
  height: auto;
}
#submit-code .status {
  float: left;
}
.submission-status:hover {
  cursor: pointer;
}
#submit-code .status span {
  margin-left: 10px;
}
.captcha-container {
  display: inline-block;
}
.captcha-container .captcha-code {
  width: auto;
  margin-top: -20px;
  margin-left: 20px;
}

.fl-right {
  float: right;
}
/deep/.el-dialog__body {
  padding: 10px 10px !important;
}
#pieChart .echarts {
  height: 250px;
  width: 210px;
}
#pieChart #detail {
  position: absolute;
  right: 10px;
  top: 10px;
}
/deep/.echarts {
  width: 350px;
  height: 350px;
}
#pieChart-detail {
  /* margin-top: 20px; */
  height: 350px;
}
.tj-btn {
  margin-right: 15px;
  float: right;
  cursor: pointer;
}
.tj-btn.non-active {
  border: 1px solid #32ca99;
}
.tj-btn.non-active:hover {
  background-color: #d5f1eb;
}
.tj-btn.active {
  background-color: #67c23a;
  border-color: #67c23a;
  color: #fff;
}
.problem-bottom{
  margin-top: 15px;
  width: 100%;
  height: 50px;
  display:flex; justify-content:center; align-items:center;

}
</style>