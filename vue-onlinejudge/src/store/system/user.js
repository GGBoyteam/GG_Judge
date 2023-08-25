import { login, logout, getInfo } from '@/api/login'
import { getToken, setToken, removeToken } from '@/utils/auth'
import defAva from '@/assets/images/profile.jpg'

const useUserStore = defineStore(
  'user',
  {
    state: () => ({
      token: getToken(),
      name: '',
      avatar: '',
      roles: [],
      permissions: []
    }),
    actions: {
      // 登录
      login(userInfo) {
        const username = userInfo.username.trim()
        const password = userInfo.password
        const code = userInfo.code
        const uuid = userInfo.uuid
        return new Promise((resolve, reject) => {
          login(username, password, code, uuid).then(res => {
            setToken(res.data.token)
            this.token = res.data.token
            resolve()
          }).catch(error => {
            reject(error)
          })
        })
      },
      // 获取用户信息
      getInfo() {
        return new Promise((resolve, reject) => {
          getInfo().then(res => {
            console.log(res)
            const user = res.data
            //const avatar = (user.avatar == "" || user.avatar == null) ? defAva : import.meta.env.VITE_APP_BASE_API + user.avatar;

            if (user.permissions) { // 验证返回的roles是否是一个非空数组
              console.log( user.permissions)
              this.roles=[]
              this.roles.push(user.info.roleId)
              this.permissions=[]
              user.permissions.forEach((permission)=>{
                this.permissions.push(permission.permission)
              })
              console.log(this.permissions)
            } else {
              this.roles = ['ROLE_DEFAULT']
            }
            this.name = user.username
            resolve(res)
          }).catch(error => {
            reject(error)
          })
        })
      },
      // 退出系统
      logOut() {
        return new Promise((resolve, reject) => {
          logout(this.token).then(() => {
            this.token = ''
            this.roles = []
            this.permissions = []
            removeToken()
            resolve()
          }).catch(error => {
            reject(error)
          })
        })
      }
    }
  })

export default useUserStore
