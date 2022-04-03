import '../plug/vue/vue.min.js';
import '../plug/jquery/jquery-3.6.0.min.js';
Vue.config.devtools = true;

$(function () {
	$(".set").hover(function(){
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
			sysUserName: ''
		}
	},
	created() {
		this.sysUserName = window.localStorage.getItem('tname')
		//window.localStorage.removeItem('tname')
		window.localStorage.removeItem('tpsw')
	},
})