import '../plug/vue/vue.min.js'
import '../plug/echarts/echarts.js'
import '../plug/element-ui/element-ui.js'

Vue.config.devtools = true

var netProfitsVm = new Vue({
    el: '#netProfitsApp',
    data() {
        return {
            userInfo:{
                userName:''
            },
            netProfitsRank:[]
        }
    },
    created(){
        this.userInfo.userName = window.localStorage.getItem('tname')
        this.getNetProfitsRank()
        this.getPCR()
    },
    mounted() {

    },
    methods: {
        getNetProfitsRank(){
            let that = this
            let requestUrl = `/getNetProfitsRank?userName=${this.userInfo.userName}`
            axios.get(requestUrl).then(function (result) {
                that.netProfitsRank = result.data
                that.netProfitsChart(that.netProfitsRank)
            })
        },
        getPCR(){
            let that = this
            let requestUrl = `/getPCR?userName=${this.userInfo.userName}`
            axios.get(requestUrl).then(function (result) {
                that.PCRChart(result.data)
            })
        },
        netProfitsChart(data) {
            var myChart = echarts.init(document.getElementById('netProfits-chart'));
            var option;
            option = {
                tooltip: {
                    trigger: 'axis',
                },
                dataset: [{
                    dimensions: ['goods_name', 'goods_netProfits'],
                    source: data
                }, {
                    transform: {
                        type: 'sort',
                        config: {dimension: 'goods_netProfits', order: 'desc'}
                    }
                }],
                xAxis: {
                    type: 'category',
                    axisLabel: {interval: 0, rotate: 30},
                },
                yAxis: {},
                grid: {
                    bottom: 80
                },
                series: [{
                    type: 'bar',
                    encode: {x: 'goods_name', y: 'goods_netProfits'},
                    datasetIndex: 1,
                    itemStyle: {
                        barBorderRadius: [10, 10, 5, 5]
                    }
                }],
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1,
                    [
                        {
                            offset: 0,
                            color: '#90e0ff'
                        },
                        {
                            offset: 1,
                            color: '#f7fffe'
                        }
                    ],
                    false
                )
            };
            myChart.setOption(option);
        },
        PCRChart(Pcr) {
            var chartDom = document.getElementById('CPC-chart');
            var myChart = echarts.init(chartDom);
            var option;

            let colorArr = [new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                {offset: 0, color: '#fe2929'}, //柱图渐变色
                {offset: 1, color: '#ff7c7c'} //柱图渐变色
            ]), new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                {offset: 0, color: '#0ab634'}, //柱图渐变色
                {offset: 1, color: '#74d289'} //柱图渐变色
            ]), new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                {offset: 0, color: '#297FFE'}, //柱图渐变色
                {offset: 1, color: '#08E5FF'} //柱图渐变色
            ])]
            let shadowColor = ['#f66161', '#63d41f', '#5a95f1']
            let thisColor
            let thisShadow
            if (Pcr < 10) {
                thisColor = colorArr[0]
                thisShadow = shadowColor[0]
            } else if (Pcr < 25 && Pcr >= 10) {
                thisColor = colorArr[1]
                thisShadow = shadowColor[1]
            } else {
                thisColor = colorArr[2]
                thisShadow = shadowColor[2]
            }
            option = {
                tooltip: {
                    formatter: '{a} <br/>{b} : {c}%'
                },
                series: [
                    {
                        name: '利润转化率',
                        type: 'gauge',
                        min: 0,
                        max: 30,
                        progress: {
                            show: true
                        },
                        detail: {
                            valueAnimation: true,
                            formatter: '{value}'
                        },
                        data: [
                            {
                                value: Pcr,
                                name: '利润转化率'
                            }
                        ],
                        roundCap: true,
                        radius: '90%',
                        itemStyle: {
                            color: thisColor,
                            shadowColor: thisShadow,
                            shadowBlur: 10,
                            shadowOffsetX: 2,
                            shadowOffsetY: 2
                        },
                        axisLine: {
                            roundCap: true,
                            lineStyle: {
                                width: 12
                            }
                        },
                        axisTick: {
                            show: false
                        }
                    }
                ]
            };


            option && myChart.setOption(option);
        }
    }
})