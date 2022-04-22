import '../plug/vue/vue.min.js';
import '../plug/jquery/jquery-3.6.0.min.js';

Vue.config.devtools = true;

$(function () {
    var ad = document.querySelector('.ad_box');
    var ul = ad.querySelector('ul');
    var ol = document.querySelector('.circle');
    var imgWidth = ad.offsetWidth;
    var control = document.querySelector('.control');
    var num = 0;
    var cirNum = 0;
    // 点击圆圈
    for (var i = 0; i < ul.children.length; i++) {
        var li = document.createElement('li');
        ol.appendChild(li);
        $(li).on('click', function () {
            $(this).addClass('current').siblings(li).removeClass();
            num = cirNum = $(this).index();
            animate(ul, -$(this).index() * imgWidth);
        })
    }
    ol.children[0].className = 'current';
    var firstImg = ul.children[0].cloneNode(true);
    ul.appendChild(firstImg);

    control.addEventListener('click', function () {
        if (num == ul.children.length - 1) {
            ul.style.left = 0;
            num = 0;
        }
        // $('.ad_box>ul li img').eq(num).fadeOut("slow");
        num++;
        animate(ul, -num * imgWidth);
        cirNum++;
        if (cirNum == ol.children.length) {
            cirNum = 0;
        }
        for (var i = 0; i < ol.children.length; i++) {
            ol.children[i].className = '';
        }
        ol.children[cirNum].className = 'current';
    })

    var timer = setInterval(function () {
        control.click();
    }, 4000)

    $('.ad_box').on('mouseover', function () {
        clearInterval(timer);
        timer = null;
    })
    $(ol).on('mouseover', function () {
        clearInterval(timer);
        timer = null;
    })
    $('.ad_box').on('mouseout', function () {
        timer = setInterval(function () {
            control.click();
        }, 4000);
    })
    $(ol).on('mouseout', function () {
        timer = setInterval(function () {
            control.click();
        }, 4000);
    })
})
var logVm = new Vue({
    el: '#app',
    data() {
        return {
            userIpt: {
                userName: '',
                userPsw: '',
            }
        }
    },
    created() {
    },
    methods: {
        login() {
            //this指向jQuery，定义that指向Vue
            var that = this
            //移除上一个用户
            window.localStorage.removeItem("tname")
            $.ajax({
                async: false,
                type: 'post',
                url: '/userLogin',
                data: that.userIpt,
                dataType: "json",
                success: function (res) {
                    //this不能用因为这里的$是jq的标识
                    //判断密码是否与输入密码相同
                    if (res && res == "200") {
                        //用户名存入缓存中用于下个页面读取
                        window.localStorage.setItem("tname", that.userIpt.userName)
                        //跳转至系统
                        window.document.location.href = 'storeSystem.html'
                    } else if (res == "590") {
                        alert('用户不存在！')
                        return false
                    } else {
                        console.log(res)
                        alert('用户名或密码错误！')
                        return false
                    }
                }
            })
        }
    },
})