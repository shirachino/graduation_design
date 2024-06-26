import '../plug/echarts/echarts.js'
import '../plug/vue/vue.min.js'
import '../plug/element-ui/element-ui.js'

Vue.config.devtools = true;

var saleRankVm = new Vue({
    el: '#salesRank',
    data: {
        userInfo: {
            userName: ''
        },
        rankGoodsTotal: [],
        rankGoodsByType: [],
        rankGoodsPerType: [{
            goods_type: 'XXX'
        }, {
            goods_type: 'XXX'
        }, {
            goods_type: 'XXX'
        }, {
            goods_type: 'XXX'
        }],
        rankGoodsPerWeek: [],
        rankGoodsPerMonth: [],
        typeOneRank: []
    },
    created() {
        this.userInfo.userName = window.localStorage.getItem('tname')
        this.getSaleGoodsTotal();
        this.getTypeTotalRank();
        this.getAllType().then();
    },
    mounted() {},
    methods: {
        //axios request
        async getAllType() {
            let qs = Qs
            let that = this
            that.userInfo.userName = window.localStorage.getItem('tname')
            let typeList = await axios({
                url: '/getAllTypeLimit4',
                method: 'post',
                data: qs.stringify(that.userInfo)
            })

            for (var i = 0; i < typeList.data.length; i++){
                this.rankGoodsPerType[i].goods_type = typeList.data[i]
            }

            this.getPerTypeRank('type-one', typeList.data[0], '#ffe7dc','#fff8f6')
            this.getPerTypeRank('type-two', typeList.data[1], '#ccf3ff','#f9fcff')
            this.getPerTypeRank('type-three', typeList.data[2], '#c2f0c2','#eaf1ea')
            this.getPerTypeRank('type-four', typeList.data[3], '#ffcce0','#fff7f9')
        },
        getSaleGoodsTotal() {
            let that = this;
            //let postJsonData = JSON.stringify(that.userName)
            let requestUrl = `/rank/getTotalRank?userName=${this.userInfo.userName}`
            axios.get(requestUrl).then(function (result) {
                    //console.log(result.data)
                    that.rankGoodsTotal = result.data
                    //console.log(that.rankGoodsTotal)
                    //初始化chart不能写在mounted里，数据获取不到
                    that.totalRankChart()
                })
                .catch(function (error) { // 请求失败处理
                    console.log(error)
                });
        },
        getTypeTotalRank() {
            let that = this;
            let requestUrl = `/rank/getTypeRank?userName=${this.userInfo.userName}`
            axios.get(requestUrl).then(function (result) {
                    //console.log(result.data)
                    that.rankGoodsByType = result.data
                    //console.log(that.rankGoodsByType)
                    //初始化chart不能写在mounted里，数据获取不到
                    that.typeRankChart()
                })
                .catch(function (error) { // 请求失败处理
                    console.log(error)
                });
        },
        getPerTypeRank(divId, goodsType, color1,color2) {
            let qs = Qs
            let that = this;
            let requestData = {}
            requestData.userName = window.localStorage.getItem('tname')
            requestData.goodsType = goodsType

            axios({
                url: '/rank/getPerTypeRank',
                method: 'post',
                data: qs.stringify(requestData)
            }).then(function (result) {
                that.typeOneRank = result.data
                //console.log(that.typeOneRank)
                that.perTypeCharts(divId, that.typeOneRank,color1,color2)
            })

        },


        //charts function
        totalRankChart() {
            var myChart = echarts.init(document.getElementById('total_sales_rank'));
            var option;
            var rgTotal = this.rankGoodsTotal
            option = {
                tooltip: {
                    trigger: 'axis',

                },
                dataset: [{
                    dimensions: ['goods_name', 'goods_saleNum'],
                    source: rgTotal
                }, {
                    transform: {
                        type: 'sort',
                        config: {dimension: 'goods_saleNum', order: 'desc'}
                    }
                }],
                xAxis: {
                    type: 'category',
                    axisLabel: {interval: 0, rotate: 30},
                },
                yAxis: {},
                grid:{
                    bottom:80
                },
                series: [{
                    type: 'bar',
                    encode: {x: 'goods_name', y: 'goods_saleNum'},
                    datasetIndex: 1,
                    itemStyle: {
                        barBorderRadius:[5,5,5,5]
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
        typeRankChart() {
            var chartDom = document.getElementById('type_sales_rank');
            var myChart = echarts.init(chartDom);
            var option;

            option = {
                legend: {},
                tooltip: {},
                dataset: {
                    source: this.rankGoodsByType
                },
                series: [
                    {
                        name: '类型',
                        type: 'pie',
                        radius: '50%',
                        center: ['50%', '50%'],
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };
            myChart.setOption(option);
        },
        perTypeCharts(divId, dataList, color1, color2) {
            var chartDom = document.getElementById(divId);
            var myChart = echarts.init(chartDom);
            var option;

            option = {
                dataset: [
                    {
                        dimensions: ['goods_name', 'goods_saleNum'],
                        source: dataList
                    },
                    {
                        transform: {
                            type: 'sort',
                            config: {dimension: 'goods_saleNum', order: 'asc'}
                        }
                    }
                ],
                grid: {
                    top: 5,
                    left: 130,
                    bottom: 20
                },
                xAxis: {
                    splitLine:{
                        show:false
                    }
                },
                yAxis: {
                    type: 'category',
                    axisLabel: {interval: 0},
                    axisTick:{
                        inside:'true',
                        lineStyle:{
                            type:'dotted'
                        }
                    },
                    axisLine:{
                        show:false
                    },
                    splitLine:{
                        show:false
                    }

                },
                series: {
                    type: 'bar',
                    encode: {x: 'goods_saleNum', y: 'goods_name'},
                    datasetIndex: 1,
                    itemStyle: {
                        barBorderRadius:[5,5,5,5]
                    }
                },
                color: new echarts.graphic.LinearGradient(0, 0, 1, 0,
                    [
                        {
                            offset: 0,
                            color: color2
                        },
                        {
                            offset: 1,
                            color: color1
                        }
                    ],
                    false
                )

            };

            option && myChart.setOption(option);
        }
    }
})



