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
          <el-table-column label="异常" align="center" prop="title" />
         <el-table-column label="操作" width="800" align="center" class-name="small-padding fixed-width">
            <template #default="scope">
               <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)">配置基础信息</el-button>
               <el-button link type="primary" icon="Edit" @click="handleProblemCompilerLimit(scope.row)">配置编译器信息</el-button>
               <el-button link type="primary" icon="Edit" @click="handleProblemBodyUpdate(scope.row)">配置题面</el-button>
              <el-button link type="primary" icon="Edit" @click="handleProblemTrueCode(scope.row)">配置正确代码</el-button>
               <el-button link type="primary" icon="Edit" @click="handleProblemExample(scope.row)">配置样例</el-button>
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
     <el-dialog v-model="open" width="500" :title="title" >
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
            <el-button type="primary" @click="submitForm">修改</el-button>
            <el-button @click="cancel">取消</el-button>
          </el-form-item>
        </el-form>
     </el-dialog>
       <el-dialog v-model="openLimit" title="编译器配置" @open="getLimitList" style="width: 40%;">
           <el-dialog
                   v-model="limitEditVisible"
                   width="30%"
                   :title="limitTitle"
                   append-to-body
           >
               <el-form :model="data.limitForm" label-width="160px">
                  <el-form-item label="语言">
                      <el-select v-model="data.limitForm.language">
                          <el-option
                            v-for="(item,index) in selectCompilers"
                            :label="item.language"
                            :key="index"
                            :value="item.language"
                          >

                          </el-option>
                      </el-select>
                  </el-form-item>
                   <el-form-item  label="时间限制(单位:ms)：">
                       <el-input v-model.number="data.limitForm.time" placeholder="请输入时间限制">

                       </el-input>
                   </el-form-item>
                   <el-form-item  label="内存限制(单位:byte)：">
                       <el-input v-model.number="data.limitForm.memory"  placeholder="请输入内存限制">

                       </el-input>
                   </el-form-item>
                   <el-form-item>
                       <el-button type="primary" @click="submissionLimit">确定</el-button>
                       <el-button @click="cancelEditLimit">取消</el-button>
                   </el-form-item>
               </el-form>
           </el-dialog>
          <div>
             <span style="margin-right: 4px" v-if="!(selectCompilers&&selectCompilers.length>0)">
                 <el-tooltip content="所有编译器限制已经设置完毕，无法再新增了" placement="top">
                     <el-icon><question-filled /></el-icon>
                 </el-tooltip>
             </span>
              <el-button type="primary"
                         plain icon="Plus"
                         @click="openAddLimit"
                         :disabled="!(selectCompilers&&selectCompilers.length>0)"
              >新增</el-button>
              <el-button type="danger"
                         plain icon="Delete"
                         :disabled="!(limitSelect&&limitSelect.length>0)"
                         @click="deleteCompilerLimit"
              >删除</el-button>
          </div>
           <el-table :data="limitList" style="width: 100%" @selection-change="limitSelectionChange">
               <el-table-column type="selection" width="40" />
               <el-table-column prop="id" align="center" label="编号" width="60" />
               <el-table-column prop="language" align="center" label="语言" />
               <el-table-column prop="time" align="center" label="时间限制(单位:ms)" />
               <el-table-column prop="memory" align="center" label="内存限制(单位:byte)"/>
               <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                   <template #default="scope">
                       <el-tooltip content="修改" placement="top">
                           <el-button link type="primary" icon="Edit" @click="openUpdateLimit(scope.row)" hasPermi="['system:role:edit']"></el-button>
                       </el-tooltip>
                       <el-tooltip content="删除" placement="top">
                           <el-button link type="primary" icon="Delete" @click="handleDeleteLimit(scope.row)" hasPermi="['system:role:remove']"></el-button>
                       </el-tooltip>
                   </template>
               </el-table-column>

           </el-table>
           <el-pagination
                   style="display: flex;justify-content: right;align-items: center;margin-top: 20px"
                   background
                   layout="prev, pager, next"
                   class="mt-4"
                    v-model:current-page="data.limitQueryParams.pageNum"
                   v-model:page-size="data.limitQueryParams.pageSize"
                   :total="limitTotal"
           />
       </el-dialog>

   </div>
</template>

