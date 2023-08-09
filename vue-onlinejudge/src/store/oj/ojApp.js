import Cookies from 'js-cookie'

const useOjAppStore = defineStore(
    'ojApp',
    {
        state: ()=>({
            topNav:{
                hide: false,
                select: 0,
                toSystemRefresh: false
            }
        }),
        actions: {
            hiddenTopNav(){
                this.topNav.hide=true;
            },
            changeSelect(data){
                this.topNav.select=data;
            }
        }
    })

export default useOjAppStore