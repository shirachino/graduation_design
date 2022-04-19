import '../plug/vue/vue.min.js'
import '../plug/jquery/jquery-3.6.0.min.js'
import '../plug/element-ui/element-ui.js'

Vue.config.devtools = true

//头部 搜索、elementUI
var headVm = new Vue({
    el: '#goods-head',
    data() {
        return {
            searchGoodsInfo: {
                userName: '',
                goodsName: '',
            },
            searchResult: [],
            searchBy:'goodsName'
        }
    },
    methods: {
        openAddModal() {
            $('.my-mask').show();
            $('.my-mask').css('opacity', 1);
            $('.add').show();
            $('.add').css('opacity', 1);
        },
        searchGoodsByName() {
            if (this.searchGoodsInfo.goodsName == ''){
                goodVm.getGoodsList()
            }
            let qs = Qs
            let that = this;
            that.searchGoodsInfo.userName = window.localStorage.getItem('tname')
            axios({
                url: '/searchByName',
                method: 'post',
                data: qs.stringify(that.searchGoodsInfo)
            })
                .then(function (results) {
                    that.searchResult = results.data
                    goodVm.searchResultList()
                })
                .catch(function (error) { // 请求失败处理
                    that.errorMsg()
                    console.log(error);
                })
        },
        errorMsg() {
            this.$message.error('搜索失败，请重试！（500）');
        }
    }
})

//获取APP
var goodVm = new Vue({
    el: '#datalist',
    data: {
        //用户信息
        userInfo: {
            userName: '',
            pageNow: ''
        },
        //商品列表
        goodsList: [],
        typeFilters: [],
        //删除的商品信息
        delGoodsInfo: {
            userName: '',
            goodsId: ''
        },
        //临时存放修改商品信息
        updGoodsInfo: {
            userName: '',
            goodsId: '',
            goodsName: '',
            goodsType: '',
            goodsNum: '',
            goodsInPrice: '',
            goodsOutPrice: '',
            goodsSHLdate: '',
            goodsEXPdate: ''
        },
        isLoading: true,
        //选择页数
        currentPage: 1,
        //总件数
        stockTotal: ''
    },
    created() {
        this.userInfo.userName = window.localStorage.getItem('tname')
        this.getAllType()
        this.getGoodsList()
        this.getStockAllNum()
    },
    methods: {
        getStockAllNum() {
            let that = this;
            let requestUrl = `/getStockAll?userName=${this.userInfo.userName}`
            axios.get(requestUrl)
                .then(function (result) {
                    that.stockTotal = result.data
                })
        },
        getGoodsList() {
            let qs = Qs
            let that = this;
            that.userInfo.pageNow = that.currentPage
            //let postJsonData = JSON.stringify(that.userName)
            axios({
                url: '/getStockList',
                method: 'post',
                data: qs.stringify(that.userInfo)
            })
                .then(function (result) {
                    //console.log(result.data)
                    that.goodsList = result.data
                    that.isLoading = false
                })
                .catch(function (error) { // 请求失败处理
                    console.log(error)
                    that.isLoading = false
                });
        },
        getAllType() {
            let that = this
            let requestUrl = `/getAllType?userName=${this.userInfo.userName}`
            axios.get(requestUrl).then(function (result) {
                let filters = []
                for (var i = 0; i < result.data.length; i++) {
                    let oneType = {}
                    oneType.text = result.data[i]
                    oneType.value = result.data[i]
                    filters.push(oneType)
                }
                that.typeFilters = filters
            })
        },
        searchResultList() {
            goodVm.goodsList = headVm.searchResult;
        },
        //删除商品
        deleteGoods(row) {
            let qs = Qs
            let that = this
            this.delGoodsInfo.userName = window.localStorage.getItem('tname')
            this.delGoodsInfo.goodsId = row.goods_id
            axios({
                method: 'post',
                url: '/stockDelete',
                data: qs.stringify(that.delGoodsInfo)
            })
                .then(function (result) {
                    if (result.data == "200") {
                        that.successMsg()
                        that.getGoodsList()
                    }
                })
                .catch(function (error) { // 请求失败处理
                    that.errorMsg()
                    console.log(error);
                });
        },
        //把修改前信息赋给更新模态框APP
        moveUpdateRowToModel(row) {
            $('.my-mask').show();
            $('.my-mask').css('opacity', 1);
            $('.rev').show();
            $('.rev').css('opacity', 1);
            this.updGoodsInfo.goodsId = row.goods_id
            this.updGoodsInfo.goodsName = row.goods_name
            this.updGoodsInfo.goodsType = row.goods_type
            this.updGoodsInfo.goodsNum = row.goods_num
            this.updGoodsInfo.goodsInPrice = row.goods_inPrice
            this.updGoodsInfo.goodsOutPrice = row.goods_outPrice
            this.updGoodsInfo.goodsSHLdate = row.goods_SHLdate
            this.updGoodsInfo.goodsEXPdate = row.goods_EXPdate
            revVm.UpdGoodsInfo = this.updGoodsInfo
        },
        tableRowClassName({row, rowIndex}) {
            if (row.goods_num === 0) {
                return 'serious-row'
            } else if (row.goods_num <= 10 && row.goods_num !== 0) {
                return 'warning-row'
            }
            return ''
        },
        filterHandler(value, row, column) {
            const property = column['property'];
            // console.log(row[property] === value)
            return row[property] === value;
        },
        filterChange(value) {
            /**
             * 没做出来

            console.log(value.type)
            let qs = Qs
            let that = this
            let requestData = {}
            requestData.userName = window.localStorage.getItem('tname')
            requestData.pageNow = that.currentPage
            if (value.type[0] == undefined){
                this.getGoodsList()
            } else {
                requestData.goodsType = value.type[0]
            }
            axios({
                url: '/getStockListByType',
                method: 'post',
                data: qs.stringify(requestData)
            })
                .then(function (result) {

                })
             */
        },
        handleCurrentChange(val) {
            //console.log(`当前页: ${val}`);
            let qs = Qs
            let that = this;
            that.userInfo.pageNow = val
            //let postJsonData = JSON.stringify(that.userName)
            axios({
                url: '/getStockList',
                method: 'post',
                data: qs.stringify(that.userInfo)
            })
                .then(function (result) {
                    //console.log(result.data)
                    that.goodsList = result.data
                })
                .catch(function (error) { // 请求失败处理
                    console.log(error)
                });
        },
        successMsg() {
            this.$message({
                message: '删除成功！（200）',
                type: 'success'
            });
        },
        errorMsg() {
            this.$message.error('删除失败，请重试！（500）');
        }
    }
})

