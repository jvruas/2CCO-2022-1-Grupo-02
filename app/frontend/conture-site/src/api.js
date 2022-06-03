import axios from "axios";

const api = axios.create({
    baseURL: "http://localhost:8080/usuarios"
})

function adicionarUsuario() {
    axios.post (api, {
            "email": "",
            "senha": "",
            "nome": "",
            "sobrenome": "",
            "cpf": "",
            "genero": "",
            "dataNascimento": "",
            "estadoCivil": "",
            "telefone": "",
            "cep": "",
            "grauEscolaridade": "",
            "situacaoAtual": {
              "idSituacaoAtual": 0,
            }        
    })
}

export default api;