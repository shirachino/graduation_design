import '../plug/vue/vue.min.js'
import '../plug/element-ui/element-ui.js'

Vue.config.devtools = true;

var cashierVm = new Vue({
    el: '#cashierApp',
    data() {
        return{
            userName:'',
            addSaleGoodsList:[],
            searchInp:'',
            searchResultList:[],
            saleSum:0,
            saleVipPhone:'',
            isUseVipBalance: false,
            searchVipName:'',
        }
    },
    created(){
        this.userName = window.localStorage.getItem('tname')
    },
    mounted(){
    },
    methods: {
        searchGoodsFun() {
            if (this.searchInp == '') {
                this.$message.error('请输入搜索商品名')
                return false
            }
            let that = this;
            axios.get(`/searchByName?userName=${this.userName}&goodsName=${this.searchInp}`)
                .then(function (results) {
                    that.searchResultList = results.data
                })
                .catch(function (error) { // 请求失败处理
                    that.errorMsg()
                    console.log(error);
                })
        },
        addToCashier(row) {
            if (this.addSaleGoodsList.some(function (val) {
                return val.sale_goodsId && val.sale_goodsId == row.goods_id
            })) {
                this.$message.error('已经添加过了哦~')
                return false
            }
            let addGoodsObj = {}
            addGoodsObj.sale_goodsId = row.goods_id.toString()
            addGoodsObj.sale_goodsName = row.goods_name
            addGoodsObj.sale_outPrice = row.goods_outPrice.toString()
            addGoodsObj.goods_num = row.goods_num
            addGoodsObj.sale_goodsNum = '1'
            addGoodsObj.sale_salary = addGoodsObj.sale_goodsNum * addGoodsObj.sale_outPrice.toString()
            this.addSaleGoodsList.push(addGoodsObj)
            this.handleChange()
        },
        handleChange() {
            let sum = 0
            this.addSaleGoodsList.forEach(function (value) {
                value.sale_salary = (value.sale_outPrice * value.sale_goodsNum).toString()
                sum += value.sale_outPrice * value.sale_goodsNum
                value.sale_goodsNum = value.sale_goodsNum.toString()
            })
            this.saleSum = sum
        },
        clearCar(){
            this.saleSum = 0
            this.addSaleGoodsList = []
        },
        saleGoods() {
            if (this.addSaleGoodsList.length == 0){
                this.errorMsg("还没有添加任何商品哦")
                return false
            }
            let qs = Qs
            let that = this
            this.addSaleGoodsList.forEach(function (value) {
                value.sale_goodsNum = value.sale_goodsNum.toString()
            })//需要先转换成字符型
            let requestObj = this.addSaleGoodsList
            requestObj.forEach(function (val) {
                delete val.goods_num
            })
            let requestData = {}
            requestData.userName = this.userName
            requestData.saleInfo = JSON.stringify(requestObj)
            requestData.vipPhone = this.saleVipPhone
            requestData.isUseVipBalance = this.isUseVipBalance
            //console.log(requestData)
            axios({
                method: 'post',
                url: '/saleGoods',
                data: qs.stringify(requestData)
            }).then(function (result) {
                //console.log(result.data)
                if(result.data == "200"){
                    that.dialogFormVisible = false
                    that.successMsg('销售成功！')
                } else if (result.data == "601"){
                    that.errorMsg('Vip账户余额不足！')
                }
            }).catch(function (error) {
                that.errorMsg('销售失败！请重试。状态：'+ error.data)
            })
        },
        searchVip(){
            let regExpPhone = /^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/
            if (regExpPhone.test(this.saleVipPhone) === false ||this.saleVipPhone ===''){
                this.searchVipName = '请输入正确的手机号'
                return false
            }
            let that = this
            let userName = window.localStorage.getItem('tname')
            let requestUrl = `/searchVipByPhone?userName=${userName}&vipInfo=${this.saleVipPhone}`
            axios.get(requestUrl).then(function (result) {
                //console.log(result.data,result.data[0] == null)
                if(result.data[0] == null){
                    that.$message({
                        type: 'info',
                        message: '没有找到该用户'
                    });
                } else {
                    that.successMsg('查询到Vip')
                    that.searchVipName = result.data[0].vip_name
                }
            }).catch(function (error) {
                console.log(error)
                that.$message({
                    type: 'error',
                    message: '查询失败，状态：'+ error.data
                });
            })
        },
        successMsg(msg) {
            this.$message({
                message: msg,
                type: 'success'
            });
        },
        errorMsg(msg) {
            this.$message.error(msg);
        }
    }
})