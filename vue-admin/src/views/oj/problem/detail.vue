<script >
import {ref, watch} from "vue";
import VditorPreview from "@/components/VditorPreview/index.vue";
export default {
  components: {VditorPreview},
  setup(){
    const res=ref("# 标题aaa # aaaa \n# aaa");
    const v2=ref("# 标题aaa # aaaa \n# aaa")
    const v3=ref("# 标题aaa # aaaa \n# aaa")
    return{
      res,
      v2,
      v3
    };
  }
}

</script>

<template>
  <div class="problem-body">
    <div id="problem-main">
      <!--problem main-->
      <el-row class="problem-box"
              :id="'problem-box' + '-' + $route.name">
        <el-col
            :sm="24"
            :md="12"
            :lg="12"
            class="problem-left"
            :id="'problem-left'+'-'+ $route.name"
        >
          <el-tabs
              model-value="problemDetail"
              type="border-card"
              @tab-click="handleClickTab"
          >
            <el-tab-pane name="problemDetail" :v-loading="false">
              <template #label>
                  <span class="custom-tabs-label">
                    <el-icon><calendar /></el-icon>
                    <span>题目描述</span>
                   </span>
              </template>
              <div style="padding: 10px 10px 10px 10px" type="shadow" :id="'js-left'+'-'+ $route.name" class="js-left">
                <div slot="header" class="panel-title">
                  <span style="font-size: 20px">题目标题</span><br />
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
                    <span style="padding-right: 10px">
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

<!--                        <el-tag-->
<!--                            v-for="(tag, index) in problemData.tags"-->
<!--                            :key="index"-->
<!--                            size="small"-->
<!--                            :color="tag.color ? tag.color : '#409eff'"-->
<!--                            effect="dark"-->
<!--                            style="margin-right:5px;margin-top:2px"-->
<!--                        >{{ tag.name }}</el-tag>-->
                        <el-tag effect="plain">竞赛题目</el-tag>
                        <el-tag effect="plain">竞赛题目</el-tag>
                        <el-tag effect="plain">竞赛题目</el-tag>
                      </el-popover>
                    </span>
                    <span>
                      <el-tag effect="plain">没有标签</el-tag>
                    </span>
                  </div>

                  <div class="problem-menu">
                    <span>
                      <el-link
                          type="primary"
                          :underline="false"
                          @click="goProblemDiscussion"
                      ><el-icon><Comment /></el-icon>题目讨论</el-link>
                    </span>
                    <span>
                      <el-link
                          type="primary"
                          :underline="false"
                          @click="graphVisible = !graphVisible"
                      ><el-icon><Histogram /></el-icon>题目统计</el-link>
                    </span>
                    <span>
                      <el-link
                          type="primary"
                          :underline="false"
                          @click="goProblemSubmission"
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
                    <VditorPreview name="description" v-model="res"></VditorPreview>
                  </div>

                  <div>
                    <p class="title">输入描述</p>
                    <VditorPreview name="input_description" v-model="v2"></VditorPreview>
                  </div>

                  <div>
                    <p class="title">输出描述</p>
                    <VditorPreview name="output_description" v-model="v3"></VditorPreview>
                  </div>

                  <div>
                    <div>
                      <div class="flex-container example">
                        <div class="example-input">
                          <p class="title">
                            用户输入1
<!--                            v-clipboard:copy="example.input"-->
<!--                            v-clipboard:success="onCopy"-->
<!--                            v-clipboard:error="onCopyError"-->
                            <a class="copy">
                              <el-icon><CopyDocument /></el-icon>
                            </a>
                          </p>
                          <pre>1</pre>
                        </div>
                        <div class="example-output">
                          <p class="title">
                            用户输出1
                            <a class="copy">
                              <el-icon><CopyDocument /></el-icon>
                            </a>
                          </p>
                          <pre>2</pre>
                        </div>
                      </div>
                    </div>
                  </div>

                  <div>
                    <p class="title">样例描述</p>
                    <VditorPreview name="example_description" v-model="v3"></VditorPreview>
                  </div>
                </div>
              </div>
            </el-tab-pane>
            <el-tab-pane name="mySubmission">
              <template #label>
                  <span class="custom-tabs-label">
                    <el-icon><Timer /></el-icon>
                    <span>我的提交</span>
                   </span>
              </template>
              <div v-if="!isAuthenticated">
                <div
                    style="margin-left:-20px;"
                    id="js-submission"
                >
                  <el-alert
                      title="请先登录!"
                      type="warning"
                      center
                      :closable="false"
                      description="登录以查看您的提交记录"
                      show-icon
                  >
                  </el-alert>
                </div>
              </div>
              <div v-else>
                <div style="margin-right:10px;" id="js-submission">
                  <el-table>

                  </el-table>
