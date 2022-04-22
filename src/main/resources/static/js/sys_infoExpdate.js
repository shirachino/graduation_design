import '../plug/vue/vue.min.js'
import '../plug/element-ui/element-ui.js'

Vue.config.devtools = true

var expDateVm = new Vue({
    el:'#expDateApp',
    data(){
        return{
            expGoodsList:[{
                goods_id:'',
                goods_name:'',
                goods_type:'',
                goods_num:'',
                goods_EXPdate:''
            }]
        }
    },
    methods:{

    }
})