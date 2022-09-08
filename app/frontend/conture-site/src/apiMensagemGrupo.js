import axios from "axios";

const apiMensagemGrupo = axios.create({
    baseURL: "http://localhost:8083/mensagem-grupo"
})

export default apiMensagemGrupo;