import '../plug/vue/vue.min.js';
import '../plug/jquery/jquery-3.6.0.min.js';
Vue.config.devtools = true;

var regvm = new Vue({
    el: '#reg',
    data() {
        return {
            regInfo: {
                regName: '',
                regTelNum: '',
                regPsw: '',
                regSecPsw: '',
                regAgree: false
            },
            isNameCorrect:false,
            isTelNumCorrect:false,
            isPswCorrect:false,
            isSecPswCorrect:false,
        }
    },
    created() {

    },
    watch: {
        'regInfo.regName'(newVal) {
            var regExp = /^[a-zA-Z0-9_]{6,16}$/;
            // console.log(regExp.test(newVal));
            if (regExp.test(newVal)) {
                $('#reg-name-t').show()
                $('#reg-name-f').hide()
                this.isNameCorrect = true
            } else {
                $('#reg-name-t').hide()
                $('#reg-name-f').show()
                this.isNameCorrect = false
            }
        },
        'regInfo.regTelNum'(newVal) {
            var regExp = /^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/
            if (regExp.test(newVal)) {
                $('#reg-telnum-f').hide()
                this.isTelNumCorrect = true
            } else {
                $('#reg-telnum-f').show()
                this.isTelNumCorrect = false
            }
        },
        'regInfo.regPsw'(newVal) {
            var regExpEasy = /^[0-9]{6,16}$/
            var regExpMid = /^[a-zA-Z0-9]{6,16}$/
            var regExpStrong = /^[a-zA-Z0-9-*/+~`!@#$%^&*()]{6,16}$/
            if (regExpEasy.test(newVal)) {
                $('#reg-psw-f').hide()
                $('.ruo').removeClass('default').siblings('em').addClass('default')
                this.isPswCorrect = true
            } else if (regExpMid.test(newVal)) {
                $('#reg-psw-f').hide()
                $('.zhong').removeClass('default').siblings('em').addClass('default')
                this.isPswCorrect = true
            } else if (regExpStrong.test(newVal)) {
                $('#reg-psw-f').hide()
                $('.qiang').removeClass('default').siblings('em').addClass('default')
                this.isPswCorrect = true
            } else {
                $('.safe').find('em').addClass('default')
                $('#reg-psw-f').show()
                this.isPswCorrect = false
            }
        },
        'regInfo.regSecPsw'(newVal) {
            if (this.regInfo.regPsw != newVal) {
                $('#reg-sec-psw-f').show()
                this.isSecPswCorrect = false
            } else {
                $('#reg-sec-psw-f').hide()
                this.isSecPswCorrect = true
            }
        }
    },
    methods: {
        //1.判断用户是否同意协议，信息是否填写完整,正确
        //2.将用户名发到后端查询，是否被使用，若无重名继续执行，有重名则让reg-name-rf显示，并中断
        submitRegForm(){
            if (this.regInfo.regName == "") {
                alert('用户名不能为空!')
                return false
            }else if (this.regInfo.regTelNum == "") {
                alert('手机号不能为空!')
                return false
            }else if (this.regInfo.regPsw == "") {
                alert('密码不能为空!')
                return false
            }else if (!this.regInfo.regAgree) {
                alert('请阅读并同意用户协议!')
                return false
            }else if (!(this.isNameCorrect &&
                this.isTelNumCorrect &&
                this.isPswCorrect &&
                this.isSecPswCorrect)){
                alert('信息有误，请检查！')
                return false
            }
            $.ajax({

                type:'post',
                url:'/userRegister',
                data:regvm.regInfo,
                dataType:"json",
                success:function (res) {
                    if (res == false){
                        $('#reg-name-rf').show()
                        $('#reg-name-t').hide()
                    }else{
                        window.document.location.href = 'login.html'
                    }
                }
            })
        }
        //3.将用户信息赋值给后端数据库，完成注册
        //4.进入注册成功等待页面，跳转至登录页面
    }
})