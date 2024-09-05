import service from ".";

const nurseApi = {
    insert(patient) {
        // 发送axios请求给服务器
        return service.post('/user/nurse', nurse);
    },
    delete(nid) {
        return service.delete(`/user/nurse/${nid}`);  
    },
    update(nurse) {
        return service.put('/user/nurse/', nurse);
    },
    selectAll() {
        return service.get('/user/nurse/');
    },
    selectByPage(pageNum, nname) {
        return service.get('/user/nurse/', {
            params: {
                pageNum,
                nname
            }
        });
    },
    selectByNid(nid) {
        return service.get(`/user/nurse/byNid/${nid}`);
    },
    selectByDid(did) {
        return service.get(`/user/nurse/byDid/${did}`);
    }
}

export default nurseApi;