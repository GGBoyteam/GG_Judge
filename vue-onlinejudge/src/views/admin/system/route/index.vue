<template>
   <div class="app-container">
      <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch">
         <el-form-item label="路由标题" prop="title">
            <el-input
               v-model="queryParams.title"
               placeholder="请输入路由标题"
               clearable
               style="width: 200px"
               @keyup.enter="handleQuery"
            />
         </el-form-item>
         <el-form-item label="状态" prop="status">
            <el-select v-model="queryParams.status" placeholder="路由状态" clearable style="width: 200px">
               <el-option
                  :key="0"
                  label="启用"
                  :value="0"
               />
               <el-option
                   :key="1"
                   label="禁用"
                   :value="1"
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
               hasPermi="['system:menu:add']"
            >新增</el-button>
         </el-col>
         <el-col :span="1.5">
            <el-button 
               type="info"
               plain
               icon="Sort"
               @click="toggleExpandAll"
            >展开/折叠</el-button>
         </el-col>
         <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>

      <el-table
         v-if="refreshTable"
         v-loading="loading"
         :data="menuList"
         row-key="id"
         :default-expand-all="isExpandAll"
         :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
      >
         <el-table-column prop="title" label="路由标题" :show-overflow-tooltip="true" width="160"></el-table-column>
         <el-table-column prop="name" label="路由名称" :show-overflow-tooltip="true" width="160"></el-table-column>
         <el-table-column prop="icon" label="图标" align="center" width="100">
            <template #default="scope">
               <svg-icon :icon-class="scope.row.icon" />
            </template>
         </el-table-column>
         <el-table-column prop="sort" label="排序" width="60"></el-table-column>
         <el-table-column prop="permission" label="权限标识" :show-overflow-tooltip="true"></el-table-column>
         <el-table-column prop="component" label="组件路径" :show-overflow-tooltip="true"></el-table-column>
         <el-table-column prop="status" label="状态" width="80">
            <template #default="scope">
               <el-switch
                   v-model="scope.row.status"
                   :active-value="0"
                   :inactive-value="1"
                   @change="handleStatusChange(scope.row)"
               ></el-switch>
            </template>
         </el-table-column>
         <el-table-column label="创建时间" align="center" width="320" prop="createTime">
            <template #default="scope">
               <span>{{ scope.row.createTime }}</span>
            </template>
         </el-table-column>
         <el-table-column label="操作" align="center" width="400  " class-name="small-padding fixed-width">
            <template #default="scope">
               <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" hasPermi="['system:menu:edit']">修改</el-button>
               <el-button link type="primary" icon="Plus" @click="handleAdd(scope.row)" hasPermi="['system:menu:add']">新增</el-button>
               <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" hasPermi="['system:menu:remove']">删除</el-button>
            </template>
         </el-table-column>
      </el-table>

      <!-- 添加或修改路由对话框 -->
      <el-dialog :title="title" v-model="open" width="680px" append-to-body>
         <el-form ref="menuRef" :model="form" :rules="rules" label-width="100px">
            <el-row>
               <el-col :span="24">
                  <el-form-item label="上级路由">
                     <el-tree-select
                        v-model="form.parent"
                        :data="menuOptions"
                        :props="{ value: 'id', label: 'title', children: 'children' }"
                        value-key="id"
                        placeholder="选择上级路由"
                        check-strictly
                     />
                  </el-form-item>
               </el-col>
               <el-col :span="24">
                  <el-form-item  prop="routeType">
                     <template #label>
                        <span>
                           <el-tooltip content='访问路由的默认传递参数，如：`{"id": 1, "name": "ry"}`' placement="top">
                              <el-icon><question-filled /></el-icon>
                           </el-tooltip>
                           路由类型
                        </span>
                     </template>
                     <el-radio-group v-model="form.routeType">
                        <el-radio label="M">目录</el-radio>
                        <el-radio label="C">菜单</el-radio>
                        <el-radio label="F">按钮</el-radio>
                     </el-radio-group>
                  </el-form-item>
               </el-col>
               <el-col :span="24" v-if="form.routeType != 'F'">
                  <el-form-item label="路由图标" prop="icon">
                     <el-popover
                        placement="bottom-start"
                        :width="540"
                        v-model:visible="showChooseIcon"
                        trigger="click"
                        @show="showSelectIcon"
                     >
                        <template #reference>
                           <el-input v-model="form.icon" disabled placeholder="选择图标" @blur="showSelectIcon" v-click-outside="hideSelectIcon" readonly>
                              <template #prefix>
                                 <svg-icon
                                    v-if="form.icon"
                                    :icon-class="form.icon"
                                    class="el-input__icon"
                                    style="height: 32px;width: 16px;"
                                 />
                                 <el-icon v-else style="height: 32px;width: 16px;"></el-icon>
                              </template>
                           </el-input>
                        </template>
                        <icon-select ref="iconSelectRef" @selected="selected" :active-icon="form.icon" />
                     </el-popover>
                  </el-form-item>
               </el-col>
              <el-col :span="12">
                <el-form-item prop="name">
                  <template #label>
                        <span>
                           <el-tooltip content="路由的唯一标识名称，请确保此项的值唯一" placement="top">
                              <el-icon><question-filled /></el-icon>
                           </el-tooltip>
                           路由名称
                        </span>
                  </template>
                  <el-input v-model="form.name" placeholder="请输入路由名称" />
                </el-form-item>
              </el-col>
               <el-col :span="12">
                  <el-form-item label="路由标题" prop="title">
                     <el-input v-model="form.title" placeholder="请输入路由标题" />
                  </el-form-item>
               </el-col>
               <el-col :span="12">
                  <el-form-item label="显示排序" prop="sort">
                     <el-input-number v-model="form.sort" controls-position="right" :min="0" />
                  </el-form-item>
               </el-col>
               <el-col :span="12" v-if="form.routeType != 'F'">
                  <el-form-item>
                     <template #label>
                        <span>
                           <el-tooltip content="选择是外链则路由地址需要以`http(s)://`开头" placement="top">
                              <el-icon><question-filled /></el-icon>
                           </el-tooltip>是否外链
                        </span>
                     </template>
                     <el-radio-group v-model="form.isFrame">
                        <el-radio :label="0">是</el-radio>
                        <el-radio :label="1">否</el-radio>
                     </el-radio-group>
                  </el-form-item>
               </el-col>
               <el-col :span="12" v-if="form.routeType != 'F'">
                  <el-form-item prop="path">
                     <template #label>
                        <span>
                           <el-tooltip content="访问的路由地址，如：`user`，如外网地址需内链访问则以`http(s)://`开头" placement="top">
                              <el-icon><question-filled /></el-icon>
                           </el-tooltip>
                           路由地址
                        </span>
                     </template>
                     <el-input v-model="form.path" placeholder="请输入路由地址" />
                  </el-form-item>
               </el-col>
               <el-col :span="12" v-if="form.routeType == 'C'">
                  <el-form-item prop="component">
                     <template #label>
                        <span>
                           <el-tooltip content="访问的组件路径，如：`system/user/index`，默认在`views`目录下" placement="top">
                              <el-icon><question-filled /></el-icon>
                           </el-tooltip>
                           组件路径
                        </span>
                     </template>
                     <el-input v-model="form.component" placeholder="请输入组件路径" />
                  </el-form-item>
               </el-col>
               <el-col :span="12">
                  <el-form-item v-if="form.routeType!='F'">
                     <el-input v-model="form.permission" placeholder="请输入权限标识" maxlength="100" />
                     <template #label>
                        <span>
                           <el-tooltip content="控制器中定义的权限字符，如：@PreAuthorize(`@ss.hasPermi('system:user:list')`)" placement="top">
                              <el-icon><question-filled /></el-icon>
                           </el-tooltip>
                           权限字符
                        </span>
                     </template>
                  </el-form-item>
                  <el-form-item v-else>
                     <template #label>
                        <span>
                           <el-tooltip content="控制器中定义的权限字符，如：@PreAuthorize(`@ss.hasPermi('system:user:list')`)" placement="top">
                              <el-icon><question-filled /></el-icon>
                           </el-tooltip>
                           权限字符
                        </span>
                     </template>
                     <el-select v-model="form.permission">

                        <el-option>

                        </el-option>

                     </el-select>
                  </el-form-item>
               </el-col>
               <el-col :span="12" v-if="form.routeType == 'C'">
                  <el-form-item>
                     <el-input v-model="form.query" placeholder="请输入路由参数" maxlength="255" />
                     <template #label>
                        <span>
                           <el-tooltip content='访问路由的默认传递参数，如：`{"id": 1, "name": "ry"}`' placement="top">
                              <el-icon><question-filled /></el-icon>
                           </el-tooltip>
                           路由参数
                        </span>
                     </template>
                  </el-form-item>
               </el-col>
               <el-col :span="12" v-if="form.routeType == 'C'">
                  <el-form-item>
                     <template #label>
                        <span>
                           <el-tooltip content="选择是则会被`keep-alive`缓存，需要匹配组件的`name`和地址保持一致" placement="top">
                              <el-icon><question-filled /></el-icon>
                           </el-tooltip>
                           是否缓存
                        </span>
                     </template>
                     <el-radio-group v-model="form.noCache">
                        <el-radio :label="0">缓存</el-radio>
                        <el-radio :label="1">不缓存</el-radio>
                     </el-radio-group>
                  </el-form-item>
               </el-col>
               <el-col :span="12" v-if="form.routeType != 'F'">
                  <el-form-item>
                     <template #label>
                        <span>
                           <el-tooltip content="选择隐藏则路由将不会出现在侧边栏，但仍然可以访问" placement="top">
                              <el-icon><question-filled /></el-icon>
                           </el-tooltip>
                           显示状态
                        </span>
                     </template>
                     <el-radio-group v-model="form.hidden">
                        <el-radio
                           :key="0"
                           :label="0"
                        >显示</el-radio>
                       <el-radio
                           :key="1"
                           :label="1"
                       >隐藏</el-radio>
                     </el-radio-group>
                  </el-form-item>
               </el-col>
               <el-col :span="12" v-if="form.routeType != 'F'">
                  <el-form-item>
                     <template #label>
                        <span>
                           <el-tooltip content="选择停用则路由将不会出现在侧边栏，也不能被访问" placement="top">
                              <el-icon><question-filled /></el-icon>
                           </el-tooltip>
                           路由状态
                        </span>
                     </template>
                     <el-radio-group v-model="form.status">
                        <el-radio
                           :key="0"
                           :label="0"
                        >启用</el-radio>
                       <el-radio
                           :key="1"
                           :label="1"
                       >禁用</el-radio>
                     </el-radio-group>
                  </el-form-item>
               </el-col>
            </el-row>
         </el-form>
         <template #footer>
            <div class="dialog-footer">
               <el-button type="primary" @click="submitForm">确 定</el-button>
               <el-button @click="cancel">取 消</el-button>
            </div>
         </template>
      </el-dialog>
   </div>
