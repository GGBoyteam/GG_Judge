<template>
  <el-row :gutter="18">
    <el-col :sm="23" :md="19" :lg="17" style="margin-top: 20px;margin-left: 2%" >
      <el-card shadow="always">
        <div slot="header">
          <el-row :gutter="20" style="margin-bottom: 0.5em;" justify="center" align="middle">
            <el-col :xs="24" :sm="6">
              <span class="problem-list-title">题目列表</span>
            </el-col>
            <el-col :xs="24" :sm="6" >
              <el-input
                  placeholder="输入关键字"
                  type="search"
                  @search-click="filterByKeyword"
                  @keyup.enter.native="filterByKeyword"
                  class="filter-mt">
                <template #append>
                  <el-button :icon="Search" />
                </template>
              </el-input>
            </el-col >
            <el-col
                :xs="12"
                :sm="6"
                style="text-align: center;padding-top: 6px;"
                class="filter-mt"
            >
              <el-checkbox
                  v-model="tagVisible"
                  @change="changeTagVisible(tagVisible)"
              >显示标签</el-checkbox>
            </el-col>
            <el-col
                :xs="12"
                :sm="6"
                style="text-align: center;"
                class="filter-mt"
            >
              <el-button
                  type="primary"
                  :icon="Refresh"
                  round
                  @click="onReset"
              >重置</el-button>
            </el-col>
          </el-row>

          <section>
            <b class="problem-filter">题库</b>
            <div>
              <el-tag
                  class="filter-item"
                  @click="filterByOJ('All')"
              >全部</el-tag>
              <el-tag
                  class="filter-item"
                  @click="filterByOJ('Mine')"
              >主题库</el-tag>
              <el-tag
                  class="filter-item"
                  @click="filterByOJ('Mine')"
              >主题库</el-tag>
              <el-tag
                  class="filter-item"
                  @click="filterByOJ('Mine')"
              >主题库</el-tag>
              <el-tag
                  class="filter-item"
                  @click="filterByOJ('Mine')"
              >主题库</el-tag>
              <el-tag
                  class="filter-item"
                  @click="filterByOJ('Mine')"
              >主题库</el-tag>
<!--              <el-tag-->
<!--                  size="medium"-->
<!--                  class="filter-item"-->
<!--                  v-for="(remoteOj, index) in REMOTE_OJ"-->
<!--                  :effect="query.oj == remoteOj.key ? 'dark' : 'plain'"-->
<!--                  :key="index"-->
<!--                  @click="filterByOJ(remoteOj.key)"-->
<!--              >{{ remoteOj.name }}</el-tag-->
<!--              >-->
            </div>
          </section>

          <section>
            <b class="problem-filter">难度</b>
            <div>
              <el-tag
                  class="filter-item"
                  @click="filterByDifficulty('All')"
              >全部</el-tag>
<!--              <el-tag-->
<!--                  size="medium"-->
<!--                  class="filter-item"-->
<!--                  v-for="(value, key, index) in PROBLEM_LEVEL"-->
<!--                  :effect="query.difficulty == key ? 'dark' : 'plain'"-->
<!--                  :style="getLevelBlockColor(key)"-->
<!--                  :key="index"-->
<!--                  @click="filterByDifficulty(key)"-->
<!--              >{{ getLevelName(key) }}</el-tag-->
<!--              >-->
            </div>
          </section>
          <div v-if="filterTags&&filterTags.length > 0">
            <el-row>
              <b class="problem-filter">标签</b>
              <el-tag
                  :key="index"
                  v-for="(tag, index) in filterTags"
                  closable
                  :color="tag.color ? tag.color : '#409eff'"
                  effect="dark"
                  :disable-transitions="false"
                  @close="removeTag(tag)"
                  size="medium"
                  class="filter-item"
              >
                {{ tag.title }}
              </el-tag>
            </el-row>
          </div>
        </div>
        <el-table stripe :data="problems" style="width: 100%" @row-click="problemDetail">
          <el-table-column fixed prop="pid"  align="left" label="题目id" width="180" />
          <el-table-column prop="title" align="center" label="题目" />
          <el-table-column prop="level" align="center" label="难度" width="180" />
          <el-table-column prop="tag" align="center" label="标签" width="180">
            <template #default="scope">
                <el-popover
                        placement="bottom-start"
                        :width="200"
                        trigger="hover"
                >
                    <template #reference>
                        <el-button size="small" class="m-2">查看标签</el-button>
                    </template>
                    <template #default>
                        <span v-if="!scope.row.tags||scope.row.tags.length==0">没有标签</span>
                        <div v-else>
                            <el-tag
                                    v-for="(tag,index) in scope.row.tags"
                                    :key="index"
                                    :color="tag.color"
                                    class="mx-1"
                                    style="margin-right: 3px;margin-top: 3px;border: 0"
                                    effect="dark"
                            >{{tag.title}}</el-tag>
                        </div>
                    </template>
                </el-popover>
            </template>
          </el-table-column>
          <el-table-column prop="count" align="center" label="总数" width="180" />
          <el-table-column align="center" prop="ac" label="AC率"/>
        </el-table>
      </el-card>
<!--      <Pagination-->
<!--          :total="total"-->
<!--          :page-size="limit"-->
<!--          @on-change="pushRouter"-->
<!--          :current.sync="query.currentPage"-->
<!--          @on-page-size-change="onPageSizeChange"-->
<!--          :layout="'prev, pager, next, sizes'"-->
<!--      ></Pagination>-->
    </el-col>

    <el-col  :sm="23" :md="7" :lg="6">
