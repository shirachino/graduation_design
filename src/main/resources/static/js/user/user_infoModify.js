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
                userIp:''
            },
            requestUrl: '',
            imageUrl: '',
            squareUrl: 'https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png'
        }
    },
    created() {
        this.userInfo.userName = window.localStorage.getItem('tname')
        this.requestUrl = `http://localhost:8080/uploadAvatar?userName=${this.userInfo.userName}`
        this.getUserInfo()
    },
    methods: {
        getUserInfo(){
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
            if(status == "201"){
                this.$message.success('更换头像成功！');
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
        }
    }
})