//插入APP
var goodAddVm = new Vue({
    el: '#addFun',
    data() {
        return {
            addGoodsInfo: {
                userName: '',
                goodsId: '',
                goodsName: '',
                goodsType: '',
                goodsNum: '',
                goodsInPrice: '',
                goodsOutPrice: '',
                goodsSHLdate: '',
                goodsEXPdate: ''
            }
        }
    },
    methods: {

        isInputRight() {
            var that = this;
            var regExpInt = /^[1-9]\d*$/
            var regExpDou = /^(([^0][0-9]+|0)\.([0-9]{1,2})$)|^(([^0][0-9]+|0)$)|^(([1-9]+)\.([0-9]{1,2})$)|^(([1-9]+)$)/
            if (regExpInt.test(that.addGoodsInfo.goodsId) === false || that.addGoodsInfo.goodsId === '') {
                that.warnMsg('商品编号格式错误或未填写！请检查。')
                return false
            } else if (that.addGoodsInfo.goodsName === '') {
                that.warnMsg('商品名称未填写！')
                return false
            } else if (that.addGoodsInfo.goodsType === '') {
                that.warnMsg('商品类型未填写！')
                return false
            } else if (regExpInt.test(that.addGoodsInfo.goodsNum) === false || that.addGoodsInfo.goodsNum === '') {
                that.warnMsg('商品数量有误！')
                return false
            } else if (regExpDou.test(that.addGoodsInfo.goodsInPrice) === false || that.addGoodsInfo.goodsInPrice === '') {
                that.warnMsg('商品进价未填写或格式错误！')
                return false
            } else if (regExpDou.test(that.addGoodsInfo.goodsOutPrice) === false || that.addGoodsInfo.goodsOutPrice === '') {
                that.warnMsg('商品售价未填写或格式错误！')
                return false
            } else if (that.addGoodsInfo.goodsSHLdate === '') {
                that.warnMsg('上架日期未填写！')
                return false
            } else {
                return true
            }
        },
        addGoodsToStock() {
            let that = this
            //判断输入是否正确
            if (that.isInputRight() == false) {
                return false
            }

            $.ajax({
                async: false,
                type: 'post',
                url: '/stockInsert',
                data: that.addGoodsInfo,
                dataType: "json",
                success: function (res) {
                    if (res == "200") {
                        that.successMsg()
                        $('.close').eq(0).trigger("click")
                        goodVm.getGoodsList()
                    } else {
                        that.errorMsg()
                    }
                }
            })
            // axios({
            //     type: 'post',
            //     url: '/stockInsert',
            //     data: qs.stringify(that.addGoodsInfo),
            // })
            //     .then(function (result) {
            //         console.log(this.data)
            //         if (result.data == "200") {
            //             that.successMsg()
            //             $('.close').eq(0).trigger("click")
            //             goodVm.getGoodsList()
            //         } else {
            //             console.log(this.data)
            //             that.errorMsg()
            //         }
            //     })
            //     .catch(function (error) { // 请求失败处理
            //         that.errorMsg()
            //         console.log("添加失败\n" + error);
            //     });

        },
        successMsg() {
            this.$message({
                message: '入库成功！（200）',
                type: 'success'
            });
        },
        errorMsg() {
            this.$message.error('入库失败，请重试！（500）');
        },
        warnMsg(msg) {
            this.$message({
                showClose: true,
                message: msg,
                type: 'warning'
            });
        }
    }
})

