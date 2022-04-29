import '../plug/echarts/echarts.js'
import '../plug/vue/vue.min.js'
import '../plug/element-ui/element-ui.js'

var customerVm = new Vue({
    el: '#customerFlow',
    data() {
        return {
            userInfo: {
                userName: ''
            },
            customList: [],
            tip:[
                '使用会员功能可以让顾客充值余额、积累积分，提高顾客黏性~',
                '合理使用会员功能，适当推出充值活动、积分换礼吸引顾客~',
                '试着查看店铺的经营状况，对商品结构进行调整~'
            ],
            thisMonthCustom: {}
        }
    },
    created(){
        this.userInfo.userName = window.localStorage.getItem('tname')
        this.getCustomPerSeason()
        this.getCustomOneMonth()
    },
    mounted() {
        //this.customerDateChart()
    },
    watch: {
        customList: {
            deep: true,
            handler: function () {
                this.customerCharts(this.customList)
            }
        }
    },
    methods: {
        getCustomPerSeason() {
            let that = this
            let requestUrl = `/getCustomList?userName=${this.userInfo.userName}`
            axios.get(requestUrl).then(function (result) {
                that.customList = result.data
                //console.log(that.customList)
                that.customerCharts(that.customList)
            })
        },
        getCustomOneMonth() {
            let that = this
            let requestUrl = `/getCustomOneMonth?userName=${this.userInfo.userName}`
            axios.get(requestUrl).then(function (result) {
                that.thisMonthCustom = result.data
                console.log(that.thisMonthCustom )
                that.customerDateChart(that.thisMonthCustom)
            })
        },
        customerCharts(customList) {
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
                    data: customList.sevenDays
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
                        data: customList.weekCustom
                    },
                    {
                        name: '月流量',
                        type: 'line',
                        xAxisIndex: '1',
                        data: customList.monthCustom
                    },
                    {
                        name: '年流量',
                        type: 'line',
                        xAxisIndex: '2',
                        data: customList.seasonCustom
                    }
                ]
            };
            option && myChart.setOption(option);
        },
        customerDateChart(realDataObj) {
            var chartDom = document.querySelector('.cus-date-chart');
            var myChart = echarts.init(chartDom);
            var option;

            function getRealData(nowYearMonth,nextYearMonth) {
                let date = +echarts.number.parseDate(nowYearMonth + '-01');
                let end = +echarts.number.parseDate(nextYearMonth + '-01');
                let dayTime = 3600 * 24 * 1000;
                let dateArr = [];
                let rData = [];
                for (let time = date; time < end; time += dayTime) {
                    dateArr.push(
                        echarts.format.formatTime('yyyy-MM-dd', time)
                    );
                }
                for (var i = 0;i<realDataObj.realData.length;i++){
                    rData.push([
                        dateArr[i],
                        realDataObj.realData[i].toString()
                    ])
                }
                console.log(rData[rData.length - 1]);
                return rData;
            }

            option = {
                title: {
                    show: true,
                    text: "当月客流量"
                },
                tooltip: {
                    position: 'top'
                },
                visualMap: [
                    {
                        min: 0,
                        max: Math.max(...realDataObj.realData),
                        calculable: true,
                        seriesIndex: [0],
                        orient: 'horizontal',
                        right: '25%',
                        bottom: 10,
                        inRange: {
                            color: ['#f0fcff', '#30b8fc', '#0086ff'],
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
                            position: 'bottom'
                        },
                        monthLabel: {
                            nameMap: 'cn',
                            margin: 20
                        },
                        cellSize: 40,
                        top: 100,
                        left: 100,
                        range: realDataObj.nowYearMonth,
                        splitLine: {
                            lineStyle: {
                                color: '#cdcfd7'
                            }
                        },
                        itemStyle: {
                            borderWidth: 1,
                            borderColor: "#c2c2c2",
                            borderCap: 'round'
                        }
                    }
                ],
                series: [
                    {
                        type: 'heatmap',
                        coordinateSystem: 'calendar',
                        calendarIndex: 0,
                        data: getRealData(realDataObj.nowYearMonth,realDataObj.nextYearMonth),
                    }
                ]
            };
            option && myChart.setOption(option);
        }
    }
})
