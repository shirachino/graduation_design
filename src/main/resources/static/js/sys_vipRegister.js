import '../plug/echarts/echarts.js'
import '../plug/vue/vue.min.js'
import '../plug/element-ui/element-ui.js'

Vue.config.devtools = true;

var vipRegVm = new Vue({
    el: '#vipForm',
    data() {
        return {
            userInfo: {
                userName: ''
            },
            labelPosition: 'right',
            registerVipInfo: {
                vipName: '',
                vipPhone: '',
                vipBirthday: '',
                vipBalance: 0
            },
            regVipFormRules: {
                vipName: [
                    {required: true, message: '请输入姓名', trigger: 'blur'},
                    {min: 2, max: 4, message: '长度在 3 到 5 个字符', trigger: 'blur'}
                ],
                vipPhone: [
                    {required: true, message: '请输入手机号', trigger: 'blur'},
                    {
                        pattern: /^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/,
                        message: '请输入正确的手机号',
                        trigger: 'blur'
                    }
                ],
                vipBalance: [
                    {required: true, message: '请输入充值金额，不充值请填0', trigger: 'blur'},
                    {type: 'number', message: '充值金额必须为数字', trigger: 'blur'}
                ],
            },
            vipRegId: '202210000',
            vipRegStatus: '0',
            isPhoneUsed: false
        }
    },
    created() {
        this.userInfo.userName = window.localStorage.getItem('tname')
    },
    methods: {
        searchPhoneUsed(){
            let that = this
            let requestUrl = `/isPhoneUsed?userName=${this.userInfo.userName}&vipPhone=${this.registerVipInfo.vipPhone}`
            axios.get(requestUrl).then(function (result) {
                that.isPhoneUsed = result.data
            })
        },
        submitForm(formName) {
            let that = this
            this.$refs[formName].validate((valid) => {
                if (valid && !that.isPhoneUsed) {
                    let qs = Qs
                    let requestUrl = `/regVip?userName=${this.userInfo.userName}&`
                    axios({
                        method: 'post',
                        url: requestUrl,
                        data: qs.stringify(that.registerVipInfo)
                    }).then(function (result) {
                        that.vipRegStatus = result.data.status
                        that.vipRegId = result.data.data
                    })
                } else {
                    this.$message({
                        type: 'error',
                        message: '请检查是否输入正确，手机号是否被注册'
                    });
                    return false;
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        }
    }
})