<!--                  <vxe-table-->
<!--                      align="center"-->
<!--                      :data="mySubmissions"-->
<!--                      stripe-->
<!--                      auto-resize-->
<!--                      border="inner"-->
<!--                      :loading="loadingTable"-->
<!--                  >-->
<!--                    <vxe-table-column-->
<!--                        :title="$t('m.Submit_Time')"-->
<!--                        min-width="96"-->
<!--                    >-->
<!--                      <template v-slot="{ row }">-->
<!--                        <span>-->
<!--                          <el-tooltip-->
<!--                              :content="row.submitTime | localtime"-->
<!--                              placement="top"-->
<!--                          >-->
<!--                            <span>{{ row.submitTime | fromNow }}</span>-->
<!--                          </el-tooltip>-->
<!--                        </span>-->
<!--                      </template>-->
<!--                    </vxe-table-column>-->
<!--                    <vxe-table-column-->
<!--                        field="status"-->
<!--                        :title="$t('m.Status')"-->
<!--                        min-width="160"-->
<!--                    >-->
<!--                      <template v-slot="{ row }">-->
<!--                        <span :class="getStatusColor(row.status)">{{-->
<!--                            JUDGE_STATUS[row.status].name-->
<!--                          }}</span>-->
<!--                      </template>-->
<!--                    </vxe-table-column>-->
<!--                    <vxe-table-column-->
<!--                        :title="$t('m.Time')"-->
<!--                        min-width="96"-->
<!--                    >-->
<!--                      <template v-slot="{ row }">-->
<!--                        <span>{{ submissionTimeFormat(row.time) }}</span>-->
<!--                      </template>-->
<!--                    </vxe-table-column>-->
<!--                    <vxe-table-column-->
<!--                        :title="$t('m.Memory')"-->
<!--                        min-width="96"-->
<!--                    >-->
<!--                      <template v-slot="{ row }">-->
<!--                        <span>{{ submissionMemoryFormat(row.memory) }}</span>-->
<!--                      </template>-->
<!--                    </vxe-table-column>-->
<!--                    <vxe-table-column-->
<!--                        :title="$t('m.Score')"-->
<!--                        min-width="64"-->
<!--                        v-if="problemData.problem.type == 1"-->
<!--                    >-->
<!--                      <template v-slot="{ row }">-->
<!--                        <template v-if="contestID && row.score != null">-->
<!--                          <el-tag-->
<!--                              effect="plain"-->
<!--                              size="medium"-->
<!--                              :type="JUDGE_STATUS[row.status]['type']"-->
<!--                          >{{ row.score }}</el-tag>-->
<!--                        </template>-->
<!--                        <template v-else-if="row.score != null">-->
<!--                          <el-tooltip placement="top">-->
<!--                            <div slot="content">-->
<!--                              {{ $t('m.Problem_Score') }}：{{-->
<!--                                row.score != null ? row.score : $t('m.Unknown')-->
<!--                              }}<br />{{ $t('m.OI_Rank_Score') }}：{{-->
<!--                                row.oiRankScore != null-->
<!--                                    ? row.oiRankScore-->
<!--                                    : $t('m.Unknown')-->
<!--                              }}<br />-->
<!--                              {{-->
<!--                                $t('m.OI_Rank_Calculation_Rule')-->
<!--                              }}：(score*0.1+difficulty*2)-->
<!--                            </div>-->
<!--                            <el-tag-->
<!--                                effect="plain"-->
<!--                                size="medium"-->
<!--                                :type="JUDGE_STATUS[row.status]['type']"-->
<!--                            >{{ row.score }}</el-tag>-->
<!--                          </el-tooltip>-->
<!--                        </template>-->
<!--                        <template v-else-if="-->
<!--                            row.status == JUDGE_STATUS_RESERVE['Pending'] ||-->
<!--                              row.status == JUDGE_STATUS_RESERVE['Compiling'] ||-->
<!--                              row.status == JUDGE_STATUS_RESERVE['Judging']-->
<!--                          ">-->
<!--                          <el-tag-->
<!--                              effect="plain"-->
<!--                              size="medium"-->
<!--                              :type="JUDGE_STATUS[row.status]['type']"-->
<!--                          >-->
<!--                            <i class="el-icon-loading"></i>-->
<!--                          </el-tag>-->
<!--                        </template>-->
<!--                        <template v-else>-->
<!--                          <el-tag-->
<!--                              effect="plain"-->
<!--                              size="medium"-->
<!--                              :type="JUDGE_STATUS[row.status]['type']"-->
<!--                          >&#45;&#45;</el-tag>-->
<!--                        </template>-->
<!--                      </template>-->
<!--                    </vxe-table-column>-->
<!--                    <vxe-table-column-->
<!--                        field="language"-->
<!--                        :title="$t('m.Language')"-->
<!--                        show-overflow-->
<!--                        min-width="130"-->
<!--                    >-->
<!--                      <template v-slot="{ row }">-->
<!--                        <el-tooltip-->
<!--                            class="item"-->
<!--                            effect="dark"-->
<!--                            :content="$t('m.View_submission_details')"-->
<!--                            placement="top"-->
<!--                        >-->
<!--                          <el-button-->
<!--                              type="text"-->
<!--                              @click="showSubmitDetail(row)"-->
<!--                          >{{ row.language }}</el-button>-->
<!--                        </el-tooltip>-->
<!--                      </template>-->
<!--                    </vxe-table-column>-->
<!--                  </vxe-table>-->
<!--                  <Pagination-->
<!--                      :total="mySubmission_total"-->
<!--                      :page-size="mySubmission_limit"-->
<!--                      @on-change="getMySubmission"-->
<!--                      :current.sync="mySubmission_currentPage"-->
<!--                  ></Pagination>-->
                </div>
              </div>
            </el-tab-pane>

