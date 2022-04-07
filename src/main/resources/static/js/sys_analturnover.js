import '../plug/echarts/echarts.js'
import '../plug/vue/vue.min.js'
import 'https://unpkg.com/element-ui/lib/index.js'

Vue.config.devtools = true

var turnoverVm = new Vue({
    el: '#turnoverApp',
    data() {
        return {}
    },
    created() {

    },
    mounted() {
        this.turnoverStatic()
    },
    methods: {
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
        }
    }
})