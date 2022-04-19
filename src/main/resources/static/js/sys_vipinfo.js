import '../plug/echarts/echarts.js'
import '../plug/vue/vue.min.js'
import '../plug/element-ui/element-ui.js'

Vue.config.devtools = true;

var vipVm = new Vue({
    el: '#vipApp',
    data() {
        return {
            userInfo: {
                userName: ''
            },
            vipList: [{}],
            VipInfo: '',
            searchBy: 'Phone'
        }
    },
    created() {
        this.userInfo.userName = window.localStorage.getItem('tname')
        this.getVipList()
    },
    methods: {
        getVipList() {
            let that = this
            let requestUrl = `/getVipList?userName=${this.userInfo.userName}`
            axios.get(requestUrl).then(function (result) {
                that.vipList = result.data
            }).catch(function (error) {
                this.$message({
                    type: 'error',
                    message: '获取VIP信息失败，状态：'+ error.data
                });
            })
        },
        putMoney(row) {
            //console.log(row)
            this.$prompt('请输入充值金额（数字）', 'VIP充值', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                inputPattern: /^([1-9][0-9]*)+(\.[0-9]{1,2})?$/,
            }).then(({value}) => {
                let qs = Qs
                let that = this
                let requestData = {}
                requestData.userName = this.userInfo.userName
                requestData.putNum = value
                requestData.vipId = row.vip_id
                axios({
                    method: 'post',
                    url: '/putMoneyById',
                    data: qs.stringify(requestData)
                }).then(function (result) {
                    if (result.data == "200") {
                        that.$message({
                            type: 'success',
                            message: '充值成功！充值金额: ' + value
                        });
                        that.getVipList()
                    }
                })
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '取消充值'
                });
            });
        },
        deleteVip(row) {
            //console.log(row)
            let that = this
            let vipId = row.vip_id
            let requestUrl = `/deleteVipById?userName=${this.userInfo.userName}&vipId=${vipId}`
            //console.log(requestUrl)
            axios.get(requestUrl)
                .then(function (result) {
                if (result.data == "200") {
                    that.$message({
                        type: 'success',
                        message: '注销成功！'
                    });
                    that.getVipList()
                }
            }).catch(function (error) {
                    this.$message({
                        type: 'error',
                        message: '出错了，请稍后再试，状态：'+ error.data
                    });
                })
        },
        updateVipInfo(row) {
            console.log(row)
        },

        searchVip() {
            if (this.VipInfo == '') {
                this.getVipList()
                return false
            }
            let that = this
            let requestUrl = `/searchVipBy${this.searchBy}?userName=${this.userInfo.userName}&vipInfo=${this.VipInfo}`
            axios.get(requestUrl).then(function (result) {
                that.vipList = result.data
            }).catch(function (error) {
                this.$message({
                    type: 'error',
                    message: '查询失败，请稍后再试，状态：'+ error.data
                });
            })
        },

    }
})