<!--            <el-tab-pane-->
<!--                name="extraFile"-->
<!--                v-if="userExtraFile"-->
<!--            >-->
<!--              <span slot="label"><i class="fa fa-file-code-o"> {{ $t('m.Problem_Annex') }}</i>-->
<!--              </span>-->
<!--              <div id="js-extraFile">-->
<!--                <el-divider></el-divider>-->
<!--                <div>-->
<!--                  <el-tag-->
<!--                      :key="index"-->
<!--                      v-for="(value, key, index) in userExtraFile"-->
<!--                      class="extra-file"-->
<!--                      :disable-transitions="false"-->
<!--                      @click="showExtraFileContent(key, value)"-->
<!--                  >-->
<!--                    <i class="fa fa-file-code-o"> {{ key }}</i>-->
<!--                  </el-tag>-->
<!--                </div>-->
<!--                <el-divider></el-divider>-->

<!--                <div-->
<!--                    class="markdown-body"-->
<!--                    v-if="fileContent"-->
<!--                >-->
<!--                  <h3>-->
<!--                    {{ fileName }}-->
<!--                    <el-button-->
<!--                        type="primary"-->
<!--                        icon="el-icon-download"-->
<!--                        size="small"-->
<!--                        circle-->
<!--                        @click="downloadExtraFile"-->
<!--                        class="file-download"-->
<!--                    ></el-button>-->
<!--                  </h3>-->
<!--                  <pre v-highlight="fileContent"><code class="c++"></code></pre>-->
<!--                </div>-->
<!--              </div>-->
<!--            </el-tab-pane>-->
          </el-tabs>
        </el-col>
