function animate(obj, target, callback) {
	clearInterval(obj.timer);									//清除定时器
	obj.timer = setInterval(function() {
		var step = (target - obj.offsetLeft) / 10;				//缓入步长
		step = step > 0 ? Math.ceil(step) : Math.floor(step);	//判断正负取整数
		if (obj.offsetLeft == target) {							//定时器结束条件
			clearInterval(obj.timer);
			callback && callback();								//执行回调函数
		} else {
			obj.style.left = obj.offsetLeft + step + 'px';		//动画核心
		}
	}, 15);
}

