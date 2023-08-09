<template>
  <div style="margin: 0px 0px 15px 0px;font-size: 14px;position: relative">
    <el-row
        class="header"
        id="js-right-header"
    >
      <el-col
          :xs="24"
          :sm="16"
          :md="16"
          :lg="16"
      >
        <div class="select-row">
          <span>语言:</span>
          <span>
            <el-select
                v-model="options.language"
                @change="onLangChange"
                class="left-adjust"
                size="small"
            >
              <el-option
                  v-for="item in this.$props.languages"
                  :key="item"
                  :value="item"
              >{{ item }}
              </el-option>
            </el-select>
          </span>
          <span>
            <el-tooltip
                content="重置代码"
                placement="top"
            >
              <el-button
                  icon="Refresh"
                  @click="onResetClick"
                  size="small"
              ></el-button>
            </el-tooltip>
          </span>
          <span v-if="isAuthenticated && !submitDisabled">
            <el-tooltip
                :content="'m.Get_Recently_Passed_Code'"
                placement="top"
            >
              <el-button
                  icon="el-icon-download"
                  size="small"
                  @click="getUserLastAccepetedCode"
              >
              </el-button>
            </el-tooltip>
          </span>
        </div>
      </el-col>
      <el-col
          :xs="24"
          :sm="8"
          :md="8"
          :lg="8"
      >
        <div class="select-row fl-right">
          <span>
            <el-tooltip
                :content="'m.Upload_file'"
                placement="bottom"
            >
              <el-button
                  icon="el-icon-upload"
                  @click="onUploadFile"
                  size="small"
              ></el-button>
            </el-tooltip>
          </span>
          <span>
            <input
                type="file"
                id="file-uploader"
                style="display: none"
                @change="onUploadFileDone"
            />
          </span>
          <span>
            <el-tooltip
                :content="'m.Code_Editor_Setting'"
                placement="top"
            >
              <el-popover
                  placement="bottom"
                  width="300"
                  trigger="click"
              >
                <el-button
                    slot="reference"
                    icon="el-icon-s-tools"
                    size="small"
                >
                </el-button>
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
                  <el-select
                      :value="fontSize"
                      @change="onFontSizeChange"
                      class="setting-item-value"
                      size="small"
                  >
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
    <div :style="'line-height: 1.5;font-size:'+fontSize">
      <codemirror
          class="js-right"
          v-model="options.code"
          :extensions="options.extensions"
          :autofocus="true"
          :style="{ height: '648px' }"
          @change="onEditorCodeChange"
          ref="myEditor"
      >
      </codemirror>
    </div>
    <el-drawer
        :visible.sync="openTestCaseDrawer"
        style="position: absolute;"
        :modal="false"
        size="40%"
        :with-header="false"
        @close="closeDrawer"
        direction="btt"
    >
      <el-tabs
          v-model="testJudgeActiveTab"
          type="border-card"
          style="height: 100%;"
          @tab-click="handleClick"
      >
        <el-tab-pane
            label="测试用例"
            name="input"
            style="margin-right: 15px;margin-top: 8px;"
        >
          <div class="mt-10">
            <el-tag
                type="primary"
                class="tj-test-tag"
                size="samll"
                v-for="(example, index) of problemTestCase"
                :key="index"
                @click="addTestCaseToTestJudge(example.input, example.output, index)"
                :effect="example.active?'dark':'plain'"
            >
              填充用例 {{ index+1 }}
            </el-tag>
          </div>
          <el-input
              type="textarea"
              class="mt-10"
              :rows="7"
              show-word-limit
              resize="none"
              maxlength="1000"
              v-model="userInput"
          >
          </el-input>
        </el-tab-pane>
        <el-tab-pane
            label="运行结果"
            name="result"
        >
          <div v-loading="testJudgeLoding">
            <div v-if="testJudgeRes.status == -10">
              <div class="tj-res-tab mt-10">
                <el-alert
                    :title="$t('m.Non_Test_Judge_Tips')"
                    type="info"
                    center
                    :closable="false"
                    show-icon
                >
                </el-alert>
              </div>
            </div>
            <template v-else-if="testJudgeRes.status != -2">
              <div class="tj-res-tab">
                <el-alert
                    class="mt-10"
                    :type="getResultStausType(testJudgeRes.problemJudgeMode,testJudgeRes.status)"
                    :closable="false"
                    show-icon
                >
                  <template slot="title">
                    <span class="status-title">{{ getResultStatusName(testJudgeRes.problemJudgeMode,
                        testJudgeRes.status,
                        testJudgeRes.expectedOutput!=null) }}
                      <template v-if="equalsExpectedOuput != null">
                        {{ "("+ $t('m.Pass_Test_Case')+ " "+equalsExpectedOuput+")" }}
                      </template>
                    </span>
                  </template>
                  <template slot>
                    <div style="display:flex">
                      <div style="margin-right:15px">
                        <span class="color-gray mr-5"><i class="el-icon-time"></i></span>
                        <span class="color-gray mr-5">{{ $t('m.Time' )}}</span>
                        <span v-if="testJudgeRes.time!=null">{{testJudgeRes.time}}ms</span>
                        <span v-else>--ms</span>
                      </div>
                      <div>
                        <span
                            style="vertical-align: sub;"
                            class="color-gray mr-5"
                        >
                          <svg
                              data-v-79a9c93e=""
                              focusable="false"
                              viewBox="0 0 1025 1024"
                              fill="currentColor"
                              width="1.2em"
                              height="1.2em"
                              aria-hidden="true"
                          >
                            <path
                                d="M448.98 92.52V92.67l.37 40.46v62.75h125.5V92.52h81.22v103.36h33.12c76.08 0 137.9 61.05 139.12 136.84l.02 2.3v33.12h103.36v80.84l-40.6.37h-62.76v91.05h103.36v81.21H828.33v67.58c0 76.08-61.06 137.9-136.84 139.12l-2.3.02h-33.12v103.36h-80.84l-.37-40.6v-62.76H449.25l-.27 103.36h-80.84V828.33h-33.12c-76.08 0-137.9-61.06-139.12-136.84l-.02-2.3v-67.58H92.52v-80.83l40.6-.38h62.76v-91.15l-103.36-.27v-80.47l40.6-.37h62.76v-33.12c0-76.08 61.05-137.9 136.84-139.12l2.3-.02h33.12V92.52h80.84zM689.2 277.1H335.02c-32 0-57.93 25.93-57.93 57.93v354.17c0 32 25.93 57.93 57.93 57.93h354.17c32 0 57.93-25.94 57.93-57.93V335.02c0-32-25.94-57.93-57.93-57.93zm-73.73 91.05a40.6 40.6 0 0 1 40.6 40.6v206.72a40.6 40.6 0 0 1-40.6 40.6H408.75a40.6 40.6 0 0 1-40.6-40.6V408.75a40.6 40.6 0 0 1 40.6-40.6zm-40.6 81.16H449.3v125.55h125.55V449.3z"
                                transform="translate(1)"
                            >
                            </path>
                          </svg>
                        </span>
                        <span class="color-gray mr-5">{{ $t('m.Memory' )}}</span>
                        <span v-if="testJudgeRes.memory!=null">{{testJudgeRes.memory}}KB</span>
                        <span v-else>--KB</span>
                      </div>
                    </div>
                    <div v-if="testJudgeRes.stderr">
                      {{ testJudgeRes.stderr }}
                    </div>
                    <div
                        v-if="testJudgeRes.problemJudgeMode == 'spj'
                          && (testJudgeRes.status == 0 || testJudgeRes.status == -1)"
                        style="font-weight: 700;"
                    >
                      {{ $t('m.Problem_Uncertain_Answer') }}
                    </div>
                  </template>
                </el-alert>
              </div>
              <div class="tj-res-tab">
                <div class="tj-res-item">
                  <span class="name">{{ $t('m.Test_Input') }}</span>
                  <span class="value">
                    <el-input
                        type="textarea"
                        class="textarea"
                        :readonly="true"
                        resize="none"
                        :autosize="{ minRows: 1, maxRows: 4}"
                        v-model="testJudgeRes.userInput"
                    >
                    </el-input>
                  </span>
                </div>
                <div
                    class="tj-res-item"
                    v-if="testJudgeRes.expectedOutput!=null"
                >
                  <span class="name">{{ $t('m.Expected_Output') }}</span>
                  <span class="value">
                    <el-input
                        type="textarea"
                        :readonly="true"
                        resize="none"
                        :autosize="{ minRows: 1, maxRows: 4}"
                        class="textarea"
                        v-model="testJudgeRes.expectedOutput"
                    >
                    </el-input>
                  </span>
                </div>
                <div class="tj-res-item">
                  <span class="name">{{ $t('m.Real_Output') }}</span>
                  <span class="value">
                    <el-input
                        type="textarea"
                        class="textarea"
                        :readonly="true"
                        resize="none"
                        :autosize="{ minRows: 1, maxRows: 4}"
                        v-model="testJudgeRes.userOutput"
                    >
                    </el-input>
                  </span>
                </div>
              </div>
            </template>
            <template v-else>
              <div class="tj-res-tab mt-10">
                <el-card>
                  <div slot="header">
                    <span class="ce-title">{{ $t('m.Compilation_Failed') }}</span>
                  </div>
                  <div style="color: #f90;font-weight: 600;">
                    <pre>{{ testJudgeRes.stderr }}</pre>
                  </div>
                </el-card>
              </div>
            </template>
          </div>
        </el-tab-pane>
        <el-tab-pane>
          <span slot="label">
            <el-tag
                type="success"
                class="tj-btn"
                @click="submitTestJudge"
                effect="plain"
            >
              <i class="el-icon-video-play">正在运行</i>
            </el-tag>
          </span>
          <template v-if="!isAuthenticated">
            <div class="tj-res-tab mt-10">
              <el-alert
                  title="请先登录"
                  type="warning"
                  center
                  :closable="false"
                  show-icon
              >
              </el-alert>
            </div>
          </template>
        </el-tab-pane>
      </el-tabs>
    </el-drawer>
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
    //自测运行结果
    const testJudgeRes=ref({
      status: -10,
    });
    function onLangChange(newVal) {

    }
    function onResetClick(){
      options.value.code=undefined;
    }
    return{
      options,
      testJudgeRes,
      onLangChange,
      onResetClick
    }
  },



})
</script>

<style scoped>
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