<template>
  <div>
    <el-row>
      <el-col :span="24">
        <dy-panel>
          <dy-panel-header title="角色管理"></dy-panel-header>
          <el-row>
            <el-col :span="24">
              <dy-panel-body>
                <el-row>
                  <el-form :inline="true" class="demo-form-inline">
                    <el-form-item label="状态">
                      <el-select v-model="queryParam.disabled" placeholder="请选择状态">
                        <el-option label="全部" value></el-option>
                        <el-option label="启用" value="0"></el-option>
                        <el-option label="禁用" value="1"></el-option>
                      </el-select>
                    </el-form-item>
                    <el-form-item label="产品编号">
                      <el-input clearable v-model="queryParam.flowCode" @change="doSearchList"
                                placeholder="请输入产品编号"></el-input>
                    </el-form-item>
                    <el-form-item label="关键字">
                      <el-input
                        v-model="queryParam.searchKey"
                        placeholder="请输入产品名称"
                        clearable
                        @change="doSearchList"
                      ></el-input>
                    </el-form-item>
                    <el-form-item>
                      <el-button type="primary" @click="doSearchList">查询</el-button>
                    </el-form-item>
                  </el-form>
                </el-row>
                <el-row>
                  <el-button type="primary" :disabled="!operateBtn.addBtn" @click="onAddBtnEvent">新增</el-button>
                  <el-button
                    type="success"
                    :disabled="!operateBtn.editBtn"
                    @click="onEditBtnEvent"
                  >修改
                  </el-button>
                  <el-button type="danger" @click="onDelBtnEvent" :disabled="!operateBtn.delBtn">删除</el-button>
                  <el-button type="default" :disabled="!operateBtn.importBtn">导入</el-button>
                  <el-button type="default" :disabled="!operateBtn.exportBtn">导出</el-button>
                </el-row>
              </dy-panel-body>
              <dy-panel-body v-loading="loading">
                <el-table
                  @selection-change="onSelectRowEvent"
                  :data="tableList"
                  border
                  style="width: 100%;text-align: center"
                  @sort-change="onSortTableEvent"
                >
                  <el-table-column
                    type="selection"
                    prop="date"
                    label="序号"
                    width="30"
                    align="center"
                  ></el-table-column>
                  <el-table-column prop="flowName" label="产品名称" align="center" width="180"></el-table-column>
                  <el-table-column prop="flowCode" label="产品编码" align="center" width="80"></el-table-column>
                  <el-table-column prop="sceneCode" label="场景编码" align="center" width="80"></el-table-column>
                  <el-table-column
                    prop="flowVersion"
                    label="版本"
                    align="center"
                    width="180"
                  >
                    <template slot-scope="scope">
                      <el-tooltip class="item" content="当前版本" placement="right">
                        <el-tag v-show="scope.row.isDefault==1" effect="dark">{{scope.row.flowVersion}}</el-tag>
                      </el-tooltip>
                      <span v-show="scope.row.isDefault==0">{{scope.row.flowVersion}}</span>
                    </template>
                  </el-table-column>
                  <el-table-column prop="asyncMode" label="模式" align="center" width="80">
                    <template slot-scope="scope">

                      <el-tag v-show="scope.row.asyncMode==1" effect="dark" type="danger">同步</el-tag>
                      <el-tag v-show="scope.row.asyncMode==2" effect="dark" type="info">半异步</el-tag>
                      <el-tag v-show="scope.row.asyncMode==3" effect="dark" type="success">全异步</el-tag>
                    </template>
                  </el-table-column>
                  <el-table-column prop="sceneCode" label="流程配置" align="center" width="120">
                    <el-link title="点击配置流程" style="margin-right: 10px" @click="onEditRowEvent(false,scope.row)"
                             type="primary">
                      暂未配置
                    </el-link>
                  </el-table-column>
                  <el-table-column prop="sceneCode" label="请求示例" align="center" width="120">
                    <template slot-scope="scope">
                      <el-link title="点击查看编辑查看输入" style="margin-right: 10px" @click="onEditInputCaseEvent(scope.row)"
                               type="primary">
                        {{(scope.row.inputCase==null||Object.keys(scope.row.inputCase).length === 0)?'未配置':'已配置'}}
                      </el-link>
                    </template>
                  </el-table-column>
                  <el-table-column prop="sceneCode" label="输入示例" align="center" width="80"></el-table-column>

                  <el-table-column prop="createTime" label="创建时间" align="center"></el-table-column>
                  <el-table-column prop="updateTime" label="更新时间" align="center"></el-table-column>
                  <el-table-column prop="disabled" label="启用" align="center">
                    <template slot-scope="scope">
                      <el-tag
                        :type="scope.row.disabled==1?'danger':''"
                        effect="dark"
                      >{{scope.row.disabled==1?'禁用中':'启用中'}}
                      </el-tag>
                    </template>
                  </el-table-column>
                  <el-table-column prop="id" label="操作" width="160">
                    <template slot-scope="scope">
                      <el-link
                        style="margin-right: 10px"
                        @click="onEditRowEvent(false,scope.row)"
                        type="primary"
                      >编辑
                      </el-link>
                    </template>
                  </el-table-column>
                </el-table>
                <div>
                  <dy-pagination
                    :current-page.sync="queryParam.currentPage"
                    :page-size.sync="queryParam.pageSize"
                    @click="doSearchList"
                    :total="tableTotal"
                  ></dy-pagination>
                </div>
              </dy-panel-body>
            </el-col>
          </el-row>
        </dy-panel>
      </el-col>
    </el-row>
    <el-row>
      <dy-modal
        :read-only="editModal.readOnly"
        v-if="editModal.visible"
        :visible.sync="editModal.visible"
        :form-key="editModal.formKey"
        :title="editModal.title"
        v-on:close="onRefresh"
      >
        <engine-flow-update
          :data="editModal.data"
          v-if="editModal.visible"
          :form-key="editModal.formKey"
        ></engine-flow-update>
      </dy-modal>

      <el-dialog
        title="产品输入示例"
        :visible.sync="inputCaseModal.inputCaseDialogVisible"
        width="60%"
        :modal="false"
        :close-on-click-modal="false"
        :lock-scroll="true"
        top="5vh"
      >
        <code-editor currentTheme="solarized-dark" :toolbarShow="true" v-if="inputCaseModal.inputCaseDialogVisible"
                     height="70vh" :codeText="inputCaseModal.inputCaseDialogText"></code-editor>
        <span slot="footer" class="dialog-footer">
        <el-button @click="inputCaseModal.inputCaseDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="inputCaseModal.inputCaseDialogVisible = false">保 存</el-button>
         </span>
      </el-dialog>

    </el-row>
  </div>
