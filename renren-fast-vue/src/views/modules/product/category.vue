<template>
  <div>
    <el-tree :data="menus" :props="defaultProps"
             :expand-on-click-node="false"
             show-checkbox
             node-key="catId"
             :default-expanded-keys="expandedKey">
    <span class="custom-tree-node" slot-scope="{ node, data }">
      <span>{{ node.label }}</span>
      <span>
        <el-button v-if="node.level <= 2" type="text" size="mini" @click="() => append(data)">
          Append
        </el-button>
        <el-button v-if="node.childNodes.length === 0" type="text" size="mini" @click="() => remove(node, data)">
          Delete
        </el-button>

        <el-button type="text" size="mini" @click="() => edit(data)">
          Edit
        </el-button>
      </span>
    </span>
    </el-tree>

    <el-dialog
      :title="dialogTitle"
      :visible.sync="dialogVisible"
      :close-on-click-modal="false"
      width="30%">
      <el-form :model="category">
        <el-form-item label="分类名称">
          <el-input v-model="category.name" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="分类图标">
          <el-input v-model="category.icon" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="计量单位">
          <el-input v-model="category.productUnit" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="submitData">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  comments: {},
  props: {},

  data () {
    return {
      dialogType: '', // edit or add
      dialogTitle: '数据变动',
      category: {
        name: '',
        parentCid: 0,
        catLevel: 0,
        showStatus: 1,
        sort: 0,
        catId: null,
        icon: '',
        productUnit: ''
      },
      menus: [],
      expandedKey: [],
      dialogVisible: false,
      defaultProps: {
        children: 'children',
        label: 'name'
      }
    }
  },

  methods: {

    // 复用编辑dialog的公用点击事件方法
    submitData () {
      if (this.dialogType === 'add') {
        this.addCategory()
      } else if (this.dialogType === 'edit') {
        this.editCategory()
      }
    },

    // 点击按钮后添加三级分类的方法,此时和后台通信
    addCategory () {
      this.$http({
        url: this.$http.adornUrl('/product/category/save'),
        method: 'post',
        data: this.$http.adornData(this.category, false)
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.$message({
            message: '这是一条成功消息',
            type: 'success'
          })
        } else {
          this.$message({
            message: '这是一条失败消息',
            type: 'warning'
          })
        }
        // 关闭编辑对话框
        this.dialogVisible = false
        // 然后重新刷新菜单
        this.getMenus()
        // 保持原来的展开状态
        this.expandedKey = [this.category.parentCid]
      })
    },

    // 点击对话框确认按钮后编辑三级分类的方法,此时和后台通信
    editCategory () {
      // 只解构出需要用到的修改变量,其他为null防止数据库覆盖
      let {catId, name, icon, productUnit} = this.category
      let data = {
        catId: catId,
        name: name,
        icon: icon,
        productUnit: productUnit
      }

      this.$http({
        url: this.$http.adornUrl('/product/category/update'),
        method: 'post',
        data: this.$http.adornData(data, false)
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.$message({
            message: '这是一条修改成功消息',
            type: 'success'
          })
          // 关闭编辑对话框
          this.dialogVisible = false
          // 然后重新刷新菜单
          this.getMenus()
          // 保持原来的展开状态
          this.expandedKey = [this.category.parentCid]
        } else {
          this.$message({
            message: '这是一条修改失败消息',
            type: 'warning'
          })
        }
      })
    },

    // 修改category的方法,生成提交给后台的数据结构
    edit (data) {
      console.log('要修改的 ', data)
      this.dialogType = 'edit'
      this.dialogTitle = '目录修改'
      this.dialogVisible = true

      // 为了一致性,需要发送请求来回显最新数据
      this.$http({
        url: this.$http.adornUrl(`/product/category/info/${data.catId}`),
        method: 'get'
      }).then(({
        data
      }) => {
        if (data && data.code === 0) {
          this.category.name = data.data.name
          this.category.catId = data.data.catId
          this.category.icon = data.data.icon
          this.category.productUnit = data.data.productUnit
          this.category.parentCid = data.data.parentCid
        } else {
          console.log('数据采集失败')
        }
      })
    },

    // 获取数据列表
    getMenus () {
      this.$http({
        url: this.$http.adornUrl('/product/category/list/tree'),
        method: 'get'
      }).then(({
        data
      }) => {
        if (data && data.code === 0) {
          this.menus = data.data
        } else {
          this.$message({
            message: '数据列表获取失败了',
            type: 'warning'
          })
        }
      })
    },

    // 新增,生成提交给后台的数据结构
    append (data) {
      console.log(data)

      this.dialogType = 'add'
      this.dialogTitle = '目录新增'
      // open the dialog
      this.dialogVisible = true

      // 获取当前点击的
      this.category.parentCid = data.catId
      this.category.catLevel = data.catLevel * 1 + 1
      // 清空编辑产生的垃圾值
      this.category.name = ''
      this.category.catId = null
      this.category.icon = ''
      this.category.productUnit = ''
    },

    remove (node, data) {
      let ids = [data.catId]

      this.$confirm(`是否删除当前目录: ${data.name} ?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/product/category/delete'),
          method: 'post',
          data: this.$http.adornData(ids, false)
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.$message({
              message: '这是一条成功消息',
              type: 'success'
            })
            // 然后重新刷新菜单
            this.getMenus()
            // 保持原来的展开状态
            this.expandedKey = [node.parent.data.catId]
          } else {
            console.log('delete Failed!')
          }
        }).catch(() => {
          this.$message({
            message: '这是一条失败消息',
            type: 'warning'
          })
        })

        console.log('remove', node, data)
      })
    }
  },

  created () {
    this.getMenus()
  }

}
</script>

<style>
</style>