</template>

<script setup name="Menu">
import {addRoute, delRoute, getRoute, listRoute, updateRoute, changeStatus, listPermissions} from "@/api/system/route";
import SvgIcon from "@/components/SvgIcon";
import IconSelect from "@/components/IconSelect";
import { ClickOutside as vClickOutside } from 'element-plus'

const { proxy } = getCurrentInstance();
const menuList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const title = ref("");
const menuOptions = ref([]);
const isExpandAll = ref(false);
const refreshTable = ref(true);
const showChooseIcon = ref(false);
const iconSelectRef = ref(null);

const permissions=ref([])

const data = reactive({
  form: {},
  queryParams: {
    title: undefined,
    hidden: undefined
  },
  rules: {
    name: [{ required: true, message: "路由名称不能为空", trigger: "blur" }],
    title: [{ required: true, message: "路由标题不能为空", trigger: "blur" }],
    sort: [{ required: true, message: "路由顺序不能为空", trigger: "blur" }],
    path: [{ required: true, message: "路由地址不能为空", trigger: "blur" }]
  },
});

const { queryParams, form, rules } = toRefs(data);

/** 查询路由列表 */
function getList() {
  loading.value = true;
  listRoute(queryParams.value).then(response => {
    menuList.value = generateTree(0,response.data)
    loading.value = false;
  });
   listPermissions().then(res=>{
      permissions.value=res.data
   })
}
function handleStatusChange(row){
  const id=row.id;
  changeStatus(id).then(res=>{
    getList()
  })
}

