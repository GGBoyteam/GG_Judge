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
                  <el-button :icon="Search()" />
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
                  :icon="Refresh()"
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
          <template v-if="filterTagList&&filterTagList.length > 0 && buildFilterTagList">
            <el-row>
              <b class="problem-filter">标签</b>
              <el-tag
                  :key="index"
                  v-for="(tag, index) in filterTagList"
                  closable
                  :color="tag.color ? tag.color : '#409eff'"
                  effect="dark"
                  :disable-transitions="false"
                  @close="removeTag(tag)"
                  size="medium"
                  class="filter-item"
              >
                {{ tag.name }}
              </el-tag>
            </el-row>
          </template>
        </div>
        <el-table stripe style="width: 100%">
          <el-table-column fixed prop="id" label="题目id" width="180" />
          <el-table-column prop="name" label="题目" width="600" />
          <el-table-column prop="level" label="难度" width="180" />
          <el-table-column prop="count" label="总数" width="180" />
          <el-table-column fixed="right" prop="ac" label="AC率"/>
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
        <template v-if="searchTagClassificationList&&searchTagClassificationList.length > 0" v-loading="loadings.tag">
          <el-row :gutter="10" v-for="(item,index) in secondClassificationTemp"
                  :key="index">
            <el-col  v-for="(tagsAndClassification,i) in item" :key="i"
                     :span="query.oj == 'All' || (secondClassificationTemp.length==index+1 && item.length == i+1 && i%2 ==0)
              ?24:12">
              <el-collapse v-model="activeTagClassificationIdList" style="margin-top:10px">
                <el-collapse-item :title="getTagClassificationName(tagsAndClassification.classification)"
                                  v-if="tagsAndClassification.classification != null
                        || tagsAndClassification.tagList.length > 0 "
                                  :name="tagsAndClassification.classification == null?-1:tagsAndClassification.classification.id">
                  <el-button
                      v-for="tag in tagsAndClassification.tagList"
                      :key="tag.id"
                      @click="addTag(tag)"
                      type="ghost"
                      size="mini"
                      class="tag-btn"
                      :style="
                        'color:#FFF;background-color:' +
                          (tag.color ? tag.color : '#409eff')
                      "
                  >{{ tag.name }}
                  </el-button>
                </el-collapse-item>
              </el-collapse>
            </el-col>
          </el-row>
          <el-button long id="pick-one" @click="pickone">
            <i class="fa fa-random"></i>
            随机一题
          </el-button>
        </template>
        <template v-else>
          <el-empty description="没有数据"></el-empty>
        </template>
      </el-card>
    </el-col>
  </el-row>
</template>

<script>


import {Refresh, Search} from "@element-plus/icons-vue";

export default {
  methods: {
    Refresh() {
      return Refresh
    },
    Search() {
      return Search
    }
  },
  setup(){
  }
}

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