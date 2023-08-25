<template>
    <div class="app-container">
        <div style="width: 100%;display:flex; justify-content:center; align-items:center;">
            <div><p style="font-size: 30px;margin-top: 0;margin-bottom: 0">题目title</p></div>
        </div>
        <el-row :gutter="10" class="mb8">
            <el-col :span="1.5">
                <el-button type="primary" plain icon="Plus" @click="handleAdd" hasPermi="['system:role:add']">新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button type="danger" plain icon="Delete" :disabled="!(ids&&ids.length>0)" @click="handleDelete" hasPermi="['system:role:remove']">删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    plain
                    icon="Download"
                    @click="handleExport"
                    hasPermi="['system:role:export']"
                >导出</el-button>
            </el-col>
        </el-row>

        <!-- 表格数据 -->
        <el-table v-loading="loading" :data="exampleList" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" align="center" />
            <el-table-column label="样例编号"  prop="eid" width="120" />
            <el-table-column label="样例输入" align="center" prop="input" :show-overflow-tooltip="true" width="300" />
            <el-table-column label="样例输出" align="center" prop="output" :show-overflow-tooltip="true" width="300" />
            <el-table-column label="是否为展示样例" align="center" width="150">
                <template #default="scope">
                    <el-tag>{{scope.row.status==0?'隐藏':'展示'}}</el-tag>
                </template>
            </el-table-column>
            <el-table-column label="创建时间" align="center" prop="createTime">
                <template #default="scope">
                    <span>{{ scope.row.createTime }}</span>
                </template>
            </el-table-column>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template #default="scope">
                    <el-tooltip content="修改" placement="top">
                        <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" hasPermi="['system:role:edit']"></el-button>
                    </el-tooltip>
                    <el-tooltip content="删除" placement="top">
                        <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" hasPermi="['system:role:remove']"></el-button>
                    </el-tooltip>
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
        <!-- 添加或修改角色配置对话框 -->
        <el-dialog :title="title" v-model="open" width="60%" append-to-body>
          <el-form :model="form" label-width="120px">
              <el-form-item label="样例输入">
                  <el-input style="font-size: 20px" type="textarea" :autosize="{ minRows: 3, maxRows: 20}" v-model="form.input">

                  </el-input>
              </el-form-item>
            <el-form-item label="样例输出">
              <el-input style="font-size: 20px" type="textarea" :autosize="{ minRows: 3, maxRows: 20}" v-model="form.output">

              </el-input>
            </el-form-item>
            <el-form-item label="测试结果">
              <el-input style="font-size: 20px" type="textarea" :autosize="{ minRows: 3, maxRows: 20}"  disabled :value="data.result">
              </el-input>
            </el-form-item>
            <el-form-item>
              <div>
                <span>测试代码：</span>
                <el-select style="margin-right: 5px" v-model="form.codeId">
                    <el-option v-for="(code,index) in codes" :key="index" :label="code.language" :value="code.codeId"></el-option>
                </el-select>
              </div>
              <el-button type="primary" v-model:loading="testLoading" :disabled="!form.codeId||form.codeId==''" @click="handleTest">测试</el-button>
              <el-button type="primary" v-model:loading="confirmLoading" @click="handleSaveOrUpdate">确定</el-button>
              <el-button @click="cancel">取消</el-button>
            </el-form-item>
          </el-form>
        </el-dialog>

    </div>
</template>

<script setup name="Role">
import { delRole, getRole, listRole, updateRole, deptTreeSelect } from "@/api/system/role";
import {
    deleteAlgorithmExample,
    examples,
    getProblemTrueCode,
    saveAlgorithmExample,
    testExample,
    updateAlgorithmExample
} from "@/api/oj/algorithm";
import {useRoute, useRouter} from "vue-router";
import {nextTick, ref} from "vue";

const router = useRouter();
const route=useRoute();
const { proxy } = getCurrentInstance();
const exampleList = ref([]);
const open = ref(false);
const loading = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const menuRef = ref(null);


const pid=ref(route.query.pid);
const codes=ref([]);
const testLoading = ref(false)
const confirmLoading=ref(false)


const data = reactive({
    form: {
        pid: pid.value
    },
    result: undefined,
    queryParams: {
        pid: route.query.pid,
        pageNum: 1,
        pageSize: 10,
        status: undefined
    },
    rules: {
        name: [{ required: true, message: "角色名称不能为空", trigger: "blur" }],
        roleKey: [{ required: true, message: "权限字符不能为空", trigger: "blur" }],
        sort: [{ required: true, message: "角色顺序不能为空", trigger: "blur" }]
    },
});

const { queryParams, form, rules } = toRefs(data);

/** 查询角色列表 */
function getList() {
    getProblemTrueCode({
        pid: pid.value,
        pageNum: 1,
        pageSize: 10000
    }).then(res=>{
        codes.value=res.data.records;
        console.log(codes.value)
    })
    loading.value = true;
    examples(queryParams.value).then(response => {
        exampleList.value = response.data.records;
        total.value = response.data.total;
        loading.value = false;
    });
}

function handleTest() {
    testLoading.value=true;
    testExample(form.value).then(res=>{
        proxy.$modal.msgSuccess("测试运行成功!")
        if(res.data.status=='Accepted'){
            data.result=res.data.output
        }
    }).finally(()=>{
        testLoading.value=false;
    })
}

function handleSaveOrUpdate(){
    form.value.pid=pid.value
    if(form.value.eid!=undefined){
        proxy.$modal.confirm(`确定更新样例点吗？`).then(res=>{
            confirmLoading.value=true;
            updateAlgorithmExample(form.value).then(res=>{
                getList()
                open.value=false
            }).finally(()=>{
                confirmLoading.value=false;
            })
        })
    }else {
        proxy.$modal.confirm(`确定新增样例点吗?`).then(res=>{
            confirmLoading.value=true;
            saveAlgorithmExample(form.value).then(res=>{
                getList()
                open.value=false
            }).finally(()=>{
                confirmLoading.value=false;
            })
        }).catch(()=>{})
    }
}

/** 删除按钮操作 */
function handleDelete(row) {
    let exampleIds=[]
    if(row.eid){
        exampleIds.push(row.eid)
    }else {
         exampleIds=  ids.value;
    }
    proxy.$modal.confirm('是否确认删除样例编号为"' + exampleIds + '"的数据项?').then(function () {
        return deleteAlgorithmExample({pid:pid.value,ids:exampleIds});
    }).then(() => {
        getList();
        proxy.$modal.msgSuccess("删除成功");
    }).catch(() => {});
}
/** 导出按钮操作 */
function handleExport() {
    proxy.download("system/role/export", {
        ...queryParams.value,
    }, `role_${new Date().getTime()}.xlsx`);
}
/** 多选框选中数据 */
function handleSelectionChange(selection) {
    ids.value = selection.map(item =>{return item.eid});
}


/** 重置新增的表单以及其他数据  */
function reset() {
    data.form={}
}
/** 添加角色 */
function handleAdd() {
    open.value = true;
    title.value = "添加样例";
    reset();
}
/** 修改角色 */
function handleUpdate(row) {
    reset();
    open.value = true;
    title.value = "修改样例";
    data.form=row

}

/** 取消按钮 */
function cancel() {
    open.value = false;
    reset();
}
getList();
</script>
