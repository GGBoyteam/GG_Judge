
<template>
<div class="true-cody">
  <el-row class="code-box">
    <el-col :sm="24" :md="11" :lg="12" class="code-left"  style="height: 100%">
      <el-tabs
          type="border-card"
          class="demo-tabs"
      >
        <el-tab-pane>
          <template #label>
                  <span class="custom-tabs-label">
                    <el-icon><calendar /></el-icon>
                    <span>正确代码列表</span>
                   </span>
          </template>
          <div class="js-left">
            <el-table ref="table" :data="trueCodeData" style="width: 100%;height: 90%" :highlight-current-row="true" @row-click="handleRowClick">
              <el-table-column prop="codeId" label="代码id" width="100" />
              <el-table-column prop="language" label="语言" width="100" />
              <el-table-column prop="version" label="版本" width="100"/>
              <el-table-column prop="code" label="代码" :show-overflow-tooltip="true" width="300" />
              <el-table-column  align="center" label="操作">
                  <template #default="scope">
                      <el-button link type="primary" size="small" @click="handleDelete(scope.row)">删除</el-button>
                  </template>
              </el-table-column>
            </el-table>
            <div style="display:flex; justify-content:center;margin-top: 20px">
                <el-pagination
                        background
                        v-model:current-page="queryParams.pageNum"
                        v-model:page-size="queryParams.pageSize"
                        layout="total, prev, pager, next, jumper"
                        :total="total"
                        @current-change="handleCurrentChange"
                />
            </div>
          </div>
        </el-tab-pane>

      </el-tabs>
    </el-col>
    <el-col :sm="24" :md="11" :lg="12" class="code-left"  style="height: 100%">
      <el-tabs
          type="border-card"
          class="demo-tabs"
          v-model="select"
          @tab-click="handleTabsClick"
      >
        <el-tab-pane name="add">
          <template #label>
                  <span class="custom-tabs-label">
                    <el-icon><calendar /></el-icon>
                    <span>新增代码</span>
                   </span>
          </template>
          <div class="js-left">
            <CodeInput
              v-model:language="language"
              v-model:version="version"
              v-model="code"
              :drawer-visible="drawerVisible"
            >
            </CodeInput>
              <div style="float: right">
                  <el-button type="success"  @click="OpenDrawer">自测运行</el-button>
                  <el-button type="primary" @click="Submission">新增代码</el-button>
              </div>
          </div>
        </el-tab-pane>
        <el-tab-pane name="update" :disabled="true">
              <template #label>
                  <span class="custom-tabs-label">
                    <el-icon><calendar /></el-icon>
                    <span>修改代码</span>
                   </span>
              </template>
              <div class="js-left">
                  <CodeInput
                          v-model:language="language"
                          v-model="code"
                          v-model:version="version"
                          :drawer-visible="drawerVisible"
                  >
                  </CodeInput>
                  <div style="float: right">
                      <el-button type="success"  @click="OpenDrawer">自测运行</el-button>
                      <el-button type="primary" @click="Submission">修改代码</el-button>
                  </div>
              </div>
          </el-tab-pane>
      </el-tabs>
    </el-col>
  </el-row>
</div>
</template>
<script setup>

import {deleteTrueCode, getProblemTrueCode, saveOrUpdateProblemTrueCode} from "@/api/oj/problem";
import {ref} from "vue";
import {useRoute} from "vue-router";
const { proxy } = getCurrentInstance();
const route=useRoute()
const table=ref('')
const pid=ref('')
const language=ref('C++')
const code=ref(undefined)
const version=ref('11')
const codeId=ref('')
const select=ref('add')
const drawerVisible=ref(false)
const queryParams=ref({
    pid: route.query.pid,
    pageNum: 1,
    pageSize: 20
})
const total=ref('');

const trueCodeData=ref([])

function OpenDrawer(){
    drawerVisible.value=!drawerVisible.value
}

function Submission(){
    let form={
        language: language.value,
        code: code.value,
        version: version.value,
        pid: pid.value,
        codeId: codeId.value
    }
    proxy.$modal.confirm(`确认${select.value=='add'?'新增':'修改'}代码吗？`).then(()=>{
        saveOrUpdateProblemTrueCode(form).then(res=>{
            getTrueCodeList()
            proxy.$modal.msgSuccess(`${select.value=='add'?'新增':'修改'}代码成功！`)
        })
    })
}

function addOrUpdate(){

}

function handleRowClick(row){
    select.value='update'
    code.value=row.code
    codeId.value=row.codeId;
}

function handleDelete(row) {
    proxy.$modal.confirm(`确定删除编号为${row.codeId}的代码吗？`).then(()=>{
        deleteTrueCode(row.codeId).then(()=>{
            proxy.$modal.msgSuccess("删除成功！")
        }).catch(()=>{
            proxy.$modal.msgError("删除失败！")
        }).finally(()=>{
            getTrueCodeList()
        })
    }).catch(()=>{})
}

function handleTabsClick(){
    if(select.value='add'){
        table.value.setCurrentRow()
        code.value='';
    }
}

function handleCurrentChange(){
    getTrueCodeList()
}

function getTrueCodeList(){
    getProblemTrueCode(queryParams.value).then((res)=>{
        trueCodeData.value=res.data.records;
        total.value=res.data.total
    })
}
pid.value=route.query.pid
getTrueCodeList()
</script>

<style scoped lang="scss">
@media screen and (min-width: 992px) {
  .true-cody {
  }
  .js-left {
    height: 730px !important;
    overflow-y: auto;
  }
  .code-left {
    width: 50%; /*左侧初始化宽度*/
    height: 100%;
    overflow-y: auto;
    overflow-x: hidden;
    float: left;
  }
  .code-box {
    width: 100%;
    height: 100%;
    overflow: hidden;
  }
}
</style>