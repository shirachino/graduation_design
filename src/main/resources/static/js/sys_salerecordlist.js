import '../plug/vue/vue.min.js'
import '../plug/element-ui/element-ui.js'

Vue.config.devtools = true

var saleVm = new Vue({
    el:'#sale-app',
    data(){
        return{
            userInfo: {
                userName:'',
                pageNow: 1
            },
            saleList:[{
                sale_id:'',
                sale_info:[],
                sale_data:''
            }],
            delSalesInfo:{
                userName:'',
                salesId:''
            },
            searchSalesInfoById:{
                userName:'',
                salesId:''
            },
            searchSalesInfoByDate:{
                userName:'',
                salesDate:''
            },
            isLoading:false,
            radioForSearch: 's_id',
            value1: '',

            currentPage:1,
            saleListTotal:1
        }
    },
    created(){
        this.userInfo.userName = window.localStorage.getItem('tname')
        this.getSaleTotalNum()
        this.getSaleList()
    },
    methods: {
        getSaleList(){
            this.isLoading = true
            let qs = Qs
            let that = this
            that.userInfo.pageNow = 1
            //let postJsonData = JSON.stringify(that.userName)
            axios({
                url:'/salesRecord/getSalesList',
                method:'post',
                data:qs.stringify(that.userInfo)
            })
                .then(function (result) {
                    var saleInfoArr = result.data
                    saleInfoArr.forEach(function (value,i,arr) {
                        //传回来的json是纯字符串，转换成json对象
                        arr[i].sale_info = JSON.parse(arr[i].sale_info)
                        //当对象只有一个构不成数组，vue无法遍历，将对象转换成数组
                        if(!Array.isArray(arr[i].sale_info)){
                            //console.log(arr[i].sale_info)
                            const objToArr = [];
                            objToArr.push(arr[i].sale_info)
                            //console.log(objToArr)
                            arr[i].sale_info = objToArr
                            //console.log(arr[i].sale_info)
                        }
                    })
                    that.saleList = saleInfoArr
                    that.isLoading = false;
                })
                .catch(function (error) { // 请求失败处理
                    console.log(error);
                    that.isLoading = false;
                });
        },
        getSaleTotalNum(){
            let that = this
            let requestUrl = `/salesRecord/getSalesAll?userName=${this.userInfo.userName}`
            axios.get(requestUrl).then(function (result) {
                that.saleListTotal = result.data
            })
        },
        handleCurrentChange(val){
            //console.log(`当前页: ${val}`);
            let qs = Qs
            let that = this;
            that.userInfo.userName = window.localStorage.getItem('tname')
            that.userInfo.pageNow = val
            //let postJsonData = JSON.stringify(that.userName)
            axios({
                url: '/salesRecord/getSalesList',
                method: 'post',
                data: qs.stringify(that.userInfo)
            })
                .then(function (result) {
                    var saleInfoArrPage = result.data
                    saleInfoArrPage.forEach(function (value,i,arr) {
                        //传回来的json是纯字符串，转换成json对象
                        arr[i].sale_info = JSON.parse(arr[i].sale_info)
                        //当对象只有一个构不成数组，vue无法遍历，将对象转换成数组
                        if(!Array.isArray(arr[i].sale_info)){
                            //console.log(arr[i].sale_info)
                            const objToArr = [];
                            objToArr.push(arr[i].sale_info)
                            //console.log(objToArr)
                            arr[i].sale_info = objToArr
                            //console.log(arr[i].sale_info)
                        }
                    })
                    that.saleList = saleInfoArrPage
                })
                .catch(function (error) { // 请求失败处理
                    console.log(error)
                });
        },
        deleteSales(row){
            let qs = Qs
            let that = this
            that.delSalesInfo.userName = window.localStorage.getItem('tname')
            that.delSalesInfo.salesId = row.sale_id
            //console.log(that.delSalesInfo.salesId)
            axios({
                method:'post',
                url:'/salesRecord/salesDelete',
                data:qs.stringify(that.delSalesInfo)
            })
                .then(function (result) {
                    //console.log(result.data)
                    that.successMsg()
                    that.getSaleList()
                })
                .catch(function (error) { // 请求失败处理
                    console.log(error);
                });
        },
        searchSales(){
            let qs = Qs
            let that = this
            let postSaleSearchData
            let postSaleSearchUrl
            //判断选择了以何种类型进行查询，分情况请求后端
            if (this.radioForSearch == 's_id'){
                this.searchSalesInfoById.userName = window.localStorage.getItem('tname')
                postSaleSearchData = this.searchSalesInfoById
                postSaleSearchUrl = '/salesRecord/salesSearchById'
            } else if (this.radioForSearch == 's_date'){
                this.searchSalesInfoByDate.userName = window.localStorage.getItem('tname')
                postSaleSearchData = this.searchSalesInfoByDate
                postSaleSearchUrl = '/salesRecord/salesSearchByDate'
            } else {
                return false
            }
            //当输入为空的时候 获取销售记录列表 注意elementUI日期input初值为空字符串，清空后为null
            if (this.searchSalesInfoById.salesId == '' && (this.searchSalesInfoByDate.salesDate == null || this.searchSalesInfoByDate.salesDate == '')){
                this.getSaleList()
                return false
            }
            axios({
                url: postSaleSearchUrl,
                method: 'post',
                data: qs.stringify(postSaleSearchData)
            })
                .then(function (result) {
                    var saleInfoArr = result.data
                    saleInfoArr.forEach(function (value,i,arr) {
                        //传回来的json是纯字符串，转换成json对象
                        arr[i].sale_info = JSON.parse(arr[i].sale_info)
                        //当对象只有一个构不成数组，vue无法遍历，将对象转换成数组
                        if(!Array.isArray(arr[i].sale_info)){
                            //console.log(arr[i].sale_info)
                            const objToArr = [];
                            objToArr.push(arr[i].sale_info)
                            //console.log(objToArr)
                            arr[i].sale_info = objToArr
                            //console.log(arr[i].sale_info)
                        }
                    })
                    that.saleList = saleInfoArr
                })
                .catch(function (error) { // 请求失败处理
                    console.log(error);
                })
        },
        successMsg() {
            this.$message({
                message: '删除成功！（200）',
                type: 'success'
            });
        },
        listHeadColor(){
            return "background : #fafafa"
        },
        moneySummary(param){
            const { columns, data } = param
            const len = columns.length
            const sums = []
            columns.forEach((column, index) => {
                //如果是第一列，则最后一行展示为“总计”两个字
                if (index === 0) {
                    sums[index] = '总计'
                    //如果是最后一列，索引为列数-1，则显示计算总和
                } else if (index === len - 1) {
                    const values = data.map(item => Number(item[column.property]))
                    if (!values.every(value => isNaN(value))) {
                        sums[index] = values.reduce((prev, curr) => {
                            const value = Number(curr)
                            if (!isNaN(value)) {
                                return prev + curr
                            } else {
                                return prev
                            }
                        }, 0)
                        sums[index] = sums[index].toFixed(2)
                    } else {
                        sums[index] = 'N/A'
                    }
                    //如果是除了第一列和最后一列的其他列，则显示为空
                } else {
                    sums[index] = ''
                }
            })
            return sums
        }

    }
})