<!--      <el-card style="text-align:center">-->
<!--        <span class="panel-title">{{ currentProblemTitle }}</span>-->
<!--        <el-row v-for="(record, index) in problemRecord" :key="index">-->
<!--          <el-col :xs="5" :sm="4" :md="6" :lg="4" style="margin-top: 10px;">-->
<!--            <el-tag-->
<!--                effect="dark"-->
<!--                size="small"-->
<!--                :color="JUDGE_STATUS[record.status].rgb"-->
<!--            >{{ JUDGE_STATUS[record.status].short }}</el-tag-->
<!--            >-->
<!--          </el-col>-->
<!--          <el-col :xs="19" :sm="20" :md="18" :lg="20">-->
<!--            <el-progress-->
<!--                :text-inside="true"-->
<!--                :stroke-width="20"-->
<!--                :percentage="record.count"-->
<!--                :color="JUDGE_STATUS[record.status].rgb"-->
<!--            ></el-progress>-->
<!--          </el-col>-->
<!--        </el-row>-->
<!--      </el-card>-->
      <el-card :padding="10" style="margin-top:20px">
        <div slot="header" style="text-align: center;">
          <span class="taglist-title">标签</span>
          <div style="margin: 10px 0;">
            <el-input
                size="medium"
                prefix-icon="el-icon-search"
                placeholder="请输入标签"
                v-model="searchTag"
                @keyup.enter.native="filterSearchTag"
                @input="filterSearchTag"
                clearable
            >
            </el-input>
          </div>
        </div>
        <div v-if="tags&&tags.length>0">
            <el-button
                    v-for="(tag,index) in tags"
                    :key="tag.id"
                    @click="addTag(tag,index)"
                    size="mini"
                    class="tag-btn"
                    :style="
                        'color:#FFF;background-color:' +
                          (tag.color ? tag.color : '#409eff')
                      "
            >{{ tag.title }}
            </el-button>
          <el-button long id="pick-one" @click="pickone">
            <i class="fa fa-random"></i>
            随机一题
          </el-button>
        </div>
        <div v-else>
          <el-empty description="没有数据"></el-empty>
        </div>
      </el-card>
    </el-col>
  </el-row>
</template>

<script setup>
import {ref} from "vue";
import {getTags, list} from "@/api/oj/problem";
import {Refresh, Search} from "@element-plus/icons-vue";
import {useRouter} from "vue-router";
const router=useRouter()
const tags=ref([])
const filterTags=ref([])
const problems=ref([])
const form=ref({
    pageNum: 1,
    pageSize: 20,
    tags: [],
    pid: undefined,
    title: undefined,
    status: undefined
})
function tagList(){
    getTags().then(res=>{
        tags.value=res.data
        console.log(tags.value)
    })
}
function problemDetail(row) {
  router.push({path:"/oj/problem/detail",query:{pid:row.pid}})
}
function problemList(){
    let tagFilters=[];
    if(filterTags.value&&filterTags.value.length>0){
        filterTags.value.forEach(t=>{
            tagFilters.push(t.tid)
        })
    }
    console.log(tagFilters)
    form.value.tags=tagFilters
    list(form.value).then(res=>{
        problems.value=res.data.records;
    })
}
function pickone(){
    console.log("")
}
function addTag(tag,index){
    tags.value.splice(index,1)
    filterTags.value.push(tag)
    problemList()
}
function removeTag(tag,index){
    filterTags.value.splice(index,1)
    tags.value.push(tag)
    problemList()
}

tagList()
problemList()
</script>

<style scoped>
.problem-list-title {
  font-size: 20px;
  font-weight: 500;
  line-height: 30px;
}

.taglist-title {
  font-size: 21px;
  font-weight: 500;
}

section {
  display: flex;
  align-items: baseline;
  margin-bottom: 0.8em;
}
.problem-filter {
  margin-right: 1em;
  font-weight: bolder;
  white-space: nowrap;
  font-size: 16px;
  margin-top: 8px;
}
.filter-item {
  margin-right: 1em;
  margin-top: 0.5em;
  font-size: 13px;
}
.filter-item:hover {
  cursor: pointer;
}

@media only screen and (max-width: 767px) {
  .filter-mt {
    margin-top: 8px;
  }
}

/deep/.el-tag--dark {
  border-color: #d9ecff;
}
/deep/.tag-btn {
  margin-left: 4px !important;
  margin-top: 4px;
}
/deep/.vxe-checkbox .vxe-checkbox--label {
  overflow: unset !important;
}
/deep/ .vxe-input {
  width: 100%;
}
#pick-one {
  margin-top: 10px;
}
/deep/ .el-card__header {
  border-bottom: 0px;
  padding-bottom: 0px;
}
@media screen and (min-width: 1200px) {
  /deep/ .el-card__body {
    padding-top: 0px;
    margin-top: 5px;
  }
}
ul {
  float: right;
}
.title-a {
  color: #495060;
  font-family: inherit;
  font-size: 14px;
  font-weight: 500;
}
.el-progress {
  margin-top: 15px;
}

@media screen and (min-width: 1050px) {
  /deep/ .vxe-table--body-wrapper {
    overflow-x: hidden !important;
  }
}

/deep/.el-collapse-item__header{
  font-weight: bolder !important;
  height: 38px !important;
  line-height: 38px !important;
  font-size: 15px !important;
}
/deep/.el-collapse-item__content {
  padding-bottom: 10px !important;
}
</style>