function generateTree(id,data){
   let result=[];
   data.forEach((route)=>{
      if(route.parent==id){
         route.children=generateTree(route.id,data)
         if(route.children&&route.children.length>0){
            route.hasChildren=false;
         }else {
            route.hasChildren=undefined;
         }
         result.push(route)
      }
   })
   if (result.length===0){
      return  undefined;
   }
   return result;
}

/** 查询路由下拉树结构 */
function getTreeselect() {
  menuOptions.value = [];
  listRoute().then(response => {
    const menu = { id: 0, title: "主类目", children: [] };
    menu.children = generateTree(0,response.data)
    menuOptions.value.push(menu);
  });
}
/** 取消按钮 */
function cancel() {
  open.value = false;
  reset();
}
/** 表单重置 */
function reset() {
  form.value = {
    id: undefined,
    parent: 0,
    name: undefined,
    title: undefined,
    icon: undefined,
    c: "M",
    sort: 0,
    isFrame: 1,
    noCache: 0,
    hidden: 0,
    status: 0
  };
  proxy.resetForm("menuRef");
}
/** 展示下拉图标 */
function showSelectIcon() {
  iconSelectRef.value.reset();
  showChooseIcon.value = true;
}
/** 选择图标 */
function selected(name) {
  form.value.icon = name;
  showChooseIcon.value = false;
}
/** 图标外层点击隐藏下拉列表 */
function hideSelectIcon(event) {
   const elem = event.relatedTarget || event.srcElement || event.target || event.currentTarget;
   const className = elem.className;
   if (className !== "el-input__inner") {
    showChooseIcon.value = false;
  }
}
/** 搜索按钮操作 */
function handleQuery() {
  getList();
}
/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}
/** 新增按钮操作 */
function handleAdd(row) {
  reset();
  getTreeselect();
  if (row != null && row.id) {
    form.value.parent = row.id;
  } else {
    form.value.parent = 0;
  }
  open.value = true;
  title.value = "添加路由";
}
/** 展开/折叠操作 */
function toggleExpandAll() {
  refreshTable.value = false;
  isExpandAll.value = !isExpandAll.value;
  nextTick(() => {
    refreshTable.value = true;
  });
}
/** 修改按钮操作 */
async function handleUpdate(row) {
  reset();
  await getTreeselect();
  getRoute(row.id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改路由";
  });
}
/** 提交按钮 */
function submitForm() {
  proxy.$refs["menuRef"].validate(valid => {
    if (valid) {
      if (form.value.id != undefined) {
        updateRoute(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addRoute(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除标题为"' + row.title + '"的数据项?').then(function() {
    return delRoute(row.id);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

getList();
</script>
