
$(function() {
	// 顶部下拉菜单
	$("#top_nav>li:last").hover(function() {
		$(".menu").stop().slideToggle(200);
	})
})

