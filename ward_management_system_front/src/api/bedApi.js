import service from ".";

const bedApi = {
    //添加
    insert(bed) {
        //发送axios请求给服务器
        return service.post('/user/bed', bed);
    },
    //根据ID删除
    delete(bid) {
        return service.delete(`/user/bed/${bid}`);
    },
    //修改
    update(bed) {
        return service.put('/user/bed' ,bed);
    },
    //查询所有
    selectAll() {
        return service.get('/user/bed');
    },
    //根据ID查询
    selectByBid(bid) {
        return service.get(`/user/bed/${bid}`);
    }
}

export default bedApi;