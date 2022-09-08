import axios from "axios";

const apiUsuario = axios.create({
    baseURL: "http://localhost:8080/usuarios"
})

export default apiUsuario;