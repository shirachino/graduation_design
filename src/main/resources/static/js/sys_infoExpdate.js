import '../plug/vue/vue.min.js'
import '../plug/element-ui/element-ui.js'

Vue.config.devtools = true

var expDateVm = new Vue({
    el:'#expDateApp',
    data(){
        return{
            userName:'',
            expGoodsList:[{
                goods_id:'',
                goods_name:'',
                goods_type:'',
                goods_num:'',
                goods_EXPdate:'',
                goods_expDay:''
            }]
        }
    },
    created(){
        this.userName = window.localStorage.getItem('tname')
        this.getExpGoods()
    },
    methods:{
        getExpGoods(){
            let that = this
            axios.get(`/getExpiredList?userName=${that.userName}`).then(function (result) {
                that.expGoodsList = result.data
            }).catch(function (error) {
                that.$message.error('系统错误')
            });
        },
        deleteGoods(row){
            let that = this
            axios.get(`/stockDelete?userName=${that.userName}&goodsId=${row.goods_id}`)
                .then(function (result) {
                    if (result.data == "200") {
                        that.$message.success('下架成功！')
                        that.getExpGoods()
                    } else {
                        that.$message.error('下架失败！状态：'+ result.data)
                    }
                })
                .catch(function (error) {
                    that.$message.error('系统错误')
                });
        }
    }
})