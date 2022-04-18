import '../plug/vue/vue.min.js'
import '../plug/element-ui/element-ui.js'

Vue.config.devtools = true

var outVm = new Vue({
    el:'#datalist',
    data(){
        return{
            userInfo:{
                userName:''
            },
            requestInfo:{
              userName:'',
              pageNow: ''
            },
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
            }],
            outGoodsAll:''
        }
    },
    created(){
        this.userInfo.userName = window.localStorage.getItem('tname')
        this.getStockOutAll()
        this.getStockOutList()
        this.getOutGoods5()
    },
    methods:{
        getStockOutAll(){
            let qs = Qs
            let that = this
            axios({
                method:'POST',
                url:'/getStockOutAll',
                data: qs.stringify(that.userInfo)
            }).then(function (result) {
                that.outGoodsAll = result.data
                //console.log(that.outGoodsAll)
            })
        },
        getStockOutList(){
            let qs = Qs
            let that = this
            this.requestInfo.userName = window.localStorage.getItem('tname')
            this.requestInfo.pageNow = 1
            axios({
                method: 'post',
                url: '/getStockOut',
                data: qs.stringify(that.requestInfo)
            }).then(function (result) {
                that.outGoodsList = result.data;
                //console.log(that.outGoodsList)
            }).catch(function (error) {
                console.log(error)
            })
        },
        getOutGoods5(){
            let qs = Qs
            let that = this
            axios({
                method: 'post',
                url: '/getStockOutGoods',
                data: qs.stringify(that.userInfo)
            }).then(function (result) {
                that.outGoodsInfo = result.data;
                console.log(that.outGoodsInfo)
            })
        }
    }
})