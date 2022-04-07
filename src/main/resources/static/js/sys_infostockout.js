import '../plug/vue/vue.min.js'
import 'https://unpkg.com/element-ui/lib/index.js'

Vue.config.devtools = true

var outVm = new Vue({
    el:'#datalist',
    data(){
        return{
            outGoodsList:[{
                goods_id:1,
                goods_name:1,
                goods_type:1,
                goods_num:1,
            }],
            outGoodsInfo:[{
                goods_name:'绿茶',
                goods_num:'1'
            },{
                goods_name:'红茶',
                goods_num:'2'
            }]
        }
    }
})