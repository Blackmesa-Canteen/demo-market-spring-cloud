<template>
  <el-tree :data="menus" :props="defaultProps" @node-click="handleNodeClick"></el-tree>
</template>

<script>
  export default {
    comments:{},
    props:{},

    data() {
      return {
        menus: [],
        defaultProps: {
          children: "children",
          label: "name"
        }
      };
    },

    methods: {
      handleNodeClick(data) {
        console.log(data);
      },

      // 获取数据列表
      getMenus () {
        this.$http({
          url: this.$http.adornUrl('/product/category/list/tree'),
          method: 'get',
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.menus = data.data
          } else {
            console.log("数据采集失败 ");
          }
        })
      }

    },

    created() {
      this.getMenus();
    }


  };
</script>

<style>
</style>
