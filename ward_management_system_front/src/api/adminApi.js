import service from ".";
import qs from'qs';

const adminApi = {
    login(admin) {
        return service.post("login", qs.stringify(admin));
    }
}
export default adminApi;