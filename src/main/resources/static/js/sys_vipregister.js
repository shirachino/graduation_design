import '../plug/echarts/echarts.js'
import '../plug/vue/vue.min.js'
import '../plug/element-ui/element-ui.js'

Vue.config.devtools = true;

var vipRegVm = new Vue({
    el: '#vipForm',
    data() {
        return {
            userInfo: {
                userName: ''
            }
        }
    },
})