<!--        <div-->
<!--            class="problem-resize hidden-sm-and-down"-->
<!--            :id="'js-center'+'-'+ $route.name"-->
<!--            :title="$t('m.Shrink_Sidebar')"-->
<!--        >-->
<!--          <span>⋮</span>-->
<!--          <span>-->
<!--            <el-tooltip-->
<!--                :content="-->
<!--              toWatchProblem-->
<!--                ? $t('m.View_Problem_Content')-->
<!--                : $t('m.Only_View_Problem')-->
<!--            "-->
<!--                placement="right"-->
<!--                v-if="!toResetWatch"-->
<!--            >-->
<!--              <el-button-->
<!--                  icon="el-icon-caret-right"-->
<!--                  circle-->
<!--                  class="right-fold fold"-->
<!--                  @click.stop="onlyWatchProblem"-->
<!--                  size="mini"-->
<!--              ></el-button>-->
<!--            </el-tooltip>-->
<!--            <el-tooltip-->
<!--                :content="$t('m.Put_away_the_full_screen_and_write_the_code')"-->
<!--                placement="left"-->
<!--                v-else-->
<!--            >-->
<!--              <el-button-->
<!--                  icon="el-icon-caret-left"-->
<!--                  circle-->
<!--                  class="left-fold fold"-->
<!--                  @click.stop="resetWatch(false)"-->
<!--                  size="mini"-->
<!--              ></el-button>-->
<!--            </el-tooltip>-->
<!--          </span>-->
<!--        </div>-->
<!--        <el-col-->
<!--            :sm="24"-->
<!--            :md="12"-->
<!--            :lg="12"-->
<!--            class="problem-right"-->
<!--            :id="'problem-right' + '-' + $route.name"-->
<!--        >-->
<!--          <el-card-->
<!--              :padding="10"-->
<!--              id="submit-code"-->
<!--              shadow="always"-->
<!--              class="submit-detail"-->
<!--          >-->
<!--            <CodeMirror-->
<!--                :value.sync="code"-->
<!--                :languages="problemData.languages"-->
<!--                :language.sync="language"-->
<!--                :theme.sync="theme"-->
<!--                :height.sync="height"-->
<!--                :fontSize.sync="fontSize"-->
<!--                :tabSize.sync="tabSize"-->
<!--                @resetCode="onResetToTemplate"-->
<!--                @changeTheme="onChangeTheme"-->
<!--                @changeLang="onChangeLang"-->
<!--                @getUserLastAccepetedCode="getUserLastAccepetedCode"-->
<!--                @switchFocusMode="switchFocusMode"-->
<!--                :openFocusMode.sync="openFocusMode"-->
<!--                :openTestCaseDrawer.sync="openTestCaseDrawer"-->
<!--                :problemTestCase="problemData.problem.examples"-->
<!--                :pid="problemData.problem.id"-->
<!--                :type="problemType"-->
<!--                :isAuthenticated="isAuthenticated"-->
<!--                :isRemoteJudge="problemData.problem.isRemote"-->
<!--                :submitDisabled="submitDisabled"-->
<!--            ></CodeMirror>-->
<!--            <div id="js-right-bottom">-->
<!--              <el-row>-->
<!--                <el-col-->
<!--                    :sm="24"-->
<!--                    :md="10"-->
<!--                    :lg="10"-->
<!--                    style="margin-top:4px;"-->
<!--                >-->
<!--                  <div v-if="!isAuthenticated">-->
<!--                    <el-alert-->
<!--                        type="info"-->
<!--                        show-icon-->
<!--                        effect="dark"-->
<!--                        :closable="false"-->
<!--                    >{{ $t('m.Please_login_first') }}</el-alert>-->
<!--                  </div>-->
<!--                  <div-->
<!--                      class="status"-->
<!--                      v-if="statusVisible"-->
<!--                  >-->
<!--                    <template v-if="result.status == JUDGE_STATUS_RESERVE['sf']">-->
<!--                      <span>{{ $t('m.Status') }}:</span>-->
<!--                      <el-tag-->
<!--                          effect="dark"-->
<!--                          :color="submissionStatus.color"-->
<!--                          @click.native="reSubmit(submissionId)"-->
<!--                      >-->
<!--                        <i class="el-icon-refresh"></i>-->
<!--                        {{ submissionStatus.text }}-->
<!--                      </el-tag>-->
<!--                    </template>-->
<!--                    <template v-else-if="result.status == JUDGE_STATUS_RESERVE['snr']">-->
<!--                      <el-alert-->
<!--                          type="warning"-->
<!--                          show-icon-->
<!--                          effect="dark"-->
<!--                          :closable="false"-->
<!--                      >{{ $t('m.Submitted_Not_Result') }}</el-alert>-->
<!--                    </template>-->
<!--                    <template v-else-if="-->
<!--                        !this.contestID ||-->
<!--                          (this.contestID &&-->
<!--                            ContestRealTimePermission &&-->
<!--                            this.contestRuleType == RULE_TYPE.OI) ||-->
<!--                          (this.contestID &&-->
<!--                            this.contestRuleType == RULE_TYPE.ACM)-->
<!--                      ">-->
<!--                      <span style="font-size: 14px;font-weight: bolder;">{{ $t('m.Status') }}:</span>-->
<!--                      <el-tooltip-->
<!--                          class="item"-->
<!--                          effect="dark"-->
<!--                          :content="$t('m.View_submission_details')"-->
<!--                          placement="top"-->
<!--                      >-->
<!--                        <el-tag-->
<!--                            effect="dark"-->
<!--                            class="submission-status"-->
<!--                            :color="submissionStatus.color"-->
<!--                            @click.native="submissionRoute"-->
<!--                        >-->
<!--                          <template v-if="this.result.status == JUDGE_STATUS_RESERVE['Pending']-->
<!--                          || this.result.status == JUDGE_STATUS_RESERVE['Compiling']-->
<!--                          || this.result.status == JUDGE_STATUS_RESERVE['Judging']-->
<!--                          || this.result.status == JUDGE_STATUS_RESERVE['Submitting']">-->
<!--                            <i class="el-icon-loading"></i> {{ submissionStatus.text }}-->
<!--                          </template>-->
<!--                          <template v-else-if="this.result.status == JUDGE_STATUS_RESERVE.ac">-->
<!--                            <i class="el-icon-success"> {{ submissionStatus.text }}</i>-->
<!--                          </template>-->
<!--                          <template v-else-if="this.result.status == JUDGE_STATUS_RESERVE.pa">-->
<!--                            <i class="el-icon-remove"> {{ submissionStatus.text }}</i>-->
<!--                          </template>-->
<!--                          <template v-else>-->
<!--                            <i class="el-icon-error"> {{ submissionStatus.text }}</i>-->
<!--                          </template>-->
<!--                        </el-tag>-->
<!--                      </el-tooltip>-->
<!--                    </template>-->
<!--                    <template v-else-if="-->
<!--                        this.contestID &&-->
<!--                          !ContestRealTimePermission &&-->
<!--                          this.contestRuleType == RULE_TYPE.OI-->
<!--                      ">-->
<!--                      <el-alert-->
<!--                          type="success"-->
<!--                          show-icon-->
<!--                          effect="dark"-->
<!--                          :closable="false"-->
<!--                      >{{ $t('m.Submitted_successfully') }}</el-alert>-->
<!--                    </template>-->
<!--                  </div>-->
<!--                  <div v-else-if="-->
<!--                      (!this.contestID ||-->
<!--                        this.contestRuleType == RULE_TYPE.ACM) &&-->
<!--                        problemData.myStatus == JUDGE_STATUS_RESERVE.ac-->
<!--                    ">-->
<!--                    <el-alert-->
<!--                        type="success"-->
<!--                        show-icon-->
<!--                        effect="dark"-->
<!--                        :closable="false"-->
<!--                    >{{ $t('m.You_have_solved_the_problem') }}</el-alert>-->
<!--                  </div>-->
<!--                  <div v-else-if="-->
<!--                      this.contestID &&-->
<!--                        !ContestRealTimePermission &&-->
<!--                        this.contestRuleType == RULE_TYPE.OI &&-->
<!--                        submissionExists-->
<!--                    ">-->
<!--                    <el-alert-->
<!--                        type="success"-->
<!--                        show-icon-->
<!--                        effect="dark"-->
<!--                        :closable="false"-->
<!--                    >{{ $t('m.You_have_submitted_a_solution') }}</el-alert>-->
<!--                  </div>-->
<!--                  <div v-if="contestEnded && !statusVisible">-->
<!--                    <el-alert-->
<!--                        type="warning"-->
<!--                        show-icon-->
<!--                        effect="dark"-->
<!--                        :closable="false"-->
<!--                    >{{ $t('m.Contest_has_ended') }}</el-alert>-->
<!--                  </div>-->
<!--                </el-col>-->

