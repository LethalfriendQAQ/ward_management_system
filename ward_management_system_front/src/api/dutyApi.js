import service from ".";

const dutyApi = {
    //查询所有
    selectAll() {
        return service.get('/user/duty/selectAll');
    }
}

export default dutyApi;