var revVm = new Vue({
    el: '#revFun',
    data() {
        return {
            UpdGoodsInfo: {
                userName: '',
                goodsId: '',
                goodsName: '',
                goodsType: '',
                goodsNum: '',
                goodsInPrice: '',
                goodsOutPrice: '',
                goodsSHLdate: '',
                goodsEXPdate: ''
            }
        }
    },
    methods: {
        //修改商品
        updateGoods() {
            let qs = Qs
            let that = this
            this.UpdGoodsInfo.userName = window.localStorage.getItem('tname')
            axios({
                method: 'post',
                url: '/updateStock',
                data: qs.stringify(this.UpdGoodsInfo)
            })
                .then(function (result) {
                    if (result.data == "200") {
                        goodVm.getGoodsList()
                        that.successMsg()
                        $('.close').eq(1).trigger("click")
                    }
                })
                .catch(function (error) { // 请求失败处理
                    that.errorMsg()
                    console.log(error);
                });
        },
        successMsg() {
            this.$message({
                message: '修改成功！（200）',
                type: 'success'
            })
        },
        errorMsg() {
            this.$message.error('修改失败，请重试！（500）');
        }
    }
})

$(function () {
    // 模态框淡入淡出
    // 调用关闭
    // for(i=0; i<$('.close').length;i++){
    // 	$('.close').on('click',() => { cls($('.mydefmodal')); });
    // 	$('.mydefbtn_default').on('click',() => { cls($('.mydefmodal')); });
    // } //for循环失败


    $('.close').eq(0).on('click', () => {
        cls($('.add').get(0));
    });
    $('.mydefbtn_default').eq(0).on('click', () => {
        cls($('.add').get(0));
    });
    $('.close').eq(1).on('click', () => {
        cls($('.rev').get(0));
    });
    $('.mydefbtn_default').eq(1).on('click', () => {
        cls($('.rev').get(0));
    });
    $('.close').eq(2).on('click', () => {
        cls($('.sold').get(0));
    });
    $('.mydefbtn_default').eq(2).on('click', () => {
        cls($('.sold').get(0));
    });

    // 关闭函数
    function cls(modal) {
        $('.my-mask').css('animation', 'fadeout .3s');
        modal.style.animation = 'fadeout .3s';
        setTimeout(function () {
            $('.my-mask').hide();
            modal.style.display = 'none';
            $('.my-mask').css('animation', 'fadein .7s');
            modal.style.animation = 'fadein .5s';
        }, 300)
        $('.my-mask').css('opacity', 0);
        modal.style.opacity = 0;
    }

    // 原生JS写法
    // btnadd.addEventListener('click', function () {
    // 	mask.style.display = 'block';
    // 	mask.style.opacity = 1;
    // 	addmodal.style.display = 'block';
    // 	addmodal.style.opacity = 1;
    // });
    // btnrev.addEventListener('click', function () {
    // 	mask.style.display = 'block';
    // 	mask.style.opacity = 1;
    // 	revmodal.style.display = 'block';
    // 	revmodal.style.opacity = 1;
    // });
    // btnsold.addEventListener('click', function () {
    // 	mask.style.display = 'block';
    // 	mask.style.opacity = 1;
    // 	soldmodal.style.display = 'block';
    // 	soldmodal.style.opacity = 1;
    // });
    // 关闭

    // close[1].addEventListener('click', clsrev);
    // btndef[1].addEventListener('click', clsrev);
    // function clsrev() {
    // 	mask.style.animation = 'fadeout .3s';
    // 	revmodal.style.animation = 'fadeout .3s';
    // 	setTimeout(function () {
    // 		mask.style.display = 'none';
    // 		revmodal.style.display = 'none';
    // 		mask.style.animation = 'fadein .7s';
    // 		revmodal.style.animation = 'fadein .5s';
    // 	}, 300)
    // 	mask.style.opacity = 0;
    // 	revmodal.style.opacity = 0;
    // }

    // close[2].addEventListener('click', clssold);
    // btndef[2].addEventListener('click', clssold);
    // function clssold() {
    // 	mask.style.animation = 'fadeout .3s';
    // 	soldmodal.style.animation = 'fadeout .3s';
    // 	setTimeout(function () {
    // 		mask.style.display = 'none';
    // 		soldmodal.style.display = 'none';
    // 		mask.style.animation = 'fadein .7s';
    // 		soldmodal.style.animation = 'fadein .5s';
    // 	}, 300)
    // 	mask.style.opacity = 0;
    // 	soldmodal.style.opacity = 0;
    // }
    // 模态框淡入淡出end
});