
<template>
<div class="true-cody">
  <el-row class="code-box">
    <el-col :sm="24" :md="11" :lg="12" class="code-left"  style="height: 100%">
      <el-tabs
          type="border-card"
          class="demo-tabs"
          @tab-click="handleClick"
      >
        <el-tab-pane>
          <template #label>
                  <span class="custom-tabs-label">
                    <el-icon><calendar /></el-icon>
                    <span>代码列表</span>
                   </span>
          </template>
          <div class="js-left">
            <el-table :data="tableData" style="width: 100%;height: 90%">
              <el-table-column prop="codeId" label="代码id" width="180" />
              <el-table-column prop="name" label="语言" width="180" />
              <el-table-column prop="address" label="Address" />
            </el-table>
            <el-pagination>

            </el-pagination>
          </div>
        </el-tab-pane>

      </el-tabs>
    </el-col>
    <el-col :sm="24" :md="11" :lg="12" class="code-left"  style="height: 100%">
      <el-tabs
          type="border-card"
          class="demo-tabs"
          @tab-click="handleClick"
      >
        <el-tab-pane>
          <template #label>
                  <span class="custom-tabs-label">
                    <el-icon><calendar /></el-icon>
                    <span>新增代码</span>
                   </span>
          </template>
          <div class="js-left">
            <CodeInput>

            </CodeInput>
          </div>
        </el-tab-pane>
        <el-tab-pane>
          <template #label>
                  <span class="custom-tabs-label">
                    <el-icon><calendar /></el-icon>
                    <span>修改代码</span>
                   </span>
          </template>
          <div class="js-left">
            <CodeInput>

            </CodeInput>
          </div>
        </el-tab-pane>
      </el-tabs>
    </el-col>
  </el-row>
</div>
</template>
<script>

import {test} from "@/api/oj/problem";

export default {
  setup(){
    const form=ref({
      language: undefined,
      version: 11,
      code: undefined
    });





    return {
      form
    }
  }
}


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
<script setup>
import CodeInput from "@/components/CodeInput/index.vue";
</script>