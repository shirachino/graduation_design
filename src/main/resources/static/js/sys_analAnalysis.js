import '../plug/echarts/echarts.js'
import '../plug/vue/vue.min.js'
import '../plug/element-ui/element-ui.js'

Vue.config.devtools = true

var analVm = new Vue({
    el: '#analysisApp',
    data(){
        return{
            userName:'',
            show1: false,
            show2: false,
            show3: false,
            show4: false,
            show5: false,
            show6: false,
            turnover:{
                top:{},
                last:{}
            },
            netProfit:{
                top:{},
                last:{}
            },
            saleNum:{
                top:{},
                last:{}
            },
            saleTopList:[],
            saleLastList:[],
            turnoverList:[]
        }
    },
    created(){
        this.userName = window.localStorage.getItem('tname')
        this.getAnalysis()
        this.getAnalSug()
    },
    mounted(){
        let that = this
        setTimeout(function () {
            that.show1 = true
        },1)
        setTimeout(function () {
            that.show2 = true
        },500)
        setTimeout(function () {
            that.show3 = true
        },1000)
        setTimeout(function () {
            that.show4 = true
        },1500)
        setTimeout(function () {
            that.show5 = true
        },2000)
        setTimeout(function () {
            that.show6 = true
        },2500)

    },
    methods:{
        getAnalysis(){
            let that = this
            let requestUrl = `/getAnalysis?userName=${this.userName}`
            axios.get(requestUrl).then(function (result) {
                that.turnover.top = result.data.turnoverTop
                that.netProfit.top = result.data.netProfitTop
                that.saleNum.top = result.data.saleNumTop
                that.turnover.last  = result.data.turnoverLast
                that.netProfit.last = result.data.netProfitLast
                that.saleNum.last = result.data.saleNumLast
                that.analChart(result.data)
            })
        },
        getAnalSug(){
            let that = this
            let requestUrl = `/getSuggest?userName=${this.userName}`
            axios.get(requestUrl).then(function (result) {
                that.saleTopList = result.data.saleTop
                that.saleLastList = result.data.saleLast
                that.turnoverList = result.data.turnoverLast
            })
        },
        analChart(realData){
            var chartDom = document.getElementById('anal-chart');
            var myChart = echarts.init(chartDom);
            var option;


            const thisMonth = [
                realData.thisMonthData
            ];
            const lastMonth = [
                realData.lastMonthData
            ];
            const lineStyle = {
                width: 1,
                opacity: 0.5
            };
            option = {
                title: {
                    text: '本月上月经营对比',
                    left: 'center',
                    textStyle: {
                        color: '#eee'
                    }
                },
                legend: {
                    bottom: 5,
                    data: ['本月', '上月'],
                    itemGap: 20,
                    textStyle: {
                        color: '#2f2f2f',
                        fontSize: 14
                    }
                },
                tooltip:{
                    show: true
                },
                radar: {
                    indicator: [
                        { name: '营业额', max: Math.max( realData.thisMonthData[0],realData.lastMonthData[0]) },
                        { name: '客流量', max: Math.max( realData.thisMonthData[1],realData.lastMonthData[1]) },
                        { name: '净利润', max: Math.max( realData.thisMonthData[2],realData.lastMonthData[2]) }
                    ],
                    shape: 'circle',
                    splitNumber: 5,
                    axisName: {
                        color: 'rgb(102,136,238)'
                    },
                    splitLine: {
                        lineStyle: {
                            color: [
                                'rgba(102,168,238, 0.1)',
                                'rgba(102,168,238, 0.2)',
                                'rgba(102,168,238, 0.4)',
                                'rgba(102,168,238, 0.6)',
                                'rgba(102,168,238, 0.8)',
                                'rgb(102,168,238)'
                            ].reverse()
                        }
                    },
                    splitArea: {
                        show: false
                    },
                    axisLine: {
                        lineStyle: {
                            color: 'rgba(238, 197, 102, 0.5)'
                        }
                    }
                },
                series: [
                    {
                        name: '本月',
                        type: 'radar',
                        lineStyle: lineStyle,
                        data: thisMonth,
                        symbol: 'none',
                        itemStyle: {
                            color: '#4d86f8'
                        },
                        z: 999,
                        areaStyle: {
                            opacity: 0.2
                        }
                    },
                    {
                        name: '上月',
                        type: 'radar',
                        lineStyle: lineStyle,
                        data: lastMonth,
                        symbol: 'none',
                        z: 1,
                        itemStyle: {
                            color: '#5ab6b1'
                        },
                        areaStyle: {
                            opacity: 0.1
                        }
                    }

                ]
            };
            option && myChart.setOption(option);
        }
    }
})