<script setup name="Post">
import {
    listProblem,
    addPost,
    delPost,
    getProblem,
    updateAlgorithmBaseInfo,
    getTags,
    listLimit,
    compiler, addCompileLimit, updateCompileLimit, deleteCompileLimit
} from "@/api/oj/algorithm";
import {useRoute, useRouter} from "vue-router";
import {getCurrentInstance, reactive, ref, toRefs} from "vue";
const { proxy } = getCurrentInstance();


const router = useRouter();
const route=useRoute();
const problems = ref([]);
const loading = ref(true);
const showSearch = ref(true);
const open=ref(false)
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const openLimit=ref(false)
const limitEditVisible=ref(false)
const limitTitle=ref('')
const limitList=ref([])
const limitTotal=ref(0)
const limitSet=ref(new Set)
const limitSelect=ref([])
const compilers=ref([])
const selectCompilers=ref([])

const tags=ref([])


const data = reactive({
  form: {},
    pid: undefined,
    limitForm: {},
    limitQueryParams:{
      pid: undefined,
      pageNum: 1,
      pageSize: 5
    },
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

function submissionLimit() {
    data.limitForm.pid=data.pid
  if(data.limitForm.id!=undefined){
      proxy.$modal.confirm(`是否确认更新语言为${data.limitForm.language}的编译器限制？`).then(()=>{
          updateCompileLimit(data.limitForm).then(res=>{
              getLimitList()
              limitEditVisible.value=false
          })
      }).catch(()=>{})
  }else {
      proxy.$modal.confirm(`是否确认新增语言为${data.limitForm.language}的编译器限制？`).then(()=>{
          addCompileLimit(data.limitForm).then(res=>{
              getLimitList()
              limitEditVisible.value=false
          })
      }).catch(()=>{})
  }
}
function limitSelectionChange(selection){
  limitSelect.value=selection.map(item=>{return item.id})
}

function getCompiler(){
    compiler().then(res=>{
      compilers.value=res.data
    })
}

function getLimitList(){
    limitSet.value=new Set()
    selectCompilers.value=[]
    listLimit(data.limitQueryParams).then(res=>{
        limitList.value=res.data.records
        limitTotal.value=res.data.total
        limitList.value.forEach((item)=>{
            limitSet.value.add(item.language)
        })
        compilers.value.forEach((item)=>{
            if(!limitSet.value.has(item.language)){
                selectCompilers.value.push(item)
            }
        })
    })
}

function openUpdateLimit(row){
    limitEditVisible.value=true
    data.limitForm.id=row.id
    data.limitForm.pid=row.pid
    data.limitForm.language=row.language
    data.limitForm.time=row.time
    data.limitForm.memory=row.memory
    limitTitle.value='修改编译器限制';
}

function openAddLimit(row){
    data.limitForm.pid=row.pid
    limitEditVisible.value=true
    limitTitle.value='新增编译器限制';
}

function deleteCompilerLimit(){
    let str=limitSelect.value.join(",");
    proxy.$modal.confirm(`确认删除id为[`+str+`]的编译器限制信息吗`).then(()=>{
        deleteCompileLimit({pid:data.pid,ids:limitSelect.value}).then(res=>{
            getLimitList()
            proxy.$modal.msgSuccess(`id为[`+str+`]的编译器限制信息删除成功`);
        })
    }).catch(()=>{})
}

function handleDeleteLimit(row) {
    limitSelect.value=[]
    limitSelect.value.push(row.id)
    deleteCompilerLimit()
}

function handleProblemCompilerLimit(row){
    data.pid=row.pid
    data.limitQueryParams.pid=row.pid
    openLimit.value=true;
}




/** 取消按钮 */
function cancel() {
  open.value = false;
  reset();
}
function cancelEditLimit(){
    limitEditVisible.value=false
    data.limitForm={}
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



function handleProblemBodyUpdate(row){
  router.push({path:'/judge/problem/updateBody',query:{pid:row.pid}});
}

function handleProblemTrueCode(row){
  router.push({path:'/judge/problem/trueCode',query:{pid:row.pid}});
}

function handleProblemExample(row) {
    router.push({path:'/judge/problem/example',query:{pid:row.pid}});
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
        updateAlgorithmBaseInfo(form.value).then(response => {
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
getCompiler();
</script>