<!--                <el-col-->
<!--                    :sm="24"-->
<!--                    :md="14"-->
<!--                    :lg="14"-->
<!--                    style="margin-top:4px;"-->
<!--                >-->
<!--                  <template v-if="captchaRequired">-->
<!--                    <div class="captcha-container">-->
<!--                      <el-tooltip-->
<!--                          v-if="captchaRequired"-->
<!--                          content="Click to refresh"-->
<!--                          placement="top"-->
<!--                      >-->
<!--                        <img-->
<!--                            :src="captchaSrc"-->
<!--                            @click="getCaptchaSrc"-->
<!--                        />-->
<!--                      </el-tooltip>-->
<!--                      <el-input-->
<!--                          v-model="captchaCode"-->
<!--                          class="captcha-code"-->
<!--                      />-->
<!--                    </div>-->
<!--                  </template>-->
<!--                  <el-button-->
<!--                      type="primary"-->
<!--                      icon="el-icon-edit-outline"-->
<!--                      size="small"-->
<!--                      :loading="submitting"-->
<!--                      @click.native="submitCode"-->
<!--                      :disabled="problemSubmitDisabled || submitted || submitDisabled"-->
<!--                      class="fl-right"-->
<!--                  >-->
<!--                    <span v-if="submitting">{{ $t('m.Submitting') }}</span>-->
<!--                    <span v-else>{{ $t('m.Submit') }}</span>-->
<!--                  </el-button>-->
<!--                  <el-tag-->
<!--                      type="success"-->
<!--                      :class="openTestCaseDrawer?'tj-btn active':'tj-btn non-active'"-->
<!--                      @click.native="openTestJudgeDrawer"-->
<!--                      v-if="!submitDisabled"-->
<!--                      effect="plain"-->
<!--                  >-->
<!--                    <svg-->
<!--                        t="1653665263421"-->
<!--                        class="icon"-->
<!--                        viewBox="0 0 1024 1024"-->
<!--                        version="1.1"-->
<!--                        xmlns="http://www.w3.org/2000/svg"-->
<!--                        p-id="1656"-->
<!--                        width="12"-->
<!--                        height="12"-->
<!--                        style="vertical-align: middle;"-->
<!--                    >-->
<!--                      <path-->
<!--                          d="M1022.06544 583.40119c0 11.0558-4.034896 20.61962-12.111852 28.696576-8.077979 8.077979-17.639752 12.117992-28.690436 12.117992L838.446445 624.215758c0 72.690556-14.235213 134.320195-42.718941 184.89915l132.615367 133.26312c8.076956 8.065699 12.117992 17.634636 12.117992 28.690436 0 11.050684-4.034896 20.614503-12.117992 28.691459-7.653307 8.065699-17.209964 12.106736-28.690436 12.106736-11.475356 0-21.040199-4.041036-28.690436-12.106736L744.717737 874.15318c-2.124384 2.118244-5.308913 4.88424-9.558703 8.283664-4.259 3.3984-13.180184 9.463536-26.78504 18.171871-13.598716 8.715499-27.415396 16.473183-41.439808 23.276123-14.029528 6.797823-31.462572 12.966313-52.289923 18.49319-20.827351 5.517667-41.446971 8.28571-61.842487 8.28571L552.801776 379.38668l-81.611739 0 0 571.277058c-21.668509 0-43.250036-2.874467-64.707744-8.615215-21.473057-5.734608-39.960107-12.749372-55.476499-21.039175-15.518438-8.289804-29.541827-16.572444-42.077328-24.867364-12.541641-8.290827-21.781072-15.193027-27.739784-20.714787l-9.558703-8.93244L154.95056 998.479767c-8.500605 8.921183-18.699897 13.386892-30.606065 13.386892-10.201339 0-19.335371-3.40454-27.409257-10.202363-8.079002-7.652284-12.437264-17.10968-13.080923-28.372188-0.633427-11.263531 2.659573-21.143553 9.893324-29.647227l128.787178-144.727219c-24.650423-48.464805-36.980239-106.699114-36.980239-174.710091L42.738895 624.207571c-11.057847 0-20.61655-4.041036-28.690436-12.111852-8.079002-8.082072-12.120039-17.640776-12.120039-28.696576 0-11.050684 4.041036-20.61962 12.120039-28.689413 8.073886-8.072863 17.632589-12.107759 28.690436-12.107759l142.81466 0L185.553555 355.156836l-110.302175-110.302175c-8.074909-8.077979-12.113899-17.640776-12.113899-28.691459 0-11.04966 4.044106-20.61962 12.113899-28.690436 8.071839-8.076956 17.638729-12.123109 28.691459-12.123109 11.056823 0 20.612457 4.052293 28.692482 12.123109l110.302175 110.302175 538.128077 0 110.303198-110.302175c8.070816-8.076956 17.632589-12.123109 28.690436-12.123109 11.050684 0 20.617573 4.052293 28.689413 12.123109 8.077979 8.070816 12.119015 17.640776 12.119015 28.690436 0 11.050684-4.041036 20.614503-12.119015 28.691459l-110.302175 110.302175 0 187.448206 142.815683 0c11.0558 0 20.618597 4.034896 28.690436 12.113899 8.076956 8.069793 12.117992 17.638729 12.117992 28.683273l0 0L1022.06544 583.40119 1022.06544 583.40119zM716.021162 216.158085 307.968605 216.158085c0-56.526411 19.871583-104.667851 59.616796-144.414087 39.733956-39.746236 87.88256-59.611679 144.411017-59.611679 56.529481 0 104.678084 19.865443 144.413064 59.611679C696.156742 111.48921 716.021162 159.631674 716.021162 216.158085L716.021162 216.158085 716.021162 216.158085 716.021162 216.158085z"-->
<!--                          p-id="1657"-->
<!--                          :fill="openTestCaseDrawer?'#ffffff':'#67c23a'"-->
<!--                      >-->
<!--                      </path>-->
<!--                    </svg>-->
<!--                    <span style="vertical-align: middle;">-->
<!--                      {{ $t('m.Online_Test') }}-->
<!--                    </span>-->
<!--                  </el-tag>-->
<!--                </el-col>-->
<!--              </el-row>-->
<!--            </div>-->
<!--          </el-card>-->
<!--        </el-col>-->
      </el-row>
    </div>
