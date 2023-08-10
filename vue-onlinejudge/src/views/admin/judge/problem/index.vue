<template>
   <div class="app-container">
      <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch">
         <el-form-item label="题目编号" prop="pid">
            <el-input
               v-model="queryParams.pid"
               placeholder="请输入题目编号"
               clearable
               style="width: 200px"
               @keyup.enter="handleQuery"
            />
         </el-form-item>
         <el-form-item label="题目名称" prop="postName">
            <el-input
               v-model="queryParams.title"
               placeholder="请输入题目名称"
               clearable
               style="width: 200px"
               @keyup.enter="handleQuery"
            />
         </el-form-item>
         <el-form-item label="状态" prop="status">
            <el-select v-model="queryParams.status" placeholder="题目状态" clearable style="width: 200px">
               <el-option
                  v-for="dict in sys_normal_disable"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
               />
            </el-select>
         </el-form-item>
         <el-form-item>
            <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
            <el-button icon="Refresh" @click="resetQuery">重置</el-button>
         </el-form-item>
      </el-form>

      <el-row :gutter="10" class="mb8">
         <el-col :span="1.5">
            <el-button
               type="primary"
               plain
               icon="Plus"
               @click="handleAdd"
            >新增</el-button>
         </el-col>
         <el-col :span="1.5">
            <el-button
               type="success"
               plain
               icon="Edit"
               :disabled="single"
               @click="handleUpdate"
            >修改</el-button>
         </el-col>
         <el-col :span="1.5">
            <el-button
               type="danger"
               plain
               icon="Delete"
               :disabled="multiple"
               @click="handleDelete"
            >删除</el-button>
         </el-col>
         <el-col :span="1.5">
            <el-button
               type="warning"
               plain
               icon="Download"
               @click="handleExport"
               v-hasPermi="['system:post:export']"
            >导出</el-button>
         </el-col>
         <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>

      <el-table v-loading="loading" :data="problems" @selection-change="handleSelectionChange">
         <el-table-column type="selection" width="55" align="center" />
         <el-table-column label="题目编号" width="80" prop="pid" />
         <el-table-column label="题目标题" align="center" prop="title" />
         <el-table-column label="状态" width="80" prop="status">
            <template #default="scope">
              <el-tag type="success" v-if="scope.row.status==0">启用</el-tag>
              <el-tag type="danger" v-if="scope.row.status==1">禁用</el-tag>
            </template>
         </el-table-column>
         <el-table-column label="标签" align="center" prop="tags">
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
         <el-table-column label="操作" width="800" align="center" class-name="small-padding fixed-width">
            <template #default="scope">
               <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)">配置基础信息</el-button>
               <el-button link type="primary" icon="Edit" @click="handleProblemBodyUpdate(scope.row)">配置题面</el-button>
              <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)">配置正确代码</el-button>
               <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)">配置样例</el-button>
               <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)">删除题目</el-button>
            </template>
         </el-table-column>
      </el-table>
      <pagination
         v-show="total > 0"
         :total="total"
         v-model:page="queryParams.pageNum"
         v-model:limit="queryParams.pageSize"
         @pagination="getList"
      />
     <el-dialog v-model="open" width="500" :title="title">
        <el-form :model="form" label-width="120px" ref="problemRef" :rules="rules">
          <el-form-item label="标题">
            <el-input style="width: 240px" v-model="form.title" ></el-input>
          </el-form-item>
          <el-form-item label="状态">
            <el-radio-group v-model="form.status" class="ml-4">
              <el-radio :label="0" size="large">启用</el-radio>
              <el-radio :label="1" size="large">禁用</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="标签">
            <el-select
                v-model="form.tags"
                multiple
                placeholder="Select"
                style="width: 240px"
            >
              <el-option
                  v-for="item in tags"
                  :key="item.tid"
                  :label="item.title"
                  :value="item.tid"
              />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm()">修改</el-button>
            <el-button>取消</el-button>
          </el-form-item>
        </el-form>
     </el-dialog>

   </div>
</template>

<script setup name="Post">
import {listProblem, addPost, delPost, getProblem, updateProblemBaseInfo, getTags} from "@/api/system/problem";
import axios from 'axios'
import {useRouter} from "vue-router";
const { proxy } = getCurrentInstance();


const router = useRouter();
const problems = ref([]);
const loading = ref(true);
const showSearch = ref(true);
const open=ref(false)
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");


const tags=ref([])


const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    pid: undefined,
    title: undefined,
    status: undefined
  },
  rules: {
    title: [{ required: true, message: "题目标题不能为空", trigger: "blur" }],
    status: [{ required: true, message: "题目状态不能为空", trigger: "blur" }],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询岗位列表 */
function getList() {
  loading.value = true;
  listProblem(queryParams.value).then(res=>{
    problems.value=res.data.records
    total.value=res.data.total
    loading.value=false
  })
}
/** 取消按钮 */
function cancel() {
  open.value = false;
  reset();
}
/** 表单重置 */
function reset() {
  form.value = {
    title: undefined,
    description: undefined,
    inputDescription: undefined,
    outputDescription: undefined,
    dataDescription: undefined,
    status: 0
  };
  proxy.resetForm("postRef");
}
/** 搜索按钮操作 */


function handleProblemBodyUpdate(row){
  router.push({path:'/judge/problem/updateBody',query:{pid:row.pid}});
}
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}
/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}
/** 多选框选中数据 */
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.postId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}
/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加题目";
}
/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const pid = row.pid || ids.value;
  getProblem(pid).then(response => {
    getTags().then(res=>{
      tags.value=res.data
    })
    let value=response.data
    let array=[]
    if(value.tags&&value.tags.length>0){
      value.tags.forEach(tag=>{
        array.push(tag.tid)
      })
    }
    value.tags=array;
    form.value = value;
    open.value = true;
    title.value = "修改题目";
  });
}
/** 提交按钮 */
function submitForm() {
  proxy.$refs["problemRef"].validate(valid => {
    if (valid) {
      console.log(form.value.pid!= undefined)
      if (form.value.pid!= undefined) {
        updateProblemBaseInfo(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addPost(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}
/** 删除按钮操作 */
function handleDelete(row) {
  const postIds = row.postId || ids.value;
  proxy.$modal.confirm('是否确认删除岗位编号为"' + postIds + '"的数据项？').then(function() {
    return delPost(postIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}
/** 导出按钮操作 */
function handleExport() {
  proxy.download("system/post/export", {
    ...queryParams.value
  }, `post_${new Date().getTime()}.xlsx`);
}

getList();
</script>
