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


var sysVm = new Vue({
    el: '#user-box',
    data() {
        return {
            sysUserName: '',
            avatarUrl: ''
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
            window.document.location.href = 'login.html'
        }
    }
})