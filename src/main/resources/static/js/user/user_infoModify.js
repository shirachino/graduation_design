import '../../plug/vue/vue.min.js'
import '../../plug/element-ui/element-ui.js'

Vue.config.devtools = true;

var userInfoVm = new Vue({
    el: '#userInfoApp',
    data() {
        return {
            userInfo: {
                userName: 'elaina',
                userPhone: '15555555555',
                userId: '10001',
                userIsVip: 1,
                userRegDate: '2022-01-01',
                userIp: ''
            },
            requestUrl: '',
            imageUrl: '',
            squareUrl: 'https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',
            activeName: 'first',
            modifyInfo: {
                userPhone: ''
            },
            modifyPsw: {
                oldPsw: '',
                newPsw: ''
            },
            regVip: ''
        }
    },
    created() {
        this.userInfo.userName = window.localStorage.getItem('tname')
        this.requestUrl = `http://localhost:8080/uploadAvatar?userName=${this.userInfo.userName}`
        this.getUserInfo()
    },
    methods: {
        getUserInfo() {
            let that = this
            let url = `/getUserInfo?userName=${this.userInfo.userName}`
            axios.get(url).then(function (result) {
                console.log(result.data)
                that.userInfo.userPhone = result.data.phonenum
                that.userInfo.userId = result.data.id
                that.userInfo.userIsVip = result.data.isvip
                that.userInfo.userRegDate = result.data.regtime
                that.userInfo.userIp = result.data.last_login_ip
                that.imageUrl = result.data.avatar_url
            })
        },
        handleAvatarSuccess(res, file) {
            this.imageUrl = URL.createObjectURL(file.raw);
            let {status, url} = res
            if (status == "201") {
                this.$message.success('更换头像成功！');
                setTimeout(function () {
                    top.document.location.href = '../storeSystem.html'
                }, 2000)
            }
        },
        beforeAvatarUpload(file) {
            const isJPG = file.type === 'image/jpeg';
            const isLt2M = file.size / 1024 / 1024 < 2;

            if (!isJPG) {
                this.$message.error('上传头像图片只能是 JPG 格式!');
            }
            if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 2MB!');
            }
            return isJPG && isLt2M;
        },
        handleClick(tab, event) {
            //console.log(tab, event);
        },
        modifyInfoFun() {
            let regExpPhone = /^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/
            if (regExpPhone.test(this.modifyInfo.userPhone) === false || this.modifyInfo.userPhone === '') {
                this.$message.error('请输入正确的信息')
                return false
            }
            let that = this;
            axios.get(`/modifyUserInfo?userName=${that.userInfo.userName}&phoneNum=${that.modifyInfo.userPhone}`)
                .then(function (results) {
                    if (results.data == '200') {
                        that.$message.success('修改信息成功！')
                        that.getUserInfo()
                    } else if (results.data == '590') {
                        that.$message.error('修改信息失败，手机号已被使用')
                    }
                })
                .catch(function (error) {
                    that.$message.error('系统错误：' + error.data)
                })
        },
        modifyPswFun() {
            var regExpStrong = /^[a-zA-Z0-9-*/+~`!@#$%^&*()]{6,16}$/
            if (regExpStrong.test(this.modifyPsw.newPsw) === false || this.modifyPsw.newPsw === '') {
                this.$message.error('请输入6-16位新密码')
                return false
            }
            let that = this;
            axios.get(`/modifyUserPsw?userName=${that.userInfo.userName}&oldPsw=${that.modifyPsw.oldPsw}&newPsw=${that.modifyPsw.newPsw}`)
                .then(function (results) {
                    if (results.data == '200') {
                        that.$message.success('修改密码成功！3秒后跳转至登录页面')
                        window.localStorage.removeItem('tname')
                        window.localStorage.removeItem('token')
                        window.localStorage.removeItem('tpsw')
                        setTimeout(function () {
                            top.document.location.href = '../login.html'
                        }, 3000)
                    } else if (results.data == '590') {
                        that.$message.error('原密码不正确！')
                    }
                })
                .catch(function (error) {
                    that.$message.error('系统错误：' + error.data)
                })
        },
        regVipFun() {
            let that = this;
            axios.get(`/becomeVip?userName=${that.userInfo.userName}`)
                .then(function (results) {
                    if (results.data == '200') {
                        that.$message.success('注册VIP成功！')
                        setTimeout(function () {
                            top.document.location.href = '../storeSystem.html'
                        }, 2000)
                    }
                })
        },
        deleteAccount() {
            let that = this;
            axios.get(`/deleteAccount?userName=${that.userInfo.userName}`)
                .then(function (result) {
                    if (result.data == "200"){
                        window.localStorage.removeItem('tname')
                        window.localStorage.removeItem('token')
                        window.localStorage.removeItem('tpsw')
                        top.document.location.href = '../login.html'
                    } else {
                        that.$message.error('注销失败')
                    }
                })
                .catch(function (error) {
                    that.$message.error('系统错误：' + error.data)
                })
        }
    }
})