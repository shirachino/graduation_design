import '../plug/echarts/echarts.js'
import '../plug/vue/vue.min.js'
import '../plug/element-ui/element-ui.js'

Vue.config.devtools = true

var turnoverVm = new Vue({
    el: '#turnoverApp',
    data() {
        return {
            userInfo:{
              userName:''
            },
            turnoverType:[],
            turnoverRec:{
                dayTurnover:'100',
                monthTurnover:'1000',
                yearTurnover:'10000'
            },
            incTurnover:'50'
        }
    },
    created() {
        this.getDayTurnover()
        this.getTurnoverType()
    },
    mounted() {
        this.turnoverStatic()
    },
    methods: {
        /**
         * 获取每日营业额
         */
        getDayTurnover(){
            let qs = Qs
            let that = this
            this.userInfo.userName = window.localStorage.getItem('tname')
            axios({
                method:'post',
                url:'/getDayTurnover',
                data:qs.stringify(that.userInfo)
            }).then(function (result) {
                that.turnoverRec.dayTurnover = result.data[0]
                that.turnoverRec.monthTurnover = result.data[1]
                that.turnoverRec.yearTurnover = result.data[2]
                that.incTurnover = result.data[3]
            }).catch(function (error) {
                console.log(error)
            })
        },
        /**
         *
         */
        getTurnoverType(){
            let qs = Qs
            let that = this
            this.userInfo.userName = window.localStorage.getItem('tname')
            axios({
                method:'post',
                url:'/getTurnoverType',
                data:qs.stringify(that.userInfo)
            }).then(function (result) {
                that.turnoverType = result.data
                console.log(that.turnoverType)
                that.typeTurnoverChart()
            }).catch(function (error) {
                console.log(error)
            })
        },
        turnoverStatic() {
            var chartDom = document.getElementById('turnoverChart');
            var myChart = echarts.init(chartDom);
            var option;

            option = {
                title: {
                    text: '营业额统计'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: ['每日', '每月', '每年']
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                xAxis: [{
                    type: 'category',
                    boundaryGap: false,
                    data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
                }, {
                    type: 'category',
                    boundaryGap: false,
                    position: 'bottom',
                    offset: 30,
                    data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
                }, {
                    type: 'category',
                    boundaryGap: false,
                    position: 'top',
                    data: ['第一季度', '第二季度', '第三季度', '第四季度']
                }],
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        name: '每日',
                        type: 'line',
                        stack: 'Total',
                        xAxisIndex: '0',
                        data: [120, 132, 101, 134, 90, 230, 210]
                    },
                    {
                        name: '每月',
                        type: 'line',
                        stack: 'Total',
                        xAxisIndex: '1',
                        data: [150, 232, 201, 154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410]
                    },
                    {
                        name: '每年',
                        type: 'line',
                        stack: 'Total',
                        xAxisIndex: '2',
                        data: [1320, 1332, 1301, 1334]
                    }
                ]
            };
            myChart.setOption(option);
        },
        typeTurnoverChart(){
            var chartDom = document.getElementById('type-turnover');
            var myChart = echarts.init(chartDom);
            var option;
            option = {
                dataset:{
                    dimensions: ['turnover','goods_type'],
                    source: this.turnoverType
                },
                series: [
                    {
                        name: '营业额商品类型分布图',
                        type: 'pie',
                        radius: [10, 100],
                        center: ['50%', '50%'],
                        itemStyle: {
                            borderRadius: 8
                        }
                    }
                ]
            };
            option && myChart.setOption(option);
        }
    }
})