import '../plug/echarts/echarts.js'
import '../plug/vue/vue.min.js'
import '../plug/element-ui/element-ui.js'

var saleRankVm = new Vue({
    el: '#salesRank',
    data: {
        userInfo:{
            userName:''
        },
        rankGoodsTotal: [],
        rankGoodsByType:[],
        rankGoodsPerWeek:[],
        rankGoodsPerMonth:[]
    },
    created() {
        this.getSaleGoodsTotal();
        this.getTypeTotalRank();
        // this.getSaleGoodsPerMonth();
    },
    mounted(){

    },
    methods: {
        getSaleGoodsTotal(){
            let qs = Qs
            let that = this;
            that.userInfo.userName = window.localStorage.getItem('tname')
            //let postJsonData = JSON.stringify(that.userName)
            axios({
                url: '/rank/getTotalRank',
                method: 'post',
                data: qs.stringify(that.userInfo)
            })
                .then(function (result) {
                    //console.log(result.data)
                    that.rankGoodsTotal = result.data
                    console.log(that.rankGoodsTotal)
                    //初始化chart不能写在mounted里，数据获取不到
                    that.totalRankChart()
                })
                .catch(function (error) { // 请求失败处理
                    console.log(error)
                });
        },
        getTypeTotalRank(){
            let qs = Qs
            let that = this;
            that.userInfo.userName = window.localStorage.getItem('tname')

            axios({
                url: '/rank/getTypeRank',
                method: 'post',
                data: qs.stringify(that.userInfo)
            })
                .then(function (result) {
                    //console.log(result.data)
                    that.rankGoodsByType = result.data
                    console.log(that.rankGoodsByType)
                    //初始化chart不能写在mounted里，数据获取不到
                    that.typeRankChart()
                })
                .catch(function (error) { // 请求失败处理
                    console.log(error)
                });
        },
        totalRankChart() {
            var myChart = echarts.init(document.getElementById('total_sales_rank'));
            var option;
            var rgTotal = this.rankGoodsTotal
            option = {
                tooltip : {
                    trigger: 'axis',

                },
                dataset: [{
                    dimensions: ['goods_name', 'goods_saleNum'],
                    source: rgTotal
                }, {
                    transform: {
                        type: 'sort',
                        config: { dimension: 'goods_saleNum', order: 'desc' }
                    }
                }],
                xAxis: {
                    type: 'category',
                    axisLabel: { interval: 0, rotate: 30 },
                },
                yAxis: {},
                series: [{
                    type: 'bar',
                    encode: { x: 'goods_name', y: 'goods_saleNum' },
                    datasetIndex: 1
                }],
                color:['#99cfff']
            };
            myChart.setOption(option);
        },
        typeRankChart(){
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
        }
    }
})



