import service from ".";
import qs from'qs';

const adminApi = {
    login(admin) {
        return service.post("login", qs.stringify(admin));
    },
    getLoginInfo() {
        return service.get("/user/info/userInfo")
    },
    captcha() {
        return service.get("/captcha")
    }
}
export default adminApi;