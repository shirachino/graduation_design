import '../plug/echarts/echarts.js'
import '../plug/vue/vue.min.js'
import '../plug/element-ui/element-ui.js'

var customerVm = new Vue({
    el: '#customerFlow',
    data() {
        return {}
    },
    mounted() {
        this.customerCharts()
        this.customerDateChart()
    },
    methods: {
        customerCharts() {
            var chartDom = document.querySelector(".cus-chart");
            var myChart = echarts.init(chartDom);
            var option;

            option = {
                title: {
                    text: '顾客流量统计'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: ['日流量', '月流量', '年流量'],
                    selected: {
                        '日流量': true,
                        '月流量': true,
                        '年流量': false
                    }
                },
                axisPointer: {
                    lineStyle: {
                        width: 0
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
                }
                ],
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        name: '日流量',
                        type: 'line',
                        xAxisIndex: '0',
                        data: [33, 38, 61, 88, 110, 40, 21]
                    },
                    {
                        name: '月流量',
                        type: 'line',
                        xAxisIndex: '1',
                        data: [220, 182, 191, 234, 290, 330, 523, 123, 213, 452, 234, 123],
                    },
                    {
                        name: '年流量',
                        type: 'line',
                        xAxisIndex: '2',
                        data: [1241, 4343, 2132, 3232]
                    }
                ]
            };
            option && myChart.setOption(option);
        },
        customerDateChart() {
            var chartDom = document.querySelector('.cus-date-chart');
            var myChart = echarts.init(chartDom);
            var option;

            function getVirtulData(year) {
                let date = +echarts.number.parseDate(year + '-01-01');
                let end = +echarts.number.parseDate(+year + 1 + '-01-01');
                let dayTime = 3600 * 24 * 1000;
                let data = [];
                for (let time = date; time < end; time += dayTime) {
                    data.push([
                        echarts.format.formatTime('yyyy-MM-dd', time),
                        Math.floor(Math.random() * 10000)
                    ]);
                }
                console.log(data[data.length - 1]);
                return data;
            }

            option = {
                title:{
                    show:true,
                    text: "当月客流量"
                },
                tooltip: {
                    position: 'top'
                },
                visualMap: [
                    {
                        min: 0,
                        max: 10000,
                        calculable: true,
                        seriesIndex: [0],
                        orient: 'horizontal',
                        right: '25%',
                        bottom: 10,
                        inRange: {
                            color: ['#9cf7ff', '#2eb6ff', '#0086ff'],
                            symbolSize: [30, 100]
                        }
                    }
                ],
                calendar: [
                    {
                        orient: 'vertical',
                        yearLabel: {
                            margin: 20
                        },
                        dayLabel: {
                            firstDay: 1,
                            nameMap: ['周天', '周一', '周二', '周三', '周四', '周五', '周六'],
                            position:'bottom'
                        },
                        monthLabel: {
                            nameMap: 'cn',
                            margin: 20
                        },
                        cellSize: 40,
                        top: 100,
                        left: 100,
                        range: '2022-3',
                        splitLine:{
                            lineStyle:{
                                color:'#93fcdd'
                            }
                        },
                        itemStyle:{
                            borderWidth: 1,
                            borderColor: "#f5f5f5",
                            borderCap:'round'
                        }
                    }
                ],
                series: [
                    {
                        type: 'heatmap',
                        coordinateSystem: 'calendar',
                        calendarIndex: 0,
                        data: getVirtulData('2022'),
                    }
                ]
            };
            option && myChart.setOption(option);
        }
    }
})
