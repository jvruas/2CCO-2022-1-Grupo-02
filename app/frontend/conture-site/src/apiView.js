import axios from "axios";

const apiView = axios.create({
    baseURL: "http://localhost:8084/view"
})

export default apiView;