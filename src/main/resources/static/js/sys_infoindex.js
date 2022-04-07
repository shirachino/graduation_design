import '../plug/vue/vue.min.js';
import '../plug/jquery/jquery-3.6.0.min.js';
import 'https://unpkg.com/element-ui/lib/index.js';
Vue.config.devtools = true;

var sysIndexVm = new Vue({
	el: '#sale_info_app',
	data() {
		return{
			userInfo: {
				userName:''
			},
			customNum:'0',
			saleNum:'0',
			drawer: false,
		}
	},
	created(){
		//this指向jQuery，定义that指向Vue
		var that = this
		that.userInfo.userName = window.localStorage.getItem('tname')

		$.ajax({
			async:false,
			type:'post',
			url:'/infoDisplay',
			data: that.userInfo,
			dataType:"json",
			success:function (res) {
				//返回订单个数
				that.customNum = res.customsNum
				that.saleNum = res.salesNum
			}
		})
	}
})

var goodFastAddVm = new Vue({
	el: '#fast-addFun',
	data(){
		return {
			fastAddGoodsInfo:{
				userName:'',
				goodsId:'',
				goodsName:'',
				goodsType:'',
				goodsNum:'',
				goodsInPrice:'',
				goodsOutPrice:'',
				goodsSHLdate:'',
				goodsEXPdate:''
			}
		}
	},
	methods:{
		isInputRight(){
			var that = this;
			var regExpInt = /^[1-9]\d*$/
			var regExpDou = /^(([^0][0-9]+|0)\.([0-9]{1,2})$)|^(([^0][0-9]+|0)$)|^(([1-9]+)\.([0-9]{1,2})$)|^(([1-9]+)$)/
			if(regExpInt.test(that.fastAddGoodsInfo.goodsId) === false || that.fastAddGoodsInfo.goodsId === ''){
				that.warnMsg('商品编号格式错误或未填写！请检查。')
				return false
			} else if (that.fastAddGoodsInfo.goodsName === '' ){
				that.warnMsg('商品名称未填写！')
				return false
			} else if (that.fastAddGoodsInfo.goodsType === '' ){
				that.warnMsg('商品类型未填写！')
				return false
			} else if (regExpInt.test(that.fastAddGoodsInfo.goodsNum) === false || that.fastAddGoodsInfo.goodsNum === '' ){
				that.warnMsg('商品数量有误！')
				return false
			} else if (regExpDou.test(that.fastAddGoodsInfo.goodsInPrice) === false || that.fastAddGoodsInfo.goodsInPrice === '' ){
				that.warnMsg('商品进价未填写或格式错误！')
				return false
			} else if (regExpDou.test(that.fastAddGoodsInfo.goodsOutPrice) === false || that.fastAddGoodsInfo.goodsOutPrice === '' ){
				that.warnMsg('商品售价未填写或格式错误！')
				return false
			} else if (that.fastAddGoodsInfo.goodsSHLdate === '' ){
				that.warnMsg('上架日期未填写！')
				return false
			} else {
				return true
			}
		},
		fastAddGoodsToStock(){
			var that = this;
			that.fastAddGoodsInfo.userName = window.localStorage.getItem('tname')
			//判断输入是否正确
			if(that.isInputRight() == false ){
				return false
			}
			$.ajax({
				async:false,
				type:'post',
				url:'/stockInsert',
				data:that.fastAddGoodsInfo,
				dataType:"json",
				success:function (res) {
					if (res == "200"){
						that.successMsg()
						$('.close').eq(0).trigger("click")
					}else{
						that.errorMsg()
					}
				}
			})
		},
		successMsg() {
			this.$message({
				message: '入库成功！（200）',
				type: 'success'
			});
		},
		errorMsg() {
			this.$message.error('入库失败，请重试！（500）');
		},
		warnMsg(msg){
			this.$message({
				showClose: true,
				message: msg,
				type: 'warning'
			});
		}
	}
})

