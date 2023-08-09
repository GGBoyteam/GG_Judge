import request from '@/utils/request'

export function getProblem(id){
    return request({
        url: '/oj/problem/info/'+id,
        method: 'get',
    })
}

export function getSubmissionsByPid(pid,query){
    return request({
        url: '/oj/problem/submissions/'+pid,
        method: 'get',
        params: query
    })
}