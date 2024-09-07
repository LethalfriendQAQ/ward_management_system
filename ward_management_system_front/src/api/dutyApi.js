import service from ".";

const dutyApi = {
    //查询所有
    selectAll() {
        return service.get('/user/duty/selectAll');
    },
    //获取所有护士
    allNurse() {
        return service.get("/user/duty/allNurse")
    }
};

export default dutyApi;