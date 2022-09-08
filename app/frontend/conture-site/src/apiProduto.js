import axios from "axios";

const apiProdutos = axios.create({
    baseURL: "http://localhost:8081/produtos"
})

export default apiProdutos;