$(function() {
	// 模态框淡入淡出
	$('.mod_one').on('click', function() {
		$('.mask').show();
		$('.mask').css('opacity', 1);
		$('.fastadd').show();
		$('.fastadd').css('opacity', 1);
	});
	$('.mod_two').on('click', function() {
		$('.mask').show();
		$('.mask').css('opacity', 1);
		$('.fastsearch').show();
		$('.fastsearch').css('opacity', 1);
	});
	$('.mod_thr').on('click', function() {
		$('.mask').show();
		$('.mask').css('opacity', 1);
		$('.fastsold').show();
		$('.fastsold').css('opacity', 1);
	});
	$('#newmem').on('click', function() {
		$('.mask').show();
		$('.mask').css('opacity', 1);
		$('.mem').show();
		$('.mem').css('opacity', 1);
	});
	// 调用关闭
	$('.close').eq(0).on('click', () => {
		cls($('.fastadd').get(0));
	});
	$('.mydefbtn_default').eq(0).on('click', () => {
		cls($('.fastadd').get(0));
	});
	$('.close').eq(1).on('click', () => {
		cls($('.fastsearch').get(0));
	});
	$('.mydefbtn_default').eq(1).on('click', () => {
		cls($('.fastsearch').get(0));
	});
	$('.close').eq(2).on('click', () => {
		cls($('.fastsold').get(0));
	});
	$('.mydefbtn_default').eq(2).on('click', () => {
		cls($('.fastsold').get(0));
	});
	$('.close').eq(3).on('click', () => {
		cls($('.mem').get(0));
	});
	// 关闭函数
	function cls(modal) {
		$('.mask').css('animation', 'fadeout .3s');
		modal.style.animation = 'fadeout .3s';
		setTimeout(function() {
			$('.mask').hide();
			modal.style.display = 'none';
			$('.mask').css('animation', 'fadein .7s');
			modal.style.animation = 'fadein .5s';
		}, 300)
		$('.mask').css('opacity', 0);
		modal.style.opacity = 0;
	}
	// 模态框淡入淡出end

	// 备忘录
	// 获取本地缓存数据
	function getLocalData() {
		var data = localStorage.getItem("memdata")
		if (data !== null) {
			return JSON.parse(data); //字符串转换为数组
		} else {
			return [];
		}
	}
	// 将数据转换成字符串保存到本地存储
	function saveLocalData(data) {
		localStorage.setItem("memdata", JSON.stringify(data)); //数组对象转换成字符串
	}
	// 渲染页面
	function loadLocalData() {
		var data = getLocalData();
		$(".memarea").empty();
		$.each(data, function(i, n) {
			// if(n.time !== ""){
			// 	n.time = n.time + " 之前记得完成：";
			// }
			n.time = n.time !== "" ? n.time + " 之前记得完成：" : n.time;
			$(".memarea").append('<div class="membox"><span data-index=' + i + '>&times;</span><i>' + n.time + '</i>' + n.inner +
				'</div>');
		})
	}
	// 调用渲染页面
	loadLocalData();
	// 对备忘录区域的关闭按钮添加删除事件
	$(".memarea").on("click", "span", function() {
		var data = getLocalData();
		var index = $(this).attr("data-index");
		data.splice(index, 1);
		saveLocalData(data);
		loadLocalData();
	})
	// 将输入的内容保存至本地存储
	$("#memconfirm").on("click", function() {
		if ($(".mem textarea").val() === "") {
			alert('您还没有输入内容！');
		} else {
			var local = getLocalData();
			local.push({
				inner: $(".mem textarea").val(),
				time: $(".mem input").val()
			});
			$(".mem textarea").val("");
			$(".mem input").val("");
			saveLocalData(local);
			loadLocalData();
		}
	});
	// 确认按钮调用关闭
	$('.mydefbtn_primary').eq(3).on('click', () => {
		cls($('.mem').get(0));
	});
	// 备忘录end
});