</template>
<script>
  import {DyPanel, DyPanelHeader} from '@/view/panel'
  import DyPanelBody from '@/view/panel/DyPanelBody'
  import engineTbFlowApi from '@/apis/dpsApp/engineTbFlowApi.js'
  import authTbDeptApi from '@/apis/auth/authTbDeptApi.js'
  import authTbRoleApi from '@/apis/auth/authTbRoleApi.js'
  import EngineFlowUpdate from '@/pages/dpsApp/engineFlow/engineFlowUpdate.vue'
  import DyPagination from '@/components/pagination/DyPagination.vue'
  import CodeEditor from '@/components/codeEditor/CodeEditor'

  export default {
    name: 'engineFlow',
    components: {
      CodeEditor,
      DyPanelBody,
      DyPanel,
      DyPanelHeader,
      EngineFlowUpdate,
      DyPagination
    },
    data () {
      return {
        /**
         * 编辑弹出框
         */
        editModal: {
          formKey: 'engine-flow-update',
          readOnly: false,
          data: {},
          visible: false
        },
        inputCaseModal: {
          inputCaseDialogVisible: false,
          inputCaseDialogText: '',
        },
        loading: true,
        /**
         * 查询参数
         */
        queryParam: {
          currentPage: 1,
          pageSize: 10
        },
        /**
         * 上方操作按钮
         */
        operateBtn: {
          addBtn: true,
          editBtn: false,
          delBtn: false,
          importBtn: true,
          exportBtn: true
        },
        /**
         * 已选中的行
         */
        selectRows: [],
        deptFilter: '',
        roleList: [],
        deptList: [],
        deptProps: {
          children: 'children',
          label: 'deptName'
        },
        tableTotal: 0,
        tableList: []
      }
    },
    created () {
      /**
       * 查询所有部门
       */
      authTbDeptApi.selectTreeList({}).then(msg => {
        this.deptList = msg.rows
      })
      /**
       * 获取可用角色
       */
      authTbRoleApi.selectListByEnabled().then(msg => {
        this.roleList = msg.rows
      })
      /**
       * 执行列表查询
       * */
      this.doSearchList()
    },

    methods: {
      /**
       * 部门筛选事件处理
       * @param value
       * @param data
       * @returns {boolean}
       */
      onDeptFilterNodeEvent (value, data) {
        if (!value) return true
        return data.deptName.indexOf(value) !== -1
      },
      /**
       * 编辑按钮事件处理
       */
      onEditBtnEvent () {
        if (this.selectRows.length != 1) {
          return
        }
        this.editModal.title = '产品编辑'
        this.editModal.readOnly = false
        this.editModal.data = this.selectRows[0]
        this.editModal.visible = true
      },
      /**
       * 新增按钮事件处理
       */
      onAddBtnEvent () {
        this.editModal.readOnly = false
        this.editModal.data = null
        this.editModal.visible = true
      },
      /**
       * 编辑某行事件处理
       * @param readOnly
       * @param data
       */
      onEditRowEvent (readOnly, data) {
        this.editModal.readOnly = false
        this.editModal.data = this.cloneDeep(data)
        this.editModal.visible = true
      },
      /**
       * 删除按钮
       * */
      onDelBtnEvent () {
        this.$confirm('您确认要删除选中的记录么？', '删除警告', {
          distinguishCancelAndClose: true,
          confirmButtonText: '确认',
          cancelButtonText: '取消'
        })
          .then(() => {
              let ids=[];
              for(let row of this.selectRows)
              {
                ids.push(row.id);
              }
              this.doDeleteList(ids)
          })
          .catch(action => {
            this.$message({
              type: 'info',
              message: '取消删除'
            })
          })
      },
      /**
       * 选中某行事件处理
       * @param selection
       */
      onSelectRowEvent (selection) {
        this.selectRows = selection
        if (this.selectRows.length != 1) {
          this.operateBtn.editBtn = false
        } else {
          this.operateBtn.editBtn = true
        }
        if (this.selectRows.length >= 1) {
          this.operateBtn.delBtn = true
        } else {
          this.operateBtn.delBtn = false
        }
      },

      /**
       * 排序事件处理
       * @param col
       */
      onSortTableEvent (col) {
        this.queryParam.fixed = col.prop
        this.queryParam.sort = col.order == 'descending' ? 'desc' : 'asc'
        this.doSearchList()
      },
      onEditInputCaseEvent (row) {
        this.inputCaseModal.inputCaseDialogVisible = true
        this.inputCaseModal.inputCaseDialogText = JSON.stringify(row.inputCase)
      },
      onRefresh (isRefresh) {
        if (isRefresh) {
          this.doSearchList()
        }
      },
      /**
       * 查询列表业务处理
       */
      doSearchList () {
        this.loading = true
        engineTbFlowApi.selectList(this.queryParam).then(msg => {
          setTimeout(() => {
            this.tableList = msg.rows
            this.tableTotal = msg.total
            this.queryParam.currentPage = msg.currentPage
            this.loading = false
          }, 200)
        }).catch(msg => {
          console.log(msg)
        })
      },
      doDeleteList(ids){
        let data={ids:ids}
        engineTbFlowApi.deleteListService(data).then(msg=>{
          this.onRefresh(true);
        })
      }
    },
    computed: {},
    watch: {
      deptFilter (val) {
        this.$refs.tree.filter(val)
      }
    }
  }
</script>
<style>

  .is-current.el-tree-node:focus > .el-tree-node__content {
    background: #409eff;
    color: white;
  }

  .is-current.el-tree-node > .el-tree-node__content:first-child {
    background: #409eff;
    color: white;
  }
</style>
