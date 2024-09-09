import service from ".";

const patientApi = {
    insert(patient) {
        // 发送axios请求给服务器
        return service.post('/user/patient', patient);
    },
    delete(pid) {
        return service.delete(`/user/patient/${pid}`);  
    },
    update(patient) {
        return service.put('/user/patient', patient);
    },
    selectAll() {
        return service.get('/user/patient');
    },
    selectByPage(pageNum, pname, did, pstatus) {
        return service.get('/user/patient/selectByPage', {
            params: {
                pageNum,
                pname,
                did,
                pstatus
            }
        });
    },
    selectByPid(pid) {
        return service.get(`/user/patient/${pid}`);
    },
    getPatientCountByDepartment() {
        return service.get('/user/patient/patientCountByDepartment');
    }
}

export default patientApi;
