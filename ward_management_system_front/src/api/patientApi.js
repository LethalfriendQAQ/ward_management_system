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
    selectByPage(pageNum, pname) {
        return service.get('/user/patient', {
            params: {
                pageNum,
                pname
            }
        });
    },
    selectByPid(pid) {
        return service.get(`/user/patient/${pid}`);
    }
}

export default patientApi;
