import service from ".";

const dutyApi = {
    //添加
    insert(duty) {
        return service.post(`/user/duty/insert/${duty}`)
    },
    update(duty) {
        return service.put('/user/duty' ,duty);
    },
    delete(dutyId) {
        return service.delete(`/user/duty/${dutyId}`)
    },
    //查询所有
    selectAll() {
        return service.get('/user/duty/selectAll');
    },
    //获取所有护士
    allNurse(dutyId) {
        return service.get(`/user/duty/allNurse/${dutyId}`)
    },
    //分配护士
    insertDutyIdAndNid(dutyId, nids) {
        return service.post("/user/duty/dutyIdAndNid", {
            dutyId,
            nids
        });
    },
    selectByDutyId(dutyId) {
        return service.get(`/user/duty/selectByDutyId/${dutyId}`)
    }
};

export default dutyApi;