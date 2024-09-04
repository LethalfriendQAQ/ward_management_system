import service from ".";

const departmentApi = {
    //添加
    insert(department) {
        //发送axios请求给服务器
        return service.post('/user/department', department);
    },
    //根据ID删除
    delete(did) {
        return service.delete(`/user/department/${did}`);
    },
    //修改
    update(department) {
        return service.put('/user/department' ,department);
    },
    //查询所有
    selectAll() {
        return service.get('/user/department');
    },
    //根据ID查询
    selectById(did) {
        return service.get(`/user/department/${did}`);
    }
}

export default departmentApi;