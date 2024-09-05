import service from ".";

const wardApi = {
    insert(ward) {
        // 发送axios请求给服务器
        return service.post('/user/ward', ward);
    },
    delete(wid) {
        return service.delete(`/user/ward/${wid}`);  
    },
    update(ward) {
        return service.put('/user/ward', ward);
    },
    selectAll() {
        return service.get('/user/ward/all');
    },
    selectByWid(wid) {
        return service.get(`/user/ward/${wid}`);
    },
    selectByDid(did) {
        return service.get(`/user/ward/${did}`);
    }
}

export default wardApi;