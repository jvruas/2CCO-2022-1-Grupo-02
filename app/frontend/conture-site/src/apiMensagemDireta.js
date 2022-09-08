import axios from "axios";

const apiMensagemDireta = axios.create({
    baseURL: "http://localhost:8082/mensagem-direta"
})


export default apiMensagemDireta;