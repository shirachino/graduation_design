import '../plug/vue/vue.min.js';
import '../plug/jquery/jquery-3.6.0.min.js';

Vue.config.devtools = true;

$(function () {
    $(".set").hover(function () {
        $(".user_set").stop().slideToggle(200);
    })
    // 导航栏缓动
    $("summary").on("click", function () {
        $(this).parent().next().stop().slideToggle(300);
        $(this).toggleClass("change");
    })
    $(".nav_tog").on("click", function () {
        $(".nav").toggleClass("nav_hide");
        $(this).toggleClass("nav_hide");
    })
})

var sysNavVm = new Vue({
    el: '#sys-nav',
    data(){
        return{
            isVip:''
        }
    }
})

var sysHeadVm = new Vue({
    el: '#user-box',
    data() {
        return {
            sysUserName: '',
            avatarUrl: '',
            isVIP:''
        }
    },
    created() {
        this.sysUserName = window.localStorage.getItem('tname')
        //window.localStorage.removeItem('tname')
        window.localStorage.removeItem('tpsw')
        this.getUserInfo()
    },
    methods: {
        getUserInfo() {
            let that = this
            let url = `/getUserInfo?userName=${this.sysUserName}`
            axios.get(url).then(function (result) {
                window.localStorage.setItem('token',result.data.token)
                that.isVIP = result.data.isvip
                sysNavVm.isVip = that.isVIP
                if (that.isVIP != '1'){
                    that.$refs.user_vip.setAttribute("class", " not-vip")
                    sysNavVm.$refs.vip_mod_1.setAttribute("class", " not-click")
                    sysNavVm.$refs.vip_det_1.setAttribute("class", " vip-can-use")
                    sysNavVm.$refs.vip_mod_2.setAttribute("class", " not-click")
                    sysNavVm.$refs.vip_det_2.setAttribute("class", " vip-can-use")
                }
                if (result.data.avatar_url == null || result.data.avatar_url == '') {
                    that.avatarUrl = 'https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png'
                } else {
                    that.avatarUrl = result.data.avatar_url
                }
            })
        },
        logout() {
            window.localStorage.removeItem('tname')
            window.localStorage.removeItem('token')
            window.localStorage.removeItem('tpsw')
            top.document.location.href = 'login.html'
        }
    }
})

