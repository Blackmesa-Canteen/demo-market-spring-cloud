<template>
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
        <el-button v-if="node.childNodes.length == 0" type="text" size="mini" @click="() => remove(node, data)">
          Delete
        </el-button>
      </span>
    </span>
  </el-tree>
</template>

<script>
export default {
  comments: {},
  props: {},

  data () {
    return {
      menus: [],
      expandedKey: [],
      defaultProps: {
        children: 'children',
        label: 'name'
      }
    }
  },

  methods: {

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
          console.log('数据采集失败')
        }
      })
    },

    append (data) {
      console.log(data)
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