<!--    <ProblemHorizontalMenu-->
<!--        v-if="showProblemHorizontalMenu"-->
<!--        :pid.sync="problemData.problem.id"-->
<!--        :cid="contestID"-->
<!--        :tid="trainingID"-->
<!--        ref="problemHorizontalMenu"-->
<!--        :gid="groupID">-->
<!--    </ProblemHorizontalMenu>-->

    <el-dialog
        :visible.sync="graphVisible"
        width="400px"
    >
      <div id="pieChart-detail">
        <ECharts
            :options="largePie"
            :initOptions="largePieInitOpts"
        ></ECharts>
      </div>
      <div slot="footer">
        <el-button
            type="ghost"
            @click="graphVisible = false"
            size="small"
        >{{
            $t('m.Close')
          }}</el-button>
      </div>
    </el-dialog>

    <el-dialog
        :visible.sync="submitPwdVisible"
        width="340px"
    >
      <el-form>
        <el-form-item
            :label="$t('m.Enter_the_contest_password')"
            required
        >
          <el-input
              :placeholder="$t('m.Enter_the_contest_password')"
              v-model="submitPwd"
              show-password
          ></el-input>
        </el-form-item>
        <el-button
            type="primary"
            round
            style="margin-left:130px"
            @click="checkContestPassword"
        >
          {{ $t('m.Submit') }}
        </el-button>
      </el-form>
    </el-dialog>
  </div>
</template>


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
    margin-left: 3%;
    margin-top: 1%;
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
</style>