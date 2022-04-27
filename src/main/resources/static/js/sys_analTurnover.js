import '../plug/echarts/echarts.js'
import '../plug/vue/vue.min.js'
import '../plug/element-ui/element-ui.js'

Vue.config.devtools = true

var turnoverVm = new Vue({
    el: '#turnoverApp',
    data() {
        return {
            userInfo: {
                userName: ''
            },
            turnoverType: [],
            turnoverRec: {
                dayTurnover: '100',
                monthTurnover: '1000',
                yearTurnover: '10000'
            },
            incTurnover: '',
            showTurnover: ''
        }
    },
    created() {
        this.userInfo.userName = window.localStorage.getItem('tname')
        this.getDayTurnover()
        this.getTurnoverType()
        this.getTurnoverStat()
    },
    mounted() {
        //this.turnoverStatic()
    },
    methods: {
        /**
         * 获取每日营业额
         */
        getDayTurnover() {
            let that = this
            let requestUrl = `/getTurnoverList?userName=${this.userInfo.userName}`

            axios.get(requestUrl).then(function (result) {
                that.turnoverRec.dayTurnover = result.data[0]
                that.turnoverRec.monthTurnover = result.data[1]
                that.turnoverRec.yearTurnover = result.data[2]
                //先查看是正数还是负数，再取绝对值显示
                that.incTurnover = result.data[3]
                that.showTurnover = Math.abs(that.incTurnover)
                //console.log(that.incTurnover,typeof that.incTurnover)
            }).catch(function (error) {
                console.log(error)
            })
        },
        /**
         *获取每个类型的营业额
         */
        getTurnoverType() {
            let that = this
            let requestUrl = `/getTurnoverType?userName=${this.userInfo.userName}`
            axios.get(requestUrl).then(function (result) {
                that.turnoverType = result.data
                //console.log(that.turnoverType)
                that.typeTurnoverChart()
            }).catch(function (error) {
                console.log(error)
            })
        },
        /**
         *获取每日、每月、季度营业额
         */
        getTurnoverStat() {
            let that = this
            let requestUrl = `/getTurnoverStatics?userName=${this.userInfo.userName}`
            axios.get(requestUrl).then(function (result) {
                that.turnoverStatic(result.data)
            })
        },
        turnoverStatic(data) {
            var chartDom = document.getElementById('turnoverChart');
            var myChart = echarts.init(chartDom);
            var option;

            option = {
                title: {
                    text: '营业额统计'
                },
                color: ['#0090ff', '#58a366','#ba6b6b'],
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: ['近七日', '每月', '每年'],
                    selected: {
                        '近七日': true,
                        '每月': false,
                        '每年': false
                    }
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
                    //data:[1,2,3,4,5,6,7]
                    data: data.sevenDays,
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
                        name: '近七日',
                        type: 'line',
                        xAxisIndex: '0',
                        //data: [10000,11000,10000,11000,10000,11000,10000,],
                        data: data.weekTurnover,
                        symbol:'roundRect',
                        areaStyle: {
                            color: {
                                type: 'linear',
                                x: 0,
                                y: 0,
                                x2: 0,
                                y2: 1,
                                colorStops: [
                                    {
                                        offset: 0,
                                        color: 'rgba(0,156,255,0.5)',
                                    },
                                    {
                                        offset: 0.8, //这是于下方线的距离,设置1就不留空隙
                                        color: 'rgba(0,156,255,0)',
                                    },
                                ],
                                global: false,
                            },
                        },
                    },
                    {
                        name: '每月',
                        type: 'line',
                        xAxisIndex: '1',
                        //data: [1000,1100,1000,1100,1000,1100,1000,1000,1100,1000,1100,1000],
                        data: data.monthTurnover,
                        symbol:'circle',
                        areaStyle: {
                            color: {
                                type: 'linear',
                                x: 0,
                                y: 0,
                                x2: 0,
                                y2: 1,
                                colorStops: [
                                    {
                                        offset: 0,
                                        color: 'rgb(212,255,210)',
                                    },
                                    {
                                        offset: 0.8, //这是于下方线的距离,设置1就不留空隙
                                        color: 'rgba(76,255,0,0)',
                                    },
                                ],
                                global: false,
                            },
                        },
                    },
                    {
                        name: '每年',
                        type: 'line',
                        xAxisIndex: '2',
                        data: data.seasonTurnover,
                        //data: [1000,1100,1000,1000],
                        symbol:'diamond',
                        areaStyle: {
                            color: {
                                type: 'linear',
                                x: 0,
                                y: 0,
                                x2: 0,
                                y2: 1,
                                colorStops: [
                                    {
                                        offset: 0,
                                        color: 'rgba(252,222,189,0.5)',
                                    },
                                    {
                                        offset: 0.8, //这是于下方线的距离,设置1就不留空隙
                                        color: 'rgba(255,236,213,0)',
                                    },
                                ],
                                global: false,
                            },
                        },
                    }
                ]
            };
            myChart.setOption(option);
        },
        typeTurnoverChart() {
            var chartDom = document.getElementById('type-turnover');
            var myChart = echarts.init(chartDom);
            var option;
            option = {
                dataset: {
                    dimensions: ['turnover', 'goods_type'],
                    source: this.turnoverType
                },
                tooltip: {
                    trigger: 'item'
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