import '../plug/vue/vue.min.js'
import '../plug/element-ui/element-ui.js'

Vue.config.devtools = true

var outVm = new Vue({
    el: '#datalist',
    data() {
        return {
            userInfo: {
                userName: ''
            },
            requestInfo: {
                userName: '',
                pageNow: ''
            },
            outGoodsList: [{
                goods_id: 1,
                goods_name: 1,
                goods_type: 1,
                goods_num: 1,
            }],
            outGoodsInfo: [{
                goods_name: '绿茶',
                goods_num: '1'
            }, {
                goods_name: '红茶',
                goods_num: '2'
            }],
            outGoodsAll: ''
        }
    },
    created() {
        this.userInfo.userName = window.localStorage.getItem('tname')
        this.getStockOutAll()
        this.getStockOutList()
        this.getOutGoods5()
    },
    methods: {
        getStockOutAll() {
            let that = this
            let requestUrl = `/getStockOutAll?userName=${this.userInfo.userName}`
            axios.get(requestUrl).then(function (result) {
                that.outGoodsAll = result.data
                //console.log(that.outGoodsAll)
            })
        },
        getStockOutList() {
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
        getOutGoods5() {
            let that = this
            let requestUrl = `/getStockOutGoods?userName=${this.userInfo.userName}`
            axios.get(requestUrl).then(function (result) {
                that.outGoodsInfo = result.data;
                console.log(that.outGoodsInfo)
            })
        },
        addGoodsNum(row) {
            this.$prompt('请输入补货数量', '数量', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                inputPattern: /^[0-9]*$/,
                inputErrorMessage: '请输入数字'
            }).then(({value}) => {
                let qs = Qs
                let that = this
                let requestUrl = `/addOutGoodsNum?userName=${this.userInfo.userName}`
                let requestData = {}
                requestData.goodsId = row.goods_id
                requestData.addNum = value
                axios({
                    method: 'post',
                    url: requestUrl,
                    data: qs.stringify(requestData)
                }).then(function (result) {
                    that.$message({
                        type: 'success',
                        message: '补货成功！状态：' + result.data
                    })
                    that.getStockOutList()
                    that.getOutGoods5()
                })
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '取消输入'
                });
            });
        }
    }
})